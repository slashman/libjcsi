/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.slashie.libjcsi.examples.luck.toybox;

import java.util.Random;
import net.slashie.libjcsi.CSIColor;
//import net.slashie.libjcsi.jcurses.JCursesConsoleInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 *
 * @author ehoward
 */
public class CustomColorImageTest {

    String text = "rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjzYaTSLzcrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrlusUVfUaeljYZbXXpddddXXbxnIltJCnnnJjrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrctkBHBFdyyDpKNKEmmmRFpXdbyydQBQQKKmRRmKBBberrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrjufwpRKAMWBKmRqbPTYYLfEMBMMHHHHHHRsOYYLLeeLLYSpHHavvolcrrrrrrrrrrrrrrrrrrrrrroVqENmbghUSsssSUhgdmNNqZTpHHRbbFHHRUOOYaUhgyXqRmmmABAQQQQKRpwTJrrrrrrrrrrrrrrrJyHHphYLeeLLOOYYsssssaVkQMRhNHKffkHHyegFQEHHHHMBRdkhSYOOYsaUhgXBHAxorrrrrrrrrrrGHHwOeOThgDXFmmRmKBMHHHHHMBFVEHHQARNApDNHHHWmdkkwdqmQBARDhsLOOOOOUpHWhrrrrrrrrryHEGfbmBBQmpyZPwpAEWNKpbwgxxpNHKwGZRHNgxggwbqKQKdhYeLsTGDKWWqxYOYsYLhMHwrrrrrrrLHHMENFwhSYeLTDBEKbPTsOOOOeaFHNGOOOOLxWHpLLOOOYTgRMMpVOOOOOSgAHNgYOssLGHHTrrrrrIpHMphsOOYOYPAHBwTOOOYYsssOhEHFsOYssssOUEHRYYssYYOOayMHpSOssYOOhQHAxOYYOKHKrrrrCBHRTLOYssOVKHBGYOYsssssssLZHHDOYsssssssOfHHbOssssssYLaqHExOsssYOOkHHbOYOdHMIrroEHFOOssssOZMHbYOYssssssssOZHHyLYsssssssssLDHHhOsssssssYODHHGOssssYLhWHqOLdHMIrrGHHUOssssOxHHDLYsssssssssYPHHDOYssssssssssYsNHKYYsssssssYOqHMTYsssssLhHHbLmHQrrrkHMSYsssYYAHQYYssssssssssYVGGYYssssssssssssLwHHhOssssssssYSWHqOssssssLbHHZWHZrrrOHHZLsssYVHHDLssssssssssssOLLssssssssssssssYSWHFOsssssssssOqHWSYsssssYSWHHHMtrrrrdHNSOssYUHHbLssssssssssYsxGVYssssssssssssssOFMBsYssssssssOyHHVYsssssYYKHHHhrrrrrIFHASLYYYKHNsYsssssssssYsWHmOYsssssssssssssYsSSssssssssssObHHUYsssssYOmHAJrrrrrrrcDHWPOYYVMHpOYssssssssYYAHQYYssssssssssssssOLLYssssssssYYQHAYYsssssYsEHdrrrrrrrrrrsBHRUOLfEHFaLYsssssssOpHWSYsssssssssssssYhgZsYsssssssLPHHgOssssssLkHHerrrrrrrrrrrIhWHFVLspHWgYOYsssssOPHHZOsssssssssssssOKHWsYssssssLUWHFOYssssYLhMHZrrrrrrrrrrrrrrjfQHBkafFHNwTOOYYsYYAHAYYsssssssssssYSWHqOsssssYLhWHFYYssYYOYkHHGrrrrrrrrrrrrrrrrrrngQWQXXQHHmyVYOOLfHHbLYsssssssssYLXHMUYsYYOOTdHWgLOOOOYTwEHqurrrrrrrrrrrrrrrrrrrrrrvSgdqQWHANNmbGSPWHDOOYsssssYOObHWxeOOYTgKHHBgfxPkDFAEAkCrrrrrrrrrrrrrrrrrrrrrrrrrrrrrcjjrjehbmAQMHHAxYOOOOOsPBHHdPyXRBNQwPybXppXbkhYtrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrroCJLbBKXwkwXQAyehkgGVsCcrrrrrrrrrrrrrrrrrrrrrrrrrr";
    
    WSwingConsoleInterface mainInterface;
//    JCursesConsoleInterface mainInterface;
    Random rng = new Random();

    public CustomColorImageTest() {
        try {
            mainInterface = new WSwingConsoleInterface("CSIColor Test", false);
//            mainInterface = new JCursesConsoleInterface();
        } catch (ExceptionInInitializerError eiie) {
            System.out.println("Fatal Error Initializing Swing Console Box");
            eiie.printStackTrace();
            System.exit(-1);
        }

        char[] c = new char[mainInterface.xdim * mainInterface.ydim];
//        char[] c = new char[80 * 25];
        c = text.toCharArray();
        int x = 0, g;
        CSIColor frontColor;
        for (int i = 0; i < mainInterface.xdim; i++) {
            for (int k = 0; k < mainInterface.ydim; k++) {
                x = i + k * mainInterface.xdim;
//        for (int i = 0; i < 80; i++) {
//            for (int k = 0; k < 25; k++) {
//                x = i + k * 80;

                        frontColor = new CSIColor(CSIColor.ORANGE);
                switch (c[x]) {
                    case 'r':
                        g = 40 + k;
                        frontColor = new CSIColor(g, g, g);
                    break;
                    default:
                        frontColor.setG(Integer.valueOf(c[x]));
                        frontColor.setR(Math.min((Integer.valueOf(c[x]) + 150),255));
                }

                mainInterface.print(i, k, c[x], new CSIColor(frontColor));
            }
        }
    }

    public static void main(String[] args) {
        new CustomColorImageTest();
    }
}
