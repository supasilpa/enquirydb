package com.sup.administrator.enquiry;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class search extends AppCompatActivity {
EditText e,e3;
    Button b;
    String s,s1;
    enquirydb e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        e=(EditText)findViewById(R.id.nam);
        e3=(EditText)findViewById(R.id.res);
        b=(Button)findViewById(R.id.s);
        e1=new enquirydb(this);
        e1.getWritableDatabase();

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
                    }
                }
            }
        });
    }
}
