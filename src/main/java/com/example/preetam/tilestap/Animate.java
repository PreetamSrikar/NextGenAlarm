package com.example.preetam.tilestap;

import android.graphics.Bitmap;

/**
 * Created by Preetam on 03-10-2015.
 */
public class Animate {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    public boolean playedOnce = false;

    public void setFrames(Bitmap[] frames)
    {
        this.frames=frames;
        currentFrame=0;
        startTime=System.nanoTime();

    }

    public void setDelay(long d){delay=d;}

    public void setFrame(int i){currentFrame=i;}

    public void update()
    {
        long timeElapsed = (System.nanoTime() - startTime)/1000000;

        if(timeElapsed>delay)
        {

            currentFrame++;

            startTime=System.nanoTime();

        }
        if(currentFrame == frames.length)
        {
            currentFrame = 0;
            playedOnce = true;
        }
    }

    public Bitmap getImage()
    {
       return frames[currentFrame];

    }

    public int getFrame(){return currentFrame;}

    public boolean playedOnce(){return playedOnce;}
}
