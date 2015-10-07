package com.example.preetam.tilestap;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Preetam on 07-10-2015.
 */
public class Explosion {
    private Bitmap spritesheet;
    private int height,width,row,x,y,xa,ya;
    Animate animate = new Animate();
    public boolean delete;

    public Explosion(Bitmap res,int w,int h,int xpos,int ypos, int ax,int ay,int numFrames)
    {

        height=h;
        width=w;
        spritesheet = res;
        x=xpos;
        y=ypos;
        xa=ax;
        ya=ay;

        Bitmap[] image = new Bitmap[numFrames];

        for(int i=0; i < image.length; i++)
        {

            if(i%5 == 0 && i>0)
                row++;

            image[i] = Bitmap.createBitmap(spritesheet,(i-row*5)*width,row*height,width,height);

        }

        animate.setFrames(image);
        animate.setDelay(10);
    }

    public void update()
    {
        animate.update();
        delete = animate.playedOnce();
        x = x+xa;
        y = y+ya;
    }
    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animate.getImage(), x, y, null);
    }

}
