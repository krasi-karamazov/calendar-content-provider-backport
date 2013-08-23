package com.example.calendarproviderbackport;

import java.util.List;

import com.example.calendarproviderbackport.models.Event;
import com.example.calendarproviderbackport.provider.CalendarProvider;

import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;

public class MainActivity extends Activity {

	private CalendarProvider mProvider;
	private static final String CALENDAR_ID_PREFS_KEY = "calendar_id";
	private static long CALENDAR_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initCalendar();
		
	}
	
	private void initCalendar() {
		
		mProvider = new CalendarProvider(this);
		
		SharedPreferences prefs = getSharedPreferences(MainActivity.class.getSimpleName(), MODE_PRIVATE);
		if(!prefs.contains(CALENDAR_ID_PREFS_KEY)){
			addCalendar(prefs);
			
		}else{
			CALENDAR_ID = prefs.getLong(CALENDAR_ID_PREFS_KEY, -1);
			List<Event> ev = mProvider.getEventsInCalendar(CALENDAR_ID);
		}
	}
	
	private void addCalendar(SharedPreferences prefs) {
		Account account = null;;
		
		AccountManager accountsManager = (AccountManager)getSystemService(ACCOUNT_SERVICE);
		
		Account[] accounts = accountsManager.getAccounts();
		for(Account acc : accounts) {
			if(acc.type.equalsIgnoreCase("com.google")) {
				account = acc;
				break;
			}
		}
		long id = mProvider.insertCalendar(account, account.name + "_infs");
		final Editor editor = prefs.edit();
		editor.putLong(CALENDAR_ID_PREFS_KEY, id);
		editor.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	private OnDateSelectedListener getOnDateSelectedListener() {
//		return new OnDateSelectedListener() {
//			@Override
//			public void onDateSelected(Date date) {
//				
//			}
//		};
//	}

}
