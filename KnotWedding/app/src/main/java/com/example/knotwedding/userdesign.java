package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class userdesign extends AppCompatActivity {
LottieAnimationView lottieAnimationView;

    private ProgressBar progressBar;
    private int progress;  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdesign);
        lottieAnimationView=findViewById(R.id.lottie);
        lottieAnimationView.animate().translationY(1200).setDuration(2000).setStartDelay(7000);
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
        Intent intent = new Intent(com.example.knotwedding.userdesign.this,userbutton.class);
        startActivity(intent);
        finish();
    }
}

