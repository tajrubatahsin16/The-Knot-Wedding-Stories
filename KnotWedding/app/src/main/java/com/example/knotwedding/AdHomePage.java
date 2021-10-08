package com.example.knotwedding;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import io.paperdb.Paper;

public class AdHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ad_home_page);
    }
    public void adminVenue(View view) {
        Intent LoadAdminVenue = new Intent(AdHomePage.this,AdminVenUpload.class);
        startActivity(LoadAdminVenue);
    }
    public void adminFood(View view) {
        Intent LoadAdminFood = new Intent(AdHomePage.this,adminbutton.class);
        startActivity(LoadAdminFood);
    }
    public void adminDress(View view) {
        Intent LoadAdminDress = new Intent(AdHomePage.this,AdminDreUpload.class);
        startActivity(LoadAdminDress);
    }
    public void photoUp(View view) {
        Intent LoadAdminChat = new Intent(AdHomePage.this,savadataActivity.class);
        startActivity(LoadAdminChat);
    }

    public void adminLogout(View view) {
        Intent in = getIntent();
        String string = in.getStringExtra("message");
        AlertDialog.Builder builder = new AlertDialog.Builder(AdHomePage.this);
        builder.setTitle("Confirmation PopUp!").
                setMessage("You sure, that you want to logout?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent(getApplicationContext(),
                                Selectionpage.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public void bookings(View view) {
        Intent book = new Intent(AdHomePage.this, BudgetShowactivity.class);
        startActivity(book);
    }

    public void rating(View view) {
        Intent rating = new Intent(AdHomePage.this, ShowRate.class);
        startActivity(rating);
    }
}