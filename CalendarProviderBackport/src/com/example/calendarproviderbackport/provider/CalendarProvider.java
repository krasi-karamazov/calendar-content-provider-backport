package com.example.calendarproviderbackport.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.calendarproviderbackport.models.Event;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;

public class CalendarProvider {
	private Context mContext;
	public CalendarProvider(Context context) {
		mContext = context;
	}

	public Map<Long, String> getCalendars() {
		ContentResolver cr = mContext.getContentResolver();
		String[] projection = new String[] { CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME };
        String selection = CalendarContract.Calendars.VISIBLE +   "=1";
        final Map<Long, String> calendarsMap = new HashMap<Long, String>();
		Cursor cursor = cr.query(CalendarContract.Calendars.CONTENT_URI, projection, selection, null, null);
		while(cursor.moveToNext()) {
			Log.d("CALENDARS PROVIDER", cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Calendars.NAME)));
			calendarsMap.put(Long.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Calendars._ID))), cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Calendars.NAME)));
		}
		cursor.close();
		return calendarsMap;
	}
	
	public long getCalendarIdByName(String name) {
		long id = -1;
		ContentResolver cr = mContext.getContentResolver();
		String[] projection     = new String[] { CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME };
        String selection        = CalendarContract.Calendars.NAME +   "='" + name + "'";
		Cursor cursor = cr.query(CalendarContract.Calendars.CONTENT_URI, projection, selection, null, null);
		if(cursor.moveToFirst()) {
			id = Long.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Calendars._ID)));
		}
		cursor.close();
		return id;
	}

	public String getCalendarNameById(long id) {
		String name = "";
		ContentResolver cr = mContext.getContentResolver();
		String[] projection = new String[] { CalendarContract.Calendars._ID, CalendarContract.Calendars.NAME };
        String selection = CalendarContract.Calendars._ID +   "=" + Long.valueOf(id).toString();
		Cursor cursor = cr.query(CalendarContract.Calendars.CONTENT_URI, projection, selection, null, null);
		if(cursor.moveToFirst()) {
			name = cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Calendars.NAME));
		}
		cursor.close();
		return name;
	}

	public List<Event> getEventsInCalendar(long calendarId) {
		ContentResolver cs = mContext.getContentResolver();
		final List<Event> events = new ArrayList<Event>();
		Uri uri = CalendarContract.Events.CONTENT_URI;
		String selection = CalendarContract.Events.CALENDAR_ID + "='" + calendarId + "'";
		Cursor cursor = cs.query(uri, null, selection, null, null);
		while(cursor.moveToNext()) {
			Event ev = new Event();
			ev.setCalendarId(calendarId);
			ev.setAllDay((cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Events.ALL_DAY))== 1)?true:false);
			ev.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Events.DESCRIPTION)));
			ev.setStartTime(cursor.getLong(cursor.getColumnIndexOrThrow(CalendarContract.Events.DTSTART)));
			ev.setEndTime(cursor.getLong(cursor.getColumnIndexOrThrow(CalendarContract.Events.DTEND)));
			ev.setLastDate(cursor.getLong(cursor.getColumnIndexOrThrow(CalendarContract.Events.LAST_DATE)));
			ev.setRRule(cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Events.RRULE)));
			ev.setTimeZone(cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Events.EVENT_TIMEZONE)));
			ev.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(CalendarContract.Events.TITLE)));
			ev.setGuestsCanInviteOthers((cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS))== 1)?true:false);
			ev.setGuestsCanModify((cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Events.GUESTS_CAN_MODIFY))== 1)?true:false);
			ev.setGuestsCanSeeGuests((cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Events.GUESTS_CAN_SEE_GUESTS))== 1)?true:false);
			if(!CalendarContract.legacyApi){
				ev.setAvailability(cursor.getInt(cursor.getColumnIndexOrThrow(CalendarContract.Events.AVAILABILITY)));
			}
			events.add(ev);
		}
		cursor.close();
		return events;
	}

	public long insertCalendar(Account acc, String calName) {
		ContentValues cv = new ContentValues();
		cv.put(CalendarContract.Calendars.NAME, calName);
		cv.put(CalendarContract.Calendars.ACCOUNT_NAME, acc.name);
		cv.put(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL);
		cv.put(CalendarContract.Calendars.NAME, acc.name + "new");
		cv.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, acc.name + "new");
		cv.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.RED);
		cv.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
		cv.put(CalendarContract.Calendars.OWNER_ACCOUNT, true);
		cv.put(CalendarContract.Calendars.VISIBLE, 1);
		cv.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
		final CalendarSyncAdapter syncAdapter = new CalendarSyncAdapter(mContext, true);
		return syncAdapter.doCreateCalendar(acc, calName, cv);
	}
}
