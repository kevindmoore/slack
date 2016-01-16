package com.mastertechsoftware.slack.activities;

import com.mastertechsoftware.slack.R;
import com.mastertechsoftware.slack.models.Profile;
import com.mastertechsoftware.slack.models.User;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

	private User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

		if (toolbar != null) {
			toolbar.setTitle(R.string.title_activity_profile);
			toolbar.setNavigationIcon(R.mipmap.icon_back_arrow_white);
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		user = getIntent().getParcelableExtra(MainActivity.USER);
		if (user != null) {
			final Profile profile = user.getProfile();

			((TextView) findViewById(R.id.username)).setText(user.getName());
			if (profile != null) {
				final TextView realName = (TextView) findViewById(R.id.realname);
				realName.setText(String.format("%s %s", (TextUtils.isEmpty(profile.getFirst_name()) ? "" : profile.getFirst_name()),
						(TextUtils.isEmpty(profile.getLast_name()) ? "" : profile.getLast_name())));
				((TextView) findViewById(R.id.title)).setText(profile.getTitle());
				Picasso.with(this).load(getProfileImage(profile)).into((ImageView) findViewById(R.id.userImage));
			}
		}
	}

	/**
	 * Try to load the largest image possible for the best results
	 * @param profile
	 * @return image url
	 */
	private String getProfileImage(Profile profile) {
		if (!TextUtils.isEmpty(profile.getImage_192())) {
			return profile.getImage_192();
		} else if (!TextUtils.isEmpty(profile.getImage_72())) {
			return profile.getImage_72();
		} else if (!TextUtils.isEmpty(profile.getImage_48())) {
			return profile.getImage_48();
		} else if (!TextUtils.isEmpty(profile.getImage_32())) {
			return profile.getImage_32();
		} else  {
			return profile.getImage_24();
		}
	}

}
