package com.example.jemmett.bookedo;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jemmett on 27/03/2018.
 */


public class AdminBookList extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_book_list);

        ConnectionClass connectionClass = new ConnectionClass();

        // Get reference of widgets from XML layout
        final ListView Books = (ListView) findViewById(R.id.lv);


        // Initializing a new String Array
        String[] Book = new String[] {

        };

        // Create a List from String Array elements
        final List<String> Books_List_Names = new ArrayList<String>(Arrays.asList(Book));
        final List<String> Books_List_Images = new ArrayList<String>(Arrays.asList(Book));

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Books_List_Names);


        // DataBind ListView with items from ArrayAdapter
        Books.setAdapter(arrayAdapter);


        Books.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                String name = (String) parent.getItemAtPosition(position);

                Intent i = new Intent(getApplicationContext(), EditBook.class);
                i.putExtra("BookName1", name);
                startActivity(i);
            }
        });



        Connection conn = connectionClass.CONN();

        try {
            String query = "SELECT * FROM tblBooks";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) // if resultset not null, I add items to itemArraylist using class created
            {
                do {
                    Books_List_Names.add(rs.getString("BookName"));
                    Books_List_Images.add(rs.getString("ImageURL"));

                } while (rs.next());
            }
            conn.close();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }






    }

}




