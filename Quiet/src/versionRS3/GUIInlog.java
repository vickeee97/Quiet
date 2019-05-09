package versionRS3;

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
/**
 * Method that created the log in UI
 * @author Viktor, Kajsa, Emil
 *
 */
public class GUIInlog extends JPanel implements ActionListener {
	private JTextField tfUsername= new JTextField();
	private JLabel lblUsername = new JLabel("Username");
	private JButton btnGoOnline = new JButton("Go online!");
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
		pnlCenter.add(btnGoOnline);
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

		btnGoOnline.addActionListener(this);
		btnExit.addActionListener(this);
	}
	public void setController(ClientController controller) {
    	this.controller=controller;
    }

	public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==btnGoOnline) {
			 String name = tfUsername.getText();
			 if(controller.isUsernameValid(name)) {
				 JFrame frame = new JFrame("Quiet: " + name);
				 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				 frame.add(this.controller.getClient());
				 frame.pack();
				 frame.setVisible(true);
				 frame.setResizable(false);
				 setVisible(false);
				 controller.createUser(tfUsername.getText());  
			}else{ 
				JOptionPane.showMessageDialog(null, "Username is not valid.", "fel", 0);
				
			}
		}else if(e.getSource()==btnExit) {
			System.exit(0);
		}
	}
}

