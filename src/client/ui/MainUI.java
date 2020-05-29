package client.ui;

import client.module.Connector;

import javax.swing.*;

public class MainUI extends JFrame {

    Connector connector;
    JFrame mainFrame;

    public MainUI(Connector connector) {
        this.connector = connector;
        this.mainFrame = this;

        mainFrame.setTitle("메인화면");
        mainFrame.setSize(800,800);



        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}
