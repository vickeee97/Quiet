package version4;

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
	public GUIClient getClient() {
		return uiC;
	}
	public void setTextArea(String s) {
		System.out.println("11");
		uiC.setTextArea(s);
	}
	public void sendMessage() {
		System.out.println("1");
		LinkedList<User> users=client.getUserList();
		System.out.println("2");
		LinkedList<User> selectedUsers=new LinkedList<User>();
		System.out.println("3");
		int[] selectedU=uiC.getSelectedUsers();
		System.out.println("4");
		for(int i=0; i<selectedU.length-1; i++) {
			selectedUsers.add(users.get(selectedU[i]));
			System.out.println(users.get(selectedU[i]));
		}
		Message message= new Message(uiC.getMessage(), client.getUser(), selectedUsers);
		client.sendMessage(message);
	}
	
	
}
