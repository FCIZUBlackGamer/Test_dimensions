package com.free.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    List<String> strings;
    RecyclerView.Adapter adapter;
    int numItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
