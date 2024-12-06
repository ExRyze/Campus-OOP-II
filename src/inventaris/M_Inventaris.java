package inventaris;

//import java.util.Calendar;
//import java.util.Date;

public class M_Inventaris {
	private String judul;
	private String pengarang;
	private String penerbit;
	private String tahun_terbit;
	private String genre;
	private String jumlah_halaman;
	private String isbn;
	private boolean check;
	
	public M_Inventaris() {
		judul = "";
		pengarang = "";
		penerbit = "";
		tahun_terbit = "";
		genre = "";
		jumlah_halaman = "";
		isbn = "";
		check = false;
	}
	
	
	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getPengarang() {
		return pengarang;
	}

	public void setPengarang(String pengarang) {
		this.pengarang = pengarang;
	}

	public String getPenerbit() {
		return penerbit;
	}

	public void setPenerbit(String penerbit) {
		this.penerbit = penerbit;
	}

	public String getTahun_terbit() {
		return tahun_terbit;
	}

	public void setTahun_terbit(String tahun_terbit) {
		this.tahun_terbit = tahun_terbit;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getJumlah_halaman() {
		return jumlah_halaman;
	}

	public void setJumlah_halaman(String jumlah_halaman) {
		this.jumlah_halaman = jumlah_halaman;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isCheck() {
		return check;
	}
	public void setCheck(boolean check) {
		this.check = check;
	}
}
