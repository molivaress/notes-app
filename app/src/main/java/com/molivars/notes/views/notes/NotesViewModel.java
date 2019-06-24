package com.molivars.notes.views.notes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.molivars.notes.entities.Note;

import java.util.List;
import java.util.Objects;

public class NotesViewModel extends AndroidViewModel {

    private static final String TAG = NotesViewModel.class.getCanonicalName();
    public MutableLiveData<List<Note>> notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notes = new MutableLiveData<>();
    }

    public void loadData(List<Note> list) {
        notes.postValue(list);
    }

    public void addNote(Note note) {
        if (notes.getValue() != null) {
            notes.getValue().add(note);
            loadData(notes.getValue());
        }
    }

    public void addFavorite(Note note) {
        if (notes.getValue() != null) {
            int index = notes.getValue().indexOf(note);
            notes.getValue().get(index).setFavorite(true);
            notes.postValue(notes.getValue());
        }
    }

}
