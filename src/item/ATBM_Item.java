package item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ATBM_Item extends AbstractTableModel {
	private ArrayList<M_Item> data_Items = new ArrayList<M_Item>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	public ATBM_Item() {
		data_Items = new ArrayList<M_Item>();
	}

	public void setData(ArrayList<M_Item> data_Items) {
		this.data_Items = data_Items;
	}
	
	public ArrayList<M_Item> getData() {
		return data_Items;
	}

	public int getRowCount() {
		return data_Items.size();
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
		case 1: return "Item_id";
		case 2: return "Name";
		case 3: return "Category_name";
		case 4: return "Quantity";
		case 5: return "unit";
		case 6: return "description";
		case 7: return "created_at";
		default:
			return null;
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: return data_Items.get(rowIndex).isCheck();
		case 1: return data_Items.get(rowIndex).getItem_id();
		case 2: return data_Items.get(rowIndex).getName();
		case 3: return data_Items.get(rowIndex).getCategory_name();
		case 4: return data_Items.get(rowIndex).getQuantity();
		case 5: return data_Items.get(rowIndex).getUnit();
		case 6: return data_Items.get(rowIndex).getDescription();
		case 7: return sdf.format(data_Items.get(rowIndex).getCreated_at());
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (aValue != null && aValue instanceof Boolean && columnIndex == 0) {
			data_Items.get(rowIndex).setCheck((Boolean)aValue);
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
		ArrayList<M_Item> new_items = new ArrayList<M_Item>();
		for (M_Item item:data_Items) {
			if (!item.isCheck()) {
				new_items.add(item);
			}
			data_Items = new_items;
			fireTableDataChanged();
		}
	}
	
	public void addData(M_Item category) {
		data_Items.add(category);
		fireTableDataChanged();
	}
	
	public void editData(int id, M_Item item) {
		int index = -1;
		int i = 0;
		for (M_Item item1:getData()) {
			if (item1.getItem_id() == id) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			data_Items.set(index, item);
			fireTableDataChanged();
		}
	}
	
	public void removeAll() {
		ArrayList<M_Item> new_items = new ArrayList<M_Item>();
		data_Items = new_items;
		fireTableDataChanged();
	}
}
