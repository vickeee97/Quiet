package version4;

import java.util.LinkedList;
import java.util.List;
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
	public String getClientName() {
		return client.getUserName();
	}
	public void setTextArea(String s) {
		System.out.println("11");
		uiC.setTextArea(s);
	}
	public void sendMessage() {
		
		LinkedList<User> users=client.getUserList();
		LinkedList<User> selectedUsers=new LinkedList<User>();
		List<String> selectedU=uiC.getSelectedUsers();
		
		for(int i=0; i<selectedU.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				if(selectedU.get(i)==users.get(j).getName()) {
					selectedUsers.add(users.get(j));
				}
			}
		}
		
		Message message= new Message(uiC.getMessage(), client.getUser(), selectedUsers);
		client.sendMessage(message);
	}
	
	
}
