package com.example.jemmett.bookedo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jemmett on 13/03/2018.
 */

public class Help extends AppCompatActivity {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_help);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



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
}