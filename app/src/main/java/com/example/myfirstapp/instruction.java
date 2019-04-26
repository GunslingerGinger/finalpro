package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class instruction extends AppCompatActivity {
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);
        findViewById(R.id.goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hui();
            }
        });
    }
    void hui() {
        Intent intent = new Intent(this, startscreen.class);
        startActivity(intent);
        finish();
    }
}
