package model;
import java.io.Serializable;
import java.util.ArrayList;

public class Events implements Serializable {

	private int number;
	private String event;
	private int type;
	private int level;
	private int available;
	private String user_id;

	private ArrayList<EventType> ET = null;
	private ArrayList<EventLevel> EL = null;


	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public ArrayList<EventType> getET(){
		return ET;
	}

	public void setET(ArrayList<EventType> ET) {
		this.ET = ET;
	}

	public ArrayList<EventLevel> getEL(){
		return EL;
	}

	public void setEL(ArrayList<EventLevel> EL) {
		this.EL = EL;
	}
	public Events(int number, String event, int type, int level, int available, String user_id) {
		super();
		this.number = number;
		this.event = event;
		this.type = type;
		this.level = level;
		this.available = available;
		this.user_id = user_id;

		ET = new ArrayList<EventType>();
		EL = new ArrayList<EventLevel>();
	}

	public Events() {
		super();
		this.number = 1;
		this.event = "";
		this.type = 1;
		this.level = 1;
		this.available = 0;
		this.user_id = "";

		ET = new ArrayList<EventType>();
		EL = new ArrayList<EventLevel>();
	}


}
