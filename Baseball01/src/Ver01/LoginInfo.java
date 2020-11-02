package Ver01;

public class LoginInfo {

	// 사용자 로그인 정보 
	private String id;      	// 아이디
	private String pw;       	// 비밀번호

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

	
	// 정보 출력 
	public void showLoginInfo() {
		System.out.println("아 이 디  : "+ id);
		System.out.println("비밀번호 : "+ pw);	
	}
}
