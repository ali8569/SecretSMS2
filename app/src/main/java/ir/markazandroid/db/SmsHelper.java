package ir.markazandroid.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SmsHelper extends SQLiteOpenHelper {

	public SmsHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	// TABLE
	public static final String TABLE_SMS = "SMS";
	public static final String COLUMN_ID = "_ID";
	public static final String COLUMN_NAME = "NAME";
	public static final String COLUMN_NUMBER = "NUMBER";
	public static final String COLUMN_DATE = "DATE";
	public static final String COLUMN_SEND = "SEND";
	public static final String COLUMN_RECEIVE = "RECEIVE";
	public static final String COLUMN_DRAFT = "DERAFT";
	public static final String COLUMN_STATUS = "STATUS";
	public static final String COLUMN_READ = "READ";

	// DATABASE
	private static final String DB_NAME = "sms.db";
	private static final int DB_VERSION = 1;
	private static final String DB_CREATE = "CREATE TABLE " + TABLE_SMS + " ("
			+ COLUMN_DATE + " LONG PRIMARY KEY," + COLUMN_NAME + " TEXT, "
			+ COLUMN_NUMBER + " TEXT, " + COLUMN_ID + " TEXT, " + COLUMN_SEND
			+ " TEXT, " + COLUMN_RECEIVE + " TEXT, " + COLUMN_READ + " Text, "
			+ COLUMN_DRAFT + " TEXT, " + COLUMN_STATUS + " TEXT )";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DB_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
}
