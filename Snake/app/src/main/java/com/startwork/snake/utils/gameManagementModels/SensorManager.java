package com.startwork.snake.utils.gameManagementModels;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class SensorManager implements SensorEventListener {

    public static SensorManager instance = null;
    public static Integer rand = 929321;
    public double x = 0,y = 0,z = 0;

    public static SensorManager getInstance(Activity main) {
        if(instance != null)
            return instance;
        synchronized (rand) {
            if(instance == null) {
                instance = new SensorManager(main);
            }
        }
        return instance;
    }

    public static SensorManager getInstance() {
        return instance;
    }

    public android.hardware.SensorManager sensorManager;
    public Sensor accelerometer;

    public SensorManager(Activity main) {
        sensorManager = (android.hardware.SensorManager) main.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer , android.hardware.SensorManager.SENSOR_DELAY_GAME);
    }

    public void onPause() {
        sensorManager.unregisterListener(this);
    }

    public void onResume() {
        sensorManager.registerListener(this, accelerometer, android.hardware.SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
        }
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}
