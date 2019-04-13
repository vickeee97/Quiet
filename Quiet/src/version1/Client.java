package version1;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * En klass som hanterar det som skickas fr�n klienten.
 * @author Viktor
 *
 */
public class Client {
	private User user;
	private controllerClient controllerClient;
	
	private Socket socket;
	private LinkedList<Message> recievedMessages = new LinkedList<Message>();
	private String ip;
	private int port;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private Client (String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void run(){
		try {
			while(true) {
				
			}
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
		String str = "hej";
		
		Socket socket = new Socket("Localhost", 4555);
		try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {
			oos.writeObject(str);
			oos.flush();
			oos.close();
		} catch (IOException e) {}
				
	}
}
