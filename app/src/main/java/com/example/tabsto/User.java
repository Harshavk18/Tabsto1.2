package com.example.tabsto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;

import java.util.ArrayList;
import java.util.List;

public class User extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference dbArtists;
    //spinner2 for Technical or cultural
    List<XYValue> artistList;
    List<String> spinnerarray,spinnerarray2,spinnerarray3,spinnerarray4,spinnerarray5,spinnerarray6,spinnerarray7,spinnerarray8;
  Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8;
  EditText ed1,ed5;
  TextView t1,t2,t3,t4,t5,t6,t7,t8;
  ArtistsAdapter adapter;
  private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        t1=findViewById(R.id.textView17);
        t2=findViewById(R.id.textView15);
        t3=findViewById(R.id.textView18);
        t4=findViewById(R.id.textView19);
        t5=findViewById(R.id.textView20);
        t6=findViewById(R.id.textView21);
        t7=findViewById(R.id.textView22);
        t8=findViewById(R.id.textView23);
        ed1=(EditText) findViewById(R.id.editText4);
        ed5=(EditText) findViewById(R.id.editText5);
        spinner1=(Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);
        spinner2=(Spinner)findViewById(R.id.spinner2);
        spinner3=(Spinner)findViewById(R.id.spinner3);
        spinner4=(Spinner)findViewById(R.id.spinner4);
        spinner5=(Spinner)findViewById(R.id.spinner5);
        spinner6=(Spinner)findViewById(R.id.spinner6);
        spinner7=(Spinner)findViewById(R.id.spinner7);
        spinner8=(Spinner)findViewById(R.id.spinner8);
        dbArtists = FirebaseDatabase.getInstance().getReference("XYValue");
        recyclerView = (RecyclerView) findViewById(R.id.listview1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artistList = new ArrayList<>();
        adapter = new ArtistsAdapter(this, artistList);
        recyclerView.setAdapter(adapter);
        spinnerarray=new ArrayList<String>();
        spinnerarray2=new ArrayList<String>();
        spinnerarray3=new ArrayList<String>();
        spinnerarray4=new ArrayList<String>();
        spinnerarray5=new ArrayList<String>();
        spinnerarray6=new ArrayList<String>();
        spinnerarray7=new ArrayList<String>();
        spinnerarray8=new ArrayList<String>();
        spinnerarray.add("");
        spinnerarray.add("name");
        spinnerarray.add("type");
        spinnerarray.add("incharge");
        spinnerarray.add("department");
        spinnerarray.add("duration");
        spinnerarray.add("date");
        spinnerarray.add("venue");
        spinnerarray2.add("");
        spinnerarray2.add("Technical");
        spinnerarray2.add("Cultural");
        spinnerarray2.add("Sports");
        spinnerarray3.add("All");
        spinnerarray3.add("CSE");
        spinnerarray3.add("ECE");
        spinnerarray3.add("EEE");
        spinnerarray3.add("MECH");
        spinnerarray3.add("AERO");
        spinnerarray3.add("CIVIL");
        spinnerarray3.add("EIE");
        spinnerarray3.add("MBA");
        spinnerarray4.add("ES");
        spinnerarray4.add("AB-3");
        spinnerarray4.add("Main Ground");
        spinnerarray4.add("AB-1");
        spinnerarray4.add("AB-2");
        spinnerarray4.add("Coimbatore");
        spinnerarray5.add("");
        spinnerarray5.add("hours");
        spinnerarray5.add("days");
        spinnerarray6.add(" ");
        spinnerarray6.add("1");
        spinnerarray6.add("2");
        spinnerarray6.add("3");
        spinnerarray6.add("4");
        spinnerarray6.add("5");
        spinnerarray6.add("6");
        spinnerarray6.add("7");
        spinnerarray6.add("8");
        spinnerarray6.add("9");
        spinnerarray6.add("10");
        spinnerarray6.add("11");
        spinnerarray6.add("12");
        spinnerarray6.add("13");
        spinnerarray6.add("14");
        spinnerarray6.add("15");
        spinnerarray6.add("16");
        spinnerarray6.add("17");
        spinnerarray6.add("18");
        spinnerarray6.add("19");
        spinnerarray6.add("20");
        spinnerarray6.add("21");
        spinnerarray6.add("22");
        spinnerarray6.add("23");
        spinnerarray6.add("24");
        spinnerarray6.add("25");
        spinnerarray6.add("26");
        spinnerarray6.add("27");
        spinnerarray6.add("28");
        spinnerarray6.add("29");
        spinnerarray6.add("30");
        spinnerarray6.add("31");
        spinnerarray7.add(" ");
        spinnerarray7.add("01");
        spinnerarray7.add("02");
        spinnerarray7.add("03");
        spinnerarray7.add("04");
        spinnerarray7.add("05");
        spinnerarray7.add("06");
        spinnerarray7.add("07");
        spinnerarray7.add("08");
        spinnerarray7.add("09");
        spinnerarray7.add("10");
        spinnerarray7.add("11");
        spinnerarray7.add("12");
        spinnerarray8.add(" ");
        spinnerarray8.add("18");
        spinnerarray8.add("19");
        spinnerarray8.add("20");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        ArrayAdapter<String> adapter5 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray5);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        ArrayAdapter<String> adapter6 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray6);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        ArrayAdapter<String> adapter7 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray7);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        ArrayAdapter<String> adapter8 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,spinnerarray8);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        t1.setVisibility(View.GONE);
        ed1.setVisibility(View.GONE);
        t2.setVisibility(View.GONE);
        spinner2.setVisibility(View.GONE);
        t3.setVisibility(View.GONE);
        spinner3.setVisibility(View.GONE);
        t4.setVisibility(View.GONE);
        spinner4.setVisibility(View.GONE);
        t5.setVisibility(View.GONE);
        spinner5.setVisibility(View.GONE);
        ed5.setVisibility(View.GONE);
        t6.setVisibility(View.GONE);
        t7.setVisibility(View.GONE);
        t8.setVisibility(View.GONE);
        spinner6.setVisibility(View.GONE);
        spinner7.setVisibility(View.GONE);
        spinner8.setVisibility(View.GONE);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        XYValue x=artistList.get(position);
                        Intent i=new Intent(User.this,details.class);
                        i.putExtra("name",x.getName());
                        i.putExtra("Type",x.getType());
                        i.putExtra("duration",x.getDuration());
                        i.putExtra("venue",x.getVenue());
                        i.putExtra("Date",x.getDate());
                        i.putExtra("Incharge",x.getIncharge());
                        i.putExtra("department",x.getDepartment());
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
       }

    private void firebaseUserSearch(String a) {
        Query q=FirebaseDatabase.getInstance().getReference("XYValue").orderByChild(spinner1.getSelectedItem().toString()).startAt(a).endAt(a+"\uf8ff");
        q.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    XYValue artist = snapshot.getValue(XYValue.class);
                    artistList.add(artist);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String temp=parent.getItemAtPosition(position).toString();
        if(temp.equals("name")||temp.equals("incharge")) {
            t1.setVisibility(View.VISIBLE);
            ed1.setVisibility(View.VISIBLE);
            t2.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            spinner3.setVisibility(View.GONE);
            t4.setVisibility(View.GONE);
            spinner4.setVisibility(View.GONE);
            t5.setVisibility(View.GONE);
            spinner5.setVisibility(View.GONE);
            ed5.setVisibility(View.GONE);
            t6.setVisibility(View.GONE);
            t7.setVisibility(View.GONE);
            t8.setVisibility(View.GONE);
            spinner6.setVisibility(View.GONE);
            spinner7.setVisibility(View.GONE);
            spinner8.setVisibility(View.GONE);
            ed1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    firebaseUserSearch(ed1.getText().toString());
                }
            });
        }
        else if(temp.equals("type")){
            t1.setVisibility(View.GONE);
            ed1.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            spinner3.setVisibility(View.GONE);
            t2.setVisibility(View.VISIBLE);
            t4.setVisibility(View.GONE);
            spinner4.setVisibility(View.GONE);
            spinner2.setVisibility(View.VISIBLE);
            t5.setVisibility(View.GONE);
            spinner5.setVisibility(View.GONE);
            ed5.setVisibility(View.GONE);
            t6.setVisibility(View.GONE);
            t7.setVisibility(View.GONE);
            t8.setVisibility(View.GONE);
            spinner6.setVisibility(View.GONE);
            spinner7.setVisibility(View.GONE);
            spinner8.setVisibility(View.GONE);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp=parent.getItemAtPosition(position).toString();
                    if(temp.equals("Technical")){
                        firebaseUserSearch("T");
                    }
                    else if(temp.equals("Cultural")){
                        firebaseUserSearch("C");
                    }
                    else if(temp.equals("Sports")){
                        firebaseUserSearch("S");
                    }
                    else if(temp.equals("")){
                        firebaseUserSearch(" ");
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else if(temp.equals("department")){
            t1.setVisibility(View.GONE);
            ed1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            t3.setVisibility(View.VISIBLE);
            t4.setVisibility(View.GONE);
            spinner4.setVisibility(View.GONE);
            spinner3.setVisibility(View.VISIBLE);
            t5.setVisibility(View.GONE);
            spinner5.setVisibility(View.GONE);
            ed5.setVisibility(View.GONE);
            t6.setVisibility(View.GONE);
            t7.setVisibility(View.GONE);
            t8.setVisibility(View.GONE);
            spinner6.setVisibility(View.GONE);
            spinner7.setVisibility(View.GONE);
            spinner8.setVisibility(View.GONE);
            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    firebaseUserSearch(parent.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else if(temp.equals("duration")){
            t1.setVisibility(View.GONE);
            ed1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            t4.setVisibility(View.GONE);
            spinner4.setVisibility(View.GONE);
            spinner3.setVisibility(View.GONE);
            t5.setVisibility(View.VISIBLE);
            spinner5.setVisibility(View.VISIBLE);
            ed5.setVisibility(View.VISIBLE);
            t6.setVisibility(View.GONE);
            t7.setVisibility(View.GONE);
            t8.setVisibility(View.GONE);
            spinner6.setVisibility(View.GONE);
            spinner7.setVisibility(View.GONE);
            spinner8.setVisibility(View.GONE);
            ed5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                     firebaseUserSearch(ed5.getText().toString()+" "+spinner5.getSelectedItem().toString());
                }
            });
            spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    firebaseUserSearch(ed5.getText().toString()+" "+parent.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else if(temp.equals("venue")){
            t1.setVisibility(View.GONE);
            ed1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            t4.setVisibility(View.VISIBLE);
            spinner4.setVisibility(View.VISIBLE);
            spinner3.setVisibility(View.GONE);
            t5.setVisibility(View.GONE);
            spinner5.setVisibility(View.GONE);
            ed5.setVisibility(View.GONE);
            t6.setVisibility(View.GONE);
            t7.setVisibility(View.GONE);
            t8.setVisibility(View.GONE);
            spinner6.setVisibility(View.GONE);
            spinner7.setVisibility(View.GONE);
            spinner8.setVisibility(View.GONE);
            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    firebaseUserSearch(parent.getItemAtPosition(position).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else if(temp.equals("date")){
            t1.setVisibility(View.GONE);
            ed1.setVisibility(View.GONE);
            t2.setVisibility(View.GONE);
            spinner2.setVisibility(View.GONE);
            t3.setVisibility(View.GONE);
            t4.setVisibility(View.GONE);
            spinner4.setVisibility(View.GONE);
            spinner3.setVisibility(View.GONE);
            t5.setVisibility(View.GONE);
            spinner5.setVisibility(View.GONE);
            ed5.setVisibility(View.GONE);
            t6.setVisibility(View.VISIBLE);
            t7.setVisibility(View.VISIBLE);
            t8.setVisibility(View.VISIBLE);
            spinner6.setVisibility(View.VISIBLE);
            spinner7.setVisibility(View.VISIBLE);
            spinner8.setVisibility(View.VISIBLE);

            spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp=parent.getItemAtPosition(position).toString()+"/"+spinner7.getSelectedItem().toString()+"/"+spinner8.getSelectedItem().toString();
                    firebaseUserSearch(temp);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp=spinner6.getSelectedItem().toString()+"/"+parent.getItemAtPosition(position).toString()+"/"+spinner8.getSelectedItem().toString();
                    firebaseUserSearch(temp);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String temp=spinner6.getSelectedItem().toString()+"/"+spinner7.getSelectedItem().toString()+"/"+parent.getItemAtPosition(position).toString();
                    firebaseUserSearch(temp);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}