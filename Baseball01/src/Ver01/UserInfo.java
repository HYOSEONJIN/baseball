package Ver01;

public class UserInfo implements LoginInfo{

	// 사용자 로그인 정보 

	private String id;      	// 사용자 아이디
	private int pw;       		// 사용자 비밀번호

	public UserInfo(String id, int pw) {
		this.id = id;
		this.pw = pw;
	}
	

	public String getId() {
		return id;
	}
	public int getPw() {
		return pw;
	}


	// 정보 출력 
	void showLoginInfo() {
		System.out.println("아이디 : "+ id);
		System.out.println("비밀번호 : "+ pw);	
	}
}
