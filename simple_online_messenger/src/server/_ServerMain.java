package server;


import javax.swing.JFrame;

public class _ServerMain {
	public static void main(String[] args) {		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(600, 200);
		frame.setTitle("Server");
		
		Server server = new Server();
		ServerPanel panel = new ServerPanel();
		
		panel.setServer(server);
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
