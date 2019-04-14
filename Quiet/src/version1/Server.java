package version1;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
/**
 * En klass som hanterar logiken fï¿½r servern.
 * @author Viktor
 *
 */
public class Server extends Thread {

	private ServerSocket serverSocket;
	private LinkedList<handler> handlerList = new LinkedList<handler>();
	private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;
    private inloggUI inlogg = new inloggUI();
	
	
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}
	
	public void run() {
		while(true) {
			try {
				Socket socket = serverSocket.accept();
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String arg[]) throws IOException, ClassNotFoundException{
		ServerSocket serverSocket = new ServerSocket(4555);
		Socket socket = serverSocket.accept();
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
			String str = (String)ois.readObject();
			System.out.println(str);
		} catch (IOException e) {}
		System.out.println("Client connected");
	}
	
	public void login() throws Exception{
        //buffered reader som läser datan från clienten
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String username = reader.readLine();
        String password = reader.readLine();

        //printwriter som skriver data till clienten
        writer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

        if(username.equals(inlogg.getUsername()) &&password.equals(inlogg.getPassword())){
            writer.println("Välkommen " + username);
        }else { 
            writer.println("Inloggningen misslyckades");
        }
    }

	
	private class handler extends Thread {
		private Socket socket;
		private ObjectOutputStream oos;
		private ObjectInputStream ois;
		private User user;
		
		public handler (Socket socket) {
			this.socket = socket;
			handlerList.add(this);
		}
		
		public synchronized ObjectOutputStream getOutputStream() {
			return oos;
		}
		
		public User getUser() {
			return user;
		}
		
		public void run() {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				
				while(true) {
					Object o = ois.readObject();
					if(o instanceof Message) {
						Message message = (Message) o;
						sendMessage(message);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private synchronized void sendMessage(Message message) {
			LinkedList<User> recieverList = message.getRecievers();
			for(handler handler : handlerList) {
				for(int i = 0; i<recieverList.size(); i++) {
					if(handler.getUser().getName().equals(recieverList.get(i).getName())) {
						try {
							handler.getOutputStream().writeObject(message);
							recieverList.remove(i);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	}
}
