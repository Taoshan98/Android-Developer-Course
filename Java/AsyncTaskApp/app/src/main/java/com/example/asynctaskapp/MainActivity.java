package com.example.asynctaskapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setTitle("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for(int i = 0; i < 10; i++){

                try {
                    publishProgress(i * 10);
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
        }

    }
}
