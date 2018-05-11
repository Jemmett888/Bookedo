package com.example.jemmett.bookedo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainActivity extends AppCompatActivity {

    ConnectionClass connectionClass = new ConnectionClass();
    private BottomNavigationView navigation;
    private TextView BookOne;
    private TextView BookTwo;
    private TextView BookThree;
    public String Image;
    public String Image2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_Home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BookOne = findViewById(R.id.txtBookOne);
        BookTwo = findViewById(R.id.txtBookTwo);
       // BookThree = findViewById(R.id.txtBookThree);

        BookOne();
        BookTwo();

        new DownloadImageTask((ImageView) findViewById(R.id.ptBookOne))
                .execute(Image);

        new DownloadImageTask((ImageView) findViewById(R.id.ptBookTwo))
                .execute(Image2);




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


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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

    private void BookOne() {

        Connection conn = connectionClass.CONN();

        try {
            String query = "SELECT TOP 1 * FROM tblBooks ORDER BY ID DESC";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) // if result set not null, I add items to item Arraylist using class created
            {
                do {

                    String BookName1 = rs.getString(2);
                    BookOne.setText(BookName1);

                    String ImageURl1 = rs.getString(7);
                    Image = ImageURl1;


                } while (rs.next());
            }
            conn.close();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }

    private void BookTwo() {

        Connection conn = connectionClass.CONN();

        try {
            String query = "SELECT TOP 2 * FROM tblBooks ORDER BY ID DESC";
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query);

            if (rs2.next()) // if result set not null, I add items to item Arraylist using class created
            {
                do {

                    String BookName2 = rs2.getString(2);
                    BookTwo.setText(BookName2);

                    String ImageURl2 = rs2.getString(7);
                    Image2 = ImageURl2;


                } while (rs2.next());
            }
            conn.close();
        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
    }
}

