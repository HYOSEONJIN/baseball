package Ver01;

import java.util.ArrayList;

public class UserInfo{
	
//	static String NOWID ;	// 현재 로그인 ID
//	static int INDEX; 		// 현재 로그인 ID index

	
	public String name;  // 예약한 사람 이름
	public char grade;   // 등급 (A, B, C) 
	public int seatNum;  // 좌석번호 
	public int point; //
	public String choiceDate; 	// 선택한 야구날짜
	public int price = 0;			// 티켓 가격
	public int myMoney=0;			//충전금액 (나의금액) 
	
	
	
	public UserInfo(String name, String date, char grade, int seatNum, int point, String choiceDate, int price,
			int myMoney) {
		super();
		this.name = name;
		this.grade = grade;
		this.seatNum = seatNum;
		this.point = point;
		this.choiceDate = choiceDate;
		this.price = price;
		this.myMoney = myMoney;
	}
	
	


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point += point;
	}
	public String getChoiceDate() {
		return choiceDate;
	}
	public void setChoiceDate(String choiceDate) {
		this.choiceDate = choiceDate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMyMoney() {
		return myMoney;
	}
	public void setMyMoney(int myMoney) {
		this.myMoney = myMoney;
	}

	public void showInfo () {
//		System.out.println("티켓명:");
//		System.out.println("이름:"+getName());
//		System.out.println("경기일:"+getChoiceDate());
//		System.out.println("좌석정보:"+getSeatNum());
//		System.out.println("포인트:"+getPoint());
//		System.out.println("충전금액:"+getMyMoney());
		System.out.println("이름:"+name);
		System.out.println("경기일:"+choiceDate);
		System.out.println("좌석정보:"+seatNum);
		System.out.println("포인트:"+point);
		System.out.println("충전금액:"+myMoney);
		
	}
	
	public void showcharge() {
		System.out.println("이름:"+name);
		System.out.println("충전금액:"+myMoney);
	}
	
		 

}