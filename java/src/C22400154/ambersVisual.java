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
        off += 1;
    
        for (int i = 0; i < ab.size(); i++) 
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();
        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
    
        float averageAmplitude = getAverageAmplitude(ab);

        float treeX1 = width / 4;
        float treeX2 = width * 3 / 4;
  
        float treeY1 = map(averageAmplitude, 0, 1, height * 0.7f, height * 0.9f);
        float treeY2 = map(averageAmplitude, 0, 1, height * 0.7f, height * 0.9f);

        background(152, 193, 217);
    
        //draw leafs at updated positions
        drawTree(treeX1, treeY1, 1.5f);
        drawTree(treeX2, treeY2, 1.5f);
    }

    float getAverageAmplitude(AudioBuffer buffer) {
        float sum = 0;
        for (int i = 0; i < buffer.size(); i++) {
          sum += abs(buffer.get(i));
        }
        return sum / buffer.size();
    }

    void drawTree(float x, float y, float scale) 
    {
        //trunk
        fill(55, 40, 28);
        rect(x - 10 * scale, y, 20 * scale, 150 * scale);
    
        //leaves 
        fill(19, 128, 99);
        
        //topleft
        beginShape();
        vertex(x + 10 * scale, y);
        bezierVertex(x - 50 * scale, y - 60 * scale, x - 70 * scale, y + 20 * scale, x - 50 * scale, y + 60 * scale);
        bezierVertex(x - 20 * scale, y + 20 * scale, x - 40 * scale, y - 10 * scale, x, y);
        endShape(CLOSE);
                
        //left
        beginShape();
        vertex(x - 10 * scale, y);
        bezierVertex(x - 80 * scale, y - 30 * scale, x - 90 * scale, y + 10 * scale, x - 70 * scale, y + 50 * scale);
        bezierVertex(x - 30 * scale, y + 20 * scale, x - 40 * scale, y - 10 * scale, x - 10 * scale, y);
        endShape(CLOSE);
        
        //right
        beginShape();
        vertex(x + 10 * scale, y);
        bezierVertex(x + 80 * scale, y - 30 * scale, x + 90 * scale, y + 10 * scale, x + 70 * scale, y + 50 * scale);
        bezierVertex(x + 30 * scale, y + 20 * scale, x + 40 * scale, y - 10 * scale, x + 10 * scale, y);
        endShape(CLOSE);

        //topright
        beginShape();
        vertex(x + 10 * scale, y);
        bezierVertex(x + 50 * scale, y - 60 * scale, x + 80 * scale, y - 20 * scale, x + 50 * scale, y + 20 * scale);
        bezierVertex(x + 20 * scale, y - 20 * scale, x + 30 * scale, y - 40 * scale, x + 10 * scale, y);
        endShape(CLOSE);
    }

} 