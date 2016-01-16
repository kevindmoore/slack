package com.mastertechsoftware.slack.api;

import com.mastertechsoftware.slack.models.UserList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Retrofit API
 */
public interface SlackAPI {
	@GET("users.list?token=xoxp-5048173296-5048487710-18650790535-1cc8644082")
	Call<UserList> getUsers();
}
