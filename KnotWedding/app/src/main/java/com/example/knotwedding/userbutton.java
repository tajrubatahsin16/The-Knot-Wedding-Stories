package com.example.knotwedding;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class userbutton extends AppCompatActivity implements View.OnClickListener {
    private TextView t1,t2,t3;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.useractivity_button);
        //getSupportActionBar().hide();
        t1=findViewById(R.id.TextView1);
        t1.setOnClickListener(this);
        t2=findViewById(R.id.TextView2);
        t2.setOnClickListener(this);
        t3=findViewById(R.id.TextView3);
        t3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.TextView1) {
            Intent intent = new Intent(com.example.knotwedding.userbutton.this, Engagementfood.class);
            startActivity(intent);

        }
        if(v.getId()==R.id.TextView2) {
            Intent intent = new Intent(com.example.knotwedding.userbutton.this, Holudfood.class);
            startActivity(intent);

        }

        if(v.getId()==R.id.TextView3) {
            Intent intent = new Intent(com.example.knotwedding.userbutton.this, MainProgram.class);
            startActivity(intent);

        }
    }

}