package com.example.knotwedding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BudgetCalculate extends AppCompatActivity {
    EditText user;
    EditText phone;
    EditText event;
    EditText date;
    EditText venuename;
    private EditText venuecost;
    EditText plattername;
    private EditText plattercost;
    private EditText guestqua;
    EditText dressname;
    private EditText dresscost;
    EditText packagename;
    private EditText packagecost;
    private Button calculate;
    private TextView cost;
    private Button savedata;
    private Button nothing;
    private FirebaseDatabase db= FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Budget");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_calculate);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        venuecost = (EditText) findViewById(R.id.vcost);
        plattercost = (EditText) findViewById(R.id.pcost);
        guestqua = (EditText) findViewById(R.id.gcount);
        dresscost = (EditText) findViewById(R.id.dcost);
        packagecost = (EditText) findViewById(R.id.photocost);
        calculate = (Button) findViewById(R.id.button1);
        savedata = (Button) findViewById(R.id.button2);
        user = (EditText) findViewById(R.id.uname);
        phone = (EditText) findViewById(R.id.pname);
        event = (EditText) findViewById(R.id.ename1);
        date = (EditText) findViewById(R.id.date);
        venuename = (EditText) findViewById(R.id.vname1);
        plattername = (EditText) findViewById(R.id.fname1);
        dressname = (EditText) findViewById(R.id.dname1);
        packagename = (EditText) findViewById(R.id.photoname1);
        cost = (TextView) findViewById(R.id.finalcost);
        nothing = (Button) findViewById(R.id.button3);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double num1 = Double.parseDouble(plattercost.getText().toString());
                double num2 = Double.parseDouble(guestqua.getText().toString());
                double totalfood = (num1 * num2);
                double place = Double.parseDouble(venuecost.getText().toString());
                double dress = Double.parseDouble(dresscost.getText().toString());
                double photo = Double.parseDouble(packagecost.getText().toString());
                double total = (place + totalfood + dress + photo);
                cost.setText("" + String.valueOf(total));
            }
        });
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = user.getText().toString();
                String pnum = phone.getText().toString();
                String ename = event.getText().toString();
                String edate = date.getText().toString();
                String vname = venuename.getText().toString();
                String vcost = venuecost.getText().toString();
                String fname = plattername.getText().toString();
                String fcost = plattercost.getText().toString();
                String gquan = guestqua.getText().toString();
                String dname = dressname.getText().toString();
                String dcost = dresscost.getText().toString();
                String pname = packagename.getText().toString();
                String pcost = packagecost.getText().toString();
                String tcost = cost.getText().toString();
                HashMap<String, String> userMap = new HashMap<>();
                userMap.put("uname", uname);
                userMap.put("pnum", pnum);
                userMap.put("ename", ename);
                userMap.put("edate", edate);
                userMap.put("vname", vname);
                userMap.put("vcost", vcost);
                userMap.put("fname", fname);
                userMap.put("fcost", fcost);
                userMap.put("gquan", gquan);
                userMap.put("dname", dname);
                userMap.put("dcost", dcost);
                userMap.put("pname", pname);
                userMap.put("pcost", pcost);
                userMap.put("tcost", tcost);
                root.push().setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(com.example.knotwedding.BudgetCalculate.this, "You've booked for your event", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
        nothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.knotwedding.BudgetCalculate.this, Showactivity.class));
            }

        });
    }
}