<<<<<<< HEAD
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
=======
package ie.tudublin;

import c22305863.iriaVisual;
import example.CubeVisual;
import example.RotatingAudioBands;
import ie.tudublin.Allfiles;

public class Main {

    public void startUI() {
        String[] a = { "MAIN" };
        processing.core.PApplet.runSketch(a, new Allfiles());
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.startUI();
    }
>>>>>>> 8b3be4fbce12a731e0d2402b35d1105a3f8b5226
}