package com.education.imagefire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.education.imagefire.R.drawable.hostel;
import static com.education.imagefire.R.id.e1;


public class RoomsActivity extends AppCompatActivity {
    Button upload,choose,next;
    private EditText name;
    private EditText latitude;
    private EditText longitude;
    private ImageView image1111,image2,image3,image4,image5;
    private Uri filepath;
    private StorageReference storeReference;
    private DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="RoomsInfo/";
    public static final String FB_DATABASE_PATH="RoomsInfo";
    ArrayList<Uri> myOwn=new ArrayList<Uri>();
    String key;
    int PICK_IMAGE_MULTIPLE = 1;
    String imageEncoded;
    List<String> imagesEncodedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
//        image1111 = (ImageView) findViewById(R.id.image111);
//        image2 = (ImageView) findViewById(R.id.image12);
//       // image3 = (ImageView) findViewById(R.id.image13);
//        //image4 = (ImageView) findViewById(R.id.image14);
//        //image5 = (ImageView) findViewById(R.id.image15);
//        name = (EditText) findViewById(e1);
//        latitude = (EditText) findViewById(R.id.e2);
//        longitude = (EditText) findViewById(R.id.e3);
//        upload=(Button)findViewById(R.id.upload);
//        choose=(Button)findViewById(R.id.choose);
//        next=(Button)findViewById(R.id.nextt);
//
//        //key=getIntent().getStringExtra("id");
//
//        storeReference = FirebaseStorage.getInstance().getReference();
//        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
//
//
//
//        Permission.checkPermission(this);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for(int i=0;i<myOwn.size();i++) {
//                    String num=myOwn.get(i).toString();
//                    Toast.makeText(RoomsActivity.this, "please 3 and " +num, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//    public void upload(View v) {
//        if(filepath!=null){
//            final ProgressDialog progress=new ProgressDialog(this);
//            progress.setTitle("uploading.....");
//            progress.show();
//
//            StorageReference ref=storeReference.child(FB_STOARGE_PATH + System.currentTimeMillis()+ getImageExt(filepath));
//            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @SuppressWarnings("VisibleForTests")
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progress.dismiss();
//                    Toast.makeText(RoomsActivity.this,"Uploaded",Toast.LENGTH_LONG).show();
//                    String id=key;
//                    String nam=name.getText().toString();
//                    double logitude=Double.parseDouble(longitude.getText().toString());
//                    double latitud=Double.parseDouble(latitude.getText().toString());
//
//                    Map maping=new Map(id,nam,latitud,logitude,taskSnapshot.getDownloadUrl().toString());
//                    databaseReference.setValue(maping);
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    progress.dismiss();
//                    Toast.makeText(RoomsActivity.this,"Failed",Toast.LENGTH_LONG).show();
//                }
//            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//
//                @SuppressWarnings("VisibleForTests")
//                @Override
//                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                    double pro=(100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                    progress.setMessage("uploaded"+(int)pro+"%");
//                }
//            });
//        }else{
//            Toast.makeText(RoomsActivity.this,"please select image",Toast.LENGTH_LONG).show();
//        }
    }
//
//
//    public void chooose(View view) {
//        Intent intent=new Intent();
//        intent.setType("Image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Images"),REQUEST_CODE);
//
////        Intent intent = new Intent();
////        intent.setType("image/*");
////        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
////        intent.setAction(Intent.ACTION_GET_CONTENT);
////        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);
//
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE) {
//            if(resultCode==RESULT_OK) {
//                Toast.makeText(RoomsActivity.this, "please 1", Toast.LENGTH_LONG).show();
//                if (data != null && data.getData() != null) {
//                    Uri mImageUri = data.getData();
//                    try {
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);
//                        //Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
//                        image1111.setImageBitmap(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                } else if (data.getClipData() != null) {
//                        Toast.makeText(RoomsActivity.this,"please 2",Toast.LENGTH_LONG).show();
//                    ClipData mClipData = data.getClipData();
//                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
//                    for (int i = 0; i < mClipData.getItemCount(); i++) {
//                        Toast.makeText(RoomsActivity.this,"please 2 and in ",Toast.LENGTH_LONG).show();
//                        ClipData.Item item = mClipData.getItemAt(i);
//                        Uri uri = item.getUri();
//                        String urii=uri.toString();
//                        Toast.makeText(RoomsActivity.this,"please 3 and "+urii,Toast.LENGTH_LONG).show();
//                        mArrayUri.add(uri);
//                        switch (i) {
//
//                            case 0:
//                                try {
//                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                                    image1111.setImageBitmap(bitmap);
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//                            case 1:
//                                try {
//                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                                    image2.setImageBitmap(bitmap);
//                                } catch (FileNotFoundException e) {
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                break;
//
//                        }
//
//                    }
//                    myOwn=mArrayUri;
//                   // Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
//
//                }
//                }
//            }
//
//
//        }
//
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
////            //Toast.makeText(MainActivity.this,"please image 2",Toast.LENGTH_LONG).show();
////            filepath=data.getData();
////
////            try{
////                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
////                //Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
////                image.setImageBitmap(bitmap);
////            }catch (FileNotFoundException e){
////                e.printStackTrace();
////            }
////            catch (IOException e){
////                e.printStackTrace();
////            }
////
////        }
////
////    }
//
//    //    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if(requestCode==REQUEST_CODE && requestCode== RESULT_OK && data !=null&& data.getData()!=null){
////            Toast.makeText(MainActivity.this,"please image 2",Toast.LENGTH_LONG).show();
////                    filepath=data.getData();
////
////            try{
////                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
////                Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
////                I1.setImageBitmap(bitmap);
////            }catch (FileNotFoundException e){
////                e.printStackTrace();
////            }
////            catch (IOException e){
////                e.printStackTrace();
////            }
////
////        }
////    }
//    public String getImageExt(Uri uri){
//        ContentResolver contentResolver=getContentResolver();
//        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
//        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
}
