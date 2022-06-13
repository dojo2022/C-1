package model;
import java.io.Serializable;

public class UserFavoriteImg implements Serializable{
	private int number;
	private String user_id;
	private String favorite_good_img;
	private String favorite_bad_img;
	private String favorite_other_img;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getId() {
		return user_id;
	}
	public void setId(String user_id) {
		this.user_id = user_id;
	}
	public String getFavorite_good_img() {
		return favorite_good_img;
	}
	public void setFavorite_good_img(String favorite_good_img) {
		this.favorite_good_img = favorite_good_img;
	}
	public String getFavorite_bad_img() {
		return favorite_bad_img;
	}
	public void setFavorite_bad_img(String favorite_bad_img) {
		this.favorite_bad_img = favorite_bad_img;
	}
	public String getFavorite_other_img() {
		return favorite_other_img;
	}
	public void setFavorite_other_img(String favorite_other_img) {
		this.favorite_other_img = favorite_other_img;
	}

	public UserFavoriteImg(int number, String user_id, String favorite_good_img, String favorite_bad_img,
			String favorite_other_img) {
		super();
		this.number = number;
		this.user_id = user_id;
		this.favorite_good_img = favorite_good_img;
		this.favorite_bad_img = favorite_bad_img;
		this.favorite_other_img = favorite_other_img;
	}

	public UserFavoriteImg() {
		super();
		this.number = 0;
		this.user_id = "";
		this.favorite_good_img = "";
		this.favorite_bad_img = "";
		this.favorite_other_img = "";
	}



}
