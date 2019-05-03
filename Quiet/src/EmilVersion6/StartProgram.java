package EmilVersion6;

import javax.swing.JFrame;

public class StartProgram {

	public static void main(String[] args) {
		GUIInlog uiI = new GUIInlog();
		GUIClient uiC = new GUIClient();
		new ClientController(new Client("127.0.0.1", 6946), uiI, uiC);
		JFrame frame = new JFrame("Quiet");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(uiI);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
