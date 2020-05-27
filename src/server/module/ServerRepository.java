package server.module;

import java.util.HashSet;

import server.model.User;

public class ServerRepository {
	private HashSet<User> loginedUser = new HashSet<User>();

	public HashSet<User> getLoginedUser() {
		return loginedUser;
	}

	public void setLoginedUser(HashSet<User> loginedUser) {
		this.loginedUser = loginedUser;
	}

}
