package Ver01;

import java.util.List;

public class BaseballMain {

	public static void main(String[] args) {
		// 예매 메인
		
		// 로그인,예매, 회원정보 조회를 위한 인스턴스 생성
		List<UserInfo> userinfo = UserInfoManager.getInstance();
		AdminManager admin = new AdminManager();
		PointZoneManager pointZone = new PointZoneManager();
		
		// 메인 메뉴 만들기 - 메뉴 부분 인터페이스 차후 수정
		while(true) {
			System.out.println("********2020 포스트시즌 야구 예매********");
			System.out.println("1.로그인");
			System.out.println("2.예매하기");
			System.out.println("3.회원정보보기");
			System.out.println("4.이벤트페이지");
			System.out.println("5.페이지 닫기");
			System.out.println("*********************************");
			
			int select = Util.sc.nextInt();
			
			switch(select) {
			case 1: 
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			}
		}
		
		
		
		
		
		

	}

}
