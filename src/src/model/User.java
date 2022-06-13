package model;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	private String id;
	private String pass;
	private String user_name;
	private int reward;
	private int point;
	private String icon;

	private ArrayList<UserFavoriteImg> FI = null;
	private ArrayList<UserFavoriteVoice>FV = null;
	//Rewardについて、結合して持ってくる？

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public ArrayList<UserFavoriteImg> getFI() {
		return FI;
	}
	public void setFI(ArrayList<UserFavoriteImg> FI) {
		this.FI = FI;
	}

	public ArrayList<UserFavoriteVoice> getFV() {
		return FV;
	}
	public void setFV(ArrayList<UserFavoriteVoice> FV) {
		this.FV = FV;
	}


	public User(String id, String pass, String user_name, int reward, int point, String icon) {
		super();
		this.id = id;
		this.pass = pass;
		this.user_name = user_name;
		this.reward = reward;
		this.point = point;
		this.icon = icon;

		FI = new ArrayList<UserFavoriteImg>();
		FV = new ArrayList<UserFavoriteVoice>();
	}

	public User() {
		super();
		this.id = "";
		this.pass = "";
		this.user_name = "";
		this.reward = 0;
		this.point = 0;
		this.icon = "";

		FI = new ArrayList<UserFavoriteImg>();
		FV = new ArrayList<UserFavoriteVoice>();
	}

}
