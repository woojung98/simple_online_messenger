package rank;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JoinPanel extends JPanel implements ActionListener{
	
	JTextField name_tf;
	JTextField nickname_tf;
	JTextField age_tf;
	JTextField email_tf;
	JTextField id_tf;
	JTextField pw_tf;
	
	JButton join_membership_button;
	JButton back_button;
	
	JButton check_nickname;
	JButton check_id;
	boolean check_n;
	boolean check_i;
	
	JoinPanel(){
		setLayout(null);
		
		// 회원가입
		Font font = new Font("NanumGothic",Font.BOLD, 45);
		JLabel label = new JLabel("회원가입");
		label.setForeground(Color.gray);
		label.setFont(font);
		label.setBounds(110,40,250,70);
		add(label);
		
		// 이름
		font = new Font("NanumGothic", Font.BOLD, 14);
		JLabel name = new JLabel(" 이름 : ");
		name.setBackground(Color.lightGray);
		name.setOpaque(true);
		name.setFont(font);
		name.setBounds(18, 130, 90, 30);
		add(name);
		
		// 별칭
		JLabel nickname = new JLabel(" 닉네임 : ");
		nickname.setBackground(Color.lightGray);
		nickname.setOpaque(true);
		nickname.setFont(font);
		nickname.setBounds(18, 160, 90, 30);
		add(nickname);
		
		// 생년월일
		JLabel age = new JLabel(" 생년월일 : ");
		age.setBackground(Color.lightGray);
		age.setOpaque(true);
		age.setFont(font);
		age.setBounds(18, 190, 90, 30);
		add(age);
		
		// 이메일 주소
		JLabel email = new JLabel(" email 주소 : ");
		email.setBackground(Color.lightGray);
		email.setOpaque(true);
		email.setFont(font);
		email.setBounds(18, 220, 90, 30);
		add(email);
		
		// ID
		JLabel id = new JLabel(" ID : ");
		id.setBackground(Color.lightGray);
		id.setOpaque(true);
		id.setFont(font);
		id.setBounds(18, 250, 90, 30);
		add(id);
		
		// password
		JLabel pw = new JLabel(" PW : ");
		pw.setBackground(Color.lightGray);
		pw.setOpaque(true);
		pw.setFont(font);
		pw.setBounds(18, 280, 90, 30);
		add(pw);
		
		// 이름 입력 칸 설정
		name_tf = new JTextField();
		name_tf.setBounds(110, 130, 200, 30);
		name_tf.addActionListener(this);
		add(name_tf);

		// 별칭 입력 칸 설정
		nickname_tf = new JTextField();
		nickname_tf.setBounds(110, 160, 200, 30);
		nickname_tf.addActionListener(this);
		add(nickname_tf);

		// 생년월일 입력 칸 설정
		age_tf = new JTextField();
		age_tf.setBounds(110, 190, 200, 30);
		age_tf.addActionListener(this);
		add(age_tf);

		// email 주소 입력 칸 설정
		email_tf = new JTextField();
		email_tf.setBounds(110, 220, 200, 30);
		email_tf.addActionListener(this);
		add(email_tf);
		
		// ID 입력 칸 설정
		id_tf = new JTextField();
		id_tf.setBounds(110, 250, 200, 30);
		id_tf.addActionListener(this);
		add(id_tf);

		// password 입력 칸 설정
		pw_tf = new JTextField();
		pw_tf.setBounds(110, 280, 200, 30);
		pw_tf.addActionListener(this);
		add(pw_tf);
		
		// 별칭 중복 체크 버튼
		check_nickname = new JButton("체크");
		check_nickname.setBackground(Color.gray);
		check_nickname.setForeground(Color.white);
		check_nickname.setBounds(310, 160, 60, 30);
		check_nickname.addActionListener(this);
		add(check_nickname);
		
		// ID 중복 체크 버튼
		check_id = new JButton("체크");
		check_id.setBackground(Color.gray);
		check_id.setForeground(Color.white);
		check_id.setBounds(310, 250, 60, 30);
		check_id.addActionListener(this);
		add(check_id);
		
		// 가입완료 버튼
		join_membership_button = new JButton("가입");
		join_membership_button.setBackground(Color.pink);
		join_membership_button.setForeground(Color.white);
		join_membership_button.setBounds(73, 330, 120, 30);
		join_membership_button.addActionListener(this);
		join_membership_button.setFont(font);
		add(join_membership_button);
		
		// 처음화면 버튼
		back_button = new JButton("처음화면");
		back_button.setBackground(Color.pink);
		back_button.setForeground(Color.white);
		back_button.setBounds(213, 330, 120, 30);
		back_button.setFont(font);
		back_button.addActionListener(this);
		add(back_button);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() ==  back_button) {						// 처음화면 버튼 눌렀을 때
			_MainSystem.frame.setContentPane(new LoginPanel());
			_MainSystem.frame.revalidate();
		}
		else if(e.getSource() == check_nickname) { 	// 별칭 중복 체크 버튼 눌렀을 때
			check_n = FileManager.instance.checkNickname(name_tf.getText());
			if(check_n) {		// 중복x
				JOptionPane.showMessageDialog(null, "사용가능한 닉네임입니다",
						"닉네임 중복확인", JOptionPane.INFORMATION_MESSAGE);
			}else {				// 중복o
				JOptionPane.showMessageDialog(null, "중복된 닉네임입니다",
						"닉네임 중복확인", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == check_id) {		// ID 중복 체크 버튼 눌렀을 때
			check_i = FileManager.instance.checkId(id_tf.getText());
			if(check_i) {		// 중복x
				JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다",
						"아이디 중복확인", JOptionPane.INFORMATION_MESSAGE);
			}else {				// 중복o
				JOptionPane.showMessageDialog(null, "중복된 아이디입니다",
						"아이디 중복확인", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getSource() == join_membership_button) {		// 가입완료 버튼 눌렀을 때
			if(name_tf.getText().equals("") ||			
					nickname_tf.getText().equals("") ||	   // 빈 칸이 하나라도 있는 경우
					age_tf.getText().equals("") ||
					email_tf.getText().equals("") ||
					id_tf.getText().equals("") ||
					pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 칸을 채워주세요",
						"빈 공간 존재", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!check_n || !check_i) {					  // 별칭이나 ID가 중복일 경우
				JOptionPane.showMessageDialog(null, "중복 확인을 해주세요",
						"중복 확인 미실행", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			UserInfo user = new UserInfo();				  // 칸이 다 채워지고 중복도 없는 경우, 사용자 정보 저장
			user.setName(name_tf.getText());		
			user.setNickname(nickname_tf.getText());
			user.setAge(Integer.parseInt(age_tf.getText()));
			user.setEmail(email_tf.getText());
			user.setId(id_tf.getText());
			user.setPw(pw_tf.getText());
			FileManager.instance.addUser(user);
			JOptionPane.showMessageDialog(null, "가입이 완료되었습니다", "회원가입 성공",
					JOptionPane.INFORMATION_MESSAGE);
			_MainSystem.frame.setContentPane(new LoginPanel());
			_MainSystem.frame.setVisible(true);
			
		}
		
	}
}