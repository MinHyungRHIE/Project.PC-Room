package client.module;

import server.model.response.ResponseModel;
import server.config.ServerConfiguration;
import server.model.request.RequestModel;

import javax.swing.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Connector {

    private Socket sock;
    private InetSocketAddress ipep;
    private ObjectOutputStream requestData;
    private ObjectInputStream responseData;
    private HashMap<String, JFrame> currentGui;

    public void connectToServer() {
        try {
            this.sock = new Socket();
            this.ipep = new InetSocketAddress(ServerConfiguration.IP, ServerConfiguration.PORT);
            this.sock.connect(ipep);

            this.requestData = new ObjectOutputStream(sock.getOutputStream());
            this.responseData = new ObjectInputStream(sock.getInputStream());

            if(sock.isConnected()) {
                System.out.println("서버와 연결 성공 [Client :"+sock.getInetAddress()+"--->"+sock.getLocalAddress()+": Server]");
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public boolean isConnected() {
        return this.sock.isConnected();
    }

    public void guiManager(JFrame frame, int option){
        if(!isConnected()) {
            JOptionPane.showMessageDialog(null, "서버와 연결이 안되어 있습니다.", "네트워크 오류",JOptionPane.WARNING_MESSAGE);
            destoryAll();
            return;
        }


    }

    public void destoryAll(){

    }

    public HashMap<String,Object> communicateWithServer(RequestModel sendToServer) {
        HashMap<String,Object> result = null;
        try {
            //서버에게 요청을 보낸다.
            requestData.writeObject(sendToServer);

            //서버에게 요청을 받는다.
            ResponseModel recvFromServer = (ResponseModel)responseData.readObject();

            //받은 요청을 처리한다.
            result = recvFromServer.data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
