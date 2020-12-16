package rank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Size{
	int x;
	int y;
	int w;
	int h;
}
class RankRact{
	Size s1 = new Size();
	Size s2 = new Size();
	Size s3 = new Size();
}

public class RankingPanel extends JPanel implements ActionListener, MouseListener{
	
	JButton back_game;
	JButton back_main;
	RankRact[] box = new RankRact[6];
	
	RankingPanel(){
		
		setLayout(null);
		addMouseListener(this);
		
		Font font = new Font("NanumGothic", Font.BOLD, 15);
		back_game = new JButton("게임화면");
		back_game.setBackground(Color.pink);
		back_game.setForeground(Color.white);
		back_game.setBounds(73, 355, 120, 30);
		back_game.setFont(font);
		back_game.addActionListener(this);
		add(back_game);
		
		back_main = new JButton("로그아웃");
		back_main.setBackground(Color.pink);
		back_main.setForeground(Color.white);
		back_main.setBounds(213, 355, 120, 30);
		back_main.setFont(font);
		back_main.addActionListener(this);
		add(back_main);
		
		font = new Font("NanumGothic", Font.BOLD, 25);
		JLabel title = new JLabel("Ranking");
		title.setFont(font);
		title.setBounds(40, 25, 100, 40);
		add(title);
		
		font = new Font("NanumGothic", Font.PLAIN, 12);
		JLabel explan = new JLabel("1~5위까지의 순위를 보여드립니다");
		explan.setFont(font);
		explan.setForeground(Color.GRAY);
		explan.setBounds(43, 60, 200, 30);
		add(explan);
		
		
		font = new Font("NanumGothic", Font.BOLD, 15);
		JLabel rank = new JLabel("순위");
		rank.setFont(font);
		rank.setBounds(52, 101, 35, 25);
		add(rank);
		
		JLabel name = new JLabel("이름");
		name.setFont(font);
		name.setBounds(132, 101, 40, 25);
		add(name);
		
		JLabel record = new JLabel("시간");
		record.setFont(font);
		record.setBounds(277, 101, 40, 25);
		add(record);
		
		setLine();
	}
	void setLine() {
		for(int i = 0; i<box.length; i++) {
			box[i] = new RankRact();
			
			box[i].s1.x = 38;
			box[i].s1.y = 95+(i*40);
			box[i].s1.w = 50;
			box[i].s1.h = 40;
			
			box[i].s2.x = 88;
			box[i].s2.y = 95+(i*40);
			box[i].s2.w = 135;
			box[i].s2.h = 40;
			
			box[i].s3.x = 223;
			box[i].s3.y = 95+(i*40);
			box[i].s3.w = 135;
			box[i].s3.h = 40;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Font font = new Font("NanumGothic", Font.BOLD, 20);
		g.setFont(font);
		for(int i = 0; i<box.length; i++) {
			g.drawRect(box[i].s1.x, box[i].s1.y, box[i].s1.w, box[i].s1.h);
			g.drawRect(box[i].s2.x, box[i].s2.y, box[i].s2.w, box[i].s2.h);
			g.drawRect(box[i].s3.x, box[i].s3.y, box[i].s3.w, box[i].s3.h);
			if(i!= 0) {
				g.drawString(i+"", box[i].s1.x+17, box[i].s1.y+27);
			}
		}
		
		font = new Font("NanumGothic", Font.PLAIN, 15);
		g.setFont(font);
		int size = FileManager.instance.rankManager.size();
		for(int i = 0; i<size; i++) {
			String name = FileManager.instance.rankManager.get(i).getName();
			g.drawString(name, box[i+1].s2.x+20, box[i+1].s2.y+23);
			String time = FileManager.instance.rankManager.get(i).getTimeText();
			g.drawString(time, box[i+1].s3.x+30, box[i+1].s3.y+23);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == back_game) {
			_MainSystem.frame.setContentPane(new GamePanel());
			_MainSystem.frame.revalidate();
		}else if(e.getSource() == back_main) {
			LoginPanel.loginUser = null;
			_MainSystem.frame.setContentPane(new LoginPanel());
			_MainSystem.frame.revalidate();
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
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
