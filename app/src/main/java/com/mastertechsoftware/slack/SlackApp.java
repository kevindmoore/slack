package com.mastertechsoftware.slack;

import com.mastertechsoftware.slack.log.Logger;
import com.mastertechsoftware.slack.models.Profile;
import com.mastertechsoftware.slack.models.User;
import com.mastertechsoftware.slack.sql.DatabaseHelper;
import com.mastertechsoftware.slack.sql.DatabaseManager;

import android.app.Application;

/**
 * Application for initializing App
 */
public class SlackApp extends Application {

	private DatabaseHelper databaseHelper;

	@Override
	public void onCreate() {
		super.onCreate();
		setLogInfo();
		setupDatabase();
	}

	private void setupDatabase() {
		DatabaseManager.getInstance(this);
		databaseHelper = new DatabaseHelper("Slack");
		databaseHelper.createDatabase("User", User.class, Profile.class);
	}

	public void setLogInfo() {
		Logger.setApplicationTag("SlackApp");
		Logger.setAddClassNameToMessage(true);
	}

	public DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
}
