package ie.tudublin;

import c22305863.iriaVisual;
<<<<<<< HEAD
=======
import c21404706.wissamVisual;
import c22305863.IriaVisual2;
>>>>>>> 249e807053a4ce8badcd13d4583b23754f30830e
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
<<<<<<< HEAD
        processing.core.PApplet.runSketch(a, new MyVisual2());
=======
        processing.core.PApplet.runSketch(a, new wissamVisual());
>>>>>>> 249e807053a4ce8badcd13d4583b23754f30830e
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}