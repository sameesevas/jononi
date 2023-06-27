package com.example.idp_jononi_final_version;

import org.checkerframework.checker.nullness.qual.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Bloodpressure extends AppCompatActivity {
    ImageView back3,person;
    ImageButton rightbp;
    TextView bppp,bppp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodpressure);
        back3=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        bppp=findViewById(R.id.bppp);
        bppp2=findViewById(R.id.bppp2);
        rightbp=findViewById(R.id.imageButtonbp);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("App Value");
        //DatabaseReference bpRef1 = rootRef.child("App Value").child("Systolic Pressure");
        //DatabaseReference bpRef2 = rootRef.child("App Value").child("Diastolic Pressure");
        //int sysp=0,diasp=0;
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String sysbp = null,diasbp=null;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.hasChild("Systolic Pressure") ) {
                    sysbp = dataSnapshot.child("Systolic Pressure").getValue(String.class);
                    //int diastolic = dataSnapshot.child("diastolic").getValue(Integer.class);

                }
                if(dataSnapshot.hasChild("Diastolic Pressure")){
                    diasbp = dataSnapshot.child("Diastolic Pressure").getValue(String.class);

                }

                //String sysbp = dataSnapshot.getValue(String.class);
                int sysp=Integer.parseInt(sysbp);
                int diasp=Integer.parseInt(diasbp);

                String pressure,pressure2;

                pressure="Your Systolic Pressure is "+sysbp+" mmHg";
                bppp.setText(pressure);
                pressure2="and Diastolic Pressure is "+diasbp+" mmHg";
                bppp2.setText(pressure2);

                int marginTop;


                if (sysp > 120 && diasp > 80) {
                    marginTop = 110;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rightbp.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    rightbp.setLayoutParams(layoutParams);
                    rightbp.setVisibility(View.VISIBLE);

                }
                else if((sysp >= 120 && diasp <= 129 && diasp < 80)||((sysp >= 130 && sysp <= 139) || (diasp >= 80 && diasp <= 89))||(sysp >= 140 || diasp >= 90)){
                    marginTop = 40;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rightbp.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;

                    rightbp.setLayoutParams(layoutParams);
                    rightbp.setVisibility(View.VISIBLE);

                }
                else if(sysp < 90 && diasp < 60){
                    marginTop = 180;

                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rightbp.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    rightbp.setLayoutParams(layoutParams);
                    rightbp.setVisibility(View.VISIBLE);


                }

                    // Set the new marginTop value

                //Log.d("YOUR SUCCESS LOG TAG", "Value is: " + pressure);
                //Log.d("YOUR SUCCESS LOG TAG", "Value is: " + pressure2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bloodpressure.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bloodpressure.this, profilepage.class);
                startActivity(intent);
            }

        });

    }

}