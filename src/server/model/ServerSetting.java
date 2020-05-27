package server.model;

import java.net.Socket;

import server.module.ServerRepository;

/**
 * Thread의 생성자로 각 변수들을 하나씩 넘겨주게 되면 나중에 수정할 때
 * 작업이 많아지고 귀찮아진다. 그러므로 이곳과 Thread에서 속성과 Getter/Setter만 추가하면
 * 편하게 유지보수할 수 있다.
 */
public class ServerSetting {
	private Socket sock;
	private ServerRepository repo;

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public ServerRepository getRepo() {
		return repo;
	}

	public void setRepo(ServerRepository repo) {
		this.repo = repo;
	}
}
