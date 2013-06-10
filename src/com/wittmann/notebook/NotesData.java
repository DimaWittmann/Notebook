package com.wittmann.notebook;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;


public class NotesData {
	
	
	public ArrayList<Entry> Entries;
	
	public NotesData(Context context){
		Entries = new ArrayList<Entry>();
	}
	public void addEntry(String title, String date, String time, String desc){
		Entries.add(new Entry(title, date, time, desc));
	}
	 
	public List<Entry> getEntries(){
		return Entries;
	}

}
