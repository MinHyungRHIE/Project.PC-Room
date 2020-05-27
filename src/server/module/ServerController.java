package server.module;

public class ServerController {

    ServerService service;

    public ServerController(ServerRepository repo) {
        this.service = new ServerService(repo);
    }
}
