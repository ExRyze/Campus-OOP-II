package user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ATBM_User extends AbstractTableModel {
	private ArrayList<M_User> data_Users = new ArrayList<M_User>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public ATBM_User() {
		data_Users = new ArrayList<M_User>();
	}

	public void setData(ArrayList<M_User> data_Users) {
		this.data_Users = data_Users;
	}
	
	public ArrayList<M_User> getData() {
		return data_Users;
	}

	public int getRowCount() {
		return data_Users.size();
	}

	public int getColumnCount() {
		return 4;
	}

	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Boolean.class;
		} else {
			return super.getColumnClass(columnIndex);
		}
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0: return "Check";
		case 1: return "User_Id.";
		case 2: return "Name";
		case 3: return "created_at";
		default:
			return null;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: return data_Users.get(rowIndex).isCheck();
		case 1: return data_Users.get(rowIndex).getUser_id();
		case 2: return data_Users.get(rowIndex).getName();
		case 3: return sdf.format(data_Users.get(rowIndex).getCreated_at());
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (aValue != null && aValue instanceof Boolean && columnIndex == 0) {
			data_Users.get(rowIndex).setCheck((Boolean)aValue);
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void removeCheck() {
		ArrayList<M_User> new_Users = new ArrayList<M_User>();
		for (M_User user:data_Users) {
			if (!user.isCheck()) {
				new_Users.add(user);
			}
			data_Users = new_Users;
			fireTableDataChanged();
		}
	}
	
	public void addData(M_User category) {
		data_Users.add(category);
		fireTableDataChanged();
	}
	
	public void editData(int id, M_User user) {
		int index = -1;
		int i = 0;
		for (M_User user1:getData()) {
			if (user1.getUser_id() == id) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			data_Users.set(index, user);
			fireTableDataChanged();
		}
	}
	
	public void removeAll() {
		ArrayList<M_User> new_Users = new ArrayList<M_User>();
		data_Users = new_Users;
		fireTableDataChanged();
	}
}
