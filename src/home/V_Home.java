package home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class V_Home extends JFrame {
	Dimension dm = getToolkit().getScreenSize();
	private JLabel lblTanggal;
	private JLabel lblJam;
	private JLabel lblStatus;
	private JDesktopPane desktopPane;
	private JMenuItem mntmDataBuku;
	private JMenu mnFile;
	private JMenuItem mntmTambahBuku;
	private JMenuItem mntmExit;
	
	public V_Home() {
		super("System Inventaris");
		int w = (int)dm.getWidth();
		int h = (int)dm.getHeight();
		setBounds(0, 0, w+1, h+1);
		
		JToolBar toolBar_2 = new JToolBar();
		getContentPane().add(toolBar_2, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		toolBar_2.add(panel);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		lblTanggal = new JLabel("New label");
		lblTanggal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTanggal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblTanggal);
		
		lblJam = new JLabel("New label");
		lblJam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblJam);
		
		lblStatus = new JLabel("New label");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblStatus);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.activeCaption);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		mnFile.add(mntmExit);
		
		JMenu mnBuku = new JMenu("Buku");
		menuBar.add(mnBuku);
		
		JMenu mnDataBuku = new JMenu("Data Buku");
		mnBuku.add(mnDataBuku);
		
		mntmDataBuku = new JMenuItem("Daftar Data Buku");
		mntmDataBuku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		mnDataBuku.add(mntmDataBuku);
		
		mntmTambahBuku = new JMenuItem("Tambah Data Buku");
		mntmTambahBuku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mnDataBuku.add(mntmTambahBuku);
	}
	
	public JMenuItem getMntmTambahBuku() {
		return mntmTambahBuku;
	}
	public JMenuItem getMntmDataBuku() {
		return mntmDataBuku;
	}
	public JLabel getLblJam() {
		return lblJam;
	}
	public JLabel getLblStatus() {
		return lblStatus;
	}
	public JLabel getLblTanggal() {
		return lblTanggal;
	}
	public JMenuItem getMntmExit() {
		return mntmExit;
	}
}
