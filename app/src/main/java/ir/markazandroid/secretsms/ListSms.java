package ir.markazandroid.secretsms;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import ir.markazandroid.adaptor.ListAdaptor;
import ir.markazandroid.adaptor.Messages;
import ir.markazandroid.db.SmsHelper;
import ir.markazandroid.sms.ImportSms;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListSms extends ListActivity implements OnItemClickListener,
		OnItemLongClickListener {
	ListAdapter adapter;
	Intent in;

	ArrayList<String> messages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ImportSms.sms();
		adapter = new ListAdaptor(this, ImportSms.listMessage2);
		setListAdapter(adapter);
		ListView listView = getListView();
		listView.setOnItemClickListener(this);
		listView.setOnItemLongClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View arg1, int position,
			long arg3) {
		// TODO Auto-generated method stub
		Messages item = (Messages) parent.getItemAtPosition(position);
		Message.setNumber(item.getNumber());
		Message.setMessage(item.getMassages(0));
		in = new Intent(this, Message.class);
		startActivity(in);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View arg1,
			int position, long arg3) {
		// TODO Auto-generated method stub
		String item = parent.getItemAtPosition(position).toString();
		Toast.makeText(this, "OnItemLongClick:" + item, Toast.LENGTH_SHORT)
				.show();
		return false;
	}
}
