package server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server.config.ServerConfiguration;
import server.model.ServerSetting;
import server.module.ServerRepository;
import server.module.ServerThread;

public class MainServer {

	public static void startMainServer() throws Exception{
		/* 서버 객체 만들기 */
		ServerSocket server = new ServerSocket();
		InetSocketAddress ipep = new InetSocketAddress(ServerConfiguration.IP, ServerConfiguration.PORT);
		server.bind(ipep);

		/* 모든 서버 Thread가 공유할 Repository 객체 생성 */
		ServerRepository repo = new ServerRepository();

		/* 서버 리스닝 시작*/
		System.out.println("=====서버 시작됨=====");
		while(true) {
			//리스닝
			Socket sock = server.accept();

			//생성될 Thread에 넘겨줄 정보들 Setting
			ServerSetting setting = new ServerSetting();
			setting.setRepo(repo);
			setting.setSock(sock);

			//Thread 생성
			ServerThread getUser = new ServerThread(setting);
			getUser.start();
		}
	}

	public static void main(String[] args) {
		try {
			startMainServer();
		}catch(Exception e) {
			System.out.println("MainServer 에러 : " + e);
		}
	}
}
