package ir.markazandroid.sms;

import ir.markazandroid.db.SmsHelper;
import ir.markazandroid.db.SmsSource;
import ir.markazandroid.secretsms.MainActivity;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

//import phone sms from android database to app database
public class SMSrsr {

	private static ContentResolver cr;
	public static Map<String, String> lastSMS = new HashMap<String, String>();
	static ContentValues values;

	public static ContentResolver getCr() {
		return cr;
	}

	public static void setCr(ContentResolver cr) {
		SMSrsr.cr = cr;
	}
	
	public static void insertNew(String  person,String  number,String  body,Long date){
		SQLiteDatabase smsDB=SmsSource.open();
		values = new ContentValues();
		values.put(SmsHelper.COLUMN_DATE, date);
		values.put(SmsHelper.COLUMN_NAME, person);
		values.put(SmsHelper.COLUMN_NUMBER, number);
		values.put(SmsHelper.COLUMN_RECIVE, body);
		smsDB.insert(SmsHelper.TABLE_SMS, null, values);
	}

	public static void readAllSms(SQLiteDatabase smsDB) {

		Cursor cursor = getCr().query(Uri.parse("content://sms"), null, null,
				null, "date desc");
		if (cursor.moveToFirst()) {
			do {
				String body = cursor.getString(cursor
						.getColumnIndexOrThrow("body"));
				String person = cursor.getString(cursor
						.getColumnIndexOrThrow("person"));
				String number = cursor.getString(cursor
						.getColumnIndexOrThrow("address"));
				String _id = cursor.getString(cursor
						.getColumnIndexOrThrow("_id"));
				String date=cursor.getString(cursor.getColumnIndexOrThrow("date"));
				MainActivity.tv.setText("\n"+date);
				values = new ContentValues();
				values.put(SmsHelper.COLUMN_ID, _id);
				values.put(SmsHelper.COLUMN_NAME, person);
				values.put(SmsHelper.COLUMN_NUMBER, number);
				values.put(SmsHelper.COLUMN_RECIVE, body);
				smsDB.insert(SmsHelper.TABLE_SMS, null, values);

			} while (cursor.moveToNext());
		} else {
			// try again
		}

		Toast.makeText(MainActivity.cntx, "Make database", Toast.LENGTH_LONG)
				.show();
		cursor.close();
		smsDB.close();
	}
}