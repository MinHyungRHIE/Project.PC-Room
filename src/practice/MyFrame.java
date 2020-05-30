package practice;
import java.awt.BorderLayout;

import java.awt.FlowLayout;

import java.awt.Font;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

import javax.swing.JTextField;



class MyFrame extends JFrame implements ActionListener {

    private JPanel displayPanel;

    private JPanel inputPanel;


    private JTextArea display;

    private JTextField input;


    public MyFrame() {

        this.setTitle("My GUI");

        this.setSize(500, 400);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        /*

         * panel 생성하고, 필요한 컴포넌트를 만들기

         */


        displayPanel = new JPanel();

        displayPanel.setLayout(new FlowLayout());

        display = new JTextArea(11, 30);

        Font displayFont = new Font("Serif", Font.BOLD, 20);

        display.setFont(displayFont);

        display.setEditable(false);


// textarea를 스크롤이 되도록 변경

        JScrollPane scroll = new JScrollPane(display);

        displayPanel.add(scroll);


        inputPanel = new JPanel();

        inputPanel.setLayout(new FlowLayout());

        input = new JTextField(30);

        Font inputFont = new Font("Serif", Font.BOLD, 20);

        input.setFont(inputFont);

        input.addActionListener(this);


        inputPanel.add(input);



        /*

         * Frame에 두개의 panel을 붙이기

         */

        this.setLayout(new BorderLayout());

        this.add(displayPanel, BorderLayout.CENTER);

        this.add(inputPanel, BorderLayout.SOUTH);


        this.setVisible(true);

    }


    @Override

    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == input) {

            display.append(input.getText() + "\n");

            input.selectAll();

        }

    }

    public static void main(String[] args) {



        MyFrame frame = new MyFrame();

    }// main
}
