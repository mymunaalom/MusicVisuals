package c22305863;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;
import ie.tudublin.*;
import processing.core.*;
import java.util.ArrayList;

public class iriaVisual extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;// default mode for switch statement

    float[] lerpedBuffer;
    float y = 0; // vertical position
    float ySpeed = 2;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    ArrayList<Coconut> coconuts = new ArrayList<Coconut>(); // array to store coconuts

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
        switch (mode) {
            case 0:
                draw();
                break;

            default:
                break;
        }
    }

    public void settings() {
        size(1024, 600, P3D);
        // fullScreen(P3D, SPAN);
        // size(1024,700);
    }

    PImage tikiface;

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        tikiface = loadImage("tiki_face2.png");
        textureMode(NORMAL);

        lerpedBuffer = new float[width];
    }

    float off = 0;

    public void reactToMouseMovement() {
        // make coconuts appear on screen when clicked by mouse
        if (mousePressed) {
            coconuts.add(new Coconut(mouseX, mouseY));// create new coconuts + add to list
        }
    }

    class Coconut {
        // Position of coconut
        float x, y; // Position of coconut
        int timesReachedEnd;

        Coconut(float x, float y) {
            this.x = x;
            this.y = y;
        }

        // update coconut position make it appear on screen
        void update() {
            // Draw coconut
            float coconutSize = 100;
            float coconutLeftX = x - coconutSize / 2;
            stroke(360, 100, 36);
            fill(360, 100, 36);
            ellipse(coconutLeftX, y, coconutSize, coconutSize);

            // Draw small circle on the right top corner of coconut
            float smallCircleSize = 20;
            float smallCircleOffsetX = coconutSize / 2 - 30;
            float smallCircleOffsetY = -coconutSize / 2 + 30;
            stroke(67, 37, 100);
            fill(67, 37, 100);
            ellipse(coconutLeftX + smallCircleOffsetX, y + smallCircleOffsetY, smallCircleSize, smallCircleSize);

            // makes coconut falls dow
            y += ySpeed;
            if (y > height) {
                y = 0; // coconut goes back up when ir reaches the bottom
                timesReachedEnd++;
            }
            if (timesReachedEnd >= 2) {
                coconuts.remove(this); // remove coconut from the list if reached the end twice
            }
        }

    }

    public void draw() {
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // calculate sum and average of the samples
        // lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        background(0);

        colorMode(HSB, 360, 100, 100); // mode HSB
        background(209, 58, 100);

        reactToMouseMovement();
        for (int i = coconuts.size() - 1; i >= 0; i--) {
            Coconut c = coconuts.get(i); // get the coconut from the list
            c.update(); // draw the coconut
        }

        for (int i = 0; i < ab.size(); i++) {

            float hue = map(i, 0, ab.size(), 0, 121);
            float s = lerpedBuffer[i] * cx;
            stroke(hue, 255, 300);
            // noFill();
            // circle(512, 300, average * cy * 5);
            // line(cy * s, i * s, s, ab.get(i) + s * 100);

            // cool line that moves with the music
            // cos and sin functions to make line move in a circular way
            // two_pi is a full circle
            line(cx, cy + smoothedAmplitude, cx + cos(TWO_PI / ab.size() * i) * s * 2,
                    cy + sin(TWO_PI / ab.size() * i) * s * 2);

        }
        for (int i = 0; i < ab.size(); i++) {
            float hue = map(i, 0, ab.size(), 290, 51);
            float s = lerpedBuffer[i] * cx;
            stroke(hue, 100, 100);
            // circle to be infront of the cool line
            circle(cx, cy, average * i / 8);
        }

        for (int i = 0; i < ab.size(); i++) {
            float hue = map(i, 0, ab.size(), 41, 70);
            float s = lerpedBuffer[i] * cx;
            stroke(hue, 100, 100);
            // idk yet

        }

    }
}
