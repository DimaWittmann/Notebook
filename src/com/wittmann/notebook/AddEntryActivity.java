package com.wittmann.notebook;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddEntryActivity extends Activity
							  implements OnClickListener{

	private static final String TAG = AddEntryActivity.class.getSimpleName();
	
	EditText title;
	Button date;
	Button time;
	EditText desc;
	Button add;
	
	NotebookApplication app;
	
	Calendar dateAndTime = Calendar.getInstance();
	
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.add);
		 
		 title = (EditText) findViewById(R.id.title);
		 date = (Button) findViewById(R.id.date);
		 time = (Button) findViewById(R.id.time);
		 desc = (EditText) findViewById(R.id.desc);
		 
		 date.setOnClickListener(new DateButtonListener());
		 time.setOnClickListener(new TimeButtonListener());
		 
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
			Toast.makeText(getApplicationContext(), R.string.ToastAddEntry, Toast.LENGTH_SHORT).show();
		}catch(SQLException e){
			Log.e(TAG, e.getMessage());
			Toast.makeText(getApplicationContext(), R.string.ToastAddEntryError, Toast.LENGTH_SHORT).show();
		}finally{
			db.close();
		}
		
		
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.addactivitymenu, menu);
		
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
	
	
	private class DateListener implements OnDateSetListener{
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			
			Log.d(TAG, "DateListener");
			dateAndTime.set(Calendar.YEAR, year);
			dateAndTime.set(Calendar.MONTH, monthOfYear);
			dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			String Date = String.format("%02d.%02d.%d", dayOfMonth, monthOfYear+1, year);
			date.setText(Date);
		}
	}
	
	private class TimeListener implements OnTimeSetListener{

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			
			Log.d(TAG, "TimeListener");
			dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
			dateAndTime.set(Calendar.MINUTE, minute);
			
			String Time = String.format("%02d:%02d", hourOfDay, minute);
			time.setText(Time);
		}
	}
	
	private class DateButtonListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			new DatePickerDialog(AddEntryActivity.this, new DateListener(), 
					dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), 
					dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
		}
		
	}
	
	private class TimeButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			new TimePickerDialog(AddEntryActivity.this, new TimeListener(),
					dateAndTime.get(Calendar.HOUR_OF_DAY), dateAndTime.get(Calendar.MINUTE), true).show();
			
		}
		
	}
	
}
