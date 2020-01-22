package com.free.test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.Toast;

public class MyReceiver extends Service {

    public MyReceiver() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
//        Toast.makeText(this, "Invoke background service onCreate method.", Toast.LENGTH_LONG).show();
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
//            intent1.putExtra("Status","Connected");
            try {
                Toast.makeText(this, "Network is connected", Toast.LENGTH_LONG).show();
                stopSelf();
                //Todo: Start Activity
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
//            intent1.putExtra("Status","NotConnected");
            Toast.makeText(this, "Something seems to be wrong with network", Toast.LENGTH_SHORT).show();
            stopSelf();
            new CountDownTimer(4000, 1000) {
                public void onTick(long millisUntilFinished) {
//                    mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);

                }

                public void onFinish() {
//                    mTextField.setText("done!");

                    startService(new Intent(MyReceiver.this,MyReceiver.class));
                }
            }.start();

        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        Toast.makeText(this, "Invoke background service onDestroy method.", Toast.LENGTH_LONG).show();
    }

}
