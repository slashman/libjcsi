package net.slashie.util;

import java.util.*;

/**
 * Various utilities to make things easier.
 * @author Santiago Zapata
 */
public class Util {

    private static Random rand = new Random(System.currentTimeMillis());

    /**
     * Used to get a random number within a range, with all numbers being
     * equally likely.
     * @param low low end of range
     * @param hi high end of range
     * @return randomly chosen number
     */
    public static int rand(int low, int hi) {
        //return (int)((Math.random() * (hi -low))+low);
        //rand.setSeed(rand.nextLong());
        int ret = (int) ((rand.nextDouble() * (hi - low + 1)) + low);
        //Debug.say("low " +low+" hi "+hi+"="+ret);
        return ret;
    }

    /**
     * Compares two numbers and returns the larger of them.  If they are
     * equal, returns the second number.
     * @param a
     * @param b
     * @return larger number
     */
    public static int greater(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }

    /**
     * Returns the absolute value of a number.
     * @param a number to be used
     * @return absolute value of a
     */
    public static int abs(int a) {
        if (a > 0) {
            return a;
        }
        return -a;
    }

    /**
     * Tells if a random percent roll is at or below a certain number.
     * @param p number to test
     * @return true if random roll is less than or equal to p
     */
    public static boolean chance(int p) {
        return Util.rand(1, 100) <= p;
    }

    public static Vector page(Vector source, int elementsOnPage, int pageNumber) {
        //System.out.println("elements on page"+elementsOnPage+" page Number"+pageNumber);
        if (source.size() == 0) {
            return source;
        }
        if ((pageNumber + 1) * elementsOnPage > source.size()) {
            return new Vector(source.subList(pageNumber * elementsOnPage, source.size()));
        } else {
            return new Vector(source.subList(pageNumber * elementsOnPage, (pageNumber + 1) * elementsOnPage));
        }
    }

    /**
     * 
     * @param array
     * @return a randomly chosen element of array
     */
    public static String randomElementOf(String[] array) {
        return array[rand(0, array.length - 1)];
    }

    /**
     * 
     * @param array
     * @return a randomly chosen element of array
     */
    public static int randomElementOf(int[] array) {
        return array[rand(0, array.length - 1)];
    }

    /**
     * 
     * @param array
     * @return a randomly chosen element of array
     */
    public static Object randomElementOf(Vector array) {
        return array.elementAt(rand(0, array.size() - 1));
    }

    /**
     * 
     * @param array
     * @return a randomly chosen element of array
     */
    public static Object randomElementOf(ArrayList array) {
        return array.get(rand(0, array.size() - 1));
    }

    /**
     * 
     * @param array
     * @return a randomly chosen element of array
     */
    public static Object randomElementOf(Object[] array) {
        return array[rand(0, array.length - 1)];
    }

    /**
     * Gives an indication of the sign (negative or positive) of
     * a specific number.
     * @param n number specified
     * @return 1 if positive, -1 if negative, 0 if n==0
     */
    public static int sign(int n) {
        if (n > 0) {
            return 1;
        } else if (n < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}