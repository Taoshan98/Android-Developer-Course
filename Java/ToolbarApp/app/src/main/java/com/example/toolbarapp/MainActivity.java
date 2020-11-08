package com.example.toolbarapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId){
            case R.id.addNewItem:
                Toast.makeText(MainActivity.this, "Aggiungi", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settingsItem:
                Toast.makeText(MainActivity.this, "Impostazioni", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                Toast.makeText(MainActivity.this, "Back", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
