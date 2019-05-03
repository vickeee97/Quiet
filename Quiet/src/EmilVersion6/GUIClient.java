package EmilVersion6;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

public class GUIClient extends JPanel implements ActionListener {
	
	private JPanel pnl = new JPanel(new BorderLayout());
	private JPanel pnlWest = new JPanel(new GridLayout(2,1));
	private JPanel pnlCenter= new JPanel(new BorderLayout());
	private JPanel pnlCenterSouth= new JPanel(new BorderLayout());
	private JPanel pnlCenterS= new JPanel(new GridLayout(2,1));
	private JTextArea taMessage= new JTextArea();
	private JButton btnEcryptnSend = new JButton("Encrypt and send");
	private JButton btnDecrypt = new JButton("Decrypt");
	
	private DefaultListModel<String> LMKontactList= new DefaultListModel<String>();
	private JList<String> kontactList = new JList<String>(LMKontactList);
	
	private JScrollPane jspKontaktList = new JScrollPane(kontactList);
	private DefaultListModel LMMessageList = new DefaultListModel();
	private JList messageList = new JList(LMMessageList);
	private JScrollPane scrollMessageList = new JScrollPane(messageList);
	private ImageIcon img = new ImageIcon(this.getClass().getResource("/quiet.png"));
	private JLabel lblBild = new JLabel(img);
	private ClientController controller;
	
	private JButton keyBtn = new JButton("Get keys");
	
	public GUIClient() {
		JScrollBar kontaktScrollBar = jspKontaktList.getVerticalScrollBar();
		JScrollBar messageScrollBar = scrollMessageList.getVerticalScrollBar();
	   
		add(pnl);
		pnlWest.setPreferredSize(new Dimension(200,400));
		pnlCenter.setPreferredSize(new Dimension(400,400));
		
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
	    pnlCenterS.add(btnEcryptnSend);
	    pnlCenterS.add(keyBtn);
	    
	    pnlCenterSouth.add(pnlCenterS, BorderLayout.EAST);
	    pnlCenterSouth.add(taMessage, BorderLayout.CENTER);
	    
	    pnlCenter.add(pnlCenterSouth, BorderLayout.SOUTH);
	    pnlCenter.add(scrollMessageList, BorderLayout.CENTER);
	    scrollMessageList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	    
	    messageList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		messageList.setLayoutOrientation(JList.VERTICAL);
		messageList.setVisibleRowCount(-1);
	    
	    taMessage.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
	    btnDecrypt.addActionListener(this);
	    btnEcryptnSend.addActionListener(this);
	    
	}
	public void setUserList(LinkedList<User> userList) {
		if (LMKontactList.getSize() > 0) {
			LMKontactList.removeAllElements();
		}
		String temp;
		for(User elem: userList) {
			temp=elem.getName();
			LMKontactList.addElement(temp);
		}
	}
	public List<String> getSelectedUsers() {
		List<String> list=kontactList.getSelectedValuesList();
		return list;
	}
	public String getMessage() {
		return taMessage.getText();
	}
	public void setTextArea(String s) {
		System.out.println("12");
		LMMessageList.addElement(s);
	}
	
	public void setController(ClientController controller) {
    	this.controller=controller;
    }
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEcryptnSend) {
			if (controller.checkClientKeys() == true) {
			try {
				controller.sendMessage();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		}} if (e.getSource() == keyBtn) {
			controller.getKeys();
		} if (e.getSource() == btnDecrypt) {
			controller.deCryptMsg(taMessage.getText());
			String text = controller.getDecrypted();
			JOptionPane.showMessageDialog(null, text, "Test", 0);
			
		}
	}

}
