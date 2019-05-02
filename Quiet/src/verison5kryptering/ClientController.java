package verison5kryptering;

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
	public void setTextArea(String s, User sender) {
		uiC.setTextArea(s, sender);
	}
	public void sendMessage(List<String> selectedUsers, String m) {
		
		LinkedList<User> users=client.getUserList();
		LinkedList<User> selectedU=new LinkedList<User>();
		
		for(int i=0; i<selectedUsers.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				if(selectedUsers.get(i)==users.get(j).getName()) {
					selectedU.add(users.get(j));
				}
			}
		}
		selectedU.add(client.getUser());
		
		Message message= new Message(m, client.getUser(), selectedU);
		client.sendMessage(message);
	}
	
	
}
