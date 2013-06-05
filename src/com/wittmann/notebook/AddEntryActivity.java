package com.wittmann.notebook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.add);
		 
		 title = (EditText) findViewById(R.id.title);
		 date = (EditText) findViewById(R.id.date);
		 time = (EditText) findViewById(R.id.time);
		 desc = (EditText) findViewById(R.id.desc);
		 
		 add = (Button) findViewById(R.id.add);
		 add.setOnClickListener(this);
		 
	}
	
	@Override
	public void onClick(View v) {
		Entry entry = new Entry(title.getText().toString(),
				date.getText().toString()+ time.getText().toString(), desc.getText().toString()); 
		
	}

}
