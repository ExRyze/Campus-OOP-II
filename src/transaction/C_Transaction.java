package transaction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packages.KoneksiJDBC;

public class C_Transaction extends V_ListTransaction implements ActionListener {
	private KoneksiJDBC konek = new KoneksiJDBC();
	private String name_Table = "`transactions`";
	private String id_Table = "`transaction_id`";
	private String relation1_Table = "`items`";
	private String relation1_Id = "`item_id`";
	private String relation2_Table = "`users`";
	private String relation2_Id = "`user_id`";
	private String relation = " INNER JOIN "+relation1_Table+" ON "+name_Table+"."+relation1_Id+" = "+relation1_Table+"."+relation1_Id
							+ " INNER JOIN "+relation2_Table+" ON "+name_Table+"."+relation2_Id+" = "+relation2_Table+"."+relation2_Id;
	
	public C_Transaction() {
		tampilData();

		getPanelAtas().getBtnSearch().addActionListener(this);
		getPanelAtas().getBtnRefresh().addActionListener(this);
		getPanelAtas().getBtnHapus().addActionListener(this);
		getPanelAtas().getBtnInsert().addActionListener(this);
		getPanelAtas().getBtnEdit().addActionListener(this);
		getPanelBawah().getBtnKeluar().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getPanelAtas().getBtnSearch()) {
			search(getPanelAtas().getFieldSearch().getText());
		}
		if(e.getSource() == getPanelAtas().getBtnRefresh()) {
			tampilData();
		}
		if(e.getSource() == getPanelAtas().getBtnInsert()) {
			tambah(false);
		}
		if(e.getSource() == getPanelAtas().getBtnEdit()) {
			edit();
		}
		if(e.getSource() == getPanelAtas().getBtnHapus()) {
			hapus();
		}
		if(e.getSource() == getPanelBawah().getBtnKeluar()) {
			dispose();
		}
	}
	
	public void tampilData() {
		try {
			if (konek.isKonek()) {
				getTBM().removeAll();
				String query = "SELECT "+name_Table+".*, "+relation1_Table+".`name` AS `item_name`, "+relation2_Table+".`name` AS `user_name` FROM "+name_Table+relation;
				ResultSet rs = konek.getQuery(query);
				transaction(rs);
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void search(String cari) {
		try {
			if (konek.isKonek()) {
				getTBM().removeAll();
				String query = "SELECT "+name_Table+".*, "+relation1_Table+".`name` AS `item_name`, "+relation2_Table+".`name` AS `user_name` FROM "+name_Table+relation+" WHERE "
						+relation1_Table+".`name` LIKE '%"+cari+"%' OR "
						+relation2_Table+".`name` LIKE '%"+cari+"%' OR "
						+"`type` LIKE '%"+cari+"%' OR "
						+relation1_Table+".`quantity` LIKE '%"+cari+"%' OR "
						+"`remarks` LIKE '%"+cari+"%' OR "
						+relation1_Table+".`created_at` LIKE '%"+cari+"%'";
				ResultSet rs = konek.getQuery(query);
				transaction(rs);
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void tambah(boolean home) {
		final V_FormTransaction formTransaction = new V_FormTransaction();
		formTransaction.setVisible(true);
		formTransaction.getBtnSimpan().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				M_Transaction transaction = formTransaction.getData();
				if (!validation(transaction, formTransaction)) {
					return;
				}
				
				try {
					if (konek.isKonek()) {
						String query1 = "INSERT INTO "+name_Table+"(`item_id`, `user_id`, `type`, `quantity`, `remarks`) VALUES ("
								+ "'"+transaction.getItem_id()+"',"
								+ "'"+transaction.getUser_id()+"',"
								+ "'"+transaction.getType()+"',"
								+ "'"+transaction.getQuantity()+"',"
								+ "'"+transaction.getRemarks()+"')";
						if (konek.insert_update_delete(query1) > 0) {
							if (!home) {
								tampilData();
							}
							JOptionPane.showMessageDialog(formTransaction, "Transaksi berhasil di tambahkan!");
							formTransaction.dispose();
						} else {
							JOptionPane.showMessageDialog(formTransaction, "Terjadi kesalahan, data gagal ditambahkan!");
						}
					} else {
						JOptionPane.showMessageDialog(formTransaction, "Koneksi ke server gagal!");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					return;
				}
			}
		});
		formTransaction.getBtnBatal().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				formTransaction.dispose();
			}
		});
	}
	
	public void edit() {
		int pilih = getTable().getSelectedRow();
		if (pilih >= 0) {
			final V_FormTransaction formTransaction = new V_FormTransaction();
			formTransaction.setVisible(true);
			final M_Transaction transaction = getTBM().getData().get(pilih);
			int transaction_id = transaction.getTransaction_id();
			formTransaction.setData(transaction);
			
			formTransaction.getBtnSimpan().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					M_Transaction transaction = formTransaction.getData();
					if (!validation(transaction, formTransaction)) {
						return;
					}
					try {
						if (konek.isKonek()) {
							String query1 = "UPDATE "+name_Table+" SET "
									+ "`item_id`='"+transaction.getItem_id()+"',"
									+ "`user_id`='"+transaction.getUser_id()+"',"
									+ "`type`='"+transaction.getType()+"',"
									+ "`quantity`='"+transaction.getQuantity()+"',"
									+ "`remarks`='"+transaction.getRemarks()+"' "
									+ "WHERE "+id_Table+"= "+transaction_id+";";
							if (konek.insert_update_delete(query1) > 0) {
								transaction.setTransaction_id(transaction_id);
								getTBM().editData(transaction_id, transaction);
								JOptionPane.showMessageDialog(formTransaction, "Transaction berhasil di update!");
								formTransaction.dispose();
							} else {
								JOptionPane.showMessageDialog(formTransaction, "Terjadi kesalahan, data gagal diupdate!");
							}
						} else {
							JOptionPane.showMessageDialog(formTransaction, "Koneksi ke server gagal!");
						}
					} catch (Exception e2) {
						System.out.println(e2);
						return;
					}
				}
			});
			formTransaction.getBtnBatal().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					formTransaction.dispose();
				}
			});
		}
	}
	
	public void hapus() {
		int n = 0;
		for (M_Transaction transaction:getTBM().getData()) {
			if (transaction.isCheck()) {
				n++;
			}
		}
		if (JOptionPane.showConfirmDialog(getParent(), "Yakin ingin menghapus ["+n+"] data ini dari tabel?", "Hapus data!", 
				JOptionPane.YES_NO_CANCEL_OPTION+JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			try {
				if (konek.isKonek()) {
					n = 0;
					for (M_Transaction transaction:getTBM().getData()) {
						if (transaction.isCheck()) {
							String query1 = "DELETE FROM "+name_Table+" WHERE `transaction_id`='"+transaction.getTransaction_id()+"'";
							if (konek.insert_update_delete(query1) > 0) {
								n++;
							}
						}
					}
					JOptionPane.showMessageDialog(getParent(), n+" Data berhasil dihapus!");
					getTBM().removeCheck();
				}
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		}
	}
	
	public void transaction(ResultSet rs) {
		try {
			while (rs.next()) {
				M_Transaction transaction = new M_Transaction();
				transaction.setTransaction_id(rs.getInt("transaction_id"));
				transaction.setItem_name(rs.getString("item_name"));
				transaction.setUser_name(rs.getString("user_name"));
				transaction.setType(rs.getString("type"));
				transaction.setQuantity(rs.getInt("quantity"));
				transaction.setCreated_at(rs.getDate("created_at"));
				transaction.setRemarks(rs.getString("remarks"));
				getTBM().addData(transaction);
			}
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Terjadi suatu kesalahan!");
		}
	}
	
	public boolean validation(M_Transaction transaction, V_FormTransaction formTransaction) {
		if (transaction.getRemarks().equals("")) {
			JOptionPane.showMessageDialog(formTransaction, "Category description is required!");
			formTransaction.getTextRemarks().requestFocusInWindow();
			return false;
		}
		return true;
	}
}
