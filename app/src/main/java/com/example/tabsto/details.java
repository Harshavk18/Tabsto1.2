package com.example.tabsto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class details extends AppCompatActivity implements View.OnClickListener {
  TextView t1,t2,t3,t4,t5,t6,t7;
  Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        t1=(TextView) findViewById(R.id.textView2);
        t2=(TextView) findViewById(R.id.textView4);
        t3=(TextView) findViewById(R.id.textView6);
        t4=(TextView) findViewById(R.id.textView8);
        t5=(TextView) findViewById(R.id.textView10);
        t6=(TextView) findViewById(R.id.textView12);
        t7=(TextView) findViewById(R.id.textView14);
        Intent i= getIntent();
        t1.setText(i.getStringExtra("name"));
        t2.setText(i.getStringExtra("Type"));
        t3.setText(i.getStringExtra("duration"));
        t4.setText(i.getStringExtra("venue"));
        t5.setText(i.getStringExtra("Date"));
        t6.setText(i.getStringExtra("Incharge"));
        t7.setText(i.getStringExtra("department"));
        b1=(Button) findViewById(R.id.button5);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button5){
            Intent i=new Intent(this,User.class);
            startActivity(i);
        }
        if(v.getId()==R.id.button6){
        }
    }
}
