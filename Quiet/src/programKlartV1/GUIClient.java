package programKlartV1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

/**
 * 
 * Method that creates the client UI, fills the list of connected clients also contains what happends when the two buttons "encrypt and send" and "decrypt" is pressed.
 *
 */

/*
 * All parts of client GUI is declared and created.
 */
public class GUIClient extends JPanel implements ActionListener {
	private JPanel pnl = new JPanel(new BorderLayout());
	private JPanel pnlWest = new JPanel(new GridLayout(2, 1));
	private JPanel pnlCenter = new JPanel(new BorderLayout());
	private JPanel pnlCenterSouth = new JPanel(new BorderLayout());
	private JPanel pnlCenterS = new JPanel(new GridLayout(2, 1));
	private JTextArea taMessage = new JTextArea();
	private JButton btnEncryptnSend = new JButton("Encrypt and send");
	private JButton btnDecrypt = new JButton("Decrypt");

	private DefaultListModel<String> LMKontactList = new DefaultListModel<String>();
	private JList<String> kontactList = new JList<String>(LMKontactList);

	private JScrollPane jspKontaktList = new JScrollPane(kontactList);
	private DefaultListModel LMMessageList = new DefaultListModel();
	private JList messageList = new JList(LMMessageList);
	private JScrollPane scrollMessageList = new JScrollPane(messageList);
	private ImageIcon img = new ImageIcon(this.getClass().getResource("/quiet.png"));
	private JLabel lblBild = new JLabel(img);
	private ClientController controller;

	/**
	 * Constructor where the declared "parts" are added to the frame
	 */
	public GUIClient() {
		JScrollBar kontaktScrollBar = jspKontaktList.getVerticalScrollBar();
		JScrollBar messageScrollBar = scrollMessageList.getVerticalScrollBar();

		add(pnl);
		pnlWest.setPreferredSize(new Dimension(200, 400));
		pnlCenter.setPreferredSize(new Dimension(400, 400));

		pnlWest.setBackground(Color.WHITE);
		pnlCenter.setBackground(Color.WHITE);

		pnl.add(pnlWest, BorderLayout.WEST);
		pnl.add(pnlCenter, BorderLayout.CENTER);

		jspKontaktList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		kontactList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		kontactList.setLayoutOrientation(JList.VERTICAL);
		kontactList.setVisibleRowCount(-1);
		pnlWest.add(lblBild);
		pnlWest.add(jspKontaktList);

		pnlCenterS.add(btnDecrypt);
		pnlCenterS.add(btnEncryptnSend);

		pnlCenterSouth.add(pnlCenterS, BorderLayout.EAST);
		pnlCenterSouth.add(taMessage, BorderLayout.CENTER);

		pnlCenter.add(pnlCenterSouth, BorderLayout.SOUTH);
		pnlCenter.add(scrollMessageList, BorderLayout.CENTER);
		scrollMessageList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		messageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		messageList.setLayoutOrientation(JList.VERTICAL);
		messageList.setVisibleRowCount(-1);

		taMessage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
/*
 * Adding actionListeners for the two buttons
 */
		btnDecrypt.addActionListener(this);
		btnEncryptnSend.addActionListener(this);
	}

	/**
	 * Method that clears the defaultListModel and then fills it with the LinkedList parameter
	 * @param userList
	 */
	public void setUserList(LinkedList<User> userList) {
		if (LMKontactList.getSize() > 0) {
			LMKontactList.removeAllElements();
		}
		String temp;
		for (User elem : userList) {
			temp = elem.getName();
			LMKontactList.addElement(temp);
		}
	}
/**
 * Method that sets the textArea to the string parameter by adding the string to LMMessageList
 * @param s
 */
	public void addInMessageList(String s) {
		LMMessageList.addElement(s);

	}

	/**
	 * 
	 * @param controller
	 */
	public void setController(ClientController controller) {
		this.controller = controller;
	}

	/**
	 * Method that responds to the two actionListeners for the buttons in the GUI.
	 * btnEncryptnSend sets list selectedUsers to the users that have been selected by the user in the GUI.
	 * Calls sendMessage() with the selectedUsers(receivers user have selected) and the actual message text, then clears where the user writes text.
	 * 
	 * the selected messages that the user have received is decrypted by sending these messages as a parameter to the decrypyMessage() method in the controller class.
	 */
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEncryptnSend) {
			List<String> selectedUsers = kontactList.getSelectedValuesList();
			controller.sendMessage(selectedUsers, taMessage.getText());
			taMessage.setText("");
		} else if (e.getSource() == btnDecrypt) {
			List<String> selectedMessages = messageList.getSelectedValuesList();
			controller.decryptMessage(selectedMessages);
		}
	}

}
