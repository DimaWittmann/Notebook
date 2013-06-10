package com.wittmann.notebook;

import android.provider.BaseColumns;

public class Entry implements BaseColumns{
	public long id;
	public String title;
	public String date;
	public String time;
	public String description;
	
	
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String DATE = "date";
	public static final String TIME = "time";
	public static final String DESC = "description";
	
	public Entry(String title, String date, String time, String desc){
		this.title = title;
		this.date = date;
		this.time = time;
		this.description = desc;
	}
	
	
}
