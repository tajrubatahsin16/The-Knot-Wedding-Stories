package com.example.knotwedding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class AdminDreUpload extends AppCompatActivity {
    ImageView dressPic;
    Uri uri1;
    EditText txt_name1, txt_des1, txt_cost1;
    String imageUrl1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_dre_upload);
        dressPic = (ImageView)findViewById(R.id.ivDressImage);
        txt_name1 = (EditText)findViewById(R.id.txtDressName);
        txt_des1 = (EditText)findViewById(R.id.txtDesp1);
        txt_cost1 = (EditText)findViewById(R.id.txtCost1);
    }

    public void btnSelectImage1(View view) {
        Intent photoPicker1 = new Intent(Intent.ACTION_PICK);
        photoPicker1.setType("image/*");
        startActivityForResult(photoPicker1,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri1 = data.getData();
            dressPic.setImageURI(uri1);
        } else {
            Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();
        }
    }
    public void upImageD() {
        StorageReference storageReference1 = FirebaseStorage.getInstance().getReference().child("DressImage").child(uri1.getLastPathSegment());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Picture Uploading....");
        progressDialog.show();
        storageReference1.putFile(uri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete()) ;
                Uri urlImage1 = uriTask.getResult();
                imageUrl1 = urlImage1.toString();
                uploadImageD();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminDreUpload.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btnUploadImage1(View view) {
        upImageD();
    }
    public void uploadImageD()
    {
        DressData dressData = new DressData(
                txt_name1.getText().toString(),
                txt_des1.getText().toString(),
                txt_cost1.getText().toString(),
                imageUrl1
        );
        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Dress").child(myCurrentDateTime).setValue(dressData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AdminDreUpload.this,"Picture Uploaded Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}