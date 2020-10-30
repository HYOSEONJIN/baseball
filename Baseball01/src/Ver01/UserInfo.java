package Ver01;

public class UserInfo {

	// 기본 회원정보 

	private String id;      	// 사용자 아이디
	private int pw;       		// 사용자 비밀번호
	private int myMoney = 0;    // 충전된 금액 
	private int point = 0;      // 보유 포인트


	// 정보 출력 기능
	void showUserInfo() {
		System.out.println(id +"님의 회원 정보입니다.");
		System.out.println("=====================");
		System.out.println("아이디 : "+ id);
		System.out.println("비밀번호 : "+ pw);
		System.out.println("충전 금액: "+ myMoney);
		System.out.println("보유 포인트 : "+ point);
		System.out.println("=====================");
		
	}
}
