package com.example.jemmett.bookedo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jemmett on 13/03/2018.
 */

public class BookInfo extends AppCompatActivity {

    private BottomNavigationView navigation;
    FirebaseAuth mAuth;
    ConnectionClass connectionClass = new ConnectionClass();

    private TextView BookName;
    private TextView PubDate;
    private TextView ISBN;
    private TextView Category;
    private TextView Description;
    public String Images;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);

        navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_update_book);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BookName = findViewById(R.id.txtName);
        PubDate = findViewById(R.id.txtPubDate);
        ISBN = findViewById(R.id.txtISBN);
        Category = findViewById(R.id.txtCategory);
        Description = findViewById(R.id.txtDescription);

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
                     Images = ImageURl1;




                } while (rs.next());
            }
            conn.close();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }

        new DownloadImageTask((ImageView) findViewById(R.id.ptIcon))
                .execute(Images);


    }

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

    class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

}