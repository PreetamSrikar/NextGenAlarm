package com.example.preetam.tilestap;

import android.graphics.Rect;

/**
 * Created by Preetam on 31-08-2015.
 */
public abstract class GameObjects {
    protected int x;
    protected int y;
    protected int ya;
    protected int xa;
    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public int ya(){return ya;}

    public int xa(){return xa;}

    public Rect rectangle()
    {
        return new Rect(x,y,x+width,y+height);
    }

}
