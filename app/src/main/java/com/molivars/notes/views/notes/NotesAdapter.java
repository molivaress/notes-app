package com.molivars.notes.views.notes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.molivars.notes.entities.Note;
import com.molivars.notes.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private List<Note> noteList;
    private LayoutInflater layoutInflater;

    public NotesAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.noteList = new ArrayList<>();
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
        final View view = this.layoutInflater.inflate(R.layout.note_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        final Note note = noteList.get(position);
        holder.textViewTitle.setText(note.getTitle());
        holder.textViewContent.setText(note.getContent());
        holder.viewColor.setBackgroundResource(note.getColor());
        holder.imageViewFavorite.setImageResource(note.isFavorite() ?
                R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp);
    }

    @Override
    public int getItemCount() {
        return noteList != null ? noteList.size() : 0;
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        TextView textViewContent;
        ImageView imageViewFavorite;
        View viewColor;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            textViewContent = itemView.findViewById(R.id.tv_content);
            imageViewFavorite = itemView.findViewById(R.id.iv_favorite);
            viewColor = itemView.findViewById(R.id.view_color);
        }
    }

}
