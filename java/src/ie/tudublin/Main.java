
package ie.tudublin;

import c21404706.wissamVisual;
import c22305863.iriaVisual;

public class Main {
    iriaVisual ip;
    wissamVisual wv;

    int mode = 0;

    public void startUI() {
        String[] a = { "MAIN" };

        ip = new iriaVisual(); //iriaVisual class
        wv = new wissamVisual(); //wissamVisual class
        processing.core.PApplet.runSketch(a, ip);
    }

    public void keyPressed() {
        char key = ip.key;
        switch (key) {
            case '1':
               
                ip.setup();
                mode = 1;
                break;
            case '2':
                wv.setup();
             
                break;
            case ' ':
                
                break;
            default:
                break;
        }

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}

