package com.example.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class startscreen extends AppCompatActivity {
    //asdasdsdfaasdfasdfafa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        Button play = (Button) findViewById(R.id.Play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Final Project", "oh yeah!");
                Toast.makeText((getApplicationContext()),
                        "Jincheng, Jack, Jordan!", Toast.LENGTH_LONG).show();
            }
        });
        Button btn1 = (Button) findViewById(R.id.quit);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        findViewById(R.id.truth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        findViewById(R.id.imageButton5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((getApplicationContext()),
                        "♥++!!!", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView jin = findViewById(R.id.imageView);
        jin.setVisibility(View.INVISIBLE);
        final Button me = findViewById(R.id.button2);
        me.setVisibility(View.VISIBLE);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.imageView).setVisibility(View.VISIBLE);
                Toast.makeText((getApplicationContext()),
                        "Jincheng!!!!", Toast.LENGTH_LONG).show();
                me.setVisibility(View.INVISIBLE);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instruction();
            }
        });
    }
    void startGame() {
        Intent intent = new Intent(this, intro.class);
        startActivity(intent);
        finish();
    }
    void instruction() {
        Intent intent = new Intent(this, instruction.class);
        startActivity(intent);
        finish();
    }
}
