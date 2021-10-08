package com.example.knotwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DressDetail extends AppCompatActivity {
    TextView dressDes;
    ImageView dressImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dress_detail);
        dressDes = (TextView)findViewById(R.id.txtDes1);
        dressImage = (ImageView)findViewById(R.id.tvImage2);

        Bundle mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            dressDes.setText(mBundle.getString("Description"));
            //venImage.setImageResource(mBundle.getInt("Image"));
            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(dressImage);
        }
    }
}