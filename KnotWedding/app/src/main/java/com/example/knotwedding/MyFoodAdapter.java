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

public class MyFoodAdapter extends RecyclerView.Adapter<com.example.knotwedding.FoodViewHolder> {

    private Context mContext2;
    private List<FoodData> myFoodList;
    private int lastposition=-1;


    public MyFoodAdapter(Context mContext2, List<FoodData> myFoodList) {
        this.mContext2 = mContext2;
        this.myFoodList = myFoodList;
    }

    @Override
    public com.example.knotwedding.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_food_item,viewGroup, false);
        return new com.example.knotwedding.FoodViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.knotwedding.FoodViewHolder foodViewHolder, int i) {

        Glide.with(mContext2).load(myFoodList.get(i).getItemImage()).into(foodViewHolder.imageView);
        //foodViewHolder.imageView.setImageResource(myPlaceList.get(i).getItemImage());
        foodViewHolder.mTitle.setText(myFoodList.get(i).getItemName());
        foodViewHolder.mDescription.setText(myFoodList.get(i).getItemDescription());
        foodViewHolder.mPrice.setText(myFoodList.get(i).getItemPrice());

        foodViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext2,DetailActivity.class);
                intent.putExtra("Image",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemImage());
                intent.putExtra("Description",myFoodList.get(foodViewHolder.getAdapterPosition()).getItemDescription());
                mContext2.startActivity(intent);
            }
        });
        setAnimation(foodViewHolder.imageView,i);
    }
    public void setAnimation(View viewToAnimate,int position )
    {
      if(position>lastposition)
      {
          ScaleAnimation animation=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f,
                  Animation.RELATIVE_TO_SELF,0.5f,
                  Animation.RELATIVE_TO_SELF,0.5f);
          animation.setDuration(1500);
          viewToAnimate.startAnimation(animation);
          lastposition=position;

      }
    }

    @Override
    public int getItemCount() {
        return myFoodList.size();
    }
}

class FoodViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle, mDescription, mPrice;
    CardView mCardView;

    public FoodViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.ivImage);
        mTitle = itemView.findViewById(R.id.tvTitle);
        mDescription = itemView.findViewById(R.id.tvDes);
        mPrice = itemView.findViewById(R.id.tvPrice);

        mCardView = itemView.findViewById(R.id.myCardView);



    }
}
