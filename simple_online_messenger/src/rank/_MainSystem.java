package rank;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class _MainSystem {
	
	static JFrame frame = new JFrame();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final int WIDTH = 400;
		final int HEIGHT = 500;
		frame.setTitle("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen = tk.getScreenSize();
		frame.setLocation(screen.width/2 - WIDTH/2, screen.height/2 - HEIGHT/2);
		
		FileManager.instance.loadData();
		
		LoginPanel login_panel = new LoginPanel();
		frame.setContentPane(login_panel);
		frame.revalidate();
		
		StopWatch sw = new StopWatch();
		sw.run();
	}

}
