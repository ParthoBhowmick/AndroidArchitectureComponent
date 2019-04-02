package com.example.androidarchitecture;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version =1)
public abstract class NoteDatabase extends RoomDatabase {

    // by declaring a class using "static" , create a singleton.. only one instance of the class can be made
    private static NoteDatabase instance;

    public abstract NoteDao mNoteDao();

    // using synchronized two instance can't be created accidently..  only one thread at a time
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private NoteDao mNoteDao;

        private PopulateDbAsyncTask(NoteDatabase db) {
            //not understanding
            mNoteDao = db.mNoteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            mNoteDao.insert(new Note("Title #1", "Description #1" , 1));
            mNoteDao.insert(new Note("Title #2", "Description #2" , 2));
            mNoteDao.insert(new Note("Title #3", "Description #3" , 3));

            return null;

        }
    }

}
