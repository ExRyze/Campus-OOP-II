package item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packages.KoneksiJDBC;

public class C_Item extends V_ListItem implements ActionListener {
	private KoneksiJDBC konek = new KoneksiJDBC();
	private String name_Table = "`items`";
	private String id_Table = "`item_id`";
	private String relation_Table = "`categories`";
	private String relation_Id = "`category_id`";
	private String relation = " INNER JOIN "+relation_Table+" ON "+name_Table+"."+relation_Id+" = "+relation_Table+"."+relation_Id;
	
	public C_Item() {
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
				String query = "SELECT "+name_Table+".*, "+relation_Table+".`name` AS `category_name` FROM "+name_Table+relation;
				ResultSet rs = konek.getQuery(query);
				item(rs);
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
				String query = "SELECT "+name_Table+".*, "+relation_Table+".`name` AS `category_name` FROM "+name_Table+relation+" WHERE "
						+name_Table+".`name` LIKE '%"+cari+"%' OR "
						+relation_Table+".`name` LIKE '%"+cari+"%' OR "
						+"`quantity` LIKE '%"+cari+"%' OR "
						+"`unit` LIKE '%"+cari+"%' OR "
						+name_Table+".`description` LIKE '%"+cari+"%' OR "
						+"`created_at` LIKE '%"+cari+"%'";
				ResultSet rs = konek.getQuery(query);
				item(rs);
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void tambah(boolean home) {
		final V_FormItem formItem = new V_FormItem();
		formItem.setVisible(true);
		
		formItem.getBtnSimpan().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				M_Item item = formItem.getData();
				if (!validation(item, formItem)) {
					return;
				}
				
				try {
					if (konek.isKonek()) {
						String query = "SELECT * FROM "+name_Table+" WHERE `name` = '"+item.getName()+"'";
						ResultSet rs = konek.getQuery(query);
						rs.next();
						if (rs.getRow()>0) {
							JOptionPane.showMessageDialog(formItem, "Item sudah ada!");
							return;
						} else {
							String query1 = "INSERT INTO "+name_Table+"(`name`, `category_id`, `quantity`, `unit`, `description`) VALUES ("
									+ "'"+item.getName()+"',"
									+ "'"+item.getCategory_id()+"',"
									+ "'"+item.getQuantity()+"',"
									+ "'"+item.getUnit()+"',"
									+ "'"+item.getDescription()+"')";
							if (konek.insert_update_delete(query1) > 0) {
								if (!home) {
									String query2 = "SELECT "+name_Table+".*, "+relation_Table+".`name` AS `category_name` FROM "+name_Table+relation+" WHERE "
											+name_Table+".`name` LIKE '"+item.getName()+"'";
									ResultSet rs1 = konek.getQuery(query2);
									item(rs1);
								}
								JOptionPane.showMessageDialog(formItem, "Item berhasil di tambahkan!");
								formItem.dispose();
							} else {
								JOptionPane.showMessageDialog(formItem, "Terjadi kesalahan, data gagal ditambahkan!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(formItem, "Koneksi ke server gagal!");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					return;
				}
			}
		});
		formItem.getBtnBatal().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				formItem.dispose();
			}
		});
	}
	
	public void edit() {
		int pilih = getTable().getSelectedRow();
		if (pilih >= 0) {
			final V_FormItem formItem = new V_FormItem();
			formItem.setVisible(true);
			final M_Item item = getTBM().getData().get(pilih);
			int item_id = item.getItem_id();
			formItem.setData(item);
			
			formItem.getBtnSimpan().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					M_Item item = formItem.getData();
					if (!validation(item, formItem)) {
						return;
					}
					try {
						if (konek.isKonek()) {
							String query1 = "UPDATE "+name_Table+" SET "
									+ "`name`='"+item.getName()+"',"
									+ "`category_id`='"+item.getCategory_id()+"',"
									+ "`quantity`='"+item.getQuantity()+"',"
									+ "`unit`='"+item.getUnit()+"',"
									+ "`description`='"+item.getDescription()+"'"
									+ "WHERE "+id_Table+"= "+item_id+";";
							if (konek.insert_update_delete(query1) > 0) {
								item.setCategory_id(item_id);
								getTBM().editData(item_id, item);
								JOptionPane.showMessageDialog(formItem, "Item berhasil di update!");
								formItem.dispose();
							} else {
								JOptionPane.showMessageDialog(formItem, "Terjadi kesalahan, data gagal diupdate!");
							}
						} else {
							JOptionPane.showMessageDialog(formItem, "Koneksi ke server gagal!");
						}
					} catch (Exception e2) {
						System.out.println(e2);
						return;
					}
				}
			});
			formItem.getBtnBatal().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					formItem.dispose();
				}
			});
		}
	}
	
	public void hapus() {
		int n = 0;
		for (M_Item item:getTBM().getData()) {
			if (item.isCheck()) {
				n++;
			}
		}
		if (JOptionPane.showConfirmDialog(getParent(), "Yakin ingin menghapus ["+n+"] data ini dari tabel?", "Hapus data!", 
				JOptionPane.YES_NO_CANCEL_OPTION+JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			try {
				if (konek.isKonek()) {
					n = 0;
					for (M_Item item:getTBM().getData()) {
						if (item.isCheck()) {
							String query1 = "DELETE FROM "+name_Table+" WHERE `item_id`="+item.getItem_id();
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
	
	public void item(ResultSet rs) {
		try {
			while (rs.next()) {
				M_Item item = new M_Item();
				item.setItem_id(rs.getInt("item_id"));
				item.setName(rs.getString("name"));
				item.setCategory_name(rs.getString("category_name"));
				item.setQuantity(rs.getInt("quantity"));
				item.setUnit(rs.getString("unit"));
				item.setDescription(rs.getString("description"));
				item.setCreated_at(rs.getDate("created_at"));
				getTBM().addData(item);
			}
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Terjadi suatu kesalahan!");
		}
	}
	
	public boolean validation(M_Item item, V_FormItem formItem) {
		if (item.getName().equals("")) {
			JOptionPane.showMessageDialog(formItem, "Category name is required!");
			formItem.getTextName().requestFocusInWindow();
			return false;
		}
		if (item.getDescription().equals("")) {
			JOptionPane.showMessageDialog(formItem, "Category description is required!");
			formItem.getTextDescription().requestFocusInWindow();
			return false;
		}
		if (item.getDescription().equals("")) {
			JOptionPane.showMessageDialog(formItem, "Category description is required!");
			formItem.getTextDescription().requestFocusInWindow();
			return false;
		}
		return true;
	}
}
