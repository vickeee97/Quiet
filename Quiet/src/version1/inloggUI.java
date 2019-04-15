package version1;


import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * En klass som hanterar UI till inloggen
 * @author Viktor
 * 
 */
public class inloggUI implements ActionListener{

	JFrame frame;
	private JTextField tfUsername;
	private JTextField textField_1;
	private JButton btnLoggaIn = new JButton("Logga in");
    private JButton btnAvbryt = new JButton("Avbryt");
    private String username;
	private String password;
    

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
		
		tfUsername = new JTextField();
		tfUsername.setBounds(73, 136, 209, 20);
		frame.getContentPane().add(tfUsername);
//		tfUsername.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(73, 182, 209, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblAnvndarnamn = new JLabel("Användarnamn");
		lblAnvndarnamn.setBounds(73, 122, 113, 14);
		frame.getContentPane().add(lblAnvndarnamn);
		
		JLabel lblLsenord = new JLabel("Lösenord");
		lblLsenord.setBounds(73, 167, 113, 14);
		frame.getContentPane().add(lblLsenord);
		
		btnLoggaIn.setBounds(120, 213, 113, 23);
		frame.getContentPane().add(btnLoggaIn);
		btnLoggaIn.addActionListener(this);
		
		btnAvbryt.setBounds(133, 357, 89, 23);
		frame.getContentPane().add(btnAvbryt);
		btnAvbryt.addActionListener(this);
	}

	public Object getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLoggaIn) {
			if(tfUsername.getText().length()==0) {
			JOptionPane.showMessageDialog(null, "Du måste skriva in ett användarnamn");
			}else {
				username = tfUsername.getText();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frame.setVisible(false);
							UIclient window2 = new UIclient();
							window2.frame.setVisible(true);
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		}else if (e.getSource() == btnAvbryt) {
			System.exit(0);
		}
	}
	
	public void writeName () {
		OutputStream os = null;
		try { 
			os = new FileOutputStream(files/users.txt);
			os.write(username.getBytes()0, username.length());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}