package Ver01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReserveSeat {
	String choiceDate;  
	static int choiceDay;
	//static String loginId = LoginInfoManager.NOWID;
	static String loginId = "이수진";
	
	// 달력 관련 변수
	static Calendar cal = Calendar.getInstance(); 
	static int thisYear = cal.get(Calendar.YEAR);  		// 년
	static int thisMonth = cal.get(Calendar.MONTH)+1; 		// 월
	static int today = cal.get(Calendar.DATE); 			// 일
	
	// 배열을 이용해서 저장하는 방식을 ArrayList<T> 컬랙션을 이용해서 구현해 보자
	static List<Seat> pSeat;
	
	// 생성자 : 싱글톤 처리 -> 외부에서 인스턴스 생성을 금지  
	ReserveSeat(){
		// List<PhoneInfor> 초기화
		pSeat = new ArrayList<Seat>();
		
		// 생성자 호출할때 파일 불러오기
		//read();
	}
	
	// 날짜 입력하기 
	public static String choiceDate() {
		getMonthGalendar();
		System.out.println("예매 가능한 날짜를 입력해 주세요. [일자만 입력!]");
		
		choiceDay = Util.sc.nextInt();
		System.out.println(Calendar.MONTH + "월 " + choiceDay + "일 경기를 선택하셨습니다.");
		
		return thisYear + "-" + thisMonth + "-" + choiceDay;
	}
	
	// 좌석 선택하기 
	public static int choiceSeat(String choiceDate) {
		String arrSeat[][] = new String[3][10];  // 3 * 10 좌석 생성 
		int choiceSeatNum = 0; 
		int index = 0;
		int count = 1;
		
		System.out.println("========== 좌석 정보 [예약된 좌석인 경우 X, 아닌경우 : 좌석번호] ==========");
		for(int i = 0; i < arrSeat.length ; i++) {
			
			System.out.println("[" + (char)(i + 'A') + "석]");
			
			for(int j = 0; j < arrSeat[i].length ; j++) {
				index = searchIndex(loginId, choiceDate, count); 
				
				// 예약된 좌석인 경우 X, 아닌경우 : 좌석번호
				if(index > 0) {
					System.out.print("[X]");
				}else {
					System.out.print("[" + count + "]");
				}
				count++;
			}
			System.out.println();
		}
		System.out.println("=============================================");	
		
		System.out.println("원하시는 좌석 번호를 선택해주세요.");
		choiceSeatNum = Util.sc.nextInt();
		
		return choiceSeatNum; 
	}
	
	// 좌석 취소하기
	public static void cancelSeat() {
		int choiceSeatNum = 0;
		String choiceDate = ""; 
		
		//내 좌석 정보보기
		mySeatView(); 
		
		System.out.println("취소하시는 날짜를 입력해주세요. [ex : 2020-10-01]");
		choiceDate = Util.sc.nextLine();
		System.out.println("취소하시는 좌석 번호를 선택해주세요.");
		choiceSeatNum = Util.sc.nextInt();
		
		int index = searchIndex(loginId, choiceDate, choiceSeatNum);
		
		if(index <0) {
			System.out.println("찾으시는 정보가 존재하지 않습니다.");
			System.out.println("메뉴로 이동합니다.");			
		} else {
			// 좌석 취소 
			pSeat.get(index).cancel();
			System.out.println(loginId + "님 예약취소가 완료되었습니다.\n");
		}
	}
	
	// 해당달 달력 가져 오기
	public static void getMonthGalendar(){
        cal.set(thisYear, thisMonth - 1 ,1); 			//캘린더객체에 입력받은 년, 달, 그리고 Date을 1로설정
       
        int sDayNum = cal.get(Calendar.DAY_OF_WEEK); 		// 1일의 요일
        int endDate = cal.getActualMaximum(Calendar.DATE); 	// 달의 마지막일
       
        int nowYear = cal.get(Calendar.YEAR);
        int nowMonth = cal.get(Calendar.MONTH);
       
        System.out.println("[" + nowYear + "년  " + (nowMonth + 1) + "월]");

        //요일명 출력
        System.out.println(" 일\t 월\t 화\t 수\t 목\t 금\t 토\t");  
        System.out.println("====================================================");
        
        //1일이 시작되는 이전의 요일 공백으로 채우기
        int dateNum = 1; //출력할 date를 저장할 변수
      
        for (int i = 1; dateNum <= endDate ; i++) {    
           
            if(i<sDayNum) {
            	System.out.print("\t"); // 요일숫자보다 작으면 공백
            } else {
                if(dateNum == today) {
                	System.out.print(dateNum + "\t"); 	//오늘 날짜
                } else {
                	System.out.print(dateNum + "\t"); 		
                }
               
                dateNum++; 
            }      
            
            // 줄바꿈 
            if(i % 7 == 0) {
            	System.out.println(); 
            }
           
        }
        System.out.println();
    }


	// 정보 검색 
	// 해당 indxe의 참조변수로 정보 출력
	// 배열의 index 를 찾는 메서드
	public static int searchIndex(String name, String date, int seatNum) {
		int index = -1; // 정보가 없을때
		
		for(int i=0; i < pSeat.size() ; i++) {
			if(pSeat.get(i).getName().equals(name) && pSeat.get(i).getDate().equals(date) && pSeat.get(i).getSeatNum() == seatNum) {
				index = i;
			}
		}
		return index;
	}

	// 내 좌석 정보 보기 
	public static void mySeatView() {
		String result = "예약된 정보가 존재하지 않습니다.";
		
		System.out.println("["+ loginId + "님 예약 정보]");
		for(int i=0; i < pSeat.size() ; i++) {
			if(pSeat.get(i).getName().equals(loginId)) {
				result = "[" + pSeat.get(i).getName() + "]님은 " + pSeat.get(i).getDate() + "일 " + pSeat.get(i).getGrade() + "등급 " + pSeat.get(i).getSeatNum() + "번째 좌석을 예약하셨습니다.";
			}			
		}
		System.out.println(result);
	}
	
	// 전체 좌석 정보 보기 
	public static void viewAll() {
		String choiceDate; 
		String arrSeat[][] = new String[3][10];  // 3 * 10 좌석 생성 
		int index = 0;
		int count = 1;
		
		System.out.println("조회할 날짜를 입력해주세요. [ex : 2020-10-01]");
		choiceDate = Util.sc.nextLine();
		
		System.out.println("=============================================");
		for(int i = 0; i < arrSeat.length ; i++) {
			
			System.out.println("[" + (char)(i + 'A') + "석] 좌석 번호 -> " + (count));
			
			for(int j = 0; j < arrSeat[i].length ; j++) {
				index = searchIndex(loginId, choiceDate, count); 
				
				// 예약된 좌석인 경우 O, 아닌경우 X
				if(index > 0) {
					System.out.print("[0]");
				}else {
					System.out.print("[X]");
				}
				count++;
			}
			System.out.println();
		}
		System.out.println("=============================================");	

	}	
}
