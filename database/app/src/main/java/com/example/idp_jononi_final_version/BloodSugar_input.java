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

public class BloodSugar_input extends AppCompatActivity {
    Button submit;
    ImageView back4,person;
    DatabaseReference databaseReference;
    FirebaseDatabase  firebaseDatabase;
    EditText bs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_input);
        submit=findViewById(R.id.bs_submit);
        bs=findViewById(R.id.enterbs2);
        back4=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        //String floatValueBs = bs.getText().toString();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bloodSugarValue = bs.getText().toString(); // Get the text from EditText
                //float floatValue = Float.parseFloat(bloodSugarValue);

                databaseReference = FirebaseDatabase.getInstance().getReference("/App Value/Blood Sugar");
                databaseReference.setValue(bloodSugarValue
                );

                Intent intent = new Intent(BloodSugar_input.this, Bloodsugar.class);
                startActivity(intent);
            }

            // Write data to the database


        });
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BloodSugar_input.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BloodSugar_input.this, profilepage.class);
                startActivity(intent);
            }

        });

    }
}