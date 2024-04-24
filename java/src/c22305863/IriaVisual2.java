package c22305863;

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

public class IriaVisual2 extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0; // default mode for switch statement

    ArrayList<Coconut> coconuts = new ArrayList<Coconut>(); // array to store coconuts
    PImage tikiface;
    PVector tikiPos;

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
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        tikiface = loadImage("titki_face.png");
        tikiPos = new PVector(width / 2, height / 12);
    }

    public void reactToMouseMovement() {
        // make coconuts appear on screen when clicked by mouse
        if (mousePressed) {
            coconuts.add(new Coconut(new PVector(mouseX, mouseY)));// create new coconuts + add to list
        }
    }

    public void tiki_face() {
        int numTikis = 5;
        float tikiSpacing = width / (numTikis + 1); // calculate spacing between tikis

        for (int i = 1; i <= numTikis; i++) {
            float tikiX = i * tikiSpacing - 100; // Calculate X position

            // move tiki with music
            float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, 50);
            PVector tikiPos = new PVector(tikiX, height / 12 + amplitude);
            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);

            // draw tiki face at the updated position
            image(tikiface, tikiPos.x, tikiPos.y);
        }
    }

    class Coconut {
        // Position of coconut
        PVector position;
        int timesReachedEnd;

        Coconut(PVector position) {
            this.position = position;
        }

        // update coconut position make it appear on screen
        void update() {
            // Draw coconut
            float coconutSize = 100;

            pushMatrix(); // Save the current transformation matrix
            translate(position.x, position.y); // Translate to the coconut's position
            noStroke();
            fill(360, 100, 36);
            ellipse(0, 0, coconutSize, coconutSize); // Draw coconut at the translated position
            popMatrix(); // Restore the previous transformation matrix

            // makes coconut falls down
            position.y += 2; // You can adjust the falling speed here
            if (position.y > height) {
                position.y = 0; // coconut goes back up when it reaches the bottom
                timesReachedEnd++;
            }
            if (timesReachedEnd >= 2) {
                coconuts.remove(this); // remove coconut from the list if reached the end twice
            }
        }
    }

    public void draw() {
        background(0);

        reactToMouseMovement();
        for (int i = coconuts.size() - 1; i >= 0; i--) {
            Coconut c = coconuts.get(i); // get the coconut from the list
            c.update(); // draw the coconut
        }

        tiki_face();
    }

    public static void main(String[] args) {
        PApplet.main("IriaVisual2");
    }
}
