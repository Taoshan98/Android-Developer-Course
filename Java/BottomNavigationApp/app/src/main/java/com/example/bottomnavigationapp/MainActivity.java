package com.example.bottomnavigationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private TextView currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        currentPage = findViewById(R.id.currentPage);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.search:
                currentPage.setText("Search Page");
                break;

            case R.id.home:
                currentPage.setText("Home Page");
                break;

            case R.id.news:
                currentPage.setText("News Page");
                break;
        }


        return true;
    }
}
