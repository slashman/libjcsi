package net.slashie.libjcsi.examples.luck.toybox;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.jcurses.JCursesConsoleInterface;

/**
 *This will print out a color image of a pumpkin using a JCurses interface
 * @author Santiago Zapata
 */
public class CustomColorImageTestJC {

    String text = "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjzYaTSLzcrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrlusUVfUaeljYZbXXpddddXXbxnIltJCnnnJjrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrctkBHBFdyyDpKNKEmmmRFpXdbyydQBQQKKmRRmKBBberrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjufwpRKAMWBKmRqbPTYYLfEMBMMHHHHHHRsOYYLLeeLLYSpHHavvolcrrrrrrrrrrrrrrrrrrrrrroVqENmbghUSsssSUhgdmNNqZTpHHRbbFHHRUOOYaUhgyXqRmmmABAQQQQKRpwTJrrrrrrrrrrrrrrrJyHHphYLeeLLOOYYsssssaVkQMRhNHKffkHHyegFQEHHHHMBRdkhSYOOYsaUhgXBHAxorrrrrrrrrrrGHHwOeOThgDXFmmRmKBMHHHHHMBFVEHHQARNApDNHHHWmdkkwdqmQBARDhsLOOOOOUpHWhrrrrrrrrryHEGfbmBBQmpyZPwpAEWNKpbwgxxpNHKwGZRHNgxggwbqKQKdhYeLsTGDKWWqxYOYsYLhMHwrrrrrrrLHHMENFwhSYeLTDBEKbPTsOOOOeaFHNGOOOOLxWHpLLOOOYTgRMMpVOOOOOSgAHNgYOssLGHHTrrrrrIpHMphsOOYOYPAHBwTOOOYYsssOhEHFsOYssssOUEHRYYssYYOOayMHpSOssYOOhQHAxOYYOKHKrrrrCBHRTLOYssOVKHBGYOYsssssssLZHHDOYsssssssOfHHbOssssssYLaqHExOsssYOOkHHbOYOdHMIrroEHFOOssssOZMHbYOYssssssssOZHHyLYsssssssssLDHHhOsssssssYODHHGOssssYLhWHqOLdHMIrrGHHUOssssOxHHDLYsssssssssYPHHDOYssssssssssYsNHKYYsssssssYOqHMTYsssssLhHHbLmHQrrrkHMSYsssYYAHQYYssssssssssYVGGYYssssssssssssLwHHhOssssssssYSWHqOssssssLbHHZWHZrrrOHHZLsssYVHHDLssssssssssssOLLssssssssssssssYSWHFOsssssssssOqHWSYsssssYSWHHHMtrrrrdHNSOssYUHHbLssssssssssYsxGVYssssssssssssssOFMBsYssssssssOyHHVYsssssYYKHHHhrrrrrIFHASLYYYKHNsYsssssssssYsWHmOYsssssssssssssYsSSssssssssssObHHUYsssssYOmHAJrrrrrrrcDHWPOYYVMHpOYssssssssYYAHQYYssssssssssssssOLLYssssssssYYQHAYYsssssYsEHdrrrrrrrrrrsBHRUOLfEHFaLYsssssssOpHWSYsssssssssssssYhgZsYsssssssLPHHgOssssssLkHHerrrrrrrrrrrIhWHFVLspHWgYOYsssssOPHHZOsssssssssssssOKHWsYssssssLUWHFOYssssYLhMHZrrrrrrrrrrrrrrjfQHBkafFHNwTOOYYsYYAHAYYsssssssssssYSWHqOsssssYLhWHFYYssYYOYkHHGrrrrrrrrrrrrrrrrrrngQWQXXQHHmyVYOOLfHHbLYsssssssssYLXHMUYsYYOOTdHWgLOOOOYTwEHqurrrrrrrrrrrrrrrrrrrrrrvSgdqQWHANNmbGSPWHDOOYsssssYOObHWxeOOYTgKHHBgfxPkDFAEAkCrrrrrrrrrrrrrrrrrrrrrrrrrrrrrcjjrjehbmAQMHHAxYOOOOOsPBHHdPyXRBNQwPybXppXbkhYtrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrroCJLbBKXwkwXQAyehkgGVsCcrrrrrrrrrrrrrrrrrrrrrrrrrr";
    ConsoleSystemInterface mainInterface;
    Random rng = new Random(1000000);

    public CustomColorImageTestJC() {
        try {
            mainInterface = new JCursesConsoleInterface();
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }

        char[] c = text.toCharArray();
        int x = 0, g;
        CSIColor frontColor;

        int rx, gx, bx;
        rx = 0;
        gx = 80;
        bx = 160;
        for (int i = 0; i < 80; i++) {
            for (int k = 0; k < 25; k++) {
                rx++;
                gx++;
                bx++;
                if (rx == 250) {
                    rx = (int) Math.round(rng.nextDouble() * 200);
                }
                if (gx == 250) {
                    gx = (int) Math.round(rng.nextDouble() * 200);
                }
                if (bx == 250) {
                    bx = (int) Math.round(rng.nextDouble() * 200);
                }
                x = i + k * 80;

                frontColor = new CSIColor(CSIColor.ORANGE);
                switch (c[x]) {
                    case 'r':
                        g = 40 + k;
                        frontColor = new CSIColor(g, g, g);
                        break;
                    default:
                        frontColor.setR(rx);
                        frontColor.setG(gx);
                        frontColor.setB(bx);
                }

                mainInterface.print(i, k, '#', new CSIColor(frontColor));
            }
        }
        mainInterface.waitKey(CharKey.SPACE);
    }

    public static void main(String[] args) {
        new CustomColorImageTestJC();
    }
}
