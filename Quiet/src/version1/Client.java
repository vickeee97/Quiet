package version1;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * En klass som hanterar det som skickas fr�n klienten.
 * @author Viktor
 *
 */
public class Client {
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
