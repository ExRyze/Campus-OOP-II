package packages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class KoneksiJDBC {
	private Connection koneksi;
	private String host = "localhost";
	private String port = "3306";
	private String user = "root";
	private String pass = "";
	private String db = "pbo_inventaris";
	
	public boolean isKonek() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			koneksi = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?enabledTLSProtocols=TLSv1.2&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",user,pass);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public Connection getKoneksi() {
		return koneksi;
	}

	public void setKoneksi(Connection koneksi) {
		this.koneksi = koneksi;
	}
	
	public int insert_update_delete(String query) {
		try {
			return getKoneksi().createStatement().executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}
	
	public ResultSet getQuery(String query) {
		try {
			return getKoneksi().createStatement().executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
