package version1;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * En klass som hanterar UI till inloggen
 * @author Viktor
 * 
 */
public class inloggUI implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnLoggaIn = new JButton("Logga in");
    private JButton btnAvbryt = new JButton("Avbryt");
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inloggUI window = new inloggUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inloggUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 369, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon(this.getClass().getResource("/quiet.png"));
		label = new JLabel(img);
		label.setBounds(120, 11, 104, 95);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(73, 136, 209, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 182, 209, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAnvndarnamn = new JLabel("Anv\u00E4ndarnamn");
		lblAnvndarnamn.setBounds(73, 122, 113, 14);
		frame.getContentPane().add(lblAnvndarnamn);
		
		JLabel lblLsenord = new JLabel("L\u00F6senord");
		lblLsenord.setBounds(73, 167, 46, 14);
		frame.getContentPane().add(lblLsenord);
		
		JButton btnLoggaIn = new JButton("Logga in");
		btnLoggaIn.setBounds(120, 213, 113, 23);
		frame.getContentPane().add(btnLoggaIn);
		btnLoggaIn.addActionListener(this);
		
		JButton btnAvbryt = new JButton("Avbryt");
		btnAvbryt.setBounds(133, 357, 89, 23);
		frame.getContentPane().add(btnAvbryt);
		btnAvbryt.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
        if(o==btnLoggaIn) {
            String username = textField.getText();
        }
        if(o==btnAvbryt) {
            System.exit(0);
        }
		
	}

	public Object getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
}