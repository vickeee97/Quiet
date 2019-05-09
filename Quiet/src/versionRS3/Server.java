package versionRS3;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Class that handles the server
 * @author Viktor och Kajsa
 *
 */
public class Server extends Thread {
	private ServerSocket serverSocket;
	private LinkedList<ClientHandler> handlerList = new LinkedList<ClientHandler>();
	private ArrayList<String> invalidUsernames = new ArrayList<String>();
	/**
	 * Constructor who starts the run method
	 * @param port
	 */
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.getStackTrace();
		}
		start();
	}
	/**
	 * Run method that connects sockets and creates new client handler and then runs the run method in clientHandler class
	 */
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ClientHandler handler = new ClientHandler(socket);
				handler.start();
			} catch (IOException e) {
				
			}
		}
	}
	/**
	 * Handles client handlers to users
	 */
	public void sendUserListToAll() {
		LinkedList<User> users = new LinkedList<User>();
		for(ClientHandler elem : handlerList) {
			users.add(elem.getUser());
		}
		try {
			for(ClientHandler elem : handlerList) {
				elem.getOutputStream().writeObject(users);
				elem.getOutputStream().flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Class that handles clientHandlers
	 * @author Kajsa och Viktor
	 *
	 */
	private class ClientHandler extends Thread {
		
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private User user;
		/**
		 * Constructor who adds handlers to the handlerList
		 * @param socket
		 */
		public ClientHandler(Socket socket) {
			this.socket=socket;
			handlerList.add(this);
			
		}
		/**
		 * Method that returns user
		 * @return user
		 */
		public User getUser() {
			return user;
		}
		/**
		 * Run method that connects streams and handles objects that come in as users and messages
		 */
		public void run() {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				try {
					oos.writeObject(invalidUsernames);
					oos.flush();
				}catch(IOException e) {
					e.printStackTrace();
				}
				while (true) {
					Object o = ois.readObject();
					if (o instanceof User) {
						user = (User) o;
						invalidUsernames.add(user.getName());
						sendUserListToAll();
					}else if(o instanceof Message) {
						Message message = (Message)o;
						sendMessage(message);
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				
			}	
		}
		public synchronized ObjectOutputStream getOutputStream() {
			return oos;
		}
	}
	/**
	 * Method that sends the message to the recievern
	 * @param message
	 */
	public synchronized void sendMessage(Message message) {
		for (ClientHandler handler : handlerList) {
				if (handler.getUser().getName().equals(message.getReciever().getName())) {
					try {
						handler.getOutputStream().writeObject(message);
						handler.getOutputStream().flush();
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
		}
	/**
	 * Method that starts the server
	 * @param args
	 */
	public static void main(String[] args) {
		new Server(6946);
	}
}
