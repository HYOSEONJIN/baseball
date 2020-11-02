package Ver01;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class LoginInfoManager {
// 팝업입력창 관련해서 문제가 생겨 일단 모두 주석처리하고 콘솔입력으로 바꿨습니다. 좀 더 알아보고 해결 가능하면 수정하겠습니다. by정은
// -------------------------------------------------------------------------------------------------
	
	// 로그인정보 배열 생성
	List<LoginInfo> loginInfo; 
	LoginInfo info = new LoginInfo();
	
	// List<loginInfo> 초기화 
	// 싱글톤 패턴
	private LoginInfoManager(int num){
		loginInfo = new ArrayList<LoginInfo>();
	}
	
	// 내부에서 인스턴스 생성 : 최대 20개의 배열 생성 가능
	private static LoginInfoManager manager = new LoginInfoManager(20);	
	
	// 외부에서 참조변수 받을 수 있는 메서드
	public static LoginInfoManager getInstance() {
		return manager;
	}
	 

	// 배열에 정보 저장 메서드
	private void addInfo(LoginInfo info) {		
		loginInfo.add(info);
	}
	
	
	// 회원가입 메서드
	//		ID 입력 받기 -> ID 중복 확인 -> 비밀번호 입력 받기 -> 회원가입 완료
	public void addProfile() {
		System.out.println("회원가입을 시작합니다.");
		
		// 아이디 중복 확인 (무한반복)
		while(true) {
			System.out.println("아이디를 입력해주세요.");
			String id = Util.sc.nextLine();
			Util.sc.nextLine();
//			 String id = JOptionPane.showInputDialog("");
	
			int index = searchIndex(id);
			if(index>=0) {
				System.out.println("중복되는 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
				break;
			} else {
				System.out.println("비밀번호를 입력해주세요.");
				String pw = Util.sc.nextLine();
//				 String pw = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");
				addInfo(new LoginInfo(id, pw));
			}
			System.out.println(id+"님, 가입을 축하드립니다!");
		}
	}
	
	
	// 배열의 index 검색 메서드
	private int searchIndex(String id) {
		int index = -1;
		for(int i=0; i<loginInfo.size(); i++) {
			if(loginInfo.get(i).getId().equals(id)) {
				index = i;
			}
		}
		return index;
	}

	
	// 회원정보 변경 메서드 
	//		재로그인 -> 해당하는 index의 정보 삭제 -> 새 정보 저장
	public void changeProfile() {
		System.out.println("회원정보 확인을 위해 다시 로그인해주세요.");

		// 회원정보 확인을 위한 재로그인
		while(true) {
			// 사용자 입력
			System.out.println("아이디를 입력해주세요.");
			String id = Util.sc.nextLine();
			System.out.println("비밀번호를 입력해주세요.");
			String pw = Util.sc.nextLine();
//			 String id = JOptionPane.showInputDialog("아이디를 입력해주세요.");
//			 String pw = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");
	
			// ID의 배열 index 찾기
			int index = searchIndex(id);
			if(index>=0) {
				// 해당 index의 비밀번호와 일치 여부 확인
				if(loginInfo.get(index).getPw().equals(pw)) {
					
					// 정보 변경 : 해당 index 삭제 -> 새 정보 저장
					System.out.println("회원정보 변경을 시작합니다.");	
					loginInfo.remove(index); // 정보 삭제
					System.out.println("새 아이디를 입력해주세요.");
					String changedId = Util.sc.nextLine();
					System.out.println("세 비밀번호를 입력해주세요.");
					String changedPw = Util.sc.nextLine();
//					 String changedId = JOptionPane.showInputDialog("새 아이디를 입력해주세요.");
//					 String changedPw = JOptionPane.showInputDialog("새 비밀번호를 입력해주세요.");
					addInfo(new LoginInfo(changedId, changedPw)); // 정보 저장
					
					// 변경된 정보 출력
					System.out.println("변경된 회원정보는 다음과 같습니다.");
					System.out.println("=============");
					info.showLoginInfo();
					System.out.println("=============");
					break;
				} else {
					System.out.println("로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
//					 JOptionPane.showMessageDialog(null, "로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
				}
			}
		}
		
	}
		
	
	// 로그인 메서드 : 사용자 입력 (창 띄우기)
	public void login(){
		System.out.println("로그인을 시작합니다.");
		
		while(true) {
			// 사용자 입력
			System.out.println("아이디를 입력해주세요.");
			String id = Util.sc.nextLine();
			Util.sc.nextLine();
			System.out.println("비밀번호를 입력해주세요.");
			String pw = Util.sc.nextLine();
//			 String id = JOptionPane.showInputDialog("아이디를 입력해주세요.");
//			 String pw = JOptionPane.showInputDialog("비밀번호를 입력해주세요.");	
			
			// ID의 배열 index 찾기
			int index = searchIndex(id);
			if(index>=0) {
				// 해당 index의 비밀번호와 일치 여부 확인
				if(loginInfo.get(index).getPw().equals(pw)) {
					System.out.println(id +"님, 로그인에 성공하였습니다.");
//					 JOptionPane.showMessageDialog(null, id +"님, 로그인에 성공하였습니다.");
					break;
				} else {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
					System.out.println("===========================================");
//					 JOptionPane.showMessageDialog(null, "로그인 정보가 일치하지 않습니다. 다시 시도해주세요.");
				}
			} else {
				System.out.println("아이디가 존재하지 않습니다. 다시 시도해주세요.");
			}
		}	
	}

	
}
