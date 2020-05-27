package practice;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientTest {
    public static void main(String[] args) throws Exception{
        Socket sock = new Socket();
        InetSocketAddress ipep = new InetSocketAddress("127.0.0.1",11111);
        sock.connect(ipep);
        ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
        oos.writeObject(new SampleDataSet("test","1234"));
    }
}
