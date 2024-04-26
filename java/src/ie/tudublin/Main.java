<<<<<<< HEAD


package ie.tudublin;

import c21404706.wissamVisual;
import c22305863.iriaVisual;
import c22368271.moonVisual;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new wissamVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
=======
package ie.tudublin;

import c21404706.wissamVisual;
import c22305863.iriaVisual;

public class Main {
    iriaVisual ip;

    int mode = 0;

    public void startUI() {
        String[] a = { "MAIN" };

        ip = new iriaVisual(); //iriaVisual class
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
>>>>>>> 4394772de7d329c8e90aee7f379cd80f97a66b27
