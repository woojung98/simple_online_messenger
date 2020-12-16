package rank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {

	File file = null; //파일 존재 체크 여부
	FileWriter fout = null; //쓰기
	FileReader fr = null;	//읽기
	BufferedReader br = null;//한줄씩 읽기
	final String userPath = "userData.txt"; //경로
	final String rankPath = "rankData.txt";
	String userData = "";
	String rankData = "";
	ArrayList<UserInfo> userManager = null;
	ArrayList<RankingInfo> rankManager = null;
	boolean rankCount = false;
	
	public static FileManager instance = new FileManager();
	
	public FileManager() {
		userManager = new ArrayList<>();
		rankManager = new ArrayList<>();
	}
	
	public void loadData() {
		file = new File(userPath);
		if(!file.exists()) return;
		try {
			fr = new FileReader(userPath);
			br = new BufferedReader(fr);
			userData = "";
			while(true) {
				String userTxt = br.readLine();
				if(userTxt == null) {
					break;
				}
				userData += userTxt;
				userData += "\n";
				
				loadUser(userTxt);
			}
			System.out.println("[User]");
			System.out.println(userData);
			System.out.println("-----------------");
			fr.close();
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		file = new File(rankPath);
		if(!file.exists()) {return;}
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			rankData = "";
			while(true) {
				String rankTxt = br.readLine();
				if(rankTxt == null) {
					break;
				}
				rankData += rankTxt;
				rankData += "\n";
				loadRank(rankTxt);
				rankCount = true;
			}
			System.out.println("[Ranking]");
			System.out.println(rankData);
			System.out.println("-----------------");
			fr.close();
			br.close();
		}catch(Exception e) {}
	}
	
	void loadUser(String userTxt) {
		String userinfo[] = userTxt.split("/");
		
		UserInfo temp = new UserInfo();
		temp.setName(userinfo[0]);
		temp.setNickname(userinfo[1]);
		temp.setAge(Integer.parseInt(userinfo[2]));
		temp.setEmail(userinfo[3]);
		temp.setId(userinfo[4]);
		temp.setPw(userinfo[5]);
		userManager.add(temp);
	}
	
	void loadRank(String rankTxt) {
		String rankInfo[] = rankTxt.split("/");
		RankingInfo temp = new RankingInfo();
		temp.setName(rankInfo[0]);
		temp.setTime(Long.parseLong(rankInfo[1]));
		temp.setTimeText(rankInfo[2]);
		rankManager.add(temp);
	}
	
	public void printAllUser() {
		for(int i = 0; i<userManager.size(); i++) {
			System.out.print(userManager.get(i).getNickname()+" ");
			System.out.print(userManager.get(i).getNickname()+" ");
			System.out.print(userManager.get(i).getAge()+" ");
			System.out.print(userManager.get(i).getEmail()+" ");
			System.out.print(userManager.get(i).getId()+" ");
			System.out.println(userManager.get(i).getPw());
		}
	}
	
	void printAllRanking() {
		for(int i = 0; i<rankManager.size(); i++) {
			System.out.print(i+1+" ");
			System.out.print(rankManager.get(i).getName()+" ");
			System.out.print(rankManager.get(i).getTime()+" ");
			System.out.println(rankManager.get(i).getTimeText());
		}
	}
	
	String findID(String name, String email) {
		String id = "";
		for(int i = 0; i<userManager.size(); i++) {
			if(name.equals(userManager.get(i).getNickname()) &&
					email.equals(userManager.get(i).getId())) {
				id = userManager.get(i).getId();
				break;
			}
		}
		return id;
	}
	
	String findPW(String id, String name, String email) {
		String pw = "";
		for(int i = 0; i<userManager.size(); i++) {
			if(id.equals(userManager.get(i).getId()) &&
					name.equals(userManager.get(i).getNickname()) &&
					email.equals(userManager.get(i).getPw())) {
				pw = userManager.get(i).getPw();
				break;
			}
		}
		return pw;
	}
	
	void addUser(UserInfo user) {
		userManager.add(user);
		addUserData(user);
		saveData();
	}
	
	private void addUserData(UserInfo user) {
		int lastIndex = userManager.size()-1;
		UserInfo temp = userManager.get(lastIndex);
		userData += temp.getName();
		userData += "/";
		userData += temp.getNickname();
		userData += "/";
		userData += temp.getAge();
		userData += "/";
		userData += temp.getEmail();
		userData += "/";
		userData += temp.getId();
		userData += "/";
		userData += temp.getPw();
		userData += "\n";
		System.out.println("== save ==\n"+userData);
	}
	
	private void saveData() {
		try {
			fout = new FileWriter(userPath);
			fout.write(userData);
			fout.close();
			fout = new FileWriter(rankPath);
			fout.write(rankData);
			fout.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	UserInfo login(String id, String pw) {
		UserInfo check_id = null;
		for(int i = 0; i<userManager.size(); i++) {
			if(id.equals(userManager.get(i).getId()) &&
					pw.equals(userManager.get(i).getPw())) {
				check_id = userManager.get(i);
				break;
			}
		}
		return check_id;
	}
	
	void checkRank(RankingInfo rank) {
		int index = -1;
		if(!rankCount) {
			rankManager.add(rank);
			rankCount = true;
		}else {
			for(int i = 0; i<rankManager.size(); i++) {
				if(rank.getTime() < rankManager.get(i).getTime()) {
					index = i;
					break;
				}
			}
			if(index != -1) {
				rankManager.add(index, rank);
				if(rankManager.size()>5) {
					for(int i = rankManager.size()-1; i>4; i--) {
						rankManager.remove(i);
					}
				}
			}
		}
		addRankData();
	}
	
	void addRankData() {
		rankData = "";
		for(int i = 0; i<rankManager.size(); i++) {
			rankData += rankManager.get(i).getName();
			rankData += "/";
			rankData += rankManager.get(i).getTime();
			rankData += "/";
			rankData += rankManager.get(i).getTimeText();
			rankData += "\n";
		}
		System.out.println(rankData);
		saveData();
	}
	
	boolean checkNickname(String nickName) {
		boolean check = true;
		for(int i = 0; i<userManager.size(); i++) {
			if(userManager.get(i).getNickname().equals(nickName)) {
				check = false;
				break;
			}
		}
		return check;
	}

	boolean checkId(String id) {
		boolean check = true;;
		for(int i = 0; i<userManager.size(); i++) {
			if(userManager.get(i).getId().equals(id)) {
				check = false;
				break;
			}
		}
		return check;
	}
	
}
