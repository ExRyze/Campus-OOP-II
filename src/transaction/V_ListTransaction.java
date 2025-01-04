package transaction;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import components.V_PanelAtas;
import components.V_PanelBawah;

public class V_ListTransaction extends JInternalFrame {
	private JTable table;
	private ATBM_Transaction tTransaction;
	private V_PanelAtas panelAtas;
	private V_PanelBawah panelBawah;
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ATBM_Transaction getTBM() {
		return tTransaction;
	}

	public void setTBM(ATBM_Transaction tItem) {
		this.tTransaction = tItem;
	}

	public V_ListTransaction() {
		super("List Item");
		panelAtas = new V_PanelAtas();
		panelAtas.getBtnHapus().setText("Delete");
		getContentPane().add(panelAtas,BorderLayout.NORTH);
		panelBawah = new V_PanelBawah();
		getContentPane().add(panelBawah,BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tTransaction = new ATBM_Transaction();
		table.setModel(tTransaction);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(500);
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
