package com.sup.administrator.enquiry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText e1,e2,e3,e4,e5;
    Button b;
    String s1,s2,s3,s4,s5;
    enquirydb en;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.email);
        e3=(EditText)findViewById(R.id.mob);
        e4=(EditText)findViewById(R.id.place);
        e5=(EditText)findViewById(R.id.msg);
        b=(Button)findViewById(R.id.cs);
        en=new enquirydb(this);
        en.getWritableDatabase();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=e1.getText().toString();
                s2=e2.getText().toString();
                s3=e3.getText().toString();
                s4=e4.getText().toString();
                s5=e5.getText().toString();
                Log.d("name",s1);
                Log.d("mail",s2);
                Log.d("mob",s3);
                Log.d("place",s4);
                Log.d("msg",s5);
                boolean status=en.insert(s1,s2,s3,s4,s5);
                if (status==true)
                {
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
