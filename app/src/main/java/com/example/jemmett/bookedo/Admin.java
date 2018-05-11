package com.example.jemmett.bookedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnLogin;
    private ProgressBar LoginProgress;
    private FirebaseAuth mAuth;

    private BottomNavigationView navigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);

        mAuth = FirebaseAuth.getInstance();


        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        LoginProgress = findViewById(R.id.LoginProgress);
        btnLogin.setOnClickListener(login);

        navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_admin);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private Button.OnClickListener login = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String emailtext = txtEmail.getText().toString();
            String passwordtext = txtPassword.getText().toString();

            if(!TextUtils.isEmpty(emailtext) && !TextUtils.isEmpty(passwordtext)) {
                LoginProgress.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(emailtext, passwordtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //sendToMain();
                            Log.e("test", "logged in");

                            Intent intent = new Intent(getApplicationContext(), AdminHome.class);
                            startActivity(intent);

                        }else{
                            String error = task.getException().getMessage();
                            Toast.makeText(getApplicationContext(), "error: " + error, Toast.LENGTH_LONG).show();

                        }
                        LoginProgress.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }
    };

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Home:

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                    return true;
                case R.id.navigation_update_book:

                    Intent intent1 = new Intent(getApplicationContext(), Books.class);
                    startActivity(intent1);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_admin:

                    Intent intent2 = new Intent(getApplicationContext(), Admin.class);
                    startActivity(intent2);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_help:

                    Intent intent3 = new Intent(getApplicationContext(), Help.class);
                    startActivity(intent3);
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        }
    };

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