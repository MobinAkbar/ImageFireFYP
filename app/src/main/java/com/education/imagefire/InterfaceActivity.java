package com.education.imagefire;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class InterfaceActivity extends AppCompatActivity {

    TextView     name,adress,e_room,e_bed,t_room,t_bed,m_r_price,m_b_price,s_r_price,s_b_price,
                 f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,property;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
    ImageView i1,i2,i3,i4,i5,i6,img;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface);

        name=(TextView) findViewById(R.id.nam1e);
        adress=(TextView)findViewById(R.id.addres1s);
        img=(ImageView)findViewById(R.id.image);

        i1=(ImageView)findViewById(R.id.im1);i2=(ImageView)findViewById(R.id.im2);
        i3=(ImageView)findViewById(R.id.im3);i4=(ImageView)findViewById(R.id.im4);
        i5=(ImageView)findViewById(R.id.im5);i6=(ImageView)findViewById(R.id.im6);

        e_room=(TextView)findViewById(R.id.e_r); e_bed=(TextView)findViewById(R.id.e_b);
        t_room=(TextView)findViewById(R.id.t_r); t_bed=(TextView)findViewById(R.id.t_b);
        m_r_price=(TextView)findViewById(R.id.m_r_room); m_b_price=(TextView)findViewById(R.id.m_b_beds);
        s_r_price=(TextView)findViewById(R.id.s_r_room); s_b_price=(TextView)findViewById(R.id.s_b_beds);

        f1=(TextView)findViewById(R.id.fa1);f2=(TextView)findViewById(R.id.fa2);
        f3=(TextView)findViewById(R.id.fa3);f4=(TextView)findViewById(R.id.fa4);
        f5=(TextView)findViewById(R.id.fa5);f6=(TextView)findViewById(R.id.fa6);
        f7=(TextView)findViewById(R.id.fa7);f8=(TextView)findViewById(R.id.fa8);
        f9=(TextView)findViewById(R.id.fa9);f10=(TextView)findViewById(R.id.fa10);

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

