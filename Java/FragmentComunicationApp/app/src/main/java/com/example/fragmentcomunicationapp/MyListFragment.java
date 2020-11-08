package com.example.fragmentcomunicationapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends ListFragment {

    private List<String> ListFragments;

    public MyListFragment() {
    }

    public interface OnMyListListener {
        void onElementSelected(int position);
    }

    OnMyListListener onMyListListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            onMyListListener = (OnMyListListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("devi implementare l'interfaccia");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListFragments = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, ListFragments);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        onMyListListener.onElementSelected(position);
    }
}
