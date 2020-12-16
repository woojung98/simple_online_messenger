package rank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel implements ActionListener{
	
	JButton find_button = null;
	JButton login_button = null;
	JButton join_button = null;
	
	JTextField id_tf = null;
	JPasswordField pw_pf = null;
	
	static UserInfo loginUser;
	
	LoginPanel(){
		setLayout(null);
		
		// 로그인
		Font font = new Font("NanumGothic", Font.BOLD, 50);
		JLabel label = new JLabel("로그인");
		label.setFont(font);
		label.setForeground(Color.gray);
		label.setOpaque(true);
		label.setBounds(115, 60, 160, 80);
		add(label);
		
		font = new Font("NanumGothic", Font.BOLD, 15);
		
		// ID
		JLabel id = new JLabel(" ID : ");
		id.setBackground(Color.lightGray);
		id.setOpaque(true);
		id.setFont(font);
		id.setBounds(35, 190, 50, 40);
		add(id);
		
		// 비밀번호
		JLabel pw = new JLabel(" PW : ");
		pw.setBackground(Color.lightGray);
		pw.setOpaque(true);
		pw.setFont(font);
		pw.setBounds(35, 240, 50, 40);
		add(pw);
		
		// 로그인 버튼 
		login_button = new JButton("로그인");
		login_button.setBackground(Color.gray);
		login_button.setForeground(Color.white); //글씨색
		login_button.setFont(font);
		login_button.setBounds(275, 180, 80, 100);
		login_button.addActionListener(this);
		add(login_button);
		
		// 회원가입 버튼
		join_button = new JButton("회원가입");
		join_button.setBackground(Color.pink);
		join_button.setForeground(Color.WHITE);
		join_button.setFont(font);
		join_button.setBounds(73, 320, 130, 30);
		join_button.addActionListener(this);
		add(join_button);
		
		// ID/PW 찾기 버튼
		find_button = new JButton("ID/PW 찾기");
		find_button.setBackground(Color.pink);
		find_button.setForeground(Color.WHITE);
		find_button.setFont(font);
		find_button.setBounds(213, 320, 120, 30);
		find_button.addActionListener(this);
		add(find_button);
		
		// id 입력 칸
		id_tf = new JTextField(5); //다섯글자 집어넣으면 딱 맞는 크기(레이아웃 있을때 한정)
		id_tf.setBounds(95, 195, 170, 30);
		id_tf.addActionListener(this);
		add(id_tf);
		
		// password 입력 칸
		pw_pf = new JPasswordField(5);
		pw_pf.setBounds(95, 245, 170, 30);
		add(pw_pf);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == find_button) {
			_MainSystem.frame.setContentPane(new FindPanel());
			_MainSystem.frame.revalidate();
		}
		else if(e.getSource() == join_button) {
			_MainSystem.frame.setContentPane(new JoinPanel());
			_MainSystem.frame.revalidate();
		}
		else if(e.getSource() == login_button) {
			UserInfo log = FileManager.instance.login(id_tf.getText(),
					pw_pf.getText());
			if(log == null) {
				JOptionPane.showMessageDialog(null, "id와 pw를 확인하세요",
						"로그인 실패", JOptionPane.WARNING_MESSAGE);
			}else {
				loginUser = log;
				_MainSystem.frame.setContentPane(new GamePanel());
				_MainSystem.frame.revalidate();
			}
		}
	}

}
