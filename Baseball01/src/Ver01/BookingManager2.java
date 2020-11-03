
package Ver01;

public class BookingManager2 {
	

	import java.awt.List;
import java.text.SimpleDateFormat;
	import java.util.Calendar;
	import java.util.Date;

	public class booking implements Util {
		
		
		
		
	public static void main(String[] args) 
		
		BaseballPark n = new BaseballPark(0, 0);
	 // 결제하기  
		

	//예약가능한 날짜 조회 
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

				
				// 선택한 날짜에 따라 index 설정(0 : 오늘, 1 : 내일, 2 : 모레)
			int choiceDate = 0;
			String id=null;
			char input='A';//  등급 
			int seat=0;// 좌석
			int price=0;// 티켓 가격
				
			

				while(true) {
					System.out.println("1. 예약 하기");
					System.out.println("2. 예약 취소 ");
					System.out.println("3. 예약정보보기");
					System.out.println("4. 전체 좌석 정보보기 ");
					System.out.println("5. 결제하기 ");
					System.out.println("6. 충전하기 ");
					int choice = Util.sc.nextInt();
					Util.sc.nextLine();
					
					switch(choice) {
					case 1:
						n.reserve(name, grade, seatNum);
					int i;	
					for ( i = 0; i < 3 ; i++) {
							System.out.println((i+1)+"."+date[i].getDate());
							
						}
				
		         	   choiceDate = Util.sc.nextInt();
						System.out.println();
						sc.nextLine();
						
						
						System.out.println("예약자 이름: ");
						 id=Util.sc.nextLine();
						System.out.println("좌석 등급 : ");
						 input = sc.next().charAt(0);
					 System.out.println("좌석 번호 : ");
					
					 
						 seat=Util.sc.nextInt();
					 
					result = date[choiceDate].getParks().reserve(id, input, seat);
					if (result) {
						date[choiceDate].getParks().viewAll();
						System.out.println(id+"님 "+input+"석 "+seat+"자리가 예매 되셨습니다");
					}
						
						break;
						
					case 2:
						date[choiceDate].getParks().viewAll();
						System.out.println("이름: ");
						 id=Util.sc.nextLine();
						System.out.println(id+"님 예약 취소 --------------------");
						result = date[choiceDate].getParks().cancel( id, 'A', 1);
						if (result) {
							System.out.println(id+"님 예약취소가 완료되었습니다.\n");
						}
						
						
						break;
				
						
					case 3:
					//내 좌석 정보보기
						date[choiceDate].getParks().mySeatView(id); 
						System.out.println();
						break;
					case 4:
						// 전체 좌석 정보보기 
						
						date[choiceDate].getParks().viewAll();
						break;
						
					case 5: 
					//결제하기
						int myMoney;
						int point=0;
						
						
					if (input=='A') {
				     price=10000;  
				     point=price/10;
				   System.out.println(price+"원이 결제 되었습니다."); 
				   System.out.println(point +"가  적립 되었습니다."); 
				 
					
					}else  if (input=='B') {
						price=5000;
					     point=price/10;
						   System.out.println(price+"원이 결제 되었습니다."); 
						   System.out.println(point+ "가  적립 되었습니다."); 
						
					}else  if (input=='c') {
	                  price=3000;
	                  
	                  point=price/10;
	   			   System.out.println(price+"원이 결제 되었습니다."); 
	   			   System.out.println(point+"가  적립 되었습니다."); 
					}
					
					
						
						
						
					}
				
				}}
}
