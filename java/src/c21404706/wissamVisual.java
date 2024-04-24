
package c21404706;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.*;
import processing.core.PApplet;
import processing.core.PImage;
import ie.tudublin.*;
import processing.core.*;
import java.util.ArrayList;

public class wissamVisual extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float[] lerpedBuffer;
    float y = 0;
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
        //size(1024, 1000, P3D);
        size(1024, 600, P3D);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    float off = 0;

    // Function to draw the Squidward meme animation
    public void squidwardMeme() {
        float[] tentacleX = {200, 250, 300};
        float[] tentacleY = {200, 250, 200};

        for (int i = 0; i < tentacleX.length; i++) {
            tentacleX[i] += off;
            tentacleY[i] -= off;
            float endX = tentacleX[i] + 50;
            float endY = tentacleY[i] + 50;

            float hue = map(i, 0, tentacleX.length, 0, 256);
            stroke(hue, 255, 255);
            line(tentacleX[i], tentacleY[i], endX, endY);
        }
    } 
    public void OutlineVis() {

        for (int i = 0; i < ab.size(); i++) {
            float cx = width / 2;
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            float hue = map(i, 0, ab.size(), 0, 256);
            float s = lerpedBuffer[i] * cx * 2;

            // from left side to right
            stroke(hue, 255, 255);
            line(0, i, s, i);
            // from right side to left
            line(width, i, width - s, i);
            // from top to bottom
            line(i, 0, i, s);
            // from bottom to top
            line(i, height, i, height - s);
        }
        
    }

    // Function to draw two big tentacles moving up and down
    public void bigTentacles() {
        float x1 = width / 4;
        float y1 = height / 2 - 50 * sin(radians(frameCount * 0.05f)); // Convert to radians
        float x2 = 3 * width / 4;
        float y2 = height / 2 - 50 * cos(radians(frameCount * 0.05f)); // Convert to radians

        float z = 0;

        float hue = (frameCount / 2) % 256;
        stroke(200, 255, 255); // Light blue color
        beginShape();
        vertex(x1, height, z);
        vertex(x1, y1, z);
        endShape();
        
        beginShape();
        vertex(x2, height, z);
        vertex(x2, y2, z);
        endShape();
    }

    public void draw() {
        background(180);
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

    }

}