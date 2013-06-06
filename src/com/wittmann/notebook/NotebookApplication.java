package com.wittmann.notebook;

import android.app.Application;

public class NotebookApplication extends Application{
	
	NotesData data;

	public void onCreate() {
		super.onCreate();
		data = new NotesData(this);
	}
	
	public void onTerminate() {
		super.onTerminate();
	}
}
