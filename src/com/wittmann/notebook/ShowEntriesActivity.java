package com.wittmann.notebook;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class ShowEntriesActivity extends Activity {

	private static final String TAG = ShowEntriesActivity.class.getSimpleName();
	NotebookApplication app;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		app = (NotebookApplication) getApplication();
		
		final ListView list = (ListView)findViewById(R.id.list);
		String from[] = {Entry.TITLE, Entry.DATE ,Entry.TIME, Entry.DESC};
		int to[] = {R.id.entityTitle, R.id.entityDate, R.id.entityTime, R.id.entityDesc};
		
		SQLiteDatabase db =  app.DBhelper.getReadableDatabase();
		try{
			Cursor c = db.query(false, DBHelper.TableName, null , null, null, null, null, 
					"'"+Entry.TIME+"'", "20", null);
			
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), 
					R.layout.row, c, from, to);
			list.setAdapter(adapter);
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			db.close();
		}
		
		
		
	}
}
