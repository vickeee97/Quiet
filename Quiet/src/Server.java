import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String arg[]) throws IOException{
		ServerSocket serverSocket = new ServerSocket(4555);
		Socket socket = serverSocket.accept();
		
		System.out.println("Client connected");
	}
}
