package com.example.preetam.tilestap;


import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Random;

/**
 * Created by Preetam on 04-10-2015.
 */
public class Movement extends GameObjects{
    private int mHeight,mWidth,x,y,xa,ya;
    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
    Random rand = new Random();

    public Movement()
    {
        mHeight = metrics.heightPixels;
        mWidth = metrics.widthPixels-160;
        x=rand.nextInt(mWidth-300);
        y=-200;
        ya=rand.nextInt(10)+10;
        xa=rand.nextInt(40)-20;
        //System.out.println("xa" +xa);
    }

    public void move()
    {
        y+=ya;
        x+=xa;

        if(x<-45)
        {
            xa=(-xa);
            //System.out.println("xa" +xa);
        }
        else if(x>mWidth)
        {
            xa=(-xa);
           // System.out.println("xa" +xa);
        }

    }

    public int xpos() {return x;}
    public int ypos() {return y;}
    public int ya(){return ya;}
    public int xa(){return xa;}
}
