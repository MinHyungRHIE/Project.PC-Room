package practice;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;


public class ServerTest {
    public static void main(String[] args) throws Exception {
        java.net.ServerSocket server = new java.net.ServerSocket();
        InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 11111);
        server.bind(ipep);
        System.out.println("서버켜짐");

        Socket sock = server.accept();
        System.out.println("클라이언트와 연결됨");
        InputStream input = sock.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(input);
        System.out.println(ois.readObject());
        System.out.println("종료되었다.");
    }
}
