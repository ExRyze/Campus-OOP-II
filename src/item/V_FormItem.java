package item;

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

public class V_FormItem extends JFrame {
	private Dimension dm = getToolkit().getScreenSize();
	private JButton btnSimpan;
	private JButton btnBatal;
	private JTextField textName;
	private JTextField textQuantity;
	private JComboBox comboCategory = new JComboBox();
	private JComboBox comboUnit = new JComboBox();
	private KoneksiJDBC  konek=new KoneksiJDBC();
	private ArrayList<String> idkatagori;
	private JTextArea textDescription;
	
	public V_FormItem() {
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
		
		JLabel lblNewLabel_2 = new JLabel("Item name");
		lblNewLabel_2.setBounds(10, 33, 125, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Category");
		lblNewLabel_3.setBounds(10, 56, 125, 13);
		panel_1.add(lblNewLabel_3);
		
		textName = new JTextField();
		textName.setBounds(128, 30, 150, 19);
		panel_1.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Quantity");
		lblNewLabel_2_1.setBounds(10, 78, 125, 13);
		panel_1.add(lblNewLabel_2_1);
		
		textQuantity = new JTextField();
		textQuantity.setColumns(10);
		textQuantity.setBounds(128, 75, 150, 19);
		panel_1.add(textQuantity);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("unit");
		lblNewLabel_2_1_1.setBounds(10, 104, 125, 13);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Description");
		lblNewLabel_3_1.setBounds(10, 128, 125, 13);
		panel_1.add(lblNewLabel_3_1);
		
		comboCategory.setModel(new DefaultComboBoxModel(new String[] {}));
		comboCategory.setBounds(128, 52, 150, 21);
		panel_1.add(comboCategory);
		
		comboUnit.setModel(new DefaultComboBoxModel(new String[] {"pcs", "unit"}));
		comboUnit.setBounds(128, 100, 150, 21);
		panel_1.add(comboUnit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 127, 150, 42);
		panel_1.add(scrollPane);
		
		textDescription = new JTextArea();
		scrollPane.setViewportView(textDescription);
		
		idkatagori =new ArrayList<String>();
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM `categories`";
				ResultSet rs = konek.getQuery(query);
				getComboCategory().removeAllItems();
				while (rs.next()) {
					idkatagori.add(rs.getString("category_id"));
					getComboCategory().addItem(rs.getString("name"));
				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
	}
	
	public void setData(M_Item m_Category) {
		getTextName().setText(m_Category.getName());
		getTextQuantity().setText(String.valueOf(m_Category.getQuantity()));
		getTextDescription().setText(m_Category.getDescription());
		try {
			getComboCategory().setSelectedItem(m_Category.getCategory_name());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			getComboUnit().setSelectedItem(m_Category.getUnit());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public M_Item getData() {
		M_Item item = new M_Item();
		item.setName(getTextName().getText());
		item.setQuantity(Integer.parseInt(getTextQuantity().getText()));
		item.setDescription(getTextDescription().getText());
		try {
			item.setUnit(getComboUnit().getSelectedItem().toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM categories WHERE name = '"+getComboCategory().getSelectedItem().toString()+"'";
				ResultSet rs = konek.getQuery(query);
				while (rs.next()) {
					item.setCategory_id(rs.getInt("category_id"));
					item.setCategory_name(getComboCategory().getSelectedItem().toString());				}
				konek.getKoneksi().close();
			}
		} catch (Exception e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(getParent(), "Koneksi gagal!");
		}
		return item;
	}

	public JTextField getTextName() {
		return textName;
	}

	public JComboBox getComboCategory() {
		return comboCategory;
	}

	public JComboBox getComboUnit() {
		return comboUnit;
	}

	public JTextField getTextQuantity() {
		return textQuantity;
	}

	public JButton getBtnSimpan() {
		return btnSimpan;
	}

	public JButton getBtnBatal() {
		return btnBatal;
	}

	public ArrayList<String> getIdkatagori() {
		return idkatagori;
	}
	public JTextArea getTextDescription() {
		return textDescription;
	}
}
