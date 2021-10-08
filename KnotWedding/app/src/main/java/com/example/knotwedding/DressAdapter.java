package com.example.knotwedding;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DressAdapter extends RecyclerView.Adapter<DressViewHolder> {

    private Context mContext1;
    private List<DressData> myDressList;
    private int lastPosition1 = -1;

    public DressAdapter(Context mContext1, List<DressData> myDressList) {
        this.mContext1 = mContext1;
        this.myDressList = myDressList;
    }

    @Override
    public DressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.dress_row_item,parent,false);
        return new DressViewHolder(mView1);
    }

    @Override
    public void onBindViewHolder(@NonNull final DressViewHolder holder, int position) {
        Glide.with(mContext1)
                .load(myDressList.get(position).getDrImage())
                .into(holder.image1);

        holder.mTitle1.setText(myDressList.get(position).getDrName());
        holder.mDes1.setText(myDressList.get(position).getDrDes());
        holder.mPrice1.setText(myDressList.get(position).getDrCost());

        holder.mCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(mContext1,DressDetail.class);
                intent1.putExtra("Image",myDressList.get(holder.getAdapterPosition()).getDrImage());
                intent1.putExtra("Description",myDressList.get(holder.getAdapterPosition()).getDrDes());
                mContext1.startActivity(intent1);
            }
        });
        setAnimation1(holder.itemView,position);
    }
    public void setAnimation1(View viewToAnimate, int position)
    {
        if(position>lastPosition1)
        {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f,0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition1 = position;
        }
    }

    @Override
    public int getItemCount() {
        return myDressList.size();
    }
}
class DressViewHolder extends RecyclerView.ViewHolder{
    ImageView image1;
    TextView mTitle1, mDes1, mPrice1;
    CardView mCardView1;

    public DressViewHolder( View itemView) {
        super(itemView);
        image1 = itemView.findViewById(R.id.ivImage1);
        mTitle1 = itemView.findViewById(R.id.tvTitle1);
        mDes1 = itemView.findViewById(R.id.tvDes1);
        mPrice1 = itemView.findViewById(R.id.tvPrice1);
        mCardView1 = itemView.findViewById(R.id.myCardView1);
    }
}
