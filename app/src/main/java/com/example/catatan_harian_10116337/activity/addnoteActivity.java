package com.example.catatan_harian_10116337.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catatan_harian_10116337.NoteInterface;
import com.example.catatan_harian_10116337.R;
import com.example.catatan_harian_10116337.database.DatabaseHelper;
import com.example.catatan_harian_10116337.model.Note;

import java.util.Date;

/**
 * 10116337 Gery Gunawan AKB IF-3
 */
public class addnoteActivity extends AppCompatActivity {

    ImageButton button;
    EditText editTitle;
    EditText editCategory;
    EditText editDesc;
    Button addButton;
    Button deleteButton;
    TextView titleAdd;

    private NoteInterface noteInterface;
    Note note = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);
        getSupportActionBar().hide();
        note = (Note) getIntent().getSerializableExtra("Note");
        button = findViewById(R.id.back);
        editTitle = findViewById(R.id.title);
        editCategory = findViewById(R.id.category);
        editDesc = findViewById(R.id.txt_desc);
        addButton = findViewById(R.id.buttonAdd);
        deleteButton = findViewById(R.id.buttonDelete);
        titleAdd = findViewById(R.id.txt_add);
        noteInterface = new DatabaseHelper(this);

        button.setOnClickListener(v -> {
            finish();
        });

        if (note == null){
            deleteButton.setVisibility(View.GONE);

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Note title cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editCategory.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Note category cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Note content cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMM yyyy HH:mm", d.getTime());
                Note n = new Note(
                        d.getTime() + "",
                        editTitle.getText().toString(),
                        editCategory.getText().toString(),
                        editDesc.getText().toString() ,
                        "created on " + date + ""
                );

                noteInterface.create(n);
                finish();
                Toast.makeText(this, "Note successfully added", Toast.LENGTH_SHORT).show();
            });
        } else {
            editTitle.setText(note.getTitle());

            editCategory.setText(note.getCategory());

            editDesc.setText(note.getDesc());

            deleteButton.setVisibility(View.VISIBLE);
            titleAdd.setText("Change note");

            addButton.setOnClickListener(v -> {
                if (editTitle.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Note title cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editCategory.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Note category cannot be empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (editDesc.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Note content cannot be empty!", Toast.LENGTH_SHORT).show();
                }

                Date d = new Date();
                CharSequence date = DateFormat.format("EEEE, d MMMM yyyy HH:mm",d.getTime());

                note.setTitle(editTitle.getText().toString());
                note.setCategory(editCategory.getText().toString());
                note.setDesc(editDesc.getText().toString());
                note.setDate("last modified " + date + "");
                noteInterface.update(note);
                finish();
                Toast.makeText(this, "Note successfully modified", Toast.LENGTH_SHORT).show();
            });
        }

        deleteButton.setOnClickListener(v-> {
            noteInterface.delete(note.getId());
            finish();
            Toast.makeText(this, "Note successfully deleted", Toast.LENGTH_SHORT).show();
        });
    }

}