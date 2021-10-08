package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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


public class login extends AppCompatActivity {
    private EditText pass, phone;
    private Button login;
    private TextView adm, nadmn;
    private String parentdbname = "Users";
    private CheckBox rememberme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.logb);
        adm = (TextView) findViewById(R.id.admin);
        nadmn = (TextView) findViewById(R.id.nadmin);
        phone = (EditText) findViewById(R.id.loginphne);
        pass = (EditText) findViewById(R.id.loginpass);
        rememberme = (CheckBox) findViewById(R.id.chckbox);
        Paper.init(this);

        nadmn.setVisibility(View.INVISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView tv = (TextView) findViewById(R.id.tvlogin);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tv.startAnimation(anim);
        Animation anim1 = new AlphaAnimation(0.0f, 1.0f);
        anim1.setDuration(500);
        anim1.setStartOffset(20);
        anim1.setRepeatMode(Animation.REVERSE);
        anim1.setRepeatCount(Animation.INFINITE);
        //tv1.startAnimation(anim1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguser();
            }
        });

        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setText("Login admin");
                adm.setVisibility(View.INVISIBLE);
                nadmn.setVisibility(View.VISIBLE);
                parentdbname = "Admins";
                loguser();
            }
        });
        nadmn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.setText("Login ");
                adm.setVisibility(View.VISIBLE);
                nadmn.setVisibility(View.INVISIBLE);
                parentdbname = "Users";
            }
        });


    }


    private void loguser(){
        String pne = phone.getText().toString();
        String passwd = pass.getText().toString();
        if (TextUtils.isEmpty(pne)) {

            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(passwd)) {

            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        } else {

            Allowaccess(pne,passwd);


        }
    }

    private void Allowaccess(final String pne,final String passwd) {

        if(rememberme.isChecked())
        {
            Paper.book().write(Prevalent.UserPhoneKey, pne);
            Paper.book().write(Prevalent.UserPasswordKey,passwd);
        }

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentdbname).child(pne).exists()) {

                    Users user=snapshot.child(parentdbname).child(pne).getValue(Users.class);

                    if(user.getPne().equals(pne)){
                        if(user.getPasswd().equals(passwd)){
                            if(parentdbname.equals("Users")){

                                Toast.makeText(com.example.knotwedding.login.this, "login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(com.example.knotwedding.login.this, HomePage.class);
                                startActivity(intent);
                            }

                            else if(parentdbname.equals("Admins")){

                                Toast.makeText(com.example.knotwedding.login.this, "login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(com.example.knotwedding.login.this, AdHomePage.class);
                                startActivity(intent);
                            }
                        }
                    }

                } else {

                    Toast.makeText(com.example.knotwedding.login.this, "Account"+pne+"does not exit", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}

