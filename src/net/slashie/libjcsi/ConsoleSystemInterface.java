package net.slashie.libjcsi;

import net.slashie.util.Position;

/**
 * Allows for easy output to a console screen emulation and keyboard input.
 * 
 * @author Santiago Zapata
 * @author Eben Howard
 */
public interface ConsoleSystemInterface {

    /**
     * Prints a character on the console
     * @param x horizontal position
     * @param y vertical position
     * @param what The character to be printed
     * @param color The color, one of the ConsoleSystemInterface constants
     */
    public void print(int x, int y, char what, int color);

    /**
     * Same as print but must check for validity of the coordinates
     * @param x horizontal position
     * @param y vertical position
     * @param what The character to be printed
     * @param color The color, one of the ConsoleSystemInterface constants
     */
    public void safeprint(int x, int y, char what, int color);

    /**
     * Prints a String on the console in the color specified.
     * 
     * Does not check for running of the edge of the screen, so calling function
     * must do such a check to avoid errors.
     * @param x horizontal starting position
     * @param y vertical position
     * @param what The string to be printed
     * @param color The color, one of the ConsoleSystemInterface constants
     */
    public void print(int x, int y, String what, int color);

    /**
     * Prints a String on the console with the default color.
     * 
     * Does not check for running of the edge of the screen, so calling function
     * must do such a check to avoid errors.
     * @param x horizontal starting position
     * @param y vertical starting position
     * @param what The String to be printed
     */
    public void print(int x, int y, String what);

    /**
     * Checks what character is at a given position
     * @param x horizontal position
     * @param y vertical position
     * @return The character at the x,y position
     */
    public char peekChar(int x, int y);

    /**
     * Checks what color is at a given position
     * @param x horizontal position
     * @param y vertical position
     * @return The color at the x,y position
     */
    public int peekColor(int x, int y);

    /**
     * Waits until a key is pressed and returns it 
     * @return The key that was pressed
     */
    public CharKey inkey();

    /**
     * Locates the input caret on a given position
     * @param x horizontal position
     * @param y vertical position
     */
    public void locateCaret(int x, int y);

    /**
     * Reads a string from the keyboard
     * @return The String that was read after pressing enter
     */
    public String input();

    /**
     * Reads a string from the keyboard with a maximum length
     * @return The String that was read after pressing enter, truncated at specified length
     */
    public String input(int length);

    /**
     * Checks if the position is valid
     * @param e position to be tested
     * @return true if the position is valid
     */
    public boolean isInsideBounds(Position e);

    /**
     * Clears the screen
     *
     */
    public void cls();

    /**
     * Refreshes the screen, printing all characters that were buffered
     * 
     * Some implementations may instead write directly to the console
     */
    public void refresh();

    /**
     * Refreshes the screen, printing all characters that were buffered, and interrupts the Thread
     * 
     * Some implementations may instead write directly to the console
     */
    public void refresh(Thread t);

    /**
     * Makes the screen flash with a given color
     * @param color
     */
    public void flash(int color);

    /**
     * This flushes the internal Hashtable for the colors.
     * Should be called when changing the map drasticaly, such as during a level change event.
     */
    public void flushColorTable();

    /**
     * Sets whether or not a buffer will be used 
     * @param value true to activate buffer
     */
    public void setAutoRefresh(boolean value);

    /**
     * Waits for the user to press a key
     * @param keyCode code of specific key to wait for
     */
    public void waitKey(int keyCode);

    /**
     * Saves the screen contents to a backup buffer
     *
     */
    public void saveBuffer();

    /**
     * Restores the contents of the backup buffer to screen
     *
     */
    public void restore();
    public static final int BLACK = 0,  DARK_BLUE = 1,  GREEN = 2,  TEAL = 3,  DARK_RED = 4,  PURPLE = 5,  BROWN = 6,  LIGHT_GRAY = 7,  GRAY = 8,  BLUE = 9,  LEMON = 10,  CYAN = 11,  RED = 12,  MAGENTA = 13,  YELLOW = 14,  WHITE = 15;

    /**
     * Prints a character on the console, using a custom color
     * @param x horizontal position
     * @param y vertical position
     * @param what The character to be printed
     * @param color The color, a rgba instance of CSIColor
     */
    public void print(int x, int y, char what, CSIColor color);

    /**
     * Prints a String on the console, using a custom color.
     * 
     * Does not check for running of the edge of the screen, so calling function
     * must do such a check to avoid errors.
     * @param x horizontal position
     * @param y vertical position
     * @param what The String to be printed
     * @param color The color, a rgba instance of CSIColor
     */
    public void print(int x, int y, String what, CSIColor color);
}