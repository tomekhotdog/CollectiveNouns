package com.collectivenouns.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.collectivenouns.Database.InternalDbHelper;
import com.collectivenouns.Model.DatabaseStrings;
import com.collectivenouns.Model.InternalDbContract;

import java.net.URI;

public class InternalDbProvider extends ContentProvider {
    private static final String TAG = InternalDbProvider.class.getSimpleName();

    private static final String TABLE_WORDS = DatabaseStrings.WORDS_TABLE_NAME;

    // URI ids
    private static final int WORDS = 0;
    private static final int WORDS_BY_CATEGORY = 1;

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private static SQLiteDatabase mDatabase;
    private InternalDbHelper mHelper;
    private Context mContext;

    static {
        URI_MATCHER.addURI(InternalDbContract.CONTENT_AUTHORITY, TABLE_WORDS, WORDS);
        URI_MATCHER.addURI(InternalDbContract.CONTENT_AUTHORITY, TABLE_WORDS + "/" + DatabaseStrings.CATEGORY + "/*", WORDS_BY_CATEGORY);
    }

    public InternalDbProvider(Context context) { this.mContext = context; }
    public InternalDbProvider() { this.mContext = getContext(); }

    private SQLiteDatabase getDatabase(boolean writable) {
        if (mDatabase == null || !mDatabase.isOpen()) {
            if(writable) {
                mDatabase = mHelper.getWritableDatabase();
            } else {
                mDatabase = mHelper.getReadableDatabase();
            }
        }
        return mDatabase;
    }

    @Override
    public boolean onCreate() {
        mHelper = new InternalDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        Log.d(TAG, "Uri: " + uri.toString());

        switch (URI_MATCHER.match(uri)) {
            case WORDS_BY_CATEGORY:
                String query = InternalDbContract.getCategoryID(uri);
                String[] args = {query};
                Log.d(TAG, "querying Words by Category: " + query);
                cursor = getDatabase(false).query(
                        TABLE_WORDS, InternalDbContract.PROJECTION_WORDS, DatabaseStrings.CATEGORY + "=?", args, null, null, sortOrder);
                break;
        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "Attempting insert operation...");
        switch(URI_MATCHER.match(uri)) {
            case WORDS:
                Log.d(TAG, "inserting into WORDS");
                getDatabase(true).insert(TABLE_WORDS, null, values);
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
