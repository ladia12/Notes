package sample.com.notes.activity;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by ladia on 02/08/16.
 */
public class NotesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

    }
}
