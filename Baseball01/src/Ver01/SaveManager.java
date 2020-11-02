package Ver01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveManager {
	
	void save()  {
		
		
		// 회원 구매이력 정보 저장
		
		// 인스턴스 저장하는 출력 스트림 생성
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("yourBooking.ser"));
			// out.writeObject(출력할 구매이력 인스턴스 넣기);
			// out.close();
			System.out.println("저장되었습니다.(yourBooking.ser파일 확인)");
		} catch (IOException e) {
			System.out.println("저장중 오류가 발생했습니다. 다시 시도해 주세요.");
		}

		
		

		
				
	}
	
	
	
	

}
