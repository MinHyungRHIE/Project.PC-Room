package client.ui;

import client.module.Connector;
import server.model.request.OpenChattingRequest;
import server.model.request.ShowMyUsernameRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.HashMap;

public class MainGUI extends JFrame implements GUI{
    @Override
    public String getGuiName() {
        return GUI.MAIN;
    }
    Connector connector;
    JFrame mainFrame;

    /** Setting **/
    String id;
    JLabel username;
    JEditorPane chatDisplay;
    JScrollPane scroll;
    JTextField typingField;
    StringBuffer chattingText = new StringBuffer();

    /***sample***/
//        JTextField inputId = new JTextField();
//        inputId.setBounds(105,265,225,40);
//        inputId.setFont(new Font("돋음",Font.BOLD,24));
//        inputId.setForeground(Color.cyan);
//        inputId.setOpaque(false);
//        inputId.setBorder(javax.swing.BorderFactory.createEmptyBorder());
//
//
//        JButton loginBtn = new JButton();
//        loginBtn.setBounds(42,453,320,50);
//        loginBtn.setText("로그인");
//        loginBtn.setFont(new Font("돋음",Font.BOLD,24));
//        loginBtn.setForeground(Color.cyan);
//        loginBtn.setFocusPainted(false);
//        loginBtn.setContentAreaFilled(false);


    public MainGUI(Connector connector) {
        this.connector = connector;
        this.mainFrame = this;
        connector.request(new ShowMyUsernameRequest());
        connector.request(
                new OpenChattingRequest()
                .setFirstAccess(true)
        );

        mainFrame.setTitle("메인화면");
        mainFrame.setSize(720,750);

        /*** 사용자 프로필 보기 ***/
        JButton profileSettingBtn = new JButton();
        profileSettingBtn.setBounds(17,28,100,85);
        profileSettingBtn.setFocusPainted(false);
        profileSettingBtn.setContentAreaFilled(false);
//        profileSettingBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        mainFrame.add(profileSettingBtn);

        class profileSettingBtnAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("profileSettingBtn");
            }
        }

        profileSettingBtn.addActionListener(new profileSettingBtnAction());

        /*** 사용자 아이디 정보 보여주기 ***/
        this.username = new JLabel();
        this.username.setBounds(210,35,210,67);
        this.username.setFont(new Font("HY견고딕",Font.BOLD,24));
        this.username.setForeground(Color.BLACK);
        mainFrame.add(this.username);

        /*** 오픈 채팅창 보이기 ***/
        JPanel chatDisplayFrame = new JPanel(); // 서브 프레임 생성
        chatDisplayFrame.setBounds(23,123,475,505);

        this.chatDisplay = new JEditorPane(); // 서브 프레임에 넣을 텍스트 컴포넌트 생성
        this.chatDisplay.setContentType("text/plain");
        this.chatDisplay.setFont(new Font("Serif",Font.BOLD,17));
        this.chatDisplay.setEditable(false);


        this.scroll = new JScrollPane(this.chatDisplay); // 텍스트 컴포넌트에 스크롤 기능 추가
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        chatDisplayFrame.add(scroll);
        chatDisplayFrame.setLayout(new BoxLayout(chatDisplayFrame, BoxLayout.X_AXIS));
        mainFrame.add(chatDisplayFrame);

        this.typingField = new JTextField(); // 채팅 창 생성
        this.typingField.setBounds(27,641,471,44);
        
        class TypingFieldAction implements KeyListener {
            @Override
            public void keyTyped(KeyEvent e) {
                
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER && !typingField.getText().equals("")){
                    connector.request(
                            new OpenChattingRequest()
                            .setMsg(typingField.getText())
                    );
                    typingField.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        }

        this.typingField.addKeyListener(new TypingFieldAction());
        mainFrame.add(typingField);


        /*** 버튼 기능 ***/
        JButton b1 = new JButton("버튼1");
        b1.setBounds(518,26,185,95);

        class B1Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b1");
            }
        }
        b1.addActionListener(new B1Action());
        mainFrame.add(b1);

        JButton b2 = new JButton("버튼2");
        b2.setBounds(518,149,185,95);

        class B2Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b2");
            }
        }
        b2.addActionListener(new B2Action());
        mainFrame.add(b2);

        JButton b3 = new JButton("버튼3");
        b3.setBounds(518,273,185,95);

        class B3Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b3");
            }
        }
        b3.addActionListener(new B3Action());
        mainFrame.add(b3);

        JButton b4 = new JButton("버튼4");
        b4.setBounds(518,397,185,95);

        class B4Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b4");
            }
        }
        b4.addActionListener(new B4Action());
        mainFrame.add(b4);

        JButton b5 = new JButton("버튼5");
        b5.setBounds(518,520,185,95);

        class B5Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b5");
            }
        }
        b5.addActionListener(new B5Action());
        mainFrame.add(b5);

        JButton b6 = new JButton("버튼6");
        b6.setBounds(518,645,180,54);

        class B6Action implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("b6");
            }
        }
        b6.addActionListener(new B6Action());
        mainFrame.add(b6);


        /***배경 이미지 추가***/
        //이미지 재료 불러와서 세팅 준비
        Image img = null;
        try {
            img = ImageIO.read(new File("./img/MainClient.png"));
        }catch(Exception e) {
            System.out.println(e);
        }

        //불러온 이미지 재료 세팅 기능
        class MyPanel extends JPanel{
            private static final long serialVersionUID = 2845825549862323736L;
            Image img =null;
            MyPanel(Image img){
                this.img = img;
            }

            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        }

        //준비된 세팅 기능을 이용해 이미지 세팅 완료
        MyPanel panel = new MyPanel(img);
        panel.setBounds(0,0,720,780);
        mainFrame.add(panel);


//        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);

    }

    public void showMyUsernameResult(HashMap<String,Object> data){
        String username = (String)data.get("username");
        String id = (String)data.get("id");
        this.username.setText(username);
        this.id = id;
    }

    public void openChattingResult(HashMap<String, Object> data){
        String senderId = (String)data.get("senderId");
        String senderUsername = (String)data.get("senderUsername");
        String msg = (String)data.get("msg");

        // GUI에 해당 유저 정보를 서버로부터 얻어 왔는지 안 왔는지 확인하고, 없으면 다시 오픈채팅방 접속을 요청한다.
        if(this.id == null && this.username.getText().equals("")){
            connector.request(
                    new OpenChattingRequest()
                            .setFirstAccess(true)
            );
            return;
        }

        if(this.id.equals(senderId) && this.username.getText().equals(senderUsername)){ // 내 자신이 보낸 메시지를 echo받은 경우
            if(data.get("welcome")!=null){
                this.chatDisplay.setText(chattingText.append(((String)data.get("welcome"))+"\n").toString());
            }else{
                this.chatDisplay.setText(chattingText.append("나: "+msg+"\n").toString());
            }
        }else{ // 다른 사람이 보낸 메시지를 echo받은 경우
            if(data.get("inform")!=null){
                this.chatDisplay.setText(chattingText.append(((String)data.get("inform"))+"\n").toString());
            }else{
                this.chatDisplay.setText(chattingText.append("["+senderUsername+"]("+senderId+"): "+msg+"\n").toString());
            }
        }
        scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
    }

}
