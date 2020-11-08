package com.example.parsingjsonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> stringList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(adapter);

        try {
            getJson();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getJson() throws IOException {
        InputStream inputStream = new ByteArrayInputStream(DataModel.getJsonString().getBytes(StandardCharsets.UTF_8.name()));
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        reader.beginArray();

        //inizio il parsing
        while (reader.hasNext()){
            readJson(reader);
        }

        //fine parsing
        reader.endArray();
    }

    private void readJson(JsonReader reader) throws IOException {

        reader.beginObject();

        //inizio il parsing
        while (reader.hasNext()){

            if (reader.nextName().equals("name")){
                stringList.add(reader.nextString());
            }else{
                reader.skipValue();
            }
        }

        reader.endObject();
        adapter.notifyDataSetChanged();

    }

    /*private void getJson() throws JSONException {
        String data = null;
        JSONArray jsonArray = new JSONArray(DataModel.getJsonString());

        for (int i = 0; i < jsonArray.length(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            data = String.valueOf(jsonObject.get("name"));
            stringList.add(data);
        }

        adapter.notifyDataSetChanged();
    }*/
}
