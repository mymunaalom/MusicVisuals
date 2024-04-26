package ie.tudublin;

import c21404706.wissamVisual;
import c22305863.iriaVisual;
import c22368271.moonVisual;

import processing.core.PApplet;

public class Main extends PApplet {
    iriaVisual ip;
    wissamVisual wh;
    moonVisual mv;

    int mode = 0;

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        ip = new iriaVisual();
        wh = new wissamVisual();
        mv = new moonVisual();
    }

    public void draw() {
        switch (mode) {
            case 1:
                ip.setup();
                ip.draw();
                break;
            case 2:
                wh.setup();
                wh.draw();
                break;
            case 3:
                mv.setup();
                mv.draw();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyPressed() {
        switch (key) {
            case '1':
            case '2':
            case '3':
                mode = Integer.parseInt(String.valueOf(key));
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        String[] a = { "ie.tudublin.Main" };
        PApplet.runSketch(a, new Main());
    }
}
