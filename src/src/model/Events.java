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
	private boolean check_tf;
	private int list_dataNum;
	private boolean listCheck_tf;

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

	public boolean getCheck_tf() {
		return check_tf;
	}
	public void setCheck_tf(boolean check_tf) {
		this.check_tf = check_tf;
	}


	public ArrayList<EventType> getET(){
		return ET;
	}

	public void setET(ArrayList<EventType> ET) {
	}

	public ArrayList<EventLevel> getEL(){
		return EL;
	}

	public void setEL(ArrayList<EventLevel> EL) {
		this.EL = EL;
	}


	public boolean getListCheck_tf() {
		return listCheck_tf;
	}
	public void setListCheck_tf(boolean listCheck_tf) {
		this.listCheck_tf = listCheck_tf;
	}


	public int getList_dataNum() {
		return list_dataNum;
	}
	public void setList_dataNum(int list_dataNum) {
		this.list_dataNum = list_dataNum;
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
		this.check_tf = false;
		this.list_dataNum =0;
		this.listCheck_tf=false;

		ET = new ArrayList<EventType>();
		EL = new ArrayList<EventLevel>();
	}


	public Events(String event, int type, int level, int available, String user_id) {
		super();
		this.number = 0;
		this.event = event;
		this.type = type;
		this.level = level;
		this.available = available;
		this.user_id = user_id;
		this.check_tf = false;
	}
	public Events(int number,String event, int type, int level, int available, String user_id, boolean check_tf ,int list_dataNum,boolean listCheck_tf) {
		super();
		this.number = number;
		this.event = event;
		this.type = type;
		this.level = level;
		this.available = available;
		this.user_id = user_id;
		this.check_tf = check_tf;
		this.list_dataNum = list_dataNum;
		this.listCheck_tf =listCheck_tf;
	}


	public Events(String event, int type , int level) {
		this.number = 0;
		this.event = event;
		this.type = type;
		this.level = level;
	}

}
