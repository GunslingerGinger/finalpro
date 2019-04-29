package com.example.myfirstapp;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextClock;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

public class intro extends AppCompatActivity {

    TextClock currentTime;
    TimePicker alarmTime;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        currentTime = findViewById(R.id.textClock);
        alarmTime = findViewById(R.id.timePicker);
        final Ringtone simpleRingtone = RingtoneManager.getRingtone(getApplicationContext(),
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));
        Timer checkTime = new Timer();
        checkTime.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (currentTime.getText().toString().equals(AlarmTime())) {
                    simpleRingtone.play();
                } else {
                    simpleRingtone.stop();
                }
            }
        }, 0, 2000);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hui();
            }
        });
    }
    public String AlarmTime() {
        int hour = alarmTime.getCurrentHour();
        int minute = alarmTime.getCurrentMinute();
        String minuteString;
        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = minute + "";
        }
        String alarmTimeAsString;
        if (hour > 12) {
            hour = hour - 12;
            alarmTimeAsString = (hour + ":" + minuteString + " PM");
        } else {
            alarmTimeAsString = (hour + ":" + minuteString + " AM");
        }
        return alarmTimeAsString;
    }
    void hui() {
        Intent intent = new Intent(this, startscreen.class);
        startActivity(intent);
        finish();
    }
}