package client;

import client.module.Connector;
import client.ui.SignUpGUI;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Connector connector = new Connector();
        connector.connectToServer();
        new SignUpGUI(connector);
    }
}
