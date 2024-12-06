package home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Timer;

import inventaris.C_DaftarInventaris;

public class C_Home extends V_Home implements ActionListener {
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
	private SimpleDateFormat sdt = new SimpleDateFormat("hh : mm : ss");
	private Timer tmTimer;
	
	public C_Home() {
		getLblTanggal().setText("Tanggal : "+sdf.format(Calendar.getInstance().getTime()));
		tmTimer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLblJam().setText(" Jam : "+sdt.format(Calendar.getInstance().getTime()));
			}
		});
		tmTimer.start();
		getLblStatus().setText("Pilih menu yang diinginkan");
		
		getMntmExit().addActionListener(this);		
		getMntmDataBuku().addActionListener(this);
		getMntmTambahBuku().addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getMntmExit()) {
			System.exit(0);
		}
		if (e.getSource() == getMntmDataBuku()) {
			C_DaftarInventaris cds = new C_DaftarInventaris();
			getContentPane().add(cds);
			cds.setVisible(true);
			try {
				cds.setMaximum(true);;
			} catch (Exception ex) {
				System.out.println(ex);
				return;
			}
		}
	}
}
