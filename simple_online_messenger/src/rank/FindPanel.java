package rank;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FindPanel extends JPanel implements ActionListener{
	
	JButton find_pw_button;
	JButton back_main;
	JButton find_id_button;
	JTextField name_id_tf = new JTextField();
	JTextField email_id_tf = new JTextField();
	
	JTextField name_pw_tf = new JTextField();
	JTextField email_pw_tf = new JTextField();
	JTextField id_pw_tf = new JTextField();
	
	FindPanel(){
		setLayout(null);
		
		Font font = new Font("NanumGothic", Font.BOLD, 25);
		JLabel label = new JLabel("ID 찾기");
		label.setForeground(Color.gray);
		label.setFont(font);
		label.setBounds(165, 20, 95, 30);
		add(label);
		
		font = new Font("NanumGothic", Font.BOLD, 15);
		JLabel name1 = new JLabel(" 이름 : ");
		name1.setBackground(Color.lightGray);
		name1.setOpaque(true);
		name1.setFont(font);
		name1.setBounds(35, 50, 95, 30);
		add(name1);
		
		JLabel email1 = new JLabel(" email 주소 : ");
		email1.setBackground(Color.lightGray);
		email1.setOpaque(true);
		email1.setFont(font);
		email1.setBounds(35, 80, 95, 30);
		add(email1);
		
		name_id_tf.setBounds(135, 50, 200, 30);
		email_id_tf.setBounds(135, 80, 200, 30);
		add(name_id_tf);
		add(email_id_tf);
		
		find_id_button = new JButton("ID 찾기");
		find_id_button.setBackground(Color.pink);
		find_id_button.setFont(font);
		find_id_button.setForeground(Color.white);
		find_id_button.setBounds(135, 120, 120, 30);
		find_id_button.addActionListener(this);
		add(find_id_button);
		
		font = new Font("NanumGothic", Font.BOLD, 25);
		JLabel label2 = new JLabel("PW 찾기");
		label2.setFont(font);
		label2.setForeground(Color.gray);
		label2.setBounds(165, 200, 100, 30);
		add(label2);
		
		font = new Font("NanumGothic", Font.BOLD, 15);
		JLabel id = new JLabel(" ID : ");
		id.setBackground(Color.lightGray);
		id.setOpaque(true);
		id.setFont(font);
		id.setBounds(35, 230, 95, 30);
		add(id);
		
		JLabel name2 = new JLabel(" 이름 : ");
		name2.setBackground(Color.lightGray);
		name2.setOpaque(true);
		name2.setFont(font);
		name2.setBounds(35, 260, 95, 30);
		add(name2);
		
		JLabel email2 = new JLabel(" email 주소 : ");
		email2.setBackground(Color.lightGray);
		email2.setOpaque(true);
		email2.setFont(font);
		email2.setBounds(35, 290, 95, 30);
		add(email2);
		
		id_pw_tf.setBounds(135, 230, 200, 30);
		name_pw_tf.setBounds(135, 260, 200, 30);
		email_pw_tf.setBounds(135, 290, 200, 30);
		add(id_pw_tf);
		add(name_pw_tf);
		add(email_pw_tf);
		
		find_pw_button = new JButton("PW 찾기");
		find_pw_button.setBackground(Color.pink);
		find_pw_button.setFont(font);
		find_pw_button.setForeground(Color.WHITE);
		find_pw_button.setBounds(135, 330, 120, 30);
		find_pw_button.addActionListener(this);
		add(find_pw_button);
		
		back_main = new JButton("처음화면");
		back_main.setBackground(Color.gray);
		back_main.setFont(font);
		back_main.setForeground(Color.white);
		back_main.setBounds(135, 400, 120, 30);
		back_main.addActionListener(this);
		add(back_main);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == back_main) {
			_MainSystem.frame.setContentPane(new LoginPanel());
			_MainSystem.frame.revalidate();
		}else if(e.getSource() == find_id_button) {
			String id = FileManager.instance.findID(name_id_tf.getText(),
								email_id_tf.getText());
			if(id == "") {
				JOptionPane.showMessageDialog(null, "존재하지 않는 정보입니다",
						"아이디 찾기", JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "아이디: " +id,
						"아이디 찾기", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource() == find_pw_button) {
			String pw = FileManager.instance.findPW(id_pw_tf.getText(),
					name_pw_tf.getText(), email_pw_tf.getText());
			if(pw == "") {
				JOptionPane.showMessageDialog(null, "존재하지 않는 정보입니다",
						"비밀번호 찾기", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
