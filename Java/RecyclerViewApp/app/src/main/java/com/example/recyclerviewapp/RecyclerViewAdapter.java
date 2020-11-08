package com.example.recyclerviewapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<ModelData> usersData;
    private Context context;

    public RecyclerViewAdapter(List<ModelData> userData, Context context) {
        this.usersData = userData;
        this.context = context;
    }

    // Richiamo il Layout Della riga e lo inserisco nel viewHolder tramite l'inflate, grazie al quale richiamo i vari elementi della riga
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.layout_row, parent, false);
        return new ViewHolder(view);
    }

    // setto i valori degli oggetti con dati presi da ModelData
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ModelData user = usersData.get(position);
        holder.username.setText(user.getName());
        holder.imageUser.setImageResource(user.getUserImage());

        holder.touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Position " + position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Immagine " + position, Toast.LENGTH_SHORT).show();
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Testo " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //prendo il numero degli elementi nella lista
    @Override
    public int getItemCount() {
        return usersData.size();
    }

    //Recupero gli oggetti dal layout
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView username;
        private ImageView imageUser;
        private LinearLayout touchLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.nomeText);
            imageUser = itemView.findViewById(R.id.imageView);
            touchLayout = itemView.findViewById(R.id.touchLayout);
        }
    }
}
