package Ver01;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingManager implements Menu {

	// public void Bookingmain() {
	public static void main(String[] args) {

		ReserveSeat reserve = new ReserveSeat();

		// 테스트 변수
		boolean result = false;

		// 테스트를 위해 우선 넣어놓음 . 테스트 끝나면 아래 주석 풀기
		// String loginId = LoginInfoManager.NOWID;
		String loginId = "이수진";

		String choiceDate = null; // 선택한 야구날짜
		char grade = ' '; // 좌석 등급
		int price = 0; // 티켓 가격
		int myMoney = 5000; // 현재 가진 돈
		int point = 0; // 포인트
		int getPoint = myMoney / 10;
		while (true) {

			System.out.println("********2020 포스트시즌 야구 예매********");
			System.out.println("*             " + BOOKINGNOW + ".예매하기                     *");
			System.out.println("*              " + CANCEL + ".예매취소                         *");
			System.out.println("*        " + BOOKINGINFO + ".결제정보                      *");
			System.out.println("*           " + RECHARGE + ".충전하기                           *");
			System.out.println("**********************************");
			System.out.println("보유금액:" + myMoney);
			int choice = Util.sc.nextInt();
			Util.sc.nextLine();

			switch (choice) {
			// 예약 하기
			case BOOKINGNOW:
				reserve.insertSeat();
				
				boolean sucess = (boolean)reserve.paying(myMoney);

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

			case RECHARGE:
				
			myMoney = reserve.recharge(myMoney);
				
				
				break;

			}
		}

	}
}
