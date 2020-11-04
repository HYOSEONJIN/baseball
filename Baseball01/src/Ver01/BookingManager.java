package Ver01;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingManager extends LoginInfoManager {
	ReserveSeat reserve = new ReserveSeat();
	LoginInfoManager loginManager = new LoginInfoManager();

	public void Bookingmain() {
		// 로그인 체크 
		if(NOWID == null) {
			System.out.println("로그인 해주세요.");
			return;
		}
		
		while (true) {

			System.out.println("********2020 포스트시즌 야구 예매********");
			System.out.println("             " + BOOKINGNOW + ".예매하기");
			System.out.println("              " + CANCEL + ".예매취소");
			System.out.println("        " + BOOKINGINFO + ".결제정보");
			System.out.println("           " + RECHARGE + ".충전하기");
			System.out.println("**********************************");
			System.out.println("보유금액:" + loginInfo.get(INDEX).getMyMoney());
			int choice = Util.sc.nextInt();
			Util.sc.nextLine();

			switch (choice) {
			// 예약 하기
			case BOOKINGNOW:
				reserve.insertSeat();
				
				boolean sucess = reserve.paying();

				// 결제가 제대로 완료된 경우 
				if(sucess) {
					reserve.payed();
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

			}
		}

	}
}
