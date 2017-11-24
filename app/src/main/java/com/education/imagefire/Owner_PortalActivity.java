package com.education.imagefire;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.key;
import static android.R.attr.value;
import static android.R.id.toggle;
import static com.education.imagefire.R.id.recyclerView;
import static com.education.imagefire.R.id.start;

public class Owner_PortalActivity extends AppCompatActivity {

    private TextView T1;
    private TextView T2;
    private TextView signout;
    private TextView T4;
    Button B1,B2,B3;
    private ImageView imageView;
    List<Hostel> listhos;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener listener;
   // private RecyclerView recyclerView;
    private ListView listView;
    private String ownerId;
    private String UserId;
    private String HostelId;
    private String HostelId2;
    private String HostelName;
    private String HostelUri;
   // private List<RecyclerUpload> list;
    private Owner owner;
    private Hostel hostel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner__portal);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.pop_up);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawr);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        // toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // list.clear();
        B1=(Button)findViewById(R.id.add_1);
       // B2=(Button)findViewById(R.id.deletehostel);
        //B3=(Button)findViewById(R.id.btn6);
        //imageView=(ImageView)findViewById(R.id.imageView);
        //T1=(TextView)findViewById(R.id.name);
        //T2=(TextView)findViewById(R.id.numb1);
        //signout=(TextView)findViewById(R.id.out);
        //T4=(TextView)findViewById(R.id.email);
        listView=(ListView)findViewById(R.id.recyc);
        listhos=new ArrayList<>();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        UserId=user.getUid();
        Toast.makeText(Owner_PortalActivity.this,"Valu is"+UserId,Toast.LENGTH_SHORT).show();

        listener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user != null){
                    // email=user.getEmail();
                    //ids=user.getUid();
                }else{
                    Toast.makeText(Owner_PortalActivity.this,"Sign out operation",Toast.LENGTH_SHORT).show();
                }
            }
        };

//        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
//        Query query = mFirebaseDatabaseReference.child("Owners").orderByChild("id").equalTo(UserId);
//        final ValueEventListener eventListener=new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot ds:dataSnapshot.getChildren()){
//                    owner=ds.getValue(Owner.class);
//
//                    ownerId=owner.getId();
//                    HostelName= owner.getName();
//                    HostelUri=owner.getUri();
//                    T1.setText(owner.getName());
//                    T2.setText(owner.getNumber_1());
//                    T4.setText(owner.getEmail());
//                    // imageView.setImageURI(owner.getUri());
//                    //Picasso.with(Owner_PortalActivity.this).load(owner.getUri()).resize(100, 100).into(imageView);
//                    // PicassoClient.downloadImage(Owner_PortalActivity.this,owner.getUri(),imageView);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        };
//
//        query.addValueEventListener(eventListener);

       // Toast.makeText(Owner_PortalActivity.this,"IM here running",Toast.LENGTH_SHORT).show();

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    Intent intent = new Intent(Owner_PortalActivity.this, HostelActivity.class);
                    intent.putExtra("UID", UserId);
                    startActivity(intent);
                }
            }
        );

//        B2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final Dialog dialog = new Dialog(getApplicationContext());
//                dialog.setContentView(R.layout.delete_dialog);
//                dialog.setTitle("Enter Name To Cancel");
//
//                dialog.show();
//
//                // set the custom dialog components - text, image and button
//                TextView T1 = (TextView) dialog.findViewById(R.id.t1);
//                final EditText E12 = (EditText) dialog.findViewById(R.id.e1);
//               final Button B12=(Button)dialog.findViewById(R.id.del1);
//                final Button B22=(Button)dialog.findViewById(R.id.can1);
//
//                B12.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        String name=E12.getText().toString();
//                    DatabaseReference databaseReferenceA=FirebaseDatabase.getInstance().getReference("Hostels");
//                        Query query = databaseReferenceA.child("Hostels").orderByChild("name").equalTo(name);
//
//                        final ValueEventListener eventListener56=new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                                   Hostel hostel=ds.getValue(Hostel.class);
//                                    HostelId2=hostel.getId();
//                                    String name=hostel.getName();
//                                    Toast.makeText(Owner_PortalActivity.this,"IM that"+name,Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                            }
//                        };
//                        query.addValueEventListener(eventListener56);
//
//                        DatabaseReference databaseReferenceB=FirebaseDatabase.getInstance().getReference("Hostels").child(HostelId2);
//                        DatabaseReference databaseReferenceC=FirebaseDatabase.getInstance().getReference("HostelInformation").child(HostelId2);
//                        DatabaseReference databaseReferenceD=FirebaseDatabase.getInstance().getReference("MapsInfo").child(HostelId2);
//                        DatabaseReference databaseReferenceE=FirebaseDatabase.getInstance().getReference("Hostel_Property_Info").child(HostelId2);
//                        DatabaseReference databaseReferenceF=FirebaseDatabase.getInstance().getReference("Facilities").child(HostelId2);
//
//                        Toast.makeText(Owner_PortalActivity.this,"Deleting",Toast.LENGTH_SHORT).show();
//                        databaseReferenceB.removeValue();
//                        databaseReferenceC.removeValue();
//                        databaseReferenceD.removeValue();
//                        databaseReferenceE.removeValue();
//                        databaseReferenceF.removeValue();
//                        Toast.makeText(Owner_PortalActivity.this,"I Have deleted",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                B22.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                //dialog.show();
//            }
//        });

//        B3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent123=new Intent(Owner_PortalActivity.this,ShowdataActivity.class);
//                //startActivity(intent123);
//                intent123.putExtra("Hostelid",HostelId);
//                intent123.putExtra("ownerid",ownerId);
//                intent123.putExtra("Hostelname",HostelName);
//                intent123.putExtra("Hosteluri",HostelUri);
//                startActivity(intent123);
//                //Toast.makeText(Owner_PortalActivity.this,"Value is"+HostelId,Toast.LENGTH_SHORT).show();
//            }
//        });

//        signout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseAuth.signOut();
//                Toast.makeText(Owner_PortalActivity.this,"Digning out",Toast.LENGTH_SHORT).show();
//                Intent intt=new Intent(Owner_PortalActivity.this,OwnerSignInActivity.class);
//                startActivity(intt);
//            }
//        });
//        Toast.makeText(Owner_PortalActivity.this,"Above of mainActivity",Toast.LENGTH_SHORT).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Hostel hostell = listhos.get(i);
                String sta=hostell.getStatus();
                if (sta.equals("NOT_APPROVED")) {
                    Toast.makeText(Owner_PortalActivity.this, "Please wait for approval ", Toast.LENGTH_SHORT).show();
                } else {
                    String id = hostell.getId();
                    String name = hostell.getName();
                    String urii = hostell.getUri();
                    Intent intent12345 = new Intent(Owner_PortalActivity.this, OwnerShowActivity.class);
                    intent12345.putExtra("Ownerid", UserId);
                    intent12345.putExtra("Hostelid", id);
                    intent12345.putExtra("Hostelname", name);
                    intent12345.putExtra("Hosteluri", urii);
                    startActivity(intent12345);

                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Hostel hostel=listhos.get(i);
                HostelId2=hostel.getId();

                AlertDialog.Builder dialog = new AlertDialog.Builder(Owner_PortalActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Dialog on Android");
                dialog.setMessage("Are you sure you want to delete this entry?" );
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        DatabaseReference databaseReferenceB=FirebaseDatabase.getInstance().getReference("Hostels").child(HostelId2);
                        DatabaseReference databaseReferenceC=FirebaseDatabase.getInstance().getReference("HostelInformation").child(HostelId2);
                        DatabaseReference databaseReferenceD=FirebaseDatabase.getInstance().getReference("MapsInfo").child(HostelId2);
                        DatabaseReference databaseReferenceE=FirebaseDatabase.getInstance().getReference("Hostel_Property_Info").child(HostelId2);
                        DatabaseReference databaseReferenceF=FirebaseDatabase.getInstance().getReference("Facilities").child(HostelId2);

                        Toast.makeText(Owner_PortalActivity.this,"Deleting",Toast.LENGTH_SHORT).show();
                        databaseReferenceB.removeValue();
                        databaseReferenceC.removeValue();
                        databaseReferenceD.removeValue();
                        databaseReferenceE.removeValue();
                        databaseReferenceF.removeValue();
                        Toast.makeText(Owner_PortalActivity.this,"I Have deleted",Toast.LENGTH_SHORT).show();


                    }
                })
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();


                return false;
            }
        });

}

//private void showDialog(String id,String name){
//      AlertDialog.Builder dialog=new AlertDialog.Builder(this);
//      LayoutInflater inflater=getLayoutInflater();
//     final View dialogview=inflater.inflate(R.layout.delete_dialog,null);
//     dialog.setView(dialogview);
//
//    final Spinner E12 = (Spinner) dialogview.findViewById(R.id.spin);
//    final Button B12=(Button)dialogview.findViewById(R.id.del1);
//    final Button B13=(Button)dialogview.findViewById(R.id.can1);
//
//    dialog.setTitle("updating ... "+name);
//    AlertDialog alertDialog=dialog.create();
//    alertDialog.show();
//
//}

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(listener);

       // databaseReference=FirebaseDatabase.getInstance().getReference("Hostels").orderByChild("owner").equalTo(UserId);
        //databaseReference.addValueEventListener(new ValueEventListener() {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("Hostels").orderByChild("owner").equalTo(UserId);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Hostel hostell=listhos.get(i);
//
//                if(hostell.getStatus()== "NOT_APPROVED"){
//                    Toast.makeText(Owner_PortalActivity.this,"Please wait for approval ",Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    String id=hostell.getId();
//                    String name=hostell.getName();
//                    String urii=hostell.getUri();
//                    Intent intent12345=new Intent(Owner_PortalActivity.this,ShowdataActivity.class);
//                    intent12345.putExtra("Ownerid",UserId);
//                    intent12345.putExtra("Hostelid",id);
//                    intent12345.putExtra("Hostelname",name);
//                    intent12345.putExtra("Hosteluri",urii);
//                    startActivity(intent12345);
//
//                }
//                String id=hostell.getId();
//                String name=hostell.getName();
//                String urii=hostell.getUri();
//                Intent intent12345=new Intent(Owner_PortalActivity.this,ShowdataActivity.class);
//                intent12345.putExtra("Ownerid",UserId);
//                intent12345.putExtra("Hostelid",id);
//                intent12345.putExtra("Hostelname",name);
//                intent12345.putExtra("Hosteluri",urii);
//                startActivity(intent12345);
                //Toast.makeText(Owner_PortalActivity.this,"Value is"+HostelId,Toast.LENGTH_SHORT).show();

          //  }
        //});
        final ValueEventListener eventListener=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listhos.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    hostel=ds.getValue(Hostel.class);
                    HostelId=hostel.getId();
                    String answer=hostel.getStatus();

                   // if(answer=="APPROVED"){
                     //   listhos.add(hostel);
                    //}
                    String sta=hostel.getStatus();
                    Toast.makeText(Owner_PortalActivity.this,"Value is"+sta,Toast.LENGTH_SHORT).show();
                    listhos.add(hostel);

                }
                CarAdapter carAdapter=new CarAdapter(Owner_PortalActivity.this,listhos);
                listView.setAdapter(carAdapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        query.addValueEventListener(eventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (listener != null) {
            firebaseAuth.removeAuthStateListener(listener);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pop_up, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }
}
