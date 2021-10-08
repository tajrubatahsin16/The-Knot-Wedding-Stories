package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private Button cra;
    private EditText nam, phone, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        cra = (Button) findViewById(R.id.cr);
        nam = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.loginphne);
        pass = (EditText) findViewById(R.id.loginpass);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        TextView tv=(TextView)findViewById(R.id.tvsignuppage1);
        Animation anim=new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(500);
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tv.startAnimation(anim);
        cra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                craAcc();
            }
        });

    }

    private void craAcc() {
        String name = nam.getText().toString();
        String pne = phone.getText().toString();
        String passwd = pass.getText().toString();

        if (TextUtils.isEmpty(name)) {

            Toast.makeText(this, "Please write your name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(pne)) {

            Toast.makeText(this, "Please write your phone number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(passwd)) {

            Toast.makeText(this, "Please write your password", Toast.LENGTH_SHORT).show();
        } else {

            valide(name, pne, passwd);


        }
    }

    private void valide(String name, String pne, String passwd) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(pne).exists())) {

                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("pne", pne);
                    userdataMap.put("passwd", passwd);
                    userdataMap.put("name", name);
                    RootRef.child("Users").child(pne).updateChildren(userdataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(com.example.knotwedding.Register.this, "Account is created", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(com.example.knotwedding.Register.this, login.class);
                                startActivity(intent);


                            }
                        }
                    });

                } else {

                    Toast.makeText(com.example.knotwedding.Register.this, "This" + pne + "already exits", Toast.LENGTH_SHORT).show();

                    Toast.makeText(com.example.knotwedding.Register.this, "please try", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(com.example.knotwedding.Register.this, Selectionpage.class);
                    startActivity(intent);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

