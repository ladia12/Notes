package sample.com.notes.db;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

import sample.com.notes.model.Note;

/**
 * Created by ladia on 29/07/16.
 */
public class DbHelper {

    public static Uri saveNote(Note note, Context context){
        ContentValues cv = new ContentValues();
        cv.put(Tables.Notes.TITLE, note.getTitle());
        cv.put(Tables.Notes.DESCRIPTION, note.getDesc());
        cv.put(Tables.Notes.COLOR, note.getColor());
        return context.getContentResolver().insert(Tables.Notes.CONTENT_URI, cv);
    }

    public static void editNote(Note note, Context context) {
        String[] PROJECTION = new String[]{note.getId()+""};
        ContentValues cv = new ContentValues();
        cv.put(Tables.Notes.TITLE, note.getTitle());
        cv.put(Tables.Notes.DESCRIPTION, note.getDesc());
        cv.put(Tables.Notes.COLOR, note.getColor());
        context.getContentResolver().update(Tables.Notes.CONTENT_URI, cv, Tables.Notes.ID + " = ? ", PROJECTION);
    }

    public static ArrayList<Note> getAllNotes(Context context) {
        ArrayList<Note> notes = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(Tables.Notes.CONTENT_URI, null, null, null, null);
        if(cursor != null){
            if(!cursor.moveToFirst() || cursor.getCount() == 0)
                return notes;
            do{
                Note note = Note.getFromCursor(cursor);
                notes.add(note);
            } while(cursor.moveToNext());
            cursor.close();
        }
        return notes;
    }

    public static Note getNote(long id, Context context) {
        String[] PROJECTION = new String[]{id+""};
        Cursor cursor = context.getContentResolver().query(Tables.Notes.CONTENT_URI, null, Tables.Notes.ID + " = ? ", PROJECTION, null);
        if(cursor != null) {
            if(cursor.moveToFirst()){
                Note note = Note.getFromCursor(cursor);
                return note;
            }
        }
        return null;
    }

    public static void deleteNote(Note note, Context context) {
        String[] PROJECTION = new String[]{note.getId()+""};
        context.getContentResolver().delete(Tables.Notes.CONTENT_URI, Tables.Notes.ID + " = ? ", PROJECTION);
    }
}
