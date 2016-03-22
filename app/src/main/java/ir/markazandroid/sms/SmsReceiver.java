package ir.markazandroid.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
	String addr = "";
	private Uri lookupUri;

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			// get sms objects
			Object[] pdus = (Object[]) bundle.get("pdus");
			if (pdus.length == 0) {
				return;
			}
			// large message might be broken into many
			SmsMessage[] messages = new SmsMessage[pdus.length];
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pdus.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				sb.append(messages[i].getMessageBody());
			}
			final String sender = messages[0].getOriginatingAddress();
			// Resolving the contact name from the contacts.
			Uri lookupUri = Uri.withAppendedPath(
					PhoneLookup.CONTENT_FILTER_URI, Uri.encode(sender));
			Cursor c = context.getContentResolver().query(lookupUri,
					new String[] { ContactsContract.Data.DISPLAY_NAME }, null,
					null, null);
			try {
				c.moveToFirst();
				String displayName = c.getString(0);
				String ContactName = displayName;
				final String message = sb.toString();
				Toast.makeText(context, message + "  " + ContactName,
						Toast.LENGTH_SHORT).show();
				SMSrsr.insertNew(ContactName, sender, message);
				abortBroadcast();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				c.close();
			}
		}
	}
}