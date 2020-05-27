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

public class MainServer {

    private static void startServer(){
        try {
            // 서버 객체 생성
            ServerSocket server = new ServerSocket();
            InetSocketAddress ipep = new InetSocketAddress(ServerConfiguration.IP, ServerConfiguration.PORT);
            server.bind(ipep);

            // Database 접근 객체 (모든 Thread들이 공유)
            ServerRepository repo = new ServerRepository();
            
            // 서버 리스닝 시작
            System.out.println("=====서버가 시작되었음=====");
            while(true){
                Socket sock = server.accept();
                ServerState state = new ServerState();
                state.setSocket(sock);
                state.setRepository(repo);
                ServerThread getClient = new ServerThread(state);
                getClient.start();
            }
        } catch (IOException e) {
            System.out.println("MainServer-startServer() Error:"+e);
        }

    }

    public static void main(String[] args) {
        try{
            startServer();
        }catch(Exception e){
            System.out.println("MainServer-main() Error:"+e);
        }

    }
}
