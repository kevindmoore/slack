package com.mastertechsoftware.slack.users;

import com.mastertechsoftware.slack.SlackApp;
import com.mastertechsoftware.slack.models.User;
import com.mastertechsoftware.slack.sql.DatabaseHelper;

import java.util.List;

/**
 * Concrete implementation of SlackUserStorate
 */
public class SlackUserDatabaseStorage implements SlackUserStorage {
	private DatabaseHelper databaseHelper;
	private SlackApp app;

	public SlackUserDatabaseStorage(SlackApp app) {
		this.app = app;
	}

	@Override
	public List<User> getUsers() {
		return (List<User>) getDatabaseHelper().getAll(User.class);
	}

	@Override
	public void removeAll() {
		getDatabaseHelper().removeAll(User.class);
	}

	@Override
	public void addAll(List<User> users) {
		getDatabaseHelper().removeAll(User.class);
	}

	private DatabaseHelper getDatabaseHelper() {
		if (databaseHelper == null) {
			databaseHelper = app.getDatabaseHelper();
		}
		return databaseHelper;
	}
}
