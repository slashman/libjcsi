
package net.slashie.libjcsi.examples.luck.toybox;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

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

        //--------for testing screen size-----//
        CSIColor tempColor = new CSIColor(50, 50, 50);
        int x = 0;
        for (int i = 0; i < mainInterface.xdim; i++) {
            for (int k = 0; k < mainInterface.ydim; k++) {
                x = k;
                while (x > 9) {
                    x = (i / 10);
                }
                tempColor.setR(i * 3);
                tempColor.setB(k * 10);
//                tempColor.setG(((i * 3) + (k * 10)) / 2);
                mainInterface.print(i, k, "#", new CSIColor(tempColor));
            }
        }
    }

    public static void main(String[] args){
        new CSIColorTest();
    }
}