package com.mysterium.a1pra.helpinghand.mynotes;
/*
 * Author: Pratik Bhirud
 * Edited and Debugged by Prabhutva Agrawal
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mysterium.a1pra.helpinghand.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesViewHolder>{

    List<NotesModel> noteList;

    public  NotesAdapter(List<NotesModel> noteList){
        this.noteList=noteList;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.note_card, parent, false);
        NotesViewHolder holder = new NotesViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position){
        NotesModel noteItem=noteList.get(position);
        holder.populate(noteItem, position);
    }

    @Override
    public int getItemCount(){
        return noteList.size();
    }

}

