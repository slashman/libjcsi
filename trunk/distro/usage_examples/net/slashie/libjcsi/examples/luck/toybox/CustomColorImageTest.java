package net.slashie.libjcsi.examples.luck.toybox;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *This will output a color image of a pumpkin to a Swing interface.
 * @author Eben Howard
 */
public class CustomColorImageTest {

    String text = "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjzYaTSLzcrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrlusUVfUaeljYZbXXpddddXXbxnIltJCnnnJjrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrctkBHBFdyyDpKNKEmmmRFpXdbyydQBQQKKmRRmKBBberrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjufwpRKAMWBKmRqbPTYYLfEMBMMHHHHHHRsOYYLLeeLLYSpHHavvolcrrrrrrrrrrrrrrrrrrrrrroVqENmbghUSsssSUhgdmNNqZTpHHRbbFHHRUOOYaUhgyXqRmmmABAQQQQKRpwTJrrrrrrrrrrrrrrrJyHHphYLeeLLOOYYsssssaVkQMRhNHKffkHHyegFQEHHHHMBRdkhSYOOYsaUhgXBHAxorrrrrrrrrrrGHHwOeOThgDXFmmRmKBMHHHHHMBFVEHHQARNApDNHHHWmdkkwdqmQBARDhsLOOOOOUpHWhrrrrrrrrryHEGfbmBBQmpyZPwpAEWNKpbwgxxpNHKwGZRHNgxggwbqKQKdhYeLsTGDKWWqxYOYsYLhMHwrrrrrrrLHHMENFwhSYeLTDBEKbPTsOOOOeaFHNGOOOOLxWHpLLOOOYTgRMMpVOOOOOSgAHNgYOssLGHHTrrrrrIpHMphsOOYOYPAHBwTOOOYYsssOhEHFsOYssssOUEHRYYssYYOOayMHpSOssYOOhQHAxOYYOKHKrrrrCBHRTLOYssOVKHBGYOYsssssssLZHHDOYsssssssOfHHbOssssssYLaqHExOsssYOOkHHbOYOdHMIrroEHFOOssssOZMHbYOYssssssssOZHHyLYsssssssssLDHHhOsssssssYODHHGOssssYLhWHqOLdHMIrrGHHUOssssOxHHDLYsssssssssYPHHDOYssssssssssYsNHKYYsssssssYOqHMTYsssssLhHHbLmHQrrrkHMSYsssYYAHQYYssssssssssYVGGYYssssssssssssLwHHhOssssssssYSWHqOssssssLbHHZWHZrrrOHHZLsssYVHHDLssssssssssssOLLssssssssssssssYSWHFOsssssssssOqHWSYsssssYSWHHHMtrrrrdHNSOssYUHHbLssssssssssYsxGVYssssssssssssssOFMBsYssssssssOyHHVYsssssYYKHHHhrrrrrIFHASLYYYKHNsYsssssssssYsWHmOYsssssssssssssYsSSssssssssssObHHUYsssssYOmHAJrrrrrrrcDHWPOYYVMHpOYssssssssYYAHQYYssssssssssssssOLLYssssssssYYQHAYYsssssYsEHdrrrrrrrrrrsBHRUOLfEHFaLYsssssssOpHWSYsssssssssssssYhgZsYsssssssLPHHgOssssssLkHHerrrrrrrrrrrIhWHFVLspHWgYOYsssssOPHHZOsssssssssssssOKHWsYssssssLUWHFOYssssYLhMHZrrrrrrrrrrrrrrjfQHBkafFHNwTOOYYsYYAHAYYsssssssssssYSWHqOsssssYLhWHFYYssYYOYkHHGrrrrrrrrrrrrrrrrrrngQWQXXQHHmyVYOOLfHHbLYsssssssssYLXHMUYsYYOOTdHWgLOOOOYTwEHqurrrrrrrrrrrrrrrrrrrrrrvSgdqQWHANNmbGSPWHDOOYsssssYOObHWxeOOYTgKHHBgfxPkDFAEAkCrrrrrrrrrrrrrrrrrrrrrrrrrrrrrcjjrjehbmAQMHHAxYOOOOOsPBHHdPyXRBNQwPybXppXbkhYtrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrroCJLbBKXwkwXQAyehkgGVsCcrrrrrrrrrrrrrrrrrrrrrrrrrr";
    WSwingConsoleInterface mainInterface;
    Random rng = new Random();

    public CustomColorImageTest() {
        try {
            mainInterface = new WSwingConsoleInterface("CSIColor Test", false);
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }

        char[] c = new char[mainInterface.xdim * mainInterface.ydim];
        c = text.toCharArray();
        int x = 0, g;
        CSIColor frontColor;
        for (int i = 0; i < mainInterface.xdim; i++) {
            for (int k = 0; k < mainInterface.ydim; k++) {
                x = i + k * mainInterface.xdim;

                frontColor = new CSIColor(CSIColor.ORANGE);
                switch (c[x]) {
                    case 'r':
                        g = 40 + k;
                        frontColor = new CSIColor(g, g, g);
                        break;
                    default:
                        frontColor.setG(Integer.valueOf(c[x]));
                        frontColor.setR(Math.min((Integer.valueOf(c[x]) + 150), 255));
                }

                mainInterface.print(i, k, c[x], new CSIColor(frontColor));
            }
        }
    }

    public static void main(String[] args) {
        new CustomColorImageTest();
    }
}
