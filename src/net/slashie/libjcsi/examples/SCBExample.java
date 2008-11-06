package net.slashie.libjcsi.examples;

import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 * This shows a basic output window using the Swing interface.
 * @author Santiago Zapata
 */
public class SCBExample {

    public static void main(String[] args) {
        ConsoleSystemInterface csi = null;
        try {
            csi = new WSwingConsoleInterface("libjcsi example - Santiago Zapata", false);
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }
        csi.cls();
        csi.print(1, 1, "Hello, Hello!", ConsoleSystemInterface.CYAN);
        csi.print(2, 3, "This is printed using the Java Console System Interface lib. (libjcsi)");
        csi.print(2, 4, "Swing Console Box Implementation", ConsoleSystemInterface.RED);

        csi.print(5, 6, "########", ConsoleSystemInterface.GRAY);
        csi.print(5, 7, "#......#", ConsoleSystemInterface.GRAY);
        csi.print(5, 8, "#......#", ConsoleSystemInterface.GRAY);
        csi.print(5, 9, "####/###", ConsoleSystemInterface.GRAY);

        csi.print(6, 7, "......", ConsoleSystemInterface.BLUE);
        csi.print(6, 8, "......", ConsoleSystemInterface.BLUE);

        csi.print(9, 9, "/", ConsoleSystemInterface.BROWN);

        csi.print(8, 8, "@", ConsoleSystemInterface.RED);
    }
}
