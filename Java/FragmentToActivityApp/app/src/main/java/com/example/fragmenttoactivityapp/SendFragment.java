package com.example.fragmenttoactivityapp;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendFragment extends Fragment {

    public SendFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_send, container, false);

        final EditText sendText = view.findViewById(R.id.editTextSend);
        Button buttonSend = view.findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sendText.getText().toString().trim().isEmpty()) {

                    Bundle bundle = new Bundle();

                    bundle.putString("stringName", sendText.getText().toString().trim());

                    Intent intent = new Intent().putExtras(bundle);

                    intent.setComponent(new ComponentName(Objects.requireNonNull(getActivity()), ReciveActivity.class));

                    getActivity().startActivity(intent);
                }
            }
        });
        return view;
    }
}
