package com.mastertechsoftware.slack.models;

import com.mastertechsoftware.slack.sql.DefaultReflectTable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for a specific User
 */
public class User extends DefaultReflectTable implements Parcelable {
	private String id;
	private String name;
	private boolean deleted;
	private String color;
	private Profile profile;
	private boolean is_admin;
	private boolean is_owner;
	private boolean has_2fa;
	private boolean has_files;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isHas_2fa() {
		return has_2fa;
	}

	public void setHas_2fa(boolean has_2fa) {
		this.has_2fa = has_2fa;
	}

	public boolean isHas_files() {
		return has_files;
	}

	public void setHas_files(boolean has_files) {
		this.has_files = has_files;
	}

	public String getUserId() {
		return id;
	}

	public void setUserId(String id) {
		this.id = id;
	}

	public boolean is_admin() {
		return is_admin;
	}

	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}

	public boolean is_owner() {
		return is_owner;
	}

	public void setIs_owner(boolean is_owner) {
		this.is_owner = is_owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Override
	public int describeContents() {
		return 0;
	}

		public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
		public User createFromParcel(Parcel in) {
			User user = new User();
			user.readFromParcel(in);
			return user;
		}

		public User[] newArray(int size) {
			return new User[size];
		}
	};

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeInt(deleted ? 1 : 0);
		dest.writeString(color);
		profile.writeToParcel(dest, flags);
		dest.writeInt(is_admin ? 1 : 0);
		dest.writeInt(is_owner ? 1 : 0);
		dest.writeInt(has_2fa ? 1 : 0);
		dest.writeInt(has_files ? 1 : 0);
	}
	/**
	 * Reads the Parcel contents into this Bundle, typically in order for
	 * it to be passed through an IBinder connection.
	 * @param parcel The parcel to overwrite this bundle from.
	 */
	public void readFromParcel(Parcel parcel) {
		id = parcel.readString();
		name = parcel.readString();
		deleted = parcel.readInt() == 1;
		color = parcel.readString();
		profile = new Profile();
		profile.readFromParcel(parcel);
		deleted = parcel.readInt() == 1;
		is_admin = parcel.readInt() == 1;
		is_owner = parcel.readInt() == 1;
		has_2fa = parcel.readInt() == 1;
		has_files = parcel.readInt() == 1;
	}
}
