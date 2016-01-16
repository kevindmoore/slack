package com.mastertechsoftware.slack.adapters;

/**
 *
 */

import com.mastertechsoftware.slack.R;
import com.mastertechsoftware.slack.models.Profile;
import com.mastertechsoftware.slack.models.User;
import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
		Picasso.with(holder.username.getContext()).load(getProfileImage(user.getProfile())).into(holder.userImage);
	}

	/**
	 * Try to load the largest image possible for the best results
	 * @param profile
	 * @return image url
	 */
	private String getProfileImage(Profile profile) {
		if (!TextUtils.isEmpty(profile.getImage_72())) {
			return profile.getImage_72();
		} else if (!TextUtils.isEmpty(profile.getImage_48())) {
			return profile.getImage_48();
		} else if (!TextUtils.isEmpty(profile.getImage_32())) {
			return profile.getImage_32();
		} else  {
			return profile.getImage_24();
		}
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
		protected ImageView userImage;
		public UserViewHolder(View itemView) {
			super(itemView);
			if (onClickListener != null) {
				itemView.setOnClickListener(onClickListener);
			}
			// This helps us find the holder when the view is clicked on
			itemView.setTag(this);
			username = (TextView) itemView.findViewById(R.id.username);
			userImage = (ImageView) itemView.findViewById(R.id.userImage);
		}
	}

}
