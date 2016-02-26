package com.collectivenouns.TestSuite;

import android.content.ContentValues;
import android.database.Cursor;
import android.test.AndroidTestCase;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

import com.collectivenouns.Model.DatabaseStrings;
import com.collectivenouns.Model.InternalDbContract;
import com.collectivenouns.Provider.InternalDbProvider;

public class ContentProviderTest extends ProviderTestCase2<InternalDbProvider> {

    private MockContentResolver mMockResolver;

    public ContentProviderTest() {
        super(InternalDbProvider.class, InternalDbContract.CONTENT_AUTHORITY);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMockResolver = getMockContentResolver();
    }

    public void testCanInsertAndRetrieveWords() {
        Cursor c = mMockResolver.query(InternalDbContract.queryForWords(1), null, null, null, null);
        c.moveToFirst();
        assertEquals(0, c.getCount());

        mMockResolver.insert(InternalDbContract.insertWords(), getWordTestValues("lions", "pride", 1, 1));
        mMockResolver.insert(InternalDbContract.insertWords(), getWordTestValues("flowers", "bunch",1, 2));

        c = mMockResolver.query(InternalDbContract.queryForWords(1), null, null, null, null);
        c.moveToFirst();
        assertEquals(2, c.getCount());
    }

    private ContentValues getWordTestValues(String noun, String group, int category, int categoryEntry) {
        ContentValues values = new ContentValues();
        values.put(DatabaseStrings.NOUN, noun);
        values.put(DatabaseStrings.GROUP, group);
        values.put(DatabaseStrings.CATEGORY, category);
        values.put(DatabaseStrings.CATEGORY_ENTRY, categoryEntry);
        return values;
    }
}
