package com.tekup.cabinetmedicale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctorDetails1 = {
            {"Doctor Name:Mohsen Iifa","Hospital Address:Charles Nicols","Telephone Num:7474747","415"},
            {"Doctor Name:Taid Jandoubi","Hospital Address:Yhtur","Telephone Num:7074747754","600"},
            {"Doctor Name:Imen Kasah","Hospital Address:Charles Nicols","Telephone Num:2474747","500"},
            {"Doctor Name:Jamila Aabdelaoui","Hospital Address:Rabta","Telephone Num:1452247","8632"}
    };

    private String[][] doctorDetails2 = {
            {"Doctor Name:John Doe","Hospital Address:Main Street","Telephone Num:1234567","789"},
            {"Doctor Name:Emily Smith","Hospital Address:Elm Avenue","Telephone Num:9876543","321"},
            {"Doctor Name:Michael Johnson","Hospital Address:Pine Street","Telephone Num:5551234","432"},
            {"Doctor Name:Sara Williams","Hospital Address:Maple Avenue","Telephone Num:7778888","567"}
    };

    private String[][] doctorDetails3 = {
            {"Doctor Name:David Brown","Hospital Address:Cedar Lane","Telephone Num:3332222","654"},
            {"Doctor Name:Laura Miller","Hospital Address:Oak Street","Telephone Num:9990001","876"},
            {"Doctor Name:Daniel Wilson","Hospital Address:Willow Avenue","Telephone Num:1110000","246"},
            {"Doctor Name:Amy Garcia","Hospital Address:Sunset Boulevard","Telephone Num:8889991","135"}
    };

    private String[][] doctorDetails4 = {
            {"Doctor Name:Robert Lee","Hospital Address:Waterfront Road","Telephone Num:4443332","978"},
            {"Doctor Name:Olivia Davis","Hospital Address:Hillside Drive","Telephone Num:2221110","357"},
            {"Doctor Name:William Clark","Hospital Address:Meadow Lane","Telephone Num:7773331","789"},
            {"Doctor Name:Sophia Martinez","Hospital Address:Park Avenue","Telephone Num:8880009","654"}
    };

    private String[][] doctorDetails5 = {
            {"Doctor Name:Ethan Wright","Hospital Address:Harbor Street","Telephone Num:1237890","456"},
            {"Doctor Name:Chloe Anderson","Hospital Address:Valley Road","Telephone Num:4567890","987"},
            {"Doctor Name:Aiden Hernandez","Hospital Address:Mountain View","Telephone Num:9871234","321"},
            {"Doctor Name:Isabella Thompson","Hospital Address:Lakeside Drive","Telephone Num:5550001","654"}
    };

TextView tv;
Button btn;
ArrayList List;
SimpleAdapter sa;
HashMap<String,String> item;

    String[][] doctorDetails= {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.textViewDDTitle);
        btn=findViewById(R.id.buttonCartBack);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("FamilyPhysician")==0)
            doctorDetails=doctorDetails1;

        else
        if(title.compareTo("Dietician")==0)
            doctorDetails=doctorDetails2;

        else
        if(title.compareTo("Dentist")==0)
            doctorDetails=doctorDetails3;

        else
        if(title.compareTo("Surgeon")==0)
            doctorDetails=doctorDetails4;

        else

            doctorDetails=doctorDetails5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
      List=new ArrayList<>();
      for(int i=0;i<doctorDetails.length;i++){
          item=new HashMap<String, String>();
          item.put("Line1",doctorDetails[i][0]);
          item.put("Line2",doctorDetails[i][1]);
          item.put("Line3",doctorDetails[i][2]);
          item.put("Line4",doctorDetails[i][3]);
          item.put("Line5","con fees:"+doctorDetails[i][4]+"/-");
          List.add(item);
      }
sa=new SimpleAdapter(this,List,
        R.layout.multi_lines,
        new String[]{"line1","line2","line3","line4","line5"}
        ,new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst=findViewById((R.id.listViewLT));
        lst.setAdapter(sa);

    }
}