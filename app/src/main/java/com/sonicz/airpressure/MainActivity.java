package com.sonicz.airpressure;


import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView text;
    android.hardware.SensorManager sm;
    Sensor pre;
    double pressure = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textView);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        pre = sm.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sm.registerListener( this, pre, SensorManager.SENSOR_DELAY_UI);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        pressure = event.values[0];
        text.setText("Current pressure:\n" + String.format("%.1f", pressure) + " hPa\n");
        text.append(String.format("%.3f", hPa2inHg(pressure)) + " inHg");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public double hPa2inHg(double hPa) {
        return hPa * 0.02952998751;
    }

}
