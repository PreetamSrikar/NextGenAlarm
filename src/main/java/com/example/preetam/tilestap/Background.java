package com.example.preetam.tilestap;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Preetam on 31-08-2015.
 */
public class Background {
    private Bitmap bg,scaledbg;

    public Background(Bitmap res){
        bg= res;
    }

    public void draw(Canvas canvas,int width,int height){
        scaledbg=Bitmap.createScaledBitmap(bg,width,height,false);
        canvas.drawBitmap(scaledbg,0,0,null);

    }
}
