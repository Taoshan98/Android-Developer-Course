package com.example.viewpagerscroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private MyViewPager adapter;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleIndicator = findViewById(R.id.circleIndicator);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        adapter = new MyViewPager(this);
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);



    }
}
