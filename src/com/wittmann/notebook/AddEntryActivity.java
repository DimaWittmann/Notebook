package com.wittmann.notebook;

import android.app.Activity;
import android.content.Intent;
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
		app.data.addEntry(title.getText().toString(), date.getText().toString()+" "+
				time.getText().toString(), desc.getText().toString());
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
