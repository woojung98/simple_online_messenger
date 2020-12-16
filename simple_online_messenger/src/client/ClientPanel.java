package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientPanel extends JPanel implements ActionListener{
	Client client;
	JTextArea textArea = new JTextArea();
	JButton inputBtn = new JButton();
	JButton connBtn = new JButton();
	TextField input = new TextField();
	TextField userIp ;
	TextField userName;
	TextField userPort;
	int port = 9876;
	public ClientPanel() {
		setLayout(null);
		Font font = new Font("NanumGothic", Font.PLAIN, 19);
		
		userName = new TextField(8);
		userName.setBounds(0, 0, 160, 30);
		userName.setForeground(Color.darkGray);
		userName.setFont(font);
		add(userName);
		userName.setText("닉네임");
		userIp = new TextField("127.0.0.1");
		userIp.setBounds(180, 0, 190, 30);
		userIp.setForeground(Color.darkGray);
		userIp.setFont(font);
		add(userIp);
		userPort = new TextField("9876");
		userPort.setBounds(390, 0, 80, 30);
		userPort.setForeground(Color.darkGray);
		userPort.setFont(font);
		add(userPort);
		textArea.setBounds(0, 30, 500, 390);
		textArea.setForeground(Color.GRAY);
		textArea.setEditable(false);
		add(textArea);
		
		connBtn.setText("접속하기");
		font = new Font("NanumGothic", Font.BOLD, 16);
		connBtn.setFont(font);
		connBtn.setBounds(0, 420, 100, 40);
		connBtn.setBackground(Color.pink);
		connBtn.setForeground(Color.white);
		add(connBtn);
		connBtn.addActionListener(this);
		
		input.setBounds(100, 420 , 300 , 40);
		add(input);
		input.addActionListener(this);
		input.setFont(font);
		
		inputBtn.setText("보내기");
		inputBtn.setFont(font);
		inputBtn.setBounds(400, 420, 90, 40);
		inputBtn.setBackground(Color.pink);
		inputBtn.setForeground(Color.white);
		add(inputBtn);
		inputBtn.addActionListener(this);
		
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == connBtn) {
			if(connBtn.getText().equals("접속하기")) {
				
				this.client.startClient(userIp.getText() , port);
				String msg = textArea.getText();
				msg += "[채팅방 접속]\n";
				textArea.setText(msg);
				connBtn.setText("종료하기");
				input.requestFocus();
			}else {
				this.client.stopClient();
				String msg = textArea.getText();
				msg += "[채팅방 퇴장]\n";
				textArea.setText(msg);
				connBtn.setText("접속하기");
			}
		
		}
		if(e.getSource() == input) {
			this.client.send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");
			input.requestFocus();
		}
		if(e.getSource() == inputBtn) {
			String msg = textArea.getText();
			msg += userName.getText() + ": " + input.getText() + "\n";
			
			this.client.send(msg);
			input.setText("");
			input.requestFocus();
		}
	}
	
}
