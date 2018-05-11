package com.example.jemmett.bookedo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Jemmett on 27/03/2018.
 */

public class EditBook extends AppCompatActivity {
    FirebaseAuth mAuth;
    ConnectionClass connectionClass = new ConnectionClass();

    private TextView BookName;
    private Button Update;
    private Button Delete;
    private TextView PubDate;
    private TextView ISBN;
    private TextView Category;
    private TextView Description;
    private TextView ImageURl;
    private TextView Error;

    private String BookNameLoad = "helo";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_book);
        mAuth = FirebaseAuth.getInstance();

        BookName = findViewById(R.id.txtBookName);
        PubDate = findViewById(R.id.txtPubDate);
        ISBN = findViewById(R.id.txtISBN);
        Category = findViewById(R.id.txtCategory);
        Description = findViewById(R.id.txtDescription);
        ImageURl = findViewById(R.id.txtImage);
        Update = findViewById(R.id.btnUpdate);
        Delete = findViewById(R.id.btnDelete);
        Error = findViewById(R.id.txtError);

        Bundle extras = getIntent().getExtras();
        final String name = extras.getString("BookName1");



        Connection conn = connectionClass.CONN();


        try {
            String query = "SELECT * FROM tblBooks WHERE BookName LIKE '" + name + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) // if result set not null, I add items to item Arraylist using class created
            {
                do {

                    String BookName1 = rs.getString(2);
                    BookName.setText(BookName1);

                    String PubDate1 = rs.getString(3);
                    PubDate.setText(PubDate1);

                    String ISBN1 = rs.getString(4);
                    ISBN.setText(ISBN1);

                    String Category1 = rs.getString(5);
                    Category.setText(Category1);

                    String Description1 = rs.getString(6);
                    Description.setText(Description1);

                    String ImageURl1 = rs.getString(7);
                    ImageURl.setText(ImageURl1);

                } while (rs.next());
            }
            conn.close();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        Update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                PubDate();
                ISBN();
                Category();
                Description();
                ImageURl();
                BookName();

                Error.setText("Book has been Updated");
            }

            private void BookName() {
                Connection conn = connectionClass.CONN();

                try {
                    String queryBookName = "UPDATE tblBooks SET BookName = '" + BookName.getText() + "' WHERE BookName LIKE '" + name + "'";
                    Statement statement = conn.createStatement();
                    statement.execute(queryBookName);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }

            private void PubDate() {
                Connection conn = connectionClass.CONN();

                try {
                    String queryPubDate = "UPDATE tblBooks SET PublicationDate = '" + PubDate.getText() + "' WHERE BookName LIKE '" + name + "'";
                    Statement statement2 = conn.createStatement();
                    statement2.execute(queryPubDate);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }

            private void ISBN() {
                Connection conn = connectionClass.CONN();

                try {

                    String queryISBN = "UPDATE tblBooks SET ISBN = '" + ISBN.getText() + "' WHERE BookName LIKE '" + name + "'";
                    Statement statement3 = conn.createStatement();
                    statement3.execute(queryISBN);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }

            private void Category() {
                Connection conn = connectionClass.CONN();

                try {

                    String queryCategory = "UPDATE tblBooks SET Category = '" + Category.getText() + "' WHERE BookName LIKE '" + name + "'";
                    Statement statement4 = conn.createStatement();
                    statement4.execute(queryCategory);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }

            private void Description() {
                Connection conn = connectionClass.CONN();

                try {
                    String queryDescription = "UPDATE tblBooks SET Description = '" + Description.getText() + "' WHERE BookName LIKE '" + name + "'";
                    Statement statement5 = conn.createStatement();
                    statement5.execute(queryDescription);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }

            private void ImageURl() {
                Connection conn = connectionClass.CONN();

                try {
                    String queryImageURl = "UPDATE tblBooks SET ImageURL = '" + ImageURl.getText() + "' WHERE BookName LIKE '" + name + "'";
                    Statement statement6 = conn.createStatement();
                    statement6.execute(queryImageURl);

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                }

            }
        });


        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Connection conn = connectionClass.CONN();

                try {
                    String queryDescription = "DELETE FROM tblBooks  WHERE BookName LIKE '" + name + "'";
                    Statement statement5 = conn.createStatement();
                    statement5.execute(queryDescription);

                    Error.setText("Book has been deleted!");

                } catch (Exception e) {
                    Log.e("Error", e.toString());
                    Error.setText("Error, Try again later.");
                }


            }
        });
    }
}



















