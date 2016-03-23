package ir.markazandroid.adaptor;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.net.URI;

import ir.markazandroid.secretsms.Contact;
import ir.markazandroid.secretsms.R;

/**
 * Created by Ali on 23/03/2016.
 */
public class ContactAdaptor extends CursorAdapter {


    /**
     * Constructor that allows control over auto-requery.  It is recommended
     * you not use this, but instead .
     * When using this constructor, {@link #FLAG_REGISTER_CONTENT_OBSERVER}
     * will always be set.
     *
     * @param context     The context
     * @param c           The cursor from which to get the data.
     * @param autoRequery If true the adapter will call requery() on the
     *                    cursor whenever it changes so the most recent
     */
    public ContactAdaptor(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ContactViewHolder holder;
            View view=LayoutInflater.from(context).inflate(R.layout.contact,parent,false);
            holder = new ContactViewHolder();
            holder.iconImageView = (ImageView) view.findViewById(R.id.image);
            holder.nameLabel = (TextView) view.findViewById(R.id.contactname);
            view.setTag(holder);

        return view;
    }

    /**
     * Bind an existing view to the data pointed to by cursor
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
            ContactViewHolder holder;
             holder =(ContactViewHolder) view.getTag();

            try {
                holder.iconImageView.setImageURI(Uri.parse(cursor.getString(2)));
                holder.nameLabel.setText(cursor.getString(1));
            }
            catch (NullPointerException e){e.printStackTrace();}


    }

    private static class ContactViewHolder {
        ImageView iconImageView;
        TextView nameLabel;
    }
}
