package com.example.preetam.tilestap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;



/**
 * Created by Preetam on 31-08-2015.
 */
public class FallOb extends GameObjects{
    private Bitmap spritesheet;
    private boolean playing;
    int k,l=4,row;
    private long startTime;

    Animate animate = new Animate();
    Movement movement = new Movement();


    public FallOb(Bitmap res,int w,int h, int numFrames)
    {

        height=h;
        width=w;
        spritesheet = res;

        Bitmap[] image = new Bitmap[numFrames];

        for(int i=0; i < image.length; i++)
        {
            if(i<5)
            {
                image[i] = Bitmap.createBitmap(spritesheet,k*width,0,width,height);
                k++;
            }
            else
            {
            image[i] = Bitmap.createBitmap(spritesheet,l*width,0,width,height);
            l--;
            }

        }

        animate.setFrames(image);
        animate.setDelay(10);
    }


       public void update()
    {
        animate.update();
        movement.move();
    }

    public void draw(Canvas canvas)
    {
        super.x=movement.xpos();
        super.y=movement.ypos();
        super.xa=movement.xa();
        super.ya=movement.ya();
        canvas.drawBitmap(animate.getImage(), x, y, null);
    }

    public boolean getPlaying(){return playing;}

    public void setPlaying(boolean b){playing = b;}

}
