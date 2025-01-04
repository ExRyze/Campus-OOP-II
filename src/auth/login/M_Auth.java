package auth.login;

import java.util.Calendar;
import java.util.Date;

public class M_Auth {
	private int admin_id;
	private String username;
	private String password;
	private Date created_at;
	
	public M_Auth() {
		admin_id = 0;
		username = "";
		password = "";
		created_at = Calendar.getInstance().getTime();
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
