package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;

public class Email_Verificiation extends AppCompatActivity {
    Button verify;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verificiation);
        verify = findViewById(R.id.verify);
        fAuth = FirebaseAuth.getInstance();

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = fAuth.getCurrentUser();
                System.out.println(user);
                if (user.isEmailVerified()){
                    Toast.makeText(Email_Verificiation.this,"verification Successfull.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                }else{
                    Toast.makeText(Email_Verificiation.this, "Error !", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}