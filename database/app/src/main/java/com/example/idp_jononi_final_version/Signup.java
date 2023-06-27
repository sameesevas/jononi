package com.example.idp_jononi_final_version;

import org.checkerframework.checker.nullness.qual.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;




public class Signup extends AppCompatActivity {
    Button signUp;
    EditText fullname,spousename,email,password,condate, dof, height, emergency;
    FirebaseAuth fAuth;
    String userID;

    ProgressBar progressBar;
    FirebaseFirestore firestore;
    DatabaseReference databaseReference;
    FirebaseDatabase  firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullname=findViewById(R.id.fullname);
        spousename=findViewById(R.id.spousename);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        condate=findViewById(R.id.condate);
        dof=findViewById(R.id.dof);
        height=findViewById(R.id.height);
        emergency=findViewById(R.id.emergency);
        signUp= findViewById(R.id.signin);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser()!= null){
            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
            finish();

        }
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fn= fullname.getText().toString();
                String pw= password.getText().toString().trim();
                String sn= spousename.getText().toString();
                String em= email.getText().toString().trim();
                String df= dof.getText().toString();
                String ht= height.getText().toString();
                String emr= emergency.getText().toString();
                String cd= condate.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]+";
                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(em);
                if (TextUtils.isEmpty(em)){
                    email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pw)){
                    password.setError("Password is required");
                    return;
                }
                if(pw.length()<6){
                    password.setError("The password must be 6 charecters long");
                    return;
                }


                fAuth.createUserWithEmailAndPassword(em, pw)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = fAuth.getCurrentUser();
                                    userID=fAuth.getCurrentUser().getUid();
                                    if (user.isEmailVerified()){
                                        Toast.makeText(Signup.this,"Authentication Successfull.",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                                    }else {
                                        user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                    Toast.makeText(Signup.this,"A verification email has been sent.",Toast.LENGTH_SHORT).show();
                                                    Toast.makeText(Signup.this, "Kindly verify your email then login",
                                                        Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Signup.this, MainActivity.class);
                                                String valueToSend = df;
                                                intent.putExtra("Age is sent", valueToSend);

                                                startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Signup.this,"verification failed due to : "+e.getMessage(),Toast.LENGTH_SHORT).show();

                                            }
                                        });
                                    }


                                    if (user.isEmailVerified()){
                                        Toast.makeText(Signup.this,"Authentication Successfull.",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
                                    }

                                    // Sign in success, update UI with the signed-in user's information
                                    DocumentReference documentReference=firestore.collection("User Information").document(userID);
                                    Map<String,String> UserInfo=new HashMap<>();
                                    UserInfo.put("Fullname",fn);
                                    UserInfo.put("Spouse name",sn);
                                    UserInfo.put("Conceived Date",cd);
                                    UserInfo.put("Date of Birth",df);
                                    UserInfo.put("Height",ht);
                                    UserInfo.put("Emergency Contact",emr);
                                    UserInfo.put("Email",em);
                                    documentReference.set(UserInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("TAG", "onSuccess: User profile is created for "+userID);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(Exception e) {
                                            Log.d("TAG", "onFailure: "+e.toString());
                                        }
                                    });

                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));





                                } else {
                                    // If sign in fails, display a message to the user
                                    Toast.makeText(Signup.this, "Error !"+ task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


//                backup bk=new backup(fn,sn,em,pw,cd,df,ht,emr);
//                reference.setValue(bk);
//                Toast.makeText(Signup.this, "Signup is successful. Your account has been created", Toast.LENGTH_SHORT).show();
//
//                Intent intent=new Intent(Signup.this, MainActivity.class);
//                startActivity(intent);
                int currentAge = calculateAge(df);
                databaseReference = FirebaseDatabase.getInstance().getReference("/App Value/Age");

                // Write data to the database
                databaseReference.setValue(currentAge);




                Toast.makeText(Signup.this, "Current age: " + currentAge, Toast.LENGTH_SHORT).show();

            }


        });



    }
    private int calculateAge(String dateOfBirth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(dateOfBirth);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar today = Calendar.getInstance();
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);

        int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }
}