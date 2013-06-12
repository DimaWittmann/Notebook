package com.wittmann.notebook;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class ShowEntriesActivity extends Activity {

	private static final String TAG = ShowEntriesActivity.class.getSimpleName();
	NotebookApplication app;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		app = (NotebookApplication) getApplication();
		
		final ListView list = (ListView)findViewById(R.id.list);
		String from[] = {Entry.TITLE, Entry.DESC, Entry.YEAR, Entry.HOUR}; // два останні перевизнааються в Binder
		int to[] = {R.id.entityTitle, R.id.entityDesc, R.id.entityDate, R.id.entityTime };
		SQLiteDatabase db = null;
		try{
			db =  app.DBhelper.getReadableDatabase();
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}
		String orderBy = String.format("%s DESC, %s DESC, %s DESC, %s DESC, %s DESC", Entry.YEAR, Entry.MONTH, Entry.DAY, Entry.HOUR, Entry.MINUTE);
		try{
			Cursor c = db.query(false, DBHelper.TableName, null , null, null, null, null, orderBy
					, "20", null);
			
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), 
					R.layout.row, c, from, to);
			adapter.setViewBinder(new Binder());
			list.setAdapter(adapter);
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
		}finally{
			db.close();
		}
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.showactivitymenu, menu);
		return true;	
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.addEntityMI:
			startActivity(new Intent(this, AddEntryActivity.class));
			break;

		default:
			break;
		}
		return true;
	}
	
	private class Binder implements ViewBinder{

		@Override
		public boolean setViewValue(View v, Cursor c, int i) {
			if(v.getId() == R.id.entityTime){
				((TextView) v).setText(String.format("%02d:%02d", c.getInt(5), c.getInt(6)));
				return true;
			}
			if(v.getId() == R.id.entityDate){
				((TextView) v).setText(String.format("%02d.%02d.%04d", c.getInt(4), c.getInt(3)+1, c.getInt(2)));
				return true;
			}
			return false;
			
		}
		
	}
	
}
