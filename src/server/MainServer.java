package server;

import com.sun.security.ntlm.Server;
import server.config.ServerConfiguration;
import server.model.ServerState;
import server.module.ServerRepository;
import server.module.ServerThread;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MainServer {

    private static void startServer(){
        try {
            // 서버 객체 생성
            ServerSocket server = new ServerSocket();
            InetSocketAddress ipep = new InetSocketAddress(ServerConfiguration.IP, ServerConfiguration.PORT);
            server.bind(ipep);

            // Database 접근 객체 (모든 Thread들이 공유)
            ServerRepository repo = new ServerRepository();
            repo.checkDatabaseState();
            
            // 서버에 연결된 client들의 정보를 얻는 객체 (모든 Thread들이 공유
            LinkedList<ServerState> clientList = new LinkedList<ServerState>();

            // 서버 리스닝 시작
            System.out.println("=====서버가 시작되었음=====");
            while(true){
                Socket sock = server.accept();
                ServerState state = new ServerState();
                state.setSocket(sock);
                state.setRepository(repo);
                ServerThread getClient = new ServerThread(state, clientList);
                getClient.start();
                System.out.println(":::클라이언트가 접속함["+sock.getInetAddress()+"]");
            }
        } catch (Exception e) {
            System.out.println("MainServer-startServer() Error:"+e);
        }
    }

    public static void main(String[] args) {
        startServer();
    }
}
