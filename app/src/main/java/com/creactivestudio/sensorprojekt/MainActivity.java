package com.creactivestudio.sensorprojekt;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    SensorManager gyroscopeSensorManager;
    Sensor gyroscopeSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(gyroscopeSensor==null)
        {
            Toast.makeText(this, "No Gyposcope Sensor detected", Toast.LENGTH_SHORT).show();
            finish();
        }
        else Toast.makeText(this, "You have a Gyposcope Sensor", Toast.LENGTH_SHORT).show();


        gyroscopeSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor=gyroscopeSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

    }

    private SensorEventListener gyroscopeSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            // More code goes here
            if(sensorEvent.values[2] > 0.5f) { // anticlockwise
                getWindow().getDecorView().setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            } else if(sensorEvent.values[2] < -0.5f) { // clockwise
                getWindow().getDecorView().setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeSensorManager.registerListener(gyroscopeSensorListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeSensorManager.unregisterListener(gyroscopeSensorListener);

    }
}