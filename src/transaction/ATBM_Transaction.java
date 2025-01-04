package transaction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ATBM_Transaction extends AbstractTableModel {
	private ArrayList<M_Transaction> data_Transactions = new ArrayList<M_Transaction>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public ATBM_Transaction() {
		data_Transactions = new ArrayList<M_Transaction>();
	}

	public void setData(ArrayList<M_Transaction> data_Transactions) {
		this.data_Transactions = data_Transactions;
	}
	
	public ArrayList<M_Transaction> getData() {
		return data_Transactions;
	}

	public int getRowCount() {
		return data_Transactions.size();
	}

	public int getColumnCount() {
		return 8;
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
		case 1: return "Transaction_Id";
		case 2: return "Item_Name";
		case 3: return "User_Username";
		case 4: return "Type";
		case 5: return "Quantity";
		case 6: return "created_at";
		case 7: return "remarks";
		default:
			return null;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: return data_Transactions.get(rowIndex).isCheck();
		case 1: return data_Transactions.get(rowIndex).getTransaction_id();
		case 2: return data_Transactions.get(rowIndex).getItem_name();
		case 3: return data_Transactions.get(rowIndex).getUser_name();
		case 4: return data_Transactions.get(rowIndex).getType();
		case 5: return data_Transactions.get(rowIndex).getQuantity();
		case 6: return sdf.format(data_Transactions.get(rowIndex).getCreated_at());
		case 7: return data_Transactions.get(rowIndex).getRemarks();
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (aValue != null && aValue instanceof Boolean && columnIndex == 0) {
			data_Transactions.get(rowIndex).setCheck((Boolean)aValue);
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
		ArrayList<M_Transaction> new_Transactions = new ArrayList<M_Transaction>();
		for (M_Transaction transaction:data_Transactions) {
			if (!transaction.isCheck()) {
				new_Transactions.add(transaction);
			}
			data_Transactions = new_Transactions;
			fireTableDataChanged();
		}
	}
	
	public void addData(M_Transaction transaction) {
		data_Transactions.add(transaction);
		fireTableDataChanged();
	}
	
	public void editData(int id, M_Transaction transaction) {
		int index = -1;
		int i = 0;
		for (M_Transaction transaction1:getData()) {
			if (transaction1.getTransaction_id() == id) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			data_Transactions.set(index, transaction);
			fireTableDataChanged();
		}
	}
	
	public void removeAll() {
		ArrayList<M_Transaction> new_Transactions = new ArrayList<M_Transaction>();
		data_Transactions = new_Transactions;
		fireTableDataChanged();
	}
}
