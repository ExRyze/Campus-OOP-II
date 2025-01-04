package transaction;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import packages.KoneksiJDBC;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class V_FormTransaction extends JFrame {
	private Dimension dm = getToolkit().getScreenSize();
	private JButton btnSimpan;
	private JButton btnBatal;
	private JTextField textQuantity;
	private JComboBox comboUser = new JComboBox();
	private JComboBox comboType = new JComboBox();
	private KoneksiJDBC  konek=new KoneksiJDBC();
	private ArrayList<String> idItem;
	private ArrayList<String> idUser;
	private JTextArea textRemarks;
	private JComboBox comboItem;
	
	public V_FormTransaction() {
		super("Data Item");
		int w = (int) dm.getWidth();
		int h = (int) dm.getHeight();
		setBounds(w/2-132, h/2-150, 350, 300);
		
		JLabel lblNewLabel = new JLabel("Data Item");
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
		
		JLabel lblNewLabel_2 = new JLabel("Item");
		lblNewLabel_2.setBounds(10, 33, 125, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("User");
		lblNewLabel_3.setBounds(10, 56, 125, 13);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Quantity");
		lblNewLabel_2_1.setBounds(10, 106, 125, 13);
		panel_1.add(lblNewLabel_2_1);
		
		textQuantity = new JTextField();
		textQuantity.setColumns(10);
		textQuantity.setBounds(128, 103, 150, 19);
		panel_1.add(textQuantity);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("type");
		lblNewLabel_2_1_1.setBounds(10, 80, 125, 13);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Remarks");
		lblNewLabel_3_1.setBounds(10, 128, 125, 13);
		panel_1.add(lblNewLabel_3_1);
		
		comboUser.setModel(new DefaultComboBoxModel(new String[] {}));
		comboUser.setBounds(128, 52, 150, 21);
		panel_1.add(comboUser);
		
		comboType.setModel(new DefaultComboBoxModel(new String[] {"in", "out"}));
		comboType.setBounds(128, 76, 150, 21);
		panel_1.add(comboType);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 127, 150, 42);
		panel_1.add(scrollPane);
		
		textRemarks = new JTextArea();
		scrollPane.setViewportView(textRemarks);
		
		comboItem = new JComboBox();
		comboItem.setBounds(128, 29, 150, 21);
		panel_1.add(comboItem);
		
		idItem =new ArrayList<String>();
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM `items`";
				ResultSet rs = konek.getQuery(query);
				getComboItem().removeAllItems();
				while (rs.next()) {
					idItem.add(rs.getString("item_id"));
					getComboItem().addItem(rs.getString("name"));
				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
		
		idUser =new ArrayList<String>();
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM `users`";
				ResultSet rs = konek.getQuery(query);
				getComboUser().removeAllItems();
				while (rs.next()) {
					idUser.add(rs.getString("user_id"));
					getComboUser().addItem(rs.getString("name"));
				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void setData(M_Transaction m_Transaction) {
		try {
			getComboItem().setSelectedItem(m_Transaction.getItem_name());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			getComboUser().setSelectedItem(m_Transaction.getUser_name());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			getComboType().setSelectedItem(m_Transaction.getType());
		} catch (Exception e) {
			// TODO: handle exception
		}
		getTextQuantity().setText(String.valueOf(m_Transaction.getQuantity()));
		getTextRemarks().setText(m_Transaction.getRemarks());
	}
	
	public M_Transaction getData() {
		M_Transaction transaction = new M_Transaction();
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM items WHERE name = '"+getComboItem().getSelectedItem().toString()+"'";
				ResultSet rs = konek.getQuery(query);
				while (rs.next()) {
					transaction.setItem_id(rs.getInt("item_id"));
					transaction.setItem_name(getComboItem().getSelectedItem().toString());				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM users WHERE name = '"+getComboUser().getSelectedItem().toString()+"'";
				ResultSet rs = konek.getQuery(query);
				while (rs.next()) {
					transaction.setUser_id(rs.getInt("user_id"));
					transaction.setUser_name(getComboItem().getSelectedItem().toString());				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
		try {
			transaction.setType(getComboType().getSelectedItem().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		transaction.setQuantity(Integer.parseInt(getTextQuantity().getText()));
		transaction.setRemarks(getTextRemarks().getText());
		return transaction;
	}
	
	public JButton getBtnSimpan() {
		return btnSimpan;
	}

	public JButton getBtnBatal() {
		return btnBatal;
	}
	
	public JComboBox getComboItem() {
		return comboItem;
	}
	
	public JComboBox getComboUser() {
		return comboUser;
	}
	
	public JComboBox getComboType() {
		return comboType;
	}
	
	public JTextField getTextQuantity() {
		return textQuantity;
	}
	
	public JTextArea getTextRemarks() {
		return textRemarks;
	}

	public ArrayList<String> getIdItem() {
		return idItem;
	}

	public ArrayList<String> getIdUser() {
		return idUser;
	}
}
