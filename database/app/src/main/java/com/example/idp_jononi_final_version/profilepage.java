package com.example.idp_jononi_final_version;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class profilepage extends AppCompatActivity {
    Button cross;
    EditText pf_name, pf_spousename,pf_height,pf_condate, pf_email,pf_age;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilepage);
        Button cross = (Button)this.findViewById(R.id.buttoncross);
        pf_condate=findViewById(R.id.conceivedate);
        pf_email=findViewById(R.id.email);
        pf_spousename=findViewById(R.id.spousename);
        pf_height=findViewById(R.id.hght);
        pf_name=findViewById(R.id.fullname);
        //pf_age=findViewById(R.id.age);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        //String receivedAge = getIntent().getStringExtra("Age is sent");
        //pf_age.setText(receivedAge);

        DocumentReference documentReference=fStore.collection("User Information").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                      pf_condate.setText(documentSnapshot.getString("Conceived Date"));
                      pf_email.setText(documentSnapshot.getString("Email"));
                      pf_spousename.setText(documentSnapshot.getString("Spouse name"));
                      pf_height.setText(documentSnapshot.getString("Height"));
                      pf_name.setText(documentSnapshot.getString("Fullname"));
            }
        });

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}