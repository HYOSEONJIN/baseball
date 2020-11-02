package Ver01;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SujinTest implements Util {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
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

		// 예약가능한 날짜 조회 
		for (int i = 0; i < 3 ; i++) {
			System.out.println(date[i].getDate());
		}
		
		// 선택한 날짜에 따라 index 설정(0 : 오늘, 1 : 내일, 2 : 모레)
		int choiceDate = 0;
		
		// 예약
		date[choiceDate].getParks().reserve("테스터1", 'A', 1);
		date[choiceDate].getParks().reserve("테스터2", 'B', 3);
		date[choiceDate].getParks().reserve("테스터3", 'C', 5);
		
		// 취소 
		date[choiceDate].getParks().cancel("테스터1", 'A', 1);
		
		// 내 좌석 정보보기
		date[choiceDate].getParks().mySeatView("테스터2"); 
		
		// 전체 좌석 정보보기 
		date[choiceDate].getParks().viewAll();
		

	}

}
