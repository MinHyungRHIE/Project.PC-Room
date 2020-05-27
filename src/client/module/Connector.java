package client.module;

import client.model.ResponseModel;
import server.config.ServerConfiguration;
import server.model.communication.SignUpRequest;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Connector {

    private Socket sock;
    private InetSocketAddress ipep;
    private PrintWriter sendString;
    private BufferedReader recvString;
    private ObjectOutputStream requestData;
    private ObjectInputStream responseData;


    public void connectToServer() {

        try {
            this.sock = new Socket();
            this.ipep = new InetSocketAddress(ServerConfiguration.IP, ServerConfiguration.PORT);
            this.sock.connect(ipep);

            this.sendString = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            this.recvString = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            this.requestData = new ObjectOutputStream(sock.getOutputStream());
            this.responseData = new ObjectInputStream(sock.getInputStream());

            if(sock.isConnected()) {
                System.out.println("서버와 연결 성공 [Client :"+sock.getInetAddress()+"--->"+sock.getLocalAddress()+": Server]");
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public String toSignUp(SignUpRequest sendToServer) {
        String result = "";
        try {

            //서버에게 보낸다.
            requestData.writeObject(sendToServer);
            ResponseModel recvFromServer = (ResponseModel)responseData.readObject();
            result = (String)recvFromServer.data.get("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
