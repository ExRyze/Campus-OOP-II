package components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class V_PanelAtas extends JPanel{
	private JTextField fieldSearch;
	private JButton btnSearch;
	private JButton btnRefresh;
	private JButton btnInsert;
	private JButton btnEdit;
	private JButton btnHapus;
	private JButton btnPrint;
	
	public V_PanelAtas() {
		setLayout(new MigLayout("inset 2 2 2 2 2", "[100px:100px:100px][grow][400px:400px:400px,grow]", "[40px:40px:40px,grow]"));
		
		JLabel lblNewLabel = new JLabel("Searh");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, "cell 0 0,grow");
		
		JPanel panel = new JPanel();
		add(panel, "cell 1 0,grow");
		panel.setLayout(new MigLayout("", "[grow][250px:250px:250px,grow]", "[grow]"));
		
		fieldSearch = new JTextField();
		panel.add(fieldSearch, "cell 0 0,growx");
		fieldSearch.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 1 0,grow");
		panel_1.setLayout(null);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(0, 0, 115, 21);
		panel_1.add(btnSearch);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRefresh.setBounds(125, 0, 115, 21);
		panel_1.add(btnRefresh);
		
		JPanel panel_2 = new JPanel();
		add(panel_2, "cell 2 0,grow");
		panel_2.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnInsert = new JButton("Insert");
		panel_2.add(btnInsert);
		
		btnEdit = new JButton("Edit");
		panel_2.add(btnEdit);
		
		btnHapus = new JButton("Delete");
		panel_2.add(btnHapus);
		
		btnPrint = new JButton("Print");
		panel_2.add(btnPrint);
	}
	
	public JTextField getFieldSearch() {
		return fieldSearch;
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
	public JButton getBtnRefresh() {
		return btnRefresh;
	}
	public JButton getBtnInsert() {
		return btnInsert;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnHapus() {
		return btnHapus;
	}
	public JButton getBtnPrint() {
		return btnPrint;
	}
}
