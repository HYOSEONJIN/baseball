package Ver01;

public class SujinTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 해당 날짜에 해당하는 구장 좌석
		BaseballDate date = new BaseballDate("2020-11-02");
		
		// 생성한 날짜의 구장 가져오기 
		BaseballPark todayPark = date.getParks();
		
		// 예약
		boolean result = false;
		result = todayPark.reserve("테스터1", 'A', 1);
		todayPark.reserve("테스터2", 'B', 3);
		todayPark.reserve("테스터3", 'C', 5);
		
		// 취소 
		todayPark.cancel("테스터1", 'A', 1);
		
		// 내 좌석 정보보기
		todayPark.mySeatView("테스터2"); 
		
		// 전체 좌석 정보보기 
		todayPark.viewAll();
		

	}

}
