package model;
import java.io.Serializable;
import java.sql.Date;

//javabeans
public class List implements Serializable {
	private int number;
	private Date date;
	private String id;
	private boolean check_tf;

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean getCheck_tf() {
		return check_tf;
	}
	public void setCheck_tf(boolean check_tf) {
		this.check_tf = check_tf;
	}

	public List(int number, Date date, String id, boolean check_tf) {
		super();
		this.number = number;
		this.date = date;
		this.id = id;
		this.check_tf = check_tf;
	}

	public List() {
		super();
		this.number = 0;
		this.date = null;
		this.id = "";
		this.check_tf = false;
	}

}


