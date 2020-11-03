/*
	설명 : 좌석 클래스 -> 좌석 예약시 저장할 정보 구현
	작성자 : 이수진 
	작성일 : 2020-11-02 
*/
package Ver01;

// 야구장 좌석 
public class BaseballSeat {
	private String name;  // 예약한 사람 이름
	private char grade;   // 등급 (A, B, C) 
	private int seatNum;  // 좌석번호 
	
	// getter 
	public String getName() {
		return name;
	}	
	
	public char getGrade() {
		return grade;
	}	
	
	public int getSeatNum() {
		return seatNum;
	}
	
	// 예약시 해당 좌석에 이름, 등급,  넣어주기
	public void reverse(String name, char grade, int seatNum) {
		this.name = name;
		this.grade = grade;
		this.seatNum = seatNum;
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
		grade = ' ';
		seatNum = 0;
	}

}
