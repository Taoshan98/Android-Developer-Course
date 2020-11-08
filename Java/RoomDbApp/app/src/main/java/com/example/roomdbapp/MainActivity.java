package com.example.roomdbapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.roomdbapp.Utils.User;
import com.example.roomdbapp.Utils.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listToAdapter = new ArrayList<>();
    private ListView listView;
    private Button button;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, listToAdapter);
        listView.setAdapter(adapter);

        final UserDatabase db = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "database")
                .allowMainThreadQueries()
                .build();

        List<User> listUsers = new ArrayList<>();
        listUsers.add(new User("Giovanni", "Con La Chitarra"));
        listUsers.add(new User("Marco", "De Marco"));
        listUsers.add(new User("Luca", "De Luca"));
        listUsers.add(new User("Bruce", "Wayne"));

        //Converto la lista in array per farla interaggire con l'iterfaccia DAO
        final User[] array = listUsers.toArray(new User[listUsers.size()]);
        db.UserDAO().insertAll(array);

        List<User> listUsersAll = db.UserDAO().getAll();

        for (User user: listUsersAll){
            listToAdapter.add(user.getFirstName() + " " + user.getLastName());
        }

        adapter.notifyDataSetChanged();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User ("a", "a");
                db.UserDAO().insertAll(user);
                listToAdapter.add(user.getFirstName() + " " + user.getLastName());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
