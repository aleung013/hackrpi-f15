import java.awt.FlowLayout;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Interface extends JFrame {

	private JLabel output;
	private JButton btn;
	private JTextField txtField;

	public Interface(int width, int height) {
		this.setLayout(null);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);

		this.setTitle("Leap Sign");
		this.setResizable(true);

		createMenuBar();

		//JLabel THElabel = createlabel("lskdj", 5, 0, 60, 20);

		//JButton THEbutton = createbutton("sldkvje", 5, this.getHeight()-100, 100, 20);

		//txtField = new JTextField();

		//txtField.setBounds(120, this.getHeight() - 100, this.getWidth() - 240, 20);
		//txtField.setLayout(null);
		//this.add(txtField);

		this.setVisible(true);
	}

	public JLabel createlabel(String THEstring, int xcor, int ycor, int width, int height) {
		JLabel THElabel = new JLabel(THEstring);
		THElabel.setBounds(xcor, ycor, width, height);
		THElabel.setLayout(null);
		THElabel.setFont(THElabel.getFont().deriveFont(24.0f));

		this.add(THElabel);

		return THElabel;
	}

	public JButton createbutton(String THEstring, int xcor, int ycor, int width, int height) {
		JButton THEbutton = new JButton(THEstring);

		THEbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.out.println(THEstring);
			}
		});

		THEbutton.setBounds(xcor, ycor, width, height);
		THEbutton.setLayout(null);
		THEbutton.setFont(THEbutton.getFont().deriveFont(24.0f));

		this.add(THEbutton);

		return THEbutton;
	}

	private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Exit", icon);
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(eMenuItem);
        menubar.add(file);

        setJMenuBar(menubar);
    }
}