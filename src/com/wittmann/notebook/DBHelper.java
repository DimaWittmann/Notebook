package com.wittmann.notebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	private static final String TAG = DBHelper.class.getSimpleName();

	public static final String TableName = "assignments";
	private static final String DBName = "assignments.db";
	private static final int DBVersion = 1;
	Context context;

	public DBHelper(Context context) {
		super(context, DBName, null, DBVersion);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG, "onCreate");
		db.execSQL("CREATE TABLE " + TableName + "(" + Entry.ID
				+ " integer primary key autoincrement not null, " + Entry.TITLE
				+ " text, " + Entry.DATE + " text, " + Entry.TIME + "text,"
				+ Entry.DESC + " text);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.d(TAG, "onUpgrade");

	}

}
