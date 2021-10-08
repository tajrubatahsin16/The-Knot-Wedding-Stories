package com.example.knotwedding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyadaperPhoto extends RecyclerView.Adapter<MyadaperPhoto.MyViewHolder> {
    ArrayList<ModelPhoto> mList;
    Context context;
    public MyadaperPhoto(Context context , ArrayList<ModelPhoto> mList)
    {
        this.mList=mList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
ModelPhoto modelPhoto =mList.get(position);

holder.name.setText(modelPhoto.getName());
holder.details.setText(modelPhoto.getDetails());
        holder.price.setText(modelPhoto.getPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView name,details ,price;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            details=itemView.findViewById(R.id.details_text);
            name=itemView.findViewById(R.id.name_text);
            price=itemView.findViewById(R.id.price_text);
            
        }
    }
}
