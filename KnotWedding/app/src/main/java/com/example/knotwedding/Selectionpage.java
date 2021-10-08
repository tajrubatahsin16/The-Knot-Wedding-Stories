package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.knotwedding.Model.Users;
import com.example.knotwedding.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Selectionpage extends AppCompatActivity implements View.OnClickListener{
    private Button b1, b2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectipnpage);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView tv=(TextView)findViewById(R.id.tvselectionpage);
        Animation anim=new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tv.startAnimation(anim);
        b1 = findViewById(R.id.btn1);
        b2 = findViewById(R.id.btn2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        Paper.init(this);

        String UserPhoneKey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
        if(UserPhoneKey!="" && UserPasswordKey!="")
        {
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey))
            {
                AllowAccess(UserPhoneKey, UserPasswordKey);
            }
        }
    }
    private void AllowAccess(final String pne, final String passwd)
    {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Users").child(pne).exists()) {

                    Users user=snapshot.child("Users").child(pne).getValue(Users.class);

                    if(user.getPne().equals(pne)){
                        if(user.getPasswd().equals(passwd)){
                           // if(user.equals("Users")){

                                Toast.makeText(com.example.knotwedding.Selectionpage.this, "Already logged in!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(com.example.knotwedding.Selectionpage.this, HomePage.class);
                                startActivity(intent);
                            //}

                        }
                    }

                } else {

                    Toast.makeText(com.example.knotwedding.Selectionpage.this, "Account"+pne+"does not exit", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn1) {
            Intent intent = new Intent(com.example.knotwedding.Selectionpage.this, Register.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btn2) {
            Intent intent = new Intent(com.example.knotwedding.Selectionpage.this, login.class);
            startActivity(intent);

        }
    }
}





