package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SOS_confirmed extends AppCompatActivity {
    ImageView back,person;
    TextView soshospital;
    EditText phonetext;
    Button confirmed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_confirmed);
        back=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        confirmed=findViewById(R.id.confirmed);
        soshospital=findViewById(R.id.textViewsos9);
        phonetext=findViewById(R.id.editTextPhone2);
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String selectedValue = getIntent().getStringExtra("selectedValue");


        phonetext.setText(phoneNumber);

        soshospital.setText("Your SOS is confirmed to the "+selectedValue);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SOS_confirmed.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SOS_confirmed.this, profilepage.class);
                startActivity(intent);
            }

        });
        /*confirmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SOS_confirmed.this, MainActivity2.class);
                startActivity(intent);
            }

        });*/

    }
}