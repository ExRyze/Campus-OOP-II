package home;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.SystemColor;

public class V_Welcome extends JFrame {
	Dimension dm = getToolkit().getScreenSize();
	
	public V_Welcome() {
		super("System Inventaris");
		int w = (int)dm.getWidth();
		int h = (int)dm.getHeight();
		setBounds(0, 0, w+1, h+1);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		getContentPane().add(panel, BorderLayout.CENTER);
	}
}
