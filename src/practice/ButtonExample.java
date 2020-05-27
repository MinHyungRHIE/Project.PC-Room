package practice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ButtonExample {
	public static void main(String[] args) {
		JFrame f = new JFrame("Button Example");
		final JTextField tf=new JTextField();  
		tf.setBounds(50,50, 150,20);  
		JButton b = new JButton("Click Here");
//		JButton b=new JButton(new ImageIcon("D:\\icon.png"));    
		b.setBounds(50,100,95,30);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tf.setText("Welcome to Java");
			}
		});
		f.add(b);
		f.add(tf);
		f.setSize(400,400);
		f.setLayout(null); //이걸 해줘야 버튼이 가득 차는 것을 방지
		f.setVisible(true); //이걸 해줘야 창이 보인다.
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    

	}
}
