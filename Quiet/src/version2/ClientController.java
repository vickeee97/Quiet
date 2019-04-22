package version2;


import java.util.LinkedList;
import javax.swing.JFrame;


public class ClientController {
	private Client client;
	private GUIInlog uiI;
	private GUIClient uiC;
	
	public ClientController(Client client, GUIInlog uiI, GUIClient uiC) {
		this.client=client;
		this.uiI=uiI;
		this.uiC=uiC;
		client.setController(this);
		uiI.setController(this);
		uiC.setController(this);
	}
	public void createUser(String userName) {
		client.createUser(userName);
	}
	public LinkedList<User> getUserList() {
		return client.getUserList();
	}
	public void setUserList(LinkedList<User> userList) {
		uiC.setUserList(userList);
	}
	public static void main(String[] args) {
		GUIInlog uiI= new GUIInlog();
		GUIClient uiC= new GUIClient();
		new ClientController(new Client("127.0.0.1", 6946), uiI, uiC);
		JFrame frame = new JFrame("Quiet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(uiI);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
