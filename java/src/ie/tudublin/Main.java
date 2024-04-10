package ie.tudublin;

import c22305863.iriasVisual;
import c22305863.iriasvis2;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new iriasvis2());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}