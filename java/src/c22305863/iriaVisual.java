package c22305863;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import ie.tudublin.*;

public class iriaVisual extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0; // vertical position
    float ySpeed = 1;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        size(1024, 1000, P3D);
        // fullScreen(P3D, SPAN);
        // size(1024, 1000);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile("song.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        background(0);
        switch (mode) {
            case 1:
                colorMode(HSB, 360, 100, 100); // mode HSB
                background(199, 100, 100);
                // Water coconut effect
                noStroke();
                fill(27, 100, 29,100); 
                float coconutSize = 100;
                ellipse(cx, y, coconutSize, coconutSize);

                // smaller circle light reflection in coconut
                // Calculate the position of the small circle
                float smallCircleX = cx + coconutSize / 4; // Adjust X position to be in the corner
                float smallCircleY = y - coconutSize / 4; // Adjust Y position to be in the corner
                float smallcoconut = 20; // Size of the small circle
                noStroke();
                fill(52, 100, 100,100);
                ellipse(smallCircleX, smallCircleY, smallcoconut, smallcoconut);

                // update coconut's vertical position
                y += ySpeed;
                if (y > height) {
                    y = 0; // reset the coconuts position when it reaches the bottom
                }
                break;

            default:
                break;
        }

    }
}
