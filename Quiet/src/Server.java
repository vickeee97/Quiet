import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * En klass som hanterar logiken för servern.
 * @author Viktor
 *
 */
public class Server {
	private Socket client;
	private BufferedReader reader;
	private PrintWriter writer;
	private inloggUI inlogg = new inloggUI();

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
}