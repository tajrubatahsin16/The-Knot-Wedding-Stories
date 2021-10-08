package com.example.knotwedding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserDress extends AppCompatActivity {
    RecyclerView mRecyclerView1;
    List<DressData> dressDataList;
    DressData mDressData;
    private DatabaseReference databaseReference1;
    private ValueEventListener eventListener1;
    ProgressDialog progressDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dress);
        mRecyclerView1 = (RecyclerView) findViewById(R.id.recycler1);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(UserDress.this, 1);
        mRecyclerView1.setLayoutManager(gridLayoutManager);
        progressDialog1 = new ProgressDialog(this);
        progressDialog1.setMessage("Loading Items....");
        dressDataList = new ArrayList<>();

        DressAdapter dressAdapter = new DressAdapter(UserDress.this, dressDataList);
        mRecyclerView1.setAdapter(dressAdapter);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Dress");
        progressDialog1.show();
        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dressDataList.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    DressData dressData = itemSnapshot.getValue(DressData.class);
                    dressDataList.add(dressData);

                }
                dressAdapter.notifyDataSetChanged();
                progressDialog1.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog1.dismiss();

            }
        });

    }
}