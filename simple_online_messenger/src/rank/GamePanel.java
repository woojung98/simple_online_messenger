package rank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import client.Client;
import client.ClientPanel;

class GameRact{
	int x;
	int y;
	int size;
	String text;
	Color color;
}

public class GamePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	
	JButton game_button;
	JButton ranking_button;
	JButton reset_button;
	JButton back_main;
	JButton chat_button;
	GameRact[][] buttons;
	
	boolean start = false;
	int[][] backGame;
	int count = 1;
	
	static boolean isStopWatch = false;
	static JLabel timeTextLb;
	
	static final int BTN_CNT = 5;
	static final int BTN_SIZE = 40;
	
	GamePanel(){
		setLayout(null);
		addMouseListener(this);
		addMouseMotionListener(this);
		
		Font font = new Font("NanumGothic", Font.CENTER_BASELINE, 15);
		game_button = new JButton("게임시작");
		game_button.setFont(font);
		game_button.setBackground(Color.pink);
		game_button.setForeground(Color.white);
		game_button.setBounds(5, 10, 93, 30);
		game_button.addActionListener(this);
		add(game_button);
		
		ranking_button = new JButton("랭킹화면");
		ranking_button.setFont(font);
		ranking_button.setBackground(Color.pink);
		ranking_button.setForeground(Color.white);
		ranking_button.setBounds(100, 10, 93, 30);
		ranking_button.addActionListener(this);
		add(ranking_button);
		
		chat_button = new JButton("채팅");
		font = new Font("NanumGothic", Font.BOLD, 20);
		chat_button.setFont(font);
		chat_button.setBackground(Color.pink);
		chat_button.setForeground(Color.white);
		chat_button.setBounds(195, 10, 93, 30);
		chat_button.addActionListener(this);
		add(chat_button);
		
		back_main = new JButton("로그아웃");
		font = new Font("NanumGothic", Font.CENTER_BASELINE, 15);
		back_main.setFont(font);
		back_main.setBackground(Color.pink);
		back_main.setForeground(Color.WHITE);
		back_main.setBounds(290, 10, 93, 30);
		back_main.addActionListener(this);
		add(back_main);
		
	
		font = new Font("NanumGothic", Font.BOLD, 20);
		JLabel title = new JLabel("1 to 50");
		title.setFont(font);
		title.setBounds(40, 50, 80, 30);
		add(title);
		
		font = new Font("NanumGothic", Font.PLAIN, 12);
		JLabel explan = new JLabel("1부터 50까지 순서대로 클릭하세요");
		explan.setFont(font);
		explan.setForeground(Color.GRAY);
		explan.setBounds(40, 80, 200, 15);
		add(explan);
		
		
		JLabel action = new JLabel("게임을 누르면 시작합니다");
		action.setFont(font);
		action.setForeground(Color.GRAY);
		action.setBounds(40, 305, 200, 15);
		add(action);
		
		font = new Font("NanumGothicSSS", Font.BOLD, 20);
		timeTextLb = new JLabel("00:00:00");
		timeTextLb.setFont(font);
		timeTextLb.setBounds(270, 100, 130, 20);
		add(timeTextLb);
		
		setGamePanel();
	}
	
	void setGamePanel() { //상자크기설정
		buttons = new GameRact[BTN_CNT][BTN_CNT];
		for(int i = 0; i<BTN_CNT; i++) {
			for(int j = 0; j<BTN_CNT; j++) {
				buttons[i][j] = new GameRact();
				buttons[i][j].size = BTN_SIZE;
				buttons[i][j].x = 40+ j * BTN_SIZE;
				buttons[i][j].y = 100+i*BTN_SIZE;
				buttons[i][j].color = Color.WHITE;
			}
		}
	}
	
	void shuffle() { //상자 숫자
		Random ran = new Random();
		backGame = new int[BTN_CNT][BTN_CNT];
		Font font = new Font("Gothic", Font.BOLD, 10);
		int a = 1;
		for(int i = 0; i<BTN_CNT; i++) {
			for(int j = 0; j<BTN_CNT; j++) {
				buttons[i][j].text = a+"";
				backGame[i][j] = a+25;
				a++;
			}
		}
		
		for(int i = 0; i<1000; i++) {
			int x = ran.nextInt(BTN_CNT);
			int y = ran.nextInt(BTN_CNT);
			String temp = buttons[y][x].text;
			buttons[y][x].text = buttons[0][0].text;
			buttons[0][0].text = temp;
			
			x = ran.nextInt(BTN_CNT);
			y = ran.nextInt(BTN_CNT);
			int temp2 = backGame[y][x];
			backGame[y][x] = backGame[0][0];
			backGame[0][0] = temp2;
		}
	}
	
	void result() {
		long time = StopWatch.time;
		String timeText = timeTextLb.getText();
		
		RankingInfo temp = new RankingInfo();
		temp.setName(LoginPanel.loginUser.getNickname());
		temp.setTime(time);
		temp.setTimeText(timeText);
		FileManager.instance.checkRank(temp);
		
		JOptionPane.showMessageDialog(null, timeText+"초",
				"게임클리어", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(time);
	}
	
	@Override
	protected void paintComponent(Graphics g) { //상자 그리기
		// TODO Auto-generated method stub
		super.paintComponent(g);
		try {
			Thread.sleep(10);
			repaint();
		}catch(Exception e) {}
		for(int i = 0; i<BTN_CNT; i++) {
			for(int j =0; j<BTN_CNT; j++) {
				g.setColor(buttons[i][j].color);
				g.fillRect(buttons[i][j].x, buttons[i][j].y,
						buttons[i][j].size, buttons[i][j].size);
				g.setColor(Color.black);
				g.drawRect(buttons[i][j].x, buttons[i][j].y,
						buttons[i][j].size, buttons[i][j].size);
			}
		}
		if(start) {
			Font font = new Font("Gothic", Font.BOLD, 10);
			g.setFont(font);
			for(int i = 0; i<BTN_CNT; i++) {
				for(int j = 0; j<BTN_CNT; j++) {
					g.drawString(buttons[i][j].text,
						buttons[i][j].x + (buttons[i][j].size/2 - font.getSize()/2),
						buttons[i][j].y + buttons[i][j].size/2);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == back_main) {
			if(start) {
				isStopWatch = false;
			}
			LoginPanel.loginUser = null;
			_MainSystem.frame.setContentPane(new LoginPanel());
			_MainSystem.frame.revalidate();
		}
		else if(e.getSource() == ranking_button) {
			_MainSystem.frame.setContentPane(new RankingPanel());
			_MainSystem.frame.revalidate();
			if(start) {
				isStopWatch = false;
			}
		}
		else if(e.getSource() == game_button) {
			if(!start) {
				start = true;
				isStopWatch = true;
				StopWatch.preTime = System.currentTimeMillis();
				count= 1;
				shuffle();
			}else {
				start = false;
				isStopWatch = false;
				timeTextLb.setText("00:00:00");
			}
			
		}
		
		else if(e.getSource() == chat_button) {
			
			JFrame frame = new JFrame();
			frame.setSize(500, 500);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocation(600, 200);
			frame.setTitle("채팅");
			Client client = new Client();
			ClientPanel panel = new ClientPanel();
			client.setClientPanel(panel);
			panel.setClient(client);
			frame.setContentPane(panel);
			frame.setVisible(true);			
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(start) {
			int x = e.getX();
			int y = e.getY();
			for(int i = 0; i<BTN_CNT; i++) {
				for(int j = 0; j<BTN_CNT; j++) {
					if(buttons[i][j].x <= x && x <= buttons[i][j].x+buttons[i][j].size
							&& buttons[i][j].y <= y && y <=buttons[i][j].y +buttons[i][j].size) {
						if(buttons[i][j].text.equals(count+"")) {
							buttons[i][j].text = backGame[i][j]+"";
							backGame[i][j] = 0;
							count++;
							if(count == 51) {
								buttons[i][j].color = Color.white;
							}
						}
					}
				}
			}
			if(count == 51) {
				start = false;
				isStopWatch = false;
				result();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(start) {
			int x = e.getX();
			int y = e.getY();
			for(int i = 0; i<BTN_CNT; i++) {
				for(int j = 0; j<BTN_CNT; j++) {
					if(buttons[i][j].x <= x && x < buttons[i][j].x+buttons[i][j].size
							&& buttons[i][j].y <= y && y <buttons[i][j].y +buttons[i][j].size) {
						buttons[i][j].color = Color.LIGHT_GRAY;
					}else {
						buttons[i][j].color = Color.white;
					}
				}
			}
		}
		
	}

}
