package com.example.jemmett.bookedo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Jemmett on 27/03/2018.
 */

public class EditAdmin extends AppCompatActivity {
    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_admins);
        mAuth = FirebaseAuth.getInstance();
    }
}




