package Ver01;

// 날짜 예약 
public class BaseballDate {
	private String date; // 야구 하는 날짜
	
	// 날짜별로 구장을 새로 생성 
	private BaseballPark parks; 
	
	// 생성자
	public BaseballDate(String date){
		this.date = date;  // 날짜를 몇개 지정할지 확인
		
		parks = new BaseballPark(3, 10);  // 3x10짜리 구장 생성 
	
	}

	public String getDate() {
		return date;
	}

	public BaseballPark getParks() {
		return parks;
	}
	
	
}
