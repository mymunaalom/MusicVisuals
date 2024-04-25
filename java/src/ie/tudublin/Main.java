package ie.tudublin;

import c22305863.iriaVisual;
import C22400154.ambersVisual;
import c21404706.wissamVisual;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new ambersVisual());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}