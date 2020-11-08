package com.example.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle menuToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        menuToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigationOpen, R.string.navigationClosed);
        drawerLayout.addDrawerListener(menuToggle);
        menuToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId){
            case R.id.inbox:
                Toast.makeText(MainActivity.this, "Inbox", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sentMail:
                Toast.makeText(MainActivity.this, "Inviate", Toast.LENGTH_SHORT).show();
                break;
            case R.id.allEmails:
                Toast.makeText(MainActivity.this, "Tutti", Toast.LENGTH_SHORT).show();
                break;
            case R.id.trash:
                Toast.makeText(MainActivity.this, "Cestino", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawers();

        return true;
    }
}
