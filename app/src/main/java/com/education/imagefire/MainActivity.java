package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import java.net.URI;
import java.net.URL;
import java.util.UUID;

import static java.lang.Double.parseDouble;

public class MainActivity extends AppCompatActivity {

    Button upload,choose,next;
   // EditText name,numb1,numb2,email,descption;
    //private ImageView image;
    //DatabaseReference databaseReference;
    //Uri filepath;
    //StorageReference storageReference;
    private EditText name;
    private EditText numb1;
    private EditText numb2;
    private EditText email;
    private EditText password;
    private ImageView image;
    private Uri filepath;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private String UserId;
    private FirebaseAuth.AuthStateListener stateListener;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="Owners/";
    public static final String FB_DATABASE_PATH="Owners";
    Owner owner;

    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        name = (EditText) findViewById(R.id.e1);
        numb1 = (EditText) findViewById(R.id.e2);
        numb2 = (EditText) findViewById(R.id.e3);
        email = (EditText) findViewById(R.id.e4);
        password = (EditText) findViewById(R.id.e5);
        upload=(Button)findViewById(R.id.upload);
        choose=(Button)findViewById(R.id.choose);
        next=(Button)findViewById(R.id.next);
        //databaseReference= FirebaseDatabase.getInstance().getReference("Owners");

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        FirebaseUser user=auth.getCurrentUser();
        UserId=user.getUid();

        stateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(MainActivity.this,"Id have something"+UserId,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Id is empty",Toast.LENGTH_SHORT).show();
                }
            }
        };



        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        Permission.checkPermission(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,Owner_PortalActivity.class);
                //in.putExtra("id",databaseReference.getKey());
                in.putExtra("UID",UserId);
                //String id=databaseReference.getKey();
                //String key = databaseReference.push().getKey();
//                Toast.makeText(MainActivity.this, "value is "+owner.getId() ,Toast.LENGTH_LONG).show();
                startActivity(in);

            }
        });
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent o=new Intent(MainActivity.this,CarActivity.class);
//                startActivity(o);
//            }
//        });
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent o=new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(o);
//            }
//        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot sp: dataSnapshot.getChildren()){
//                    Owner per=sp.getValue(Owner.class);
//                    owner=per;
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//
//
//
//
//    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(stateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (stateListener != null) {
            auth.removeAuthStateListener(stateListener);
        }
    }



       // progressDialog=new ProgressDialog(this);

//        B1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                upload();
//            }
//        });
//
//        B2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chooose();
//            }
//        });
//    }
// @SuppressWarnings("VisibleForTests")
    public void upload(View v) {

        if(filepath!=null){
            final ProgressDialog progress=new ProgressDialog(this);
            progress.setTitle("uploading.....");
            progress.show();

            StorageReference ref=storageReference.child(FB_STOARGE_PATH + System.currentTimeMillis()+ getImageExt(filepath));
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @SuppressWarnings("VisibleForTests")
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progress.dismiss();
                    Toast.makeText(MainActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                    String id=UserId;
                    String owner_name=name.getText().toString().trim();
                    String number1=numb1.getText().toString().trim();
                    String number2=numb2.getText().toString().trim();
                    String mail=email.getText().toString().trim();
                    String passw=password.getText().toString().trim();

                     //double logitude=Double.parseDouble(E2.getText().toString());
                     //double latitude=Double.parseDouble(E3.getText().toString());

                    Owner owner=new Owner(id,owner_name,number1,number2,mail,passw,taskSnapshot.getDownloadUrl().toString());

                    //String uploadId=databaseReference.push().getKey();
                    databaseReference.child(id).setValue(owner);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                @SuppressWarnings("VisibleForTests")
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                   double pro=(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progress.setMessage("uploaded"+(int)pro+"%");
                   // Intent in=new Intent(MainActivity.this,Owner_PortalActivity.class);
//                    startActivity(in);
                }
            });
        }else{
            Toast.makeText(MainActivity.this,"please select image",Toast.LENGTH_LONG).show();
        }
    }


    public void chooose(View view) {
        Intent intent=new Intent();
        intent.setType("Owners/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Owners"),REQUEST_CODE);
        Toast.makeText(MainActivity.this,"please image 3",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
            Toast.makeText(MainActivity.this,"please image 2",Toast.LENGTH_LONG).show();
                    filepath=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
                image.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==REQUEST_CODE && requestCode== RESULT_OK && data !=null&& data.getData()!=null){
//            Toast.makeText(MainActivity.this,"please image 2",Toast.LENGTH_LONG).show();
//                    filepath=data.getData();
//
//            try{
//                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
//                Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
//                I1.setImageBitmap(bitmap);
//            }catch (FileNotFoundException e){
//                e.printStackTrace();
//            }
//            catch (IOException e){
//                e.printStackTrace();
//            }
//
//        }
//    }
    public String getImageExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

}
