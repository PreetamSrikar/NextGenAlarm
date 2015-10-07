package com.example.preetam.tilestap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by Preetam on 29-07-2015.
 */
public class GameThread extends Thread
{
    int FPS=30;
    private double avgFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private static Canvas canvas;



    public GameThread(SurfaceHolder surfaceHolder, GamePanel gamePanel)
    {
    this.surfaceHolder=surfaceHolder;
    this.gamePanel=gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long waitTime;
        long milliS;
        long frameCount=0;
        long totalTime=0;
        long targetTime=1000/FPS;
        double check = 25;
        while(running)
        {
            startTime=System.nanoTime();
            canvas=null;

            //try locking and editing the pixels
            try
            {
                canvas=this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){}

            finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e1){e1.printStackTrace();}
                }
            }
            milliS=(System.nanoTime()-startTime)/1000000;
            waitTime=targetTime-milliS;

            try
            {
                this.sleep(waitTime);
            }catch (Exception e){}

            totalTime+=System.nanoTime()-startTime;
            frameCount++;
            if(frameCount==FPS)
            {
                avgFPS=1000/((totalTime/frameCount)/1000000);
                frameCount=0;
                totalTime=0;
                System.out.println(avgFPS);
                gamePanel.fps=(int)avgFPS;

            }


        }
    }
    public void setRunning(boolean b)
    {
        running=b;
    }
}
