import java.io.IOException;
import java.net.Socket;


public class Client {
	public static void main(String arg[]) throws IOException{
		Socket socket = new Socket("Localhost", 4555);
	}
}
