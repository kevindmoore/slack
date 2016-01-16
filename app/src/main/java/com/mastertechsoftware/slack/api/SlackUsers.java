package com.mastertechsoftware.slack.api;

import com.mastertechsoftware.slack.models.UserList;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 *
 */
public class SlackUsers {
	private final SlackAPI service;

	public SlackUsers() {
//		Gson gson = new GsonBuilder()
//			.create();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://slack.com/api/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		service = retrofit.create(SlackAPI.class);
	}

	public void getUsers(Callback<UserList> callback) throws IOException {
		Call<UserList> call = service.getUsers();
		call.enqueue(callback);
	}
}
