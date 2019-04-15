package version1;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
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
	private DefaultListModel<String> LMMessageList2 =  new DefaultListModel();
	private JButton btnNewButton_1 = new JButton("Encrypt and send");
	

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIclient window = new UIclient();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
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
//		event e = new event();
		btnNewButton_1.addActionListener(this);

		JList list = new JList();
		list.setBounds(130, 163, 1, 1);
		frame.getContentPane().add(list);

//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setBounds(852, 11, 17, 353);
//		frame.getContentPane().add(scrollBar);

		JLabel lblNamn = new JLabel("Viktor");
		lblNamn.setBounds(120, 37, 46, 14);
		frame.getContentPane().add(lblNamn);

//		JScrollBar scrollBar_1 = new JScrollBar();
//		scrollBar_1.setBounds(179, 117, 17, 318);
//		frame.getContentPane().add(scrollBar_1);

		DefaultListModel LMMessageList = new DefaultListModel();
		JList messageList = new JList(LMMessageList);
		JScrollPane scrollMessageList = new JScrollPane(messageList);
		JScrollBar messageScrollBar = scrollMessageList.getVerticalScrollBar();
//		JTextPane txtpnVnner = new JTextPane();
//		txtpnVnner.setText(" V�nner");
		scrollMessageList.setBounds(10, 117, 178, 318);
		frame.getContentPane().add(scrollMessageList);
//		scrollMessageList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		JLabel label = new JLabel();
		ImageIcon img = new ImageIcon(this.getClass().getResource("/insertImageHere.png"));
		label = new JLabel(img);
		label.setBounds(10, 11, 104, 95);
		frame.getContentPane().add(label);

		textArea_2 = new JTextArea();
		textArea_2.setBounds(206, 375, 339, 60);
		frame.getContentPane().add(textArea_2);
		textArea_2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
//		textArea_1 = new JTextArea();
//		DefaultListModel LMMessageList2 = new DefaultListModel();
		JList messageList2 = new JList(LMMessageList2);
		JScrollPane scrollMessageList2 = new JScrollPane(messageList2);
		JScrollBar messageScrollBar2 = scrollMessageList2.getVerticalScrollBar();
		messageList2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		messageList2.setLayoutOrientation(JList.VERTICAL);
		messageList2.setVisibleRowCount(-1);
		scrollMessageList2.setBounds(206, 11, 636, 353);
		frame.getContentPane().add(scrollMessageList2);

		
//		textArea_1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

	}
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnNewButton_1) {
//			String text = textArea_2.getText();
//			if(text == null ? "" == null : text.equals("")) {
//				textArea_2.setText("Please enter some text");
//			} else {
//				LMMessageList2.addElement("hej");
//			}
//			textArea_2.setText("");
				LMMessageList2.addElement("hej");
		}}
	}

