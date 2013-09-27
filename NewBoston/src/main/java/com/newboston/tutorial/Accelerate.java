package com.newboston.tutorial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Chris on 27.09.13.
 */
public class Accelerate extends Activity implements SensorEventListener {

    float x, y, sensorX, sensorY;
    Bitmap ball;
    SensorManager sm;
    MyBringBackSurface ourSurfaceView;

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            boolean registered = sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        x = y = sensorY = sensorX = 0;

        ourSurfaceView = new MyBringBackSurface(this);
        ourSurfaceView.resume();
        setContentView(ourSurfaceView);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
            startThread();
        }

        public void pause() {
            isRunning = false;
            while (true) {
                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        public void resume() {
            isRunning = true;
            startThread();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(2, 20, 150);
                canvas.drawBitmap(ball, (canvas.getWidth() / 2 + sensorX * 20), (canvas.getHeight() / 2 + sensorY * 20), null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        private void startThread() {
            ourThread = new Thread(this);
            ourThread.start();
        }
    }
}
