package com.education.imagefire;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Property;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterfaceActivity extends AppCompatActivity {

    TextView     name,adress,e_room,e_bed,t_room,t_bed,m_r_price,m_b_price,s_r_price,s_b_price,
                 p1,p2,p3,p4,p5,p6,prop;
    CheckBox f1,f2,f3,f4,f5,f6,f7,f8,f9,f10;
    ImageView i1,i2,i3,i4,i5,i6,img;
    ImageButton img1,img2,img3,img4,img5,img6,img7,img8,img9,img10,img11,img12,img13;
    private String namep,adresp,rom1p,rom2p,rom3p,rom4p,rom5p,rom6p,rom7p,rom8p;
    private Uri filepath;
    public static final int REQUEST_CODE=1234;
    private ImageView imageView;
    public static final String FB_STOARGE_PATH="Hostels/";
    public static final String FB_STOARGE_PATH1="AllHostelImages/";
    Dialog myDialog;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1,databaseReference2,databaseReference3,databaseReference5;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId;
    private Rooms rooms;
    private List<Owner> ownerList;
    private Facilities facilities,facilities2;
    private PropertyInfo property;
    private HostelImages hostelImages;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    TextView t1,t2;
    ImageView im1;
    Owner users;
    private String hostel_id,hostel_name,hostel_url,hostel_adres;
    private String nameHod;
    private String adres;
    private String var1,var2,var3,var4,var5,var6,var7,var8,var9,var10;
    private String url1,url2,url3,url4,url5,url6;
    Owner owner22;
    private StorageReference storageReference;



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

        img1=(ImageButton)findViewById(R.id.edit1);
        img2=(ImageButton)findViewById(R.id.edit2);
        img3=(ImageButton)findViewById(R.id.edit3);
        img4=(ImageButton)findViewById(R.id.image4);
        img5=(ImageButton)findViewById(R.id.image5);
        img6=(ImageButton)findViewById(R.id.image6);
        img7=(ImageButton)findViewById(R.id.image7);
        img8=(ImageButton)findViewById(R.id.image11);
        img9=(ImageButton)findViewById(R.id.image12);
        img10=(ImageButton)findViewById(R.id.image13);
        img11=(ImageButton)findViewById(R.id.image14);
        img12=(ImageButton)findViewById(R.id.image15);
        img13=(ImageButton)findViewById(R.id.image16);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();

        UserId=user.getUid();
        Toast.makeText(InterfaceActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        hostel_id=getIntent().getStringExtra("Hostelid");
        hostel_name=getIntent().getStringExtra("Hostelname");
        hostel_url=getIntent().getStringExtra("Hostelimage");
        String hostel_type=getIntent().getStringExtra("Hosteltype");
        String hostel_likes=getIntent().getStringExtra("Hostellikes");
        hostel_adres=getIntent().getStringExtra("Hosteladdress");

        Toast.makeText(InterfaceActivity.this,"Valu is"+hostel_id,Toast.LENGTH_SHORT).show();

        databaseReference1=FirebaseDatabase.getInstance().getReference("Rooms").child(hostel_id);
        databaseReference2=FirebaseDatabase.getInstance().getReference("Hoste_Property_Info").child(hostel_id);
        databaseReference3=FirebaseDatabase.getInstance().getReference("Facilities").child(hostel_id);
        databaseReference=FirebaseDatabase.getInstance().getReference("Hostels").child(hostel_id);
        databaseReference5=FirebaseDatabase.getInstance().getReference("AllHostelImages").child(hostel_id);

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
                        f2.setChecked(true);
                    }
                    if (facilities.getBreakfast().equals("Yes")) {
                        f3.setChecked(true);
                    }
                    if (facilities.getCamera().equals("Yes")) {
                        f4.setChecked(true);
                    }
                    if (facilities.getElectrition().equals("Yes")) {
                        f5.setChecked(true);
                    }
                    if (facilities.getGuesthouse().equals("Yes")) {
                        f6.setChecked(true);
                    }
                    if (facilities.getShop().equals("Yes")) {
                        f7.setChecked(true);
                    }
                    if (facilities.getParking().equals("Yes")) {
                        f8.setChecked(true);
                    }
                    if (facilities.getWasherman().equals("Yes")) {
                        f9.setChecked(true);
                    }
                    if (facilities.getKitchen().equals("Yes")) {
                        f10.setChecked(true);
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
                    url1=hostelImages.getUri_1();
                    url2=hostelImages.getUri_2();
                    url3=hostelImages.getUri_3();
                    url4=hostelImages.getUri_4();
                    url5=hostelImages.getUri_5();
                    url6=hostelImages.getUri_6();

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

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img1);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup1();
                        return true;
                    }
                });
                popup.show();
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img2);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup2();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img3);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup3();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img4);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup4();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img5);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup5();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img6);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup6();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img7);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup7();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img8);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup8();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img9);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup9();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img10);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup10();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img11);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup11();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img12);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup12();
                        return true;
                    }
                });
                popup.show();
            }
        });
        img13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(InterfaceActivity.this, img13);
                popup.getMenuInflater().inflate(R.menu.edit, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showPopup13();
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    public void showPopup1() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase1popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               uploads(hostel_url,FB_STOARGE_PATH,"uri",databaseReference);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup2() {
        Button button;
        final EditText t11,t22;
        myDialog.setContentView(R.layout.phase2popup);

        button = (Button) myDialog.findViewById(R.id.posave);
        t11=(EditText) myDialog.findViewById(R.id.poname);
        t22=(EditText) myDialog.findViewById(R.id.poadress);
        t11.setText(name.getText().toString());
        t22.setText(adress.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameHod=t11.getText().toString();
                adres=t22.getText().toString();
                databaseReference.child("name").setValue(nameHod);
                databaseReference.child("addres").setValue(adres);
                Toast.makeText(InterfaceActivity.this,"here is"+nameHod,Toast.LENGTH_SHORT).show();
                Toast.makeText(InterfaceActivity.this,"Done",Toast.LENGTH_SHORT).show();
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup3() {
        Button button;
       final EditText t11,t22,t33,t44;
        myDialog.setContentView(R.layout.phase3popup);

        button = (Button) myDialog.findViewById(R.id.blow1);
        t11=(EditText) myDialog.findViewById(R.id.tRooms);
        t22=(EditText) myDialog.findViewById(R.id.tbeds);
        t33=(EditText) myDialog.findViewById(R.id.erooms);
        t44=(EditText) myDialog.findViewById(R.id.ebeds);
        t11.setText(t_room.getText().toString());
        t22.setText(t_bed.getText().toString());
        t33.setText(e_room.getText().toString());
        t44.setText(e_bed.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                var1=t11.getText().toString();
                var2=t22.getText().toString();
                var3=t33.getText().toString();
                var4=t44.getText().toString();
                databaseReference1.child("total_rooms").setValue(var1);
                databaseReference1.child("total_beds").setValue(var2);
                databaseReference1.child("empty_rooms").setValue(var3);
                databaseReference1.child("empty_beds").setValue(var4);
                finish();
                startActivity(getIntent());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup4() {
        Button button;
        final EditText t11,t22,t33,t44;
        myDialog.setContentView(R.layout.phase4popup);

        button = (Button) myDialog.findViewById(R.id.blow2);
        t11=(EditText) myDialog.findViewById(R.id.pmr);
        t22=(EditText) myDialog.findViewById(R.id.pmb);
        t33=(EditText) myDialog.findViewById(R.id.psr);
        t44=(EditText) myDialog.findViewById(R.id.psb);
        t11.setText(m_r_price.getText().toString());
        t22.setText(m_b_price.getText().toString());
        t33.setText(s_r_price.getText().toString());
        t44.setText(s_b_price.getText().toString());


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                var1=t11.getText().toString();
                var2=t22.getText().toString();
                var3=t33.getText().toString();
                var4=t44.getText().toString();
                databaseReference1.child("r_monthprize").setValue(var1);
                databaseReference1.child("b_monthprize").setValue(var2);
                databaseReference1.child("r_sixmonth").setValue(var3);
                databaseReference1.child("b_sixmonth").setValue(var4);
                finish();
                startActivity(getIntent());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup7() {
        Button button;
        final EditText t11;
        myDialog.setContentView(R.layout.phase7popup);

        button = (Button) myDialog.findViewById(R.id.blow3);
        t11=(EditText) myDialog.findViewById(R.id.prop1);
        t11.setText(prop.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                var1=t11.getText().toString();
                databaseReference2.child("property").setValue(var1);
                finish();
                startActivity(getIntent());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup6() {
        Button button;
        final EditText t11,t22,t33,t44,t55,t66;
        myDialog.setContentView(R.layout.phase6popup);

        button = (Button) myDialog.findViewById(R.id.blow4);
        t11=(EditText) myDialog.findViewById(R.id.pla1);
        t22=(EditText) myDialog.findViewById(R.id.pla2);
        t33=(EditText) myDialog.findViewById(R.id.pla3);
        t44=(EditText) myDialog.findViewById(R.id.pla4);
        t55=(EditText) myDialog.findViewById(R.id.pla5);
        t66=(EditText) myDialog.findViewById(R.id.pla6);

        t11.setText(p1.getText().toString());
        t22.setText(p2.getText().toString());
        t33.setText(p3.getText().toString());
        t44.setText(p4.getText().toString());
        t55.setText(p5.getText().toString());
        t66.setText(p6.getText().toString());

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                var1=t11.getText().toString();
                var2=t22.getText().toString();
                var3=t33.getText().toString();
                var4=t44.getText().toString();
                var5=t55.getText().toString();
                var6=t66.getText().toString();
                databaseReference2.child("nearby_place1").setValue(var1);
                databaseReference2.child("nearby_place2").setValue(var2);
                databaseReference2.child("nearby_place3").setValue(var3);
                databaseReference2.child("nearby_place4").setValue(var4);
                databaseReference2.child("nearby_place5").setValue(var5);
                databaseReference2.child("nearby_place6").setValue(var6);
                finish();
                startActivity(getIntent());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup5() {
        Button button;
        CheckBox t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
        myDialog.setContentView(R.layout.phase5popup);

        button = (Button) myDialog.findViewById(R.id.blow5);
        t1=(CheckBox) myDialog.findViewById(R.id.fac1);
        t2=(CheckBox) myDialog.findViewById(R.id.fac2);
        t3=(CheckBox) myDialog.findViewById(R.id.fac3);
        t4=(CheckBox) myDialog.findViewById(R.id.fac4);
        t5=(CheckBox) myDialog.findViewById(R.id.fac5);
        t6=(CheckBox) myDialog.findViewById(R.id.fac6);
        t7=(CheckBox) myDialog.findViewById(R.id.fac7);
        t8=(CheckBox) myDialog.findViewById(R.id.fac8);
        t9=(CheckBox) myDialog.findViewById(R.id.fac9);
        t10=(CheckBox) myDialog.findViewById(R.id.fac10);
        String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10;
        String id=hostel_id;
        if(t1.isChecked()) {
            c1 = "Yes";
        }else{
            c1="No";
        }


        if(t2.isChecked()) {
            c2 = "Yes";
        }else{
            c2="No";
        }

        if(t3.isChecked()) {
            c3 = "Yes";;
        }
        else{
            c3="No";
        }

        if(t4.isChecked()) {
            c4 = "Yes";;
        }else{
            c4="No";
        }

        if(t5.isChecked()) {
            c5 = "Yes";;
        }else{
            c5="No";
        }


        if(t6.isChecked()) {
            c6 = "Yes";;
        }else{
            c6="No";
        }

        if(t7.isChecked()) {
            c7 = "Yes";;
        }else{
            c7="No";
        }

        if(t8.isChecked()) {
            c8 = "Yes";;
        }else{
            c8="No";
        }

        if(t9.isChecked()) {
            c9 = "Yes";;
        }else{
            c9="No";
        }

        if(t10.isChecked()) {
            c10 = "Yes";
        }else{
            c10="No";
        }

        facilities2=new Facilities(id,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseReference3.setValue(facilities2);
                finish();
                startActivity(getIntent());
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup8() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase8popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploads(url1,FB_STOARGE_PATH1,"uri_1",databaseReference5);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup9() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase9popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploads(url2,FB_STOARGE_PATH1,"uri_2",databaseReference5);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup10() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase10popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploads(url3,FB_STOARGE_PATH1,"uri_3",databaseReference5);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup11() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase11popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploads(url4,FB_STOARGE_PATH1,"uri_4",databaseReference5);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup12() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase12popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploads(url5,FB_STOARGE_PATH1,"uri_5",databaseReference5);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }
    public void showPopup13() {
        Button button,button2;
        myDialog.setContentView(R.layout.phase13popup);

        imageView=(ImageView)myDialog.findViewById(R.id.hostello);
        button = (Button) myDialog.findViewById(R.id.blowA);
        button2 = (Button) myDialog.findViewById(R.id.blowC);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chosen();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploads(url6,FB_STOARGE_PATH1,"uri_6",databaseReference5);
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public void uploads(String url,final String path, final String attribute, final DatabaseReference databaseReferenceCu) {

        if(filepath!=null){
            FirebaseStorage firebaseStorage=FirebaseStorage.getInstance().getReference().getStorage();
            StorageReference photoRef = firebaseStorage.getReferenceFromUrl(url);

            photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(InterfaceActivity.this,"Deleted Succesfully",Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(InterfaceActivity.this,"Deletion failed",Toast.LENGTH_LONG).show();
                }
            });


            storageReference = FirebaseStorage.getInstance().getReference();
            final ProgressDialog progress=new ProgressDialog(this);
            progress.setTitle("uploading.....");
            progress.show();

            StorageReference ref=storageReference.child(path + System.currentTimeMillis()+ getImageExt(filepath));
            ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @SuppressWarnings("VisibleForTests")
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progress.dismiss();

                    databaseReferenceCu.child(attribute).setValue(taskSnapshot.getDownloadUrl().toString());


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progress.dismiss();
                    Toast.makeText(InterfaceActivity.this,"Failed",Toast.LENGTH_LONG).show();
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
            Toast.makeText(InterfaceActivity.this,"Succesfully complete",Toast.LENGTH_LONG).show();
        }
    }
    public void chosen() {
        Intent intent=new Intent();
        intent.setType("Image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Images"),REQUEST_CODE);
       // Toast.makeText(HostelActivity.this,"please image 3",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //imageView=(ImageView)findViewById(R.id.hostel_image);
        if(requestCode==REQUEST_CODE || requestCode== RESULT_OK && data !=null&& data.getData()!=null){
            filepath=data.getData();

            try{
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                imageView.setImageBitmap(bitmap);
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

