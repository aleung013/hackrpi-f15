import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Interface extends JFrame {
	
	private JLabel output;
	private JButton btn;
	private JTextField txtField;
	
	public Interface() {
		this.setLayout(null);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		
		this.setTitle("Leap Sign");
		this.setResizable(false);
		
		output = new JLabel("Hello");
		
		output.setBounds(5, 0, 60, 20);
		output.setLayout(null);
		this.add(output);
		
		btn = new JButton("Start");
		
		btn.setBounds(5, this.getHeight() - 50, 60, 20);
		btn.setLayout(null);
		this.add(btn);
		
		txtField = new JTextField();
		
		txtField.setBounds(70, this.getHeight() - 50, this.getWidth() - 80, 20);
		txtField.setLayout(null);
		this.add(txtField);
		
		this.setVisible(true);
	}
}
