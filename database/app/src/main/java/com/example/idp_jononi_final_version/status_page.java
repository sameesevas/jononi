package com.example.idp_jononi_final_version;

import org.checkerframework.checker.nullness.qual.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class status_page extends AppCompatActivity {
    ImageView back6,person;
    TextView temp;
    TextView bs;
    TextView bp;
    TextView bmi;
    TextView recommendation;
    EditText st_name,st_height,st_condate;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_page);
        back6=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        temp = findViewById(R.id.temp);
        bs = findViewById(R.id.bs);
        bp = findViewById(R.id.bp);
        bmi = findViewById(R.id.bmi);
        recommendation = findViewById(R.id.recommen);
        st_condate=findViewById(R.id.Status_condate);
        st_name=findViewById(R.id.Status_name);
        st_height=findViewById(R.id.Status_height);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("User Information").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                st_condate.setText(documentSnapshot.getString("Conceived Date"));
                st_height.setText(documentSnapshot.getString("Height"));
                st_name.setText(documentSnapshot.getString("Fullname"));
            }
        });




        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mother0/Sensor Data/temperature");
        final int[] blood_pressure_s = new int[1];
        final int[] blood_pressure_d = new int[1];
        final float[] blood_sugar_p = new float[1];
        final int[] bmi_p = new int[1];
        final int[] temperature_p = new int[1];
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String temperature = dataSnapshot.getValue(String.class);
                int t=Integer.parseInt(temperature);
                t=Math.round(((t * 9)/5)+32);
                String temper="\n  Body \n Temperature \n"+t+"\u00B0 F";
                temp.setText(temper);
                temperature_p[0] = Integer.parseInt(temperature);
                temperature_p[0]= Math.round(((temperature_p[0]*9)/5)+32);
                Log.d("temperature", "Value is: " + temperature_p[0]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myRef1 = database.getReference("mother0/Sensor Data/pulse rate");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String blood_pressure = dataSnapshot.getValue(String.class);
                String b_p="\n  Pulse \n Rate \n"+ blood_pressure +"bpm";
                bp.setText(b_p);

                blood_pressure_s[0] =Integer.parseInt(blood_pressure);
                Log.d("blood_pressure", "Value is: " + blood_pressure );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myRef2 = database.getReference("App Value/Blood Sugar");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String blood_sugar = dataSnapshot.getValue(String.class);
                String b_s="\n  Blood \n  Sugar \n"+ blood_sugar ;
                bs.setText(b_s);
                blood_sugar_p[0] = Float.parseFloat(blood_sugar);
                Log.d("blood sugar", "Value is: " + blood_sugar );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference myRef3 = database.getReference("App Value/Bmi");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String bmi_val = dataSnapshot.getValue(String.class);
                float b= Float.parseFloat(bmi_val);
                String b_mi="\n  BMI \n \n"+ Math.round(b);
                bmi.setText(b_mi);
                bmi_p[0] =Math.round(b);
                Log.d("YOUR bmi is", "Value is: " + bmi_val );

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });



        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(status_page.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(status_page.this, profilepage.class);
                startActivity(intent);
            }

        });

        recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(status_page.this, recommendation.class);
                startActivity(intent);
            }


        });

//        recommendation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String originalText = "You are Healthy";
//                String recomm = "";
//                Log.d("YOUR total LOG TAG", "Value is: " + temperature_p[0]+" , " + blood_pressure_s[0]);
//                int score=0;
//                if (temperature_p[0]>100 && temperature_p[0]<103){
//                    score+=1;
//                    originalText="mild fever";
//                    recomm="you should take Napa, drink fluids";
//                }
//                else if (temperature_p[0]>103){
//                    score+=100;
//                }
//                else if (temperature_p[0]<95)
//                {
//                    score+=100;
//                }
//                if (blood_sugar_p[0]<4){
//                    score+=100;
//                }
//                else if (blood_sugar_p[0]>10){
//                    score+=100;
//                }
//                else if (blood_sugar_p[0]>4 && blood_sugar_p[0]<5){
//                    score+=1;
//                    if (originalText == "You are Healthy"){
//                        recomm="you should";
//                    }
//                    else {
//                        originalText+=",";
//                        recomm+=",";
//                    }
//
//                    originalText+="slightly low blood sugar";
//                    recomm+="have some carbs ";
//                }
//                if (blood_pressure_s[0]>140 || blood_pressure_d[0]<60){
//                    score+=100;
//                }
//                else if (blood_pressure_s[0]>130){
//                    score+=1;
//                    if (originalText == "You are Healthy"){
//                        recomm="you should";
//                    }
//                    else {
//                        originalText+=",";
//                        recomm+=",";
//                    }
//                        originalText += "slightly high blood pressure";
//                        recomm+="take some rest";
//                }
//                else if (blood_pressure_d[0]<70){
//                    score+=1;
//                    if (originalText == "You are Healthy"){
//                        recomm="you should";
//                    }
//                    else {
//                        originalText+=",";
//                        recomm+=",";
//                    }
//                        originalText += "slightly low blood pressure";
//                        recomm+="have some salt ";
//
//                }
//                if (score>=100){
//                    originalText="Your health parameters are abnormal \n immediately seek medical attention.";
//                    recomm="";
//                }
//                final String ultimate= originalText + " \n " + recomm;
//                recommendation.setText("Click here to see your recommendation");
//                recommendation.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        recommendation.setText(ultimate);
//                    }
//                }, 4000);
//            }
//        });

    }
}