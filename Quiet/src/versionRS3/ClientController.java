package versionRS3;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.Cipher;

/**
 * 
 *  Class that acts like a communicator between other classes. Also contains RSA methods that deals with encryption and decryption of messages
 *
 */
public class ClientController {
	private Client client;
	private GUIInlog uiI;
	private GUIClient uiC;
	private LinkedList<Message> messages= new LinkedList<Message>();
	private ArrayList<String> invalidUsernames = new ArrayList<String>();
	
	/**
	 * Constructor that takes in instance variables for Client, GUInlog and GUIClient
	 * @param client
	 * @param uiI
	 * @param uiC
	 */
	
	public ClientController(Client client, GUIInlog uiI, GUIClient uiC) {
		this.client=client;
		this.uiI=uiI;
		this.uiC=uiC;
		client.setController(this);
		uiI.setController(this);
		uiC.setController(this);
	}
	
	/**
	 * Method that calls the method createUser in the class client with the parameter userName
	 * @param userName
	 */
	public void createUser(String userName) {
		client.createUser(userName);
	}
	
	/**
	 * Method that calls the method getUserList in the class client, and then returns the LinkedList
	 * @return LinkedList <user> Users
	 */
	public LinkedList<User> getUserList() {
		return client.getUserList();
	}
	
	/**
	 *  Method that calls the method setUserList() in the class GUIClient with the parameter userList
	 * @param userList
	 */
	public void setUserList(LinkedList<User> userList) {
		uiC.setUserList(userList);
	}
	public GUIClient getClient() {
		return uiC;
	}
	
	/**
	 * Method that calls the method getUserName in the class client and returns the string userName
	 * @return userName
	 */
	public String getClientName() {
		return client.getUserName();
	}
	
	/**
	 * Method that adds the parameter message in the LinkedList messages
	 * @param message
	 */
	public void addInMessageList(Message message) {
		uiC.addInMessageList(message.getText());
		messages.add(message);
	}
	
	/**
	 * @param invalidUsernames
	 */
	public void setinvalidUsernames(ArrayList invalidUsernames) {
		this.invalidUsernames=invalidUsernames;
	}
	
	/**
	 * 
	 * @return ArraList invalidUserNames
	 */
	public ArrayList getInvalidUsernames() {
		return this.invalidUsernames;
	}
	
	/**
	 * Method that sends the parameter String m to the List of selected users
	 * @param selectedUsers
	 * @param m
	 */
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
	
	/**
	 * Method that encrypts a message using a publicKey
	 * @param message
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String message, PublicKey publicKey) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	    byte[] text = cipher.doFinal(message.getBytes("UTF-8"));
	    return Base64.getEncoder().encodeToString(text);
	}
	
	/**
	 * Method that decrypts the selected messages that the methods takes in as a parameter
	 * @param selectedMessages
	 */
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
			String decryptedMessage=client.decrypt(selectedmessage.getText());
			uiC.addInMessageList(decryptedMessage);
		}
	}
	
	/**
	 *  Method that checks if a username is already used, returns true if that is the case.
	 * @param username
	 * @return
	 */
	public boolean isUsernameValid(String username) {
		if(!username.contains(" ") && !(username.length()==0)) {
			for(int i=0; i<invalidUsernames.size(); i++) {
				if(username.equals(invalidUsernames.get(i))) {
					return false;
				}
			}
		}
		return true;
	}
}
