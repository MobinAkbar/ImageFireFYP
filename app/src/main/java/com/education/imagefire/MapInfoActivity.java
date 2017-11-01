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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MapInfoActivity extends AppCompatActivity {

    Button upload,choose,next;
    private EditText latitude;
    private EditText longitude;
    private ImageView image;
    private Uri filepath;
    private StorageReference storeReference;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="MapsInfo/";
    public static final String FB_DATABASE_PATH="MapsInfo";
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_info);

        image = (ImageView) findViewById(R.id.image);
        latitude = (EditText) findViewById(R.id.e1);
        longitude = (EditText) findViewById(R.id.e2);
        upload=(Button)findViewById(R.id.upload);
        choose=(Button)findViewById(R.id.choose);
        next=(Button)findViewById(R.id.nxt);
        //b2=(Button)findViewById(R.id.search);

        key=getIntent().getStringExtra("id");

        storeReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH).child(key);

        Permission.checkPermission(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(MapInfoActivity.this,Owner_PortalActivity.class);
                o.putExtra("id",key);
                startActivity(o);
            }
        });
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent o=new Intent(MainActivity.this,SearchActivity.class);
//                startActivity(o);
//            }
//        });


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
                    Toast.makeText(MapInfoActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                    double logitude=Double.parseDouble(longitude.getText().toString());
                    double latitud=Double.parseDouble(latitude.getText().toString());

                    Map maping=new Map(latitud,logitude,taskSnapshot.getDownloadUrl().toString());

                    //String uploadId=databaseReference.push().getKey();
                    databaseReference.setValue(maping);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(MapInfoActivity.this,"Failed",Toast.LENGTH_LONG).show();
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
            Toast.makeText(MapInfoActivity.this,"please select image",Toast.LENGTH_LONG).show();
        }
    }


    public void chooose(View view) {
        Intent intent=new Intent();
        intent.setType("Image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Images"),REQUEST_CODE);
        //Toast.makeText(MainActivity.this,"please image 3",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
            //Toast.makeText(MainActivity.this,"please image 2",Toast.LENGTH_LONG).show();
            filepath=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                //Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
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

