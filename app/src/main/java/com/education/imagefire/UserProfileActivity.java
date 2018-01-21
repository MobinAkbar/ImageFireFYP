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
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static com.education.imagefire.R.id.address;
//import static com.education.imagefire.R.id.imageView;
import static com.education.imagefire.R.id.location;
//import static com.education.imagefire.R.id.name20;
import static com.education.imagefire.R.id.security;

public class UserProfileActivity extends AppCompatActivity {


    TextView name,adres,numb,university,gender;
    Button B1,B2,B3;
    ImageView image1;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    String UserId;
    Users users;
    private Uri filepath;
    public static final int REQUEST_CODE=1234;
    String namess,addresss,numr,school,sexy;
    private String urll;
    public static final String FB_STOARGE_PATH="Users_Info/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name=(TextView)findViewById(R.id.namez);
        adres=(TextView)findViewById(R.id.adresz);
        numb=(TextView)findViewById(R.id.numz);
        university=(TextView)findViewById(R.id.uniz);
        gender=(TextView)findViewById(R.id.sexz);
        B1=(Button)findViewById(R.id.savesz);
        B2=(Button)findViewById(R.id.removesz);
        B3=(Button)findViewById(R.id.choosesz);
        image1=(ImageView)findViewById(R.id.hostel_image);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(UserProfileActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        databaseReference=FirebaseDatabase.getInstance().getReference("Users_Info").child(UserId);

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Users_Info").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Users.class);
                    urll=users.getUri();
                    PicassoClient.downloadImage(UserProfileActivity.this,users.getUri(),image1);
                    name.setText(users.getName());
                    adres.setText(users.getAdress());
                    numb.setText(users.getNumber());
                    university.setText(users.getUniversity());
                    gender.setText(users.getSex());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);
    }
    public void uploads(View v) {
        namess=name.getText().toString();
        addresss=adres.getText().toString();
        numr=numb.getText().toString();
        school=university.getText().toString();
        sexy=gender.getText().toString();

        databaseReference.child("name").setValue(namess);
        databaseReference.child("adress").setValue(addresss);
        databaseReference.child("number").setValue(numr);
        databaseReference.child("university").setValue(school);
        databaseReference.child("sex").setValue(sexy);

//        FirebaseStorage firebaseStorage=FirebaseStorage.getInstance().getReference().getStorage();
//        StorageReference photoRef = firebaseStorage.getReferenceFromUrl(urll);
//
//        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(OwnerProfileActivity.this,"Deleted Succesfully",Toast.LENGTH_LONG).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(OwnerProfileActivity.this,"Deletion failed",Toast.LENGTH_LONG).show();
//            }
//        });

        if(filepath!=null){
            FirebaseStorage firebaseStorage=FirebaseStorage.getInstance().getReference().getStorage();
            StorageReference photoRef = firebaseStorage.getReferenceFromUrl(urll);

            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(UserProfileActivity.this,"Deleted Succesfully",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(UserProfileActivity.this,"Deletion failed",Toast.LENGTH_LONG).show();
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

                    namess=name.getText().toString();
                    addresss=adres.getText().toString();
                    numr=numb.getText().toString();
                    school=university.getText().toString();
                    sexy=gender.getText().toString();

                    databaseReference.child("name").setValue(namess);
                    databaseReference.child("adress").setValue(addresss);
                    databaseReference.child("number").setValue(numr);
                    databaseReference.child("university").setValue(school);
                    databaseReference.child("sex").setValue(sexy);
                    databaseReference.child("uri").setValue(taskSnapshot.getDownloadUrl().toString());


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(UserProfileActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                @SuppressWarnings("VisibleForTests")
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double pro=(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progress.setMessage("uploaded"+(int)pro+"%");
                    Intent intent=new Intent(UserProfileActivity.this,SearchActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            Toast.makeText(UserProfileActivity.this,"Succesfully complete",Toast.LENGTH_LONG).show();
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
                image1.setImageBitmap(bitmap);
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
        image1.setImageResource(0);
    }
}
