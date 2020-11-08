package com.example.fragmentcomunicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyListFragment.OnMyListListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.ancora, new MyListFragment()).commit();
    }

    @Override
    public void onElementSelected(int position) {
        Toast.makeText(MainActivity.this, "posizione " + position, Toast.LENGTH_SHORT).show();
    }
}
