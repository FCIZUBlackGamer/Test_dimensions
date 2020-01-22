package com.free.test;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    List<String> strings;
    RecyclerView.Adapter adapter;
    int numItems = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        recyclerView = findViewById(R.id.rec);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        strings = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            strings.add("Name "+(i+1));
        }

        view = findViewById(R.id.v).getRootView();

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                numItems = view.getHeight()/300;
                Log.e("Hieght view", view.getHeight() + ""); //height is ready
                Log.e("numItems", numItems + ""); //height is ready

            }
        });

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                if (numItems == 0)
                handler.postDelayed(this, 1000);
                adapter = new adap(strings, MainActivity.this, numItems);
                recyclerView.setAdapter(adapter);
            }
        };

        handler.postDelayed(r, 1000);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this,MyReceiver.class);

        startService(intent);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            stopService(intent);
        }catch (Exception e){

        }
    }

}
