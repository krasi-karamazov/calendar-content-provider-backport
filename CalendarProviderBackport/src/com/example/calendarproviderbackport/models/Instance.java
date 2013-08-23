package com.example.calendarproviderbackport.models;

public class Instance {
	
	private long mBeginTime;
	private long mEndTime;
	private int mEndDay;
	private int mEndMinute;
	private long mEventId;
	private int mStartDay;
	private int mStartMinute;
	private long mID;
	
	
	public long getID() {
		return mID;
	}
	
	public void setID(long id) {
		mID = id;
	}
	
	public long getBeginTime() {
		return mBeginTime;
	}
	public void setBeginTime(long beginTime) {
		this.mBeginTime = beginTime;
	}
	public long getEndTime() {
		return mEndTime;
	}
	public void setEndTime(long endTime) {
		this.mEndTime = endTime;
	}
	public int getEndDay() {
		return mEndDay;
	}
	public void setEndDay(int endDay) {
		this.mEndDay = endDay;
	}
	public int getEndMinute() {
		return mEndMinute;
	}
	public void setEndMinute(int endMinute) {
		this.mEndMinute = endMinute;
	}
	public long getEventId() {
		return mEventId;
	}
	public void setEventId(long eventId) {
		this.mEventId = eventId;
	}
	public int getStartDay() {
		return mStartDay;
	}
	public void setStartDay(int startDay) {
		this.mStartDay = startDay;
	}
	public int getStartMinute() {
		return mStartMinute;
	}
	public void setStartMinute(int startMinute) {
		this.mStartMinute = startMinute;
	}
	
	
	
}
