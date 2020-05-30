package practice;

import javax.swing.*;
import java.awt.*;

public class JEditorPaneExample {
    JFrame myFrame = null;

    public static void main(String[] a) {
        (new JEditorPaneExample()).test();
    }

    private void test() {
        myFrame = new JFrame("JEditorPane Test");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(400, 200);
        JEditorPane myPane = new JEditorPane();
        myPane.setContentType("text/plain");
        myPane.setText("Sleeping is necessary for a healthy body."
                + " But sleeping in unnecessary times may spoil our health, wealth and studies."
                + " Doctors advise that the sleeping at improper timings may lead for obesity during the students days.");
        myPane.setFont(new Font("Serif",Font.BOLD,40));
        JScrollPane scroll = new JScrollPane(myPane);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        myFrame.add(scroll);
//        myFrame.setContentPane(myPane);
        myFrame.setVisible(true);
    }
}