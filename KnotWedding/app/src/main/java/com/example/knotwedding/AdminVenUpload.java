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

public class AdminVenUpload extends AppCompatActivity {
    ImageView venPic;
    Uri uri;
    EditText txt_name, txt_des, txt_cost;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_ven_upload);
        venPic = (ImageView)findViewById(R.id.ivVenImage);
        txt_name = (EditText)findViewById(R.id.txtVenName);
        txt_des = (EditText)findViewById(R.id.txtDesp);
        txt_cost = (EditText)findViewById(R.id.txtCost);
    }

    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            venPic.setImageURI(uri);
        } else {
            Toast.makeText(this, "You haven't picked image", Toast.LENGTH_SHORT).show();
        }
    }
        public void upImage() {
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("VenueImage").child(uri.getLastPathSegment());
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Picture Uploading....");
            progressDialog.show();
            storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!uriTask.isComplete()) ;
                    Uri urlImage = uriTask.getResult();
                    imageUrl = urlImage.toString();
                    uploadImage();
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AdminVenUpload.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });


    }


    public void btnUploadImage(View view) {
        upImage();
    }
    public void uploadImage()
    {
        VenueData venueData = new VenueData(
                txt_name.getText().toString(),
                txt_des.getText().toString(),
                txt_cost.getText().toString(),
                imageUrl
        );
        String myCurrentDateTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Venue").child(myCurrentDateTime).setValue(venueData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AdminVenUpload.this,"Picture Uploaded Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}