package Ver01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LoginInfo implements Serializable {

	// 사용자 로그인 정보 
	private String id;      	// 아이디
	private String pw;       	// 비밀번호
	private int money;			// 보유충전금액
	private int point; 			// 포인트

	public LoginInfo(String id, String pw, int money, int point) {
		this.id = id;
		this.pw = pw;
		this.money = money;
		this.point = point;
	}
	
	public LoginInfo(String id, String pw) { // 생성자 오버라이딩
		
	}
	
	public LoginInfo() {
		
	}
	
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}

	
	public int getPoint() {
		return point;
	}

	public int getMoney() {
		return money;
	}

	// 정보 출력 
	public void showLoginInfo() {
		System.out.println("아 이 디  : "+ id);
		System.out.println("비밀번호 : "+ pw);	
		System.out.println("보유금액 : "+ money);
		System.out.println("포 인 트  : "+ point);
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		// 인스턴스 저장을 위한 스트림 생성
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("LoginInfo.ser"));
		
		LoginInfo info = new LoginInfo();
		
		out.writeObject(info);
		out.close();
		
		// 인스턴스 복원을 위한 스트림 생성
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("LoginInfo.ser"));
		
		// 복원
		LoginInfo reInfo = (LoginInfo) in.readObject();
		
		// 복원된 인스턴스의 정보 출력
		reInfo.showLoginInfo();
		
	}

}
