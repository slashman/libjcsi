package net.slashie.libjcsi.examples.luck.toybox;

import net.slashie.libjcsi.CSIColor;
import net.slashie.libjcsi.ConsoleSystemInterface;
import net.slashie.libjcsi.wswing.WSwingConsoleInterface;

/**
 * An example of Luck using the Swing interface.
 * @author Santiago Zapata
 */
class LuckCSIColor {

    int a, b, c, d, k, l, o, p, q, w, v, r, i, e = 600, f, h;
    boolean m[][];

    int r() {
        return (int) (Math.random() * 17 + 1);
    }
    int[] s = new int[]{0, 0, -1, 1, -1, 1, 0, 0};
    CSIColor[] colors = new CSIColor[]{
        new CSIColor(80, 0, 0),
        new CSIColor(80, 20, 0),
        new CSIColor(80, 0, 20),
        new CSIColor(80, 20, 20),
        new CSIColor(80, 40, 80),
        new CSIColor(20, 20, 80),
        new CSIColor(80, 40, 40),
        new CSIColor(200, 60, 50),
        new CSIColor(80, 250, 60),
        new CSIColor(80, 60, 60),
		new CSIColor(80,20,0),
		new CSIColor(80,0,20),
		new CSIColor(80,20,20),
		new CSIColor(80,40,80),
		new CSIColor(20,20,80),
		new CSIColor(80,40,40),
		new CSIColor(200,60,50),
		new CSIColor(80,250,60)
    };

    void a(int x, int y, char h) {
        j.print(x, y, Math.abs(a - x) + Math.abs(b - y) < 7 ? h : ' ', colors[v]);
    }

    public static void main(String[] p) {
        new LuckCSIColor();
    }

    LuckCSIColor() {
        j.cls();
        for (;;) {
            if (q == a & w == b) {
                m = new boolean[20][20];
                for (i = 0; ++i < 20;) {
                    m[r()][r()] = m[0][i] = m[19][i] = m[i][0] = m[i][19] = true;
                }
                q = r();
                w = r();
                a = r();
                b = r();
                o = r();
                p = r();
                v++;
                m[q][w] = m[o][p] = false;
            }
            if (o == a & p == b) {
                o = 40;
                r++;
            }
            d = -1;
            while (d++ < 19) {
                c = -1;
                while (c++ < 19) {
                    a(c, d, m[c][d] ? '#' : '.');
                }
            }
            j.print(2, 22, "L" + v + " E" + e + " R" + r + "  ", 7);
            j.print(a, b, "@", 12);
            a(q, w, '>');
            a(o, p, '%');
            a(k, l, '&');
            j.refresh();
            i = j.inkey().code;
            d = s[i % 4];
            c = s[4 + i % 4];
            if (a + d == k & b + c == l) {
                if (r() > 8) {
                    k = r();
                    l = r();
                }
            } else if (!m[a + d][b + c]) {
                a += d;
                b += c;
            }
            f = Integer.signum(a - k);
            h = Integer.signum(b - l);
            if (k + f == a & l + h == b) {
                e -= 5;
            } else if (e % (5 - (int) (v / 4.5d)) == 0 & !m[k + f][l + h]) {
                k += f;
                l += h;
            }
            if (--e < 0 | v > 20) {
                break;
            }
        }
        j.print(2, 23, r > 9 & v > 20 ? "WON" : "DIE", 4);
        j.refresh();
        j.waitKey(40);
    }
    ConsoleSystemInterface j = new WSwingConsoleInterface("LUCK - libjcsi Testing Grounds", true);
}
