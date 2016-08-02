package sample.com.notes.db;

import android.net.Uri;

/**
 * Created by ladia on 29/07/16.
 */
public class Tables {

    public static final String AUTHORITY = "sample.com.notes.contentprovider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY);

    public static final class Notes {
        public static final String ID = "id";
        public static final String TABLE_NAME = "notes";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String COLOR = "color";


        /**
         * Table which will be used to store key value pair, this table is replacement for shared preferences,
         */
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" +
                TABLE_NAME +
                "?" +
                NotesContentProvider.PARAMETER_NOTIFY +
                "=true");

    }
    public static final String CREATE_NOTES_TABLE =
            " CREATE TABLE " + Notes.TABLE_NAME
                    + " ( "
                    + Notes.ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + Notes.DESCRIPTION
                    + " TEXT, "
                    + Notes.COLOR
                    + " TEXT, "
                    + Notes.TITLE
                    + " TEXT );";

}
