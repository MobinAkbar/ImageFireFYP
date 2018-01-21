package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

public class OwnerProfileActivity extends AppCompatActivity {

    private EditText name,adres,profession,numb1,numb2,numb3;
    Button B1,B2,B3;
    ImageView image;
    private Uri filepath;
    public static final int REQUEST_CODE=1234;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    String UserId,names,prof,numr1,numbr2,numbr3,addresss;
    Owner users;
    public static final String FB_STOARGE_PATH="Owners/";
    private String urll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);

        name=(EditText) findViewById(R.id.name);
        adres=(EditText)findViewById(R.id.adresy);
        profession=(EditText) findViewById(R.id.job);
        numb1=(EditText) findViewById(R.id.num1);
        numb2=(EditText) findViewById(R.id.num2);
        numb3=(EditText) findViewById(R.id.num3);
        B1=(Button)findViewById(R.id.saves);
        B2=(Button)findViewById(R.id.removes);
        B3=(Button)findViewById(R.id.chooses);
        image=(ImageView)findViewById(R.id.hostel_image);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(OwnerProfileActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        databaseReference=FirebaseDatabase.getInstance().getReference("Owners").child(UserId);

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Owner.class);

                    urll=users.getUri();
                    name.setText(users.getName());
                    adres.setText(users.getAddress());
                    profession.setText(users.getProfessionn());
                    numb1.setText(users.getNumber_1());
                    numb2.setText(users.getNumber_2());
                    numb3.setText(users.getNumber_3());
                    PicassoClient.downloadImage(OwnerProfileActivity.this,users.getUri(),image);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);
    }

    public void uploads(View v) {
        names=name.getText().toString();
        addresss=adres.getText().toString();
        prof=profession.getText().toString();
        numr1=numb1.getText().toString();
        numbr2=numb2.getText().toString();
        numbr3=numb3.getText().toString();

        databaseReference.child("name").setValue(names);
        databaseReference.child("address").setValue(addresss);
        databaseReference.child("number_1").setValue(numr1);
        databaseReference.child("number_2").setValue(numbr2);
        databaseReference.child("number_3").setValue(numbr3);
        databaseReference.child("professionn").setValue(prof);

        if(filepath!=null){
            FirebaseStorage firebaseStorage=FirebaseStorage.getInstance().getReference().getStorage();
            StorageReference photoRef = firebaseStorage.getReferenceFromUrl(urll);

            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(OwnerProfileActivity.this,"Deleted Succesfully",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(OwnerProfileActivity.this,"Deletion failed",Toast.LENGTH_LONG).show();
                }
            });


            storageReference = FirebaseStorage.getInstance().getReference();
            final ProgressDialog progress=new ProgressDialog(this);
            progress.setTitle("uploading.....");
            progress.show();

            StorageReference ref=storageReference.child(FB_STOARGE_PATH + System.currentTimeMillis()+ getImageExt(filepath));
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @SuppressWarnings("VisibleForTests")
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progress.dismiss();

                    names=name.getText().toString();
                    addresss=adres.getText().toString();
                    prof=profession.getText().toString();
                    numr1=numb1.getText().toString();
                    numbr2=numb2.getText().toString();
                    numbr3=numb3.getText().toString();

                    databaseReference.child("name").setValue(names);
                    databaseReference.child("address").setValue(addresss);
                    databaseReference.child("number_1").setValue(numr1);
                    databaseReference.child("number_2").setValue(numbr2);
                    databaseReference.child("number_3").setValue(numbr3);
                    databaseReference.child("professionn").setValue(prof);
                    databaseReference.child("uri").setValue(taskSnapshot.getDownloadUrl().toString());


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(OwnerProfileActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                @SuppressWarnings("VisibleForTests")
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double pro=(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progress.setMessage("uploaded"+(int)pro+"%");
                }
            });
        }else{
            Toast.makeText(OwnerProfileActivity.this,"Succesfully complete",Toast.LENGTH_LONG).show();
        }
    }
    public void chosen(View view) {
        Intent gallery =
                new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
            filepath=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                image.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String getImageExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void clean(View view){
        image.setImageResource(0);
    }
}
