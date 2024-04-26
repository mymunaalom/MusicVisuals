package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import java.util.ArrayList;

public class Allfiles extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    FFT fft;
    private AudioBuffer ab;

    int mode = 0;// default mode for switch statement

    float[] lerpedBuffer;
    float y = 0; // vertical position
    float ySpeed = 2;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    ArrayList<Coconut> coconuts = new ArrayList<Coconut>(); // array to store coconuts
    PImage tikiface;
    PImage skirt;
    PVector tikiPos;
    PVector tikiPos2;
    PVector skirtPos;
    PVector skirtPos2;

    PImage bananaPic;
    PImage bg;
    ArrayList<Banana> bananas;

    
    PImage flower;
    float speedX, speedY;
    float flowerX, flowerY; // flower pos
    PVector flowerPos;

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
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        textureMode(NORMAL);

        lerpedBuffer = new float[width];

        tikiface = loadImage("tiki_face3.png");
        skirt = loadImage("skirt.png");
        tikiPos = new PVector(width / 2, height / 12);

        bananaPic = loadImage("banana_pic.png");
        bananas = new ArrayList<Banana>();

        bg = loadImage("spongeBG.png");

        int numBananas = 30;
        float bananaSpacing = width / (numBananas + 1); // calculate spacing between bananas
        for (int i = 1; i <= numBananas; i++) {
            float bananaX = i * bananaSpacing;
            bananas.add(new Banana(bananaX, 0)); // Add bananas to the list
        }
        fft = new FFT(ap.bufferSize(), ap.sampleRate());

        bg = loadImage("spongeBG.png");
        flower = loadImage("spongeFlower.png");

    }

    float off = 0;

  

        private void  reactToMouseMovement() {
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
                float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, ab.size() / 8);
                PVector tikiPos = new PVector(tikiX, height / 12 + amplitude);
                PVector tikiPos2 = new PVector(tikiX, height / 1.50f + amplitude);
    
                // skirt
                PVector skirtPos = new PVector(tikiX, height / 3.4f + amplitude);
                PVector skirtPos2 = new PVector(tikiX, height / 1.14f + amplitude);
    
                // draw tiki face at the updated position
                image(tikiface, tikiPos.x, tikiPos.y);
                image(tikiface, tikiPos2.x, tikiPos2.y);
                float hue = map(amplitude, 0, 50, 0, 360);
                fill(hue, 100, 100);
                image(skirt, skirtPos.x, skirtPos.y);
                image(skirt, skirtPos2.x, skirtPos2.y);
    
            }
    
        }
    
        class Coconut {
            // position of coconut
            PVector position;
            int timesReachedEnd;
    
            Coconut(PVector position) {
                this.position = position;
            }
    
            // update coconut position make it appear on screen
            void update() {
                // Draw coconut
                float coconutSize = 100;
    
                pushMatrix(); // save the current transformation matrix
                translate(position.x, position.y); // translate to the coconut's position
                noStroke();
                fill(360, 100, 36);
                ellipse(0, 0, coconutSize, coconutSize); // draw coconut at the translated position
    
                // Draw small circle inside the coconut
                float smallCircleSize = 20;
                float smallCircleOffsetX = coconutSize / 2 - 30;
                float smallCircleOffsetY = -coconutSize / 2 + 30;
                fill(67, 37, 100);
                ellipse(smallCircleOffsetX, smallCircleOffsetY, smallCircleSize, smallCircleSize);
    
                popMatrix(); // restore the previous transformation matrix
    
                // makes coconut falls down
                position.y += 3;
                if (position.y > height) {
                    position.y = 0; // coconut goes back up when it reaches the bottom
                    timesReachedEnd++;
                }
                if (timesReachedEnd >= 2) {
                    coconuts.remove(this); // remove coconut from the list if reached the end twice
                }
    
            }
    
        }
    
        public void displayIria() {

            background(0);
    
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
    
            colorMode(HSB, 360, 100, 100); // mode HSB
            image(bg, 0, 0,width,height);
    
            reactToMouseMovement();
            for (int i = coconuts.size() - 1; i >= 0; i--) {
                Coconut c = coconuts.get(i); // get the coconut from the list
                c.update(); // draw the coconut
            }
    
            for (int i = 0; i < ab.size(); i++) {
                float hue = map(i, 0, ab.size(), 297, 58);
                stroke(hue, 100, 100);
                noFill();
                circle(cx, cy, average * i * 4 * smoothedAmplitude);
                // line that surrounds the border of the circle
                line(cx, cy, cx + cos(TWO_PI / average * i) * 100, cy + sin(TWO_PI / average * i) * 100);
    
            }
            for (int i = 0; i < ab.size(); i++) {
    
                float hue = map(i, 0, ab.size(), 50, 0);
                float s = lerpedBuffer[i] * cx;
                stroke(hue, 100, 100);
                // cool line that moves with the music
                // cos and sin functions to make line move in a circular way
                // two_pi is a full circle
                line(cx, cy + smoothedAmplitude, cx + cos(TWO_PI / ab.size() * i) * s * 2,
                        cy + sin(TWO_PI / ab.size() * i) * s * 2);
    
            }
            for (int i = 0; i < ab.size(); i++) {
                float hue = map(i, 0, ab.size(), 290, 51);
    
                // circle to be infront of the cool line
                circle(cx, cy, average * i / 8);
            }
    
            tiki_face();
        }
    

    

    public void visiual() {

        for (int i = 0; i < ab.size(); i++) {
            float cx = width / 2;
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
            float hue = map(i, 0, ab.size(), 0, 256);
            float s = lerpedBuffer[i] * cx * 2;

            // from left side to right
            stroke(40, 255, 255);
            line(0, i, s, i);
            // from right side to left
            line(width, i, width - s, i);
            // from top to bottom
            line(i, 0, i, s);
            // from bottom to top
            line(i, height, i, height - s);
        }

        int gridSize = 40;

        for (int x = gridSize; x <= width - gridSize; x += gridSize) {
            for (int y = gridSize; y <= height - gridSize; y += gridSize) {
                noStroke();
                fill(255);
                rect(x - 1, y - 1, 3, 3);
                stroke(255, 100);
                line(x, y, width / 2, height / 2);
            }
        }

        int num = 60;
        float mx[] = new float[num];
        float my[] = new float[num];

        // Cycle through the array, using a different entry on each frame.
        // Using modulo (%) like this is faster than moving all the values over.
        int which = frameCount % num;
        mx[which] = mouseX;
        my[which] = mouseY;

        for (int i = 0; i < num; i++) {
            // which+1 is the smallest (the oldest in the array)
            int index = (which + 1 + i) % num;
            ellipse(mx[index], my[index], i, i);
        }

        int x;
        int y;
        float outsideRadius = 150;
        float insideRadius = 100;

        int numPoints = (int) map((float) mouseX, 0, (float) width, 6, 60);
        float angle = 0;
        float angleStep = 180.0f / num;

        // Ocean wave parameters
        float waveSpeed = 0.0000000000000000001f; // Speed of the waves
        float waveAmplitude = 10; // Amplitude of the waves
        float waveFrequency = 0.025f; // Frequency of the waves

        for (int z = 0; z < width; z++) {
            stroke(160, 255, 255); // HSB color (blue)
            float waveHeight = sin(z * waveFrequency + off) * waveAmplitude;
            line(z, height * 2 / 3 + waveHeight, z, height); // Drawing waves from the center of the canvas to the
                                                             // bottom
        }

        off += waveSpeed; // Incrementing the offset for the waves animation

        int numBananas = 15;
        float bananaSpacing = width / (numBananas + 1); // calculate spacing between tikis

        for (int i = 1; i <= numBananas; i++) {
            float bananaX = i * bananaSpacing - 100; // Calculate X position

            // move bananas with music
            float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, ab.size() / 8);
            float bananaY = -amplitude * 0.5f;
            float bananaSize = map(amplitude, 0, 50, 10, 50);

            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);

        }

    }

    class Banana {
        float x, y;
        int timesReachedEnd;

        Banana(float x, float y) {
            this.x = x;
            this.y = y;
            timesReachedEnd = 0;
        }

        void display() {
            float amplitude = map(sin(frameCount * 0.05f), -1, 1, 0, ab.size() / 8);
            float bananaX = x * (width / 1024.0f); // Adjust x position for different screen widths
            float bananaY = y * (height / 600.0f);
            float bananaSize = map(amplitude, 0, 50, 10, 50);

            float hue = map(amplitude, 0, 50, 0, 360);
            fill(hue, 100, 100);
            image(bananaPic, x, bananaY, bananaSize, bananaSize * 2);

            y += 2;
            if (y > height) {
                y = 0; // banana goes back up when it reaches the bottom
                timesReachedEnd++;
            }
            if (timesReachedEnd >= 2) {
                bananas.remove(this); // remove banana from the list if reached the end twice
            }
        }
    }

    public void displayWissam() {
        background(0);
        image(bg, 0, 0, width, height);
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

        for (Banana banana : bananas) {
            banana.display();
        }

        // calling outline for visiual
        visiual();

    }

    public void flowers(float amplitude) {
        int numflowers = 7;
        float angleIn = TWO_PI / numflowers;

        float flowerSize = map(amplitude, 0, 1, 10, 100);

        for (int i = 0; i < numflowers; i++) {
            float angle = i * angleIn;
            float radius = flowerSize * (i + 10);
            float flowerPosX = width / 2 + cos(angle) * radius-100;
            float flowerPosY = height / 2 + sin(angle) * radius-100;
            image(flower, flowerPosX, flowerPosY, flower.width / 6, flower.height / 6);
        }
    }
    void drawStrobeLights() {
        int numLights = 20; // Number of strobe lights
        float angleStep = TWO_PI / numLights; // Angle step between each light
        float radius = min(width, height) * 0.4f; // Radius of the circle containing the lights
        float centerX = width / 2; // X-coordinate of the center of the circle
        float centerY = height / 2; // Y-coordinate of the center of the circle
    
        for (int i = 0; i < numLights; i++) {
            // Calculate position of each strobe light around the circle
            float x = centerX + cos(angleStep * i) * radius;
            float y = centerY + sin(angleStep * i) * radius;
    
            // Calculate size of the strobe light based on the amplitude
            float lightSize = map(smoothedAmplitude, 0, 1, 10, 50);
    
            // Calculate color of the strobe light based on time
            int lightColor = color((frameCount + i * 10) % 255, 255, 255);
    
            // Set fill and stroke colors
            fill(lightColor);
            stroke(lightColor);
    
            // Draw the strobe light
            ellipse(x, y, lightSize, lightSize);
        }
    }
    public void displaymoon(){
        background(0);
        image(bg, 0, 0, width, height);

        float average = 0;
        float sum = 0;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.05f);
        }
        average = sum / (float) ab.size();

        flowers(smoothedAmplitude);

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        float squareSize = map(smoothedAmplitude, 0, 1, 10, 400);
        float squareX = width / 2 - squareSize / 2;
        float squareY = height / 2 - squareSize / 2;
        int rainbowColor = color((frameCount / 2) % 255, (frameCount / 3) % 255, (frameCount / 4) % 255);
        stroke(rainbowColor);
        fill(0);
        rect(squareX, squareY, squareSize, squareSize);

        float waveAmplitude = map(smoothedAmplitude, 0, 1, 0, squareSize / 2);
        fft.forward(ap.mix);
        for (int i = 0; i < fft.specSize(); i++) {
            float x = map(i, 0, fft.specSize(), squareX, squareX + squareSize);
            float y = squareY + squareSize / 2 + fft.getBand(i) * waveAmplitude;
            point(x, y);
        }

        drawStrobeLights();
    }
    public void draw() {
        switch (mode) {
            case 1: {
                displayWissam();
                break;
            }
            case 2: {
                displaymoon();
                break;
            }
            case 3: {
                
                
                displayIria();
                break;
            }

            default:
                displayIria();
                break;
        }

    }
}
