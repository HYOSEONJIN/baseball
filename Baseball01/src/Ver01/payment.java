package Ver01;

public class payment extends BookingManager {

	String choiceDate = null; // 선택한 야구날짜
	char grade = ' '; // 좌석 등급
	int price = 0; // 티켓 가격
	int myMoney = 0; // 현재 가진 돈
	int point = 0; // 포인트

	ReserveSeat reserve = new ReserveSeat();

	void paying() {
		 reserve.insertSeat();
		if (myMoney < price) {

			System.out.println("포인트 충전이 필요합니다.");

		} else if (myMoney <= price) {

			myMoney -= price;
           reserve.pay();
           
		}else {
			
		}

	}

}
