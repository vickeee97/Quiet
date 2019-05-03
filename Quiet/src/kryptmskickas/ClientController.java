package kryptmskickas;

import java.security.PublicKey;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Cipher;
import javax.swing.JFrame;

public class ClientController {
	private Client client;
	private GUIInlog uiI;
	private GUIClient uiC;
	private LinkedList<Message> messages= new LinkedList<Message>();
	
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
	public void setTextArea(Message message) {
		uiC.setTextArea(message.getText());
		messages.add(message);
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
//		selectedU.add(client.getUser());
		for(int k=0; k<selectedU.size(); k++) {
			byte [] encryptedMessage= null;
			try {
			encryptedMessage=encrypt(m, selectedU.get(k).getPublicKey());
			} catch (Exception e) {
			e.printStackTrace();
			}
			Message message= new Message(encryptedMessage.toString(), client.getUser(), selectedU.get(k));
			client.sendMessage(message);
		}
		
		
	}
	public byte[] encrypt(String message, PublicKey publicKey) throws Exception {
		   
		Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);

	    return cipher.doFinal(message.getBytes());
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
			
			String decryptedMessage=client.decrypt(fromHexString(selectedmessage.getText()));
			uiC.setTextArea(decryptedMessage);
		}
	}
	public byte[] fromHexString(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len-1; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
}
