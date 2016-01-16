package com.mastertechsoftware.slack.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for the List of users.
 */
public class UserList {
	private boolean ok;
	private List<User> members = new ArrayList<>();

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
