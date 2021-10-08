package com.example.knotwedding;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);
    /*____________________________________________HOOKS___________________________________________________________*/
        drawerLayout = findViewById(R.id.home_drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar3);


        /*____________________________________________TOOLBAR__________________________________________________________*/
        //setSupportActionBar(toolbar);
        //getSupportActionBar().hide();  eta diye project name hide kore
        //getSupportActionBar().setTitle("");  upore tittle set kore

        /*____________________________________________NAVIGATION DRAWER MENU___________________________________________*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case  R.id.nav_home :
                break;

            case R.id.nav_photography:
                Intent photography_intent = new Intent(com.example.knotwedding.HomePage.this,Cameradesign.class);
                startActivity(photography_intent);
                break;

            case R.id.nav_dress:
                Intent dresses_intent = new Intent(com.example.knotwedding.HomePage.this,DressIntro.class);
                startActivity(dresses_intent);
                break;

            case R.id.nav_venue:
                Intent venue_intent = new Intent(com.example.knotwedding.HomePage.this,UserVenue.class);
                startActivity(venue_intent);
                break;

            case R.id.nav_food:
                Intent food_intent = new Intent(com.example.knotwedding.HomePage.this, userdesign.class);
                startActivity(food_intent);
                break;

            case R.id.nav_logout:
                Intent in = getIntent();
                String string = in.getStringExtra("message");
                AlertDialog.Builder builder = new AlertDialog.Builder(HomePage.this);
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
                break;

            case R.id.nav_call:
                Intent call_intent = new Intent(com.example.knotwedding.HomePage.this, AppCalling.class);
                startActivity(call_intent);
                break;

            case R.id.nav_rate:
                Intent rate_intent = new Intent(com.example.knotwedding.HomePage.this, Rate.class);
                startActivity(rate_intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void Calculate(View view) {
        Intent calculate = new Intent(HomePage.this, BudgetIntro.class);
        startActivity(calculate);
    }
}