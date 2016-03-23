package ir.markazandroid.contacts;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import ir.markazandroid.secretsms.R;

public class Contact extends AppCompatActivity {

    private SimpleCursorAdapter adapter;
    public static final int CONTACT_LOADER_ID = 8569;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setupCursorAdapter();
        getLoaderManager().initLoader(CONTACT_LOADER_ID,
                new Bundle(), contactsLoader);
        ListView list = (ListView) findViewById(R.id.contactlist);
        list.setAdapter(adapter);
    }

    private void setupCursorAdapter() {
        String[] uiBindFrom = { Contacts.DISPLAY_NAME_PRIMARY,
                Contacts.PHOTO_URI };
        int[] uiBindTo = { R.id.contactname, R.id.image };

        adapter = new SimpleCursorAdapter(
                this, R.layout.contact,
                null, uiBindFrom, uiBindTo,
                0);
    }
    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    String[] projectionFields = new String[] { ContactsContract.Contacts._ID,
                            Contacts.DISPLAY_NAME,
                            Contacts.PHOTO_URI };
                    CursorLoader cursorLoader = new CursorLoader(Contact.this,
                            Contacts.CONTENT_URI, // URI
                            projectionFields, // projection fields
                            null, // the selection criteria
                            null, // the selection args
                            null // the sort order
                    );
                    return cursorLoader;
                }


                @Override
                public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
                    adapter.swapCursor(cursor);
                }


                @Override
                public void onLoaderReset(Loader<Cursor> loader) {
                    adapter.swapCursor(null);
                }
            };

    public void add(View v){
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        startActivity(intent);

    }



}