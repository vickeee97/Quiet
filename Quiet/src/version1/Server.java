package version1;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * En klass som hanterar logiken f�r servern.
 * @author Viktor
 *
 */
public class Server {

	public static void main(String arg[]) throws IOException, ClassNotFoundException{
		ServerSocket serverSocket = new ServerSocket(4555);
		Socket socket = serverSocket.accept();
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()))) {
			String str = (String)ois.readObject();
			System.out.println(str);
		} catch (IOException e) {}
		System.out.println("Client connected");
	}
}
