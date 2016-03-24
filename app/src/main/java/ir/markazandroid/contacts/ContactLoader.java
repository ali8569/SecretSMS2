package ir.markazandroid.contacts;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Adapter;
import android.widget.SimpleCursorAdapter;

import ir.markazandroid.adaptor.ContactAdaptor;

/**
 * Created by Ali on 23/03/2016.
 */
public class ContactLoader {
    private Activity context;
    public static final int CONTACT_LOADER_ID = 8569;
    private ContactAdaptor adapter;

    private LoaderManager.LoaderCallbacks<Cursor> contactsLoader =
            new LoaderManager.LoaderCallbacks<Cursor>() {
                @Override
                public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                    String[] projectionFields = new String[] { ContactsContract.Contacts._ID,
                            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                            ContactsContract.Contacts.PHOTO_URI,
                            ContactsContract.Contacts.LOOKUP_KEY
                    };
                    CursorLoader cursorLoader = new CursorLoader(context,
                            ContactsContract.Contacts.CONTENT_URI, // URI
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

    public ContactLoader(Activity context, ContactAdaptor adapter) {
        this.context = context;
        this.adapter = adapter;

    }

    public void loadContacts(){
        context.getLoaderManager().initLoader(CONTACT_LOADER_ID,
                new Bundle(), contactsLoader);
    }


}
