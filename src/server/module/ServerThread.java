package server.module;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import server.model.ServerSetting;

public class ServerThread extends Thread {
	Socket sock;
	PrintWriter send;
	BufferedReader recv;
	ServerController controller;

	public ServerThread(ServerSetting setting) {
		this.sock = setting.getSock();
		initSendAndRecv();
		this.controller = new ServerController(setting.getRepo());
	}

	void initSendAndRecv() {
		try {
			this.send = new PrintWriter(new OutputStreamWriter(this.sock.getOutputStream()));
			this.recv = new BufferedReader(new InputStreamReader(this.sock.getInputStream()));
		} catch (Exception e) {
			System.out.println("ServerThread:InitSendAndRecv() 에러 : " + e);
		}
	}


}
