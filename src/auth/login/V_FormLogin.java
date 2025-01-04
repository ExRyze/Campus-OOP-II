package auth.login;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class V_FormLogin extends JFrame {
	private Dimension dm = getToolkit().getScreenSize();
	private JTextField textUsername;
	private JButton btnLogin;
	private JButton btnBatal;
	private JTextField textPassword;
	
	public V_FormLogin() {
		super("Data Category");
		int w = (int) dm.getWidth();
		int h = (int) dm.getHeight();
		setBounds(w/2-132, h/2-150, 350, 300);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnBatal = new JButton("Batal");
		panel.add(btnBatal);
		
		btnLogin = new JButton("Login");
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setBounds(10, 33, 125, 13);
		panel_1.add(lblNewLabel_2);
		
		textUsername = new JTextField();
		textUsername.setBounds(128, 30, 150, 19);
		panel_1.add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(128, 56, 150, 19);
		panel_1.add(textPassword);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password");
		lblNewLabel_2_1.setBounds(11, 60, 125, 13);
		panel_1.add(lblNewLabel_2_1);
	}
	
	public M_Auth getData() {
		M_Auth login = new M_Auth();
		login.setUsername(getTextUsername().getText());
		login.setPassword(getTextPassword().getText());
		return login;
	}

	public JTextField getTextUsername() {
		return textUsername;
	}

	public void setTextUsername(JTextField textUsername) {
		this.textUsername = textUsername;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public void setBtnLogin(JButton btnLogin) {
		this.btnLogin = btnLogin;
	}

	public JButton getBtnBatal() {
		return btnBatal;
	}

	public void setBtnBatal(JButton btnBatal) {
		this.btnBatal = btnBatal;
	}

	public JTextField getTextPassword() {
		return textPassword;
	}

	public void setTextPassword(JTextField textPassword) {
		this.textPassword = textPassword;
	}
}
