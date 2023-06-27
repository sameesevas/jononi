package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import org.checkerframework.checker.nullness.qual.NonNull;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BMI extends AppCompatActivity {
    ImageView back5,person;
    TextView bmi,bmi3;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        back5=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        bmi=findViewById(R.id.bmi_show);
        bmi3=findViewById(R.id.textViewbmi3);
        //righttick=findViewById(R.id.imageButtonbmi);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference bmiRef = rootRef.child("App Value").child("Bmi");

        bmiRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Retrieve the BMI value from the dataSnapshot
                    String bmiValue = dataSnapshot.getValue(String.class);
                    bmi.setText("Your Current BMI is "+bmiValue);
                    float bmiVal = Float.parseFloat(bmiValue);
                    int bmistate=checkBMI(bmiVal);
                    if(bmistate==0){
                        ImageButton imageButton = findViewById(R.id.imageButtonbmi);

                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageButton.getLayoutParams();

                        float scale = getResources().getDisplayMetrics().density;
                        int marginTopDp = 168; // Replace with your desired margin top value in dp
                        int marginTopPx = (int) (marginTopDp * scale + 0.5f);

                        layoutParams.topMargin = marginTopPx;



                        imageButton.setLayoutParams(layoutParams);
                        imageButton.setVisibility(View.VISIBLE);

                    }
                    else if(bmistate==1){
                        ImageView imageButton = findViewById(R.id.imageButtonbmi);

                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageButton.getLayoutParams();

                        float scale = getResources().getDisplayMetrics().density;
                        int marginTopDp = 25; // Replace with your desired margin top value in dp
                        int marginTopPx = (int) (marginTopDp * scale + 0.5f);

                        layoutParams.topMargin = marginTopPx;


                        imageButton.setLayoutParams(layoutParams);
                        imageButton.setVisibility(View.VISIBLE);


                    }
                    else if(bmistate==2){
                        ImageButton imageButton = findViewById(R.id.imageButtonbmi);

                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) imageButton.getLayoutParams();

                        float scale = getResources().getDisplayMetrics().density;
                        int marginTopDp = 90; // Replace with your desired margin top value in dp
                        int marginTopPx = (int) (marginTopDp * scale + 0.5f);

                        layoutParams.topMargin = marginTopPx;


                        imageButton.setLayoutParams(layoutParams);
                        imageButton.setVisibility(View.VISIBLE);


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

        back5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI.this, BMI_input.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI.this, profilepage.class);
                startActivity(intent);
            }

        });

    }
    private int checkBMI(float bmi){
        if(bmi<18.50)return 0;
        else if (bmi>= 18.50 && bmi<= 24.90)return 1;
        else return 2;

    }
}