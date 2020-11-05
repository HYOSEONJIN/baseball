package Ver01;

import java.awt.List;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingManager extends LoginInfoManager {
	ReserveSeat reserve = new ReserveSeat();
	LoginInfoManager loginManager = new LoginInfoManager();

	public void Bookingmain() throws IOException, ClassNotFoundException { 
		// 로그인 체크 
		if(NOWID == null) {
			System.out.println("로그인 해주세요.");
			return;
		}
		
		while (true) {

			System.out.println("\n ■■■■■■■■ RESERVATION ■■■■■■■■\n");
			System.out.println("\t" + RECHARGE + ".충전하기");
			System.out.println("\t"+ BOOKINGNOW + ".예매하기");
			System.out.println("\t" + CANCEL + ".예매취소");
			System.out.println("\t" + BOOKINGINFO + ".결제정보");
			System.out.println("\t"+ EXIT2 + ".홈 메뉴로 돌아가기");
			System.out.println("\n ■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("보유금액:" + loginInfo.get(INDEX).getMyMoney() + ", 보유포인트 : " + loginInfo.get(INDEX).getPoint());
			System.out.println("SELECT MENU >> ");
			int choice = Util.sc.nextInt();
			Util.sc.nextLine();

			switch (choice) {
			// 예약 하기
			case BOOKINGNOW:
				boolean sucess;  
				sucess = reserve.insertSeat();
				
				// 좌석 예약이 제대로 완료된 경우 
				if(sucess) {
					sucess = reserve.paying();
					
					// 결제가 제대로 완료된 경우 
					if(sucess) {
						reserve.payed();
						
						// 회원정보 파일 저장 
						saveInfo();
					}
				} 		
				break;

			case CANCEL:
				reserve.cancelSeat();
				break;

			// 예약 정보보기
			case BOOKINGINFO:
				// 내 좌석 정보보기
				reserve.mySeatView();
				break;

			// 결제하기
			case RECHARGE://충전하기
				reserve.recharge();
				break;

			// 홈메뉴로 돌아가기
			case EXIT2:
				return;
			}
			
		}

	}
}
