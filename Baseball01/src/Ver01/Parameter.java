package Ver01;

public class Parameter {
	// 클래스 다이어그램 대체 : 이 안에 있는 변수 이용해 주세요
	
	private String id;      // 사용자 id
	private int pass;       // 사용자 비번
	private int point;      // 포인트
	private int charge;     // 사용자가 입력하는 충전할 금액1
	private int myMoney;    // 충전된 금액
	private int Grade;      // 좌석등급n
	private int Date;       // 경기일
	private int Seat;       // 좌석
	private int mySeat;     // 내가 고른 좌석
	private int price;      // 티켓 가격
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPass() {
		return pass;
	}
	public void setPass(int pass) {
		this.pass = pass;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	public int getMyMoney() {
		return myMoney;
	}
	public void setMyMoney(int myMoney) {
		this.myMoney = myMoney;
	}
	public int getGrade() {
		return Grade;
	}
	public void setGrade(int grade) {
		Grade = grade;
	}
	public int getDate() {
		return Date;
	}
	public void setDate(int date) {
		Date = date;
	}
	public int getSeat() {
		return Seat;
	}
	public void setSeat(int seat) {
		Seat = seat;
	}
	public int getMySeat() {
		return mySeat;
	}
	public void setMySeat(int mySeat) {
		this.mySeat = mySeat;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	


	

}

