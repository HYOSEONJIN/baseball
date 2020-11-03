package Ver01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SujinTest implements Serializable {

	// 배열을 이용해서 저장하는 방식을 ArrayList<T> 컬랙션을 이용해서 구현해 보자
	List<BaseballDate> pBaseball;
	
	// 생성자 : 싱글톤 처리 -> 외부에서 인스턴스 생성을 금지  
	private SujinTest(){
		// List<PhoneInfor> 초기화
		pBaseball = new ArrayList<BaseballDate>();
		
		// 생성자 호출할때 파일 불러오기
		read();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SujinTest sujin = new SujinTest();
		
		// 테스트 변수
		boolean result = false;
		
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
		System.out.println("예약가능한 날짜 조회 --------------------");
		for (int i = 0; i < 3 ; i++) {
			System.out.println(date[i].getDate());
		}
		System.out.println();
		
		// 선택한 날짜에 따라 index 설정(0 : 오늘, 1 : 내일, 2 : 모레)
		int choiceDate = 0;
		
		// 예약
		System.out.println("테스터1 예약 --------------------");
		result = date[choiceDate].getParks().reserve("테스터1", 'A', 1);
		date[choiceDate].getParks().reserve("테스터2", 'B', 3);
		date[choiceDate].getParks().reserve("테스터3", 'C', 5);
		if (result) {
			System.out.println("테스터1 예약 완료\n");
		}
		
		// 취소
		System.out.println("테스터1 취소 --------------------");
		result = date[choiceDate].getParks().cancel("테스터1", 'A', 1);
		if (result) {
			System.out.println("테스터1 예약취소 완료\n");
		}
		
		// 내 좌석 정보보기
		System.out.println("내 좌석 정보보기--------------------");
		date[choiceDate].getParks().mySeatView("테스터2"); 
		System.out.println();
		
		// 전체 좌석 정보보기 
		System.out.println("전체 좌석 정보보기 --------------------");
		date[choiceDate].getParks().viewAll();
		
		// 파일 저장 
		sujin.save();
		

	}

	
	// 파일 저장 
	void save() {
		// 저장할 데이터가 있는지 확인 
		if (pBaseball.size() == 0) {
			System.out.println("저장할 데이터가 없습니다.");
			return;
		}
		
		try {
			// 아웃풋 스트림 오브젝트 생성(파일 생성) 
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("sujin_test.ser"));
			
			// 파일 쓰기 
			out.writeObject(pBaseball);
			out.close();
			
			System.out.println("파일 저장 완료!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 저장에 실패하였습니다!");
		}		
	}
	
	// 파일 읽어오기
	void read() {
		// 파일 데이터 있는지 확인 
		File file = new File("sujin_test.ser");
		if(!file.exists()) {
			System.out.println("로드할 데이터가 없습니다.");
			return;
		}
		
		// 인풋 스트림 오프젝트 생성(파일 리드)
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("sujin_test.ser"));
			
			pBaseball = (List<BaseballDate>)in.readObject();
			in.close();
			
			System.out.println("파일이 로딩되었습니다.");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 로딩에 실패하였습니다!");
		}
	}

}
