package Ver01;

public class LoginInfo {

	// 사용자 로그인 정보 
	private String id;      	// 아이디
	private String pw;       	// 비밀번호
	private int point; 			// 포인트 추가
	private int money;			// 보유충전금액 추가

	public LoginInfo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public LoginInfo() { // 생성자 오버라이딩
		
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

	public void setPoint(int point) {
		this.point = point;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	// 정보 출력 
	public void showLoginInfo() {
		System.out.println("아 이 디  : "+ id);
		System.out.println("비밀번호 : "+ pw);	
	}
}
