package com.mastertechsoftware.slack.adapters;

/**
 *
 */

import com.mastertechsoftware.slack.R;
import com.mastertechsoftware.slack.models.User;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter for main view
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.UserViewHolder> {
	protected View.OnClickListener onClickListener;
	protected List<User> users;

	public MainAdapter(List<User> users, View.OnClickListener onClickListener) {
		this.users = users;
		this.onClickListener = onClickListener;
	}

	@Override
	public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
	}

	@Override
	public void onBindViewHolder(UserViewHolder holder, int position) {
		final User user = users.get(position);
		holder.username.setText(user.getName());
	}


	@Override
	public int getItemCount() {
		return users.size();
	}

	public User getUser(int adapterPosition) {
		return users.get(adapterPosition);
	}

	public List<User> getUsers() {
		return users;
	}

	/**
	 * This is the holder for the statement view. Holds the month & year
	 */
	public class UserViewHolder extends RecyclerView.ViewHolder {
		protected TextView username;
		public UserViewHolder(View itemView) {
			super(itemView);
			if (onClickListener != null) {
				itemView.setOnClickListener(onClickListener);
			}
			// This helps us find the holder when the view is clicked on
			itemView.setTag(this);
			username = (TextView) itemView.findViewById(R.id.username);
		}
	}

}
