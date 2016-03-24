package ir.markazandroid.secretsms;

import java.io.File;

import ir.markazandroid.contacts.Contact;
import ir.markazandroid.db.SmsSource;
import ir.markazandroid.sms.SMSrsr;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	public static TextView tv;
	public static Context cntx;
	EditText phone, matn;
	Button bt, button1, contact;
	TextView inbox, sqlview;
	SmsManager smsmanager;
	SmsSource smsDB;
	SQLiteDatabase sql;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		varibales();
		cntx = this;
		// convert to background code & only one time
		if (!checkDataBase()) {
			smsDB = new SmsSource(MainActivity.this);
			sql = smsDB.open();
			SMSrsr.setCr(this.getContentResolver());
			SMSrsr.readAllSms(sql);
		}

	}

	public boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			checkDB = SQLiteDatabase.openDatabase(
					"/data/data/ir.markazandroid.secretsms/databases/sms.db",
					null, SQLiteDatabase.OPEN_READONLY
							| SQLiteDatabase.NO_LOCALIZED_COLLATORS);

		} catch (SQLiteException e) {

			// database does't exist yet.
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	private void varibales() {
		phone = (EditText) findViewById(R.id.phone);
		matn = (EditText) findViewById(R.id.matn);
		bt = (Button) findViewById(R.id.bt);
		bt.setOnClickListener(this);
		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.sqlview);
		contact = (Button) findViewById(R.id.contactbutt);
		contact.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt:

			smsmanager = SmsManager.getDefault();
			String text = matn.getText().toString();
			String phone = this.phone.getText().toString();
			if (!phone.isEmpty() && !text.isEmpty())
				smsmanager.sendTextMessage(phone, null, text, null, null);
			break;
		case R.id.button1:
			Intent intent = new Intent(this, ListSms.class);
			startActivity(intent);
			break;
		case R.id.contactbutt:
			Intent intent2 = new Intent(this, Contact.class);
			startActivity(intent2);
			break;
		}
	}
}