package ie.tudublin;

import c22305863.iriaVisual;
import example.CubeVisual;
import example.RotatingAudioBands;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new iriaVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}