package welcome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class V_Welcome extends JFrame {
	private Dimension dm = getToolkit().getScreenSize();
	private JButton btnLogin;
	private JButton btnQuit;
	
	public V_Welcome() {
		super("Welcome");
		int w = (int) dm.getWidth();
		int h = (int) dm.getHeight();
		setBounds(w/2-132, h/2-150, 350, 300);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome!");
		lblNewLabel.setBounds(0, 28, 336, 20);
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(81, 105, 168, 21);
		panel_1.add(btnLogin);
		
		btnQuit = new JButton("Quit");
		btnQuit.setBounds(81, 136, 168, 21);
		panel_1.add(btnQuit);
	}
	
	public JButton getBtnQuit() {
		return btnQuit;
	}

	public void setBtnQuit(JButton btnQuit) {
		this.btnQuit = btnQuit;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}
}
