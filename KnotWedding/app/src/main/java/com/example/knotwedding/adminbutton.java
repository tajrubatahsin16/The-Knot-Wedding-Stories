package com.example.knotwedding;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class adminbutton extends AppCompatActivity implements View.OnClickListener {
    private TextView t1,t2,t3;

    protected void onCreate(Bundle savedInstanceState) {
        //getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_adminbutton);
        t1=findViewById(R.id.TextView8);
        t1.setOnClickListener(this);
        t2=findViewById(R.id.TextView9);
        t2.setOnClickListener(this);
        t3=findViewById(R.id.TextView10);
        t3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.TextView8) {
            Intent intent = new Intent(com.example.knotwedding.adminbutton.this, Uploadengagement_Item.class);
            startActivity(intent);

        }
        if(v.getId()==R.id.TextView9) {
            Intent intent = new Intent(com.example.knotwedding.adminbutton.this, Uploadholud_Item.class);
            startActivity(intent);

        }

        if(v.getId()==R.id.TextView10) {
            Intent intent = new Intent(com.example.knotwedding.adminbutton.this, Uploadmainprogram_item.class);
            startActivity(intent);

        }
    }

}