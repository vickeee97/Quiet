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
 * Klass som hanterar servern
 * @author Viktor och Kajsa
 *
 */
public class Server extends Thread {
	private ServerSocket serverSocket;
	private LinkedList<ClientHandler> handlerList = new LinkedList<ClientHandler>();
	/**
	 * Konstruktor som startar run metoden
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
	 * Run metod som kopplar ihop sockets och skapar ny clienthandler och k�r sedan run metoden i clientHandler klassen
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
	 * Delar ut clientHandlers till users
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
	 * Klass som hanterar clientHandlers
	 * @author Kajsa och Viktor
	 *
	 */
	private class ClientHandler extends Thread {
		
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private User user;
		/**
		 * Konstruktor som l�gger till handlers i handlerList
		 * @param socket
		 */
		public ClientHandler(Socket socket) {
			this.socket=socket;
			handlerList.add(this);
			
		}
		/**
		 * Metod som returnerar user
		 * @return user
		 */
		public User getUser() {
			return user;
		}
		/**
		 * Run metod som kopplar ihop str�mmar och hanterar objekt som kommer in iform av users och messages
		 */
		public void run() {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				while (true) {
					Object o = ois.readObject();
					if (o instanceof User) {
						user = (User) o;
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
	 * Metod som skickar meddelandet till recievern
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
	 * Metod som startar servern
	 * @param args
	 */
	public static void main(String[] args) {
		new Server(6946);
	}
}
