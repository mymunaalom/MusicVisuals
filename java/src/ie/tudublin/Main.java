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

<<<<<<< HEAD
import c22368271.MusicVisualizer;
import processing.core.PApplet;
=======
import c22305863.iriaVisual;
>>>>>>> 9e15907fa0ffc83b53a1831b2167d54560c71ab9

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
<<<<<<< HEAD
        PApplet.runSketch(a, new MusicVisualizer());
=======
        processing.core.PApplet.runSketch(a, new OurVisual());
>>>>>>> 9e15907fa0ffc83b53a1831b2167d54560c71ab9
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
}
