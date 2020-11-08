package com.example.dialogapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder mainDialog;
    private AlertDialog.Builder dialogChooser;
    private Snackbar snackbar;
    private ConstraintLayout view;
    private String[] dialogItems = {"Facile", "Media", "Difficile", "Molto Difficile"};
    private String result = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainDialog = new AlertDialog.Builder(this);

        /*LayoutInflater inflater = this.getLayoutInflater();
        @SuppressLint("InflateParams") View dialogView = inflater.inflate(R.layout.dialog_layout, null);
        builder.setView(dialogView);

        final EditText dialogEditText = dialogView.findViewById(R.id.edit);
        final String s = dialogEditText.getText().toString().trim();*/

        mainDialog.setMessage("Vuoi selezionare una difficoltà?");
        view = findViewById(R.id.view);

        mainDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogChooser = new AlertDialog.Builder(MainActivity.this);

                dialogChooser.setTitle("Quale Scegli?");

                dialogChooser.setSingleChoiceItems(dialogItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result = dialogItems[which];
                    }
                });

                dialogChooser.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        snackbar = Snackbar.make(view, "Bene! " + result + " Sia!", Snackbar.LENGTH_INDEFINITE);
                        snackbar.setDuration(1000).show();
                    }
                });

                dialogChooser.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialogChooser.create().show();
            }
        });

        mainDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mainDialog.create().show();
    }
}
