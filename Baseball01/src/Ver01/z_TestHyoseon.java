package Ver01;

import java.io.IOException;
import java.util.InputMismatchException;

public class z_TestHyoseon {

	public static void main(String[] args) throws IOException {
			// 나중에 삭제할 예정
		
		
		
		PointZoneManager pzm = new PointZoneManager();
		String testName = "hyoseon";
		
		while(true) {
		System.out.println("1. 숫자게임");
		System.out.println("2. 랜덤뽑기");
		System.out.println("3. 굿즈구매");
		System.out.println("4. 포인트 사용 내역 조회");
		System.out.println("5. 종료");
		
		
		
		int choice = Util.sc.nextInt();
		Util.sc.nextLine();
		
		switch(choice) {
		case 1:
			pzm.pointGame1();
			break;
		case 2:
			pzm.pointGame2();
			break;
		case 3:
			pzm.buyGoods();
			break;
		case 4:
			pzm.pointHistoryInfo(testName);
			break;
		case 5:
			System.out.println("메인으로 돌아갑니다.");
			return;
			
		} 
		
		
		
		}

		}	
	}


