package com.example.jemmett.bookedo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Jemmett on 27/03/2018.
 */

public class AdminHome extends AppCompatActivity {
    FirebaseAuth mAuth;

    private EditText Name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home);

        mAuth = FirebaseAuth.getInstance();


        final Button NewAdmin = findViewById(R.id.btnNewAdmin);
        NewAdmin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome.this, NewAdmin.class);
                startActivity(intent);
            }
        });


        final Button NewBook = findViewById(R.id.btnNewBook);
        NewBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome.this, NewBook.class);
                startActivity(intent);

            }
        });

        final Button EditBook = findViewById(R.id.btnEditBook);
        EditBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome.this, AdminBookList.class);
                startActivity(intent);

            }
        });

        final Button Logout = findViewById(R.id.btnLogout);
        Logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(AdminHome.this, Admin.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //If user logged in

        } else {
            //If user not logged in
        }
    }
}




