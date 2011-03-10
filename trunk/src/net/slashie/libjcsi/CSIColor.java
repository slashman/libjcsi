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
     * Duplicated as AQUA for usability
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
    public static final CSIColor CRIMSON = new CSIColor(0xDC143C);
    /**
     *<pre><font style="background-color: #654321;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #654321;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #654321;" color=654321>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #654321;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #654321;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #654321;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=654321>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_BROWN = new CSIColor(0x654321);
    /**
     *<pre><font style="background-color: #08457E;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #08457E;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #08457E;" color=08457E>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #08457E;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #08457E;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #08457E;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=08457E>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_CERULEAN = new CSIColor(0x08457E);
    /**
     *<pre><font style="background-color: #986960;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #986960;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #986960;" color=986960>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #986960;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #986960;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #986960;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=986960>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_CHESTNUT = new CSIColor(0x986960);
    /**
     *<pre><font style="background-color: #CD5B45;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD5B45;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CD5B45;" color=CD5B45>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CD5B45;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CD5B45;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD5B45;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CD5B45>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_CORAL = new CSIColor(0xCD5B45);
    /**
     *<pre><font style="background-color: #B8860B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B8860B;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B8860B;" color=B8860B>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B8860B;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B8860B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B8860B;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B8860B>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_GOLDENROD = new CSIColor(0xB8860B);
    /**
     *<pre><font style="background-color: #013220;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #013220;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #013220;" color=013220>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #013220;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #013220;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #013220;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=013220>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_GREEN = new CSIColor(0x013220);
    /**
     *<pre><font style="background-color: #BDB76B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #BDB76B;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #BDB76B;" color=BDB76B>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #BDB76B;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #BDB76B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #BDB76B;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=BDB76B>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_KHAKI = new CSIColor(0xBDB76B);
    /**
     *<pre><font style="background-color: #03C03C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #03C03C;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #03C03C;" color=03C03C>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #03C03C;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #03C03C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #03C03C;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=03C03C>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_PASTEL_GREEN = new CSIColor(0x03C03C);
    /**
     *<pre><font style="background-color: #E75480;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E75480;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E75480;" color=E75480>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E75480;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E75480;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E75480;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E75480>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_PINK = new CSIColor(0xE75480);
    /**
     *<pre><font style="background-color: #560319;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #560319;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #560319;" color=560319>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #560319;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #560319;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #560319;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=560319>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_SCARLET = new CSIColor(0x560319);
    /**
     *<pre><font style="background-color: #E9967A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E9967A;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E9967A;" color=E9967A>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E9967A;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E9967A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E9967A;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E9967A>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_SALMON = new CSIColor(0xE9967A);
    /**
     *<pre><font style="background-color: #2F4F4F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #2F4F4F;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #2F4F4F;" color=2F4F4F>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #2F4F4F;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #2F4F4F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #2F4F4F;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=2F4F4F>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_SLATE_GRAY = new CSIColor(0x2F4F4F);
    /**
     *<pre><font style="background-color: #177245;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #177245;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #177245;" color=177245>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #177245;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #177245;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #177245;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=177245>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_SPRING_GREEN = new CSIColor(0x177245);
    /**
     *<pre><font style="background-color: #918151;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #918151;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #918151;" color=918151>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #918151;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #918151;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #918151;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=918151>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_TAN = new CSIColor(0x918151);
    /**
     *<pre><font style="background-color: #00CED1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00CED1;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00CED1;" color=00CED1>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #00CED1;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00CED1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00CED1;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00CED1>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_TURQUOISE = new CSIColor(0x00CED1);
    /**
     *<pre><font style="background-color: #9400D3;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #9400D3;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #9400D3;" color=9400D3>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #9400D3;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #9400D3;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #9400D3;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=9400D3>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DARK_VIOLET = new CSIColor(0x9400D3);
    /**
     *<pre><font style="background-color: #DA3287;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DA3287;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #DA3287;" color=DA3287>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #DA3287;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #DA3287;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DA3287;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=DA3287>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_CERISE = new CSIColor(0xDA3287);
    /**
     *<pre><font style="background-color: #B94E48;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B94E48;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B94E48;" color=B94E48>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B94E48;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B94E48;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B94E48;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B94E48>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_CHESTNUT = new CSIColor(0xB94E48);
    /**
     *<pre><font style="background-color: #C154C1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C154C1;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #C154C1;" color=C154C1>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #C154C1;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #C154C1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C154C1;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=C154C1>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_FUCHSIA = new CSIColor(0xC154C1);
    /**
     *<pre><font style="background-color: #9955BB;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #9955BB;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #9955BB;" color=9955BB>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #9955BB;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #9955BB;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #9955BB;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=9955BB>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_LILAC = new CSIColor(0x9955BB);
    /**
     *<pre><font style="background-color: #CD00CC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD00CC;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CD00CC;" color=CD00CC>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CD00CC;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CD00CC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CD00CC;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CD00CC>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_MAGENTA = new CSIColor(0xCD00CC);
    /**
     *<pre><font style="background-color: #FFCBA4;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFCBA4;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFCBA4;" color=FFCBA4>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFCBA4;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFCBA4;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFCBA4;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFCBA4>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_PEACH = new CSIColor(0xFFCBA4);
    /**
     *<pre><font style="background-color: #FF1493;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF1493;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF1493;" color=FF1493>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF1493;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF1493;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF1493;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF1493>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DEEP_PINK = new CSIColor(0xFF1493);
    /**
     *<pre><font style="background-color: #1560BD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #1560BD;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #1560BD;" color=1560BD>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #1560BD;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #1560BD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #1560BD;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=1560BD>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DENIM = new CSIColor(0x1560BD);
    /**
     *<pre><font style="background-color: #1E90FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #1E90FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #1E90FF;" color=1E90FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #1E90FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #1E90FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #1E90FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=1E90FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor DODGER_BLUE = new CSIColor(0x1E90FF);
    /**
     *<pre><font style="background-color: #C2B280;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C2B280;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #C2B280;" color=C2B280>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #C2B280;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #C2B280;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C2B280;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=C2B280>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ECRU = new CSIColor(0xC2B280);
    /**
     *<pre><font style="background-color: #1034A6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #1034A6;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #1034A6;" color=1034A6>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #1034A6;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #1034A6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #1034A6;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=1034A6>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor EGYPTIAN_BLUE = new CSIColor(0x1034A6);
    /**
     *<pre><font style="background-color: #7DF9FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7DF9FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #7DF9FF;" color=7DF9FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #7DF9FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #7DF9FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7DF9FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=7DF9FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ELECTRIC_BLUE = new CSIColor(0x7DF9FF);
    /**
     *<pre><font style="background-color: #00FF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00FF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00FF00;" color=00FF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #00FF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00FF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00FF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00FF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ELECTRIC_GREEN = new CSIColor(0x00FF00);
    /**
     *<pre><font style="background-color: #6600FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #6600FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #6600FF;" color=6600FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #6600FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #6600FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #6600FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=6600FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ELECTRIC_INDIGO = new CSIColor(0x6600FF);
    /**
     *<pre><font style="background-color: #CCFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CCFF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CCFF00;" color=CCFF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CCFF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CCFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CCFF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CCFF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ELECTRIC_LIME = new CSIColor(0xCCFF00);
    /**
     *<pre><font style="background-color: #BF00FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #BF00FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #BF00FF;" color=BF00FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #BF00FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #BF00FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #BF00FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=BF00FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ELECTRIC_PURPLE = new CSIColor(0xBF00FF);
    /**
     *<pre><font style="background-color: #50C878;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #50C878;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #50C878;" color=50C878>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #50C878;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #50C878;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #50C878;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=50C878>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor EMERALD = new CSIColor(0x50C878);
    /**
     *<pre><font style="background-color: #614051;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #614051;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #614051;" color=614051>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #614051;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #614051;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #614051;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=614051>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor EGGPLANT = new CSIColor(0x614051);
    /**
     *<pre><font style="background-color: #801818;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #801818;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #801818;" color=801818>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #801818;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #801818;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #801818;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=801818>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FALU_RED = new CSIColor(0x801818);
    /**
     *<pre><font style="background-color: #4F7942;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #4F7942;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #4F7942;" color=4F7942>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #4F7942;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #4F7942;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #4F7942;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=4F7942>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FERN_GREEN = new CSIColor(0x4F7942);
    /**
     *<pre><font style="background-color: #B22222;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B22222;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B22222;" color=B22222>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B22222;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B22222;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B22222;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B22222>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FIREBRICK = new CSIColor(0xB22222);
    /**
     *<pre><font style="background-color: #EEDC82;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #EEDC82;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #EEDC82;" color=EEDC82>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #EEDC82;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #EEDC82;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #EEDC82;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=EEDC82>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FLAX = new CSIColor(0xEEDC82);
    /**
     *<pre><font style="background-color: #228B22;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #228B22;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #228B22;" color=228B22>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #228B22;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #228B22;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #228B22;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=228B22>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FOREST_GREEN = new CSIColor(0x228B22);
    /**
     *<pre><font style="background-color: #F64A8A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F64A8A;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F64A8A;" color=F64A8A>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F64A8A;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F64A8A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F64A8A;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F64A8A>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FRENCH_ROSE = new CSIColor(0xF64A8A);
    /**
     *<pre><font style="background-color: #FF77FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF77FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF77FF;" color=FF77FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF77FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF77FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF77FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF77FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor FUCSHIA_PINK = new CSIColor(0xFF77FF);
    /**
     *<pre><font style="background-color: #E49B0F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E49B0F;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E49B0F;" color=E49B0F>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E49B0F;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E49B0F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E49B0F;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E49B0F>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GAMBOGE = new CSIColor(0xE49B0F);
    /**
     *<pre><font style="background-color: #D4AF37;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #D4AF37;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #D4AF37;" color=D4AF37>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #D4AF37;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #D4AF37;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #D4AF37;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=D4AF37>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GOLD = new CSIColor(0xD4AF37);
    /**
     *<pre><font style="background-color: #FFD700;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFD700;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFD700;" color=FFD700>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFD700;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFD700;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFD700;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFD700>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GOLDEN = new CSIColor(0xFFD700);
    /**
     *<pre><font style="background-color: #996515;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #996515;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #996515;" color=996515>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #996515;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #996515;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #996515;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=996515>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GOLDEN_BROWN = new CSIColor(0x996515);
    /**
     *<pre><font style="background-color: #FFDF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFDF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFDF00;" color=FFDF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFDF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFDF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFDF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFDF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GOLDEN_YELLOW = new CSIColor(0xFFDF00);
    /**
     *<pre><font style="background-color: #DAA520;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DAA520;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #DAA520;" color=DAA520>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #DAA520;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #DAA520;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DAA520;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=DAA520>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GOLDENROD = new CSIColor(0xDAA520);
    /**
     *<pre><font style="background-color: #465945;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #465945;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #465945;" color=465945>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #465945;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #465945;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #465945;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=465945>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GRAY_ASPARAGUS = new CSIColor(0x465945);
    /**
     *<pre><font style="background-color: #ADFF2F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ADFF2F;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ADFF2F;" color=ADFF2F>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #ADFF2F;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ADFF2F;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ADFF2F;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ADFF2F>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor GREEN_YELLOW = new CSIColor(0xADFF2F);
    /**
     *<pre><font style="background-color: #5218FA;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #5218FA;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #5218FA;" color=5218FA>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #5218FA;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #5218FA;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #5218FA;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=5218FA>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor HAN_PURPLE = new CSIColor(0x5218FA);
    /**
     *<pre><font style="background-color: #DF73FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DF73FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #DF73FF;" color=DF73FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #DF73FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #DF73FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #DF73FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=DF73FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor HELIOTROPE = new CSIColor(0xDF73FF);
    /**
     *<pre><font style="background-color: #F400A1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F400A1;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F400A1;" color=F400A1>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F400A1;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F400A1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F400A1;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F400A1>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor HOLLYWOOD_CERISE = new CSIColor(0xF400A1);
    /**
     *<pre><font style="background-color: #FF00CC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF00CC;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF00CC;" color=FF00CC>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF00CC;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF00CC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF00CC;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF00CC>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor HOT_MAGENTA = new CSIColor(0xFF00CC);
    /**
     *<pre><font style="background-color: #FF69B4;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF69B4;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF69B4;" color=FF69B4>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF69B4;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF69B4;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF69B4;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF69B4>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor HOT_PINK = new CSIColor(0xFF69B4);
    /**
     *<pre><font style="background-color: #00416A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00416A;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00416A;" color=00416A>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #00416A;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00416A;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00416A;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00416A>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor INDIGO_DYE = new CSIColor(0x00416A);
    /**
     *<pre><font style="background-color: #4B0082;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #4B0082;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #4B0082;" color=4B0082>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #4B0082;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #4B0082;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #4B0082;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=4B0082>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor INDIGO = new CSIColor(0x4B0082);
    /**
     *<pre><font style="background-color: #002FA7;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #002FA7;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #002FA7;" color=002FA7>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #002FA7;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #002FA7;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #002FA7;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=002FA7>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor INTERNATIONAL_KLEIN_BLUE = new CSIColor(0x002FA7);
    /**
     *<pre><font style="background-color: #FF4F00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF4F00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FF4F00;" color=FF4F00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FF4F00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FF4F00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FF4F00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FF4F00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor INTERNATIONAL_ORANGE = new CSIColor(0xFF4F00);
    /**
     *<pre><font style="background-color: #009000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #009000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #009000;" color=009000>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #009000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #009000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #009000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=009000>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor ISLAMIC_GREEN = new CSIColor(0x009000);
    /**
     *<pre><font style="background-color: #FFFFF0;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFFFF0;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFFFF0;" color=FFFFF0>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFFFF0;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFFFF0;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFFFF0;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFFFF0>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor IVORY = new CSIColor(0xFFFFF0);
    /**
     *<pre><font style="background-color: #00A86B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00A86B;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #00A86B;" color=00A86B>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #00A86B;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #00A86B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00A86B;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=00A86B>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor JADE = new CSIColor(0x00A86B);
    /**
     *<pre><font style="background-color: #4CBB17;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #4CBB17;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #4CBB17;" color=4CBB17>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #4CBB17;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #4CBB17;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #4CBB17;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=4CBB17>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor KELLY_GREEN = new CSIColor(0x4CBB17);
    /**
     *<pre><font style="background-color: #C3B091;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C3B091;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #C3B091;" color=C3B091>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #C3B091;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #C3B091;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C3B091;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=C3B091>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor KHAKI = new CSIColor(0xC3B091);
    /**
     *<pre><font style="background-color: #F0E68C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F0E68C;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F0E68C;" color=F0E68C>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F0E68C;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F0E68C;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F0E68C;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F0E68C>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIGHT_KHAKI = new CSIColor(0xF0E68C);
    /**
     *<pre><font style="background-color: #B57EDC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B57EDC;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B57EDC;" color=B57EDC>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B57EDC;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B57EDC;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B57EDC;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B57EDC>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_FLORAL = new CSIColor(0xB57EDC);
    /**
     *<pre><font style="background-color: #E6E6FA;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E6E6FA;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #E6E6FA;" color=E6E6FA>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #E6E6FA;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #E6E6FA;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #E6E6FA;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=E6E6FA>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER = new CSIColor(0xE6E6FA);
    /**
     *<pre><font style="background-color: #CCCCFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CCCCFF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CCCCFF;" color=CCCCFF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CCCCFF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CCCCFF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CCCCFF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CCCCFF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_BLUE = new CSIColor(0xCCCCFF);
    /**
     *<pre><font style="background-color: #FFF0F5;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFF0F5;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFF0F5;" color=FFF0F5>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFF0F5;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFF0F5;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFF0F5;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFF0F5>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_BLUSH = new CSIColor(0xFFF0F5);
    /**
     *<pre><font style="background-color: #C4C3D0;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C4C3D0;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #C4C3D0;" color=C4C3D0>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #C4C3D0;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #C4C3D0;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C4C3D0;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=C4C3D0>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_GRAY = new CSIColor(0xC4C3D0);
    /**
     *<pre><font style="background-color: #EE82EE;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #EE82EE;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #EE82EE;" color=EE82EE>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #EE82EE;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #EE82EE;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #EE82EE;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=EE82EE>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_MAGENTA = new CSIColor(0xEE82EE);
    /**
     *<pre><font style="background-color: #FBAED2;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FBAED2;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FBAED2;" color=FBAED2>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FBAED2;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FBAED2;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FBAED2;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FBAED2>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_PINK = new CSIColor(0xFBAED2);
    /**
     *<pre><font style="background-color: #967BB6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #967BB6;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #967BB6;" color=967BB6>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #967BB6;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #967BB6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #967BB6;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=967BB6>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_PURPLE = new CSIColor(0x967BB6);
    /**
     *<pre><font style="background-color: #FBA0E3;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FBA0E3;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FBA0E3;" color=FBA0E3>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FBA0E3;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FBA0E3;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FBA0E3;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FBA0E3>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAVENDER_ROSE = new CSIColor(0xFBA0E3);
    /**
     *<pre><font style="background-color: #7CFC00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7CFC00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #7CFC00;" color=7CFC00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #7CFC00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #7CFC00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #7CFC00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=7CFC00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LAWN_GREEN = new CSIColor(0x7CFC00);
    /**
     *<pre><font style="background-color: #FFFACD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFFACD;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFFACD;" color=FFFACD>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFFACD;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFFACD;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFFACD;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFFACD>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LEMON_CHIFFON = new CSIColor(0xFFFACD);
    /**
     *<pre><font style="background-color: #ADD8E6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ADD8E6;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #ADD8E6;" color=ADD8E6>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #ADD8E6;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #ADD8E6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ADD8E6;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=ADD8E6>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIGHT_BLUE = new CSIColor(0xADD8E6);
    /**
     *<pre><font style="background-color: #FFB6C1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFB6C1;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FFB6C1;" color=FFB6C1>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FFB6C1;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FFB6C1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FFB6C1;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FFB6C1>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIGHT_PINK = new CSIColor(0xFFB6C1);
    /**
     *<pre><font style="background-color: #C8A2C8;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C8A2C8;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #C8A2C8;" color=C8A2C8>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #C8A2C8;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #C8A2C8;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #C8A2C8;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=C8A2C8>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LILAC = new CSIColor(0xC8A2C8);
    /**
     *<pre><font style="background-color: #FDE910;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FDE910;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FDE910;" color=FDE910>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FDE910;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FDE910;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FDE910;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FDE910>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LEMON = new CSIColor(0xFDE910);
    /**
     *<pre><font style="background-color: #BFFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #BFFF00;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #BFFF00;" color=BFFF00>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #BFFF00;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #BFFF00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #BFFF00;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=BFFF00>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIGHT_LIME = new CSIColor(0xBFFF00);
    /**
     *<pre><font style="background-color: #32CD32;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #32CD32;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #32CD32;" color=32CD32>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #32CD32;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #32CD32;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #32CD32;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=32CD32>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIME_GREEN = new CSIColor(0x32CD32);
    /**
     *<pre><font style="background-color: #FAF0E6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FAF0E6;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #FAF0E6;" color=FAF0E6>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #FAF0E6;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #FAF0E6;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #FAF0E6;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=FAF0E6>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LINEN = new CSIColor(0xFAF0E6);
    /**
     *<pre><font style="background-color: #CA1F7B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CA1F7B;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #CA1F7B;" color=CA1F7B>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #CA1F7B;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #CA1F7B;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #CA1F7B;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=CA1F7B>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MAGENTA_DYE = new CSIColor(0xCA1F7B);
    /**
     *<pre><font style="background-color: #AAF0D1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #AAF0D1;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #AAF0D1;" color=AAF0D1>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #AAF0D1;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #AAF0D1;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #AAF0D1;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=AAF0D1>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MAGIC_MINT = new CSIColor(0xAAF0D1);
    /**
     *<pre><font style="background-color: #F8F4FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F8F4FF;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #F8F4FF;" color=F8F4FF>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #F8F4FF;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #F8F4FF;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #F8F4FF;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=F8F4FF>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MAGNOLIA = new CSIColor(0xF8F4FF);
    /**
     *<pre><font style="background-color: #0BDA51;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0BDA51;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #0BDA51;" color=0BDA51>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #0BDA51;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #0BDA51;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0BDA51;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=0BDA51>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MALACHITE = new CSIColor(0x0BDA51);
    /**
     *<pre><font style="background-color: #800000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #800000;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #800000;" color=800000>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #800000;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #800000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #800000;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=800000>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MAROON = new CSIColor(0x800000);
    /**
     *<pre><font style="background-color: #B03060;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B03060;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #B03060;" color=B03060>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #B03060;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #B03060;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #B03060;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=B03060>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor LIGHT_MAROON = new CSIColor(0xB03060);
    /**
     *<pre><font style="background-color: #73C2FB;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #73C2FB;" color=000000>&nbsp@&nbsp</font>
     *<font style="background-color: #73C2FB;" color=73C2FB>&nbsp&nbsp&nbsp<font style="background-color: #000000;">&nbsp@&nbsp<font style="background-color: #888888;">&nbsp@&nbsp<font style="background-color: #ffffff;">&nbsp@&nbsp<font style="background-color: #73C2FB;" color=888888>&nbsp@&nbsp</font>
     *<font style="background-color: #73C2FB;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #000000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #888888;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffffff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #73C2FB;" color=ffffff>&nbsp@&nbsp</font>
     *
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font>
     *<font style="background-color: #ff0000;" color=73C2FB>&nbsp@&nbsp<font style="background-color: #ffff00;">&nbsp@&nbsp<font style="background-color: #00ff00;">&nbsp@&nbsp<font style="background-color: #0000ff;">&nbsp@&nbsp<font style="background-color: #964b00;">&nbsp@&nbsp</font>
     *<font style="background-color: #ff0000;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #ffff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #00ff00;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #0000ff;" color=000000>&nbsp&nbsp&nbsp<font style="background-color: #964b00;" color=000000>&nbsp&nbsp&nbsp</font></pre>
     */
    public static final CSIColor MAYA_BLUE = new CSIColor(0x73C2FB),  MAUVE = new CSIColor(0xE0B0FF),
            MAUVE_TAUPE = new CSIColor(0x915F6D),  MEDIUM_BLUE = new CSIColor(0x0000CD),
            MEDIUM_CARMINE = new CSIColor(0xAF4035),  MEDIUM_LAVENDER_MAGENTA = new CSIColor(0xCC99CC),
            MEDIUM_PURPLE = new CSIColor(0x9370DB),  MEDIUM_SPRING_GREEN = new CSIColor(0x00FA9A),
            MIDNIGHT_BLUE = new CSIColor(0x003366),  MINT_GREEN = new CSIColor(0x98FF98),
            MISTY_ROSE = new CSIColor(0xFFE4E1),  MOSS_GREEN = new CSIColor(0xADDFAD),
            MOUNTBATTEN_PINK = new CSIColor(0x997A8D),  MUSTARD = new CSIColor(0xFFDB58),
            MYRTLE = new CSIColor(0x21421E),  NAVAJO_WHITE = new CSIColor(0xFFDEAD),
            NAVY_BLUE = new CSIColor(0x000080),  OCHRE = new CSIColor(0xCC7722),
            OLD_GOLD = new CSIColor(0xCFB53B),  OLD_LACE = new CSIColor(0xFDF5E6),
            OLD_LAVENDER = new CSIColor(0x796878),  OLD_ROSE = new CSIColor(0xC08081),
            OLIVE = new CSIColor(0x808000),  OLIVE_DRAB = new CSIColor(0x6B8E23),
            OLIVINE = new CSIColor(0x9AB973),  ORANGE_PEEL = new CSIColor(0xFFA000),
            ORANGE_RED = new CSIColor(0xFF4500),  ORCHID = new CSIColor(0xDA70D6),
            PALE_BLUE = new CSIColor(0xAFEEEE),  PALE_BROWN = new CSIColor(0x987654),
            PALE_CARMINE = new CSIColor(0xAF4035),  PALE_CHESTNUT = new CSIColor(0xDDADAF),
            PALE_CORNFLOWER_BLUE = new CSIColor(0xABCDEF),  PALE_MAGENTA = new CSIColor(0xF984E5),
            PALE_PINK = new CSIColor(0xFADADD),  PALE_RED_VIOLET = new CSIColor(0xDB7093),
            PAPAYA_WHIP = new CSIColor(0xFFEFD5),  PASTEL_GREEN = new CSIColor(0x77DD77),
            PASTEL_PINK = new CSIColor(0xFFD1DC),  PEACH = new CSIColor(0xFFE5B4),
            PEACH_ORANGE = new CSIColor(0xFFCC99),  PEACH_YELLOW = new CSIColor(0xFADFAD),
            PEAR = new CSIColor(0xD1E231),  PERIWINKLE = new CSIColor(0xCCCCFF),
            PERSIAN_BLUE = new CSIColor(0x1C39BB),  PERSIAN_GREEN = new CSIColor(0x00A693),
            PERSIAN_INDIGO = new CSIColor(0x32127A),  PERSIAN_RED = new CSIColor(0xCC3333),
            PERSIAN_PINK = new CSIColor(0xF77FBE),  PERSIAN_ROSE = new CSIColor(0xFE28A2),
            PERSIMMON = new CSIColor(0xEC5800),  PINE_GREEN = new CSIColor(0x01796F),
            TRUE_PINK = new CSIColor(0xFFC0CB),  PINK_ORANGE = new CSIColor(0xFF9966),
            PLATINUM = new CSIColor(0xE5E4E2),  PLUM = new CSIColor(0xCC99CC),
            POWDER_BLUE = new CSIColor(0xB0E0E6),  PUCE = new CSIColor(0xCC8899),
            PRUSSIAN_BLUE = new CSIColor(0x003153),  PSYCHEDELIC_PURPLE = new CSIColor(0xDD00FF),
            PUMPKIN = new CSIColor(0xFF7518),  PURPLE_TAUPE = new CSIColor(0x50404D),
            RAW_UMBER = new CSIColor(0x734A12),  RAZZMATAZZ = new CSIColor(0xE30B5C),
            RED_PIGMENT = new CSIColor(0xED1C24),  RED_VIOLET = new CSIColor(0xC71585),
            RICH_CARMINE = new CSIColor(0xD70040),  ROBIN_EGG_BLUE = new CSIColor(0x00CCCC),
            ROSE = new CSIColor(0xFF007F),  ROSE_MADDER = new CSIColor(0xE32636),
            ROSE_TAUPE = new CSIColor(0x905D5D),  ROYAL_BLUE = new CSIColor(0x4169E1),
            ROYAL_PURPLE = new CSIColor(0x6B3FA0),  RUBY = new CSIColor(0xE0115F),
            RUSSET = new CSIColor(0x80461B),  RUST = new CSIColor(0xB7410E),
            SAFETY_ORANGE = new CSIColor(0xFF6600),  SAFFRON = new CSIColor(0xF4C430),
            SALMON = new CSIColor(0xFF8C69),  SANDY_BROWN = new CSIColor(0xF4A460),
            SANGRIA = new CSIColor(0x92000A),  SAPPHIRE = new CSIColor(0x082567),
            SCARLET = new CSIColor(0xFF2400),  SCHOOL_BUS_YELLOW = new CSIColor(0xFFD800),
            SEA_GREEN = new CSIColor(0x2E8B57),  SEASHELL = new CSIColor(0xFFF5EE),
            SELECTIVE_YELLOW = new CSIColor(0xFFBA00),  SEPIA = new CSIColor(0x704214),
            SHAMROCK_GREEN = new CSIColor(0x009E60),  SHOCKING_PINK = new CSIColor(0xFC0FC0),
            SILVER = new CSIColor(0xC0C0C0),  SKY_BLUE = new CSIColor(0x87CEEB),
            SLATE_GRAY = new CSIColor(0x708090),  SMALT = new CSIColor(0x003399),
            SPRING_BUD = new CSIColor(0xA7FC00),  SPRING_GREEN = new CSIColor(0x00FF7F),
            STEEL_BLUE = new CSIColor(0x4682B4),  TAN = new CSIColor(0xD2B48C),
            TANGERINE = new CSIColor(0xF28500),
            TAUPE = new CSIColor(0x483C32),  TEA_GREEN = new CSIColor(0xD0F0C0),
            TEA_ORANGE = new CSIColor(0xF88379),  TEA_ROSE = new CSIColor(0xF4C2C2),
            TAWNY = new CSIColor(0xCD5700),  TERRA_COTTA = new CSIColor(0xE2725B),
            THISTLE = new CSIColor(0xD8BFD8),  TOMATO = new CSIColor(0xFF6347),
            TURQUOISE = new CSIColor(0x30D5C8),  TYRIAN_PURPLE = new CSIColor(0x66023C),
            ULTRAMARINE = new CSIColor(0x120A8F),  VEGAS_GOLD = new CSIColor(0xC5B358),
            VERMILION = new CSIColor(0xE34234),  VIOLET = new CSIColor(0x8B00FF),
            LIGHT_VIOLET = new CSIColor(0xEE82EE),  VIRIDIAN = new CSIColor(0x40826D),
            WHEAT = new CSIColor(0xF5DEB3),  WISTERIA = new CSIColor(0xC9A0DC),
            YELLOW_GREEN = new CSIColor(0x9ACD32),  ZINNWALDITE = new CSIColor(0xEBC2AF);
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

    /**Returns the name of the CSIColor constant that matches this color.
     * If there is no such content, returns "none"
     */
    public String toString(){
        String ret = "none";

        if(this.value == BLACK.value){
            ret = "BLACK";
        }else if(this.value == BLUE.value){
            ret = "BLUE";
        }else if(this.value == BROWN.value){
            ret = "BROWN";
        }else if(this.value == CYAN.value){
            ret = "CYAN";
        }else if(this.value == DARK_BLUE.value){
            ret = "DARK_BLUE";
        }else if(this.value == DARK_GRAY.value){
            ret = "DARK_GRAY";
        }else if(this.value == DARK_RED.value){
            ret = "DARK_RED";
        }else if(this.value == GRAY.value){
            ret = "GRAY";
        }else if(this.value == GREEN.value){
            ret = "GREEN";
        }else if(this.value == LIME.value){
            ret = "LIME";
        }else if(this.value == LIGHT_GRAY.value){
            ret = "LIGHT_GRAY";
        }else if(this.value == MAGENTA.value){
            ret = "MAGENTA";
        }else if(this.value == ORANGE.value){
            ret = "ORANGE";
        }else if(this.value == PINK.value){
            ret = "PINK";
        }else if(this.value == PURPLE.value){
            ret = "PURPLE";
        }else if(this.value == TEAL.value){
            ret = "TEAL";
        }else if(this.value == RED.value){
            ret = "RED";
        }else if(this.value == WHITE.value){
            ret = "WHITE";
        }else if(this.value == YELLOW.value){
            ret = "YELLOW";
        }else if(this.value == ALICE_BLUE.value){
            ret = "ALICE_BLUE";
        }else if(this.value == ALIZARIN.value){
            ret = "ALIZARIN";
        }else if(this.value == AMARANTH.value){
            ret = "AMARANTH";
        }else if(this.value == AMBER.value){
            ret = "AMBER";
        }else if(this.value == AMETHYST.value){
            ret = "AMETHYST";
        }else if(this.value == APRICOT.value){
            ret = "APRICOT";
        }else if(this.value == AQUAMARINE.value){
            ret = "AQUAMARINE";
        }else if(this.value == ARMY_GREEN.value){
            ret = "ARMY_GREEN";
        }else if(this.value == ASPARAGUS.value){
            ret = "ASPARAGUS";
        }else if(this.value == ATOMIC_TANGERINE.value){
            ret = "ATOMIC_TANGERINE";
        }else if(this.value == AUBURN.value){
            ret = "AUBURN";
        }else if(this.value == AZUL.value){
            ret = "AZUL";
        }else if(this.value == AZURE.value){
            ret = "AZURE";
        }else if(this.value == BABY_BLUE.value){
            ret = "BABY_BLUE";
        }else if(this.value == BEIGE.value){
            ret = "BEIGE";
        }else if(this.value == BISTRE.value){
            ret = "BISTRE";
        }else if(this.value == PIGMENT_BLUE.value){
            ret = "PIGMENT_BLUE";
        }else if(this.value == BLUE_GREEN.value){
            ret = "BLUE_GREEN";
        }else if(this.value == BLUE_VIOLET.value){
            ret = "BLUE_VIOLET";
        }else if(this.value == BONDI_BLUE.value){
            ret = "BONDI_BLUE";
        }else if(this.value == BRASS.value){
            ret = "BRASS";
        }else if(this.value == BRIGHT_GREEN.value){
            ret = "BRIGHT_GREEN";
        }else if(this.value == BRIGHT_PINK.value){
            ret = "BRIGHT_PINK";
        }else if(this.value == BRIGHT_TURQUOISE.value){
            ret = "BRIGHT_TURQUOISE";
        }else if(this.value == BRILLIANT_ROSE.value){
            ret = "BRILLIANT_ROSE";
        }else if(this.value == BRONZE.value){
            ret = "BRONZE";
        }else if(this.value == BROWNER.value){
            ret = "BROWNER";
        }else if(this.value == BUFF.value){
            ret = "BUFF";
        }else if(this.value == BURGUNDY.value){
            ret = "BURGUNDY";
        }else if(this.value == BURNT_ORANGE.value){
            ret = "BURNT_ORANGE";
        }else if(this.value == BURNT_SIENNA.value){
            ret = "BURNT_SIENNA";
        }else if(this.value == BURNT_UMBER.value){
            ret = "BURNT_UMBER";
        }else if(this.value == CAMO_GREEN.value){
            ret = "CAMO_GREEN";
        }else if(this.value == CAPUT_MORTUUM.value){
            ret = "CAPUT_MORTUUM";
        }else if(this.value == CARDINAL.value){
            ret = "CARDINAL";
        }else if(this.value == CARMINE.value){
            ret = "CARMINE";
        }else if(this.value == CARNATION_PINK.value){
            ret = "CARNATION_PINK";
        }else if(this.value == CAROLINA_BLUE.value){
            ret = "CAROLINA_BLUE";
        }else if(this.value == CARROT_ORANGE.value){
            ret = "CARROT_ORANGE";
        }else if(this.value == CELADON.value){
            ret = "CELADON";
        }else if(this.value == CERISE.value){
            ret = "CERISE";
        }else if(this.value == CERULEAN.value){
            ret = "CERULEAN";
        }else if(this.value == CERULEAN_BLUE.value){
            ret = "CERULEAN_BLUE";
        }else if(this.value == CHARTREUSE.value){
            ret = "CHARTREUSE";
        }else if(this.value == CHARTREUSE_GREEN.value){
            ret = "CHARTREUSE_GREEN";
        }else if(this.value == CHERRY_BLOSSOM.value){
            ret = "CHERRY_BLOSSOM";
        }else if(this.value == CHESTNUT.value){
            ret = "CHESTNUT";
        }else if(this.value == CHOCOLATE.value){
            ret = "CHOCOLATE";
        }else if(this.value == CINNABAR.value){
            ret = "CINNABAR";
        }else if(this.value == CINNAMON.value){
            ret = "CINNAMON";
        }else if(this.value == COBALT.value){
            ret = "COBALT";
        }else if(this.value == COLUMBIA_BLUE.value){
            ret = "COLUMBIA_BLUE";
        }else if(this.value == COPPER.value){
            ret = "COPPER";
        }else if(this.value == COPPER_ROSE.value){
            ret = "COPPER_ROSE";
        }else if(this.value == CORAL.value){
            ret = "CORAL";
        }else if(this.value == CORAL_RED.value){
            ret = "CORAL_RED";
        }else if(this.value == CORN.value){
            ret = "CORN";
        }else if(this.value == CORNFLOWER_BLUE.value){
            ret = "CORNFLOWER_BLUE";
        }else if(this.value == COSMIC_LATTE.value){
            ret = "COSMIC_LATTE";
        }else if(this.value == CREAM.value){
            ret = "CREAM";
        }else if(this.value == CRIMSON.value){
            ret = "CRIMSON";
        }else if(this.value == DARK_BROWN.value){
            ret = "DARK_BROWN";
        }else if(this.value == DARK_CERULEAN.value){
            ret = "DARK_CERULEAN";
        }else if(this.value == DARK_CHESTNUT.value){
            ret = "DARK_CHESTNUT";
        }else if(this.value == DARK_CORAL.value){
            ret = "DARK_CORAL";
        }else if(this.value == DARK_GOLDENROD.value){
            ret = "DARK_GOLDENROD";
        }else if(this.value == DARK_GREEN.value){
            ret = "DARK_GREEN";
        }else if(this.value == DARK_KHAKI.value){
            ret = "DARK_KHAKI";
        }else if(this.value == DARK_PASTEL_GREEN.value){
            ret = "DARK_PASTEL_GREEN";
        }else if(this.value == DARK_PINK.value){
            ret = "DARK_PINK";
        }else if(this.value == DARK_SCARLET.value){
            ret = "DARK_SCARLET";
        }else if(this.value == DARK_SALMON.value){
            ret = "DARK_SALMON";
        }else if(this.value == DARK_SLATE_GRAY.value){
            ret = "DARK_SLATE_GRAY";
        }else if(this.value == DARK_SPRING_GREEN.value){
            ret = "DARK_SPRING_GREEN";
        }else if(this.value == DARK_TAN.value){
            ret = "DARK_TAN";
        }else if(this.value == DARK_TURQUOISE.value){
            ret = "DARK_TURQUOISE";
        }else if(this.value == DARK_VIOLET.value){
            ret = "DARK_VIOLET";
        }else if(this.value == DEEP_CERISE.value){
            ret = "DEEP_CERISE";
        }else if(this.value == DEEP_CHESTNUT.value){
            ret = "DEEP_CHESTNUT";
        }else if(this.value == DEEP_FUCHSIA.value){
            ret = "DEEP_FUCHSIA";
        }else if(this.value == DEEP_LILAC.value){
            ret = "DEEP_LILAC";
        }else if(this.value == DEEP_MAGENTA.value){
            ret = "DEEP_MAGENTA";
        }else if(this.value == DEEP_PEACH.value){
            ret = "DEEP_PEACH";
        }else if(this.value == DEEP_PINK.value){
            ret = "DEEP_PINK";
        }else if(this.value == DENIM.value){
            ret = "DENIM";
        }else if(this.value == DODGER_BLUE.value){
            ret = "DODGER_BLUE";
        }else if(this.value == ECRU.value){
            ret = "ECRU";
        }else if(this.value == EGYPTIAN_BLUE.value){
            ret = "EGYPTIAN_BLUE";
        }else if(this.value == ELECTRIC_BLUE.value){
            ret = "ELECTRIC_BLUE";
        }else if(this.value == ELECTRIC_GREEN.value){
            ret = "ELECTRIC_GREEN";
        }else if(this.value == ELECTRIC_INDIGO.value){
            ret = "ELECTRIC_INDIGO";
        }else if(this.value == ELECTRIC_LIME.value){
            ret = "ELECTRIC_LIME";
        }else if(this.value == ELECTRIC_PURPLE.value){
            ret = "ELECTRIC_PURPLE";
        }else if(this.value == EMERALD.value){
            ret = "EMERALD";
        }else if(this.value == EGGPLANT.value){
            ret = "EGGPLANT";
        }else if(this.value == FALU_RED.value){
            ret = "FALU_RED";
        }else if(this.value == FERN_GREEN.value){
            ret = "FERN_GREEN";
        }else if(this.value == FIREBRICK.value){
            ret = "FIREBRICK";
        }else if(this.value == FLAX.value){
            ret = "FLAX";
        }else if(this.value == FOREST_GREEN.value){
            ret = "FOREST_GREEN";
        }else if(this.value == FRENCH_ROSE.value){
            ret = "FRENCH_ROSE";
        }else if(this.value == FUCSHIA_PINK.value){
            ret = "FUCSHIA_PINK";
        }else if(this.value == GAMBOGE.value){
            ret = "GAMBOGE";
        }else if(this.value == GOLD.value){
            ret = "GOLD";
        }else if(this.value == GOLDEN.value){
            ret = "GOLDEN";
        }else if(this.value == GOLDEN_BROWN.value){
            ret = "GOLDEN_BROWN";
        }else if(this.value == GOLDEN_YELLOW.value){
            ret = "GOLDEN_YELLOW";
        }else if(this.value == GOLDENROD.value){
            ret = "GOLDENROD";
        }else if(this.value == GRAY_ASPARAGUS.value){
            ret = "GRAY_ASPARAGUS";
        }else if(this.value == GREEN_YELLOW.value){
            ret = "GREEN_YELLOW";
        }else if(this.value == HAN_PURPLE.value){
            ret = "HAN_PURPLE";
        }else if(this.value == HELIOTROPE.value){
            ret = "HELIOTROPE";
        }else if(this.value == HOLLYWOOD_CERISE.value){
            ret = "HOLLYWOOD_CERISE";
        }else if(this.value == HOT_MAGENTA.value){
            ret = "HOT_MAGENTA";
        }else if(this.value == HOT_PINK.value){
            ret = "HOT_PINK";
        }else if(this.value == INDIGO_DYE.value){
            ret = "INDIGO_DYE";
        }else if(this.value == INDIGO.value){
            ret = "INDIGO";
        }else if(this.value == INTERNATIONAL_KLEIN_BLUE.value){
            ret = "INTERNATIONAL_KLEIN_BLUE";
        }else if(this.value == INTERNATIONAL_ORANGE.value){
            ret = "INTERNATIONAL_ORANGE";
        }else if(this.value == ISLAMIC_GREEN.value){
            ret = "ISLAMIC_GREEN";
        }else if(this.value == IVORY.value){
            ret = "IVORY";
        }else if(this.value == JADE.value){
            ret = "JADE";
        }else if(this.value == KELLY_GREEN.value){
            ret = "KELLY_GREEN";
        }else if(this.value == KHAKI.value){
            ret = "KHAKI";
        }else if(this.value == LIGHT_KHAKI.value){
            ret = "LIGHT_KHAKI";
        }else if(this.value == LAVENDER_FLORAL.value){
            ret = "LAVENDER_FLORAL";
        }else if(this.value == LAVENDER.value){
            ret = "LAVENDER";
        }else if(this.value == LAVENDER_BLUE.value){
            ret = "LAVENDER_BLUE";
        }else if(this.value == LAVENDER_BLUSH.value){
            ret = "LAVENDER_BLUSH";
        }else if(this.value == LAVENDER_GRAY.value){
            ret = "LAVENDER_GRAY";
        }else if(this.value == LAVENDER_MAGENTA.value){
            ret = "LAVENDER_MAGENTA";
        }else if(this.value == LAVENDER_PINK.value){
            ret = "LAVENDER_PINK";
        }else if(this.value == LAVENDER_PURPLE.value){
            ret = "LAVENDER_PURPLE";
        }else if(this.value == LAVENDER_ROSE.value){
            ret = "LAVENDER_ROSE";
        }else if(this.value == LAWN_GREEN.value){
            ret = "LAWN_GREEN";
        }else if(this.value == LEMON_CHIFFON.value){
            ret = "LEMON_CHIFFON";
        }else if(this.value == LIGHT_BLUE.value){
            ret = "LIGHT_BLUE";
        }else if(this.value == LIGHT_PINK.value){
            ret = "LIGHT_PINK";
        }else if(this.value == LILAC.value){
            ret = "LILAC";
        }else if(this.value == LEMON.value){
            ret = "LEMON";
        }else if(this.value == LIGHT_LIME.value){
            ret = "LIGHT_LIME";
        }else if(this.value == LIME_GREEN.value){
            ret = "LIME_GREEN";
        }else if(this.value == LINEN.value){
            ret = "LINEN";
        }else if(this.value == MAGENTA_DYE.value){
            ret = "MAGENTA_DYE";
        }else if(this.value == MAGIC_MINT.value){
            ret = "MAGIC_MINT";
        }else if(this.value == MAGNOLIA.value){
            ret = "MAGNOLIA";
        }else if(this.value == MALACHITE.value){
            ret = "MALACHITE";
        }else if(this.value == MAROON.value){
            ret = "MAROON";
        }else if(this.value == LIGHT_MAROON.value){
            ret = "LIGHT_MAROON";
        }else if(this.value == MAYA_BLUE.value){
            ret = "MAYA_BLUE";
        }else if(this.value == MAUVE.value){
            ret = "MAUVE";
        }else if(this.value == MAUVE_TAUPE.value){
            ret = "MAUVE_TAUPE";
        }else if(this.value == MEDIUM_BLUE.value){
            ret = "MEDIUM_BLUE";
        }else if(this.value == MEDIUM_CARMINE.value){
            ret = "MEDIUM_CARMINE";
        }else if(this.value == MEDIUM_LAVENDER_MAGENTA.value){
            ret = "MEDIUM_LAVENDER_MAGENTA";
        }else if(this.value == MEDIUM_PURPLE.value){
            ret = "MEDIUM_PURPLE";
        }else if(this.value == MEDIUM_SPRING_GREEN.value){
            ret = "MEDIUM_SPRING_GREEN";
        }else if(this.value == MIDNIGHT_BLUE.value){
            ret = "MIDNIGHT_BLUE";
        }else if(this.value == MINT_GREEN.value){
            ret = "MINT_GREEN";
        }else if(this.value == MISTY_ROSE.value){
            ret = "MISTY_ROSE";
        }else if(this.value == MOSS_GREEN.value){
            ret = "MOSS_GREEN";
        }else if(this.value == MOUNTBATTEN_PINK.value){
            ret = "MOUNTBATTEN_PINK";
        }else if(this.value == MUSTARD.value){
            ret = "MUSTARD";
        }else if(this.value == MYRTLE.value){
            ret = "MYRTLE";
        }else if(this.value == NAVAJO_WHITE.value){
            ret = "NAVAJO_WHITE";
        }else if(this.value == NAVY_BLUE.value){
            ret = "NAVY_BLUE";
        }else if(this.value == OCHRE.value){
            ret = "OCHRE";
        }else if(this.value == OLD_GOLD.value){
            ret = "OLD_GOLD";
        }else if(this.value == OLD_LACE.value){
            ret = "OLD_LACE";
        }else if(this.value == OLD_LAVENDER.value){
            ret = "OLD_LAVENDER";
        }else if(this.value == OLD_ROSE.value){
            ret = "OLD_ROSE";
        }else if(this.value == OLIVE.value){
            ret = "OLIVE";
        }else if(this.value == OLIVE_DRAB.value){
            ret = "OLIVE_DRAB";
        }else if(this.value == OLIVINE.value){
            ret = "OLIVINE";
        }else if(this.value == ORANGE_PEEL.value){
            ret = "ORANGE_PEEL";
        }else if(this.value == ORANGE_RED.value){
            ret = "ORANGE_RED";
        }else if(this.value == ORCHID.value){
            ret = "ORCHID";
        }else if(this.value == PALE_BLUE.value){
            ret = "PALE_BLUE";
        }else if(this.value == PALE_BROWN.value){
            ret = "PALE_BROWN";
        }else if(this.value == PALE_CARMINE.value){
            ret = "PALE_CARMINE";
        }else if(this.value == PALE_CHESTNUT.value){
            ret = "PALE_CHESTNUT";
        }else if(this.value == PALE_CORNFLOWER_BLUE.value){
            ret = "PALE_CORNFLOWER_BLUE";
        }else if(this.value == PALE_MAGENTA.value){
            ret = "PALE_MAGENTA";
        }else if(this.value == PALE_PINK.value){
            ret = "PALE_PINK";
        }else if(this.value == PALE_RED_VIOLET.value){
            ret = "PALE_RED_VIOLET";
        }else if(this.value == PAPAYA_WHIP.value){
            ret = "PAPAYA_WHIP";
        }else if(this.value == PASTEL_GREEN.value){
            ret = "PASTEL_GREEN";
        }else if(this.value == PASTEL_PINK.value){
            ret = "PASTEL_PINK";
        }else if(this.value == PEACH.value){
            ret = "PEACH";
        }else if(this.value == PEACH_ORANGE.value){
            ret = "PEACH_ORANGE";
        }else if(this.value == PEACH_YELLOW.value){
            ret = "PEACH_YELLOW";
        }else if(this.value == PEAR.value){
            ret = "PEAR";
        }else if(this.value == PERIWINKLE.value){
            ret = "PERIWINKLE";
        }else if(this.value == PERSIAN_BLUE.value){
            ret = "PERSIAN_BLUE";
        }else if(this.value == PERSIAN_GREEN.value){
            ret = "PERSIAN_GREEN";
        }else if(this.value == PERSIAN_INDIGO.value){
            ret = "PERSIAN_INDIGO";
        }else if(this.value == PERSIAN_RED.value){
            ret = "PERSIAN_RED";
        }else if(this.value == PERSIAN_PINK.value){
            ret = "PERSIAN_PINK";
        }else if(this.value == PERSIAN_ROSE.value){
            ret = "PERSIAN_ROSE";
        }else if(this.value == PERSIMMON.value){
            ret = "PERSIMMON";
        }else if(this.value == PINE_GREEN.value){
            ret = "PINE_GREEN";
        }else if(this.value == TRUE_PINK.value){
            ret = "TRUE_PINK";
        }else if(this.value == PINK_ORANGE.value){
            ret = "PINK_ORANGE";
        }else if(this.value == PLATINUM.value){
            ret = "PLATINUM";
        }else if(this.value == PLUM.value){
            ret = "PLUM";
        }else if(this.value == POWDER_BLUE.value){
            ret = "POWDER_BLUE";
        }else if(this.value == PUCE.value){
            ret = "PUCE";
        }else if(this.value == PRUSSIAN_BLUE.value){
            ret = "PRUSSIAN_BLUE";
        }else if(this.value == PSYCHEDELIC_PURPLE.value){
            ret = "PSYCHEDELIC_PURPLE";
        }else if(this.value == PUMPKIN.value){
            ret = "PUMPKIN";
        }else if(this.value == PURPLE_TAUPE.value){
            ret = "PURPLE_TAUPE";
        }else if(this.value == RAW_UMBER.value){
            ret = "RAW_UMBER";
        }else if(this.value == RAZZMATAZZ.value){
            ret = "RAZZMATAZZ";
        }else if(this.value == RED_PIGMENT.value){
            ret = "RED_PIGMENT";
        }else if(this.value == RED_VIOLET.value){
            ret = "RED_VIOLET";
        }else if(this.value == RICH_CARMINE.value){
            ret = "RICH_CARMINE";
        }else if(this.value == ROBIN_EGG_BLUE.value){
            ret = "ROBIN_EGG_BLUE";
        }else if(this.value == ROSE.value){
            ret = "ROSE";
        }else if(this.value == ROSE_MADDER.value){
            ret = "ROSE_MADDER";
        }else if(this.value == ROSE_TAUPE.value){
            ret = "ROSE_TAUPE";
        }else if(this.value == ROYAL_BLUE.value){
            ret = "ROYAL_BLUE";
        }else if(this.value == ROYAL_PURPLE.value){
            ret = "ROYAL_PURPLE";
        }else if(this.value == RUBY.value){
            ret = "RUBY";
        }else if(this.value == RUSSET.value){
            ret = "RUSSET";
        }else if(this.value == RUST.value){
            ret = "RUST";
        }else if(this.value == SAFETY_ORANGE.value){
            ret = "SAFETY_ORANGE";
        }else if(this.value == SAFFRON.value){
            ret = "SAFFRON";
        }else if(this.value == SALMON.value){
            ret = "SALMON";
        }else if(this.value == SANDY_BROWN.value){
            ret = "SANDY_BROWN";
        }else if(this.value == SANGRIA.value){
            ret = "SANGRIA";
        }else if(this.value == SAPPHIRE.value){
            ret = "SAPPHIRE";
        }else if(this.value == SCARLET.value){
            ret = "SCARLET";
        }else if(this.value == SCHOOL_BUS_YELLOW.value){
            ret = "SCHOOL_BUS_YELLOW";
        }else if(this.value == SEA_GREEN.value){
            ret = "SEA_GREEN";
        }else if(this.value == SEASHELL.value){
            ret = "SEASHELL";
        }else if(this.value == SELECTIVE_YELLOW.value){
            ret = "SELECTIVE_YELLOW";
        }else if(this.value == SEPIA.value){
            ret = "SEPIA";
        }else if(this.value == SHAMROCK_GREEN.value){
            ret = "SHAMROCK_GREEN";
        }else if(this.value == SHOCKING_PINK.value){
            ret = "SHOCKING_PINK";
        }else if(this.value == SILVER.value){
            ret = "SILVER";
        }else if(this.value == SKY_BLUE.value){
            ret = "SKY_BLUE";
        }else if(this.value == SLATE_GRAY.value){
            ret = "SLATE_GRAY";
        }else if(this.value == SMALT.value){
            ret = "SMALT";
        }else if(this.value == SPRING_BUD.value){
            ret = "SPRING_BUD";
        }else if(this.value == SPRING_GREEN.value){
            ret = "SPRING_GREEN";
        }else if(this.value == STEEL_BLUE.value){
            ret = "STEEL_BLUE";
        }else if(this.value == TAN.value){
            ret = "TAN";
        }else if(this.value == TANGERINE.value){
            ret = "TANGERINE";
        }else if(this.value == TAUPE.value){
            ret = "TAUPE";
        }else if(this.value == TEA_GREEN.value){
            ret = "TEA_GREEN";
        }else if(this.value == TEA_ORANGE.value){
            ret = "TEA_ORANGE";
        }else if(this.value == TEA_ROSE.value){
            ret = "TEA_ROSE";
        }else if(this.value == TAWNY.value){
            ret = "TAWNY";
        }else if(this.value == TERRA_COTTA.value){
            ret = "TERRA_COTTA";
        }else if(this.value == THISTLE.value){
            ret = "THISTLE";
        }else if(this.value == TOMATO.value){
            ret = "TOMATO";
        }else if(this.value == TURQUOISE.value){
            ret = "TURQUOISE";
        }else if(this.value == TYRIAN_PURPLE.value){
            ret = "TYRIAN_PURPLE";
        }else if(this.value == ULTRAMARINE.value){
            ret = "ULTRAMARINE";
        }else if(this.value == VEGAS_GOLD.value){
            ret = "VEGAS_GOLD";
        }else if(this.value == VERMILION.value){
            ret = "VERMILION";
        }else if(this.value == VIOLET.value){
            ret = "VIOLET";
        }else if(this.value == LIGHT_VIOLET.value){
            ret = "LIGHT_VIOLET";
        }else if(this.value == VIRIDIAN.value){
            ret = "VIRIDIAN";
        }else if(this.value == WHEAT.value){
            ret = "WHEAT";
        }else if(this.value == WISTERIA.value){
            ret = "WISTERIA";
        }else if(this.value == YELLOW_GREEN.value){
            ret = "YELLOW_GREEN";
        }else if(this.value == ZINNWALDITE.value){
            ret = "ZINNWALDITE";
        }

        return ret;
    }

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

    public int getGray(){
        return (getR() + getG() + getB());
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
    public static CSIColor getColorFromCode(int code) {
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
    public static int getCodeFromColor(CSIColor color) {
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

