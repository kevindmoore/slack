package com.mastertechsoftware.slack.activities;

import com.mastertechsoftware.slack.DividerItemDecoration;
import com.mastertechsoftware.slack.R;
import com.mastertechsoftware.slack.SlackApp;
import com.mastertechsoftware.slack.adapters.MainAdapter;
import com.mastertechsoftware.slack.api.SlackUsers;
import com.mastertechsoftware.slack.log.Logger;
import com.mastertechsoftware.slack.models.User;
import com.mastertechsoftware.slack.models.UserList;
import com.mastertechsoftware.slack.users.SlackUserDatabaseStorage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String USER = "user";
	private RecyclerView recyclerView;
	protected Toolbar toolbar;
	private MainAdapter mainAdapter;
	private SlackUserDatabaseStorage userDatabaseStorage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		final Drawable divider = getResources().getDrawable(R.drawable.divider);
		recyclerView.addItemDecoration(new DividerItemDecoration(divider));
		toolbar = (Toolbar) findViewById(R.id.toolbar);

		if (toolbar != null) {
			toolbar.setTitle(R.string.users_title);
		}
		// First get the stored Users
		userDatabaseStorage = ((SlackApp) getApplication()).getUserDatabaseStorage();
		mainAdapter = new MainAdapter((List<User>)userDatabaseStorage.getUsers(), MainActivity.this);
		recyclerView.setAdapter(mainAdapter);
		retrieveUsers();
	}

	private void retrieveUsers() {
		SlackUsers retriever = new SlackUsers();
		try {
			Callback<UserList> callback = new Callback<UserList>() {

				@Override
				public void onResponse(Response<UserList> userList) {
					if (userList.isSuccess()) {
						final List<User> members = userList.body().getMembers();
						mainAdapter = new MainAdapter(members, MainActivity.this);
						recyclerView.setAdapter(mainAdapter);
						userDatabaseStorage.removeAll();
						userDatabaseStorage.addAll(members);
					}
				}

				@Override
				public void onFailure(Throwable t) {
					Logger.error("Problems calling API", t);
				}
			};
			retriever.getUsers(callback);
		} catch (IOException e) {
			Logger.error("Problems calling API", 3);
		}

	}

	@Override
	public void onClick(View v) {
		MainAdapter.UserViewHolder userViewHolder = (MainAdapter.UserViewHolder) v.getTag();
		final User user = mainAdapter.getUser(userViewHolder.getAdapterPosition());
		Intent intent = new Intent(this, ProfileActivity.class);
		intent.putExtra(USER, user);
		startActivity(intent);
	}
}
