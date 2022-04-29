package com.example.nodirectorswork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class OnBordingScreen1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_bording_screen1);
    }
    float x1,x2,y1,y2;

    public boolean onTouchEvent(MotionEvent touchEvent){



        switch(touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();

                if((x1 > x2+200) & ((y2 <= y1 + 100)&(y2>=y1-100))) {
                    Intent i = new Intent(OnBordingScreen1.this, OnBordingScreen2.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}