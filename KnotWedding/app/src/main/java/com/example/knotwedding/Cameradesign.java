package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Cameradesign extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;

    private ProgressBar progressBar;
    private int progress;  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_design);
        lottieAnimationView=findViewById(R.id.lottie1);
        lottieAnimationView.animate().translationY(1200).setDuration(1200).setStartDelay(6000);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        progressBar = (ProgressBar) findViewById(R.id.progressBarId);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();
    }
    public void doWork()
    {
        for(progress=20; progress<=100; progress=progress+20)
        {
            try {
                Thread.sleep(1200);
                progressBar.setProgress(progress);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void startApp()
    {
        Intent intent = new Intent(com.example.knotwedding.Cameradesign.this,Showactivity.class);
        startActivity(intent);
        finish();
    }
}

