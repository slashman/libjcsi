package net.slashie.libjcsi;

/**
 *
 * @author ehoward
 */
public enum CSIColorEnum {

    BLACK(0x000000),
    BLUE(0x0000ff),
    BROWN(0x808000),
    CYAN(0x00ffff),
    DARK_BLUE(0x0000c8),
    DARK_GRAY(0X404040),
    DARK_RED(0x800000),
    GRAY(0x808080),
    GREEN(0x008000),
    LEMON(0x00FF00),// duplicated as LIGHT_GREEN for useability
    LIGHT_GRAY(0xc0c0c0),
    LIGHT_GREEN(0x00FF00),
    MAGENTA(0xff00ff),
    ORANGE(0xffc800),
    PINK(0xffafaf),
    PURPLE(0x800080),
    TEAL(0x008080),
    RED(0xff0000),
    WHITE(0xffffff),
    YELLOW(0xffff00);
    
    int color;

    private CSIColorEnum(int value) {
        color = value;
    }
}
