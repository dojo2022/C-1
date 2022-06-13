package model;
import java.io.Serializable;

//javabeans
public class EventLevel implements Serializable {

	private int code;
	private String level;
	private int point;


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}


	public EventLevel(int code, String level, int point) {
		super();
		this.code = code;
		this.level = level;
		this.point = point;
	}

	public EventLevel() {
		super();
		this.code = 1;
		this.level = "";
		this.point = 0;
	}



}
