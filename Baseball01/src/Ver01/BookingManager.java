package Ver01;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BookingManager implements Menu {
		
	//public void Bookingmain() {
	public static void main(String[] args) {
		
		ReserveSeat reserve = new ReserveSeat();
		
		// 테스트 변수
		boolean result = false;
				
		// 테스트를 위해 우선 넣어놓음 . 테스트 끝나면 아래 주석 풀기
		//String loginId = LoginInfoManager.NOWID;
		String loginId = "이수진";

		String choiceDate = null; 	// 선택한 야구날짜
		char grade = ' ';  		// 좌석 등급 
		int price = 0;			// 티켓 가격
		int myMoney=0;			//현재 가진 돈 
		int point=0;			//포인트
		
		while(true) {
			
			System.out.println("********2020 포스트시즌 야구 예매********");
	         System.out.println("*             "+  BOOKINGNOW+".예매하기                     *");
	         System.out.println("*              "+CANCEL+".예매취소                         *");
	     	 System.out.println("*        "+BOOKINGINFO+".결제정보                      *");
	         System.out.println("*           "+RECHARGE+".충전하기                           *");
	         System.out.println("**********************************");
			
			int choice = Util.sc.nextInt();
			Util.sc.nextLine();
			
			switch(choice) {
				// 예약 하기
				case BOOKINGNOW:		
					reserve.insertSeat();
					System.out.println("결제 하시겠습니까?");
					System.out.println("1.예 ,2.아니요");
					
					break;
					
				// 예약 취소		
				case CANCEL:
					reserve.cancelSeat();
					break;
					
				// 예약 정보보기		
				case BOOKINGINFO:
					//내 좌석 정보보기
					reserve.mySeatView(); 
					break;
				
					//결제하기		
			/*
						
					if (grade=='A') {
						price = 10000;  
						point = price/10;
						System.out.println(price+"원이 결제 되었습니다."); 
						System.out.println(point +"가  적립 되었습니다."); 
	 				}else  if (grade=='B') {
						price=5000;
					    point=price/10;
						System.out.println(price+"원이 결제 되었습니다."); 
						System.out.println(point+ "가  적립 되었습니다."); 
					}else  if (grade=='c') {
						price=3000;
						point=price/10;
						System.out.println(price+"원이 결제 되었습니다."); 
						System.out.println(point+"가  적립 되었습니다."); 
					}
*/					
					
					
				case RECHARGE:
/*					
					System.out.println("충전 금액을 입력 하세요 ");
					   
				   int recharge=Util.sc.nextInt();
				    myMoney=+ recharge; 
				
				    System.out.println(myMoney+"가 충전되었습니다. ");
*/					
					break;
				
			}
		}
	
	}
}
