package com.education.imagefire;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Property;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.List;

public class InterfaceActivity extends AppCompatActivity {

    TextView     name,adress,e_room,e_bed,t_room,t_bed,m_r_price,m_b_price,s_r_price,s_b_price,
                 p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,prop;
    CheckBox f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    ImageView i1,i2,i3,i4,i5,i6,img;
    Dialog myDialog;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId;
    private Rooms rooms;
    private List<Owner> ownerList;
    private Facilities facilities;
    private PropertyInfo property;
    private HostelImages hostelImages;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView t1,t2;
    ImageView im1;
    Owner users;
    private String hostel_id;
    private String nameHod;
    private String adres;
    Owner owner22;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        ownerList=new ArrayList<>();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.pop_up);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawr12);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        View navi=navigationView.inflateHeaderView(R.layout.nav_header_main);
        t1=(TextView) navi.findViewById(R.id.headtext);
        t2=(TextView)navi.findViewById(R.id.textView89);
        im1=(ImageView)navi.findViewById(R.id.profile_image);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        UserId=user.getUid();
        Toast.makeText(InterfaceActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        hostel_id=getIntent().getStringExtra("Hostelid");
        databaseReference=FirebaseDatabase.getInstance().getReference("Hostels").child(hostel_id);
        String hostel_name=getIntent().getStringExtra("Hostelname");
        String hostel_url=getIntent().getStringExtra("Hostelimage");
        String hostel_type=getIntent().getStringExtra("Hosteltype");
        String hostel_likes=getIntent().getStringExtra("Hostellikes");
        String hostel_adres=getIntent().getStringExtra("Hosteladdress");

        //Toast.makeText(InterfaceActivity.this,"Valu is"+hostel_url,Toast.LENGTH_SHORT).show();


        name=(TextView) findViewById(R.id.nam1e);
        adress=(TextView)findViewById(R.id.addres1s);
        img=(ImageView)findViewById(R.id.imagees);
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

        DatabaseReference mFirebaseDatabaseReference18 = FirebaseDatabase.getInstance().getReference();
        Query query18 = mFirebaseDatabaseReference18.child("Owners").orderByChild("id").equalTo(UserId);

        final ValueEventListener eventListener18=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    users=ds.getValue(Owner.class);
                    t1.setText(users.getName());
                    t2.setText(users.getEmail());
                    PicassoClient.downloadImage(InterfaceActivity.this,users.getUri(),im1);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query18.addValueEventListener(eventListener18);


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
                    if (facilities.getWifi().equals("Yes")) {
                         f1.setChecked(true);
                    }
                    if (facilities.getGenereter().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getBreakfast().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getCamera().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getElectrition().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getGuesthouse().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getShop().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getParking().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getWasherman().equals("Yes")) {
                        f1.setChecked(true);
                    }
                    if (facilities.getKitchen().equals("Yes")) {
                        f1.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query2.addValueEventListener(eventListener2);

        DatabaseReference mFirebaseDatabaseReference3 = FirebaseDatabase.getInstance().getReference();
        Query query3 = mFirebaseDatabaseReference3.child("Hostel_Property_Info").orderByChild("id").equalTo(hostel_id);

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
        Query query4 = mFirebaseDatabaseReference4.child("AllHostelImages").orderByChild("ids").equalTo(hostel_id);

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

        //myDialog=new Dialog(this);
    }

//    public void showPopup10() {
//        Button button;
//        myDialog.setContentView(R.layout.phase10popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup9() {
//        Button button;
//        myDialog.setContentView(R.layout.phase9popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup8() {
//        Button button;
//        myDialog.setContentView(R.layout.phase8popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup7() {
//        Button button;
//        myDialog.setContentView(R.layout.phase7popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup6() {
//        Button button;
//        myDialog.setContentView(R.layout.phase6popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup5() {
//        Button button;
//        myDialog.setContentView(R.layout.phase5popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup4() {
//        Button button;
//        myDialog.setContentView(R.layout.phase4popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup3() {
//        Button button;
//        myDialog.setContentView(R.layout.phase3popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
//    public void showPopup2() {
//        Button button;
//        final EditText name,dress;
//        myDialog.setContentView(R.layout.phase2popup);
//        button = (Button) myDialog.findViewById(R.id.posave);
//        name=(EditText) myDialog.findViewById(R.id.poname);
//        dress=(EditText)myDialog.findViewById(R.id.poadress);
//        final DatabaseReference mFirebaseDatabaseReference76 = FirebaseDatabase.getInstance().getReference();
//        Query query76 = mFirebaseDatabaseReference76.child("Hostels").orderByChild("id").equalTo(hostel_id);
//        final ValueEventListener eventListener76=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    Hostel hostel=ds.getValue(Hostel.class);
//                    name.setText(hostel.getName());
//                    dress.setText(hostel.getAddres());
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        };
//        query76.addValueEventListener(eventListener76);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                nameHod=name.getText().toString();
//                adres=dress.getText().toString();
//                databaseReference.child("name").setValue(nameHod);
//                databaseReference.child("addres").setValue(adres);
//                Toast.makeText(InterfaceActivity.this,"Updated",Toast.LENGTH_SHORT).show();
//                myDialog.dismiss();
//                Toast.makeText(InterfaceActivity.this,"Updated 2",Toast.LENGTH_SHORT).show();
//            }
//        });
//        myDialog.show();
//    }
//    public Owner getowner(Owner ownerr,String id){
//
//        DatabaseReference mFirebaseDatabaseReference18 = FirebaseDatabase.getInstance().getReference();
//        Query query18 = mFirebaseDatabaseReference18.child("Owners").orderByChild("id").equalTo(id);
//
//        final ValueEventListener eventListener18=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    ownerr =ds.getValue(Owner.class);
////                   String id=owner.getId();
////                    String name=owner.getName();
////                    String adress=owner.getAddress();
////                    String mail=owner.getEmail();
////                    String numbr1=owner.getNumber_1();
////                    String numbr2=owner.getNumber_2();
////                    String numbr3=owner.getNumber_3();
////                    String passw=owner.getPassword();
////                    String profes=owner.getProfessionn();
////                    String url=owner.getUri();
//                   // owner22=owner;
//                    //owner22=new Owner(id,name,adress,numbr1,numbr2,numbr3,mail,passw,profes,url);
//                  //ownerList.add(owner);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//        query18.addValueEventListener(eventListener18);
//
//        return ownerr;
//    }
//    public void showPopup1() {
//        Button button;
//        myDialog.setContentView(R.layout.phase1popup);
//
//        button = (Button) myDialog.findViewById(R.id.btnfollow);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
}

