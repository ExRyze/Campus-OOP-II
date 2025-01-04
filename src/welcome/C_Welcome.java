package welcome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import auth.login.C_Login;

public class C_Welcome extends V_Welcome implements ActionListener {
	
	public C_Welcome() {
		getBtnLogin().addActionListener(this);
		getBtnQuit().addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == getBtnLogin()) {
			C_Login login = new C_Login();
			login.setVisible(true);
			dispose();
		}
		if(e.getSource() == getBtnQuit()) {
			System.exit(0);
		}
	}
}