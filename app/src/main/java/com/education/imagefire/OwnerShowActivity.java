package com.education.imagefire;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class OwnerShowActivity extends AppCompatActivity {



    TextView facnew,oprofile,name,address,total_rooms,total_beds,empty_rooms,empty_beds,r_monthlyPrize,b_monthlyPrize,r_SemestrPrize,b_semestrPrize,
            ow_name,oemail,property,hospital,restaurant,shoping,grocry,park,market;
    ImageView mapping,owner_image;
    Button button,B1,B2;
    ViewPager viewPager;
    LinearLayout linearLayout;
    private int dot_count;
    private ImageView[] dotss;
    Dialog myDialog;

    private DatabaseReference databaseReference,databaseReference3;
    private DatabaseReference databaseReference1,databaseReference2;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    // private HostelInfo hostelInfo;
    private Map map;
    private PropertyInfo propertyInfo;
    private Facilities facilities;
    private HostelImages hostelImages;
    private Rooms rooms;
    private Owner owner;
    private double latitude12;
    private double longitude13;
    private double m_lat;
    private double m_logi;
    String lati_0;
    String longi_0;
    private String nameMap;
    String latitude_ss;
    String logitude_s;
    String univrsty;
    static final ArrayList<String> images1122=new ArrayList<>();
    Pager pager;
    private ArrayList<String> arrayList;
    private StorageReference storageReference;
    public static final String FB_DATABASE_PATH="Hostel_Like";
    public static final String FB_DATABASE_PATH1="Student_Like";
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
    private String UserId,ownerID,hostelName,hostelUrl,hostlid,hostl_likes;

    private String a1,a2,a3,a4,a5,a6,a7,a8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_show);

        myDialog=new Dialog(this);
        ownerID=getIntent().getStringExtra("Ownerid");
        hostlid=getIntent().getStringExtra("Hostelid");
        hostelName=getIntent().getStringExtra("Hostelname");
        String hostladress=getIntent().getStringExtra("Hosteladdress");
        hostelUrl=getIntent().getStringExtra("Hosteluri");
        univrsty=getIntent().getStringExtra("uni_name");
        hostl_likes=getIntent().getStringExtra("likes");


        //Toast.makeText(StudentShowActivity.this,"Value is"+hostl_likes,Toast.LENGTH_SHORT).show();

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();



        arrayList=new ArrayList<>();
        viewPager=(ViewPager)findViewById(R.id.viewpage);
        linearLayout=(LinearLayout) findViewById(R.id.lmobin);
        name=(TextView)findViewById(R.id.name_z);
        address=(TextView)findViewById(R.id.address_z);
        mapping=(ImageView)findViewById(R.id.map_z);
        total_rooms=(TextView)findViewById(R.id.totalrooms_z);
        total_beds=(TextView)findViewById(R.id.totalbeds_z);
        empty_rooms=(TextView)findViewById(R.id.emptyrooms_z);
        empty_beds=(TextView)findViewById(R.id.emptybeds_z);
        r_monthlyPrize=(TextView)findViewById(R.id.monthlyRprize);
        b_monthlyPrize=(TextView)findViewById(R.id.monthlyBprize);
        r_SemestrPrize=(TextView)findViewById(R.id.semstrRprize);
        b_semestrPrize=(TextView)findViewById(R.id.semestrBprize);
        ow_name=(TextView)findViewById(R.id.newoname);
        oemail=(TextView)findViewById(R.id.newoemail);
        oprofile=(TextView) findViewById(R.id.seeoprofile);
        facnew=(TextView)findViewById(R.id.newseefac);
        property=(TextView)findViewById(R.id.property_z);
        hospital=(TextView)findViewById(R.id.hosp);
        restaurant=(TextView)findViewById(R.id.resto);
        shoping=(TextView)findViewById(R.id.mall);
        grocry=(TextView)findViewById(R.id.store);
        park=(TextView)findViewById(R.id.parks);
        market=(TextView)findViewById(R.id.market);
        button=(Button)findViewById(R.id.request);
        B1=(Button)findViewById(R.id.yess);
        // B2=(Button)findViewById(R.id.nots);

        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH).child(hostlid);
        databaseReference1 = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH1).child(UserId);
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Notifications");
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Hostels").child(hostlid);


        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query1 = mFirebaseDatabaseReference.child("AllHostelImages").orderByChild("ids").equalTo(hostlid);
        //databaseReference.addValueEventListener(new ValueEventListener() {
        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //showdata(dataSnapshot);
               // Toast.makeText(StudentShowActivity.this,"Stroing....",Toast.LENGTH_SHORT).show();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    hostelImages =ds.getValue(HostelImages.class);
                    String ur_1=hostelImages.getUri_1();
                    String ur_2=hostelImages.getUri_2();
                    String ur_3=hostelImages.getUri_3();
                    String ur_4=hostelImages.getUri_4();
                    String ur_5=hostelImages.getUri_5();
                    String ur_6=hostelImages.getUri_6();

                    images1122.clear();
                    images1122.add(ur_1);
                    images1122.add(ur_2);
                    images1122.add(ur_3);
                    images1122.add(ur_4);
                    images1122.add(ur_5);
                    images1122.add(ur_6);
                    pager=new Pager(OwnerShowActivity.this,images1122);

                    viewPager.setAdapter(pager);
                    dot_count=pager.getCount();
                    Toast.makeText(OwnerShowActivity.this,"I have"+dot_count,Toast.LENGTH_SHORT).show();
                    dotss=new ImageView[dot_count];

                    for(int i=0;i<dot_count;i++){
                        dotss[i]=new ImageView(OwnerShowActivity.this);
                        dotss[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        params.setMargins(8,0,8,0);
                        linearLayout.addView(dotss[i],params);
                    }
                    dotss[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }
                        @Override
                        public void onPageSelected(int position) {
                            if(position==0){
                                dotss[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                                dotss[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[3].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[4].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[5].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                            }
                            if(position==1){
                                dotss[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                                dotss[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[3].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[4].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[5].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));

                            }
                            if(position==2){
                                dotss[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                                dotss[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[3].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[4].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[5].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                            }
                            if(position==3){
                                dotss[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                                dotss[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[4].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[5].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                            }
                            if(position==4){
                                dotss[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                                dotss[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[3].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[5].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                            }
                            if(position==5){
                                dotss[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.active_dots));
                                dotss[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[1].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[2].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[3].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                                dotss[4].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.non_active));
                            }
                        }
                        @Override
                        public void onPageScrollStateChanged(int state) {
                        }
                    });
                    Timer timer=new Timer();
                    timer.scheduleAtFixedRate(new OwnerShowActivity.MyTime(),2000,4000);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query1.addValueEventListener(eventListener);

        name.setText(hostelName);
        address.setText(hostladress);

        Query query2 = mFirebaseDatabaseReference.child("Hostels").orderByChild("id").equalTo(hostlid);
        final ValueEventListener eventListener1=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    Hostel map=ds.getValue(Hostel.class);
                    latitude12=map.getLatitude();
                    longitude13=map.getLongitude();
                    nameMap=map.getName();
                    latitude_ss=Double.toString(latitude12);
                    logitude_s=Double.toString(longitude13);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query2.addValueEventListener(eventListener1);


        Query query29 = mFirebaseDatabaseReference.child("Universities").orderByChild("name").equalTo(univrsty);
        final ValueEventListener eventListener19=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    map=ds.getValue(Map.class);
                    m_lat=map.getLatitude();
                    m_logi=map.getLongitude();
                    lati_0=Double.toString(m_lat);
                    longi_0=Double.toString(m_logi);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query29.addValueEventListener(eventListener19);


        Query query13 = mFirebaseDatabaseReference.child("Rooms").orderByChild("id").equalTo(hostlid);
        final ValueEventListener eventListener12=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    rooms=ds.getValue(Rooms.class);

                    total_rooms.setText(rooms.getTotal_rooms());
                    total_beds.setText(rooms.getTotal_beds());
                    empty_rooms.setText(rooms.getEmpty_rooms());
                    empty_beds.setText(rooms.getEmpty_beds());
                    r_monthlyPrize.setText(rooms.getR_monthprize());
                    b_monthlyPrize.setText(rooms.getB_monthprize());
                    r_SemestrPrize.setText(rooms.getR_sixmonth());
                    b_semestrPrize.setText(rooms.getB_sixmonth());

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query13.addValueEventListener(eventListener12);

        Query query3 = mFirebaseDatabaseReference.child("Hostel_Property_Info").orderByChild("id").equalTo(hostlid);
        final ValueEventListener eventListener2=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    propertyInfo=ds.getValue(PropertyInfo.class);
                    property.setText(propertyInfo.getProperty());
                    hospital.setText(propertyInfo.getNearby_place1());
                    restaurant.setText(propertyInfo.getNearby_place2());
                    shoping.setText(propertyInfo.getNearby_place3());
                    grocry.setText(propertyInfo.getNearby_place4());
                    park.setText(propertyInfo.getNearby_place5());
                    market.setText(propertyInfo.getNearby_place6());

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query3.addValueEventListener(eventListener2);


        Query query4 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(ownerID);
        final ValueEventListener eventListener3=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    owner=ds.getValue(Owner.class);
                    ow_name.setText(owner.getName());
                    oemail.setText(owner.getEmail());
                    a1=owner.getName();
                    a2=owner.getAddress();
                    a3=owner.getNumber_1();
                    a4=owner.getNumber_2();
                    a5=owner.getNumber_3();
                    a6=owner.getProfessionn();
                    a7=owner.getEmail();
                    a8=owner.getUri();
                    //PicassoClient.downloadImage(StudentShowActivity.this,owner.getUri(),owner_image);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query4.addValueEventListener(eventListener3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser userr=firebaseAuth.getCurrentUser();
                String ide=databaseReference2.push().getKey();
                String from=userr.getUid();
                String sendto=ownerID;
                String type="Pending";
                String data="1 day";
                String uriii=hostelUrl;
                String name=hostelName;
                String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                Toast.makeText(OwnerShowActivity.this,"url is"+uriii,Toast.LENGTH_SHORT).show();
                Notification notification=new Notification(ide,from,sendto,type,data,uriii,name,currentDateTimeString);
                databaseReference2.child(ide).setValue(notification);


            }
        });

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String H_id=hostlid;
                String S_id=UserId;
                String type="liked";
                int a=Integer.parseInt(hostl_likes);
                a=a+1;
                Like like=new Like(S_id,type);
                Like like1=new Like(H_id,type);
                databaseReference.child(S_id).setValue(like);
                databaseReference1.child(H_id).setValue(like1);
                databaseReference3.child("likes").setValue(a);
            }
        });

        facnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showfacilities();
            }
        });
        oprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showprofle();

            }
        });

    }
    public void showprofle(){
        Button button;
        final TextView t1,t2,t3,t4,t5,t6,t7;
        final CircleImageView imageView;
        myDialog.setContentView(R.layout.profilepopup);

        button=(Button)myDialog.findViewById(R.id.newbackpro);
        imageView=(CircleImageView)myDialog.findViewById(R.id.profile_image1);
        t1=(TextView)myDialog.findViewById(R.id.name_o1);
        t2=(TextView)myDialog.findViewById(R.id.adress_o1);
        t3=(TextView)myDialog.findViewById(R.id.num1r_01);
        t4=(TextView)myDialog.findViewById(R.id.num2r_o1);
        t5=(TextView)myDialog.findViewById(R.id.num3r_o1);
        t6=(TextView)myDialog.findViewById(R.id.job_o1);
        t7=(TextView)myDialog.findViewById(R.id.mail_o1);

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query4 = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(ownerID);
        final ValueEventListener eventListener3=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    t1.setText(owner.getName());
                    t2.setText(owner.getAddress());
                    t3.setText(owner.getNumber_1());
                    t4.setText(owner.getNumber_2());
                    t5.setText(owner.getNumber_3());
                    t6.setText(owner.getProfessionn());
                    t7.setText(owner.getEmail());

                    PicassoClient.downloadImage(OwnerShowActivity.this,owner.getUri(),imageView);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        query4.addValueEventListener(eventListener3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();





    }
    public void showfacilities() {
        Button button;
        final CheckBox t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
        myDialog.setContentView(R.layout.facilitypopup);

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

        DatabaseReference mFirebaseDatabaseReference2 = FirebaseDatabase.getInstance().getReference();
        Query query2 = mFirebaseDatabaseReference2.child("Facilities").orderByChild("id").equalTo(hostlid);

        final ValueEventListener eventListener2=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    facilities=ds.getValue(Facilities.class);
                    if (facilities.getWifi().equals("Yes")) {
                        t1.setChecked(true);
                    }
                    if (facilities.getGenereter().equals("Yes")) {
                        t2.setChecked(true);
                    }
                    if (facilities.getBreakfast().equals("Yes")) {
                        t3.setChecked(true);
                    }
                    if (facilities.getCamera().equals("Yes")) {
                        t4.setChecked(true);
                    }
                    if (facilities.getElectrition().equals("Yes")) {
                        t5.setChecked(true);
                    }
                    if (facilities.getGuesthouse().equals("Yes")) {
                        t6.setChecked(true);
                    }
                    if (facilities.getShop().equals("Yes")) {
                        t7.setChecked(true);
                    }
                    if (facilities.getParking().equals("Yes")) {
                        t8.setChecked(true);
                    }
                    if (facilities.getWasherman().equals("Yes")) {
                        t9.setChecked(true);
                    }
                    if (facilities.getKitchen().equals("Yes")) {
                        t10.setChecked(true);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        query2.addValueEventListener(eventListener2);

        button = (Button) myDialog.findViewById(R.id.newback);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    public class MyTime extends TimerTask {
        @Override
        public void run() {
            OwnerShowActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0){
                        viewPager.setCurrentItem(1);
                    }else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem()==2){
                        viewPager.setCurrentItem(3);
                    }else if(viewPager.getCurrentItem()==3){
                        viewPager.setCurrentItem(4);
                    }else if(viewPager.getCurrentItem()==4){
                        viewPager.setCurrentItem(5);
                    }else if(viewPager.getCurrentItem()==5){
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }





    }
}

