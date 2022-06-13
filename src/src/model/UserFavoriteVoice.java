package model;

import java.io.Serializable;

public class UserFavoriteVoice implements Serializable {
	private int number;
	private String user_id;
	private String favorite_good_voice;
	private String favorite_bad_voice;
	private String favorite_other_voice;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFavorite_good_voice() {
		return favorite_good_voice;
	}
	public void setFavorite_good_voice(String favorite_good_voice) {
		this.favorite_good_voice = favorite_good_voice;
	}
	public String getFavorite_bad_voice() {
		return favorite_bad_voice;
	}
	public void setFavorite_bad_voice(String favorite_bad_voice) {
		this.favorite_bad_voice = favorite_bad_voice;
	}
	public String getFavorite_other_voice() {
		return favorite_other_voice;
	}
	public void setFavorite_other_voice(String favorite_other_voice) {
		this.favorite_other_voice = favorite_other_voice;
	}

	public UserFavoriteVoice(int number, String user_id, String favorite_good_voice, String favorite_bad_voice,
			String favorite_other_voice) {
		super();
		this.number = number;
		this.user_id = user_id;
		this.favorite_good_voice = favorite_good_voice;
		this.favorite_bad_voice = favorite_bad_voice;
		this.favorite_other_voice = favorite_other_voice;
	}

	public UserFavoriteVoice() {
		super();
		this.number = 0;
		this.user_id = "";
		this.favorite_good_voice = "";
		this.favorite_bad_voice = "";
		this.favorite_other_voice = "";
	}
}
