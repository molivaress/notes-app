package com.molivars.notes.views.notes;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.molivars.notes.entities.Note;
import com.molivars.notes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NotesFragment extends Fragment {
    private int numColumns = 2;
    private NotesAdapter notesAdapter;
    RecyclerView mRecyclerViewNotes;
    FloatingActionButton mButtonAddNote;

    NotesViewModel notesViewModel;

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        mRecyclerViewNotes = view.findViewById(R.id.rv_notes);
        mButtonAddNote = view.findViewById(R.id.fab_add_note);
        notesViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(NotesViewModel.class);
        this.notesAdapter = new NotesAdapter(Objects.requireNonNull(getContext()));
        this.setUpRecyclerViewNotes(view);
        notesViewModel.loadData(generateData());
        observeViewModel();
        mButtonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesViewModel.addNote(new Note(getString(R.string.title_third), getString(R.string.content_third), false,
                        android.R.color.darker_gray));
            }
        });
        return view;
    }

    private void observeViewModel() {
        notesViewModel.notes.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> list) {
                notesAdapter.setNoteList(list);
            }
        });
    }

    private void setUpRecyclerViewNotes(View view) {
        RecyclerView.LayoutManager layoutManager;
        if (view.getId() == R.id.notes_portrait) {
            layoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL, false);
        } else {
            DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            numColumns = (int) (dpWidth / 180);
            layoutManager = new StaggeredGridLayoutManager(numColumns, StaggeredGridLayoutManager.VERTICAL);
        }
        mRecyclerViewNotes.setLayoutManager(layoutManager);
        mRecyclerViewNotes.setAdapter(notesAdapter);
    }

    private List<Note> generateData() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(getString(R.string.title_first), getString(R.string.content_first_note), false,
                android.R.color.holo_blue_light));
        notes.add(new Note(getString(R.string.title_second), getString(R.string.content_second), true,
                android.R.color.holo_red_dark));
        notes.add(new Note(getString(R.string.title_third), getString(R.string.content_third), false,
                android.R.color.holo_green_light));
        notes.add(new Note(getString(R.string.title_first), getString(R.string.content_first_note), false,
                android.R.color.holo_blue_light));
        notes.add(new Note(getString(R.string.title_second), getString(R.string.content_second), true,
                android.R.color.holo_red_dark));
        notes.add(new Note(getString(R.string.title_third), getString(R.string.content_third), false,
                android.R.color.holo_green_light));
        return notes;
    }

}
