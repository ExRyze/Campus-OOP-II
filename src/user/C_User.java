package user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import packages.KoneksiJDBC;

public class C_User extends V_ListUser implements ActionListener {
	private KoneksiJDBC konek = new KoneksiJDBC();
	private String name_Table = "`users`";
	private String id_Table = "`user_id`";
	
	public C_User() {
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
				user(rs);
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
						+"`name` LIKE '%"+cari+"%'";
				ResultSet rs = konek.getQuery(query);
				user(rs);
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void tambah(boolean home) {
		final V_FormUser formUser = new V_FormUser();
		formUser.setVisible(true);
		formUser.getBtnSimpan().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				M_User user = formUser.getData();
				if (!validation(user, formUser)) {
					return;
				}
				
				try {
					if (konek.isKonek()) {
						String query = "SELECT * FROM "+name_Table+" WHERE `name` = '"+user.getName()+"'";
						ResultSet rs = konek.getQuery(query);
						rs.next();
						if (rs.getRow()>0) {
							JOptionPane.showMessageDialog(formUser, "User sudah ada!");
							return;
						} else {
							String query1 = "INSERT INTO "+name_Table+"(`name`) VALUES ("
									+ "'"+user.getName()+"')";
							if (konek.insert_update_delete(query1) > 0) {
								if (!home) {
									String query2 = "SELECT * FROM "+name_Table+" WHERE "
											+"`name` LIKE '"+user.getName()+"'";
									ResultSet rs1 = konek.getQuery(query2);
									user(rs1);
								}
								JOptionPane.showMessageDialog(formUser, "User berhasil di tambahkan!");
								formUser.dispose();
							} else {
								JOptionPane.showMessageDialog(formUser, "Terjadi kesalahan, data gagal ditambahkan!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(formUser, "Koneksi ke server gagal!");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					return;
				}
			}
		});
		formUser.getBtnBatal().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				formUser.dispose();
			}
		});
	}
	
	public void edit() {
		int pilih = getTable().getSelectedRow();
		if (pilih >= 0) {
			final V_FormUser formUser = new V_FormUser();
			formUser.setVisible(true);
			final M_User user = getTBM().getData().get(pilih);
			int user_id = user.getUser_id();
			formUser.setData(user);
			
			formUser.getBtnSimpan().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					M_User user = formUser.getData();
					if (!validation(user, formUser)) {
						return;
					}
					try {
						if (konek.isKonek()) {
							String query1 = "UPDATE "+name_Table+" SET "
									+ "`name`='"+user.getName()+"'"
									+ "WHERE "+id_Table+" = "+user_id+";";
							if (konek.insert_update_delete(query1) > 0) {
								user.setUser_id(user_id);
								getTBM().editData(user_id, user);
								JOptionPane.showMessageDialog(formUser, "User berhasil di update!");
								formUser.dispose();
							} else {
								JOptionPane.showMessageDialog(formUser, "Terjadi kesalahan, data gagal diupdate!");
							}
						} else {
							JOptionPane.showMessageDialog(formUser, "Koneksi ke server gagal!");
						}
					} catch (Exception e2) {
						System.out.println(e2);
						return;
					}
				}
			});
			formUser.getBtnBatal().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					formUser.dispose();
				}
			});
		}
	}
	
	public void hapus() {
		int n = 0;
		for (M_User user:getTBM().getData()) {
			if (user.isCheck()) {
				n++;
			}
		}
		if (JOptionPane.showConfirmDialog(getParent(), "Yakin ingin menghapus ["+n+"] data ini dari tabel?", "Hapus data!", 
				JOptionPane.YES_NO_CANCEL_OPTION+JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			try {
				if (konek.isKonek()) {
					n = 0;
					for (M_User user:getTBM().getData()) {
						if (user.isCheck()) {
							String query1 = "DELETE FROM "+name_Table+" WHERE `user_id`='"+user.getUser_id()+"'";
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
	
	public void user(ResultSet rs) {
		try {
			while (rs.next()) {
				M_User user = new M_User();
				user.setUser_id(rs.getInt("user_id"));
				user.setName(rs.getString("name"));
				user.setCreated_at(rs.getDate("created_at"));
				getTBM().addData(user);
			}
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Terjadi suatu kesalahan!");
		}
	}
	
	public boolean validation(M_User user, V_FormUser formUser) {
		if (user.getName().equals("")) {
			JOptionPane.showMessageDialog(formUser, "User name is required!");
			formUser.getTextName().requestFocusInWindow();
			return false;
		}
		return true;
	}
}
