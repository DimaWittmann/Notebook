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
	private static final int DBVersion = 2;
	public Context context;
	
	public String query = String.format(
			"CREATE TABLE IF NOT EXISTS %s (%s integer primary key autoincrement not null, " +
			"%s text, %s integer, %s integer, %s integer, %s integer, %s integer, %s  text)", 
			TableName, Entry.ID, Entry.TITLE,  Entry.YEAR, Entry.MONTH, Entry.DAY, Entry.HOUR, 
			Entry.MINUTE, Entry.DESC);

	public DBHelper(Context context) {
		super(context, DBName, null, DBVersion);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("drop table if exists %s", TableName));
		onCreate(db);
	}

	public void onOpen(SQLiteDatabase db){	
		db.execSQL(query);
	}
}
