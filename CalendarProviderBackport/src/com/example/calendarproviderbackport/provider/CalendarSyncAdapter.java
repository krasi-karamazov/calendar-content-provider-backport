package com.example.calendarproviderbackport.provider;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SyncResult;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class CalendarSyncAdapter extends AbstractThreadedSyncAdapter {

    public CalendarSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
    }

    @SuppressLint({ "InlinedApi", "NewApi" })
	public long doCreateCalendar(Account account, String calendarName, ContentValues cv) {
        ContentResolver cr = getContext().getContentResolver();
        Uri creationUri = asSyncAdapter(CalendarContract.Calendars.CONTENT_URI, account.name, account.type);
        Uri created = cr.insert(creationUri, cv);
        long id = Long.parseLong(created.getLastPathSegment());
        return id;
    }

    @SuppressLint("InlinedApi")
	private Uri asSyncAdapter(Uri uri, String account, String accountType) {
    	Uri.Builder builder = uri.buildUpon();
    	builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, account);
    	builder.appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, accountType);
		builder.appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true");
    	
        return builder.build();
    }
}
