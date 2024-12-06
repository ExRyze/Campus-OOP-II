package inventaris;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class AbstractTBM_Inventaris extends AbstractTableModel {
	private ArrayList<M_Inventaris> data_buku = new ArrayList<M_Inventaris>();
//	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	
	public ArrayList<M_Inventaris> getDataBuku() {
		return data_buku;
	}

	public void setDataBuku(ArrayList<M_Inventaris> data_buku) {
		this.data_buku = data_buku;
	}

	public AbstractTBM_Inventaris() {
		data_buku = new ArrayList<M_Inventaris>();
	}

	public int getRowCount() {
		return data_buku.size();
	}

	public int getColumnCount() {
		return 9;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0: return data_buku.get(rowIndex).isCheck();
		case 1: return rowIndex+1;
		case 2: return data_buku.get(rowIndex).getJudul();
		case 3: return data_buku.get(rowIndex).getPengarang();
		case 4: return data_buku.get(rowIndex).getPenerbit();
		case 5: return data_buku.get(rowIndex).getTahun_terbit();
		case 6: return data_buku.get(rowIndex).getGenre();
		case 7: return data_buku.get(rowIndex).getJumlah_halaman();
		case 8: return data_buku.get(rowIndex).getIsbn();
		default:
			throw new IllegalArgumentException("Unexpected value: " + columnIndex);
		}
	}

	public String getColumnName(int column) {
		switch (column) {
		case 0: return "Check";
		case 1: return "No.";
		case 2: return "Judul";
		case 3: return "Pengarang";
		case 4: return "Penerbit";
		case 5: return "Tahun terbit";
		case 6: return "Genre";
		case 7: return "Jumlah Halaman";
		case 8: return "ISBN";
		default:
			return null;
		}
	}

	public Class getColumnClass(int columnIndex) {
		if (columnIndex == 0) {
			return Boolean.class;
		} else {
			return super.getColumnClass(columnIndex);
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, columnIndex);
		if (aValue != null && aValue instanceof Boolean && columnIndex == 0) {
			data_buku.get(rowIndex).setCheck((Boolean)aValue);
		}
	}
	
	public void addDataBuku(M_Inventaris newBuku) {
		data_buku.add(newBuku);
		fireTableDataChanged();
	}
	
	public void removeCheck() {
		ArrayList<M_Inventaris> newDataBuku = new ArrayList<M_Inventaris>();
		for (M_Inventaris ms:data_buku) {
			if (!ms.isCheck()) {
				newDataBuku.add(ms);
			}
			data_buku = newDataBuku;
			fireTableDataChanged();
		}
	}
	
	public void removeAll() {
		ArrayList<M_Inventaris> newDataBuku = new ArrayList<M_Inventaris>();
		data_buku = newDataBuku;
		fireTableDataChanged();
	}
	
	public void editBuku(String isbn, M_Inventaris ms) {
		int index = -1;
		int i = 0;
		for (M_Inventaris m:getDataBuku()) {
			if (m.getIsbn().equals(isbn)) {
				index = i;
			}
			i++;
		}
		if (index >= 0) {
			data_buku.set(index, ms);
			fireTableDataChanged();
		}
	}
}
