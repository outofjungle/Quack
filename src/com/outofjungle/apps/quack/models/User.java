package com.outofjungle.apps.quack.models;

import java.io.Serializable;

import org.json.JSONObject;

public class User extends BaseModel implements Serializable {

	private static final long serialVersionUID = 6354896382853726205L;

	public String getName() {
		return getString("name");
	}

	public long getId() {
		return getLong("id");
	}
	
	public String getScreenName() {
		return getString("screen_name");
	}
	
	public String getProfileImageUrl() {
		return getString("profile_image_url");
	}
	
	public int getFollowersCount() {
		return getInt("followers_count");
	}
	
	public int getFriendsCount() {
		return getInt("friends_count");
	}
	
	public String getTagLine() {
		return getString("description");
	}
	
	public static User fromJson(JSONObject json) {
		User u = new User();
		try {
			u.jsonObject = json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
}
