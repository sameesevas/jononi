package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

import org.checkerframework.checker.nullness.qual.NonNull;

public class Bloodsugar extends AppCompatActivity {
    ImageView back4,person;
    ImageButton bsbutton;
    TextView bs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodsugar);
        back4=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        bs=findViewById(R.id.textViewbs);
        bsbutton=findViewById(R.id.imageButtonbs);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference bsRef = rootRef.child("App Value").child("Blood Sugar");

        bsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve the BMI value from the dataSnapshot
                String bsValue = dataSnapshot.getValue(String.class);
                //String bss= Float.toString(bsValue);
                bs.setText("Your current Blood Sugar is "+bsValue+" mmol/L");
                float bsugar=Float.parseFloat(bsValue);
                if(bsugar>= 3.9 && bsugar<=5.5){
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) bsbutton.getLayoutParams();

                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopDp = 25; // Replace with your desired margin top value in dp
                    int marginTopPx = (int) (marginTopDp * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;



                    bsbutton.setLayoutParams(layoutParams);
                    bsbutton.setVisibility(View.VISIBLE);


                }
                else if(bsugar<3.9){

                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) bsbutton.getLayoutParams();

                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopDp = 168; // Replace with your desired margin top value in dp
                    int marginTopPx = (int) (marginTopDp * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;



                    bsbutton.setLayoutParams(layoutParams);
                    bsbutton.setVisibility(View.VISIBLE);


                }
                else if(bsugar>=7.8){

                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) bsbutton.getLayoutParams();

                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopDp = 100; // Replace with your desired margin top value in dp
                    int marginTopPx = (int) (marginTopDp * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;



                    bsbutton.setLayoutParams(layoutParams);
                    bsbutton.setVisibility(View.VISIBLE);


                }


                // Use the retrieved BMI value as needed
                //Log.d("Firebase", "BMI value: " + bmiValue);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle cancelled event or error
                Log.e("Firebase", "Failed to retrieve BMI value: " + databaseError.getMessage());
            }
        });
        back4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bloodsugar.this, BloodSugar_input.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Bloodsugar.this, profilepage.class);
                startActivity(intent);
            }

        });

    }
}