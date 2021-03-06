package client.ui;

import client.module.Connector;
import client.module.GuiManagerMode;
import client.module.GuiProfile;
import server.model.request.CheckIdRequest;
import server.model.request.SignUpRequest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;

public class SignUpGUI extends JFrame implements GUI{
    @Override
    public String getGuiName() {
        return GUI.SIGNUP;
    }

    JFrame mainFrame;
    boolean isFirstAccess = true;
    boolean isCheckedId = false;
    String alreadyCheckedId = null;
    Connector connector;

    JTextField inputId;
    JTextField inputName;
    JPasswordField inputPassword;
    JPasswordField inputCP;
    JComboBox<String> selectYear;
    JComboBox<String> selectMonth;
    JComboBox<String> selectDay;
    ButtonGroup selectGender;
    JRadioButton selectMale;
    JRadioButton selectFemale;
    JTextField inputPhone;
    JLabel informPhone;

    public SignUpGUI(Connector connector){
        this.connector = connector;
        this.mainFrame = this;

        mainFrame = new JFrame();
        mainFrame.setTitle("회원가입");
        mainFrame.setSize(460,690);

        /***아이디 생성부***/
        this.inputId = new JTextField();
        JButton checkId = new JButton();
        inputId.setBounds(159,160,137,39);
        checkId.setBounds(314,158,80,41);
        checkId.setFocusPainted(false);
        checkId.setContentAreaFilled(false);
        inputId.setFont(new Font("돋움", Font.BOLD, 18));

        //중복체크버튼
        class CheckIdAction implements ActionListener{

            public void actionPerformed(ActionEvent e) {
                String checkBlank = inputId.getText();
                if(checkBlank.length()==0) {
                    JOptionPane.showMessageDialog(mainFrame, "아이디 입력란이 빈칸입니다.", "아이디 입력바람",JOptionPane.ERROR_MESSAGE);
                    isCheckedId = false;
                    return;
                }else if(!(checkBlank.length()>=4)) {
                    JOptionPane.showMessageDialog(mainFrame, "아이디는 4자리 이상이어야 합니다", "아이디 입력바람",JOptionPane.ERROR_MESSAGE);
                    isCheckedId = false;
                    return;
                }

                connector.request(
                    new CheckIdRequest().setId(inputId.getText())
                );

            }
        }//CheckIdAction.class

        checkId.addActionListener(new CheckIdAction());


        mainFrame.add(inputId);
        mainFrame.add(checkId);

        /***이름 생성부***/
        this.inputName = new JTextField();
        inputName.setBounds(160,209,102,40);
        inputName.setFont(new Font("돋움", Font.BOLD, 18));
        mainFrame.add(inputName);

        /***비밀번호 생성부***/
        this.inputPassword = new JPasswordField();
        inputPassword.setBounds(160,256,171,40);
        inputPassword.setFont(new Font("돋움", Font.BOLD, 18));
        mainFrame.add(inputPassword);

        this.inputCP = new JPasswordField();
        inputCP.setBounds(160,304,170,40);
        inputCP.setFont(new Font("돋움", Font.BOLD, 18));
        mainFrame.add(inputCP);

        /***생년월일 생성부***/
        //연별 데이터 생성
        int yearNum = 1980;
        String[] years = new String[50];
        for(int i = 0; i < 50; i++) {
            years[i] = Integer.toString(yearNum);
            yearNum++;
        }
        //월별 데이터 생성
        String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
        //일별 데이터 생성
        int dayNum = 1;
        String[] days = new String[31];
        for(int i = 0; i < 31; i++) {
            String strDay = Integer.toString(dayNum);
            if(strDay.length() == 1) {
                strDay = "0"+strDay;
            }
            days[i] = strDay;
            dayNum++;
        }

        this.selectYear = new JComboBox<String>(years);
        this.selectMonth = new JComboBox<String>(months);
        this.selectDay = new JComboBox<String>(days);
        selectYear.setBounds(160,352,68,41);
        selectYear.setFont(new Font("돋움", Font.BOLD, 15));
        selectMonth.setBounds(235,352,42,41);
        selectMonth.setFont(new Font("돋움", Font.BOLD, 12));
        selectDay.setBounds(283,352,41,41);
        selectDay.setFont(new Font("돋움", Font.BOLD, 12));
        mainFrame.add(selectYear);
        mainFrame.add(selectMonth);
        mainFrame.add(selectDay);

        /***성별 생성부***/
        this.selectGender = new ButtonGroup();
        this.selectMale = new JRadioButton("남자");
        this.selectFemale = new JRadioButton("여자");
        selectMale.setBounds(160,400,70,50);
        selectFemale.setBounds(250,400,70,50);
        selectMale.setFont(new Font("돋움", Font.PLAIN, 13));
        selectFemale.setFont(new Font("돋움", Font.PLAIN, 13));
        selectGender.add(selectMale);
        selectGender.add(selectFemale);
        mainFrame.add(selectMale);
        mainFrame.add(selectFemale);

        /***핸드폰 생성부***/
        this.inputPhone = new JTextField();
        this.informPhone = new JLabel();
        inputPhone.setText("ex)01005240307");
        inputPhone.setBounds(160,453,217,41);
        inputPhone.setFont(new Font("돋움", Font.BOLD, 13));

        inputPhone.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                if(isFirstAccess) {
                    inputPhone.setText("");
                    isFirstAccess=false;
                }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

        });

        mainFrame.add(inputPhone);
        mainFrame.add(informPhone);

        /***회원가입 버튼***/
        JButton ok = new JButton();

        ok.setBounds(51, 548, 348, 60);
        ok.setFocusPainted(false);
        ok.setContentAreaFilled(false);

        class OkAction implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = inputId.getText();

                if(id.length()==0) {
                    JOptionPane.showMessageDialog(mainFrame, "아이디를 입력하시지 않았습니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(!isCheckedId) {
                    JOptionPane.showMessageDialog(mainFrame, "아이디 중복체크를 하시지 않았습니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(!alreadyCheckedId.equals(id)) {
                    JOptionPane.showMessageDialog(mainFrame, "아이디 입력란을 수정하셨습니다. 다시 입력하고 중복체크를 해주세요", "실패", JOptionPane.ERROR_MESSAGE);
                    isCheckedId = false;
                    return;
                }

                String name = inputName.getText();
                if(name.length()==0) {
                    JOptionPane.showMessageDialog(mainFrame, "이름 입력란이 비어있습니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(!(name.length()>=2)) {
                    JOptionPane.showMessageDialog(mainFrame, "이름은 2글자 이상이어야 합니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                char[] cPwd1 = inputPassword.getPassword();
                String password = new String(cPwd1);
                char[] cPwd2 = inputCP.getPassword();
                String passwordCheck = new String(cPwd2);

                if(password.length()==0 || passwordCheck.length()==0) {
                    JOptionPane.showMessageDialog(mainFrame, "비밀번호 입력 및 비밀번호 체크 입력란이 비었습니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(!password.equals(passwordCheck)){
                    JOptionPane.showMessageDialog(mainFrame, "비밀번호가 일치하지 않습니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(!(password.length() >= 4)) {
                    JOptionPane.showMessageDialog(mainFrame, "비밀번호는 4자리 이상이어야 합니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String year= (String)selectYear.getSelectedItem();
                String month= (String)selectMonth.getSelectedItem();
                String day= (String)selectDay.getSelectedItem();
                String birth = year+month+day;

                String gender = null;

                if(selectMale.isSelected()) {
                    gender = "남자";
                }else if(selectFemale.isSelected()) {
                    gender = "여자";
                }

                if(selectMale.isSelected() == false && selectFemale.isSelected() == false) {
                    JOptionPane.showMessageDialog(mainFrame, "성별을 선택하지 않았습니다.", "성별입력안됨", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String phone = inputPhone.getText();
                if(isFirstAccess) {
                    JOptionPane.showMessageDialog(mainFrame, "핸드폰을 입력하시지 않았습니다.", "입력 한적 없음", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(phone.length()==0) {
                    JOptionPane.showMessageDialog(mainFrame, "핸드폰 입력란이 비었습니다.", "실패", JOptionPane.ERROR_MESSAGE);
                    return;
                }else if(phone.length()!=11 || !(phone.startsWith("01"))) {
                    JOptionPane.showMessageDialog(mainFrame, "핸드폰 자릿수가 맞지 않거나 서식이 틀렸습니다.", "서식 오류", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                connector.request(
                        new SignUpRequest()
                        .setId(id)
                        .setUsername(name)
                        .setPassword(password)
                        .setBirth(birth)
                        .setGender(gender)
                        .setPhoneNumber(phone)
                );

            }
        }
        ok.addActionListener(new OkAction());

        mainFrame.add(ok);

        Image img = null;
        try {
            img = ImageIO.read(new File("./img/SignUp.png"));
        }catch(Exception e) {
            System.out.println(e);
        }

        @SuppressWarnings("serial")
        class MyPanel extends JPanel{

            Image img =null;
            MyPanel(Image img){
                this.img = img;
            }

            public void paint(Graphics g) {
                g.drawImage(img, 0, 0, null);
            }
        }

        MyPanel panel = new MyPanel(img);
        panel.setBounds(0,0,450,690);

        mainFrame.add(panel);

        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }

    public void checkIdResult(HashMap<String, Object> data){
        boolean isExist = (boolean)data.get("result");

        if(isExist) {
            JOptionPane.showMessageDialog(mainFrame, "이미 사용중인 아이디입니다.", "아이디 중복",JOptionPane.ERROR_MESSAGE);
            isCheckedId = false;
            alreadyCheckedId=inputId.getText();
        }else {
            JOptionPane.showMessageDialog(mainFrame, "멋진 아이디네요~! 사용가능합니다.", "성공",JOptionPane.INFORMATION_MESSAGE);
            isCheckedId = true;
            alreadyCheckedId=inputId.getText();
        }
    }

    public void signUpResult(HashMap<String, Object> data){
        String result = (String)data.get("result");

        //모든 서식 준비 완료
        JOptionPane.showMessageDialog(mainFrame, result , "결과", JOptionPane.INFORMATION_MESSAGE);
        GuiProfile profile = new GuiProfile();
        profile.setMode(GuiManagerMode.CLOSE_THIS_WINDOW).setTarget(mainFrame);
        connector.guiManager(profile);
    }

}
