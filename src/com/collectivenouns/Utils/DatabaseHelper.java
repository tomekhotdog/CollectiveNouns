package com.collectivenouns.Utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_PATH;
    public static final String DATABASE_NAME = "collectivenouns2.db";
    public static final String DATABASE_TABLE = "collectivenouns";
    public static final int DATABASE_VERSION = 1;

    public static final String ID = "_id";
    public static final String NOUN = "collective_noun";
    public static final String GROUP = "collective_group";
    public static final String CATEGORY = "category";
    public static final String CORRECT = "correct";
    public static final String TESTS = "tests";

    public static final int category_people = 1;
    public static final int category_animals = 2;
    public static final int category_objects = 3;
    public static final int category_nationalities = 4;

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {}

    private static DatabaseHelper sHelper;

    private Context mContext;

    public static DatabaseHelper getInstance(Context context) {
        if(sHelper == null) {
            sHelper = new DatabaseHelper(context);
        }
        return sHelper;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        mContext = context;

        if(!databaseExists()) {
            try {
                System.out.println("copying database");
                copyDataBase();
            } catch (Exception e) {
                System.out.print(e.fillInStackTrace());
            }
        } else {
            System.out.println("no copying db since it exists...");
        }
    }

    public void onCreate(SQLiteDatabase db){}

    /**
     * Returns a Cursor over the collective nouns matching the category, moves cursor to desired position
     * @param category
     * @param exercise
     * @return
     */
    public Cursor getQuestions(int category, int exercise) {
        String[] projection = {NOUN, GROUP};

        String path = DB_PATH + DATABASE_NAME;
        SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        //Log.d(DatabaseHelper.class.getSimpleName(), "NULL? : " + String.valueOf(db == null));
        System.out.println("NULL? : " + String.valueOf(db == null) + String.valueOf(db.isOpen()));
        Cursor playerCursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        //Cursor playerCursor = db.query(DATABASE_TABLE, projection, CATEGORY + " = " + String.valueOf(category), null, null, null, null, null);
        return (playerCursor.moveToFirst()) ? playerCursor : null;
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean databaseExists(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DATABASE_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch(SQLiteException e){}

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = mContext.getAssets().open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DATABASE_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

}
