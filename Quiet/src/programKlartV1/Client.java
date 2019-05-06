package programKlartV1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

public class Client extends Thread {
	
	private String ip;
	private int port;
	private LinkedList<User> users = new LinkedList<User>();
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private User user;
	private ClientController controller;
	
	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	public void setController(ClientController controller) {
		this.controller=controller;
	}
	
	public void run() {
		try {
			while (true) {
				Object o = ois.readObject();
				if(o instanceof LinkedList) {
					LinkedList<User> userList=(LinkedList<User>)o;
					controller.setUserList(userList);
					users=userList;
					users.stream().forEach(user -> System.out.println(user.getName() + ": " + user.getPublicKey()));
				}else if(o instanceof Message) {
					Message m=(Message)o;
					controller.setTextArea(m);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream((socket.getOutputStream()));
			ois = new ObjectInputStream((socket.getInputStream()));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public LinkedList<User> getUserList() {
		return users;
	}
	public User getUser() {
		return user;
	}
	public String getUserName() {
		return user.getName();
	}
	public synchronized void sendMessage(Message message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createUser(String userName) {
			this.user = new User(userName);
			connect();
			start();
			System.out.println("create user" + user.getName() + user.getPublicKey());
			try {
				oos.writeObject(user);
				oos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
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
