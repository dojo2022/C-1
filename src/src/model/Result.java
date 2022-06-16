package model;
import java.io.Serializable;

public class Result implements Serializable {
	private String message; //メッセージ

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result(String message) {
		super();
		this.message = message;
	}

	public Result() {
		super();
		this.message = "";
	}



}
