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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

//import static com.education.imagefire.R.id.numb1;

public class UserInfoActivity extends AppCompatActivity {

    Button upload,choose,next;
    private EditText name;
    private EditText number;
    private EditText address;
    private EditText age;
    private EditText university;
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
    public static final String FB_STOARGE_PATH="Users_Info/";
    public static final String FB_DATABASE_PATH="Users_Info";
    public static final String FB_DATABASE_PATH1="Users_Type";
    String email;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        image = (ImageView) findViewById(R.id.image);
        name = (EditText) findViewById(R.id.e1);
        number = (EditText) findViewById(R.id.e2);
        address = (EditText) findViewById(R.id.e3);
        age = (EditText) findViewById(R.id.e4);
        university = (EditText) findViewById(R.id.e5);
        upload=(Button)findViewById(R.id.upload);
        choose=(Button)findViewById(R.id.choose);
        next=(Button)findViewById(R.id.next);

        email=getIntent().getStringExtra("email");
        password=getIntent().getStringExtra("password");

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
                    Toast.makeText(UserInfoActivity.this,"Id have something"+UserId,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UserInfoActivity.this,"Id is empty",Toast.LENGTH_SHORT).show();
                }
            }
        };



        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        //databaseReference1 = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH1);

        Permission.checkPermission(this);

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent in=new Intent(UserInfoActivity.this,SearchActivity.class);
//                in.putExtra("UID",UserId);
//                startActivity(in);
//            }
//        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(stateListener);

        databaseReference1 = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH1).child(UserId);
               String type="student";
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
                    Toast.makeText(UserInfoActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                    String id=UserId;
                    String deviceToken= FirebaseInstanceId.getInstance().getToken();
                    String name1=name.getText().toString().trim();
                    String email1=email;
                    String password1=password;
                    String number1=number.getText().toString().trim();
                    String gender1=age.getText().toString().trim();
                    String address1=address.getText().toString().trim();
                    String uni1=university.getText().toString().trim();

                    Users user=new Users(id,name1,email1,password1,number1,gender1,address1,uni1,taskSnapshot.getDownloadUrl().toString(),deviceToken);
                    databaseReference.child(id).setValue(user);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(UserInfoActivity.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                @SuppressWarnings("VisibleForTests")
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double pro=(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progress.setMessage("uploaded"+(int)pro+"%");

                    Intent in=new Intent(UserInfoActivity.this,SearchActivity.class);
                    in.putExtra("UID",UserId);
                    startActivity(in);

                }
            });
        }else{
            Toast.makeText(UserInfoActivity.this,"please select image",Toast.LENGTH_LONG).show();
        }
    }


    public void chooose(View view) {
        Intent intent=new Intent();
        intent.setType("Owners/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Owners"),REQUEST_CODE);
        Toast.makeText(UserInfoActivity.this,"please image 3",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
            Toast.makeText(UserInfoActivity.this,"please image 2",Toast.LENGTH_LONG).show();
            filepath=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                Toast.makeText(UserInfoActivity.this,"please image",Toast.LENGTH_LONG).show();
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

}
