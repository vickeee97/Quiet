package version1;

import java.awt.Component;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * En klass som hanterar det som skickas fr�n klienten.
 * 
 * @author Viktor
 *
 */
public class Client extends Thread {
	private User user;

	private Socket socket;
	private LinkedList<Message> recievedMessages = new LinkedList<Message>();
	private LinkedList<User> users = new LinkedList<User>(); // borde �ndras till bara users kanske om det inte ska vara
																// s� att dem bara visas om dem �r online
	private String ip;
	private int port;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private UIclient ui;
	private inloggUI inloggUI;

	private Client(String ip, int port, UIclient ui, inloggUI inloggUI) {
		this.ip = ip;
		this.port = port;
		this.ui = ui;
		this.inloggUI = inloggUI;
		ui.sendClient(this);
		inloggUI.sendClient(this);
	}

	public void run() {
		try {
			while (true) {
				Object o = ois.readObject();
				if (o instanceof Message) {
					Message message = (Message) o;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
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

	public synchronized void sendMessage(String message, User sender) {
		Message msg = new Message(message, sender);
		try {
			oos.writeObject(msg);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// metod som l�ser av files/users, skapar User objekt med anv�ndarnamnen och
	// l�gger dem i LinkedListen onlineUsers -> denna ska sen upp i UIt
	public void getUsers(String userName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("files/users"));
			User temp;
			String name;
			while ((name = br.readLine()) != null) {
				temp = new User(name);
				users.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public void createUsers(String userName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("files/users"));
			bw.write(userName);
			User user = new User(userName);
			users.add(user);
		} catch (IOException e) {}
	}

	public void setUser(String userName) {
		this.user = new User(userName);
		users.add(user);
	}

	public static void main(String arg[]) throws IOException {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					inloggUI window = new inloggUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
