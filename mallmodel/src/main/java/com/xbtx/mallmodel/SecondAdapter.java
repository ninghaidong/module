package com.xbtx.mallmodel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author: 宁
 * @className:
 * @date: 2021/8/3 20:40
 */
public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.TextViewHolder> {

    private List<String> stringList;
    private Context context;
    private int oldPosition = -1;

    public SecondAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter, null));
    }


    @Override
    public void onBindViewHolder(@NonNull SecondAdapter.TextViewHolder holder, int position) {
        holder.textContent.setText(stringList.get(position));
        holder.textContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldPosition != position) {
                    Log.e("SecondAdapter", "新的");
                } else {
                    Log.e("SecondAdapter", "当前");
                }
                oldPosition = position;
            }
        });
    }


    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textContent;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            textContent = itemView.findViewById(R.id.textContent);
        }
    }
}