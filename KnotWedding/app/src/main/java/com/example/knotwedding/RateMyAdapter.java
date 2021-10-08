package com.example.knotwedding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RateMyAdapter extends RecyclerView.Adapter<RateMyAdapter.MyViewHolder> {

    ArrayList<RateMODEL> mList;
    Context context;

    public RateMyAdapter(Context context, ArrayList<RateMODEL> mList)
    {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemm, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RateMODEL model = mList.get(position);
        holder.Rate.setText(model.getRate());
        holder.FeedBack.setText(model.getFeedBack());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Rate,FeedBack;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Rate = (itemView).findViewById(R.id.rating_txt);
            FeedBack =(itemView).findViewById(R.id.FeeD_txt);
        }
    }
}
