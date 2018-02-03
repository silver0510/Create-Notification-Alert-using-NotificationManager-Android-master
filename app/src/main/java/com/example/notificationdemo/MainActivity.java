package com.example.notificationdemo;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	private NotificationManager mNotificationManager;
	private int notificationID = 100;
	private int totalMessages = 0;
	private Button btnStartNotif, btnUpdateNotif, btnCancelNotif,
			btnInBoxNotification;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnStartNotif = (Button) findViewById(R.id.start);
		btnUpdateNotif = (Button) findViewById(R.id.update);
		btnCancelNotif = (Button) findViewById(R.id.cancel);
		btnInBoxNotification = (Button) findViewById(R.id.bigNotification);

		btnStartNotif.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				displayNotification();
			}
		});

		btnUpdateNotif.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateNotification();
			}
		});

		btnCancelNotif.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				cancelNotification();
			}
		});

		btnInBoxNotification.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				displayInBoxNotification();
			}
		});
	}

	@SuppressLint("NewApi")
	void displayNotification() {
		NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(
				this);
		nBuilder.setContentTitle("Notification");
		nBuilder.setContentText("You have received a new Notification");
		nBuilder.setTicker("New Message");
		nBuilder.setAutoCancel(true);
		nBuilder.setSmallIcon(R.drawable.ic_tag_logo);
		nBuilder.setNumber(++totalMessages);

		Intent intent = new Intent(this, NotificationClass.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(NotificationClass.class);

		stackBuilder.addNextIntent(intent);

		PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		nBuilder.setContentIntent(pendingIntent);

		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(notificationID, nBuilder.build());
	}

	void cancelNotification() {
		if (mNotificationManager != null) {
			mNotificationManager.cancel(notificationID);
		}
	}

	@SuppressLint("NewApi")
	void updateNotification() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this);
		mBuilder.setContentTitle("Updated Notification");
		mBuilder.setContentText("You've got updated Notification.");
		mBuilder.setTicker("Updated Notification Alert!");
		mBuilder.setAutoCancel(true);
		mBuilder.setSmallIcon(R.drawable.ic_tag_logo);

		mBuilder.setNumber(++totalMessages);
		Intent resultIntent = new Intent(this, NotificationClass.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(NotificationClass.class);

		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);

		mBuilder.setContentIntent(resultPendingIntent);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(notificationID, mBuilder.build());
	}

	void displayInBoxNotification() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this);

		mBuilder.setContentTitle("New Message");
		mBuilder.setContentText("You've received new message.");
		mBuilder.setTicker("New Message Alert!");
		mBuilder.setAutoCancel(true);
		mBuilder.setSmallIcon(R.drawable.ic_tag_logo);
		mBuilder.setNumber(++totalMessages);

		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

		String[] notificationArray = new String[6];
		notificationArray[0] = new String("First Notification...");
		notificationArray[1] = new String("Second Notification...");
		notificationArray[2] = new String("Third Notification ....");
		notificationArray[3] = new String("Fourth Notification.....");
		notificationArray[4] = new String("Fifth Notification....");
		notificationArray[5] = new String("Sixth Notification....");

		inboxStyle.setBigContentTitle("Notification Details.");

		for (int i = 0; i < notificationArray.length; i++) {
			inboxStyle.addLine(notificationArray[i]);
		}
		mBuilder.setStyle(inboxStyle);

		Intent resultIntent = new Intent(this, NotificationClass.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(NotificationClass.class);

		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(resultPendingIntent);
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(notificationID, mBuilder.build());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
