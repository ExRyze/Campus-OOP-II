package user;

import java.util.Calendar;
import java.util.Date;

public class M_User {
	private int user_id;
	private String name;
	private Date created_at;
	private boolean check;
	
	public M_User() {
		user_id = 0;
		name = "";
		created_at = Calendar.getInstance().getTime();
		check = false;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	
}
