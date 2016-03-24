package ir.markazandroid.sms;

import ir.markazandroid.adaptor.ListAdaptor;
import ir.markazandroid.adaptor.Messages;
import ir.markazandroid.db.SmsHelper;
import ir.markazandroid.secretsms.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;

public class ImportSms {
	public static ArrayList<Messages> listMessage2;
	public static ArrayList<Messages> sfdg;
	public static ListAdaptor adapter;
	static SQLiteDatabase sql;
	Map<String, String> listPerson = new HashMap<String, String>();

	
	public static void sms(){
		listMessage2 = new ArrayList<Messages>();
		String name, body, number;
		Cursor cr;
		sql = SQLiteDatabase.openDatabase(
				"/data/data/ir.markazandroid.secretsms/databases/sms.db", null,
				SQLiteDatabase.OPEN_READONLY);
		cr = sql.query(SmsHelper.TABLE_SMS, null, null, null, null, null, null);
		int i = 0;
		if (cr.moveToFirst()) {
			do {
				number = null;
				name = null;
				number = cr.getString(cr.getColumnIndexOrThrow("NUMBER"));
				body = cr.getString(cr.getColumnIndexOrThrow("RECIVE"));
				name = name(number);
				if (name == null)
					name = number;
				listMessage2.add(new Messages(name, body));
				i++;
			} while (cr.moveToNext());
		} else {
			// try again
		}
	}

	private static String name(String number) {
		String cName = null;
		Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(number));
		String nameColumn[] = new String[] { PhoneLookup.DISPLAY_NAME };
		Cursor c = MainActivity.cntx.getContentResolver().query(uri,
				nameColumn, null, null, null);
		if (c == null || c.getCount() == 0)
			return cName;
		c.moveToFirst();
		cName = c.getString(0);
		return cName;

	}
}
