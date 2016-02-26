package com.collectivenouns.Model;

import android.net.Uri;
import android.provider.ContactsContract;

public class InternalDbContract {

    public final static String DATABASE_NAME = "content";
    public final static String DATABASE_PATH = "/data/data/com.collectivenouns/databases/content";

    public static final String CONTENT_AUTHORITY = "collective_nouns";
    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String[] PROJECTION_WORDS = {
            DatabaseStrings.NOUN,
            DatabaseStrings.GROUP,
            DatabaseStrings.CATEGORY,
            DatabaseStrings.CATEGORY_ENTRY
    };

    public static Uri queryForWords() {
        return CONTENT_URI.buildUpon().appendPath(DatabaseStrings.WORDS_TABLE_NAME).build();
    }

    public static Uri queryForWords(int category) {
        return CONTENT_URI.buildUpon().appendPath(DatabaseStrings.WORDS_TABLE_NAME).
                appendPath(DatabaseStrings.CATEGORY).appendPath(String.valueOf(category)).build();
    }

    public static Uri queryForWord(int category, int entry) {
        return CONTENT_URI.buildUpon().appendPath(DatabaseStrings.WORDS_TABLE_NAME).
                appendPath(DatabaseStrings.CATEGORY).appendPath(String.valueOf(category)).
                appendPath(DatabaseStrings.CATEGORY_ENTRY).appendPath(String.valueOf(entry)).build();
    }

    public static Uri insertWords() {
        return CONTENT_URI.buildUpon().appendPath(DatabaseStrings.WORDS_TABLE_NAME).build();
    }

    public static String getCategoryID(Uri uri) {
        // TODO: more general solution, this only works for URI from method queryForWords(int category)
        return uri.getLastPathSegment();
    }

    public static int getCategoryEntry(Uri uri) {
        // TODO: more general solution, analyse URI and return correct value
        return 0;
    }

}
