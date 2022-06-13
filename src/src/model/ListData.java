package model;

import java.io.Serializable;
import java.util.Date;

public class ListData implements Serializable {
	private int number;
	private int list_num;
	private int event_num;
	private boolean check_cf;
	private Date check_date;

	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getList_num() {
		return list_num;
	}
	public void setList_num(int list_num) {
		this.list_num = list_num;
	}
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public boolean isCheck_cf() {
		return check_cf;
	}
	public void setCheck_cf(boolean check_cf) {
		this.check_cf = check_cf;
	}
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}


	public ListData(int number, int list_num, int event_num, boolean check_cf, Date check_date) {
		this.number = number;
		this.list_num = list_num;
		this.event_num = event_num;
		this.check_cf = check_cf;
		this.check_date = check_date;
	}
	public ListData() {
		this.number = 0;
		this.list_num = 0;
		this.event_num = 0;
		this.check_date = null;
	}


}

