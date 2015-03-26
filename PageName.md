# Introduction #

One of the first project chats

# Details #
```
9:23 AM 
well,,
  
I worked on the custom colors implementation for jcurses
  
it is an initial implementation
 
Eben: nice
 
me: I think I must enhance it, it is flawed because it will always favour gray colores
  
colors
9:24 AM 
Eben: oh
 
me: it works, but could be better
 
Eben: as I was working with it I was thinking, is there a reason to not just use awt.Color instead of CSIColor?
 
me: pride ;)
 
Eben: hehe
9:26 AM 
I pretty much ended up converting between int, Color, and CSIColor on the fly for the swing implementation
 
me: I saw...
  
i think we must make a small change,
 
Eben: sure, what are you thinking?
9:27 AM 
me: let me check
9:28 AM 
methods getColorFromCSIColor(CSIColor color) and getCSIColorFromColor(Color color) always return new object isntances, which is very expensive
  
specially since they are called a LOT
 
Eben: ah, that is true
 
me: so I think we must reuse Color objects, are they inmutable?
9:29 AM 
Eben: I was thinking that we can actualy change the int values of the constants to the hex values that equal those colors, then conversion will be much quicker
9:30 AM 
me: hmm... or we can add a final Hashtable that maps hex values to their constant counterparts
9:31 AM 
Eben: yes, that would work as well
9:32 AM 
the trick is that ulitmately the swing implementation of plot will require a awt.Color object, so we'll have to create them eventualy and regularly
9:33 AM 
part of the issue for me is that since I'm making non-standard colors, I can't just use constant colors.
9:34 AM 
it might be best to use awt.Color objects and use the static final colors in there, and add a couple. perhaps make CSIColor extend awt.Color, that way we can add some more static final items
 
me: yeah, but we can caché the created objects
  
we can keep a pool of java.awt.Color colors,
  
so you only create each color once
9:35 AM 
(each custom color)
 
Eben: i don't think most people will use color the way I am, but each level of my game right now makes about a hundred unique color objects (each wall has a different color)
 
me: that would work for both methods I think
 
Eben: yep
9:36 AM 
me: yeah, having one hundred objects is ok, creating 200 colors for each rendering phase is chaotic! :D
 
Eben: times like this I wish there was purposeful garbage collection :D
 
me: laziness! :)
 
Eben: yep
9:37 AM 
me: o
  
I also added LUCK
  
it has been integrated into the project
  
so can be used to test things
 
Eben: cool
 
me: I created the toybox package in which you can create modified versions of LUCK
9:38 AM 
I for example did one to test the custom colors
 
Eben: ah
9:41 AM 
looking back over what I did... one of the things that made me call the creation of new objects more than I needed to was comparison.
9:42 AM 
me: where is comparison used?
9:45 AM 
Eben: to check if the object already there is the same so no change is needed
9:47 AM 
er, when printing a char
9:48 AM 
me: I dont see why a new instance would be needed :P
9:49 AM 
Eben: because CSIColor doesn't have a comparision function right now... so putting that in is now on the to do list
9:50 AM 
The more I look at it, the more I think we should go with static final hexadecimal integers for the basic colors. I think assuming alpha of opaque for a roguelike is pretty reasonable
9:51 AM 
me: that would reduce a lot of mapping work
 
Eben: yep
  
and I think that the change will be transparent to current implementations.
9:52 AM 
me: well... correct implementations thats it...
 
Eben: hehe
 
me: LUCK assumes Colors are 1 to 16
  
but you know, it is a <1KBRL
 
Eben: true
14 minutes
10:07 AM 
Eben: okay, after looking things over, my plan is to modify CSIColor so that it extends awt.Color, then add in final static fields for the colors that libjcsi use that aren't in awt.Color.
  
that will leave the implementation that LUCK is using alone, but allow for further expansion without too much trouble
10:11 AM 
me: well, but the idea is to keep the base specification free of dependencies such as that,
  
although AWT is part of the standard JFC, but we are creating dependance with a whole package
10:12 AM 
I think we can do something different,
  
make the constants be hex values as you say
 
Eben: okay
 
me: and add them an array
  
so colors[0] = hex0
 
Eben: ah, i see
10:13 AM 
me: it would be something like the default DOS pallete
 
Eben: and have an enum that gives the color as the index?
 
me: just in case some lazy <1KBRL developers wants to use that
10:14 AM 
yeah, I was about to suggest that,,
 
Eben: hehe
 
me: making Color be an enum
  
we must look for ways of libjcsi not losing its simplicity to use though
 
Eben: i agree
10:17 AM 
me: so, CSIColor an enum instead of a class?
  
with methods to retrieve a CSI color based on an index
 
Eben: I think that will work
 
me: well but then, we are back to the same problem :P
  
of having the hex values
10:18 AM 
Eben: well, we can have the hex value as a field in the enum
 
me: yeah
  
I kind of remember that
  
havent used it a lot
 
Eben: I was thinking of a new ColorEnum just for the enumeration
10:19 AM 
I used and enum for my monstertypes, it made the coding a bit easier to look at and maintain
  
I'll send you a copy of that so you can see
10:20 AM 
it seemed kind of awkward at first to use an enum that way, but it did make the code a lot smaller and more manageable
10:22 AM 
i think it might be useful to have a ColorEnum class as well as CSIColor. that way we can still have custom colors, but the standard colors won't be as awkward
10:23 AM 
ooooooh, I just had an idea
  
let me take a look at the code, brb
6 minutes
10:29 AM 
me: ok
8 minutes
10:38 AM 
Eben: okay, here's what I'm thinking...
10:42 AM 
we leave the constants in ConsoleSystemInterface the way they are, leave CSIColor the way it is, add CSIColorEnum to provide good hexadecimal color constants (which will have the effect of deprecating the original constants, but that's okay) and build CSIColorComparator in order to check if those three different items (and also awt.Color) are equivilent when needed.
10:43 AM 
The only drawback will be that the ints 1 through 16 will become awkward because they will have to mean the original colors in CSI, instead of near black colors, but that's probably not a big deal
  
perhaps libjcsi 2.0 could remove the old ints :)
 
me: :P
10:44 AM 
hmm... hmm
  
looks fine but I dont see a reason to keep the enum and the class? the class has no behaviour
  
I think it would be better modelled as an enum, no?
 
Eben: keeping the class will allow for custom colors
10:45 AM 
since enums are final static, you would be forced to either use only the defined colors or to go around it and use awt.Color, which we want to avoid
 
me: ah I see
  
sorry about that
 
Eben: so for most users, the enum will be enough
  
but for crazy me, i want the class too :D
10:46 AM 
me: technically looks great
  
then again we must discuss the modifications over the interface..
  
we would have three kind of print methods now
  
one for each kind of color
 
Eben: that is true
10:47 AM 
I think I can clean that up though
 
me: may be only having the "int" one and the CSIColor one
  
so if you use the enum you have to get its CSIColor?
 
Eben: no
10:48 AM 
the enum would use the hex int that is directly compatible with the awt.Color needed in the plot command
  
but I'd have a comparator that could check to see if the enum was the same as a CSIColor that had been custom defined
 
me: so the enum would not be used in the base interface?
  
it would be provided as an auxiliar for implementations?
10:49 AM 
Eben: it would. it would effectively replace and expand the ints in CSI
10:50 AM 
me: what will happen with the calls to print(x,y,inyt)?
  
*int
 
Eben: the exact same thing as now. the enum is an object, so to get the information out of it you'd want something like CSIColorEnum.ORANGE
10:51 AM 
awwww poo, i just got that
  
erm, i see a kludge to fix that....
10:52 AM 
me: usage of CSIColorEnum.ORANGE.intValue?
 
Eben: yes, that would be rightr
  
so then print(x,y,CSIColorEnum) would get added
10:53 AM 
and you'd pass CSIColorEnum.ORANGE and it would print color.intValue
 
me: no sense to include the original method then
  
just stick to the enums and CSIColorEnum
  
errr I mean
 
Eben: well that's what I meant when i said it would end up depricated
10:54 AM 
me: the CSIColor, and the CSIColorEnum
  
and having the array of CSIColorEnum
  
indexed by the actual constants
 
Eben: ah, i can see how that would work
10:55 AM 
me: so I would call print(x,y,ConsoleSystemInterface.colors[i]));;
  
err
  
print(x,y,ConsoleSystemInterface.colors[ConsoleSystemInterface.RED])); yuck
10:56 AM 
may be an static import would fix the awkardness
10:57 AM 
Eben: well, the print(x,y,int) can stay, it'll just have a switch statement that'll call print(x,y,CSIColorEnum) that's appropriate
 
me: yeah, the original method must be kept as depreciated for backward compatiblity
  
Although I can see somebody using colors 1 to 16
10:58 AM 
they are one kind of adhoc standard
 
Eben: yep
 
me: then we shouldnt really mark it as deprecated
 
Eben: yea, it won't be
  
it'll be optional, along with the enum, use whichever works best for the implementation
 
me: wock
10:59 AM 
there is an issue
 
Eben: hehe
 
me: ConsoleSystemInterface is an.. interface
  
so we would have to delegate a correct implementation of the "int" method
  
or make it an abstract class and destroy backward compatibility
11:00 AM 
or add an abstract class along the interface, which goes against its simplicity...
  
then the implementations would extend the abstract class instead of implementing the interface
11:02 AM 
Eben: i think it's fine as it is... adding the enum and CSIColor mainly help those using the swing side (like me).
 
me: going for the 1st option?
11:03 AM 
Eben: the curses side needs to have the interpretor for the CSIColor object anyway, so it might default to the 1-16 option and the CSIColor and enum conform to it
  
i think in that case, keeping the interface but delegating makes sense
11:05 AM 
me: so, for your swing implementation,
  
you would use the hex values of the CSIColor
  
and create awtColors and cache them?
11:06 AM 
Eben: erm, I just thought of something
  
the way I was going to implement it makes sense if the screen doesn't scroll, but wouldn't work well if there is scrolling
11:08 AM 
for the most part it's my walls that are randomly colored... i could redo them so that they read randomly from a collection of pre-created CSIColor objects (which would then correspond internally to a collection of awt.Color objects)
  
does that make sense?
11:09 AM 
me: I think a better solution would be chaching the CSIColor objects xD
11:10 AM 
you may have your collection of objects, but in the end will need to send them to the wCSI
  
so what do you do there again, create a new awtColor object?
  
so, I propose having a hashmap in the wCSI
11:11 AM 
indexed by the CSIColor key against a awtColor
  
so your getColorFromCSIColor would do a lookup over that table? and if the entry doesnt exist just add it
  
(am I missing something?)
11:12 AM 
Eben: that sounds good
  
and I should add a cleanup so that on level change (for example) you could clear out the table
11:14 AM 
me: well, but the hashmap is an artifact specific to the WCSI implementation...
  
so I dont know how that would fit into the base specification?
11:15 AM 
Eben: so to reiterate, I'm going to add CSIColorEnum with hex values for several colors. I'm going to rework the swing side to use a hash table for caching
 
me: but the cleanup idea is nice, it would be left to the implementation to decide what to do?
 
Eben: erm, since I'm not sure how you're going to convert custom colors for the curses side, I'm not sure
  
yep, I think it would have to be implementation specific
 
me: the thing is... it is too open... the user of the lib wont know what is its real purpose?
11:16 AM 
since different implementations would do different things..
  
well,, we can decide on that later :P
  
one thing at a time
 
Eben: hm...
 
me: yeah, you do the enum job,
11:17 AM 
and I will work on a better aproximation algorithm
 
Eben: okay
 
me: I think I will then adjust it to CSIColorEnum, since I already have final static CSIColors for the 16 of them
  
used in the calculation
 
Eben: okay, sounds good
11:18 AM 
me: nice talk :)
  
I am going on a meeting
  
I am recording this convo into the project wiki, for later nostalgia
 
Eben: hehe, sounds good
```