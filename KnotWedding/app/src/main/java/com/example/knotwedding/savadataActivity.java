package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class savadataActivity extends AppCompatActivity {
private EditText mName,mdetails,mprice;
private Button button,button2;
private FirebaseDatabase db= FirebaseDatabase.getInstance();
private DatabaseReference root=db.getReference().child("Photography");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savadata);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mName=findViewById(R.id.name);
        mdetails=findViewById(R.id.details);
        mprice=findViewById(R.id.price);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mName.getText().toString();
               String price=mprice.getText().toString();
               String details=mdetails.getText().toString();
                HashMap<String, String> userMap=new HashMap<>();
                userMap.put("name",name);
                userMap.put("price",price);
                userMap.put("details",details);
                root.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(com.example.knotwedding.savadataActivity.this,"DATA SAVED",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.knotwedding.savadataActivity.this,Showactivity.class));
            }
        });
    }
}