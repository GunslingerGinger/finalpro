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

import java.util.ArrayList;

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
                if (enableAlarms()) {
                    String[] currentHourAndMinute = currentTime.getText().toString().split(":");
                    Integer firstPartAsInt = Integer.parseInt(currentHourAndMinute[0]);
                    if (currentHourAndMinute[1].contains("PM")) {
                        firstPartAsInt += 12;
                    }
                    currentHourAndMinute[0] = firstPartAsInt.toString();
                    currentHourAndMinute[1] = currentHourAndMinute[1].substring(0,2);
                    String currentTimeAsString = currentHourAndMinute[0] + currentHourAndMinute[1];
                    int currentTimeAsInt = Integer.parseInt(currentTimeAsString);
                    if (currentTimeAsInt > firstSetTimeAsInt && currentTimeAsInt < lastSetTimeAsInt) {
                        if ()
                            simpleRingtone.play();
                    } else {
                        simpleRingtone.stop();
                    }
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
    public int spacingBetweenAlarms() {
        Integer alarmSpacing = 5;

        //create textbox to set alarmSpacing when typed in

        if (!(alarmSpacing instanceof Integer)) {
            alarmSpacing = 5;
        }
        if (alarmSpacing > 1438) {
            alarmSpacing = 1438;
        }
        if (alarmSpacing < 1) {
            alarmSpacing = 1;
        }
    }
    public boolean enableAlarms() {
        boolean alarmOnOrOff = false;

        //create a button that switches between and displays on or off and sets alarmOnOrOff to true
        //or false accordingly. This controls alarms working or not.

        return alarmOnOrOff;
    }
    public ArrayList<Integer> allAlarmTimesAsInts() {
        ArrayList<Integer> nothing = new ArrayList<>();
        if (currentStartAlarmTime().equals("") || currentFinalAlarmTime().equals("")) {
            return nothing;
        }

        String[] currentStartHourAndMinute = currentStartAlarmTime().split(":");
        Integer firstStartPartAsInt = Integer.parseInt(currentStartHourAndMinute[0]);
        if (currentStartHourAndMinute[1].contains("PM")) {
            firstStartPartAsInt += 12;
        }
        currentStartHourAndMinute[0] = firstStartPartAsInt.toString();
        currentStartHourAndMinute[1] = currentStartHourAndMinute[1].substring(0,2);
        String currentStartTimeAsString = currentStartHourAndMinute[0] + currentStartHourAndMinute[1];
        int currentStartTimeAsInt = Integer.parseInt(currentStartTimeAsString);

        String[] currentFinalHourAndMinute = currentFinalAlarmTime().split(":");
        Integer firstFinalPartAsInt = Integer.parseInt(currentFinalHourAndMinute[0]);
        if (currentFinalHourAndMinute[1].contains("PM")) {
            firstFinalPartAsInt += 12;
        }
        currentFinalHourAndMinute[0] = firstFinalPartAsInt.toString();
        currentFinalHourAndMinute[1] = currentFinalHourAndMinute[1].substring(0,2);
        String currentFinalTimeAsString = currentFinalHourAndMinute[0] + currentFinalHourAndMinute[1];
        int currentFinalTimeAsInt = Integer.parseInt(currentFinalTimeAsString);
        if (currentStartTimeAsInt == currentFinalTimeAsInt) {
            ArrayList<Integer> singleAlarm = new ArrayList<>();
            singleAlarm.add(currentStartTimeAsInt);
            return singleAlarm;
        }
        ArrayList<Integer> allAlarms = new ArrayList<>();
        int tempForAddingToString = currentStartTimeAsInt;
        while (tempForAddingToString != currentFinalTimeAsInt) {
            if (tempForAddi)
                tempForAddingToString++;
        }
    }
    public String currentStartAlarmTime() {
        String currentStartAlarm = "";

        //create button to call SetStartAlarmTime and set currentstartalarm equal to it when clicked

        return currentStartAlarm;
    }
    public String currentFinalAlarmTime() {
        String currentFinalAlarm = "";

        //create button to call SetFinalAlarmTime and set currentfinalalarm equal to it when clicked

        return currentFinalAlarm;
    }
    public String setStartAlarmTime() {
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
    public String setEndAlarmTime() {
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