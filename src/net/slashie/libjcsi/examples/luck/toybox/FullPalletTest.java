package net.slashie.libjcsi.examples.luck.toybox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 * This will display in a repeating pattern all of the custom named colors
 * available in CSIColor.  The test include displaying the colors onto a
 * black, grey, and white background.
 * @author Eben Howard
 */
public class FullPalletTest {

    WSwingConsoleInterface mainInterface;
    Random rng = new Random();

    public FullPalletTest() {
        ArrayList<CSIColor> list = new ArrayList<CSIColor>();
        for (int i = 0; i < CSIColor.FULL_PALLET.length; i++) {
            list.add(CSIColor.FULL_PALLET[i]);
        }

        Collections.sort(list);

        try {
            mainInterface = new WSwingConsoleInterface("CSIColor Test", false);
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }
        int x = 0, times = 0;

        CSIColor tempColor = CSIColor.WHITE, backColor = CSIColor.BLACK;
        for (int k = 0; k < mainInterface.ydim; k++) {
            for (int i = 0; i < mainInterface.xdim; i++) {

                if (!(x < list.size())) {
                    x = 0;
                    times++;
                }
                tempColor = list.get(x);
                x++;
                switch (times) {
                    case 0:
                        backColor = CSIColor.BLACK;
                        break;
                    case 1:
                        backColor = CSIColor.GRAY;
                        break;
                    case 2:
                        backColor = CSIColor.WHITE;
                        break;
                    case 3:
                        backColor = CSIColor.BLACK;
                        times = 0;
                }

                mainInterface.print(i, k, 'Q', new CSIColor(tempColor), new CSIColor(backColor));
            }
        }
    }

    public static void main(String[] args) {
        new FullPalletTest();
    }
}
