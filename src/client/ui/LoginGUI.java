package client.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;

public class LoginGUI {
    private JFrame frame;


    public LoginGUI(){

//        if(!clientHandler.isConnected()) {
//            JOptionPane.showMessageDialog(null, "서버와 연결이 안되어 있습니다.", "네트워크 오류",JOptionPane.WARNING_MESSAGE);
//            return;
//        }

        frame = new JFrame();
        frame.setTitle("로그인");
        frame.setSize(400,650);

        /***아이디 입력부***/
//		JLabel id = new JLabel();
//		JTextField inputId = new JTextField();
//		id.setBounds(48,176,61,57);
//		id.setText("ID : ");
//		id.setFont(new Font("돋음",Font.BOLD,24));
//		inputId.setBounds(132,176,222,59);
//		inputId.setFont(new Font("돋음",Font.BOLD,24));

        JTextField inputId = new JTextField();
        inputId.setBounds(105,265,225,40);
        inputId.setFont(new Font("돋음",Font.BOLD,24));
        inputId.setForeground(Color.cyan);
        inputId.setOpaque(false);
        inputId.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        /***비밀번호 입력부***/
//		JLabel pwd = new JLabel();
//		JPasswordField inputPwd = new JPasswordField();
//		pwd.setBounds(48,280,61,57);
//		pwd.setText("PW : ");
//		pwd.setFont(new Font("돋음",Font.BOLD,24));
//		inputPwd.setBounds(132,279,222,59);
//		inputId.setFont(new Font("돋음",Font.BOLD,24));

        JPasswordField inputPwd = new JPasswordField();
        inputPwd.setBounds(105,340,225,40);
        inputPwd.setFont(new Font("돋음",Font.BOLD,24));
        inputPwd.setForeground(Color.cyan);
        inputPwd.setOpaque(false);
        inputPwd.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        /***로그인 버튼***/
//		JButton loginBtn = new JButton();
//		loginBtn.setBounds(49,398,136,126);
//		loginBtn.setText("로그인");
//		loginBtn.setFont(new Font("돋음",Font.BOLD,24));

        JButton loginBtn = new JButton();
        loginBtn.setBounds(42,453,320,50);
        loginBtn.setText("로그인");
        loginBtn.setFont(new Font("돋음",Font.BOLD,24));
        loginBtn.setForeground(Color.cyan);
//		loginBtn.setBorderPainted(false);
        loginBtn.setFocusPainted(false);
        loginBtn.setContentAreaFilled(false);


        class LoginAction implements ActionListener{


            LoginAction(){
            }

            public void actionPerformed(ActionEvent ae) {

                String id = inputId.getText();
                char[] getPwd = inputPwd.getPassword();
                String pwd = new String(getPwd);

                if(id.equals("")||pwd.equals("")) {
                    JOptionPane.showMessageDialog(frame, "빈칸이 있습니다.", "실패",JOptionPane.WARNING_MESSAGE);
                }else {


//                    if(result.equals("true")) {
//                        JOptionPane.showMessageDialog(frame, "로그인 성공", "성공",JOptionPane.INFORMATION_MESSAGE);
////						clientHandler.createMainGUI(clientHandler, id, "test");
//
//                        frame.dispose();
//                    }else if(result.equals("false")){
//                        JOptionPane.showMessageDialog(frame, "로그인 실패", "실패",JOptionPane.ERROR_MESSAGE);
//                    }else if(result.equals("blocked")) {
//                        JOptionPane.showMessageDialog(frame, "이미 접속중인 아이디입니다.", "누구냐 넌...",JOptionPane.ERROR_MESSAGE);
//                    }
                }
            }
        }//LoginAction.class

//        loginBtn.addActionListener(new LoginAction(clientHandler));

        /***회원가입 버튼***/
//		JButton signUpBtn = new JButton();
//		signUpBtn.setBounds(218, 398, 136, 126);
//		signUpBtn.setText("회원가입");
//		signUpBtn.setFont(new Font("돋음",Font.BOLD,24));

        JButton signUpBtn = new JButton();
        signUpBtn.setBounds(42, 510, 320, 50);
        signUpBtn.setText("회원가입");
        signUpBtn.setFont(new Font("돋음",Font.BOLD,24));
        signUpBtn.setForeground(Color.cyan);
//		signUpBtn.setBorderPainted(false);
        signUpBtn.setFocusPainted(false);
        signUpBtn.setContentAreaFilled(false);

        class SignUpAction implements ActionListener{

            public void actionPerformed(ActionEvent ae) {
//				new ClientSignUpGUI(clientHandler);
//                clientHandler.createSignUpGUI(clientHandler);
            }
        }//SignUpAction.class


//        signUpBtn.addActionListener(new SignUpAction(clientHandler));

//		frame.add(id);
        frame.add(inputId);
//		frame.add(pwd);
        frame.add(inputPwd);
        frame.add(loginBtn);
        frame.add(signUpBtn);

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

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
}
