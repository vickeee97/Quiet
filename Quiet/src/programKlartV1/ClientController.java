package programKlartV1;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.Cipher;

public class ClientController {
	private Client client;
	private GUIInlog uiI;
	private GUIClient uiC;
	private LinkedList<Message> messages= new LinkedList<Message>();
	private ArrayList<String> invalidUsernames = new ArrayList<String>();
	
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
	public void setinvalidUsernames(ArrayList invalidUsernames) {
		System.out.println("test clientcontroller klassen");
		this.invalidUsernames=invalidUsernames;
		System.out.println("getMetoden" + getInvalidUsernames() + "parameter" + invalidUsernames);
	}
	public ArrayList getInvalidUsernames() {
		return this.invalidUsernames;
	}
	public GUIClient getClient() {
		return uiC;
	}
	public String getClientName() {
		return client.getUserName();
	}
	public void addInMessageList(Message message) {
		uiC.addInMessageList(message.getText());
		messages.add(message);
	}
	public void sendMessage(List<String> selectedUsers, String m) {
		
		LinkedList<User> users=getUserList();
		LinkedList<User> selectedU=new LinkedList<User>();
		
		for(int i=0; i<selectedUsers.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				if(selectedUsers.get(i)==users.get(j).getName()) {
					selectedU.add(users.get(j));
				}
			}
		}
		for(int k=0; k<selectedU.size(); k++) {
			String encryptedMessage= null;
			try {
				encryptedMessage=encrypt(m, selectedU.get(k).getPublicKey());
			}catch (Exception e) {
				e.printStackTrace();
			}
			Message message= new Message(encryptedMessage, client.getUser(), selectedU.get(k));
			client.sendMessage(message);
		}
		
		
	}
	public String encrypt(String message, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    byte[] text = cipher.doFinal(message.getBytes("UTF-8"));
	    return Base64.getEncoder().encodeToString(text);
	}
	
	public void decryptMessage(List<String> selectedMessages) {
		LinkedList<Message> selectedM = new LinkedList<Message>();
		
		for(Message message: messages) {
			for(String selected: selectedMessages) {
				if(message.getText()==selected) {
					selectedM.add(message);
				}
			}
		}
		for(Message selectedmessage: selectedM) {
			System.out.println("reciecer" + selectedmessage.getText());
			String decryptedMessage=client.decrypt(selectedmessage.getText());
			uiC.addInMessageList(decryptedMessage);
		}
	}
	public boolean isUsernameValid(String username) {
		if(!username.contains(" ") && !(username.length()==0)) {
			System.out.println("1" + invalidUsernames);
			for(int i=0; i<invalidUsernames.size(); i++) {
				System.out.println("2");
				if(username==invalidUsernames.get(i)) {
					System.out.println("3");
					return false;
				}
			}
		}
		return true;
	}
}
