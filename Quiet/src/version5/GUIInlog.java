package version5;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIInlog extends JPanel implements ActionListener {
	
	private JTextField tfUsername= new JTextField();
	private JTextField tfPassword= new JTextField();
	private JLabel lblUsername = new JLabel("Användarnamn");
	private JLabel lblPassword = new JLabel("Lösenord");
	private JButton btnLogin = new JButton("Logga in");
	private JButton btnRegister = new JButton("Registrera");
    private JButton btnExit = new JButton("Avbryt");
    private JPanel pnl=new JPanel(new BorderLayout());
    private JPanel pnlCenter= new JPanel(new GridLayout(7,1));
    private ImageIcon img = new ImageIcon(this.getClass().getResource("/quiet.png"));
	private JLabel lblBild = new JLabel(img);
	private ClientController controller;
	
	public GUIInlog() {
		
		add(pnl, BorderLayout.CENTER);
		
		/**
		 * === PNLCENTER ===
		 */
		
		pnlCenter.setBackground(Color.WHITE);
		pnlCenter.add(lblUsername);
		pnlCenter.add(tfUsername);
		pnlCenter.add(lblPassword);
		pnlCenter.add(tfPassword);
		pnlCenter.add(btnLogin);
		pnlCenter.add(btnRegister);
		pnlCenter.add(btnExit);
		
		/**
		 * === PNL ===
		 */
		
		pnl.setBackground(Color.WHITE);
		pnl.setPreferredSize(new Dimension(350,450));
		pnl.add(lblBild, BorderLayout.NORTH);
		pnl.add(pnlCenter, BorderLayout.CENTER);
		
		/**
		 * === ActionListeners ===
		 */

		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
		btnExit.addActionListener(this);
	}
	public void setController(ClientController controller) {
    	this.controller=controller;
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogin) {
			if (tfUsername.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Det valda användarnamnet är för kort. Försök igen.");
			}else {
				controller.createUser(tfUsername.getText());
				
			}
			
		}else if(e.getSource()==btnRegister) {
			if (tfUsername.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "Det valda användarnamnet är för kort. Försök igen.");
			}else {
				JFrame frame = new JFrame("Quiet:" + tfUsername.getText());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(this.controller.getClient());
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
				setVisible(false);
				controller.createUser(tfUsername.getText());
			}
		}else if(e.getSource()==btnExit) {
			System.exit(0);
		}
	}
}
