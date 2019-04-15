package version1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 * En klass som hanterar UI f�r klienten f�r att till exempel skicka medddelandna
 * @author Viktor
 *
 */
public class UIclient extends JPanel implements ActionListener {

	private Client client;
	JFrame frame;
	private AbstractButton textArea;
	private JTextArea textArea_2;
	private DefaultListModel<String> LMMessageList2;
	private JButton btnNewButton_1 = new JButton("Encrypt and send");
	

	/**
	 * Launch the application.
	 */
	
	public void sendClient(Client client) {
		this.client = client;
	}

	/**
	 * Create the application.
	 */
	public UIclient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Quiet");
		frame.setBounds(100, 100, 895, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Decrypt");
		btnNewButton.setBounds(555, 375, 109, 60);
		frame.getContentPane().add(btnNewButton);

		
		btnNewButton_1.setBounds(674, 375, 168, 60);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);

		JList list = new JList();
		list.setBounds(130, 163, 1, 1);
		frame.getContentPane().add(list);


		JLabel lblNamn = new JLabel("Viktor");
		lblNamn.setBounds(120, 37, 46, 14);
		frame.getContentPane().add(lblNamn);

		DefaultListModel LMKontaktList = new DefaultListModel();
		JList kontaktList = new JList(LMKontaktList);
		JScrollPane jspKontaktList = new JScrollPane(kontaktList);
		JScrollBar KontaktScrollBar = jspKontaktList.getVerticalScrollBar();
		jspKontaktList.setBounds(10, 117, 178, 318);
		frame.getContentPane().add(jspKontaktList);
		jspKontaktList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon(this.getClass().getResource("/insertImageHere.png"));
		label = new JLabel(img);
		label.setBounds(10, 11, 104, 95);
		frame.getContentPane().add(label);

		textArea_2 = new JTextArea();
		textArea_2.setBounds(206, 375, 339, 60);
		frame.getContentPane().add(textArea_2);
		textArea_2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		LMMessageList2 = new DefaultListModel();
		JList messageList2 = new JList(LMMessageList2);
		JScrollPane scrollMessageList2 = new JScrollPane(messageList2);
		JScrollBar messageScrollBar2 = scrollMessageList2.getVerticalScrollBar();
		messageList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		messageList2.setLayoutOrientation(JList.VERTICAL);
		messageList2.setVisibleRowCount(-1);
		scrollMessageList2.setBounds(206, 11, 636, 353);
		frame.getContentPane().add(scrollMessageList2);
		scrollMessageList2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnNewButton_1) {
			String text = textArea_2.getText();
			if(text == null ? "" == null : text.equals("")) {
				textArea_2.setText("Please enter some text");
			} else {
				LMMessageList2.addElement(text);
			}
			textArea_2.setText("");
		}}
	}

