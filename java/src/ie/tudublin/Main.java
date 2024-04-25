
package ie.tudublin;

import c22305863.iriaVisual;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new OurVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}