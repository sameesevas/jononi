package com.example.idp_jononi_final_version;
import android.app.PendingIntent;
import android.os.Bundle;

import org.checkerframework.checker.nullness.qual.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import android.widget.Toast;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SOS extends AppCompatActivity {
    ImageView back6 , person;
    Button confirmsos;
    EditText phone_no;
    AutoCompleteTextView autoCompleteTextView;
    String[] hospitals = { "popular","matriseba","dmc","adhunik","nogormatrisodon" };

    //EditText phone_no;
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
//    public String fetchLocationData() {
//        final String[] locationLink = new String[1];
//        DatabaseReference locationsRef = FirebaseDatabase.getInstance().getReference().child("mother0/Sensor Data");
//        locationsRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    Double latitude = locationSnapshot.child("latitude").getValue(Double.class);
//                    Double longitude = locationSnapshot.child("longitude").getValue(Double.class);
//                    locationLink[0] = "https://www.google.com/maps?q=" + latitude + "," + longitude;
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Failed to read value
//                Log.w("YOUR LOG TAG", "Failed to read value.", error.toException());
//            }
//
//        });
//        String location_mssg=locationLink[0];
//        return location_mssg;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        back6=findViewById(R.id.backbutton);
        person=findViewById(R.id.imageperson);
        confirmsos=findViewById(R.id.confirm);
        phone_no=findViewById(R.id.phone_number);
        autoCompleteTextView=findViewById(R.id.autoCompleteTextView);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("mother0/Sensor Data/latitude");
        DatabaseReference myRef2 = database.getReference("mother0/Sensor Data/longitude");
        final Double[] latitudee= new Double[1];
        final Double[] longitudee= new Double[1];
        final String[] location = new String[1];
        myRef1.addValueEventListener(new ValueEventListener() {
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
        myRef2.addValueEventListener(new ValueEventListener() {
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
//        String a= fetchLocationData();
//        System.out.println(a);
        back6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(latitudee[0]);
                System.out.println(longitudee[0]);
                System.out.println(location[0]);
                Intent intent = new Intent(SOS.this, MainActivity2.class);
                startActivity(intent);
            }

        });
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SOS.this, profilepage.class);
                startActivity(intent);
            }

        });
        confirmsos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name="Name";
                String phoneNumber=phone_no.getText().toString();
//                String phoneNumber="01753861142";
                location[0] = "https://www.google.com/maps?q=" + latitudee[0] + "," + longitudee[0];
                System.out.println("ekhan theke"+ location[0]);
                System.out.println("ekhan theke"+ latitudee[0]);
                System.out.println("ekhan theke"+ longitudee[0]);
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
                    smsManager.sendMultipartTextMessage(phoneNumber, null, parts, sentIntents, deliveryIntents);
                    Log.d("blood sugar", "message sent");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("FAIL", String.valueOf(e));
                }

                String phonumber = phone_no.getText().toString();

                String selectedValue = autoCompleteTextView.getText().toString(); // Replace 'spinner' with the ID of your drop-down list

                Intent intent = new Intent(SOS.this, SOS_confirmed.class);

                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("selectedValue", selectedValue);


                startActivity(intent);



            }

        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,hospitals );
        //Find TextView control
        AutoCompleteTextView acTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        //Set the number of characters the user must type before the drop down list is shown
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);

    }
}