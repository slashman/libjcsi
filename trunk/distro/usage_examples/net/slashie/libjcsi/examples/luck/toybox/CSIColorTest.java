package net.slashie.libjcsi.examples.luck.toybox;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 * This will display a color test using CSIColor objects.
 * @author Eben Howard
 */
public class CSIColorTest {

    WSwingConsoleInterface mainInterface;
    Random rng = new Random();

    public CSIColorTest() {
        try {
            mainInterface = new WSwingConsoleInterface("CSIColor Test", false);
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }
        int xdiv = 255 / 80, ydiv = 255 / 25;
        
        CSIColor tempColor;
        for (int i = 0; i < mainInterface.xdim; i++) {
            for (int k = 0; k < mainInterface.ydim; k++) {
               tempColor = new CSIColor(i * xdiv, k * ydiv, (i * xdiv + k * ydiv) / 2);
                mainInterface.print(i, k, "#", new CSIColor(tempColor));
            }
        }
    }

    public static void main(String[] args) {
        new CSIColorTest();
    }
}