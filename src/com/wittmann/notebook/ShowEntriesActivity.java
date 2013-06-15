package com.wittmann.notebook;

import java.util.Calendar;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;
import android.widget.Toast;

public class ShowEntriesActivity extends Activity {

	private static final String TAG = ShowEntriesActivity.class.getSimpleName();
	public static final String orderBy = 
			String.format("%s , %s , %s , %s , %s", 
			Entry.YEAR, Entry.MONTH, Entry.DAY, Entry.HOUR, Entry.MINUTE);
	
	NotebookApplication app;
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		app = (NotebookApplication) getApplication();
		
		final ListView list = (ListView)findViewById(R.id.list);
		String from[] = {Entry.TITLE, Entry.HOUR}; // останній елемент перевизначається в Binder
		int to[] = {R.id.entityTitle, R.id.entityLeftTime};
		SQLiteDatabase db = null;
		db =  app.DBhelper.getReadableDatabase();
		
		try{
			Cursor c = db.query(DBHelper.TableName, null, null, null, null, null, orderBy);
			
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
			if(v.getId() == R.id.entityLeftTime){
				
				String [] strRes = getResources().getStringArray(R.array.approximatelyLeft);
				String leftTime = strRes[0];
				Calendar currDate = Calendar.getInstance();
				
				if(c.getInt(2) > currDate.get(Calendar.YEAR)){
					if(c.getInt(2)-currDate.get(Calendar.YEAR) == 1){
						leftTime = strRes[1];
					}else{
						leftTime = String.format(strRes[2], c.getInt(2)-currDate.get(Calendar.YEAR));
					}
				}else 
				if(c.getInt(3) > currDate.get(Calendar.MONTH)){
					if(c.getInt(3)-currDate.get(Calendar.MONTH) == 1){
						leftTime = strRes[3];
					}else{
						leftTime = String.format(strRes[4], c.getInt(3)-currDate.get(Calendar.MONTH));
					}
				}else
				if (c.getInt(4) > currDate.get(Calendar.DAY_OF_MONTH)){
					if(c.getInt(4) -currDate.get(Calendar.DAY_OF_MONTH) >1){
						leftTime = String.format(strRes[5], c.getInt(4) - currDate.get(Calendar.DAY_OF_MONTH));
					}else{
						leftTime = String.format(strRes[6], 
								24 + c.getInt(5) + (24 - currDate.get(Calendar.HOUR_OF_DAY)));
					}
				}else
				if (c.getInt(5) - currDate.get(Calendar.HOUR_OF_DAY) > 2){
					leftTime = String.format(strRes[6], c.getInt(5)- currDate.get(Calendar.HOUR_OF_DAY));
				}else
				if(c.getInt(5)-currDate.get(Calendar.HOUR_OF_DAY) == 1){
					leftTime = String.format(strRes[8], c.getInt(6) + 60 - currDate.get(Calendar.MINUTE));
				}else
				if (c.getInt(6)-currDate.get(Calendar.MINUTE) > 2){
					leftTime = String.format(strRes[8], c.getInt(6) - currDate.get(Calendar.MINUTE));
				}else
				if (c.getInt(6)-currDate.get(Calendar.MINUTE) > 0){
					leftTime = strRes[9];
				}else{
					leftTime = strRes[0];
				}
								
				
				((TextView) v).setText(leftTime);
				return true;
			}

			return false;
			
		}
		
	}
	
	
	private class EntryClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		
			
		}
		
	}
	
}
