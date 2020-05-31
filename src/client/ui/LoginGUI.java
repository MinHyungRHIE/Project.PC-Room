package client.ui;

import client.module.Connector;
import client.module.GuiManagerMode;
import client.module.GuiProfile;
import server.model.request.LoginRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

public class LoginGUI extends JFrame implements GUI {
    @Override
    public String getGuiName() {
        return GUI.LOGIN;
    }
    
    Connector connector;
    JFrame mainFrame;

    public LoginGUI(Connector connector){
        this.connector = connector;
        this.mainFrame = this;

        mainFrame.setTitle("로그인");
        mainFrame.setSize(400,650);

        /***아이디 입력부***/
        JTextField inputId = new JTextField();
        inputId.setBounds(105,265,225,40);
        inputId.setFont(new Font("돋음",Font.BOLD,24));
        inputId.setForeground(Color.cyan);
        inputId.setOpaque(false);
        inputId.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        /***비밀번호 입력부***/
        JPasswordField inputPwd = new JPasswordField();
        inputPwd.setBounds(105,340,225,40);
        inputPwd.setFont(new Font("돋음",Font.BOLD,24));
        inputPwd.setForeground(Color.cyan);
        inputPwd.setOpaque(false);
        inputPwd.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        /***로그인 버튼***/
        JButton loginBtn = new JButton();
        loginBtn.setBounds(42,453,320,50);
        loginBtn.setText("로그인");
        loginBtn.setFont(new Font("돋음",Font.BOLD,24));
        loginBtn.setForeground(Color.cyan);
        loginBtn.setFocusPainted(false);
        loginBtn.setContentAreaFilled(false);


        class LoginAction implements ActionListener{

            public void actionPerformed(ActionEvent ae) {

                String id = inputId.getText();
                char[] getPwd = inputPwd.getPassword();
                String pwd = new String(getPwd);

                if(id.equals("")||pwd.equals("")) {
                    JOptionPane.showMessageDialog(mainFrame, "빈칸이 있습니다.", "실패",JOptionPane.WARNING_MESSAGE);
                }else {

                    connector.request(
                            new LoginRequest()
                            .setId(id)
                            .setPassword(pwd)
                    );
                }
            }
        }//LoginAction.class

        loginBtn.addActionListener(new LoginAction());

        /***회원가입 버튼***/
        JButton signUpBtn = new JButton();
        signUpBtn.setBounds(42, 510, 320, 50);
        signUpBtn.setText("회원가입");
        signUpBtn.setFont(new Font("돋음",Font.BOLD,24));
        signUpBtn.setForeground(Color.cyan);
        signUpBtn.setFocusPainted(false);
        signUpBtn.setContentAreaFilled(false);

        class SignUpAction implements ActionListener{

            public void actionPerformed(ActionEvent ae) {
                GuiProfile profile = new GuiProfile();
                profile.setGuiName("SignUpGUI").setMode(GuiManagerMode.NEW_WINDOW_AND_STAY_ALL_OLD_WINDOW);
                connector.guiManager(profile);
            }

        }//SignUpAction.class


        signUpBtn.addActionListener(new SignUpAction());

        mainFrame.add(inputId);
        mainFrame.add(inputPwd);
        mainFrame.add(loginBtn);
        mainFrame.add(signUpBtn);

        /***배경 이미지 추가***/
        //이미지 재료 불러와서 세팅 준비
        Image img = null;
        try {
            img = ImageIO.read(new File("./img/Login.png"));
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
        panel.setBounds(0,0,400,650);

        mainFrame.add(panel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    public void loginResult(HashMap<String, Object> data){
        System.out.println("loginResult()");
        String result = (String)data.get("result");

        if(result.equals("success")) {
            JOptionPane.showMessageDialog(mainFrame, "로그인 성공", "성공",JOptionPane.INFORMATION_MESSAGE);
            GuiProfile profile = new GuiProfile();
            profile.setGuiName(GUI.MAIN).setMode(GuiManagerMode.NEW_WINDOW_AND_CLOSE_ALL_OLD_WINDOW);
            connector.guiManager(profile);
        }else if(result.equals("fail")){
            JOptionPane.showMessageDialog(mainFrame, "로그인 실패", "실패",JOptionPane.ERROR_MESSAGE);
        }else if(result.equals("blocked")) {
            JOptionPane.showMessageDialog(mainFrame, "이미 접속중인 아이디입니다.", "누구냐 넌...",JOptionPane.ERROR_MESSAGE);
        }

    }
}
