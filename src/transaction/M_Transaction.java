package transaction;

import java.util.Calendar;
import java.util.Date;

public class M_Transaction {
	private int transaction_id;
	private int item_id;
	private String item_name;
	private int user_id;
	private String user_name;
	private String type;
	private int quantity;
	private Date created_at;
	private String remarks;
	private boolean check;
	
	public M_Transaction() {
		transaction_id = 0;
		item_id = 0;
		item_name = "";
		user_id = 0;
		user_name = "";
		type = "";
		quantity = 0;
		created_at = Calendar.getInstance().getTime();
		remarks = "";
		check = false;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_username) {
		this.user_name = user_username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}
}
