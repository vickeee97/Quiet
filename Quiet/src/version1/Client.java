package version1;
import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * En klass som hanterar det som skickas fr�n klienten.
 * @author Viktor
 *
 */
public class Client extends Thread{
	private User user;
	
	private Socket socket;
	private LinkedList<Message> recievedMessages = new LinkedList<Message>();
	private String ip;
	private int port;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private UIclient ui;
	
	private Client (String ip, int port, UIclient ui) {
		this.ip = ip;
		this.port = port;
		this.ui = ui;
		ui.sendClient(this);
	}
	
	public void run(){
		try {
			while(true) {
				Object o = ois.readObject();
				if(o instanceof Message) {
					Message message = (Message) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void connect(){
		try{
			socket = new Socket(ip,port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public User getUser(){
		return user;
	}
	
	public void sendUser(User user) {
		try {
			oos.writeObject(user);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sendMessage(String message, User sender){
		Message msg = new Message(message, sender);
		try {
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String arg[]) throws IOException{
		SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			try {
				UIclient window = new UIclient();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		});
		
		String str = "hej";
		
		Socket socket = new Socket("Localhost", 4555);
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
			oos.writeObject(str);
			oos.flush();
			oos.close();
		} catch (IOException e) {}
				
	}
}
