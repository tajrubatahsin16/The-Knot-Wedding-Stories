package com.example.knotwedding;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainProgram extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<FoodData> myFOODList;
    FoodData mFoodData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_program);
        //getSupportActionBar().hide();
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(com.example.knotwedding.MainProgram.this, 1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading......");
        myFOODList = new ArrayList<>();



        MyFoodAdapter myFoodAdapter = new MyFoodAdapter(com.example.knotwedding.MainProgram.this,myFOODList);
        mRecyclerView.setAdapter(myFoodAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("MainprogramfoodInfo");

        progressDialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myFOODList.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    FoodData foodData = snapshot.getValue(FoodData.class);
                    myFOODList.add(foodData);
                }
                myFoodAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
            }
        });

    }

    public void btn_uploadActivity(View view) {
        startActivity(new Intent(this, Uploadengagement_Item.class));
    }
}