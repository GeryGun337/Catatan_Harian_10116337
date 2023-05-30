package com.example.catatan_harian_10116337.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.catatan_harian_10116337.NoteInterface;
import com.example.catatan_harian_10116337.R;
import com.example.catatan_harian_10116337.activity.MainActivity;
import com.example.catatan_harian_10116337.activity.addnoteActivity;
import com.example.catatan_harian_10116337.adapter.NoteAdapter;
import com.example.catatan_harian_10116337.database.DatabaseHelper;
import com.example.catatan_harian_10116337.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * 10116337 Gery Gunawan AKB IF-3
 */
public class noteFragment extends Fragment {

    private MainActivity mainActivity;
    private List<Note> note;
    private NoteInterface noteInterface;
    private RecyclerView recyclerView;
    private com.example.catatan_harian_10116337.adapter.NoteAdapter noteAdapter;
    private FloatingActionButton addButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) { //10120175 - I Wayan Widi P - IF5
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_note, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().show();

        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mynote);
        addButton = view.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), addnoteActivity.class));
        });

        read();
    }

    @Override
    public void onResume() {
        super.onResume();
        read();
    }

    private void read() {
        note = new ArrayList<Note>();
        noteInterface = new DatabaseHelper(getContext());
        Cursor cursor = noteInterface.read();
        if (cursor.moveToFirst()){
            do {
                Note n = new Note(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );

                note.add(n);
            } while (cursor.moveToNext());
        }

        noteAdapter = new NoteAdapter(note);
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
    }
}