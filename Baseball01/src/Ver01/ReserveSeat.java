package Ver01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

// 야구장 예매 및 결제 매니저
public class ReserveSeat extends LoginInfoManager implements Serializable {
	static String choiceDate;
	static int seatNum = 0; // 좌석 번호
	char grade = ' '; // 좌석 등급
	
	static int choiceSeatNum = 0;

	// 배열을 이용해서 저장하는 방식을 ArrayList<T> 컬랙션을 이용해서 구현해 보자
	static ArrayList<Seat> pSeat;
	
	// 생성자 : 싱글톤 처리 -> 외부에서 인스턴스 생성을 금지
	ReserveSeat() {
		// List<PhoneInfor> 초기화
		pSeat = new ArrayList<Seat>();

		// 생성자 호출할때 파일 불러오기
		load();
	}

	// 날짜 입력하기
	public static String choiceDate() {
		getMonthGalendar();

		System.out.println("예매 가능한 날짜를 입력해 주세요. [입력형식 : 2020-01-01]");

		while (true) {
			// 입력 날짜 받기
			choiceDate = Util.sc.nextLine();

			if (choiceDate.length() != 10) {
				System.out.println("입력형식이 올바르지 않습니다. 다시 입력해주세요.");
				// Util.sc.nextLine();
				continue;
			} else {
				System.out.println("[" + choiceDate + "] 경기를 선택하셨습니다.");
				break;
			}
		}

		return choiceDate;
	}

	// 좌석 선택하기
	public static int choiceSeat(String choiceDate) {
		String arrSeat[][] = new String[3][10]; // 3 * 10 좌석 생성
		choiceSeatNum = 0;
		int index = 0;
		int count = 1;

		System.out.println("\n ■■■■■ 좌석 정보 [예약된 좌석인 경우 X, 아닌경우 : 좌석번호] ■■■■■■ \n");
		for (int i = 0; i < arrSeat.length; i++) {

			System.out.println("[" + (char) (i + 'A') + "석]");

			for (int j = 0; j < arrSeat[i].length; j++) {
				index = searchIndex(choiceDate, count);

				// 예약된 좌석인 경우 X, 아닌경우 : 좌석번호
				if (index > -1) {
					System.out.print("[X]");
				} else {
					System.out.print("[" + count + "]");
				}
				count++;
			}
			System.out.println();
		}
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

		System.out.println("원하시는 좌석 번호를 선택해주세요.");
		choiceSeatNum = Util.sc.nextInt();

		return choiceSeatNum;
	}

	// 좌석 예약하기
	boolean insertSeat() {
		boolean result = false;
		
		if (loginInfo.get(INDEX).getMyMoney() == 0) {
			System.out.println("금액 충전이 필요합니다.");
			
		} else {
			// 선택한 야구 날짜
			choiceDate = choiceDate();
	
			// 선택한 좌석번호
			seatNum = choiceSeat(choiceDate);
			
			result = true;
		}
		return result;
	}

	// 결제
	boolean paying() {
		boolean result = false;
		int price = 0; // 티켓 가격
		int myMoney = loginInfo.get(INDEX).getMyMoney();
		int myPoint = loginInfo.get(INDEX).getPoint();
	
		if (seatNum <= 10) {
			price = 10000;
		} else if (seatNum > 10 && seatNum <= 20) {
			price = 5000;
		} else if (seatNum > 20 && seatNum <= 30) {
			price = 3000;
		}
		
		if (myMoney < price) {
			System.out.println("금액 충전이 필요합니다.");
		} else {
			
			String cause="결제금액 10% 적립";
			
			myMoney -= price;
			int point = price/10;
			loginInfo.get(INDEX).setMyMoney(myMoney);
			loginInfo.get(INDEX).setPoint(point);
			try {
				pointHistory(NOWID, point , cause);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
			System.out.println(price + "원이 결제 되었습니다.");
			System.out.println((point) + "포인트가 적립 되었습니다.\n");
			System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
			result = true;
		}
		return result;
	}

	// 예약완료
	void payed() {
		pSeat.add(new Seat(INDEX, NOWID, choiceDate, seatNum));
		// 파일 저장
		save();
		System.out.println(NOWID + "님 날짜 : " + choiceDate + ",  좌석번호 : " + seatNum + "번 예매 되셨습니다");
	}

	// 좌석 취소하기
	void cancelSeat() throws IOException {
		int myMoney = loginInfo.get(INDEX).getMyMoney();
		int myPoint = loginInfo.get(INDEX).getPoint();
				
		// 내 좌석 정보보기
		if ( mySeatView()) {
			String cause="결제 취소 포인트 반환";
			System.out.println("취소하시는 번호를 입력해주세요.");
			int index = Util.sc.nextInt();
	
			if (index < 1) {
				System.out.println("찾으시는 정보가 존재하지 않습니다.");
				System.out.println("메뉴로 이동합니다.");
			} else {
				// 결제금액 및 포인트 돌려주기
				int price = 0; // 티켓 가격
				int cancelSetNum = pSeat.get(index - 1).getSeatNum();
				
				if (cancelSetNum <= 10) {
					price = 10000;
				} else if (cancelSetNum > 10 && cancelSetNum <= 20) {
					price = 5000;
				} else if (cancelSetNum > 20 && cancelSetNum <= 30) {
					price = 3000;
				}
				int point = -(price/10);
				
				if (myPoint - Math.abs(point) < 0) {
					System.out.println(NOWID + "님 포인트를 이미 쓰셨네요. 예약취소가 불가능 합니다.\n");
				} else {
					loginInfo.get(INDEX).setMyMoney(myMoney + price);
					loginInfo.get(INDEX).setPoint(point);
					pointHistory(NOWID, point, cause);
					
					// 좌석 취소
					pSeat.get(index - 1).cancel();
					
					// 파일 저장
					save();
					
					System.out.println(NOWID + "님 예약취소가 완료되었습니다.\n");
				}
				
			}
		}
	}

	// 해당달 달력 가져 오기
	public static void getMonthGalendar() {

		// 달력 관련 변수
		Calendar cal = Calendar.getInstance();
		int thisYear = cal.get(Calendar.YEAR); // 년
		int thisMonth = cal.get(Calendar.MONTH) + 1; // 월
		int today = cal.get(Calendar.DATE); // 일

		cal.set(thisYear, thisMonth - 1, 1); // thisMonth - 1 : 1월이 0이라서 해당달을 set 하려면 -1 해야함

		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1일의 요일
		int endDate = cal.getActualMaximum(Calendar.DATE); // 달의 마지막일

		int nowYear = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH);

		System.out.println("[" + nowYear + "년  " + (nowMonth + 1) + "월]");

		// 요일명 출력
		System.out.println(" 일\t 월\t 화\t 수\t 목\t 금\t 토\t");
		System.out.println("====================================================");

		// 1일이 시작되는 이전의 요일 공백으로 채우기
		int dateNum = 1; // 출력할 date를 저장할 변수

		for (int i = 1; dateNum <= endDate; i++) {

			if (i < dayOfWeek) {
				System.out.print("\t"); // 요일숫자보다 작으면 공백
			} else {
				if (dateNum < today) {
					System.out.print("\t"); // 오늘 날짜전이면 예약 불가능 하므로 공백
				} else {
					System.out.print(dateNum + "\t");
				}

				dateNum++;
			}

			// 줄바꿈
			if (i % 7 == 0) {
				System.out.println();
			}

		}
		System.out.println();
	}

	// 일자별 좌석 인덱스 조회
	// 해당 indxe의 참조변수로 정보 출력
	public static int searchIndex(String date, int seatNum) {
		int indexNum = -1; // 정보가 없을때

		for (int i = 0; i < pSeat.size(); i++) {
			if (pSeat.get(i).getDate().equals(date) && pSeat.get(i).getSeatNum() == seatNum) {
				indexNum = i;
			}
		}
		return indexNum;
	}

	// 내 좌석 정보 보기
	public static boolean mySeatView() {
		boolean result = false;
		String msg = "";
		
		for (int i = 0; i < pSeat.size(); i++) {
			if (pSeat.get(i).getIndex() == INDEX) {
				result = true;
				msg += "\n예매번호"+(i + 1)+"번" + ". [" + NOWID + "]님은\n" + pSeat.get(i).getDate() + "일,"
						+ "\n"+pSeat.get(i).getGrade() + "등급 " + pSeat.get(i).getSeatNum() + "번째 좌석을 예약하셨습니다.\n";
				
			}
		}
		
		if(result) {
			System.out.println("[" + NOWID + "님 예약 정보]");
			System.out.println(msg);
		}else {
			System.out.println("예약된 정보가 존재하지 않습니다.");
		}
		
		return result;
	}

	void recharge() {
		int myMoney = loginInfo.get(INDEX).getMyMoney();
		
		System.out.println(" 충전 금액을 입력하세요.");
		int addMoney = Util.sc.nextInt();
		myMoney += addMoney;
		loginInfo.get(INDEX).setMyMoney(myMoney);
		System.out.println("보유금액: " + myMoney);

		save();
	}

	// List:pBook 에 저장되어있는 인스턴스들을 저장 
	public void save() {
		// 파일 중복생성 방지
		File f = new File("PayInfor.ser");
		f.delete();

		// 인스턴스를 저장할 수 있는 출력 스트림 생성 
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("PayInfor.ser"));

			// 이렇게 통채로 저장 가능 
			out.writeObject(pSeat);
			out.close();
			
			// 회원 정보 재저장 
			saveInfo();
			 			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			System.out.println("저장하는 과정에 오류가 발생했습니다.(" + pSeat.size() + ") \n다시 시도해 주세요.");
		}
		
	}
	
	// 프로그램으로 파일의 저장 데이터를 로드하는 메서드 생성 
	void load() {

		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("PayInfor.ser"));
			
			pSeat = (ArrayList<Seat>)in.readObject();  
		} catch (IOException e) {
			//System.out.println("데이터를 로드하는 과정에 오류가 발생했습니다.");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("데이터를 로드하는 과정에 오류가 발생했습니다.");
			e.printStackTrace();
		}
		
	}
}
