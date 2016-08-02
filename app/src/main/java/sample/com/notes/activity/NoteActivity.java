package sample.com.notes.activity;

import android.content.ContentUris;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import sample.com.notes.R;
import sample.com.notes.db.DbHelper;
import sample.com.notes.model.Note;

public class NoteActivity extends AppCompatActivity {


    private EditText mTitle;
    private EditText mDesc;
    private Note mNote;

    private static int EDIT_NOTE = 1;
    private static int ADD_NOTE = 2;
    private static int mNoteAction;
    private static boolean dontSave = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        dontSave = false;
        mTitle = (EditText) findViewById(R.id.title_edit_text);
        mDesc = (EditText) findViewById(R.id.desc_edit_text);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mNote = getIntent().getParcelableExtra("note");
        if(mNote != null) {
            mTitle.setText(mNote.getTitle());
            mDesc.setText(mNote.getDesc());
            mNoteAction = EDIT_NOTE;
        } else{
            mNoteAction = ADD_NOTE;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;

            case R.id.action_delete:
                dontSave = true;
                if(mNoteAction == EDIT_NOTE){
                    DbHelper.deleteNote(mNote, this);
                    finish();
                } else{
                    finish();
                }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
        if(!dontSave){
            saveNote();
        }

    }

    public void saveNote() {

        String titleText = mTitle.getText().toString().trim();
        String descText = mDesc.getText().toString().trim();
        if(!(titleText.isEmpty() && descText.isEmpty())){
            if(titleText.isEmpty()) titleText = "Title";
            if(descText.isEmpty()) descText = "Empty";
            if(mNoteAction == EDIT_NOTE){
                mNote.setTitle(titleText);
                mNote.setDesc(descText);
                DbHelper.editNote(mNote, this);
            } else{
                Note note = new Note();
                note.setTitle(titleText);
                note.setDesc(descText);
                Uri uri = DbHelper.saveNote(note, this);
                long id = ContentUris.parseId(uri);
                Note newNote = DbHelper.getNote(id, this);
                mNote = new Note(newNote);
                mNoteAction = EDIT_NOTE;
            }
        }


    }
}
