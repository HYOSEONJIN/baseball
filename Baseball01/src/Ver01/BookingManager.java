package Ver01;

import java.awt.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class BookingManager implements Menu {
		
	public void Bookingmain() { 
		
		// 테스트 변수
		boolean result = false;
				
		// 날짜 설정
		Date newDate = new Date();   //오늘 날짜
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		
		// 날짜별 예약을 만들기 위해 날짜를 배열화
		BaseballDate date[] = new BaseballDate[3];
		
		// 해당 날짜에 해당하는 구장 좌석
		for (int i = 0; i < 3 ; i++) {
			date[i] = new BaseballDate(sdf.format(cal.getTime()));
			cal.add(Calendar.DATE,  1);   // 오늘 + 1
		}
		
		// 테스트를 위해 우선 넣어놓음 . 테스트 끝나면 아래 주석 풀기
		//String loginId = LoginInfoManager.NOWID;
		String loginId = "장명지";

		int choiceDate = 0;  	// 선택한 야구날짜
		char grade = ' ';  		// 좌석 등급 
		int seatNum = 0;   		// 좌석 번호
		int price = 0;			// 티켓 가격
		int myMoney=0;			//현재 가진 돈 
		int point=0;			//포인트
		
		while(true) {
			
			System.out.println("********2020 포스트시즌 야구 예매********");
	         System.out.println("*             "+  BOOKINGNOW+".예매                     *");
	         System.out.println("*              "+CANCEL+".예매취소                         *");
	         System.out.println("*             "+BOOKINGINFO+".회원예매정보             *");
	         System.out.println("*        "+ALLSEAT+".전체 좌석 정보보기                      *");
	         System.out.println("*             "+PAYING+".결제하기                           *");
	         System.out.println("*           "+RECHARGE+".충전하기                           *");
	         System.out.println("**********************************");
			
			int choice = Util.sc.nextInt();
			Util.sc.nextLine();
			
			switch(choice) {
				// 예약 하기
				case BOOKINGNOW:				
					// 예매 가능 날짜 출력
					System.out.println("예매 가능한 날짜 목록 ->");
					for (int i = 0; i < 3 ; i++) {
						System.out.println((i+1) + "." + date[i].getDate());
					}
					System.out.println("예매 가능한 날짜 번호를 입력해 주세요. ");
				
					// 선택한 날짜에 따라 index 설정(0 : 오늘, 1 : 내일, 2 : 모레)
					choiceDate = Util.sc.nextInt() - 1;
					System.out.println();
					Util.sc.nextLine();
						
					date[choiceDate].getParks().viewAll();
					
					System.out.println("원하시는 위치의 좌석 등급을 선택해 주세요(A석 : 10000원, B석 : 5000원, C석 : 1000원) ");
					grade = Util.sc.next().toUpperCase().charAt(0);
					
					System.out.println("원하시는 좌석 번호를 선택해주세요.");
					seatNum = Util.sc.nextInt();
					 
					result = date[choiceDate].getParks().reserve(loginId, grade, seatNum);
					
					if (result) {
						date[choiceDate].getParks().viewAll();
						System.out.println(loginId+"님 " + grade + "석 " + seatNum + "자리가 예매 되셨습니다");
					}
						
					break;
				// 예약 취소		
				case CANCEL:
					//내 좌석 정보보기
					date[choiceDate].getParks().mySeatView(loginId); 

					System.out.println(loginId + "취소하시는 좌석 등급을 선택해주세요.");
					grade = Util.sc.next().toUpperCase().charAt(0);
				
					System.out.println("취소하시는 좌석 번호를 선택해주세요.");
					seatNum = Util.sc.nextInt();
					
					result = date[choiceDate].getParks().cancel(loginId, grade, seatNum);
				
					if (result) {
						System.out.println(loginId + "님 예약취소가 완료되었습니다.\n");
					} else {
						System.out.println(loginId + "님 예약취소에 실패하였습니다. 관리자에게 문의하세요.\n");
					}
					
					break;
					
				// 예약 정보보기		
				case BOOKINGINFO:
					//내 좌석 정보보기
					date[choiceDate].getParks().mySeatView(loginId); 
					System.out.println();
					break;
				
				// 전체 좌석 정보보기	
				case ALLSEAT:
					date[choiceDate].getParks().viewAll();
					break;
				
					//결제하기		
				case PAYING: 
					
						
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
					
					
				case RECHARGE:
					
					System.out.println("충전 금액을 입력 하세요 ");
					   
				   int recharge=Util.sc.nextInt();
				    myMoney=+ recharge; 
				
				    System.out.println(myMoney+"가 충전되었습니다. ");
					
					break;
				
			}
		}
	}
}
