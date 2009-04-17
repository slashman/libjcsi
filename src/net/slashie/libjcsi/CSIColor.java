package net.slashie.libjcsi;

import java.util.ArrayList;

/**
 * Allows for the use of custom colors.  These colors are comparable for equality
 * but the ordering of them has more to do with saturation than color, so if
 * placed in order the output may not be what is expected.
 * 
 * Has both a small pallet called DEFAULT_PALLET which contains the basic 16
 * colors from curses implementations.
 * 
 * Has an extended pallet called FULL_PALLET which contains a wide variety of
 * custom named colors.
 * 
 * Can be used to make any color with aRGB values from 0-255.
 * 
 * @author Santiago Zapata
 * @author Eben Howard  
 */
public class CSIColor implements Comparable {

    private final static int OPAQUE = 255;// 255 is opaque for the all channels
    private int value; // this is where the color value is actually stored //964B00 == browner
    /**
     *<pre><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp@&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp@&nbsp</font></font><font style="background-color: #000000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BLACK = new CSIColor(0x000000);
    /**
     *<pre><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=0000ff>&nbsp@&nbsp</font><font style="background-color: #888888;" color=0000ff>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=0000ff>&nbsp@&nbsp</font></font><font style="background-color: #0000ff;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=0000ff>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=0000ff>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=0000ff>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=0000ff>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=0000ff>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BLUE = new CSIColor(0x0000ff);
    /**
     *<pre><font style="background-color: #808000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #808000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #808000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=808000>&nbsp@&nbsp</font><font style="background-color: #888888;" color=808000>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=808000>&nbsp@&nbsp</font></font><font style="background-color: #808000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #808000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #808000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=808000>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=808000>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=808000>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=808000>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=808000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BROWN = new CSIColor(0x808000);
    /**
     *<pre><font style="background-color: #00ffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ffff;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00ffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=00ffff>&nbsp@&nbsp</font><font style="background-color: #888888;" color=00ffff>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=00ffff>&nbsp@&nbsp</font></font><font style="background-color: #00ffff;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00ffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ffff;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00ffff>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=00ffff>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=00ffff>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=00ffff>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=00ffff>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CYAN = new CSIColor(0x00ffff);
    /**
     * Duplicated as AQUA for usability
     *<pre><font style="background-color: #0000c8;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000c8;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #0000c8;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=0000c8>&nbsp@&nbsp</font><font style="background-color: #888888;" color=0000c8>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=0000c8>&nbsp@&nbsp</font></font><font style="background-color: #0000c8;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #0000c8;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000c8;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=0000c8>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=0000c8>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=0000c8>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=0000c8>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=0000c8>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_BLUE = new CSIColor(0x0000c8);
    /**
     *<pre><font style="background-color: #404040;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #404040;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #404040;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=404040>&nbsp@&nbsp</font><font style="background-color: #888888;" color=404040>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=404040>&nbsp@&nbsp</font></font><font style="background-color: #404040;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #404040;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #404040;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=404040>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=404040>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=404040>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=404040>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=404040>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_GRAY = new CSIColor(0x404040);
    /**
     *<pre><font style="background-color: #800000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #800000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #800000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=800000>&nbsp@&nbsp</font><font style="background-color: #888888;" color=800000>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=800000>&nbsp@&nbsp</font></font><font style="background-color: #800000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #800000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #800000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=800000>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=800000>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=800000>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=800000>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=800000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_RED = new CSIColor(0x800000);
    /**
     *<pre><font style="background-color: #808080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #808080;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #808080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=808080>&nbsp@&nbsp</font><font style="background-color: #888888;" color=808080>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=808080>&nbsp@&nbsp</font></font><font style="background-color: #808080;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #808080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #808080;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=808080>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=808080>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=808080>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=808080>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=808080>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GRAY = new CSIColor(0x808080);
    /**
     *<pre><font style="background-color: #008000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #008000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #008000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=008000>&nbsp@&nbsp</font><font style="background-color: #888888;" color=008000>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=008000>&nbsp@&nbsp</font></font><font style="background-color: #008000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #008000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #008000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=008000>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=008000>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=008000>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=008000>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=008000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GREEN = new CSIColor(0x008000);
    /**
     * Duplicated as LIGHT_GREEN for usability
     *<pre><font style="background-color: #00FF00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00FF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00FF00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=00FF00>&nbsp@&nbsp</font><font style="background-color: #888888;" color=00FF00>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=00FF00>&nbsp@&nbsp</font></font><font style="background-color: #00FF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00FF00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00FF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00FF00>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=00FF00>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=00FF00>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=00FF00>&nbsp@&nbsp</font><font style="background-color: #964B00;" color=00FF00>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIME = new CSIColor(0x00FF00);
    /**
     *<pre><font style="background-color: #c0c0c0;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #c0c0c0;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #c0c0c0;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=c0c0c0>&nbsp@&nbsp</font><font style="background-color: #888888;" color=c0c0c0>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=c0c0c0>&nbsp@&nbsp</font></font><font style="background-color: #c0c0c0;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #c0c0c0;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #c0c0c0;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=c0c0c0>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=c0c0c0>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=c0c0c0>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=c0c0c0>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=c0c0c0>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIGHT_GRAY = new CSIColor(0xc0c0c0);
    /**
     *<pre><font style="background-color: #ff00ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ff00ff;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff00ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ff00ff>&nbsp@&nbsp</font><font style="background-color: #888888;" color=ff00ff>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=ff00ff>&nbsp@&nbsp</font></font><font style="background-color: #ff00ff;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ff00ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ff00ff;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ff00ff>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=ff00ff>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=ff00ff>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=ff00ff>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=ff00ff>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MAGENTA = new CSIColor(0xff00ff);
    /**
     *<pre><font style="background-color: #ffc800;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffc800;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ffc800;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ffc800>&nbsp@&nbsp</font><font style="background-color: #888888;" color=ffc800>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=ffc800>&nbsp@&nbsp</font></font><font style="background-color: #ffc800;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ffc800;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffc800;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ffc800>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=ffc800>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=ffc800>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=ffc800>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=ffc800>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ORANGE = new CSIColor(0xffc800);
    /**
     *<pre><font style="background-color: #ffafaf;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffafaf;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ffafaf;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ffafaf>&nbsp@&nbsp</font><font style="background-color: #888888;" color=ffafaf>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=ffafaf>&nbsp@&nbsp</font></font><font style="background-color: #ffafaf;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ffafaf;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffc800;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ffafaf>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=ffafaf>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=ffafaf>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=ffafaf>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=ffafaf>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor PINK = new CSIColor(0xffafaf);
    /**
     *<pre><font style="background-color: #800080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #800080;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #800080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=800080>&nbsp@&nbsp</font><font style="background-color: #888888;" color=800080>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=800080>&nbsp@&nbsp</font></font><font style="background-color: #800080;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #800080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #800080;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=800080>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=800080>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=800080>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=800080>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=800080>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor PURPLE = new CSIColor(0x800080);
    /**
     *<pre><font style="background-color: #008080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #008080;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #008080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=800080>&nbsp@&nbsp</font><font style="background-color: #888888;" color=008080>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=008080>&nbsp@&nbsp</font></font><font style="background-color: #008080;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #008080;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #008080;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=008080>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=800080>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=008080>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=008080>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=008080>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor TEAL = new CSIColor(0x008080);
    /**
     *<pre><font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ff0000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ff0000>&nbsp@&nbsp</font><font style="background-color: #888888;" color=ff0000>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=ff0000>&nbsp@&nbsp</font></font><font style="background-color: #ff0000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ff0000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ff0000>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=ff0000>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=ff0000>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=ff0000>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=ff0000>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor RED = new CSIColor(0xff0000);
    /**
     *<pre><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ffffff>&nbsp@&nbsp</font><font style="background-color: #888888;" color=ffffff>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=ffffff>&nbsp@&nbsp</font></font><font style="background-color: #ffffff;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ffffff>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=ffffff>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=ffffff>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=ffffff>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=ffffff>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor WHITE = new CSIColor(0xffffff);
    /**
     *<pre><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=ffff00>&nbsp@&nbsp</font><font style="background-color: #888888;" color=ffff00>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=ffff00>&nbsp@&nbsp</font></font><font style="background-color: #ffff00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ffff00>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=ffff00>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=ffff00>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=ffff00>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=ffff00>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor YELLOW = new CSIColor(0xffff00);
    /**
     *<pre><font style="background-color: #f0f8ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #f0f8ff;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #f0f8ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=f0f8ff>&nbsp@&nbsp</font><font style="background-color: #888888;" color=f0f8ff>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=f0f8ff>&nbsp@&nbsp</font></font><font style="background-color: #f0f8ff;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #f0f8ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #f0f8ff;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=f0f8ff>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=f0f8ff>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=f0f8ff>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=f0f8ff>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=f0f8ff>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ALICE_BLUE = new CSIColor(0xf0f8ff);
    /**
     *<pre><font style="background-color: #E32636;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #E32636;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E32636;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=E32636>&nbsp@&nbsp</font><font style="background-color: #888888;" color=E32636>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=E32636>&nbsp@&nbsp</font></font><font style="background-color: #E32636;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E32636;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #E32636;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E32636>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=E32636>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=E32636>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=E32636>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=E32636>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ALIZARIN = new CSIColor(0xE32636);
    /**
     *<pre><font style="background-color: #E52B50;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #E52B50;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E52B50;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=E52B50>&nbsp@&nbsp</font><font style="background-color: #888888;" color=E52B50>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=E52B50>&nbsp@&nbsp</font></font><font style="background-color: #E52B50;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E52B50;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #E52B50;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E52B50>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=E52B50>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=E52B50>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=E52B50>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=E52B50>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AMARANTH = new CSIColor(0xE52B50);
    /**
     *<pre><font style="background-color: #FFBF00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #FFBF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFBF00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=FFBF00>&nbsp@&nbsp</font><font style="background-color: #888888;" color=FFBF00>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=FFBF00>&nbsp@&nbsp</font></font><font style="background-color: #FFBF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFBF00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #FFBF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFBF00>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=FFBF00>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=FFBF00>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=FFBF00>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=FFBF00>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AMBER = new CSIColor(0xFFBF00);
    /**
     *<pre><font style="background-color: #9966CC;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #9966CC;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #9966CC;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=9966CC>&nbsp@&nbsp</font><font style="background-color: #888888;" color=9966CC>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=9966CC>&nbsp@&nbsp</font></font><font style="background-color: #9966CC;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #9966CC;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #9966CC;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=9966CC>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=9966CC>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=9966CC>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=9966CC>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=9966CC>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AMETHYST = new CSIColor(0x9966CC);
    /**
     *<pre><font style="background-color: #FBCEB1;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #FBCEB1;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FBCEB1;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=FBCEB1>&nbsp@&nbsp</font><font style="background-color: #888888;" color=FBCEB1>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=FBCEB1>&nbsp@&nbsp</font></font><font style="background-color: #FBCEB1;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FBCEB1;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #FBCEB1;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FBCEB1>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=FBCEB1>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=FBCEB1>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=FBCEB1>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=FBCEB1>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor APRICOT = new CSIColor(0xFBCEB1);
    /**
     * Duplicated as CYAN for backwards compatability
     *<pre><font style="background-color: #00FFFF;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00FFFF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00FFFF;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=00FFFF>&nbsp@&nbsp</font><font style="background-color: #888888;" color=00FFFF>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=00FFFF>&nbsp@&nbsp</font></font><font style="background-color: #00FFFF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00FFFF;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00FFFF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00FFFF>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=00FFFF>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=00FFFF>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=00FFFF>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=00FFFF>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AQUA = new CSIColor(0x00FFFF);
    /**
     *<pre><font style="background-color: #7FFFD4;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #7FFFD4;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #7FFFD4;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=7FFFD4>&nbsp@&nbsp</font><font style="background-color: #888888;" color=7FFFD4>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=7FFFD4>&nbsp@&nbsp</font></font><font style="background-color: #7FFFD4;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #7FFFD4;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #7FFFD4;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=7FFFD4>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=7FFFD4>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=7FFFD4>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=7FFFD4>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=7FFFD4>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AQUAMARINE = new CSIColor(0x7FFFD4);
    /**
     *<pre><font style="background-color: #4B5320;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #4B5320;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #4B5320;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=4B5320>&nbsp@&nbsp</font><font style="background-color: #888888;" color=4B5320>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=4B5320>&nbsp@&nbsp</font></font><font style="background-color: #4B5320;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #4B5320;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #4B5320;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=4B5320>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=4B5320>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=4B5320>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=4B5320>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=4B5320>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ARMY_GREEN = new CSIColor(0x4B5320);
    /**
     *<pre><font style="background-color: #7BA05B;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #7BA05B;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #7BA05B;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=7BA05B>&nbsp@&nbsp</font><font style="background-color: #888888;" color=7BA05B>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=7BA05B>&nbsp@&nbsp</font></font><font style="background-color: #7BA05B;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #7BA05B;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #7BA05B;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=7BA05B>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=7BA05B>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=7BA05B>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=7BA05B>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=7BA05B>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ASPARAGUS = new CSIColor(0x7BA05B);
    /**
     *<pre><font style="background-color: #FF9966;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #FF9966;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF9966;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=FF9966>&nbsp@&nbsp</font><font style="background-color: #888888;" color=FF9966>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=FF9966>&nbsp@&nbsp</font></font><font style="background-color: #FF9966;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF9966;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #FF9966;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF9966>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=FF9966>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=FF9966>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=FF9966>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=FF9966>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ATOMIC_TANGERINE = new CSIColor(0xFF9966);
    /**
     *<pre><font style="background-color: #6D351A;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #6D351A;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #6D351A;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=6D351A>&nbsp@&nbsp</font><font style="background-color: #888888;" color=6D351A>&nbsp@&nbsp</font><font style="background-color: #ffffff;" color=6D351A>&nbsp@&nbsp</font></font><font style="background-color: #6D351A;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #6D351A;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #6D351A;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=6D351A>&nbsp@&nbsp</font><font style="background-color: #ffff00;" color=6D351A>&nbsp@&nbsp</font><font style="background-color: #00ff00;" color=6D351A>&nbsp@&nbsp</font><font style="background-color: #0000ff;" color=6D351A>&nbsp@&nbsp</font><font style="background-color: #964b00;" color=6D351A>&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp</font><font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AUBURN = new CSIColor(0x6D351A);
    /**
     *<pre><font style="background-color: #007FFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #007FFF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #007FFF;" color=007FFF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #007FFF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #007FFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #007FFF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=007FFF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AZUL = new CSIColor(0x007FFF);
    /**
     *<pre><font style="background-color: #F0FFFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F0FFFF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F0FFFF;" color=F0FFFF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F0FFFF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F0FFFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F0FFFF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F0FFFF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor AZURE = new CSIColor(0xF0FFFF);
    /**
     *<pre><font style="background-color: #E0FFFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E0FFFF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E0FFFF;" color=E0FFFF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E0FFFF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E0FFFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E0FFFF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E0FFFF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BABY_BLUE = new CSIColor(0xE0FFFF);
    /**
     *<pre><font style="background-color: #F5F5DC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F5F5DC;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F5F5DC;" color=F5F5DC>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F5F5DC;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F5F5DC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F5F5DC;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F5F5DC>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BEIGE = new CSIColor(0xF5F5DC);
    /**
     *<pre><font style="background-color: #3D2B1F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #3D2B1F;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #3D2B1F;" color=3D2B1F>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #3D2B1F;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #3D2B1F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #3D2B1F;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=3D2B1F>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BISTRE = new CSIColor(0x3D2B1F);
    /**
     *<pre><font style="background-color: #333399;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #333399;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #333399;" color=333399>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #333399;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #333399;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #333399;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=333399>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor PIGMENT_BLUE = new CSIColor(0x333399);
    /**
     *<pre><font style="background-color: #00DDDD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00DDDD;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00DDDD;" color=00DDDD>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #00DDDD;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00DDDD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00DDDD;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00DDDD>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BLUE_GREEN = new CSIColor(0x00DDDD);
    /**
     *<pre><font style="background-color: #8A2BE2;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #8A2BE2;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #8A2BE2;" color=8A2BE2>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #8A2BE2;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #8A2BE2;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #8A2BE2;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=8A2BE2>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BLUE_VIOLET = new CSIColor(0x8A2BE2);
    /**
     *<pre><font style="background-color: #0095B6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0095B6;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #0095B6;" color=0095B6>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #0095B6;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #0095B6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0095B6;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=0095B6>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BONDI_BLUE = new CSIColor(0x0095B6);
    /**
     *<pre><font style="background-color: #B5A642;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B5A642;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B5A642;" color=B5A642>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B5A642;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B5A642;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B5A642;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B5A642>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BRASS = new CSIColor(0xB5A642);
    /**
     *<pre><font style="background-color: #66FF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #66FF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #66FF00;" color=66FF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #66FF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #66FF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #66FF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=66FF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BRIGHT_GREEN = new CSIColor(0x66FF00);
    /**
     *<pre><font style="background-color: #FF0080;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF0080;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF0080;" color=FF0080>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF0080;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF0080;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF0080;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF0080>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BRIGHT_PINK = new CSIColor(0xFF0080);
    /**
     *<pre><font style="background-color: #08E8DE;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #08E8DE;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #08E8DE;" color=08E8DE>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #08E8DE;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #08E8DE;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #08E8DE;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=08E8DE>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BRIGHT_TURQUOISE = new CSIColor(0x08E8DE);
    /**
     *<pre><font style="background-color: #FF55A3;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF55A3;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF55A3;" color=FF55A3>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF55A3;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF55A3;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF55A3;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF55A3>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BRILLIANT_ROSE = new CSIColor(0xFF55A3);
    /**
     *<pre><font style="background-color: #CD7F32;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD7F32;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CD7F32;" color=CD7F32>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CD7F32;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CD7F32;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD7F32;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CD7F32>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BRONZE = new CSIColor(0xCD7F32);
    /**
     *<pre><font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964B00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #964B00;" color=964B00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #964B00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #964B00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964B00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=964B00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BROWNER = new CSIColor(0x964B00);
    /**
     *<pre><font style="background-color: #F0DC82;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F0DC82;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F0DC82;" color=F0DC82>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F0DC82;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F0DC82;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F0DC82;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F0DC82>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BUFF = new CSIColor(0xF0DC82);
    /**
     *<pre><font style="background-color: #900020;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #900020;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #900020;" color=900020>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #900020;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #900020;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #900020;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=900020>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BURGUNDY = new CSIColor(0x900020);
    /**
     *<pre><font style="background-color: #CC5500;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CC5500;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CC5500;" color=CC5500>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CC5500;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CC5500;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CC5500;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CC5500>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BURNT_ORANGE = new CSIColor(0xCC5500);
    /**
     *<pre><font style="background-color: #E97451;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E97451;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E97451;" color=E97451>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E97451;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E97451;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E97451;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E97451>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BURNT_SIENNA = new CSIColor(0xE97451);
    /**
     *<pre><font style="background-color: #8A3324;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #8A3324;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #8A3324;" color=8A3324>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #8A3324;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #8A3324;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #8A3324;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=8A3324>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor BURNT_UMBER = new CSIColor(0x8A3324);
    /**
     *<pre><font style="background-color: #78866B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #78866B;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #78866B;" color=78866B>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #78866B;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #78866B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #78866B;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=78866B>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CAMO_GREEN = new CSIColor(0x78866B);
    /**
     *<pre><font style="background-color: #592720;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #592720;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #592720;" color=592720>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #592720;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #592720;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #592720;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=592720>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CAPUT_MORTUUM = new CSIColor(0x592720);
    /**
     *<pre><font style="background-color: #C41E3A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C41E3A;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #C41E3A;" color=C41E3A>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #C41E3A;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #C41E3A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C41E3A;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=C41E3A>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CARDINAL = new CSIColor(0xC41E3A);
    /**
     *<pre><font style="background-color: #960018;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #960018;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #960018;" color=960018>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #960018;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #960018;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #960018;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=960018>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CARMINE = new CSIColor(0x960018);
    /**
     *<pre><font style="background-color: #FFA6C9;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFA6C9;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFA6C9;" color=FFA6C9>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFA6C9;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFA6C9;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFA6C9;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFA6C9>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CARNATION_PINK = new CSIColor(0xFFA6C9);
    /**
     *<pre><font style="background-color: #99BADD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #99BADD;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #99BADD;" color=99BADD>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #99BADD;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #99BADD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #99BADD;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=99BADD>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CAROLINA_BLUE = new CSIColor(0x99BADD);
    /**
     *<pre><font style="background-color: #ED9121;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ED9121;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ED9121;" color=ED9121>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #ED9121;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ED9121;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ED9121;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ED9121>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CARROT_ORANGE = new CSIColor(0xED9121);
    /**
     *<pre><font style="background-color: #ACE1AF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ACE1AF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ACE1AF;" color=ACE1AF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #ACE1AF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ACE1AF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ACE1AF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ACE1AF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CELADON = new CSIColor(0xACE1AF);
    /**
     *<pre><font style="background-color: #DE3163;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DE3163;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #DE3163;" color=DE3163>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #DE3163;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #DE3163;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DE3163;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=DE3163>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CERISE = new CSIColor(0xDE3163);
    /**
     *<pre><font style="background-color: #007BA7;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #007BA7;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #007BA7;" color=007BA7>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #007BA7;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #007BA7;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #007BA7;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=007BA7>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CERULEAN = new CSIColor(0x007BA7);
    /**
     *<pre><font style="background-color: #2A52BE;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #2A52BE;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #2A52BE;" color=2A52BE>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #2A52BE;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #2A52BE;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #2A52BE;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=2A52BE>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CERULEAN_BLUE = new CSIColor(0x2A52BE);
    /**
     *<pre><font style="background-color: #DFFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DFFF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #DFFF00;" color=DFFF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #DFFF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #DFFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DFFF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=DFFF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CHARTREUSE = new CSIColor(0xDFFF00);
    /**
     *<pre><font style="background-color: #7FFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7FFF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #7FFF00;" color=7FFF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #7FFF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #7FFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7FFF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=7FFF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CHARTREUSE_GREEN = new CSIColor(0x7FFF00);
    /**
     *<pre><font style="background-color: #FFB7C5;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFB7C5;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFB7C5;" color=FFB7C5>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFB7C5;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFB7C5;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFB7C5;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFB7C5>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CHERRY_BLOSSOM = new CSIColor(0xFFB7C5);
    /**
     *<pre><font style="background-color: #CD5C5C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD5C5C;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CD5C5C;" color=CD5C5C>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CD5C5C;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CD5C5C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD5C5C;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CD5C5C>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CHESTNUT = new CSIColor(0xCD5C5C);
    /**
     *<pre><font style="background-color: #7B3F00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7B3F00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #7B3F00;" color=7B3F00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #7B3F00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #7B3F00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7B3F00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=7B3F00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CHOCOLATE = new CSIColor(0x7B3F00);
    /**
     *<pre><font style="background-color: #E34234;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E34234;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E34234;" color=E34234>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E34234;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E34234;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E34234;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E34234>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CINNABAR = new CSIColor(0xE34234);
    /**
     *<pre><font style="background-color: #D2691E;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #D2691E;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #D2691E;" color=D2691E>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #D2691E;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #D2691E;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #D2691E;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=D2691E>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CINNAMON = new CSIColor(0xD2691E);
    /**
     *<pre><font style="background-color: #0047AB;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0047AB;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #0047AB;" color=0047AB>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #0047AB;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #0047AB;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0047AB;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=0047AB>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor COBALT = new CSIColor(0x0047AB);
    /**
     *<pre><font style="background-color: #9BDDFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #9BDDFF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #9BDDFF;" color=9BDDFF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #9BDDFF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #9BDDFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #9BDDFF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=9BDDFF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor COLUMBIA_BLUE = new CSIColor(0x9BDDFF);
    /**
     *<pre><font style="background-color: #B87333;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B87333;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B87333;" color=B87333>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B87333;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B87333;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B87333;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B87333>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor COPPER = new CSIColor(0xB87333);
    /**
     *<pre><font style="background-color: #996666;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #996666;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #996666;" color=996666>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #996666;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #996666;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #996666;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=996666>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor COPPER_ROSE = new CSIColor(0x996666);
    /**
     *<pre><font style="background-color: #FF7F50;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF7F50;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF7F50;" color=FF7F50>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF7F50;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF7F50;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF7F50;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF7F50>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CORAL = new CSIColor(0xFF7F50);
    /**
     *<pre><font style="background-color: #FF4040;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF4040;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF4040;" color=FF4040>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF4040;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF4040;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF4040;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF4040>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CORAL_RED = new CSIColor(0xFF4040);
    /**
     *<pre><font style="background-color: #FBEC5D;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FBEC5D;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FBEC5D;" color=FBEC5D>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FBEC5D;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FBEC5D;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FBEC5D;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FBEC5D>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CORN = new CSIColor(0xFBEC5D);
    /**
     *<pre><font style="background-color: #6495ED;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #6495ED;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #6495ED;" color=6495ED>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #6495ED;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #6495ED;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #6495ED;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=6495ED>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CORNFLOWER_BLUE = new CSIColor(0x6495ED);
    /**
     *<pre><font style="background-color: #FFF8E7;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFF8E7;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFF8E7;" color=FFF8E7>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFF8E7;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFF8E7;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFF8E7;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFF8E7>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor COSMIC_LATTE = new CSIColor(0xFFF8E7);
    /**
     *<pre><font style="background-color: #FFFDD0;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFFDD0;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFFDD0;" color=FFFDD0>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFFDD0;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFFDD0;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFFDD0;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFFDD0>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CREAM = new CSIColor(0xFFFDD0);
    /**
     *<pre><font style="background-color: #DC143C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DC143C;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #DC143C;" color=DC143C>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #DC143C;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #DC143C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DC143C;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=DC143C>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor CRIMSON = new CSIColor(0xDC143C),  DARK_BROWN = new CSIColor(0x654321),  DARK_CERULEAN = new CSIColor(0x08457E),  DARK_CHESTNUT = new CSIColor(0x986960),  DARK_CORAL = new CSIColor(0xCD5B45),  DARK_GOLDENROD = new CSIColor(0xB8860B),  DARK_GREEN = new CSIColor(0x013220),  DARK_KHAKI = new CSIColor(0xBDB76B),  DARK_PASTEL_GREEN = new CSIColor(0x03C03C),  DARK_PINK = new CSIColor(0xE75480),  DARK_SCARLET = new CSIColor(0x560319),  DARK_SALMON = new CSIColor(0xE9967A),  DARK_SLATE_GRAY = new CSIColor(0x2F4F4F),  DARK_SPRING_GREEN = new CSIColor(0x177245),  DARK_TAN = new CSIColor(0x918151),  DARK_TURQUOISE = new CSIColor(0x00CED1),  DARK_VIOLET = new CSIColor(0x9400D3),  DEEP_CERISE = new CSIColor(0xDA3287),  DEEP_CHESTNUT = new CSIColor(0xB94E48),  DEEP_FUCHSIA = new CSIColor(0xC154C1),  DEEP_LILAC = new CSIColor(0x9955BB),  DEEP_MAGENTA = new CSIColor(0xCD00CC),  DEEP_PEACH = new CSIColor(0xFFCBA4),  DEEP_PINK = new CSIColor(0xFF1493),  DENIM = new CSIColor(0x1560BD),  DODGER_BLUE = new CSIColor(0x1E90FF),  ECRU = new CSIColor(0xC2B280),  EGYPTIAN_BLUE = new CSIColor(0x1034A6),  ELECTRIC_BLUE = new CSIColor(0x7DF9FF),  ELECTRIC_GREEN = new CSIColor(0x00FF00),  ELECTRIC_INDIGO = new CSIColor(0x6600FF),  ELECTRIC_LIME = new CSIColor(0xCCFF00),  ELECTRIC_PURPLE = new CSIColor(0xBF00FF),  EMERALD = new CSIColor(0x50C878),  EGGPLANT = new CSIColor(0x614051),  FALU_RED = new CSIColor(0x801818),  FERN_GREEN = new CSIColor(0x4F7942),  FIREBRICK = new CSIColor(0xB22222),  FLAX = new CSIColor(0xEEDC82),  FOREST_GREEN = new CSIColor(0x228B22),  FRENCH_ROSE = new CSIColor(0xF64A8A),  FUCSHIA_PINK = new CSIColor(0xFF77FF),  GAMBOGE = new CSIColor(0xE49B0F),  GOLD = new CSIColor(0xD4AF37),  GOLDEN = new CSIColor(0xFFD700),  GOLDEN_BROWN = new CSIColor(0x996515),  GOLDEN_YELLOW = new CSIColor(0xFFDF00),  GOLDENROD = new CSIColor(0xDAA520),  GRAY_ASPARAGUS = new CSIColor(0x465945),  GREEN_YELLOW = new CSIColor(0xADFF2F),  HAN_PURPLE = new CSIColor(0x5218FA),  HELIOTROPE = new CSIColor(0xDF73FF),  HOLLYWOOD_CERISE = new CSIColor(0xF400A1),  HOT_MAGENTA = new CSIColor(0xFF00CC),  HOT_PINK = new CSIColor(0xFF69B4),  INDIGO_DYE = new CSIColor(0x00416A),  INDIGO = new CSIColor(0x4B0082),  INTERNATIONAL_KLEIN_BLUE = new CSIColor(0x002FA7),  INTERNATIONAL_ORANGE = new CSIColor(0xFF4F00),  ISLAMIC_GREEN = new CSIColor(0x009000),  IVORY = new CSIColor(0xFFFFF0),  JADE = new CSIColor(0x00A86B),  KELLY_GREEN = new CSIColor(0x4CBB17),  KHAKI = new CSIColor(0xC3B091),  LIGHT_KHAKI = new CSIColor(0xF0E68C),  LAVENDER_FLORAL = new CSIColor(0xB57EDC),  LAVENDER = new CSIColor(0xE6E6FA),  LAVENDER_BLUE = new CSIColor(0xCCCCFF),  LAVENDER_BLUSH = new CSIColor(0xFFF0F5),  LAVENDER_GRAY = new CSIColor(0xC4C3D0),  LAVENDER_MAGENTA = new CSIColor(0xEE82EE),  LAVENDER_PINK = new CSIColor(0xFBAED2),  LAVENDER_PURPLE = new CSIColor(0x967BB6),  LAVENDER_ROSE = new CSIColor(0xFBA0E3),  LAWN_GREEN = new CSIColor(0x7CFC00),  LEMON_CHIFFON = new CSIColor(0xFFFACD),  LIGHT_BLUE = new CSIColor(0xADD8E6),  LIGHT_PINK = new CSIColor(0xFFB6C1),  LILAC = new CSIColor(0xC8A2C8),  LEMON = new CSIColor(0xFDE910),  LIGHT_LIME = new CSIColor(0xBFFF00),  LIME_GREEN = new CSIColor(0x32CD32),  LINEN = new CSIColor(0xFAF0E6),  MAGENTA_DYE = new CSIColor(0xCA1F7B),  MAGIC_MINT = new CSIColor(0xAAF0D1),  MAGNOLIA = new CSIColor(0xF8F4FF),  MALACHITE = new CSIColor(0x0BDA51),  MAROON = new CSIColor(0x800000),  LIGHT_MAROON = new CSIColor(0xB03060),  MAYA_BLUE = new CSIColor(0x73C2FB),  MAUVE = new CSIColor(0xE0B0FF),  MAUVE_TAUPE = new CSIColor(0x915F6D),  MEDIUM_BLUE = new CSIColor(0x0000CD),  MEDIUM_CARMINE = new CSIColor(0xAF4035),  MEDIUM_LAVENDER_MAGENTA = new CSIColor(0xCC99CC),  MEDIUM_PURPLE = new CSIColor(0x9370DB),  MEDIUM_SPRING_GREEN = new CSIColor(0x00FA9A),  MIDNIGHT_BLUE = new CSIColor(0x003366),  MINT_GREEN = new CSIColor(0x98FF98),  MISTY_ROSE = new CSIColor(0xFFE4E1),  MOSS_GREEN = new CSIColor(0xADDFAD),  MOUNTBATTEN_PINK = new CSIColor(0x997A8D),  MUSTARD = new CSIColor(0xFFDB58),  MYRTLE = new CSIColor(0x21421E),  NAVAJO_WHITE = new CSIColor(0xFFDEAD),  NAVY_BLUE = new CSIColor(0x000080),  OCHRE = new CSIColor(0xCC7722),  OLD_GOLD = new CSIColor(0xCFB53B),  OLD_LACE = new CSIColor(0xFDF5E6),  OLD_LAVENDER = new CSIColor(0x796878),  OLD_ROSE = new CSIColor(0xC08081),  OLIVE = new CSIColor(0x808000),  OLIVE_DRAB = new CSIColor(0x6B8E23),  OLIVINE = new CSIColor(0x9AB973),  ORANGE_PEEL = new CSIColor(0xFFA000),  ORANGE_RED = new CSIColor(0xFF4500),  ORCHID = new CSIColor(0xDA70D6),  PALE_BLUE = new CSIColor(0xAFEEEE),  PALE_BROWN = new CSIColor(0x987654),  PALE_CARMINE = new CSIColor(0xAF4035),  PALE_CHESTNUT = new CSIColor(0xDDADAF),  PALE_CORNFLOWER_BLUE = new CSIColor(0xABCDEF),  PALE_MAGENTA = new CSIColor(0xF984E5),  PALE_PINK = new CSIColor(0xFADADD),  PALE_RED_VIOLET = new CSIColor(0xDB7093),  PAPAYA_WHIP = new CSIColor(0xFFEFD5),  PASTEL_GREEN = new CSIColor(0x77DD77),  PASTEL_PINK = new CSIColor(0xFFD1DC),  PEACH = new CSIColor(0xFFE5B4),  PEACH_ORANGE = new CSIColor(0xFFCC99),  PEACH_YELLOW = new CSIColor(0xFADFAD),  PEAR = new CSIColor(0xD1E231),  PERIWINKLE = new CSIColor(0xCCCCFF),  PERSIAN_BLUE = new CSIColor(0x1C39BB),  PERSIAN_GREEN = new CSIColor(0x00A693),  PERSIAN_INDIGO = new CSIColor(0x32127A),  PERSIAN_RED = new CSIColor(0xCC3333),  PERSIAN_PINK = new CSIColor(0xF77FBE),  PERSIAN_ROSE = new CSIColor(0xFE28A2),  PERSIMMON = new CSIColor(0xEC5800),  PINE_GREEN = new CSIColor(0x01796F),  TRUE_PINK = new CSIColor(0xFFC0CB),  PINK_ORANGE = new CSIColor(0xFF9966),  PLATINUM = new CSIColor(0xE5E4E2),  PLUM = new CSIColor(0xCC99CC),  POWDER_BLUE = new CSIColor(0xB0E0E6),  PUCE = new CSIColor(0xCC8899),  PRUSSIAN_BLUE = new CSIColor(0x003153),  PSYCHEDELIC_PURPLE = new CSIColor(0xDD00FF),  PUMPKIN = new CSIColor(0xFF7518),  PURPLE_TAUPE = new CSIColor(0x50404D),  RAW_UMBER = new CSIColor(0x734A12),  RAZZMATAZZ = new CSIColor(0xE30B5C),  RED_PIGMENT = new CSIColor(0xED1C24),  RED_VIOLET = new CSIColor(0xC71585),  RICH_CARMINE = new CSIColor(0xD70040),  ROBIN_EGG_BLUE = new CSIColor(0x00CCCC),  ROSE = new CSIColor(0xFF007F),  ROSE_MADDER = new CSIColor(0xE32636),  ROSE_TAUPE = new CSIColor(0x905D5D),  ROYAL_BLUE = new CSIColor(0x4169E1),  ROYAL_PURPLE = new CSIColor(0x6B3FA0),  RUBY = new CSIColor(0xE0115F),  RUSSET = new CSIColor(0x80461B),  RUST = new CSIColor(0xB7410E),  SAFETY_ORANGE = new CSIColor(0xFF6600),  SAFFRON = new CSIColor(0xF4C430),  SALMON = new CSIColor(0xFF8C69),  SANDY_BROWN = new CSIColor(0xF4A460),  SANGRIA = new CSIColor(0x92000A),  SAPPHIRE = new CSIColor(0x082567),  SCARLET = new CSIColor(0xFF2400),  SCHOOL_BUS_YELLOW = new CSIColor(0xFFD800),  SEA_GREEN = new CSIColor(0x2E8B57),  SEASHELL = new CSIColor(0xFFF5EE),  SELECTIVE_YELLOW = new CSIColor(0xFFBA00),  SEPIA = new CSIColor(0x704214),  SHAMROCK_GREEN = new CSIColor(0x009E60),  SHOCKING_PINK = new CSIColor(0xFC0FC0),  SILVER = new CSIColor(0xC0C0C0),  SKY_BLUE = new CSIColor(0x87CEEB),  SLATE_GRAY = new CSIColor(0x708090),  SMALT = new CSIColor(0x003399),  SPRING_BUD = new CSIColor(0xA7FC00),  SPRING_GREEN = new CSIColor(0x00FF7F),  STEEL_BLUE = new CSIColor(0x4682B4),  TAN = new CSIColor(0xD2B48C),  TANGERINE = new CSIColor(0xF28500),  TANGERINE_YELLOW = new CSIColor(0xFFCC00),  TAUPE = new CSIColor(0x483C32),  TEA_GREEN = new CSIColor(0xD0F0C0),  TEA_ORANGE = new CSIColor(0xF88379),  TEA_ROSE = new CSIColor(0xF4C2C2),  TAWNY = new CSIColor(0xCD5700),  TERRA_COTTA = new CSIColor(0xE2725B),  THISTLE = new CSIColor(0xD8BFD8),  TOMATO = new CSIColor(0xFF6347),  TURQUOISE = new CSIColor(0x30D5C8),  TYRIAN_PURPLE = new CSIColor(0x66023C),  ULTRAMARINE = new CSIColor(0x120A8F),  VEGAS_GOLD = new CSIColor(0xC5B358),  VERMILION = new CSIColor(0xE34234),  VIOLET = new CSIColor(0x8B00FF),  LIGHT_VIOLET = new CSIColor(0xEE82EE),  VIRIDIAN = new CSIColor(0x40826D),  WHEAT = new CSIColor(0xF5DEB3),  WISTERIA = new CSIColor(0xC9A0DC),  YELLOW_GREEN = new CSIColor(0x9ACD32),  ZINNWALDITE = new CSIColor(0xEBC2AF);
    public static CSIColor[] FULL_PALLET = {
        BLACK,
        BLUE,
        BROWN,
        CYAN,
        DARK_BLUE,
        DARK_GRAY,
        DARK_RED,
        GRAY,
        GREEN,
        LIME,
        LIGHT_GRAY,
        MAGENTA,
        ORANGE,
        PINK,
        PURPLE,
        TEAL,
        RED,
        WHITE,
        YELLOW,
        ALICE_BLUE,
        ALIZARIN,
        AMARANTH,
        AMBER,
        AMETHYST,
        APRICOT,
        AQUAMARINE,
        ARMY_GREEN,
        ASPARAGUS,
        ATOMIC_TANGERINE,
        AUBURN,
        AZUL,
        AZURE,
        BABY_BLUE,
        BEIGE,
        BISTRE,
        PIGMENT_BLUE,
        BLUE_GREEN,
        BLUE_VIOLET,
        BONDI_BLUE,
        BRASS,
        BRIGHT_GREEN,
        BRIGHT_PINK,
        BRIGHT_TURQUOISE,
        BRILLIANT_ROSE,
        BRONZE,
        BROWNER,
        BUFF,
        BURGUNDY,
        BURNT_ORANGE,
        BURNT_SIENNA,
        BURNT_UMBER,
        CAMO_GREEN,
        CAPUT_MORTUUM,
        CARDINAL,
        CARMINE,
        CARNATION_PINK,
        CAROLINA_BLUE,
        CARROT_ORANGE,
        CELADON,
        CERISE,
        CERULEAN,
        CERULEAN_BLUE,
        CHARTREUSE,
        CHARTREUSE_GREEN,
        CHERRY_BLOSSOM,
        CHESTNUT,
        CHOCOLATE,
        CINNABAR,
        CINNAMON,
        COBALT,
        COLUMBIA_BLUE,
        COPPER,
        COPPER_ROSE,
        CORAL,
        CORAL_RED,
        CORN,
        CORNFLOWER_BLUE,
        COSMIC_LATTE,
        CREAM,
        CRIMSON,
        DARK_BROWN,
        DARK_CERULEAN,
        DARK_CHESTNUT,
        DARK_CORAL,
        DARK_GOLDENROD,
        DARK_GREEN,
        DARK_KHAKI,
        DARK_PASTEL_GREEN,
        DARK_PINK,
        DARK_SCARLET,
        DARK_SALMON,
        DARK_SLATE_GRAY,
        DARK_SPRING_GREEN,
        DARK_TAN,
        DARK_TURQUOISE,
        DARK_VIOLET,
        DEEP_CERISE,
        DEEP_CHESTNUT,
        DEEP_FUCHSIA,
        DEEP_LILAC,
        DEEP_MAGENTA,
        DEEP_PEACH,
        DEEP_PINK,
        DENIM,
        DODGER_BLUE,
        ECRU,
        EGYPTIAN_BLUE,
        ELECTRIC_BLUE,
        ELECTRIC_GREEN,
        ELECTRIC_INDIGO,
        ELECTRIC_LIME,
        ELECTRIC_PURPLE,
        EMERALD,
        EGGPLANT,
        FALU_RED,
        FERN_GREEN,
        FIREBRICK,
        FLAX,
        FOREST_GREEN,
        FRENCH_ROSE,
        FUCSHIA_PINK,
        GAMBOGE,
        GOLD,
        GOLDEN,
        GOLDEN_BROWN,
        GOLDEN_YELLOW,
        GOLDENROD,
        GRAY_ASPARAGUS,
        GREEN_YELLOW,
        HAN_PURPLE,
        HELIOTROPE,
        HOLLYWOOD_CERISE,
        HOT_MAGENTA,
        HOT_PINK,
        INDIGO_DYE,
        INDIGO,
        INTERNATIONAL_KLEIN_BLUE,
        INTERNATIONAL_ORANGE,
        ISLAMIC_GREEN,
        IVORY,
        JADE,
        KELLY_GREEN,
        KHAKI,
        LIGHT_KHAKI,
        LAVENDER_FLORAL,
        LAVENDER,
        LAVENDER_BLUE,
        LAVENDER_BLUSH,
        LAVENDER_GRAY,
        LAVENDER_MAGENTA,
        LAVENDER_PINK,
        LAVENDER_PURPLE,
        LAVENDER_ROSE,
        LAWN_GREEN,
        LEMON_CHIFFON,
        LIGHT_BLUE,
        LIGHT_PINK,
        LILAC,
        LEMON,
        LIGHT_LIME,
        LIME_GREEN,
        LINEN,
        MAGENTA_DYE,
        MAGIC_MINT,
        MAGNOLIA,
        MALACHITE,
        MAROON,
        LIGHT_MAROON,
        MAYA_BLUE,
        MAUVE,
        MAUVE_TAUPE,
        MEDIUM_BLUE,
        MEDIUM_CARMINE,
        MEDIUM_LAVENDER_MAGENTA,
        MEDIUM_PURPLE,
        MEDIUM_SPRING_GREEN,
        MIDNIGHT_BLUE,
        MINT_GREEN,
        MISTY_ROSE,
        MOSS_GREEN,
        MOUNTBATTEN_PINK,
        MUSTARD,
        MYRTLE,
        NAVAJO_WHITE,
        NAVY_BLUE,
        OCHRE,
        OLD_GOLD,
        OLD_LACE,
        OLD_LAVENDER,
        OLD_ROSE,
        OLIVE,
        OLIVE_DRAB,
        OLIVINE,
        ORANGE_PEEL,
        ORANGE_RED,
        ORCHID,
        PALE_BLUE,
        PALE_BROWN,
        PALE_CARMINE,
        PALE_CHESTNUT,
        PALE_CORNFLOWER_BLUE,
        PALE_MAGENTA,
        PALE_PINK,
        PALE_RED_VIOLET,
        PAPAYA_WHIP,
        PASTEL_GREEN,
        PASTEL_PINK,
        PEACH,
        PEACH_ORANGE,
        PEACH_YELLOW,
        PEAR,
        PERIWINKLE,
        PERSIAN_BLUE,
        PERSIAN_GREEN,
        PERSIAN_INDIGO,
        PERSIAN_RED,
        PERSIAN_PINK,
        PERSIAN_ROSE,
        PERSIMMON,
        PINE_GREEN,
        TRUE_PINK,
        PINK_ORANGE,
        PLATINUM,
        PLUM,
        POWDER_BLUE,
        PUCE,
        PRUSSIAN_BLUE,
        PSYCHEDELIC_PURPLE,
        PUMPKIN,
        PURPLE_TAUPE,
        RAW_UMBER,
        RAZZMATAZZ,
        RED_PIGMENT,
        RED_VIOLET,
        RICH_CARMINE,
        ROBIN_EGG_BLUE,
        ROSE,
        ROSE_MADDER,
        ROSE_TAUPE,
        ROYAL_BLUE,
        ROYAL_PURPLE,
        RUBY,
        RUSSET,
        RUST,
        SAFETY_ORANGE,
        SAFFRON,
        SALMON,
        SANDY_BROWN,
        SANGRIA,
        SAPPHIRE,
        SCARLET,
        SCHOOL_BUS_YELLOW,
        SEA_GREEN,
        SEASHELL,
        SELECTIVE_YELLOW,
        SEPIA,
        SHAMROCK_GREEN,
        SHOCKING_PINK,
        SILVER,
        SKY_BLUE,
        SLATE_GRAY,
        SMALT,
        SPRING_BUD,
        SPRING_GREEN,
        STEEL_BLUE,
        TAN,
        TANGERINE,
        TANGERINE_YELLOW,
        TAUPE,
        TEA_GREEN,
        TEA_ORANGE,
        TEA_ROSE,
        TAWNY,
        TERRA_COTTA,
        THISTLE,
        TOMATO,
        TURQUOISE,
        TYRIAN_PURPLE,
        ULTRAMARINE,
        VEGAS_GOLD,
        VERMILION,
        VIOLET,
        LIGHT_VIOLET,
        VIRIDIAN,
        WHEAT,
        WISTERIA,
        YELLOW_GREEN,
        ZINNWALDITE
    };
    public static CSIColor[] DEFAULT_PALLET = {BLACK, DARK_BLUE, GREEN, TEAL, DARK_RED, PURPLE, BROWN, LIGHT_GRAY, GRAY, BLUE, LIME, CYAN, RED, MAGENTA, YELLOW, WHITE};

    /**
     * A constructor with information for all color channels in the aRGB colorspace.
     * 
     * All values should be between 0 and 255, with 0 be none and 255 being max of that color.
     * @param pr Red value
     * @param pg Green value
     * @param pb Blue value
     * @param pa Alpha (or transparency) value, with 255 being fully opaque and 0 being fully transparent
     */
    public CSIColor(int pr, int pg, int pb, int pa) {
        this((pa << 24) | (pr << 16) | (pg << 8) | pb);
    }

    /**
     * A constructor with information for just RGB values.  The alpha value is assumed
     * to be 255 for full opaqueness.
     * @param pr Red Value
     * @param pg Green Value
     * @param pb Blue Value
     */
    public CSIColor(int pr, int pg, int pb) {
        this((OPAQUE << 24) | (pr << 16) | (pg << 8) | pb);
    }

    /**
     * A constructor with no passed values.  Builds a white (opaque all channels) object.
     */
    public CSIColor() { //builds white
        this((OPAQUE << 24) | (OPAQUE << 16) | (OPAQUE << 8) | OPAQUE);
    }

    /**
     * A constructor which builds a new color based on an existing color.
     * @param color The color who's value should be copied for the new color.
     */
    public CSIColor(CSIColor color) {
        value = color.value;
    }

    /**
     * A constructor which builds a new color based on a hex value which represents
     * bit shifted values for aRGB color space.
     * @param hex
     */
    public CSIColor(int hex) {
        value = hex;
    }

    /**
     * Compares current color to passed in color to determine if they are equal.
     * @param b Color to compare to
     * @return true if equal in value
     */
    public boolean equals(CSIColor b) {
        return (value == b.value);
    }

    /**
     * Set the value of current color equal to an existing color.
     * @param color The existing color to set current color equal to.
     */
    public void setColor(int color) {
        value = color;
    }

    /**
     * @return color's hex value
     */
    public int getColor() {
        return value;
    }

    /**
     * @return value of red pulled out
     */
    public int getR() {
        return (value >> 16) & 0xff;
    }

    /**
     * Sets the value of the red component of the color.
     * @param r the value that red should become
     */
    public void setR(int r) {
        value = (getA() << 24) | (r << 16) | (getG() << 8) | getB();
    }

    /**
     * @return green value seperated out
     */
    public int getG() {
        return (value >> 8) & 0xff;
    }

    /**
     * Sets the value of the green component of the color.
     * @param g the value that green should become
     */
    public void setG(int g) {
        value = (getA() << 24) | (getR() << 16) | (g << 8) | getB();
    }

    /**
     * @return blue value seperated out
     */
    public int getB() {
        return value & 0xff;
    }

    /**
     * Sets the value of the blue component of the color.
     * @param b the value that blue should become
     */
    public void setB(int b) {
        value = (getA() << 24) | (getR() << 16) | (getG() << 8) | b;
    }

    /**
     * @return alpha (or transparency) value seperated
     */
    public int getA() {
        return (value >> 24) & 0xff;
    }

    /**
     * Sets the value of the alpha channel (or transparency) of the color.
     * 
     * 0 is transparent. 255 is opaque or solid.
     * @param a the value that alpha should become
     */
    public void setA(int a) {
        value = (a << 24) | (getR() << 16) | (getG() << 8) | getB();
    }

    /**
     * Allows you to get an int that is the array position of the String color name passed in within the default pallet.
     * 
     * If the String is not in the default pallet then a -1 is returned.
     * @param colorName the String to get information on
     * @return the position of the color named by the String or -1 if no such color
     */
    public int getColor(String colorName) {
        ArrayList<CSIColor> tempList = new ArrayList<CSIColor>();
        for (int i = 0; i < DEFAULT_PALLET.length; i++) {
            tempList.add(DEFAULT_PALLET[i]);
        }
        if (colorName == null) {
            return -1;
        }
        if (colorName.equals("BLACK")) {
            return tempList.indexOf(BLACK);
        }
        if (colorName.equals("DARK_BLUE")) {
            return tempList.indexOf(DARK_BLUE);
        }
        if (colorName.equals("GREEN")) {
            return tempList.indexOf(GREEN);
        }
        if (colorName.equals("TEAL")) {
            return tempList.indexOf(TEAL);
        }
        if (colorName.equals("DARK_RED")) {
            return tempList.indexOf(DARK_RED);
        }
        if (colorName.equals("PURPLE")) {
            return tempList.indexOf(PURPLE);
        }
        if (colorName.equals("BROWN")) {
            return tempList.indexOf(BROWN);
        }
        if (colorName.equals("LIGHT_GRAY")) {
            return tempList.indexOf(LIGHT_GRAY);
        }
        if (colorName.equals("GRAY")) {
            return tempList.indexOf(GRAY);
        }
        if (colorName.equals("BLUE")) {
            return tempList.indexOf(BLUE);
        }
        if (colorName.equals("LEMON")) {
            return tempList.indexOf(LIME);
        }
        if (colorName.equals("CYAN")) {
            return tempList.indexOf(CYAN);
        }
        if (colorName.equals("RED")) {
            return tempList.indexOf(RED);
        }
        if (colorName.equals("MAGENTA")) {
            return tempList.indexOf(MAGENTA);
        }
        if (colorName.equals("YELLOW")) {
            return tempList.indexOf(YELLOW);
        }
        if (colorName.equals("WHITE")) {
            return tempList.indexOf(WHITE);
        }
        return -1;
    }

    /**
     * Attempts to return a CSIColor object from the default pallet.
     * 
     * If the int passed in is to large or small for a position in the default
     * pallet then CSIColor.BLACK is returned instead.
     * @param code the pallet position whose color is to be returned
     * @return the color in the position at code or BLACK if code is outside the default pallet
     */
    public CSIColor getColorFromCode(int code) {
        if ((code < 0) || (code > DEFAULT_PALLET.length - 1)) {
            return BLACK;
        }
        return DEFAULT_PALLET[code];
    }

    /**
     * Attempts to return the index of the passed in color in the default pallet.
     * 
     * If the color is not in the default pallet then it will return -1.
     * @param color the color to search the default pallet for
     * @return either the default pallet index matching the color or -1 if no match
     */
    public int getCodeFromColor(CSIColor color) {
        ArrayList<CSIColor> tempList = new ArrayList<CSIColor>();
        for (int i = 0; i < DEFAULT_PALLET.length; i++) {
            tempList.add(DEFAULT_PALLET[i]);
        }
        return tempList.indexOf(color);
    }

    /**
     * Compares the current color to another color.  Comparision is not guaranteed to
     * lead to a logical color ordering that may be expected.
     * @param color new color to compare with current color
     * @return indicator of comparison between colors
     */
    public int compareTo(CSIColor color) {
        int compare = 0;
        if (value < color.value) {
            compare = -1;
        }
        if (value > color.value) {
            compare = 1;
        }
        return compare;
    }

    /**
     * Compares the current color to another object of any type.
     * If the second object is not a CSIColor object than an exception will be thrown.
     * 
     * @throws UnsupportedOperationException if second object not a CSIColor object
     * @param arg0 second object to be compared to
     * @return indicator of comparison between colors
     */
    public int compareTo(Object arg0) {
        try {
            return compareTo((CSIColor) arg0);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Incorrect Object Type");
        }
    }
}

