package auth.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import home.C_Home;
import packages.KoneksiJDBC;
import welcome.C_Welcome;
import welcome.M_Auth;

public class C_Login extends V_FormLogin implements ActionListener {
	private KoneksiJDBC konek = new KoneksiJDBC();
	private String name_Table = "`admins`";
	
	public C_Login() {
		getBtnLogin().addActionListener(this);
		getBtnBatal().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getBtnLogin()) {
			login();
		}
		if(e.getSource() == getBtnBatal()) {
			C_Welcome welcome = new C_Welcome();
			welcome.setVisible(true);
			dispose();
		}
	}
	
	public void login() {
		M_Auth login = this.getData();
		try {
			if (konek.isKonek()) {
				String query = "SELECT * FROM "+name_Table+" WHERE "
						+ "`username` = '"+login.getUsername()+"' AND "
						+ "`password` = '"+login.getPassword()+"'";
				ResultSet rs = konek.getQuery(query);
				rs.next();
				if (rs.getRow()>0) {
					JOptionPane.showMessageDialog(this, "Login berhasil!");
					C_Home menu = new C_Home();
					menu.setSessionAuth(login);
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Login gagal!");
					return;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Koneksi ke server gagal!");
			}
		} catch (Exception e2) {
			System.out.println(e2);
			return;
		}
	}
}