package Ver01;

// 좌석 클래스 
public class BaseballPark {
	private Seat[][] Seats;  // 좌석을 배열로 만들어주기 
	private int rowCount, colCount; 
	private int reserveCount;    // 총 예약된 좌석수
	
	// 생성자
	public BaseballPark(int rowCount, int colCount){
		this.rowCount = rowCount;
		this.colCount = colCount;
		
		Seats = new Seat[rowCount][colCount];   // 좌석 갯수 기본 생성  
		
		for(int i = 0; i < rowCount; i++) {
			for(int j = 0; j < colCount; j++) {
				Seats[i][j] = new Seat();
			}
		}
	}
	
	// 좌석 고르기 : 예약할 사람 이름, 좌석등급, 좌석 번호
	// return : true = 예약완료, false : 이미 예약된 좌석
	public boolean reserve(String name, char grade, int seatNum) {
		boolean result = false;
		int gradeNum = searchRownum(grade);  // 등급별 변호( 0 : A석, 1 : B석, 2 : C석 ) 
		 
		Seat mySeat = Seats[gradeNum][seatNum - 1];
			
		// 해당 좌석 예약 여부 조회해서 좌석이 비어 있으면 예약 
		if(!mySeat.isExist()) {
			mySeat.reverse(name, grade, seatNum);
			result = true;
			reserveCount++;			
		}
		
		return result;
	}
	
	
	// 좌석 취소  : 예약한 사람 이름, 좌석등급, 좌석 번호
	public boolean cancel(String name, char grade, int seatNum) {
		boolean result = false;
		int gradeNum = searchRownum(grade);  // 등급별 변호( 0 : A석, 1 : B석, 2 : C석 ) 
		Seat mySeat = Seats[gradeNum][seatNum - 1];
		
		// 예약자가 맞는 경우 취소 
		if(mySeat.isExist() && mySeat.getName().equals(name)) {
			Seats[gradeNum][seatNum - 1].cancel();
			result = true;
			reserveCount--;	
		}
		
		return result;		
	}
	
	// 내 좌석 정보 보기 
	public void mySeatView(String name) {
		String result = "예약된 정보가 존재하지 않습니다.";
		
		for(int i = 0; i < Seats.length ; i++) {
			for(int j = 0; j < Seats[i].length ; j++) {
				
				// 예약한 사용자인 경우
				if(Seats[i][j].isExist() && Seats[i][j].getName().equals(name)) {
					result = "[" + Seats[i][j].getName() + "]님은 " + Seats[i][j].getGrade() + "등급 " + Seats[i][j].getSeatNum() + "번째 좌석을 예약하셨습니다.";
				}
			}			
		}
		System.out.println(result);
	}
	
	// 좌석 전체 보기 
	public void viewAll() {
		for(int i = 0; i < Seats.length ; i++) {
			for(int j = 0; j < Seats[i].length ; j++) {
				
				// 예약된 좌석인 경우 O, 아닌경우 X
				if(Seats[i][j].isExist()) {
					System.out.print("[0]");
				}else {
					System.out.print("[X]");
				}
			}
			System.out.println();
		}
	}
	
	// 등급별 rowsnum 으로 변경 
	public int searchRownum(char grade) {
		return grade - 'A';	
	}
	
	
	
	
}


