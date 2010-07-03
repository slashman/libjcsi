package net.slashie.libjcsi.examples;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.CharKey;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

public class BufferExample {
	public static void main (String args[]){
		ConsoleSystemInterface csi = new WSwingConsoleInterface("test");
		csi.cls();
		for (int y = 2; y < 21; y++) {
			csi.print(2, y, "Hello buffer", csi.RED);
		}
		csi.refresh();
		CharKey selection = csi.inkey();
		csi.saveBuffer();
		/*Blank space*/
		for (int y = 2; y < 21; y++) {
		csi.print(2, y, "                   ", csi.BLACK);
		}
		csi.refresh();

		/* Collest player selection*/                
		selection = csi.inkey();

		/* Restore original view*/                
		csi.restore();
		csi.refresh();
	}
}
