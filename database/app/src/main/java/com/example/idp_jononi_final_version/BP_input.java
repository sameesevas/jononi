package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BP_input extends AppCompatActivity {
    Button submit;
    EditText bp2,bp3;
    ImageView back4,person;
    DatabaseReference databaseReference1, databaseReference2;
    FirebaseDatabase  firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bp_input);
        submit=findViewById(R.id.bp_submit);
        bp2=findViewById(R.id.enterbp2);
        bp3=findViewById(R.id.enterbp3);
        back4=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String systolicValue = bp2.getText().toString(); // Get the text from EditText
                String diastolicValue = bp3.getText().toString(); // Get the text from EditText


                databaseReference1 = FirebaseDatabase.getInstance().getReference("/App Value/Diastolic Pressure");
                databaseReference1.setValue(diastolicValue);
                databaseReference2 = FirebaseDatabase.getInstance().getReference("/App Value/Systolic Pressure");
                databaseReference2.setValue(systolicValue);


                Intent intent = new Intent(BP_input.this, Bloodpressure.class);
                startActivity(intent);
            }

            // Write data to the database


        });
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BP_input.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BP_input.this, profilepage.class);
                startActivity(intent);
            }

        });


    }
}