package home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Timer;

import category.C_Category;
import item.C_Item;
import transaction.C_Transaction;
import user.C_User;
import auth.login.M_Auth;

public class C_Home extends V_Home implements ActionListener {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
	private SimpleDateFormat sdt = new SimpleDateFormat("hh : mm : ss");
	private Timer tmTimer;
	protected M_Auth sessionAuth;
	
	public C_Home() {
		getLblTanggal().setText("Tanggal : "+sdf.format(Calendar.getInstance().getTime()));
		tmTimer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLblJam().setText(" Jam : "+sdt.format(Calendar.getInstance().getTime()));
			}
		});
		tmTimer.start();
		
		getMntmExit().addActionListener(this);
		getMntmListCategory().addActionListener(this);
		getMntmNewCategory().addActionListener(this);
		getMntmListItem().addActionListener(this);
		getMntmNewItem().addActionListener(this);
		getMntmListUser().addActionListener(this);
		getMntmNewUser().addActionListener(this);
		getMntmListTransaction().addActionListener(this);
		getMntmNewTransaction().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getMntmExit()) {
			System.exit(0);
		}
		else if (e.getSource() == getMntmListCategory()) {
			C_Category category = new C_Category();
			this.getDesktopPane().add(category);
			category.setVisible(true);
			try {
				category.setMaximum(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmNewCategory()) {
			C_Category category = new C_Category();
			try {
				category.tambah(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmListItem()) {
			C_Item item = new C_Item();
			this.getDesktopPane().add(item);
			item.setVisible(true);
			try {
				item.setMaximum(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmNewItem()) {
			C_Item item = new C_Item();
			try {
				item.tambah(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmListUser()) {
			C_User user = new C_User();
			this.getDesktopPane().add(user);
			user.setVisible(true);
			try {
				user.setMaximum(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmNewUser()) {
			C_User user = new C_User();
			try {
				user.tambah(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmListTransaction()) {
			C_Transaction transaction = new C_Transaction();
			this.getDesktopPane().add(transaction);
			transaction.setVisible(true);
			try {
				transaction.setMaximum(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
		else if (e.getSource() == getMntmNewTransaction()) {
			C_Transaction transaction = new C_Transaction();
			try {
				transaction.tambah(true);
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
	}

	public void setSessionAuth(M_Auth sessionAuth) {
		this.sessionAuth = sessionAuth;
		getLblStatus().setText("Username : "+this.sessionAuth.getUsername());
	}
}
