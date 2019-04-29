package com.example.myfirstapp;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
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
                currentStartAlarm = setStartAlarmTime();
            }
        });
        findViewById(R.id.buttonf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFinalAlarm = setEndAlarmTime();
            }
        });
        alarmSpacing = findViewById(R.id.space);
        findViewById(R.id.mko).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spacingBetweenAlarms();
            }
        });
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
        int differenceInTimes = (currentFinalTimeAsInt - currentStartTimeAsInt);
        if (differenceInTimes < 0) {
            differenceInTimes += 2400;
        }
        int numberOfAlarms = differenceInTimes / spacingBetweenAlarms();
        if (numberOfAlarms == 0) {
            ArrayList<Integer> singleAlarm = new ArrayList<>();
            singleAlarm.add(currentStartTimeAsInt);
            return singleAlarm;
        }
        for (int i = 0; i <= numberOfAlarms; i++) {
            int toAdd;
            if (currentStartTimeAsInt + (i * spacingBetweenAlarms()) < 2400) {
                toAdd = currentStartTimeAsInt + (i * spacingBetweenAlarms());
            } else {
                toAdd = currentStartTimeAsInt + (i * spacingBetweenAlarms()) - 2400;
            }
            allAlarms.add(toAdd);
        }
        return allAlarms;
    }
    public String currentStartAlarmTime() {

        //create button to call SetStartAlarmTime and set currentStartAlarm equal to it when clicked
        //
        //
        //
        //
        //

        return currentStartAlarm;
    }
    public String currentFinalAlarmTime() {

        //create button to call SetFinalAlarmTime and set currentFinalAlarm equal to it when clicked
        //
        //
        //
        //
        //

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