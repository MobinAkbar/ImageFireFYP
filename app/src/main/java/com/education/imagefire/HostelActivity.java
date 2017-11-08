package com.education.imagefire;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

import static android.os.Build.VERSION_CODES.M;

public class HostelActivity extends AppCompatActivity {

    private EditText name;
    Button upload,choose,next;
    private ImageView image;
    private Uri filepath;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="Hostels/";
    public static final String FB_DATABASE_PATH="Hostels";
    Hostel hostel;
    String ids;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);

        name=(EditText)findViewById(R.id.e1);
        image=(ImageView)findViewById(R.id.image);
        upload=(Button)findViewById(R.id.upload);
        choose=(Button)findViewById(R.id.choose);
        next=(Button)findViewById(R.id.next);

        key=getIntent().getStringExtra("UID");
        Toast.makeText(HostelActivity.this, "value is "+key ,Toast.LENGTH_LONG).show();

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        Permission.checkPermission(this);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(HostelActivity.this,HostelInfoActivity.class);
                //in.putExtra("id",databaseReference.getKey());
                //in.putExtra("id",hostel.getId());
                in.putExtra("id",ids);
                //String id=databaseReference.getKey();
                //String key = databaseReference.push().getKey();
                Toast.makeText(HostelActivity.this, "value is "+hostel.getId() ,Toast.LENGTH_LONG).show();
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

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot sp: dataSnapshot.getChildren()){
                    Hostel per=sp.getValue(Hostel.class);
                    hostel=per;
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

            StorageReference ref=storageReference.child(FB_STOARGE_PATH + System.currentTimeMillis()+ getImageExt(filepath));
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @SuppressWarnings("VisibleForTests")
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progress.dismiss();
                    Toast.makeText(HostelActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                    String id=databaseReference.push().getKey();
                    String owner=key;
                    String owner_name=name.getText().toString().trim();
                    ids=id;

                    //double logitude=Double.parseDouble(E2.getText().toString());
                    //double latitude=Double.parseDouble(E3.getText().toString());

                    Hostel hostel=new Hostel(id,owner,owner_name,taskSnapshot.getDownloadUrl().toString());

                    //String uploadId=databaseReference.push().getKey();
                    databaseReference.child(id).setValue(hostel);


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(HostelActivity.this,"Failed",Toast.LENGTH_LONG).show();
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
            Toast.makeText(HostelActivity.this,"please select image",Toast.LENGTH_LONG).show();
        }
    }


    public void chooose(View view) {
        Intent intent=new Intent();
        intent.setType("Hostels/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Hostels"),REQUEST_CODE);
        Toast.makeText(HostelActivity.this,"please image 3",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
            Toast.makeText(HostelActivity.this,"please image 2",Toast.LENGTH_LONG).show();
            filepath=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                Toast.makeText(HostelActivity.this,"please image",Toast.LENGTH_LONG).show();
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

