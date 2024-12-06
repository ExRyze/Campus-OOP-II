package inventaris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
//import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import packages.KoneksiJDBC;

public class C_DaftarInventaris extends V_DaftarInventaris implements ActionListener {
	private KoneksiJDBC konek = new KoneksiJDBC();
	
	public C_DaftarInventaris() {
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
		if(e.getSource() == getPanelAtas().getBtnHapus()) {
			hapus();
		}
		if(e.getSource() == getPanelAtas().getBtnInsert()) {
			tambah();
		}
		if(e.getSource() == getPanelAtas().getBtnEdit()) {
			edit();
		}
		if(e.getSource() == getPanelBawah().getBtnKeluar()) {
			dispose();
		}
	}
	
	public void hapus() {
		int n = 0;
		for (M_Inventaris mb:getTbm_Buku().getDataBuku()) {
			if (mb.isCheck()) {
				n++;
			}
		}
		if (JOptionPane.showConfirmDialog(getParent(), "Yakin ingin menghapus ["+n+"] data dari tabel?", "Hapus data!", 
				JOptionPane.YES_NO_CANCEL_OPTION+JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION) {
			try {
				if (konek.isKonek()) {
					n = 0;
					for (M_Inventaris mb:getTbm_Buku().getDataBuku()) {
						if (mb.isCheck()) {
							String query1 = "DELETE FROM `buku` WHERE `isbn`='"+mb.getIsbn()+"'";
							if (konek.insert_update_delete(query1) > 0) {
								n++;
							}
						}
					}
					JOptionPane.showMessageDialog(getParent(), n+"Data berhasil dihapus!");
					getTbm_Buku().removeCheck();
				}
			} catch (Exception e) {
				System.out.println(e);
				return;
			}
		}
	}
	
	public void tambah() {
		final V_UpdateInventaris vb = new V_UpdateInventaris();
		vb.setVisible(true);
		vb.getBtnSimpan().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				M_Inventaris mb = vb.getBuku();
				if (mb.getIsbn().equals("") || mb.getIsbn().length() != 13) {
					JOptionPane.showMessageDialog(vb, "ISBN tidak memenuhi syarat!");
					vb.getTextIsbn().requestFocusInWindow();
					return;
				}
				if (mb.getJudul().equals("")) {
					JOptionPane.showMessageDialog(vb, "Judul tidak boleh kosong!");
					vb.getTextJudul().requestFocusInWindow();
					return;
				}
				try {
					if (konek.isKonek()) {
						String query = "SELECT * FROM `buku` WHERE isbn = '"+mb.getIsbn()+"'";
						ResultSet rs = konek.getQuery(query);
						rs.next();
						if (rs.getRow()>0) {
							JOptionPane.showMessageDialog(vb, "Buku dengan ISBN tersebut sudah ada, tidak bisa ditambahkan!");
							return;
						} else {
//							SimpleDateFormat sdfinsert = new SimpleDateFormat("yyyy");
							String query1 = "INSERT INTO `buku`(`judul`, `pengarang`, `penerbit`, `tahun_terbit`, `genre`, `jumlah_halaman`, `isbn`) VALUES ("
									+ "'"+mb.getJudul()+"',"
									+ "'"+mb.getPengarang()+"',"
									+ "'"+mb.getPenerbit()+"',"
									+ "'"+mb.getTahun_terbit()+"',"
									+ "'"+mb.getGenre()+"',"
									+ "'"+mb.getJumlah_halaman()+"',"
									+ "'"+mb.getIsbn()+"')";
							if (konek.insert_update_delete(query1) > 0) {
								getTbm_Buku().addDataBuku(mb);
								JOptionPane.showMessageDialog(vb, "Data buku sudah di tambahkan!");
								vb.dispose();
							} else {
								JOptionPane.showMessageDialog(vb, "Terjadi kesalahan, data gagal ditambahkan!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(vb, "Koneksi ke server gagal!");
					}
				} catch (Exception e2) {
					System.out.println(e2);
					return;
				}
			}
		});
		vb.getBtnBatal().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				vb.dispose();
			}
		});
	}
	
	public void edit() {
		int pilih = getTable().getSelectedRow();
		if (pilih >= 0) {
			final V_UpdateInventaris vb = new V_UpdateInventaris();
			vb.setVisible(true);
			final M_Inventaris mb1 = getTbm_Buku().getDataBuku().get(pilih);
			vb.setBuku(mb1);
			vb.getTextIsbn().setEnabled(false);
			
			vb.getBtnSimpan().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					M_Inventaris mb = vb.getBuku();
					if (mb.getJudul().equals("")) {
						JOptionPane.showMessageDialog(vb, "Judul tidak boleh kosong!");
						vb.getTextJudul().requestFocusInWindow();
						return;
					}
					try {
						if (konek.isKonek()) {
//							SimpleDateFormat sdfinsert = new SimpleDateFormat("yyyy");
							String query1 = "UPDATE `buku` SET "
									+ "`judul`='"+mb.getJudul()+"',"
									+ "`pengarang`='"+mb.getPengarang()+"',"
									+ "`penerbit`='"+mb.getPenerbit()+"',"
									+ "`tahun_terbit`='"+mb.getTahun_terbit()+"',"
									+ "`genre`='"+mb.getGenre()+"',"
									+ "`jumlah_halaman`='"+mb.getJumlah_halaman()+"' "
									+ "WHERE `isbn`='"+mb.getIsbn()+"'";
							if (konek.insert_update_delete(query1) > 0) {
								getTbm_Buku().editBuku(mb.getIsbn(), mb);
								JOptionPane.showMessageDialog(vb, "Data buku sudah di update!");
								vb.dispose();
							} else {
								JOptionPane.showMessageDialog(vb, "Terjadi kesalahan, data gagal diupdate!");
							}
						} else {
							JOptionPane.showMessageDialog(vb, "Koneksi ke server gagal!");
						}
					} catch (Exception e2) {
						System.out.println(e2);
						return;
					}
				}
			});
			vb.getBtnBatal().addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					vb.dispose();
				}
			});
		}
	}
	
	public void search(String cari) {
		try {
			if (konek.isKonek()) {
				getTbm_Buku().removeAll();
				String query = "SELECT * FROM `buku` WHERE "
						+"`judul` LIKE '%"+cari+"%' OR "
						+"`pengarang` LIKE '%"+cari+"%' OR "
						+"`penerbit` LIKE '%"+cari+"%' OR "
						+"`tahun_terbit` LIKE '%"+cari+"%' OR "
						+"`genre` LIKE '%"+cari+"%' OR "
						+"`jumlah_halaman` LIKE '%"+cari+"%' OR "
						+"`isbn` LIKE '%"+cari+"%'";
				ResultSet rs = konek.getQuery(query);
				while (rs.next()) {
					M_Inventaris mb = new M_Inventaris();
					mb.setJudul(rs.getString("judul"));
					mb.setPengarang(rs.getString("pengarang"));
					mb.setPenerbit(rs.getString("penerbit"));
					mb.setTahun_terbit(rs.getString("tahun_terbit"));
					mb.setGenre(rs.getString("genre"));
					mb.setJumlah_halaman(rs.getString("jumlah_halaman"));
					mb.setIsbn(rs.getString("isbn"));
					getTbm_Buku().addDataBuku(mb);
				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void tampilData() {
		try {
			if (konek.isKonek()) {
				getTbm_Buku().removeAll();
				String query = "SELECT * FROM `buku`";
				ResultSet rs = konek.getQuery(query);
				while (rs.next()) {
					M_Inventaris mb = new M_Inventaris();
					mb.setJudul(rs.getString("judul"));
					mb.setPengarang(rs.getString("pengarang"));
					mb.setPenerbit(rs.getString("penerbit"));
					mb.setTahun_terbit(rs.getString("tahun_terbit"));
					mb.setGenre(rs.getString("genre"));
					mb.setJumlah_halaman(rs.getString("jumlah_halaman"));
					mb.setIsbn(rs.getString("isbn"));
					getTbm_Buku().addDataBuku(mb);
				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
}
