package server.model;
import java.io.*;

public class User {
	private String id;
	private String password;
	private BufferedReader br;
	private PrintWriter pw;

	public User(String id, String password, BufferedReader br, PrintWriter pw){
		this.id = id;
		this.password = password;
		this.br = br;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BufferedReader getBr() {
		return br;
	}

	public void setBr(BufferedReader br) {
		this.br = br;
	}

	public PrintWriter getPw() {
		return pw;
	}

	public void setPw(PrintWriter pw) {
		this.pw = pw;
	}


}
