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
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JMenu mnItem;
	private JMenuItem mntmListCategory;
	private JMenuItem mntmNewCategory;
	private JMenuItem mntmListItem;
	private JMenuItem mntmNewItem;
	private JMenu mnUser;
	private JMenuItem mntmListUser;
	private JMenuItem mntmNewUser;
	private JMenu mnTransaction;
	private JMenuItem mntmListTransaction;
	private JMenuItem mntmNewTransaction;
	
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
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, InputEvent.CTRL_DOWN_MASK));
		mnFile.add(mntmExit);
		
		JMenu mnCategory = new JMenu("Category");
		menuBar.add(mnCategory);
		
		mntmListCategory = new JMenuItem("List Category");
		mnCategory.add(mntmListCategory);
		
		mntmNewCategory = new JMenuItem("New Category");
		mnCategory.add(mntmNewCategory);
		
		mnItem = new JMenu("Item");
		menuBar.add(mnItem);
		
		mntmListItem = new JMenuItem("List Item");
		mnItem.add(mntmListItem);
		
		mntmNewItem = new JMenuItem("New Item");
		mnItem.add(mntmNewItem);
		
		mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		mntmListUser = new JMenuItem("List User");
		mnUser.add(mntmListUser);
		
		mntmNewUser = new JMenuItem("New User");
		mnUser.add(mntmNewUser);
		
		mnTransaction = new JMenu("Transaction");
		menuBar.add(mnTransaction);
		
		mntmListTransaction = new JMenuItem("List Transaction");
		mnTransaction.add(mntmListTransaction);
		
		mntmNewTransaction = new JMenuItem("New Item");
		mnTransaction.add(mntmNewTransaction);
	}
	
	public JMenuItem getMntmListCategory() {
		return mntmListCategory;
	}

	public void setMntmListCategory(JMenuItem mntmListCategory) {
		this.mntmListCategory = mntmListCategory;
	}

	public JMenuItem getMntmNewCategory() {
		return mntmNewCategory;
	}

	public void setMntmNewCategory(JMenuItem mntmNewCategory) {
		this.mntmNewCategory = mntmNewCategory;
	}

	public JMenuItem getMntmListItem() {
		return mntmListItem;
	}

	public void setMntmListItem(JMenuItem mntmListItem) {
		this.mntmListItem = mntmListItem;
	}

	public JMenuItem getMntmNewItem() {
		return mntmNewItem;
	}

	public void setMntmNewItem(JMenuItem mntmNewItem) {
		this.mntmNewItem = mntmNewItem;
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
	public JMenuItem getMntmListTransaction() {
		return mntmListTransaction;
	}
	public JMenuItem getMntmNewTransaction() {
		return mntmNewTransaction;
	}
	public JMenuItem getMntmListUser() {
		return mntmListUser;
	}
	public JMenuItem getMntmNewUser() {
		return mntmNewUser;
	}
}
