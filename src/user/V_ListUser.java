package user;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import components.V_PanelAtas;
import components.V_PanelBawah;

public class V_ListUser extends JInternalFrame {
	private JTable table;
	private ATBM_User tUser;
	private V_PanelAtas panelAtas;
	private V_PanelBawah panelBawah;
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ATBM_User getTBM() {
		return tUser;
	}

	public void setTBM(ATBM_User tItem) {
		this.tUser = tItem;
	}

	public V_ListUser() {
		super("List Item");
		panelAtas = new V_PanelAtas();
		panelAtas.getBtnHapus().setText("Delete");
		getContentPane().add(panelAtas,BorderLayout.NORTH);
		panelBawah = new V_PanelBawah();
		getContentPane().add(panelBawah,BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tUser = new ATBM_User();
		table.setModel(tUser);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
	}
	
	public V_PanelAtas getPanelAtas() {
		return panelAtas;
	}
	
	public V_PanelBawah getPanelBawah() {
		return panelBawah;
	}
}
