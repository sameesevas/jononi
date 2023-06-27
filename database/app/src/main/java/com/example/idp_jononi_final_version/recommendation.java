package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
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

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class recommendation extends AppCompatActivity {
    ImageView back6,person;
    TextView temp;
    TextView bs;
    TextView bp;
//    TextView bmi_me;
    TextView recommendation;
    String PhoneNumber, userID;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private String convertToGsm7(String message) {
        StringBuilder gsm7Message = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            String gsm7Char = convertCharToGsm7(c);
            gsm7Message.append(gsm7Char);
        }
        return gsm7Message.toString();
    }

    private String convertCharToGsm7(char c) {
        switch (c) {
            case '@':
                return "00";
            case '£':
                return "01";
            case '$':
                return "02";
            case '¥':
                return "03";
            // Add more character mappings as needed
            default:
                return String.valueOf(c);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        back6=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        temp=findViewById(R.id.temp_r);
//        bmi_me=findViewById(R.id.bmi_r);
        bs = findViewById(R.id.bs_r);
        bp = findViewById(R.id.bp_r);
        recommendation = findViewById(R.id.recom_r);
        final Double[] latitudee= new Double[1];
        final Double[] longitudee= new Double[1];
        final String[] location = new String[1];
        fAuth=FirebaseAuth.getInstance();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef10 = database.getReference("mother0/Sensor Data/latitude");
        DatabaseReference myRef20 = database.getReference("mother0/Sensor Data/longitude");
        DatabaseReference myRef = database.getReference("mother0/Sensor Data/temperature");
        fAuth=FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        DocumentReference documentReference=fStore.collection("User Information").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                PhoneNumber=documentSnapshot.getString("Emergency Contact");
            }
        });
        myRef10.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String latitude = dataSnapshot.getValue(String.class);
                String b_s="\n  latitude \n"+ latitude ;
                latitudee[0] = Double.valueOf(latitude);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });
        myRef20.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String longitude = dataSnapshot.getValue(String.class);
                String b_s="\n  latitude \n"+ longitude ;
                longitudee[0] = Double.valueOf(longitude);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String temperature = dataSnapshot.getValue(String.class);
                int t=Integer.parseInt(temperature);
                t=Math.round(((t * 9)/5)+32);
                String temper="\n  Body \n Temperature \n"+t+"\u00B0 F";
                if (t<95){
                    temper="\n Your Body \n Temperature is \n too low \n please seek help";
                }
                else if (t>103){
                    temper="\n Your Body \n Temperature is \n too high \n please seek help";
                }
                else if (t>101 && t<103)
                {
                    temper="\n You have \n mild fever \n please take rest \n and medication properly";
                }
                else
                {
                    temper="\n Your Body \n Temperature is \n normal";
                }
                temp.setText(temper);
                Log.d("temperature", "Value is: " + t);
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
                int p=Integer.parseInt(blood_pressure);
                String b_p="\n  Pulse \n Rate \n"+ blood_pressure +"bpm";
                if (p>100)
                {
                    b_p="\n Your pulse \n rate is \n too high \n please seek help";
                }
                else if (p>60)
                {
                    b_p="\n Your pulse \n rate is \n too low \n please seek help";
                }
                else
                {
                    b_p="\n Your pulse \n rate is \n normal";
                }
                bp.setText(b_p);
                Log.d("blood_pressure", "Value is: " + p );
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
                float s=Float.parseFloat(blood_sugar);
                String b_s="\n  Blood \n  Sugar \n"+ blood_sugar;
                if (s>100)
                {
                    b_s="\n Your blood sugar \n is too high \n please seek help";
                }
                else if (s>60)
                {
                    b_s="\n Your blood sugar \n  is \n too low \n please seek help";
                }
                else
                {
                    b_s="\n Your blood sugar \n  is normal";
                }
                bs.setText(b_s);
                Log.d("blood sugar", "Value is: " + blood_sugar );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
            }
        });

//        DatabaseReference myRef3 = database.getReference("App Value/Bmi");
//        myRef3.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String bmi_val = dataSnapshot.getValue(String.class);
//                String bmi="a";
//                float b= Float.parseFloat(bmi_val);
//                String bm_i="\n  BMI \n \n"+ Math.round(b);
//                if (b>100)
//                {
//                    bm_i="\n Your pulse \n rate is \n too high \n please seek help";
//                }
//                else if (b>60)
//                {
//                    bm_i="\n Your pulse \n rate is \n too low \n please seek help";
//                }
//                else
//                {
//                    bm_i="\n Your pulse \n rate is \n normal";
//                }
//                bmi_me.setText(bm_i);
//                Log.d("YOUR bmi is", "Value is: " + bmi_val );
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Failed to read value
//                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
//            }
//        });

        DatabaseReference myRef4 = database.getReference("mother0/Sensor Data/Risk Level");
        myRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String bmi_val = dataSnapshot.getValue(String.class);
                char bm=bmi_val.charAt(1);
                int b=Integer.parseInt(String.valueOf(bm));
                String b_mi="\n  BMI \n \n"+ bmi_val;

                if (b==2){
                    b_mi="You have a high level of risk \n your close contacts are informed\n of the situation please take necessary actions";
                    location[0] = "https://www.google.com/maps?q=" + latitudee[0] + "," + longitudee[0];
                    System.out.println("ekhan theke"+ location[0]);
                    System.out.println("ekhan theke"+ latitudee[0]);
                    System.out.println("ekhan theke"+ longitudee[0]);
                    //String phoneNumber="01753861142";
                    String message="your contact is in need of immediate medical assistance. (location :  " +location[0]+
                            " )Please respond urgently!";
                    System.out.println("ekhan theke"+ message);
                    try {
                        SmsManager smsManager = SmsManager.getDefault();

                        int MAX_SMS_LENGTH = 300;
                        int MAX_GSM7_CHARACTERS = MAX_SMS_LENGTH * 8 / 7;
                        int totalParts = (int) Math.ceil((double) message.length() / MAX_GSM7_CHARACTERS);

                        ArrayList<String> parts = new ArrayList<>();
                        for (int i = 0; i < totalParts; i++) {
                            int startIndex = i * MAX_GSM7_CHARACTERS;
                            int endIndex = Math.min(startIndex + MAX_GSM7_CHARACTERS, message.length());
                            String part = convertToGsm7(message.substring(startIndex, endIndex));
                            parts.add(part);
                        }

                        ArrayList<PendingIntent> sentIntents = new ArrayList<>();
                        ArrayList<PendingIntent> deliveryIntents = new ArrayList<>();
                        for (int i = 0; i < totalParts; i++) {
                            sentIntents.add(null);
                            deliveryIntents.add(null);
                        }
                        smsManager.sendMultipartTextMessage(PhoneNumber, null, parts, sentIntents, deliveryIntents);
                        Log.d("blood sugar", "message sent");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("FAIL", String.valueOf(e));
                    }

                }
                else if (b==1){
                    b_mi="You have a mid level of risk \n please take rest \n and necessary medications";
                }
                else if (b==0){
                    b_mi="Your heath is \n normal";
                }
                recommendation.setText(b_mi);
                System.out.println("amar risk:"+b);
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
                Intent intent = new Intent(recommendation.this, MainActivity2.class);
                System.out.println(latitudee[0]);
                System.out.println(longitudee[0]);
                System.out.println(location[0]);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recommendation.this, profilepage.class);
                startActivity(intent);
            }

        });
    }
}