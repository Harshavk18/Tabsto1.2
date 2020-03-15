package com.example.tabsto;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class feedback extends AppCompatActivity {
    EditText ed1;
    RatingBar rb;
    Button b1;
    String name;
    Feedbackusers f1;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        reff= FirebaseDatabase.getInstance().getReference().child("Users");
        Intent i=getIntent();
         name=i.getStringExtra("name");
         name="varada";
        ed1 = (EditText) findViewById(R.id.editText6);
        b1=(Button)findViewById(R.id.button2);
        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.ratingBar2);
        Button submitButton = (Button) findViewById(R.id.button2);
         f1=new Feedbackusers();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback=ed1.getText().toString();
                double rating= ratingRatingBar.getRating();
                f1.setUser(name);
                f1.setFeedback(feedback);
                f1.setRating(rating);
                reff.push().setValue(f1);
                Toast.makeText(getApplicationContext(),"Feedback Entered Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }
};
