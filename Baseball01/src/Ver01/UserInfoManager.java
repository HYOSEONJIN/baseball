package Ver01;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Ver01.Util;

public class UserInfoManager {
	
	// 로그인정보 배열 생성
	List<UserInfo> userInfo; 	// 배열 선언
	
	// List<UserInfo> 초기화 
	// 싱글톤 패턴
	private UserInfoManager(int num){
		userInfo = new ArrayList<UserInfo>();
	}
	
	// 내부에서 인스턴스 생성
	private static UserInfoManager manager = new UserInfoManager(100);
	
	// 외부에서 참조변수 받을 수 있는 메서드
	public static UserInfoManager getInstance() {
		return manager;
	}
	 

	// 배열에 정보 저장 메서드
	private void addInfo(UserInfo info) {		
		userInfo.add(info);
	}
	
	// 회원가입 메서드
	public void addProfile() {
		
		System.out.println("회원가입을 시작합니다.");

//		UserInfo info = new UserInfo(null, null);		
//		String id = info.getId();
		String id = JOptionPane.showInputDialog("아이디를 입력해주세요.");
		// 아이디 중복 확인	
		if(userInfo.contains(id)) {
			System.out.println("아이디가 중복됩니다. 다른 아이디를 입력해주세요.");
		} else {
			String pw = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");
			addInfo(new UserInfo(id, pw));
		}	
	}
	
	// 로그인 메서드 : 사용자 입력 (창 띄우기)
	public void login(){
		
		// 사용자 입력
		String id = JOptionPane.showInputDialog("아이디를 입력해주세요.");
		String pw = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");

		System.out.println("id :" +id);
		System.out.println("pw :" + pw);
		
		
	}
	
	// 자가정보 조회 	
	public void displayInfo(){
		// searchInfo() 메서드로 자가정보에 해당하는 index 찾아서 해당 index 정보 출력
		
		// id/pw 출력
		// 보유 금액, 보유 포인트 출력	
		// 예매이력, 한불이력 
		// 이벤트 당첨 조회
	}	
	
	
}
