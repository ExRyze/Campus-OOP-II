package category;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class V_FormCategory extends JFrame {
	private Dimension dm = getToolkit().getScreenSize();
	private JTextField textName;
	private JTextArea textDescription;
	private JButton btnSimpan;
	private JButton btnBatal;
	
	public V_FormCategory() {
		super("Data Category");
		int w = (int) dm.getWidth();
		int h = (int) dm.getHeight();
		setBounds(w/2-132, h/2-150, 350, 300);
		
		JLabel lblNewLabel = new JLabel("Data Category");
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
		
		JLabel lblNewLabel_2 = new JLabel("Category name");
		lblNewLabel_2.setBounds(10, 33, 125, 13);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Description");
		lblNewLabel_3.setBounds(10, 56, 125, 13);
		panel_1.add(lblNewLabel_3);
		
		textName = new JTextField();
		textName.setBounds(128, 30, 150, 19);
		panel_1.add(textName);
		textName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 53, 150, 57);
		panel_1.add(scrollPane);
		
		textDescription = new JTextArea();
		scrollPane.setViewportView(textDescription);
	}
	
	public void setData(M_Category m_Category) {
		getTextName().setText(m_Category.getName());
		getTextDescription().setText(m_Category.getDescription());
	}
	
	public M_Category getData() {
		M_Category item = new M_Category();
		item.setName(getTextName().getText());
		item.setDescription(getTextDescription().getText());
		return item;
	}

	public JTextField getTextName() {
		return textName;
	}

	public void setTextName(JTextField textName) {
		this.textName = textName;
	}

	public JTextArea getTextDescription() {
		return textDescription;
	}

	public void setTextDescription(JTextArea textDescription) {
		this.textDescription = textDescription;
	}

	public JButton getBtnSimpan() {
		return btnSimpan;
	}

	public void setBtnSimpan(JButton btnSimpan) {
		this.btnSimpan = btnSimpan;
	}

	public JButton getBtnBatal() {
		return btnBatal;
	}

	public void setBtnBatal(JButton btnBatal) {
		this.btnBatal = btnBatal;
	}
}
