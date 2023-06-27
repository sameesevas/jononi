package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class BMI_input extends AppCompatActivity {
    Button submit;
    ImageView back,person;
    EditText weight;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference databaseReference;
    FirebaseDatabase  firebaseDatabase;


    String userID;
    String height;
    String weightString;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_input);
        submit=findViewById(R.id.weight);
        back=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        weight=findViewById(R.id.enterweight2);

        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("User Information").document(userID);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                 height= documentSnapshot.getString("Height");
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weightString= weight.getText().toString();
                double weight_input = Double.parseDouble(weightString);
                float BMI=calculateBMI();
                String BMI_string=Float.toString(BMI);
                databaseReference = FirebaseDatabase.getInstance().getReference("/App Value/Bmi");
                databaseReference.setValue(BMI_string);




                Intent intent = new Intent(BMI_input.this, BMI.class);

                startActivity(intent);
            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_input.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_input.this, profilepage.class);
                startActivity(intent);
            }

        });



    }
    private float calculateBMI() {

        if (height.isEmpty() || weightString.isEmpty()) {
            Toast.makeText(BMI_input.this, "Please enter your height and weight.", Toast.LENGTH_SHORT).show();
            return 0;
        }

        float height_input = Float.parseFloat(height);
        float weight = Float.parseFloat(weightString);

        // Convert height from centimeters to meters
        height_input /= 100;

        // Calculate BMI
        float bmi = weight / (height_input * height_input);

        // Display the result
        String result;
        if (bmi < 18.5) {
            result = "Underweight";
        } else if (bmi < 25) {
            result = "Normal weight";
        } else if (bmi < 30) {
            result = "Overweight";
        } else {
            result = "Obese";
        }
        return bmi;
        //resultTextView.setText("BMI: " + String.format("%.2f", bmi) + "\n" + result);
    }

}