package category;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packages.KoneksiJDBC;

public class C_Category extends V_ListCategory implements ActionListener {
	private KoneksiJDBC konek = new KoneksiJDBC();
	private String name_Table = "`categories`";
	private String id_Table = "`category_id`";
	
	public C_Category() {
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
				String query = "SELECT * FROM "+name_Table;
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
				String query = "SELECT * FROM "+name_Table+" WHERE "
						+"`name` LIKE '%"+cari+"%' OR "
						+"`description` LIKE '%"+cari+"%'";
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
		final V_FormCategory formCategory = new V_FormCategory();
		formCategory.setVisible(true);
		formCategory.getBtnSimpan().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				M_Category category = formCategory.getData();
				if (!validation(category, formCategory)) {
					return;
				}
				
				try {
					if (konek.isKonek()) {
						String query = "SELECT * FROM "+name_Table+" WHERE `name` = '"+category.getName()+"'";
						ResultSet rs = konek.getQuery(query);
						rs.next();
						if (rs.getRow()>0) {
							JOptionPane.showMessageDialog(formCategory, "Category sudah ada!");
							return;
						} else {
							String query1 = "INSERT INTO "+name_Table+"(`name`, `description`) VALUES ("
									+ "'"+category.getName()+"',"
									+ "'"+category.getDescription()+"')";
							if (konek.insert_update_delete(query1) > 0) {
								if (!home) {
									String query2 = "SELECT * FROM `"+name_Table+"` WHERE "
											+"`name` LIKE '"+category.getName()+"'";
									ResultSet rs1 = konek.getQuery(query2);
									item(rs1);
								}
								JOptionPane.showMessageDialog(formCategory, "Category berhasil di tambahkan!");
								formCategory.dispose();
							} else {
								JOptionPane.showMessageDialog(formCategory, "Terjadi kesalahan, data gagal ditambahkan!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(formCategory, "Koneksi ke server gagal!");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					return;
				}
			}
		});
		formCategory.getBtnBatal().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				formCategory.dispose();
			}
		});
	}
	
	public void edit() {
		int pilih = getTable().getSelectedRow();
		if (pilih >= 0) {
			final V_FormCategory formCategory = new V_FormCategory();
			formCategory.setVisible(true);
			final M_Category category = getTBM().getData().get(pilih);
			int category_id = category.getCategory_id();
			formCategory.setData(category);
			
			formCategory.getBtnSimpan().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					M_Category category = formCategory.getData();
					if (!validation(category, formCategory)) {
						return;
					}
					try {
						if (konek.isKonek()) {
							String query1 = "UPDATE "+name_Table+" SET "
									+ "`name`='"+category.getName()+"',"
									+ "`description`='"+category.getDescription()+"'"
									+ "WHERE "+id_Table+" = "+category_id+";";
							if (konek.insert_update_delete(query1) > 0) {
								category.setCategory_id(category_id);
								getTBM().editData(category_id, category);
								JOptionPane.showMessageDialog(formCategory, "Category berhasil di update!");
								formCategory.dispose();
							} else {
								JOptionPane.showMessageDialog(formCategory, "Terjadi kesalahan, data gagal diupdate!");
							}
						} else {
							JOptionPane.showMessageDialog(formCategory, "Koneksi ke server gagal!");
						}
					} catch (Exception e2) {
						System.out.println(e2);
						return;
					}
				}
			});
			formCategory.getBtnBatal().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					formCategory.dispose();
				}
			});
		}
	}
	
	public void hapus() {
		int n = 0;
		for (M_Category category:getTBM().getData()) {
			if (category.isCheck()) {
				n++;
			}
		}
		if (JOptionPane.showConfirmDialog(getParent(), "Yakin ingin menghapus ["+n+"] data ini dari tabel?", "Hapus data!", 
				JOptionPane.YES_NO_CANCEL_OPTION+JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			try {
				if (konek.isKonek()) {
					n = 0;
					for (M_Category category:getTBM().getData()) {
						if (category.isCheck()) {
							String query1 = "DELETE FROM "+name_Table+" WHERE "+id_Table+"='"+category.getCategory_id()+"'";
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
				M_Category item = new M_Category();
				item.setCategory_id(rs.getInt("category_id"));
				item.setName(rs.getString("name"));
				item.setDescription(rs.getString("description"));
				getTBM().addData(item);
			}
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Terjadi suatu kesalahan!");
		}
	}
	
	public boolean validation(M_Category category, V_FormCategory formCategory) {
		if (category.getName().equals("")) {
			JOptionPane.showMessageDialog(formCategory, "Category name is required!");
			formCategory.getTextName().requestFocusInWindow();
			return false;
		}
		if (category.getDescription().equals("")) {
			JOptionPane.showMessageDialog(formCategory, "Category description is required!");
			formCategory.getTextDescription().requestFocusInWindow();
			return false;
		}
		return true;
	}
}
