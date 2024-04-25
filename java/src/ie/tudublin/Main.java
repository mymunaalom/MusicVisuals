// package ie.tudublin;

// import c22305863.iriaVisual;
// import c21404706.wissamVisual;
// import c22368271.MusicVisualizer;
// import example.CubeVisual;
// import example.MyVisual;
// import example.RotatingAudioBands;

// public class Main {

//     public void startUI() {
//         String[] a = { "MAIN" };
//         processing.core.PApplet.runSketch(a, new iriaVisual());
//     }

//     public static void main(String[] args) {
//         Main main = new Main();
//         main.startUI();
//     }
// }

package ie.tudublin;

import c22368271.MusicVisualizer;
import processing.core.PApplet;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        PApplet.runSketch(a, new MusicVisualizer());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
