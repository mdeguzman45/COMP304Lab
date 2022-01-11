package com.example.marcdeguzman_bernadettealvarado_comp304lab6_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class ServicesActivity extends AppCompatActivity {
    private TextView textView;
    //replace with your package name
    public static final String INFO_INTENT = "com.example.marcdeguzman_bernadettealvarado_comp304lab6_2.INFO_UPDATE";

    //private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        textView = (TextView) findViewById(R.id.textView);
    }
    //
    public void startService(View view) {


        startService(new Intent(getBaseContext(), SimpleService.class));

        /*Intent intent = new Intent(getBaseContext(), SimpleService.class);
        startService(intent);*/
        //serviceIntent = new Intent(getApplicationContext(), SimpleService.class);

        Log.d("StartService", "Start Service is clicked");

    }

    public void stopService(View view) {
        Log.d("StopService", "Stop Service is clicked");
        stopService(new Intent(getBaseContext(),SimpleService.class));
    }
    //This will handle the broadcast
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        //@Override
        public void onReceive(Context context, Intent intent) {
            textView.setText("Here");
            String action = intent.getAction();
            if (action.equals(SimpleService.INFO_INTENT)) {
                String info = intent.getStringExtra(INFO_INTENT);
                textView.setText(info);
            }
        }
    };

    public void onResume()
    {
        super.onResume();
        //This needs to be in the activity that will end up receiving the broadcast
        registerReceiver(receiver, new IntentFilter(INFO_INTENT));

    }


}
