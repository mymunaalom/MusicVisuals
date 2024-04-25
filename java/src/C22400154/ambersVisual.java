package C22400154;

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
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PConstants;


public class ambersVisual extends PApplet 
{
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
    float outsideRadius = 150;
    float insideRadius = 100;

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

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // am = ai.mix;
        ap = minim.loadFile("Squidward's Tiki Land     Psy-Trance Remix.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(RGB, 255);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];

    }

    float off = 0;

    public void draw() 
    {
        float average = 0;
        float sum = 0;
        off += 1; // Assuming off is declared and initialized elsewhere
    
        for (int i = 0; i < ab.size(); i++) 
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
    
        background(152, 193, 217);
    
        drawTree(width/2, height/2);
    }

    void drawTree(float x, float y) {
        //trunk
        fill(139, 69, 19);
        rect(x - 10, y, 20, 150);
        
        //leaves 
        fill(34, 139, 34);
        
        //top-left
        beginShape();
        vertex(x, y);
        bezierVertex(x - 50, y - 30, x - 70, y + 20, x - 50, y + 60);
        bezierVertex(x - 20, y + 20, x - 40, y - 10, x, y);
        endShape(CLOSE);
        
        //left
        beginShape();
        vertex(x - 10, y);
        bezierVertex(x - 80, y - 30, x - 90, y + 10, x - 70, y + 50);
        bezierVertex(x - 30, y + 20, x - 40, y - 10, x - 10, y);
        endShape(CLOSE);
        
        //right 
        beginShape();
        vertex(x + 10, y);
        bezierVertex(x + 80, y - 30, x + 90, y + 10, x + 70, y + 50);
        bezierVertex(x + 30, y + 20, x + 40, y - 10, x + 10, y);
        endShape(CLOSE);

        //top-right
        beginShape();
        vertex(x + 10, y);
        bezierVertex(x + 50, y - 60, x + 80, y - 20, x + 50, y + 20);
        bezierVertex(x + 20, y - 20, x + 30, y - 40, x + 10, y);
        endShape(CLOSE);
    }
      
} 

