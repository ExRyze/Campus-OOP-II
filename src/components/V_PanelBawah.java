package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class V_PanelBawah extends JPanel {
	private JLabel lblKeterangan;
	private JButton btnPrevious;
	private JButton btnRefresh;
	private JButton btnNext;
	private JButton btnKeluar;
	private JButton btnFirst;
	private JButton btnLast;
	public V_PanelBawah() {
		setLayout(new MigLayout("inset 2 2 2 2 2", "[450px:450px:450px,grow][grow][200px:200px:200px]", "[40px:40px:40px,grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new GridLayout(0, 5, 0, 0));
		
		btnFirst = new JButton("|<");
		btnFirst.setForeground(new Color(210, 105, 30));
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnFirst);
		
		btnPrevious = new JButton("<<");
		btnPrevious.setForeground(new Color(210, 105, 30));
		btnPrevious.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnPrevious);
		
		btnRefresh = new JButton("||");
		btnRefresh.setForeground(new Color(210, 105, 30));
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnRefresh);
		
		btnNext = new JButton(">>");
		btnNext.setForeground(new Color(210, 105, 30));
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnNext);
		
		btnLast = new JButton(">|");
		btnLast.setForeground(new Color(210, 105, 30));
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnLast);
		
		lblKeterangan = new JLabel("Keterangan");
		lblKeterangan.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblKeterangan, "cell 1 0,grow");
		
		btnKeluar = new JButton("Keluar");
		add(btnKeluar, "cell 2 0,grow");
	}

	public JLabel getLblKeterangan() {
		return lblKeterangan;
	}
	public JButton getBtnPrevious() {
		return btnPrevious;
	}
	public JButton getBtnRefresh() {
		return btnRefresh;
	}
	public JButton getBtnNext() {
		return btnNext;
	}
	public JButton getBtnKeluar() {
		return btnKeluar;
	}
	public JButton getBtnFirst() {
		return btnFirst;
	}
	public JButton getBtnLast() {
		return btnLast;
	}
}
