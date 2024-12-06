package inventaris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import com.toedter.calendar.JDateChooser;

public class V_UpdateInventaris extends JFrame {
	private Dimension dm = getToolkit().getScreenSize();
	private JTextField textIsbn;
	private JTextField textJudul;
	private JTextField textPengarang;
	private JTextField textTahunTerbit;
	private JTextField textPenerbit;
	private JComboBox comboBoxGenre;
	private JButton btnSimpan;
	private JButton btnBatal;
	private JTextField textJumlahHalaman;
	
	public V_UpdateInventaris() {
		super("Data Buku");
		int w = (int) dm.getWidth();
		int h = (int) dm.getHeight();
		setBounds(w/2-132, h/2-150, 350, 300);
		
		JLabel lblNewLabel = new JLabel("Data Buku");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnSimpan = new JButton("Simpan");
		panel.add(btnSimpan);
		
		btnBatal = new JButton("Batal");
		panel.add(btnBatal);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN");
		lblNewLabel_1.setBounds(10, 10, 125, 13);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Judul Buku");
		lblNewLabel_2.setBounds(10, 33, 125, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Pengarang");
		lblNewLabel_3.setBounds(10, 56, 125, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Penerbit");
		lblNewLabel_4.setBounds(10, 79, 125, 13);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Jumlah halaman");
		lblNewLabel_5.setBounds(10, 148, 125, 13);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Genre");
		lblNewLabel_6.setBounds(10, 125, 125, 13);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Tahun terbit");
		lblNewLabel_7.setBounds(10, 102, 125, 13);
		panel_1.add(lblNewLabel_7);
		
		textIsbn = new JTextField();
		textIsbn.setBounds(128, 7, 150, 19);
		panel_1.add(textIsbn);
		textIsbn.setColumns(10);
		
		textJudul = new JTextField();
		textJudul.setBounds(128, 30, 150, 19);
		panel_1.add(textJudul);
		textJudul.setColumns(10);
		
		textPengarang = new JTextField();
		textPengarang.setBounds(128, 53, 150, 19);
		panel_1.add(textPengarang);
		textPengarang.setColumns(10);
		
		textTahunTerbit = new JTextField();
		textTahunTerbit.setBounds(128, 99, 150, 19);
		panel_1.add(textTahunTerbit);
		
		comboBoxGenre = new JComboBox();
		comboBoxGenre.setModel(new DefaultComboBoxModel(new String[] {"Jurnal", "Komik", "Novel", "Skripsi"}));
		comboBoxGenre.setBounds(128, 121, 150, 21);
		panel_1.add(comboBoxGenre);
		
		textPenerbit = new JTextField();
		textPenerbit.setBounds(128, 76, 150, 19);
		panel_1.add(textPenerbit);
		textPenerbit.setColumns(10);
		
		textJumlahHalaman = new JTextField();
		textJumlahHalaman.setColumns(10);
		textJumlahHalaman.setBounds(128, 145, 150, 19);
		panel_1.add(textJumlahHalaman);
	}
	
	public void setBuku(M_Inventaris mb) {
		getTextIsbn().setText(mb.getIsbn());
		getTextJudul().setText(mb.getJudul());
		getTextPengarang().setText(mb.getPengarang());
		getTextPenerbit().setText(mb.getPenerbit());
		getTextTahunTerbit().setText(mb.getTahun_terbit());
		try {
			getComboBoxGenre().setSelectedItem(mb.getGenre());
		} catch (Exception e) {
			System.out.println(e);
		}
		getTextJumlahHalaman().setText(mb.getJumlah_halaman());
	}
	
	public M_Inventaris getBuku() {
		M_Inventaris mb = new M_Inventaris();
		mb.setIsbn(getTextIsbn().getText());
		mb.setJudul(getTextJudul().getText());
		mb.setPengarang(getTextPengarang().getText());
		mb.setPenerbit(getTextPenerbit().getText());
		mb.setTahun_terbit(getTextTahunTerbit().getText());
		try {
			mb.setGenre(getComboBoxGenre().getSelectedItem().toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		mb.setJumlah_halaman(getTextJumlahHalaman().getText());
		return mb;
	}
	
	public JButton getBtnSimpan() {
		return btnSimpan;
	}
	public JButton getBtnBatal() {
		return btnBatal;
	}
	public JComboBox getComboBoxGenre() {
		return comboBoxGenre;
	}
	public JTextField getTextJumlahHalaman() {
		return textJumlahHalaman;
	}
	public JTextField getTextPenerbit() {
		return textPenerbit;
	}
	public JTextField getTextPengarang() {
		return textPengarang;
	}
	public JTextField getTextTahunTerbit() {
		return textTahunTerbit;
	}
	public JTextField getTextJudul() {
		return textJudul;
	}
	public JTextField getTextIsbn() {
		return textIsbn;
	}
}
