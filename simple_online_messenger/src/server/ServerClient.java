package server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
public class ServerClient {
	public Socket clientSocket;
	private DataInputStream dataInputStream;
	private DataOutputStream dataOutputStream;

	public ServerClient(Socket s) {		
		try {
			this.clientSocket = s;
			dataInputStream = new DataInputStream(clientSocket.getInputStream());
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			receive();
		} catch (Exception e) {}		
	}
	public void receive() {
		Runnable thread = new Runnable() {
			public void run() {
				try {
					while (true) {
						String recvData = dataInputStream.readUTF();					
						for (ServerClient client : Server.clientList) {
							client.send(recvData);
						}
					}
				} catch (Exception e) {}
			}
		};
		Server.threadPool.submit(thread);
	}

	public void send(String message) {
		Runnable thread = new Runnable() {
			public void run(){
				try {
					dataOutputStream.writeUTF(message);
				}catch(Exception e) {}
			}
		};
		Server.threadPool.submit(thread);				
	}
}
