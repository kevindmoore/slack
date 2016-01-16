package com.mastertechsoftware.slack.users;

import com.mastertechsoftware.slack.models.User;

import java.util.List;

/**
 * Interface for accessing Slac Users
 */
public interface SlackUserStorage {
	List<User> getUsers();
	void removeAll();
	void addAll(List<User> users);
}
