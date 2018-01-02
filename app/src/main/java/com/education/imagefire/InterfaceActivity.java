package com.education.imagefire;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Property;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class InterfaceActivity extends AppCompatActivity {

    TextView     name,adress,e_room,e_bed,t_room,t_bed,m_r_price,m_b_price,s_r_price,s_b_price,
                 p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,prop;
    CheckBox f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    ImageView i1,i2,i3,i4,i5,i6,img;
    Dialog myDialog;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId;
    private Rooms rooms;
    private Facilities facilities;
    private PropertyInfo property;
    private HostelImages hostelImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(InterfaceActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        final String hostel_id=getIntent().getStringExtra("Hostelid");
        String hostel_name=getIntent().getStringExtra("Hostelname");
        String hostel_url=getIntent().getStringExtra("Hosteluri");
        String hostel_type=getIntent().getStringExtra("Hosteltype");
        String hostel_likes=getIntent().getStringExtra("Hostellikes");
        String hostel_adres=getIntent().getStringExtra("Hosteladdress");

        name=(TextView) findViewById(R.id.nam1e);
        adress=(TextView)findViewById(R.id.addres1s);
        img=(ImageView)findViewById(R.id.image);
        prop=(TextView)findViewById(R.id.pro1);

        i1=(ImageView)findViewById(R.id.im1);i2=(ImageView)findViewById(R.id.im2);
        i3=(ImageView)findViewById(R.id.im3);i4=(ImageView)findViewById(R.id.im4);
        i5=(ImageView)findViewById(R.id.im5);i6=(ImageView)findViewById(R.id.im6);

        e_room=(TextView)findViewById(R.id.e_r); e_bed=(TextView)findViewById(R.id.e_b);
        t_room=(TextView)findViewById(R.id.t_r); t_bed=(TextView)findViewById(R.id.t_b);
        m_r_price=(TextView)findViewById(R.id.m_r_room); m_b_price=(TextView)findViewById(R.id.m_b_beds);
        s_r_price=(TextView)findViewById(R.id.s_r_room); s_b_price=(TextView)findViewById(R.id.s_b_beds);

        f1=(CheckBox) findViewById(R.id.fa1);f2=(CheckBox) findViewById(R.id.fa2);
        f3=(CheckBox) findViewById(R.id.fa3);f4=(CheckBox) findViewById(R.id.fa4);
        f5=(CheckBox) findViewById(R.id.fa5);f6=(CheckBox) findViewById(R.id.fa6);
        f7=(CheckBox) findViewById(R.id.fa7);f8=(CheckBox) findViewById(R.id.fa8);
        f9=(CheckBox) findViewById(R.id.fa9);f10=(CheckBox) findViewById(R.id.fa10);

        p1=(TextView)findViewById(R.id.pl1);p2=(TextView)findViewById(R.id.pl2);
        p3=(TextView)findViewById(R.id.pl3);p4=(TextView)findViewById(R.id.pl4);
        p5=(TextView)findViewById(R.id.pl5);p6=(TextView)findViewById(R.id.pl6);
        p7=(TextView)findViewById(R.id.pl7);p8=(TextView)findViewById(R.id.pl8);
        p9=(TextView)findViewById(R.id.pl9); p10=(TextView)findViewById(R.id.pl10);

        b1=(Button)findViewById(R.id.hy);b2=(Button)findViewById(R.id.hy2);
        b3=(Button)findViewById(R.id.hy3);b4=(Button)findViewById(R.id.hy4);
        b5=(Button)findViewById(R.id.hy5);b6=(Button)findViewById(R.id.hy6);
        b7=(Button)findViewById(R.id.hy7);b8=(Button)findViewById(R.id.hy8);
        b9=(Button)findViewById(R.id.hy9);b10=(Button)findViewById(R.id.hy10);


        PicassoClient.downloadImage(InterfaceActivity.this,hostel_url,img);
        name.setText(hostel_name);
        adress.setText(hostel_adres);

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("Rooms").orderByChild("id").equalTo(hostel_id);

        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    rooms=ds.getValue(Rooms.class);
                    t_room.setText(rooms.getTotal_rooms());
                    t_bed.setText(rooms.getTotal_beds());
                    e_room.setText(rooms.getEmpty_rooms());
                    e_bed.setText(rooms.getEmpty_beds());
                    m_r_price.setText(rooms.getR_monthprize());
                    m_b_price.setText(rooms.getB_monthprize());
                    s_r_price.setText(rooms.getR_sixmonth());
                    s_b_price.setText(rooms.getB_sixmonth());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);

        DatabaseReference mFirebaseDatabaseReference2 = FirebaseDatabase.getInstance().getReference();
        Query query2 = mFirebaseDatabaseReference2.child("Facilities").orderByChild("id").equalTo(hostel_id);

        final ValueEventListener eventListener2=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    facilities=ds.getValue(Facilities.class);
                    if (facilities.getWifi().equals("yes")) {


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query2.addValueEventListener(eventListener2);

        DatabaseReference mFirebaseDatabaseReference3 = FirebaseDatabase.getInstance().getReference();
        Query query3 = mFirebaseDatabaseReference3.child("Facilities").orderByChild("id").equalTo(hostel_id);

        final ValueEventListener eventListener3=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    property=ds.getValue(PropertyInfo.class);
                    p1.setText(property.getNearby_place1());
                    p2.setText(property.getNearby_place2());
                    p3.setText(property.getNearby_place3());
                    p4.setText(property.getNearby_place4());
                    p5.setText(property.getNearby_place5());
                    p6.setText(property.getNearby_place6());
                    p7.setText(property.getNearby_place7());
                    p8.setText(property.getNearby_place8());
                    p9.setText(property.getNearby_place9());
                    p10.setText(property.getNearby_place10());
                    prop.setText(property.getProperty());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query3.addValueEventListener(eventListener3);

        DatabaseReference mFirebaseDatabaseReference4 = FirebaseDatabase.getInstance().getReference();
        Query query4 = mFirebaseDatabaseReference4.child("Facilities").orderByChild("id").equalTo(hostel_id);

        final ValueEventListener eventListener4=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    hostelImages=ds.getValue(HostelImages.class);

                    PicassoClient.downloadImage(InterfaceActivity.this,hostelImages.getUri_1(),i1);
                    PicassoClient.downloadImage(InterfaceActivity.this,hostelImages.getUri_2(),i2);
                    PicassoClient.downloadImage(InterfaceActivity.this,hostelImages.getUri_3(),i3);
                    PicassoClient.downloadImage(InterfaceActivity.this,hostelImages.getUri_4(),i4);
                    PicassoClient.downloadImage(InterfaceActivity.this,hostelImages.getUri_5(),i5);
                    PicassoClient.downloadImage(InterfaceActivity.this,hostelImages.getUri_6(),i6);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query4.addValueEventListener(eventListener4);

        myDialog=new Dialog(this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup1();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup2();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup3();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup4();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup5();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup6();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup7();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup8();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup9();
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup10();
            }
        });


    }

    public void showPopup10() {
        Button button;
        myDialog.setContentView(R.layout.phase10popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup9() {
        Button button;
        myDialog.setContentView(R.layout.phase9popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup8() {
        Button button;
        myDialog.setContentView(R.layout.phase8popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup7() {
        Button button;
        myDialog.setContentView(R.layout.phase7popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup6() {
        Button button;
        myDialog.setContentView(R.layout.phase6popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup5() {
        Button button;
        myDialog.setContentView(R.layout.phase5popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup4() {
        Button button;
        myDialog.setContentView(R.layout.phase4popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup3() {
        Button button;
        myDialog.setContentView(R.layout.phase3popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup2() {
        Button button;
        myDialog.setContentView(R.layout.phase2popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup1() {
        Button button;
        myDialog.setContentView(R.layout.phase1popup);

        button = (Button) myDialog.findViewById(R.id.btnfollow);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
}

