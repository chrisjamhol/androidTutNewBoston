package com.newboston.tutorial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by Chris on 18.09.13.
 */
public class GFXSurface extends Activity implements View.OnTouchListener{

    MyBringBackSurface ourSurfaceView;
    Bitmap test, plus;
    float x,y,sX,sY,fX,fY,dX,dY,aniX,aniY,scaledX,scaledY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.setOnTouchListener(this);
        x = y = sX = sY = fX = fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;
        test = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
        plus = BitmapFactory.decodeResource(getResources(),R.drawable.plus);
        setContentView(ourSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = event.getX();
        y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
                break;
            case MotionEvent.ACTION_UP:
                x = y = 0;
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                dY = fY - sY;
                scaledX = dX / 30;
                scaledY = dY / 30;
                break;
        }
        return true;
    }

    /**
     * Created by Chris on 18.09.13.
     */
    public class MyBringBackSurface extends SurfaceView implements Runnable{

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
            startThread();
        }

        public void pause(){
            isRunning = false;
            while (true){
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume(){
            isRunning = true;
            startThread();
        }

        @Override
        public void run() {
            while (isRunning){
                if(!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2,20,150);
                if(x != 0 && y != 0){
                    canvas.drawBitmap(test, (x-(test.getWidth()/2)), (y-(test.getHeight()/2)), null);
                }

                if(sX != 0 && sY != 0){
                    canvas.drawBitmap(plus, (sX-(plus.getWidth()/2)), (sY-(plus.getHeight()/2)), null);
                }

                if(fX != 0 && fY != 0){
                    canvas.drawBitmap(test, (fX-(test.getWidth()/2))-aniX, (fY-(test.getHeight()/2))-aniY, null);
                    canvas.drawBitmap(plus, (fX-(plus.getWidth()/2)), (fY-(plus.getHeight()/2)), null);
                }
                aniX += scaledX;
                aniY += scaledY;
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        private void startThread(){
            ourThread = new Thread(this);
            ourThread.start();
        }
    }
}
