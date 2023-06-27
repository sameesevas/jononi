package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;


public class MainActivity extends AppCompatActivity {
    Button login, signup;
    EditText loginusername, loginpassword;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        loginusername = findViewById(R.id.username);
        loginpassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String em= loginusername.getText().toString().trim();
                String pw= loginpassword.getText().toString().trim();

                if (TextUtils.isEmpty(em)){
                    loginusername.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(pw)){
                    loginpassword.setError("Password is required");
                    return;
                }
                if(pw.length()<6){
                    loginpassword.setError("The password must be 6 charecters long");
                    return;
                }
//                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//                startActivity(intent);
                fAuth.signInWithEmailAndPassword(em,pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           FirebaseUser user = fAuth.getCurrentUser();
                           if (user.isEmailVerified()){

                               Toast.makeText(MainActivity.this,"Login is successful",Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                  startActivity(intent);
                           }else {
                               Toast.makeText(MainActivity.this,"Email not verified",Toast.LENGTH_SHORT).show();
                           }
                       }
                       else{
                           Toast.makeText(MainActivity.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                       }
                    }
                });

            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }


        });


    }


}
