package com.example.jemmett.bookedo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Jemmett on 27/03/2018.
 */



public class NewBook extends AppCompatActivity {
    FirebaseAuth mAuth;
    private TextView BookName;
    private Button Create;
    private TextView PubDate;
    private TextView ISBN;
    private TextView Category;
    private TextView Description;
    private TextView ImageURl;
    private String booknames;
    private String PubDates;
    private String ISBNs;
    private String Categorys;
    private String Descriptions;
    private String ImageURls;
    private TextView error;




    ConnectionClass connectionClass = new ConnectionClass();

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            //If user logged in
        } else {
            //If user not logged in
            Intent intent = new Intent(this, Admin.class);
            startActivity(intent);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_book);
        mAuth = FirebaseAuth.getInstance();

        final ConnectionClass connectionClass = new ConnectionClass();
        BookName = findViewById(R.id.txtBookName);
        PubDate = findViewById(R.id.txtPubDate);
        ISBN = findViewById(R.id.txtISBN);
        Category = findViewById(R.id.txtCategory);
        Description = findViewById(R.id.txtDescription);
        ImageURl = findViewById(R.id.txtImage);
        Create = findViewById(R.id.btnCreate);
        error = findViewById(R.id.txtError);



        String name = new String();

        final Connection conn = connectionClass.CONN();

        Create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                try {

                    booknames = BookName.getText().toString();
                    PubDates = PubDate.getText().toString();
                    ISBNs = ISBN.getText().toString();
                    Categorys = Category.getText().toString();
                    Descriptions = Description.getText().toString();
                    ImageURls = ImageURl.getText().toString();


                    PreparedStatement stmt = conn.prepareStatement("Insert INTO tblBooks(BookName,PublicationDate,ISBN,Category,Description,ImageURl) Values(?,?,?,?,?,?)");
                    stmt.setString(1, booknames);
                    stmt.setString(2, PubDates);
                    stmt.setString(3, ISBNs);
                    stmt.setString(4, Categorys);
                    stmt.setString(5, Descriptions);
                    stmt.setString(6, ImageURls);

                    stmt.executeUpdate();

                    error.setText("Book added.");


                } catch (Exception e) {
                    Log.e("Error", e.toString());
                    error.setText("Error, Try again later.");
                }
            }
        });
    }
}





           //













