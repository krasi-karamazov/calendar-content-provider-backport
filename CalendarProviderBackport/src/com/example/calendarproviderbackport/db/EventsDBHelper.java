package com.example.calendarproviderbackport.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsDBHelper extends SQLiteOpenHelper {
	
	public static final String DB_NAME = "events";
	private static final String EVENTS_TABLE_NAME = "user_events";
	private static final String ID_COLUMN_NAME = "_id";
	private static final String CALENDAR_EVENT_ID = "calendar_event_id";
	private static final String CREATE_EVENTS_STATEMENT = "create table if not exists " + EVENTS_TABLE_NAME + "(" + ID_COLUMN_NAME + " integer primary key autoincrement," + CALENDAR_EVENT_ID + "integer not null)";

	public EventsDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_EVENTS_STATEMENT);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public synchronized long addEvent(long calendarId) {
		ContentValues vals = new ContentValues();
		vals.put(CALENDAR_EVENT_ID, calendarId);
		long id = getWritableDatabase().insert(EVENTS_TABLE_NAME, "null", vals);
		return id;
	}

}
