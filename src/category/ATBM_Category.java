package category;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ATBM_Category extends AbstractTableModel {
	private ArrayList<M_Category> data_Categories = new ArrayList<M_Category>();

	public ATBM_Category() {
		data_Categories = new ArrayList<M_Category>();
	}

	public void setData(ArrayList<M_Category> data_Categories) {
		this.data_Categories = data_Categories;
	}
	
	public ArrayList<M_Category> getData() {
		return data_Categories;
	}

	public int getRowCount() {
		return data_Categories.size();
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
		case 1: return "Category_id";
		case 2: return "Name";
		case 3: return "Description";
		default:
			return null;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: return data_Categories.get(rowIndex).isCheck();
		case 1: return data_Categories.get(rowIndex).getCategory_id();
		case 2: return data_Categories.get(rowIndex).getName();
		case 3: return data_Categories.get(rowIndex).getDescription();
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (aValue != null && aValue instanceof Boolean && columnIndex == 0) {
			data_Categories.get(rowIndex).setCheck((Boolean)aValue);
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
		ArrayList<M_Category> new_Categories = new ArrayList<M_Category>();
		for (M_Category item:data_Categories) {
			if (!item.isCheck()) {
				new_Categories.add(item);
			}
			data_Categories = new_Categories;
			fireTableDataChanged();
		}
	}
	
	public void addData(M_Category category) {
		data_Categories.add(category);
		fireTableDataChanged();
	}
	
	public void editData(int id, M_Category category) {
		int index = -1;
		int i = 0;
		for (M_Category item:getData()) {
			if (item.getCategory_id() == id) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			data_Categories.set(index, category);
			fireTableDataChanged();
		}
	}
	
	public void removeAll() {
		ArrayList<M_Category> new_Categories = new ArrayList<M_Category>();
		data_Categories = new_Categories;
		fireTableDataChanged();
	}
}
