package com.wittmann.notebook;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter<Entry> {

	private final Context context;
	private final List<Entry> entries;
	
	public EntryAdapter(Context context, List<Entry> entries){
		super(context, R.layout.row, entries);
		this.context = context;
		this.entries = entries;
	}
	
	public View getView(int i, View contentView, ViewGroup parent){
		LayoutInflater inflanter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflanter.inflate(R.layout.row, parent, false);
		
		TextView title = (TextView) row.findViewById(R.id.entityTitle);
		TextView time = (TextView) row.findViewById(R.id.entityTime);
		TextView desc = (TextView) row.findViewById(R.id.entityDesc);
		
		title.setTextColor(Color.BLACK);
		title.setText(entries.get(i).title);
		
		time.setTextColor(Color.BLACK);
		time.setText(entries.get(i).time);
		
		desc.setText(entries.get(i).description);
		
		return row;
	}
}
