package com.example.ansanewsapp;

import android.content.Context;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

   // private List<Note> noteInfo;
    private Context context;

    public RecyclerViewAdapter(/*List<Note> noteInfo,*/ Context context) {
        //this.noteInfo = noteInfo;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        /*final Note note = noteInfo.get(position);
        holder.noteTitleList.setText(note.getNoteTitle());
        holder.noteContentList.setText(note.getNoteContent());
        holder.noteDateList.setText(note.getNoteDate());

        holder.touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailsNoteActivity.getDetailsIntent(context, note));
                notifyItemChanged(holder.getAdapterPosition());
            }
        });

        holder.noteTitleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailsNoteActivity.getDetailsIntent(context, note));
            }
        });

        holder.noteContentList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailsNoteActivity.getDetailsIntent(context, note));
            }
        });

        holder.noteDateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(DetailsNoteActivity.getDetailsIntent(context, note));
            }
        });*/

        holder.expandNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.expandableView.getVisibility() == View.GONE) {
                    Log.d("expand", "less");
                    TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                    holder.expandableView.setVisibility(View.VISIBLE);
                    holder.expandNote.setBackgroundResource(R.drawable.ic_expand_less_black);
                } else {
                    Log.d("expand", "more");
                    TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                    holder.expandableView.setVisibility(View.GONE);
                    holder.expandNote.setBackgroundResource(R.drawable.ic_expand_more_black);
                }
            }
        });
    }

    //prendo il numero degli elementi nella lista
    @Override
    public int getItemCount() {
        return 0;
    }

    //Recupero gli oggetti dal layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout touchLayout;

        private CardView cardView;
        private TextView noteTitleList;
        private TextView noteDateList;
        private Button expandNote;
        private ConstraintLayout expandableView;
        private TextView noteContentList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            touchLayout = itemView.findViewById(R.id.touchLayout);

            cardView = itemView.findViewById(R.id.cardView);
            noteTitleList = itemView.findViewById(R.id.noteTitleList);
            noteDateList = itemView.findViewById(R.id.noteDateList);
            expandNote = itemView.findViewById(R.id.expandNote);
            expandableView = itemView.findViewById(R.id.expandableView);
            noteContentList = itemView.findViewById(R.id.noteContentList);
        }
    }
}