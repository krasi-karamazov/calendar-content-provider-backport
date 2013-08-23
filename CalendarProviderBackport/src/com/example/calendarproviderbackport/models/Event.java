package com.example.calendarproviderbackport.models;

import com.example.calendarproviderbackport.provider.CalendarContract;

import android.content.ContentValues;

public class Event {
	
	private long mStartTime;
	private long mEndTime;
	private String mTitle;
	private String mDescription;
	private long mCalendarId;
	private String mTimeZone;
	private String mRRule;
	private boolean mAllDay;
	private long mLastDate;
	private String mOrganizerMail;
	private String mLocation;
	private String mDuration;
	private String mRdate;
	private int mAvailability;
	private boolean mGuestsCanModify;
	private boolean mGuestsCanInviteOthers;
	private boolean mGuestsCanSeeGuests;
	private long mID;
	
	public Event(){
		
	}
	
	public long getID() {
		return mID;
	}
	
	public void setID(long id) {
		mID = id;
	}
	
	public boolean getGuestsCanModify() {
		return mGuestsCanModify;
	}

	public void setGuestsCanModify(boolean canModify) {
		this.mGuestsCanModify = canModify;
	}
	
	public boolean getGuestsCanInviteOthers() {
		return mGuestsCanInviteOthers;
	}

	public void setGuestsCanInviteOthers(boolean guestsCanInviteOthers) {
		this.mGuestsCanInviteOthers = guestsCanInviteOthers;
	}
	
	public boolean getGuestsCanSeeGuests() {
		return mGuestsCanSeeGuests;
	}

	public void setGuestsCanSeeGuests(boolean guestsCanSeeGuests) {
		this.mGuestsCanSeeGuests = guestsCanSeeGuests;
	}
	
	public int getAvailability() {
		return mAvailability;
	}
	
	public void setAvailability(int availibility) {
		mAvailability = availibility;
	}
	
	public String getRDate() {
		return mRdate;
	}

	public void setRDate(String rdate) {
		this.mRdate = rdate;
	}
	
	public String getDuration() {
		return mDuration;
	}

	public void setDuration(String duration) {
		this.mDuration = duration;
	}
	
	public String getLocation() {
		return mLocation;
	}

	public void setLocation(String location) {
		this.mLocation = location;
	}
	
	public String getOrganizerMail() {
		return mOrganizerMail;
	}

	public void setOrganizerMail(String organizerMail) {
		this.mOrganizerMail = organizerMail;
	}
	
	public long getLastDate() {
		return mLastDate;
	}

	public void setLastDate(long date) {
		this.mLastDate = date;
	}

	public String getRRule() {
		return mRRule;
	}

	public void setRRule(String rrule) {
		this.mRRule = rrule;
	}

	public boolean isAllDay() {
		return mAllDay;
	}

	public void setAllDay(boolean allDay) {
		this.mAllDay = allDay;
	}

	public long getStartTime() {
		return mStartTime;
	}

	public void setStartTime(long startTime) {
		this.mStartTime = startTime;
	}

	public long getEndTime() {
		return mEndTime;
	}

	public void setEndTime(long endTime) {
		this.mEndTime = endTime;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		this.mTitle = title;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		this.mDescription = description;
	}

	public long getCalendarId() {
		return mCalendarId;
	}

	public void setCalendarId(long calendarId) {
		this.mCalendarId = calendarId;
	}

	public String getTimeZone() {
		return mTimeZone;
	}

	public void setTimeZone(String timeZone) {
		this.mTimeZone = timeZone;
	}
	
	public ContentValues getContentValues() {
		ContentValues vals = new ContentValues();
		vals.put(CalendarContract.Events.DTSTART, mStartTime);
		vals.put(CalendarContract.Events.DTEND, mEndTime);
		vals.put(CalendarContract.Events.TITLE, mTitle);
		vals.put(CalendarContract.Events.DESCRIPTION, mDescription);
		vals.put(CalendarContract.Events.CALENDAR_ID, mCalendarId);
		vals.put(CalendarContract.Events.EVENT_TIMEZONE, mTimeZone);
		vals.put(CalendarContract.Events.RRULE, mRRule);
		vals.put(CalendarContract.Events.ALL_DAY, (mAllDay)?1:0);
		vals.put(CalendarContract.Events.LAST_DATE, mLastDate);
		vals.put(CalendarContract.Events.ORGANIZER, mOrganizerMail);
		vals.put(CalendarContract.Events.EVENT_LOCATION, mLocation);
		vals.put(CalendarContract.Events.DURATION, mDuration);
		vals.put(CalendarContract.Events.RDATE, mRdate);
		vals.put(CalendarContract.Events.AVAILABILITY, mAvailability);
		vals.put(CalendarContract.Events.GUESTS_CAN_MODIFY, (mGuestsCanModify)?1:0);
		vals.put(CalendarContract.Events.GUESTS_CAN_INVITE_OTHERS, (mGuestsCanInviteOthers)?1:0);
		vals.put(CalendarContract.Events.GUESTS_CAN_SEE_GUESTS, (mGuestsCanSeeGuests)?1:0);
		return vals;
	}
}
