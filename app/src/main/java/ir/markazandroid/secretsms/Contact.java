package ir.markazandroid.secretsms;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import ir.markazandroid.adaptor.ContactAdaptor;
import ir.markazandroid.contacts.ContactLoader;
import ir.markazandroid.secretsms.Message;
import ir.markazandroid.secretsms.R;

public class Contact extends Activity implements AdapterView.OnItemClickListener {


    private ContactAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ListView list = (ListView) findViewById(R.id.contactlist);
        adapter = new ContactAdaptor(this,null,false);
        ContactLoader loader = new ContactLoader(this,adapter);
        loader.loadContacts();
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

  /*  private void setupCursorAdapter() {
        String[] uiBindFrom = { Contacts.DISPLAY_NAME_PRIMARY,
                Contacts.PHOTO_URI };
        int[] uiBindTo = { R.id.contactname, R.id.image };

        adapter = new SimpleCursorAdapter(
                this, R.layout.contact,
                null, uiBindFrom, uiBindTo,
                0);
    }
    */
    public void add(View v){
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        startActivity(intent);

    }


    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // SimpleCursorAdapter adapter = (SimpleCursorAdapter) parent.getAdapter();
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);
        Message.setNumber(cursor.getString(0));
        Message.setMessage(cursor.getString(1));
        Intent in = new Intent(this, Message.class);
        startActivity(in);
    }
}