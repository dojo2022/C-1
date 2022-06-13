package model;
import java.io.Serializable;

public class UserReward implements Serializable{
	//フィールド
	private int code;
	private int point;
	private String reward;

	//メソッド
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getReward() {
		return reward;
	}

	//メソッド
	//アクセス修飾子 戻り値のデータ型 メソッド名前(引数。。。) {  }
	public void setReward(String reward) {
		this.reward = reward;
	}


	//コンストラクタ
	//アクセス修飾子 コンストラクタ名前(引数。。。) {  }


	//UserReward user = new UserReward(100, 10, "reward");
	public UserReward(int code, int point, String reward) {
		super();
		this.code = code;
		this.point = point;
		this.reward = reward;
	}

	//UserReward user = new UserReward();
	//デフォルトコンストラクタ
	public UserReward() {
		super();
		this.code = 1;
		this.point = 0;
		this.reward = "";
	}

}
