package com.mastertechsoftware.slack.models;

import com.mastertechsoftware.slack.sql.DefaultReflectTable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model for a User's Profile
 */
public class Profile extends DefaultReflectTable implements Parcelable {
	private String first_name;
	private String last_name;
	private String real_name;
	private String email;
	private String skype;
	private String phone;
	private String title;
	private String image_24;
	private String image_32;
	private String image_48;
	private String image_72;
	private String image_192;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getImage_192() {
		return image_192;
	}

	public void setImage_192(String image_192) {
		this.image_192 = image_192;
	}

	public String getImage_24() {
		return image_24;
	}

	public void setImage_24(String image_24) {
		this.image_24 = image_24;
	}

	public String getImage_32() {
		return image_32;
	}

	public void setImage_32(String image_32) {
		this.image_32 = image_32;
	}

	public String getImage_48() {
		return image_48;
	}

	public void setImage_48(String image_48) {
		this.image_48 = image_48;
	}

	public String getImage_72() {
		return image_72;
	}

	public void setImage_72(String image_72) {
		this.image_72 = image_72;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<Profile> CREATOR = new Parcelable.Creator<Profile>() {
		public Profile createFromParcel(Parcel in) {
			Profile profile = new Profile();
			profile.readFromParcel(in);
			return profile;
		}

		public Profile[] newArray(int size) {
			return new Profile[size];
		}
	};

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(first_name);
		dest.writeString(last_name);
		dest.writeString(real_name);
		dest.writeString(email);
		dest.writeString(skype);
		dest.writeString(phone);
		dest.writeString(title);
		dest.writeString(image_24);
		dest.writeString(image_32);
		dest.writeString(image_48);
		dest.writeString(image_72);
		dest.writeString(image_192);
	}
	/**
	 * Reads the Parcel contents into this Bundle, typically in order for
	 * it to be passed through an IBinder connection.
	 * @param parcel The parcel to overwrite this bundle from.
	 */
	public void readFromParcel(Parcel parcel) {
		first_name = parcel.readString();
		last_name = parcel.readString();
		real_name = parcel.readString();
		email = parcel.readString();
		skype = parcel.readString();
		phone = parcel.readString();
		title = parcel.readString();
		image_24 = parcel.readString();
		image_32 = parcel.readString();
		image_48 = parcel.readString();
		image_72 = parcel.readString();
		image_192 = parcel.readString();
	}
}
