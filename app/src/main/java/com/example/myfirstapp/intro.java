package com.example.myfirstapp;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Timer;
import java.util.TimerTask;

import java.util.ArrayList;

public class intro extends AppCompatActivity {

    TextClock currentTime;
    TimePicker alarmTime;
    boolean alarmOnOrOff = false;
    String currentStartAlarm = "";
    String currentFinalAlarm = "";
    EditText alarmSpacing;
    Integer alarmSpace = 5;
    Integer currentStartAlarmInt = -1;
    Integer currentFinalAlarmInt = -1;

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
                    Integer currentTimeAsInt = Integer.parseInt(currentTimeAsString);

                    int checkThatATimeMatched = 0;
                    for (Integer alarms : allAlarmTimesAsInts()) {
                        if (currentTimeAsInt.equals(alarms)) {
                            simpleRingtone.play();
                            checkThatATimeMatched++;
                        }
                    }
                    if (checkThatATimeMatched == 0) {
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
        findViewById(R.id.off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmOnOrOff = false;
            }
        });
        findViewById(R.id.on).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmOnOrOff = true;
            }
        });
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentStartAlarmInt = currentStartAlarmTime();
            }
        });
        findViewById(R.id.buttonf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFinalAlarmInt = currentFinalAlarmTime();
            }
        });
        TextView me = findViewById(R.id.textView2);
        me.setText(alarmSpace.toString());
    }
    public int spacingBetweenAlarms() {
        String space = alarmSpacing.getText().toString().trim();
        if (space.isEmpty()) {
            alarmSpacing.setError("this can't be empty");
        } else {
            alarmSpacing.setError(null);
        }
        int a = Integer.parseInt(space);
        alarmSpace = new Integer(a);
        //create textbox to set alarmSpacing to a value when typed in
        //
        //
        //
        //

        if (!(alarmSpace instanceof Integer)) {
            alarmSpace = 5;
        }
        if (alarmSpace > 1438) {
            alarmSpace = 1438;
        }
        if (alarmSpace < 1) {
            alarmSpace = 1;
        }
        return alarmSpace;
    }
    public boolean enableAlarms() {


        //create a button that switches between and displays on or off and sets alarmOnOrOff to true
        //or false accordingly. This controls alarms working or not.
        //
        //
        //
        //

        return alarmOnOrOff;
    }
    public ArrayList<Integer> allAlarmTimesAsInts() {
        ArrayList<Integer> nothing = new ArrayList<>();
        if (currentStartAlarmTime().equals(-1) || currentFinalAlarmTime().equals(-1)) {
            return nothing;
        }
        if (currentStartAlarmInt.equals(currentFinalAlarmInt)) {
            ArrayList<Integer> singleAlarm = new ArrayList<>();
            singleAlarm.add(currentStartAlarmInt);
            return singleAlarm;
        }
        ArrayList<Integer> allAlarms = new ArrayList<>();
        int differenceInTimes = (currentFinalAlarmInt - currentStartAlarmInt);
        if (differenceInTimes < 0) {
            differenceInTimes += 2400;
        }
        int numberOfAlarms = differenceInTimes / spacingBetweenAlarms();
        if (numberOfAlarms == 0) {
            ArrayList<Integer> singleAlarm = new ArrayList<>();
            singleAlarm.add(currentStartAlarmInt);
            return singleAlarm;
        }
        for (int i = 0; i <= numberOfAlarms; i++) {
            int toAdd;
            if (currentStartAlarmInt + (i * spacingBetweenAlarms()) < 2400) {
                toAdd = currentStartAlarmInt + (i * spacingBetweenAlarms());
            } else {
                toAdd = currentStartAlarmInt + (i * spacingBetweenAlarms()) - 2400;
            }
            allAlarms.add(toAdd);
        }
        return allAlarms;
    }
    public Integer currentStartAlarmTime() {

        // old version: create button to call SetStartAlarmTime and set currentStartAlarm equal
        // to it when clicked
        //
        //
        //instead, create button to do this when clicked in order to set currentStartAlarmInt:
        String currentHour = alarmTime.getCurrentHour().toString();
        Integer currentMinute = alarmTime.getCurrentMinute();
        String currentMinuteAsString = currentMinute.toString();
        if (currentMinute < 10) {
            currentMinuteAsString += 0;
        }
        String combinedHourAndMin = currentHour + currentMinuteAsString;
        currentStartAlarmInt = Integer.parseInt(combinedHourAndMin);
        return currentStartAlarmInt;

        // return currentStartAlarm; (this is useless now)
    }
    public Integer currentFinalAlarmTime() {

        String currentHour = alarmTime.getCurrentHour().toString();
        Integer currentMinute = alarmTime.getCurrentMinute();
        String currentMinuteAsString = currentMinute.toString();
        if (currentMinute < 10) {
            currentMinuteAsString += 0;
        }
        String combinedHourAndMin = currentHour + currentMinuteAsString;
        currentFinalAlarmInt = Integer.parseInt(combinedHourAndMin);
        return currentFinalAlarmInt;
        //
        //create button to do this when clicked in order to set currentFinalAlarmInt;
        //
        //

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