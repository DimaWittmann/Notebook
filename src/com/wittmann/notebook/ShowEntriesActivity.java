package com.wittmann.notebook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ShowEntriesActivity extends Activity {

	NotebookApplication app;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		app = (NotebookApplication) getApplication();
		final ListView list = (ListView)findViewById(R.id.list);
		
		EntryAdapter adapter = new EntryAdapter(app, app.data.getEntries());
		
		list.setAdapter(adapter);
	}
}
