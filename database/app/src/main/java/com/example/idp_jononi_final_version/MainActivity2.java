package com.example.idp_jononi_final_version;

//import androidx.annotation.NonNull;
import org.checkerframework.checker.nullness.qual.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar=findViewById(R.id.toolbar);
        cardView1=findViewById(R.id.cardview1);
        cardView2=findViewById(R.id.cardview2);
        cardView3=findViewById(R.id.cardview3);
        cardView4=findViewById(R.id.cardview4);
        cardView5=findViewById(R.id.cardview5);
        cardView6=findViewById(R.id.cardview6);
        cardView7=findViewById(R.id.cardview7);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Activity_heartrate.class);
                startActivity(intent);
            }

        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, BP_input.class);
                startActivity(intent);
            }

        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, BloodSugar_input.class);
                startActivity(intent);
            }

        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Temperature.class);
                startActivity(intent);
            }

        });
        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, BMI_input.class);
                startActivity(intent);
            }

        });
        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, FAQ.class);
                startActivity(intent);
            }

        });
        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, status_page.class);
                startActivity(intent);
            }

        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {

                    case R.id.dsh:
                        Toast.makeText(MainActivity2.this, "Home is clicked", Toast.LENGTH_SHORT).show();
                        Intent home = new Intent(MainActivity2.this, MainActivity2.class);
                        startActivity(home);
                        return true;

                    case R.id.profile:
                        Toast.makeText(MainActivity2.this, "Profile is clicked", Toast.LENGTH_SHORT).show();
                        Intent prof = new Intent(MainActivity2.this, profilepage.class);
                        startActivity(prof);
                        return true;
                    case R.id.emergency:
                        Toast.makeText(MainActivity2.this, "Emergency system is clicked", Toast.LENGTH_SHORT).show();
                        Intent emer = new Intent(MainActivity2.this, SOS.class);
                        startActivity(emer);
                        return true;
                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(MainActivity2.this, "Logging out...", Toast.LENGTH_SHORT).show();
                        Intent logot = new Intent(MainActivity2.this, MainActivity.class);
                        startActivity(logot);
                        return true;
                    case R.id.connect:
                        Toast.makeText(MainActivity2.this, "Bluetooth is connecting...", Toast.LENGTH_SHORT).show();
                        Intent blth = new Intent(MainActivity2.this, Bluetooth.class);
                        startActivity(blth);
                        return true;
                    case R.id.status_overall:
                        Toast.makeText(MainActivity2.this, "Status Page...", Toast.LENGTH_SHORT).show();
                        Intent st = new Intent(MainActivity2.this, status_page.class);
                        startActivity(st);
                        return true;


                    default:


                }
                return true;
            }
        });

    }
}