package model;

import java.io.Serializable;

public class EventType implements Serializable{

	private int code;
	private String type;


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public EventType(int code, String type) {
		super();
		this.code = code;
		this.type = type;
	}

	public EventType() {
		super();
		this.code = 1;
		this.type = "";
	}


}
