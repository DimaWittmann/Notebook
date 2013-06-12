package com.wittmann.notebook;

import java.util.Calendar;

import android.provider.BaseColumns;

public class Entry implements BaseColumns{
	public long id;
	public String title;
	public Calendar date;
	public String description;
	
	
	public static final String ID = "_id";
	public static final String TITLE = "title";
	
	public static final String YEAR = "year";
	public static final String MONTH = "month";
	public static final String DAY = "day";
	public static final String HOUR = "hour";
	public static final String MINUTE = "minute";
	
	public static final String DESC = "description";
	
	public Entry(String title, Calendar date, String desc){
		this.title = title;
		this.date = date;
		this.description = desc;
	}
	
	
}
