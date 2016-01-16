package com.mastertechsoftware.slack;

import com.mastertechsoftware.slack.log.Logger;
import com.mastertechsoftware.slack.models.Profile;
import com.mastertechsoftware.slack.models.User;
import com.mastertechsoftware.slack.sql.DatabaseHelper;
import com.mastertechsoftware.slack.sql.DatabaseManager;
import com.mastertechsoftware.slack.users.SlackUserDatabaseStorage;

import android.app.Application;

/**
 * Application for initializing App
 */
public class SlackApp extends Application {

	private DatabaseHelper databaseHelper;
	private SlackUserDatabaseStorage userDatabaseStorage;

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
		userDatabaseStorage = new SlackUserDatabaseStorage(this);
	}

	public void setLogInfo() {
		Logger.setApplicationTag("SlackApp");
		Logger.setAddClassNameToMessage(true);
	}

	public SlackUserDatabaseStorage getUserDatabaseStorage() {
		return userDatabaseStorage;
	}

	public DatabaseHelper getDatabaseHelper() {
		return databaseHelper;
	}
}
