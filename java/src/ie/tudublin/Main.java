

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
