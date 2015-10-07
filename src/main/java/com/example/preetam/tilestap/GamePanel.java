package com.example.preetam.tilestap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;


/**
 * Created by Preetam on 29-07-2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    private GameThread thread;
    private Background bg;
    private ArrayList<FallOb> fallob;
    private ArrayList<Explosion> explosion;
    private long startTime,ellapsedTime;
    private int mHeight,mWidth,x,y;
    public int tapcount = 10;
    public int fps;
    Rect rect = new Rect();

    DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();


    public GamePanel(Context context)
    {
        super(context);
    //add callback to the surfaceholder to interrupt events
        getHolder().addCallback(this);


    //make GamePanel focusable so it can handle events
        setFocusable(true);
        mHeight = metrics.heightPixels;
        mWidth = metrics.widthPixels;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bg= new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bgg));
        fallob = new ArrayList<>();
        explosion = new ArrayList<>();
        startTime = System.nanoTime();

        //instatiate the thread
        thread = new GameThread(getHolder(),this);
        //we can safely start gameloop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while (retry && (counter<1000)) {
            try {
                counter++;
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread=null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                x = (int)event.getX();
                y = (int)event.getY();
                return true;
            case MotionEvent.ACTION_UP:
                x = 0;
                y = 0;
                break;
        }
        return super.onTouchEvent(event);
    }

    private boolean collision(GameObjects a)
    {
        if (x>0 && y>0)
        {
            rect = new Rect(x,y,x+1,y+1);
            if (Rect.intersects(a.rectangle(), rect))
            {
                explosion.add(new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.bubbleburst), 188, 188, a.getX(), a.getY(), a.xa(), a.ya(), 15));
                return true;
            }
        }
        return false;
    }
    public void update()
    {
        //explosion.update();
        //create object based on timer
        ellapsedTime = (System.nanoTime() - startTime)/1000000;
        if(ellapsedTime>2500)
        {
            fallob.add(new FallOb(BitmapFactory.decodeResource(getResources(), R.drawable.bubbleburst), 188, 188, 10));
            //reset timer
            startTime = System.nanoTime();
        }

        for(int i=0;i<fallob.size();i++)
        {
            fallob.get(i).update();

            if(collision(fallob.get(i)))
            {
                fallob.remove(i);
                System.out.println("removed" + i);
                tapcount--;
            }
            //if object moves off screen
            if(fallob.get(i).getY()>mHeight)
            {
                fallob.remove(i);
                System.out.println("removed" + i);
                tapcount=10;
            }
        }

        for(int j=0;j<explosion.size();j++)
        {
            explosion.get(j).update();
            if(explosion.get(j).delete) {
                explosion.remove(j);
                System.out.println("deleted explosion"+j);
            }
        }


    }

    @Override
    public void draw(Canvas canvas) {

        if(canvas!=null){
            final int savedstate = canvas.save();
            bg.draw(canvas,mWidth,mHeight);
            for(FallOb m:fallob)
            {
            m.draw(canvas);
            }
            for(Explosion e:explosion)
            {
            e.draw(canvas);
            }
            drawText(canvas);
            canvas.restoreToCount(savedstate);
        }

    }

    public void drawText(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText(String.valueOf(fps), 100, 100, paint);
        canvas.drawText(String.valueOf(tapcount),mWidth-100,100,paint);
    }
}
