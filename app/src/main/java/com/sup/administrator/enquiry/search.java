package com.sup.administrator.enquiry;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class search extends AppCompatActivity {
EditText e,e3;
    Button b,b1,b2;
    String s,s1,s2,getid,s3;
    enquirydb e1;
    AlertDialog.Builder build;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        e=(EditText)findViewById(R.id.nam);
        e3=(EditText)findViewById(R.id.res);
        b=(Button)findViewById(R.id.s);
        b1=(Button)findViewById(R.id.update);
        b2=(Button)findViewById(R.id.delete);
        build=new AlertDialog.Builder(this);
        build.setTitle("Confirm");
        build.setMessage("are you sure you want to delete?");
        build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean status=e1.delete(getid);
                if(status==true)
                {
                    Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
                dialogInterface.dismiss();
            }
        });
        build.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"No Clicked",Toast.LENGTH_LONG).show();

                dialogInterface.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//             boolean status=e1.delete(getid);
//                if(status==true)
//                {
//                    Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
//                }
                AlertDialog alert=build.create();
                alert.show();
            }
        });
        e1=new enquirydb(this);
        e1.getWritableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s2=e3.getText().toString();
//                Toast.makeText(getApplicationContext(),s2,Toast.LENGTH_LONG).show();
                boolean result=e1.updatedata(getid,s2);
                if (result==true)
                {
                    Toast.makeText(getApplicationContext(),"updated succesfully",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=e.getText().toString();
                Log.d("name",s);
                Cursor cur=e1.Searchdata(s);
                if (cur.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_LONG).show();
                }
                else
                {
                    while(cur.moveToNext())
                    {
                        s1=cur.getString(2);
//                        Toast.makeText(getApplicationContext(),s1,Toast.LENGTH_LONG).show();
                        e3.setVisibility(View.VISIBLE);
                        e3.setText(s1);
                        getid=cur.getString(0);
                        Toast.makeText(getApplicationContext(),getid,Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
