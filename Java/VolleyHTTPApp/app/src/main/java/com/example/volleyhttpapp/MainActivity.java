package com.example.volleyhttpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.image);

        //String url = "http://massimocarli.eu/bus/bus_stop.json";
        String url = "https://upload.wikimedia.org/wikipedia/en/a/a9/Example.jpg";

        //getJsonAsString(url);
        /*try {
            getJson(url);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        getWebImage(url);

    }

    private void getWebImage(String url) {
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Errore Request", "Error " + error);
            }
        });

        Volley.newRequestQueue(this).add(imageRequest);

    }

    private void getJson(String url) throws JSONException {

        final ArrayList<String> busStopList = new ArrayList<>();
        final JSONObject[] locationList = {new JSONObject()};

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                StringBuilder stringBuilder = new StringBuilder();

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String busStop = jsonObject.getString("name");

                        busStopList.add(busStop);

                        JSONObject JsonLocation = jsonObject.getJSONObject("location");

                        double latitude = JsonLocation.getDouble("latitude");
                        double longitude = JsonLocation.getDouble("longitude");

                        stringBuilder.append(busStop).append("\n Latitude: ").append(latitude).append(" Longitude: ").append(longitude).append("\n\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                textView.setText(stringBuilder.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Errore Request", "Error " + error);
            }
        });

        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }

    private void getJsonAsString(String url) {

        final StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.length() > 0) {
                            textView.setText(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Errore Request", "Error " + error);
            }
        });
        Volley.newRequestQueue(this).add(request);
    }
}