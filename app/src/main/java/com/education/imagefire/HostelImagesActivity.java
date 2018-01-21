package com.education.imagefire;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import static android.media.CamcorderProfile.get;
import static com.education.imagefire.R.drawable.hostel;

public class HostelImagesActivity extends AppCompatActivity {

    Button upload,next,clear,chose;
    private ImageView image1,image2,image3,image4,image5,image6;
    private Uri filepath1;
    private StorageReference storeReference;
    DatabaseReference databaseReference;
    public static final int REQUEST_CODE=1234;
    public static final String FB_STOARGE_PATH="AllHostelImages/";
    public static final String FB_DATABASE_PATH="AllHostelImages";
    ArrayList<Uri> myOwn=new ArrayList<Uri>();
    ArrayList<String > array=new ArrayList<String>();
    ArrayList<String> myOwn2=new ArrayList<String>();
    ArrayList<String> myOwn3=new ArrayList<String>();
    private String[] title=new String[5];
    String key;
    private String ones,twos,threes,fours,fives,sixes;
    private String Aones,Atwos,Athrees,Afours,Afives,Asixes;
    int PICK_IMAGE_MULTIPLE = 1;

    String imageEncoded;
    List<String> imagesEncodedList;
    String id;
    private Handler mHandler = new Handler();
    Button b109;
    private int ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_images);

            upload=(Button)findViewById(R.id.upload7);
            chose=(Button)findViewById(R.id.choose7);
            next=(Button)findViewById(R.id.next7);
            clear=(Button)findViewById(R.id.clear7);
            //b109=(Button)findViewById(R.id.print);
        image1=(ImageView)findViewById(R.id.image_h1);
        image2=(ImageView)findViewById(R.id.image_h2);
        image3=(ImageView)findViewById(R.id.image_h3);
        image4=(ImageView)findViewById(R.id.image_h4);
        image5=(ImageView)findViewById(R.id.image_h5);
        image6=(ImageView)findViewById(R.id.image_h6);

           key=getIntent().getStringExtra("id");
           id=key;

        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH).child(id);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int size=array.size();

                if(size==0){
                    String id=key;
                    Aones="";
                    Atwos="";
                    Athrees="";
                    Afours="";
                    Afives="";
                    Asixes="";

                    HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                    databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                            startActivity(in);
                        }
                    });

                    return;
                }
                if(size==1){
                    String id=key;
                    Aones=array.get(0);
                    Atwos="";
                    Athrees="";
                    Afours="";
                    Afives="";
                    Asixes="";

                    HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                    databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                            startActivity(in);
                        }
                    });

                    return;
                }
                if(size==2){
                    String id=key;
                    Aones=array.get(0);
                    Atwos=array.get(1);
                    Athrees="";
                    Afours="";
                    Afives="";
                    Asixes="";

                    HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                    databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                            startActivity(in);
                        }
                    });

                    return;
                }

                if(size==3){
                    String id=key;
                    Aones=array.get(0);
                    Atwos=array.get(1);
                    Athrees=array.get(2);
                    Afours="";
                    Afives="";
                    Asixes="";

                    HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                    databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                            startActivity(in);
                        }
                    });

                    return;
                }
                if(size==4){
                    String id=key;
                    Aones=array.get(0);
                    Atwos=array.get(1);
                    Athrees=array.get(2);
                    Afours=array.get(3);
                    Afives="";
                    Asixes="";

                    HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                    databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                            startActivity(in);
                        }
                    });

                    return;

                }
                if(size==5){
                    String id=key;
                    Aones=array.get(0);
                    Atwos=array.get(1);
                    Athrees=array.get(2);
                    Afours=array.get(3);
                    Afives=array.get(4);
                    Asixes="";
                    HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                    databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                            startActivity(in);
                        }
                    });

                    return;
                }

                Aones=array.get(0);
                Atwos=array.get(1);
                Athrees=array.get(2);
                Afours=array.get(3);
                Afives=array.get(4);
                Asixes=array.get(5);

                HostelImages hostelImages=new HostelImages(id,Aones,Atwos,Athrees,Afours,Afives,Asixes);
                databaseReference.setValue(hostelImages).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent in=new Intent(HostelImagesActivity.this,Owner_PortalActivity.class);
                        startActivity(in);
                    }
                });
            }
        });
    }

    public void upload(View v) {
        storeReference = FirebaseStorage.getInstance().getReference();
        final ProgressDialog progress=new ProgressDialog(this);
        progress.setTitle("uploading.....");
        progress.show();

        for(int ip=0;ip<myOwn.size();ip++) {
             filepath1=myOwn.get(ip);
            if (filepath1 != null) {
                Toast.makeText(HostelImagesActivity.this,"I have"+ip,Toast.LENGTH_SHORT).show();
                StorageReference ref = storeReference.child(FB_STOARGE_PATH + System.currentTimeMillis() + getImageExt(filepath1));
                ref.putFile(filepath1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progress.dismiss();
                        Uri final_uri=taskSnapshot.getDownloadUrl();
                        String link=final_uri.toString();
                        Toast toast = new Toast(HostelImagesActivity.this);
                        toast.setGravity(Gravity.CENTER, 30, 30);
                        TextView textView=new TextView(HostelImagesActivity.this);
                        textView.setText("Chechking images completion");
                        textView.setBackgroundColor(Color.BLUE);
                        toast.setView(textView);
                        toast.show();

                        array.add(link);
                       // title[ip]=link;

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progress.dismiss();
                        Toast.makeText(HostelImagesActivity.this, "Failed", Toast.LENGTH_LONG).show();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double pro = (100 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progress.setMessage("uploaded" + (int) pro + "%");
                    }
                });
            } else {
                Toast.makeText(HostelImagesActivity.this, "please select image", Toast.LENGTH_LONG).show();
            }
        }

    }
    public void chooose(View view) {
        Intent intent=new Intent();
        intent.setType("Image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Images"),REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if(resultCode==RESULT_OK) {
                Toast.makeText(HostelImagesActivity.this, "Select 6 image", Toast.LENGTH_LONG).show();
                if (data != null && data.getData() != null) {
                    Uri mImageUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mImageUri);
                        //Toast.makeText(MainActivity.this,"please image",Toast.LENGTH_LONG).show();
                        image1.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (data.getClipData() != null) {
                    //Toast.makeText(HostelImagesActivity.this,"please 2",Toast.LENGTH_LONG).show();
                    ClipData mClipData = data.getClipData();
                    ArrayList<Uri> mArrayUri = new ArrayList<Uri>();
                    for (int i = 0; i < mClipData.getItemCount(); i++) {
                        ClipData.Item item = mClipData.getItemAt(i);
                        Uri uri = item.getUri();
                        String urii = uri.toString();

                        myOwn2.add(urii);
                        mArrayUri.add(uri);

                        switch (i) {

                            case 0:
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    image1.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 1:
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    image2.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 2:
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    image3.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 3:
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    image4.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 4:
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    image5.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case 5:
                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                                    image6.setImageBitmap(bitmap);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break;

                        }

                    }
                    myOwn3=myOwn2;
                    myOwn=mArrayUri;
                   // Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                }
                }
            }


        }

    public String getImageExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
    public void allclear(View view){
        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
    }
}
