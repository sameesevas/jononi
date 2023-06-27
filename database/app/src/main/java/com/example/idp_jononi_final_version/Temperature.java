package com.example.idp_jononi_final_version;

import org.checkerframework.checker.nullness.qual.NonNull;
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

public class Temperature extends AppCompatActivity {
    ImageView back2,person;
    TextView ttt;
    ImageButton tempbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        back2=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        ttt = findViewById(R.id.ttt);
        tempbutton=findViewById(R.id.imageButton100);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/mother0/Sensor Data/temperature");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String temperature = dataSnapshot.getValue(String.class);
                float tempCelcius=Float.parseFloat(temperature);
                float tempFarenhite=tempCelcius * 9/5 + 36;
                String temper="Your Body Temperature is "+tempFarenhite+"\u00B0 F";
                ttt.setText(temper);
                int marginTop;
                if(tempFarenhite<95.00){
                    marginTop = 168;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tempbutton.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    tempbutton.setLayoutParams(layoutParams);
                    tempbutton.setVisibility(View.VISIBLE);

                }
                else if(tempFarenhite>100.40){
                    marginTop = 90;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tempbutton.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    tempbutton.setLayoutParams(layoutParams);
                    tempbutton.setVisibility(View.VISIBLE);


                }
                else{
                    marginTop = 25;
                    ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) tempbutton.getLayoutParams();
                    float scale = getResources().getDisplayMetrics().density;
                    int marginTopPx = (int) (marginTop * scale + 0.5f);

                    layoutParams.topMargin = marginTopPx;
                    tempbutton.setLayoutParams(layoutParams);
                    tempbutton.setVisibility(View.VISIBLE);

                }
                Log.d("YOUR SUCCESS LOG TAG", "Value is: " + tempFarenhite);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Temperature.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Temperature.this, profilepage.class);
                startActivity(intent);
            }

        });


    }
}