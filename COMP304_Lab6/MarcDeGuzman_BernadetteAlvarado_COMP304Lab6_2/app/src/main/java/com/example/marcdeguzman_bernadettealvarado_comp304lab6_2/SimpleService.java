package com.example.marcdeguzman_bernadettealvarado_comp304lab6_2;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class SimpleService extends Service {
    //replace with your package name
    public static final String INFO_INTENT = "com.example.marcdeguzman_bernadettealvarado_comp304lab6_2.INFO_UPDATE";


    public SimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand", "Simple Service.java has been started");
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(INFO_INTENT);
        broadcastIntent.putExtra(INFO_INTENT,
                "Hello there! A simple service is sending this message to you!");
        this.sendBroadcast(broadcastIntent);

//
        return START_STICKY;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
