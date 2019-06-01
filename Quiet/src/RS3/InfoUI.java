package RS3;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * @author Emil Dahl
 *
 */

public class InfoUI extends JFrame implements ActionListener {
	private JButton btnSend = new JButton("How do I send a message?");
	private JButton btnRead = new JButton("How do I read a message?");
	private JButton info1 = new JButton("Who can read my messages?");
	private JButton info2 = new JButton("What is Quiet?");
	private JPanel pnlBtn = new JPanel();


	public InfoUI () {
		JFrame frame = new JFrame("FAQ");

		pnlBtn.setLayout(new GridLayout(2, 2));
		pnlBtn.add(btnRead);
		pnlBtn.add(btnSend);
		pnlBtn.add(info1);
		pnlBtn.add(info2);
		frame.add(pnlBtn);

		btnRead.addActionListener(this);
		btnSend.addActionListener(this);
		info1.addActionListener(this);
		info2.addActionListener(this);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(450, 200);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRead) {
			JOptionPane.showMessageDialog(null,  "As you receive a message another user, the message will appear encrypted. \n To read this message highlight it in your inbox and press decrypt. \n This will decrypt the chosen message to the original message.", "Reading a message", 0);
		} else if (e.getSource() == btnSend) {
			JOptionPane.showMessageDialog(null,  "To send a message to another user, write your message in the textwindow. \n Choose which user or users you want as receivers from the list of active users. \n With the receivers selected press the button encrypt and send.", "Sending a message", 0);
		} else if (e.getSource() == info1) {
			JOptionPane.showMessageDialog(null,  "Quiet uses an encryption which only allows the user with the correct KEY to decrypt a message. \n This means noone else except the specific receiver can decrypt and read the message. \n Especially not the server or also known as we at Quiet HQ. \n", "Who has acess to my message?", 0);
		} else if (e.getSource() == info2) {
			JOptionPane.showMessageDialog(null, "Quiet is a chat application that allows you, the user to send encrypted messages. \n We value your privacy and by communicating through Quiet you dont even need to trust us. \n The messages you send are private.", "What is Quiet?", 0);
		}
	}
}