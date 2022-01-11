package com.example.marcdeguzman_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Exercise3 extends AppCompatActivity {

    // We will animate the imageview
    ImageView reusableImageView = null;

    Button startButton;
    Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise3);

        reusableImageView = (ImageView) findViewById(R.id.earthImageView);

        // Handle Start Button
        startButton = (Button) findViewById(R.id.ex3StartBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                performAnimation();
            }
        });

        // Handle Stop Button
        stopButton = (Button) findViewById(R.id.ex3StopBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stopAnimation();
            }
        });
    }

    private void performAnimation()
    {
        // Load the appropriate animation using the animation set
        Animation animateSpin =  AnimationUtils.loadAnimation(this, R.anim.spin);
        Animation animateTranslate = AnimationUtils.loadAnimation(this, R.anim.translate_position);
        Animation animateGrow = AnimationUtils.loadAnimation(this, R.anim.grow);

        AnimationSet aniSet = new AnimationSet(false); // false means don't share interpolators
        aniSet.addAnimation(animateSpin);
        aniSet.addAnimation(animateTranslate);
        aniSet.addAnimation(animateGrow);

        reusableImageView.startAnimation(aniSet);
    }

    private void stopAnimation() {
        // cancel the animation
        reusableImageView.clearAnimation();
    }
}