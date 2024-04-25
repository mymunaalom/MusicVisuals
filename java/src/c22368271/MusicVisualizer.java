package c22368271;

import processing.core.PApplet;
import processing.core.PImage;
import ddf.minim.*;
import ddf.minim.analysis.*;

public class MusicVisualizer extends PApplet {

    Minim minim;
    AudioPlayer player;
    FFT fft;

    PImage backgroundImage; // Declare a PImage variable to hold the background image
    PImage[] flowerImages; // Array to hold flower images

    float[] bands;
    int numFlowers = 5; // Number of flowers
    float[] flowerX, flowerY; // Arrays to store flower positions
    float[] flowerSize; // Array to store flower sizes
    float maxFlowerSize = 100; // Maximum size of the flower
    float scaleMultiplier = 4; // Scale multiplier for flower size

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        minim = new Minim(this);
        player = minim.loadFile("java/data/Squidward's Tiki Land     Psy-Trance Remix.mp3");
        fft = new FFT(player.bufferSize(), player.sampleRate());
        player.play();

        bands = new float[fft.specSize()];

        // Load the background image
        backgroundImage = loadImage("spongeBG.png");

        // Load flower images
        flowerImages = new PImage[numFlowers];
        for (int i = 0; i < numFlowers; i++) {
            flowerImages[i] = loadImage("spongeFlower.png");
        }

        // Initialize arrays to store flower positions and sizes
        flowerX = new float[numFlowers];
        flowerY = new float[numFlowers];
        flowerSize = new float[numFlowers];

        // Randomly position the flowers on the screen
        for (int i = 0; i < numFlowers; i++) {
            flowerX[i] = random(width);
            flowerY[i] = random(height);
        }
    }

    public void draw() {
        // Draw the background image
        image(backgroundImage, 0, 0, width, height);

        fft.forward(player.mix);

        // Calculate average amplitude across all bands
        float avgAmplitude = 0;
        for (int i = 0; i < fft.specSize(); i++) {
            avgAmplitude += fft.getBand(i);
        }
        avgAmplitude /= fft.specSize();

        // Update flower sizes based on average amplitude
        for (int i = 0; i < numFlowers; i++) {
            float scaledSize = map(avgAmplitude, 0, 1, 0, maxFlowerSize * scaleMultiplier); // Scale flower size based on average amplitude
            flowerSize[i] = min(scaledSize, maxFlowerSize); // Clamp the flower size to the maximum value
        }

        // Draw flowers
        for (int i = 0; i < numFlowers; i++) {
            drawFlower(flowerImages[i], flowerX[i], flowerY[i], flowerSize[i]);
        }
    }

    // Function to draw a flower image at the specified position and size
    void drawFlower(PImage img, float x, float y, float size) {
        image(img, x, y, size, size);
    }
}

