package com.wittmann.notebook;

import java.util.ArrayList;

import android.content.Context;

public class NotesData {
	
	
	public ArrayList<Entry> Entries;
	
	public NotesData(Context context){
		Entries = new ArrayList<Entry>();
	}
	public void addEntry(String title, String time, String desc){
		Entries.add(new Entry(title, time, desc));
	}
	
	public Entry[] getEntries(){
		return (Entry[]) Entries.toArray();
	}

}
