package com.example.knotwedding;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import dev.yuganshtyagi.smileyrating.SmileyRatingView;


/////////........................ADD DEPENDENCIES AND REPOSITORIES FOR RATING.........................///////////////////////
public class Rate extends AppCompatActivity {

    private RatingBar ratingBar;
    SmileyRatingView smileyRatingView;
    private EditText mFeedBack;
    private Button feedbackbtn;

    private FirebaseDatabase db1 = FirebaseDatabase.getInstance();
    private DatabaseReference root1 =db1.getReference().child("Ratings");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        smileyRatingView = findViewById(R.id.smiley_view);
        ratingBar = findViewById(R.id.rating_bar);
        mFeedBack = findViewById(R.id.FeedBack_txt);
        feedbackbtn = findViewById(R.id.sendFeedbtn);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                smileyRatingView.setSmiley(rating);
                int r = (int) rating;

                //Toast.makeText(getApplicationContext(), s+"Star",Toast.LENGTH_SHORT).show();

                if(r == 1){
                    Toast.makeText(com.example.knotwedding.Rate.this,"Sorry to hear that :(",Toast.LENGTH_SHORT).show();
                }
                else if(r == 2){
                    Toast.makeText(com.example.knotwedding.Rate.this,"You can always make suggestions",Toast.LENGTH_SHORT).show();
                }

                else if(r == 3){
                    Toast.makeText(com.example.knotwedding.Rate.this,"Great!Thank you!",Toast.LENGTH_SHORT).show();
                }
                else if(r == 4){
                    Toast.makeText(com.example.knotwedding.Rate.this,"Awesome!You're the best",Toast.LENGTH_SHORT).show();
                }
            }
        });

        feedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = mFeedBack.getText().toString();
                String rate = String.valueOf(ratingBar.getRating());
                //Toast.makeText(getApplicationContext(),rate+"Start",Toast.LENGTH_SHORT).show();

                if(rate == null)
                {
                    Toast.makeText(com.example.knotwedding.Rate.this,"Please Give a Rating",Toast.LENGTH_SHORT).show();
                }
//                else if (TextUtils.isEmpty(feedback))
//                {
//                    Toast.makeText(Rate.this,"Please Give a FeedBack",Toast.LENGTH_SHORT).show();
//                }

                else {

                    HashMap<String, String> userMap = new HashMap<>();
                    userMap.put("Rate", rate);
                    userMap.put("FeedBack", feedback);
                    root1.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(com.example.knotwedding.Rate.this,"Your Suggestion Is Accepted!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    //Toast.makeText(Rate.this,"Database Updated",Toast.LENGTH_SHORT).show();
                }
                mFeedBack.setText("");
                rate = null;
            }
        });








    }
}