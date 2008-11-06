package net.slashie.libjcsi.examples.luck.toybox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 * This is a test for refreshing issues with the Swing interface.
 * @author Eben Howard
 */
public class RefreshTest {

    WSwingConsoleInterface mainInterface;
    Random rng = new Random();

    public RefreshTest() {
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
        Random rng = new Random();

        CSIColor tempColor = CSIColor.WHITE, backColor = CSIColor.BLACK;

        do {
            tempColor = list.get(rng.nextInt(list.size()));
            backColor = list.get(rng.nextInt(list.size()));
            for (int k = 0; k < mainInterface.ydim; k++) {
                for (int i = 0; i < mainInterface.xdim; i++) {
                    mainInterface.print(i, k, 'Q', tempColor, backColor);
                }
            }
            mainInterface.refresh();
            mainInterface.waitKey(CharKey.ENTER);
        } while (true);

    }

    public static void main(String[] args) {
        new RefreshTest();
    }
}
