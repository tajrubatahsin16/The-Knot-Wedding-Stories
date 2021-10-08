package com.example.knotwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class VenueDetail extends AppCompatActivity {
    TextView venDes;
    ImageView venImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_detail);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        venDes = (TextView)findViewById(R.id.txtDes);
        venImage = (ImageView)findViewById(R.id.tvImage1);

        Bundle mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            venDes.setText(mBundle.getString("Description"));
            //venImage.setImageResource(mBundle.getInt("Image"));
            Glide.with(this)
                    .load(mBundle.getString("Image"))
                    .into(venImage);
        }
    }
}