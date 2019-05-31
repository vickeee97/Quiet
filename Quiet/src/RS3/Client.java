package RS3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Client extends Thread {
	private String ip;
	private int port;
	private LinkedList<User> users = new LinkedList<User>();
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private User user;
	private ClientController controller;
	
	/**
	 * Constructor with ip and port variabels as parameters, also calls the method connect and starts the thread
	 * @param ip
	 * @param port
	 */
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		connect();
		start();
	}
	
	public void setController(ClientController controller) {
		this.controller=controller;
	}
	
	/**
	 * run method that read an object using ObjectInputStream and has different if else statements depending on what the object is
	 */
	
	public void run() {
		try {
			while (true) {
				Object o = ois.readObject();
				if(o instanceof LinkedList) {
					LinkedList<User> userList=(LinkedList<User>)o;
					controller.setUserList(userList);
					users=userList;
				}else if(o instanceof Message) {
					Message m=(Message)o;
					controller.addInMessageList(m);
				}else if(o instanceof ArrayList) {
					ArrayList<String> invalidUsernames=(ArrayList<String>)o;
					controller.setinvalidUsernames(invalidUsernames);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that instantiates the streams.
	 */
	
	public void connect() {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream((socket.getOutputStream()));
			ois = new ObjectInputStream((socket.getInputStream()));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return LinkedList users
	 */
	
	public LinkedList<User> getUserList() {
		return users;
	}
	
	/**
	 * 
	 * @return user
	 */
	
	public User getUser() {
		return user;
	}
	
	/**
	 * @return userName
	 */
	
	public String getUserName() {
		return user.getName();
	}
	
	/**
	 *  Synchronized method that send the parameter to the server by using the ObjectOutputStream
	 * @param message
	 */
	
	public synchronized void sendMessage(Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that creates a user using the parameter userName and writes the user to the server
	 * @param userName
	 */
	
	public void createUser(String userName) {
			this.user = new User(userName);
			try {
				oos.writeObject(user);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Method that decrypts the parameter String encrytedMessage 
	 * @param encryptedMessage
	 * @return the decrypted string
	 */
	
	public String decrypt(String encryptedMessage) {
		String decryptedMessage= null;
		try {
			decryptedMessage =user.decrypt(encryptedMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedMessage;
	}
}