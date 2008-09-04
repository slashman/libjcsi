package net.slashie.util;

import java.util.*;

public class Util {
	private static Random rand = new Random(System.currentTimeMillis());

	public static int rand(int low, int hi){
		//return (int)((Math.random() * (hi -low))+low);
		//rand.setSeed(rand.nextLong());
		int ret = (int)((rand.nextDouble() * (hi - low + 1))+low);
		//Debug.say("low " +low+" hi "+hi+"="+ret);
		return ret;
	}

	public static int greater(int a, int b){
		if (a > b)
			return a;
		return b;
	}

	public static int abs(int a){
		if (a > 0)
			return a;
		return -a;
	}

	public static boolean chance(int p){
		return Util.rand(1,100) <= p;
	}

	public static Vector page(Vector source, int elementsOnPage, int pageNumber){
		//System.out.println("elements on page"+elementsOnPage+" page Number"+pageNumber);
		if (source.size() == 0)
			return source;
		if ((pageNumber+1) * elementsOnPage > source.size() )
			return new Vector(source.subList(pageNumber*elementsOnPage, source.size()));
		else
			return new Vector(source.subList(pageNumber*elementsOnPage, (pageNumber+1) * elementsOnPage));
	}

	public static String randomElementOf(String [] array){
		return array[rand(0, array.length -1)];
	}
	
	public static int randomElementOf(int [] array){
		return array[rand(0, array.length -1)];
	}
	
	public static Object randomElementOf(Vector array){
		return array.elementAt(rand(0, array.size() -1));
	}
	
	public static Object randomElementOf(ArrayList array){
		return array.get(rand(0, array.size() -1));
	}
	
	public static Object randomElementOf(Object [] array){
		return array[rand(0, array.length -1)];
	}
	
	public static int sign(int n){
		if (n > 0)
			return 1;
		else if (n<0)
			return -1;
		else
			return 0;
	}
}