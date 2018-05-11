package com.example.jemmett.bookedo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

/**
 * Created by Jemmett on 27/03/2018.
 */

public class NewAdmin extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView txtPassword2;
    private TextView txtError;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_admin);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtPassword2 = findViewById(R.id.txtPassword1);
        txtError = findViewById(R.id.txtError);
        mAuth = FirebaseAuth.getInstance();

            final Button CreateAdmin = findViewById(R.id.btnCreate);
            CreateAdmin.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    String emailtext = txtEmail.getText().toString();
                    String passwordtext = txtPassword.getText().toString();
                    String passwordtext2 = txtPassword2.getText().toString();

                    if (passwordtext.equals(passwordtext2) ){

                                if (!TextUtils.isEmpty(emailtext) && !TextUtils.isEmpty(passwordtext)) {

                                    mAuth.createUserWithEmailAndPassword(emailtext, passwordtext)
                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if (task.isSuccessful()) {
                                                        txtError.setText("User Created");
                                                    } else {
                                                        txtError.setText("Failed to create user please make sure your password is 6 characters long!");
                                                    }
                                                }
                                            });
                                } else {
                                    txtError.setText("Please fill out the box's");
                                }
                    } else {

                        txtError.setText("Passwords do not match!");
                    }
                }

            });
        }
}





