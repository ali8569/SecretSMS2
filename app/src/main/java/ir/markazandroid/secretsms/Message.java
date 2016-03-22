package ir.markazandroid.secretsms;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Message extends Activity {

	TextView tnumber;
	TextView tmessage;
	static String number2;
	static String message2;

	public static void setNumber(String number) {
		number2 = number;
	}

	public static void setMessage(String message) {
		message2 = message;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);
		vars();
		tnumber.setText(number2);
		tmessage.setText(message2);
	}

	private void vars() {
		tnumber = (TextView) findViewById(R.id.number);
		tmessage = (TextView) findViewById(R.id.message);
	}
}
