package com.example.marcdeguzman_comp304sec002_lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    BottomFragment bottomFragment;
    String bottomFragmentText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomFragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerBottom);
        bottomFragmentText = getString(R.string.mainOnCreateMsg);

        // when calling via putArguments, the app stops so for no I am just concatenating the value and
        // update it on onStart
//      bottomFragment.putArguments(R.string.mainOnStartMsg);

//        if (savedInstanceState == null) {
//            bottomFragment = (BottomFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerBottom);
//            updateBottomFragment(getString(R.string.mainOnCreateMsg));
//        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        // update bottom fragment message
        bottomFragmentText = bottomFragmentText + "\n" + getString(R.string.mainOnStartMsg);
        updateBottomFragment(bottomFragmentText);
    }

    protected void updateBottomFragment(String msgValue) {
         bottomFragment.putArguments(msgValue);
    }

}