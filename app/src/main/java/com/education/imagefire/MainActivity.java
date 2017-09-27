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

    Button b1,b2;
    private EditText E1;
    private EditText E2;
    private EditText E3;
    private ImageView I1;
    private Uri filepath;
    private StorageReference storeReference;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="Image/";
    public static final String FB_DATABASE_PATH="Image";

    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        I1 = (ImageView) findViewById(R.id.image);
        E1 = (EditText) findViewById(R.id.e1);
        E2 = (EditText) findViewById(R.id.e2);
        E3 = (EditText) findViewById(R.id.e3);

        b1=(Button)findViewById(R.id.nxt);
        b2=(Button)findViewById(R.id.search);

        storeReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        Permission.checkPermission(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(MainActivity.this,CarActivity.class);
                startActivity(o);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(o);
            }
        });


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

            StorageReference ref=storeReference.child(FB_STOARGE_PATH + System.currentTimeMillis()+ getImageExt(filepath));
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @SuppressWarnings("VisibleForTests")
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progress.dismiss();
                    Toast.makeText(MainActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                     double logitude=Double.parseDouble(E2.getText().toString());
                     double latitude=Double.parseDouble(E3.getText().toString());

                    ImageUpload imageUpload=new ImageUpload(E1.getText().toString(),logitude,latitude,taskSnapshot.getDownloadUrl().toString());

                    String uploadId=databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(imageUpload);


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
                }
            });
        }else{
            Toast.makeText(MainActivity.this,"please select image",Toast.LENGTH_LONG).show();
        }
    }


    public void chooose(View view) {
        Intent intent=new Intent();
        intent.setType("Image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Images"),REQUEST_CODE);
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
                I1.setImageBitmap(bitmap);
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
