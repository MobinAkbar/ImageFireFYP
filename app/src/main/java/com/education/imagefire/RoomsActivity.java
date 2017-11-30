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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import static com.education.imagefire.R.id.spin;


public class RoomsActivity extends AppCompatActivity {
    Button upload_r, next_r;
    private EditText t_rooms;
    private EditText e_rooms;
    private EditText t_beds;
    private EditText e_beds;
    private Spinner uni1;
    private Spinner uni2;
    private Spinner uni3;
    private EditText r_1month;
    private EditText r_6month;
    private EditText b_1month;
    private EditText b_6month;


    private StorageReference storeReference;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        upload_r=(Button)findViewById(R.id.upload3);
        next_r=(Button)findViewById(R.id.next3);
        t_rooms=(EditText)findViewById(R.id.total_rooms);
        e_rooms=(EditText)findViewById(R.id.empty_rooms);
        t_beds=(EditText)findViewById(R.id.total_beds);
        e_beds=(EditText)findViewById(R.id.empty_beds);
        r_1month=(EditText)findViewById(R.id.r_prizemont);
        r_6month=(EditText)findViewById(R.id.r_prize6month);
        b_1month=(EditText)findViewById(R.id.b_prize1month);
        b_6month=(EditText)findViewById(R.id.b_prize6month);


        uni1=(Spinner)findViewById(R.id.spin_1);
        uni2=(Spinner)findViewById(R.id.spin_2);
        uni3=(Spinner)findViewById(R.id.spin_3);

        key = getIntent().getStringExtra("id");

        ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.universities, android.R.layout.simple_spinner_item);
        uni1.setAdapter(adapter);
        uni2.setAdapter(adapter);
        uni3.setAdapter(adapter);

        next_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(RoomsActivity.this, PropertyActivity.class);
                in.putExtra("id", key);
                Toast.makeText(RoomsActivity.this, "value is " + key, Toast.LENGTH_LONG).show();
                startActivity(in);
            }
        });

        upload_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });

        //storeReference = FirebaseStorage.getInstance().getReference();
        databaseReference1 = FirebaseDatabase.getInstance().getReference("Rooms");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Hostel_Universities");

    }

    private void upload(){
        String totalRooms=t_rooms.getText().toString();
        String emptyRoos=e_rooms.getText().toString();
        String totalBeds=t_beds.getText().toString();
        String emptybeds=e_beds.getText().toString();
        String rm_1month=r_1month.getText().toString();
        String rm_6month=r_6month.getText().toString();
        String bd_1month=b_1month.getText().toString();
        String bd_6month=b_6month.getText().toString();


        String univrsty1=uni1.getSelectedItem().toString();
        String univrsty2=uni2.getSelectedItem().toString();
        String univrsty3=uni3.getSelectedItem().toString();

        Rooms room=new Rooms(key,totalRooms,emptyRoos,totalBeds,emptybeds,rm_1month,bd_1month,rm_6month,bd_6month);
        databaseReference1.child(key).setValue(room);

        University university=new University(key,univrsty1,univrsty2,univrsty3);
        databaseReference2.child(key).setValue(university);

    }


}