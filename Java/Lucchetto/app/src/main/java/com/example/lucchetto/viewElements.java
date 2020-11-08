package com.example.lucchetto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewElements extends AppCompatActivity {

    private ArrayList<String> list;
    private EditText textNewElement;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_element);

        listView = findViewById(R.id.listView);

        textNewElement = findViewById(R.id.textNewElement);

        list = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(viewElements.this, "Posizione: " + position + list.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void insertNewElement(View view) {
        list.add(textNewElement.getText().toString());
        textNewElement.getText().clear();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);
    }
}
