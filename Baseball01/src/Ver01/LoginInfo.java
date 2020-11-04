package Ver01;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	// 회원정보 
	private String id;      	// 아이디
	private String pw;       	// 비밀번호
	private int myMoney;		// 보유충전금액
	private int point; 			// 포인트

	public LoginInfo(String id, String pw, int myMoney, int point) { 
		this.id = id;
		this.pw = pw;
		this.myMoney = myMoney;
		this.point = point;
	}

	// 생성자로 초기화
	public LoginInfo(String id, String pw) { 
		this.id = id;
		this.pw = pw;
	}		
	// 생성자 오버라이딩
	public LoginInfo() {	
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point += point;
	}
	public int getMyMoney() {
		return myMoney;
	}
	public void setMyMoney(int myMoney) {
		this.myMoney = myMoney;
	}

}