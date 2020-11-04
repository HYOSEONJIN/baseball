/*
	설명 : 좌석 클래스 -> 좌석 예약시 저장할 정보 구현
	작성자 : 이수진 
	작성일 : 2020-11-02 
*/
package Ver01;

// 야구장 좌석 
public class Seat {
	private String name;  // 예약한 사람 이름
	private String date;  // 날짜
	private char grade;   // 등급 (A, B, C) 
	private int seatNum;  // 좌석번호 

	// 생성자
	public Seat(String name, String date, int seatNum) {
		this.name = name;
		this.date = date;
		this.grade = getGrade(seatNum);
		this.seatNum = seatNum;
	}
	
	// getter 
	public String getName() {
		return name;
	}	
	
	public String getDate() {
		return date;
	}	
	
	public char getGrade() {
		return grade;
	}	
	
	public int getSeatNum() {
		return seatNum;
	}
	

	// 해당 좌석이 예약된 좌석인지 조회 
	public boolean isExist() {
		boolean result = false;

		// 해당 좌석에 아무도 예약 안한 경우 
		if(name != null) {
			result = true;
		}
		return result;
	} 
	
	// 해당 좌석 예약 취소 
	public void cancel() {
		name = null;
		date = null;
		grade = ' ';
		seatNum = 0;
	}
	
	// 좌석별 등급 조회 
	public char getGrade(int seatNum) {
		char grade = ' ';
		
		// 좌석별 등급 (1~10 : A좌석, 11~20 : B좌석, 21~30 : C좌석)
		if(seatNum <= 10) {
			grade = 'A';
		}else if(seatNum > 10 && seatNum <= 20) {
			grade = 'B';
		}else if(seatNum > 20 && seatNum <= 30) {
			grade = 'C';
		}
		return grade;
	};

}
