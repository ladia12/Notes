package sample.com.notes.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import sample.com.notes.db.Tables;

/**
 * Created by ladia on 29/07/16.
 */
public class Note implements Parcelable {

    private int id;
    private String title;
    private String desc;
    private String color;

    public Note() {
    }

    public Note(Note newNote){
        this.id = newNote.id;
        this.title = newNote.title;
        this.desc = newNote.desc;
        this.color = newNote.color;
    }

    public Note(int id,  String title, String desc, String color) {
        this.id  =id;
        this.title = title;
        this.desc = desc;
        this.color = color;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
        color = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Note getFromCursor(Cursor c){
        Note note = new Note();
        note.id = c.getInt(c.getColumnIndexOrThrow(Tables.Notes.ID));
        note.title = c.getString(c.getColumnIndexOrThrow(Tables.Notes.TITLE));
        note.desc = c.getString(c.getColumnIndexOrThrow(Tables.Notes.DESCRIPTION));
        note.color = c.getString(c.getColumnIndexOrThrow(Tables.Notes.COLOR));
        return note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(color);
    }
}
