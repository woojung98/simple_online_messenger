package server;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerPanel extends JPanel implements ActionListener{
	JTextArea ta = new JTextArea();
	JButton btn = new JButton();
	Server server;
	String ip = "127.0.0.1";
	int port= 9876;
	
	ServerPanel(){
		setLayout(null);
		ta.setBackground(Color.lightGray);
		ta.setBounds(0, 0, 500, 420);
		add(ta);
		
		btn.setText("서버시작");
		btn.setBounds(0, 420, 500, 40);
		add(btn);
		btn.addActionListener(this);
	}
	public void setServer(Server server) {
		this.server = server;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == btn ) {
			if(btn.getText().equals("서버시작")) {
				btn.setText("서버종료");
				
				String msg = ta.getText();
				msg += "[서버시작]" + " " +  ip + " : " + port  + "\n";
				ta.setText(msg);
				
				this.server.startServer(ip , port);
			}
			else {
				btn.setText("서버시작");
				String msg = ta.getText();
				msg += "[서버종료]"   + "\n";
				ta.setText(msg);
					
				this.server.stopServer();			
			}
		}
	}

}
