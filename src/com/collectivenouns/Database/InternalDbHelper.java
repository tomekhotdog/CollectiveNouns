package com.collectivenouns.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.collectivenouns.Model.DatabaseStrings;

public class InternalDbHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;

    private SQLiteDatabase mDatabase;

   public InternalDbHelper(Context context) {
       super(context, DatabaseStrings.DATABASE_NAME, null, DATABASE_VERSION);
   }

    private static String WORDS_TABLE_CREATE =
            "CREATE TABLE " + DatabaseStrings.WORDS_TABLE_NAME +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DatabaseStrings.NOUN + " TEXT, " +
                    DatabaseStrings.GROUP + " TEXT, " +
                    DatabaseStrings.CATEGORY + " INTEGER, " +
                    DatabaseStrings.CATEGORY_ENTRY + " INTEGER)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WORDS_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // UNUSED
    }
}
