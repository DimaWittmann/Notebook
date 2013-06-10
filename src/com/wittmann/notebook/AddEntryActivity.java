package com.wittmann.notebook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddEntryActivity extends Activity
							  implements OnClickListener{

	private static final String TAG = AddEntryActivity.class.getSimpleName();
	EditText title;
	EditText date;
	EditText time;
	EditText desc;
	Button add;
	NotebookApplication app;
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.add);
		 
		 title = (EditText) findViewById(R.id.title);
		 date = (EditText) findViewById(R.id.date);
		 time = (EditText) findViewById(R.id.time);
		 desc = (EditText) findViewById(R.id.desc);
		 
		 add = (Button) findViewById(R.id.add);
		 add.setOnClickListener(this);
		 
		 app = (NotebookApplication) getApplication();
		
		 
	}
	
	@Override
	public void onClick(View v) {
		ContentValues cv = new ContentValues();
		cv.put(Entry.TITLE, title.getText().toString());
		cv.put(Entry.DATE, date.getText().toString());
		cv.put(Entry.TIME, time.getText().toString());
		cv.put(Entry.DESC, desc.getText().toString());
		SQLiteDatabase db = app.DBhelper.getWritableDatabase();
		try{
			db.insertOrThrow(DBHelper.TableName, null, cv);
		}catch(SQLException e){
			Log.e(TAG, " Can`t insert new entry");
		}finally{
			db.close();
		}
		
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.addactivitymenu, menu);
		Log.d("AddAct", "menu");
		return true;	
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()) {
		case R.id.toShowActivity:
			
			startActivity(new Intent(this, ShowEntriesActivity.class));
			break;

		default:
			break;
		}
		return true;
	}
}
