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

public class MyAdapter extends RecyclerView.Adapter<VenueViewHolder>{
    private Context mContext;
    private List<VenueData> myVenueList;
    private int lastPosition = -1;

    public MyAdapter(Context mContext, List<VenueData> myVenueList) {
        this.mContext = mContext;
        this.myVenueList = myVenueList;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item,parent,false);
        return new VenueViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final VenueViewHolder holder, int position) {
        Glide.with(mContext)
                .load(myVenueList.get(position).getVenImage())
                .into(holder.image);

        //holder.image.setImageResource(myVenueList.get(position).getVenImage());
        holder.mTitle.setText(myVenueList.get(position).getVenName());
        holder.mDes.setText(myVenueList.get(position).getVenDes());
        holder.mPrice.setText(myVenueList.get(position).getVenCost());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,VenueDetail.class);
                intent.putExtra("Image",myVenueList.get(holder.getAdapterPosition()).getVenImage());
                intent.putExtra("Description",myVenueList.get(holder.getAdapterPosition()).getVenDes());
                mContext.startActivity(intent);
            }
        });
        setAnimation(holder.itemView,position);

    }
    public void setAnimation(View viewToAnimate, int position)
    {
        if(position>lastPosition)
        {
            ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f,0.0f, 1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(1500);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return myVenueList.size();
    }
}
class VenueViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView mTitle, mDes, mPrice;
    CardView mCardView;

    public VenueViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDes = itemView.findViewById(R.id.tvDes);
        mPrice = itemView.findViewById(R.id.tvPrice);
        mCardView = itemView.findViewById(R.id.myCardView);
    }
}
