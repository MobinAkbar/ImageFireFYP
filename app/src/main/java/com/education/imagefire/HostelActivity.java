package com.education.imagefire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
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
import static com.education.imagefire.R.id.male;

public class HostelActivity extends AppCompatActivity {

    private EditText name;
    private EditText address;
    private CheckBox male1;
    private CheckBox female1;
    Button upload,choose,clear,next;
    private ImageView image;
    private Uri filepath;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="Hostels/";
    public static final String FB_DATABASE_PATH="Hostels";
    Hostel hostel;
    String ids;
    String gender;
    private String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);

        name=(EditText)findViewById(R.id.name1);
        address=(EditText)findViewById(R.id.addres1);
        image=(ImageView)findViewById(R.id.hostel_image);
        upload=(Button)findViewById(R.id.upload1);
        choose=(Button)findViewById(R.id.choose1);
        //next=(Button)findViewById(R.id.next11);
        male1=(CheckBox)findViewById(R.id.man1);
        female1=(CheckBox)findViewById(R.id.woman1);
        clear=(Button)findViewById(R.id.clear1);

        key=getIntent().getStringExtra("UID");
        Toast.makeText(HostelActivity.this, "value is "+key ,Toast.LENGTH_LONG).show();


        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        Permission.checkPermission(this);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });




//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intt=new Intent(HostelActivity.this,FacilitiesActivity.class);
//                intt.putExtra("id",ids);
//               // Toast.makeText(HostelActivity.this, "value is "+hostel.getId() ,Toast.LENGTH_LONG).show();
//                startActivity(intt);
//            }
//        });
    }

    public void upload(View v) {

        String hostel_name=name.getText().toString().trim();
        String hostel_address=address.getText().toString().trim();


        if(male1.isChecked()==true){
            gender="male";
        }else if(female1.isChecked()==true){
            gender="female";
        }

        if (male1.isChecked()&&female1.isChecked()) {
            Toast.makeText(getApplicationContext(), "Select one!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(hostel_name)) {
            Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(hostel_address)) {
            Toast.makeText(getApplicationContext(), "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        }

        if(filepath!=null){
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
                    Toast.makeText(HostelActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
                    String id=databaseReference.push().getKey();
                    String owner=key;
                    String hostel_name=name.getText().toString().trim();
                    String hostel_address=address.getText().toString().trim();
                    String statuses="NOT APPROVED";
                    double lat=12.2345;
                    double longo=23.2345;
                    int likes=0;

                    ids=id;
                    Toast.makeText(HostelActivity.this,"I HAVE"+ids,Toast.LENGTH_SHORT).show();

                    Hostel hostel=new Hostel(id,owner,hostel_name,hostel_address,taskSnapshot.getDownloadUrl().toString(),statuses,gender,lat,longo,likes);
                    databaseReference.child(id).setValue(hostel);

                    Intent intt=new Intent(HostelActivity.this,FacilitiesActivity.class);
                    intt.putExtra("id",ids);
                    startActivity(intt);

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
            Toast.makeText(HostelActivity.this,"Please select image",Toast.LENGTH_LONG).show();
        }
    }

    private void openGallery() {
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
    public void allclear(View view){
        image.setImageResource(0);
    }


    @Override
    public void onBackPressed() {
            Toast.makeText(this, "Upload Next.",
                    Toast.LENGTH_SHORT).show();


    }
}