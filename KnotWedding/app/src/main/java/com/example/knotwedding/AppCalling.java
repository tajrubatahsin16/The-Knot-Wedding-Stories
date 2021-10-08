package com.example.knotwedding;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class AppCalling extends AppCompatActivity {
    LottieAnimationView lottieAnimationView1;
    Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_calling);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        lottieAnimationView1=findViewById(R.id.lottie1);
        lottieAnimationView1.animate().translationY(12000).setDuration(40000).setStartDelay(60000);
        call = findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWithRuntimePermission();
            }
        });
    }

    private void callWithRuntimePermission() {
        if((Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new  String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        else
        {
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:01790871475"));
            startActivity(call);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callWithRuntimePermission();
            }
            else
            {
                Toast.makeText(this, "User Denied Permission", Toast.LENGTH_SHORT).show();
            }
        }
    }
}