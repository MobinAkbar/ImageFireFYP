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
import android.text.TextUtils;
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
import com.google.firebase.iid.FirebaseInstanceId;
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
    private EditText name;
    private EditText adres;
    private EditText numb1;
    private EditText numb2;
    private EditText numb3;
    private EditText profesion;
    private ImageView image;
    private Uri filepath;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private String UserId;
    private FirebaseAuth.AuthStateListener stateListener;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="Owners/";
    public static final String FB_DATABASE_PATH="Owners";
    Owner owner;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email=getIntent().getStringExtra("email");
        password=getIntent().getStringExtra("password");

        image = (ImageView) findViewById(R.id.image);
        adres=(EditText)findViewById(R.id.e1p);
        name = (EditText) findViewById(R.id.e1);
        numb1 = (EditText) findViewById(R.id.e2);
        numb2 = (EditText) findViewById(R.id.e3);
        numb3 = (EditText) findViewById(R.id.e4);
        profesion = (EditText) findViewById(R.id.e5);
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
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String owner_name=name.getText().toString().trim();
                String number1=numb1.getText().toString().trim();
                String profession=profesion.getText().toString().trim();

                if (TextUtils.isEmpty(owner_name)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(number1)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(profession)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                upload();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,Owner_PortalActivity.class);
                in.putExtra("UID",UserId);
                startActivity(in);

            }
        });
    }
        @Override
        protected void onStart() {
            super.onStart();
            auth.addAuthStateListener(stateListener);

            databaseReference1 = FirebaseDatabase.getInstance().getReference("Users_Type").child(UserId);
            String type="owner";
            UserType userType=new UserType(UserId,type);
            databaseReference1.setValue(userType);
        }


    @Override
    protected void onStop() {
        super.onStop();
        if (stateListener != null) {
            auth.removeAuthStateListener(stateListener);
        }
    }
    public void upload() {

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
                    String deviceToken= FirebaseInstanceId.getInstance().getToken();
                    String owner_name=name.getText().toString().trim();
                    String adress=adres.getText().toString();
                    String number1=numb1.getText().toString().trim();
                    String number2=numb2.getText().toString().trim();
                    String number3=numb3.getText().toString().trim();
                    String email1=email;
                    String password1=password;
                    String profession=profesion.getText().toString().trim();

                    Owner owner=new Owner(id,owner_name,adress,number1,number2,number3,email1,password1,profession,taskSnapshot.getDownloadUrl().toString(),deviceToken);
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
//                    Intent in=new Intent(MainActivity.this,Owner_PortalActivity.class);
//                    in.putExtra("UID",UserId);
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
    public String getImageExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void clean(View view){
        image.setImageResource(0);
    }

}
