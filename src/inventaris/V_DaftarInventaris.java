package inventaris;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import components.V_PanelAtas;
import components.V_PanelBawah;

public class V_DaftarInventaris extends JInternalFrame {
	private JTable table;
	private AbstractTBM_Inventaris tbm_Buku;
	private V_PanelAtas panelAtas;
	private V_PanelBawah panelBawah;
	
	
	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public AbstractTBM_Inventaris getTbm_Buku() {
		return tbm_Buku;
	}


	public void setTbm_Buku(AbstractTBM_Inventaris tbm_Buku) {
		this.tbm_Buku = tbm_Buku;
	}


	public V_DaftarInventaris() {
		super("Daftar Buku");
		panelAtas = new V_PanelAtas();
		panelAtas.getBtnHapus().setText("Delete");
		getContentPane().add(panelAtas,BorderLayout.NORTH);
		panelBawah = new V_PanelBawah();
		getContentPane().add(panelBawah,BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		tbm_Buku = new AbstractTBM_Inventaris();
		table.setModel(tbm_Buku);
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(250);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(120);
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
