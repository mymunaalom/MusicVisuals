package ie.tudublin;

import c22305863.iriaVisual;
import c22305863.IriaVisual2;
import example.CubeVisual;
import example.MyVisual;
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