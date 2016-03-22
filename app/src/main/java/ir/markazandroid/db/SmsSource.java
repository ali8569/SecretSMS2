package ir.markazandroid.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SmsSource {

	private static SQLiteDatabase mDatabase;
	private static SmsHelper mSmsHelper;
	private Context mContext;

	public SmsSource(Context context) {
		mContext = context;
		mSmsHelper = new SmsHelper(mContext);
	}

	public static SQLiteDatabase open() throws SQLException {
		mDatabase = mSmsHelper.getWritableDatabase();
		return mDatabase;
	}

}
