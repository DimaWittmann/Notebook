package com.wittmann.notebook;

import android.app.Application;

public class NotebookApplication extends Application{
	
	DBHelper DBhelper;

	public void onCreate() {
		super.onCreate();
		DBhelper = new DBHelper(this);
	}

}
