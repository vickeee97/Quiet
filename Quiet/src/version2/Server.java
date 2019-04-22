package version2;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;



public class Server extends Thread {
	private ServerSocket serverSocket;
	private LinkedList<ClientHandler> handlerList = new LinkedList<ClientHandler>();
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.getStackTrace();
		}
		start();
	}
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ClientHandler handler = new ClientHandler(socket);
				handler.start();
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
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
	
	private class ClientHandler extends Thread {
		
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private User user;
		
		public ClientHandler(Socket socket) {
			this.socket=socket;
			handlerList.add(this);
		}
		
		public User getUser() {
			return user;
		}
		
		
		
		public void run() {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				while (true) {
					Object o = ois.readObject();
					if (o instanceof User) {
						user = (User) o;
						sendUserListToAll();
					}
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}	
		}
		public synchronized ObjectOutputStream getOutputStream() {
			return oos;
		}
	}
	
	public static void main(String[] args) {
		new Server(6946);
	}
}
