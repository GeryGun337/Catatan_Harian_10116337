package com.example.catatan_harian_10116337;

import android.database.Cursor;

import com.example.catatan_harian_10116337.model.Note;

/**
 * 10116337 Gery Gunawan AKB IF-3
 */
public interface NoteInterface {
    public Cursor read();
    public boolean create(Note note);
    public boolean update(Note note);
    public boolean delete(String id);
}
