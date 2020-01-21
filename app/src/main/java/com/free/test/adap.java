package com.free.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.List;

public class adap extends RecyclerView.Adapter<adap.vHlolder> {
    List<String> strings;
    Context context;
    int numItems;

    public adap(List<String> string, Context context, int num){
        strings = string;
        this.context = context;
        numItems = num;
    }

    @NonNull
    @Override
    public adap.vHlolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.str_lay,viewGroup,false);

        return new vHlolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final adap.vHlolder viewHolder, int i) {
        viewHolder.textView.setText(strings.get(i));
        viewHolder.textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.e("Hieght Item",viewHolder.textView.getHeight()+""); //height is ready
            }
        });
    }

    @Override
    public int getItemCount() {
        return numItems;
    }

    public class vHlolder extends RecyclerView.ViewHolder{

        TextView textView;
        public vHlolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt);
        }
    }
}
