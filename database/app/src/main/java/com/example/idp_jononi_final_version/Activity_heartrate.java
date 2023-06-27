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

public class Activity_heartrate extends AppCompatActivity {
    ImageView back1,person;
    ImageButton rateButton;
    TextView rateText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate);
        back1=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        rateButton=findViewById(R.id.imageButtonrate6);
        rateText=findViewById(R.id.textViewrate);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/mother0/Sensor Data/pulse rate");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pr = dataSnapshot.getValue(String.class);
                String prString="Your current heart rate is "+pr+" bpm";
                rateText.setText(prString);

                int heartRate=Integer.parseInt(pr);
                int marginTop;
                if (heartRate < 60) {
                    // Low Heart Rate (Bradycardia)
                    marginTop = 168;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rateButton.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    rateButton.setLayoutParams(layoutParams);
                    rateButton.setVisibility(View.VISIBLE);


                }
                else if (heartRate >= 60 && heartRate <= 100) {
                    // Normal Heart Rate
                    marginTop = 25;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rateButton.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    rateButton.setLayoutParams(layoutParams);
                    rateButton.setVisibility(View.VISIBLE);


                }
                else {
                    // High Heart Rate (Tachycardia)
                    marginTop = 100;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) rateButton.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    rateButton.setLayoutParams(layoutParams);
                    rateButton.setVisibility(View.VISIBLE);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_heartrate.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_heartrate.this, profilepage.class);
                startActivity(intent);
            }

        });


    }
}