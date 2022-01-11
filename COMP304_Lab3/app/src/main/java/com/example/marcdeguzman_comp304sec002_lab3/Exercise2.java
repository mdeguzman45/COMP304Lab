package com.example.marcdeguzman_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Exercise2 extends AppCompatActivity {
    AnimationDrawable mframeAnimation = null;

    Button startButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise2);

        // Handle Start Button
        startButton = (Button) findViewById(R.id.ex2StartBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startAnimation();
            }
        });

        // Handle Stop Button
        stopButton = (Button) findViewById(R.id.ex2StopBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    private void startAnimation()
    {
        //
        // get the pointer for the image view
        ImageView img = (ImageView)findViewById(R.id.goku_imageView);

        // setup the image that we will send in the animation
        BitmapDrawable frame1 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku1);
        BitmapDrawable frame2 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku2);
        BitmapDrawable frame3 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku3);
        BitmapDrawable frame4 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku4);
        BitmapDrawable frame5 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku5);
        BitmapDrawable frame6 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku6);
        BitmapDrawable frame7 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku7);
        BitmapDrawable frame8 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku8);
        BitmapDrawable frame9 = (BitmapDrawable) ContextCompat.getDrawable(this, R.drawable.goku9);

        // Get the background, which has been compiled to an AnimationDrawable object.
        int reasonableDuration = 250;
        mframeAnimation = new AnimationDrawable();
        mframeAnimation.setOneShot(false);	// loop continuously
        mframeAnimation.addFrame(frame1, reasonableDuration);
        mframeAnimation.addFrame(frame2, reasonableDuration);
        mframeAnimation.addFrame(frame3, reasonableDuration);
        mframeAnimation.addFrame(frame4, reasonableDuration);
        mframeAnimation.addFrame(frame5, reasonableDuration);
        mframeAnimation.addFrame(frame6, reasonableDuration);
        mframeAnimation.addFrame(frame7, reasonableDuration);
        mframeAnimation.addFrame(frame8, reasonableDuration);
        mframeAnimation.addFrame(frame9, reasonableDuration);

        img.setBackground(mframeAnimation);

        mframeAnimation.setVisible(true,true);
        mframeAnimation.start();
    }
    private void stopAnimation()
    {
        mframeAnimation.stop();
        mframeAnimation.setVisible(false,false);
    }
}