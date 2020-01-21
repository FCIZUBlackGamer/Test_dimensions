package com.free.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class ConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("API123",""+intent.getAction());
        Intent intent1 = new Intent(context,MainActivity.class);
        if(intent.getAction().equals("com.free.test.SOME_ACTION")) {
            Toast.makeText(context, "SOME_ACTION is received", Toast.LENGTH_LONG).show();

            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if (isConnected) {
                intent1.putExtra("Status","Connected");
                try {
                    Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                intent1.putExtra("Status","NotConnected");
                Toast.makeText(context, "Something seems to be wrong with network", Toast.LENGTH_LONG).show();
            }

//            context.startActivity(intent1);

        }
    }
}
