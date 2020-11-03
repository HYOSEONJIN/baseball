package Ver01;

import java.util.ArrayList;
import java.util.List;

public class LoginInfoManager {
	
	// 생성자
	public LoginInfoManager() {
		super();
	}

	// 변수 상수화 
	static String NOWID ;	// 로그인한 사용자 ID
	static int POINT;		// 로그인한 사용자 보유 포인트
	static int INDEX; 		// 로그인한 사용자 ID의 index
	
	// 로그인정보 배열 생성
	static ArrayList<LoginInfo> loginInfo = new ArrayList<LoginInfo>();
	
//	// List<loginInfo> 초기화 
//	// 싱글톤 패턴
//	private LoginInfoManager(int num){
//		loginInfo = new ArrayList<LoginInfo>();
//	}
//	
//	// 내부에서 인스턴스 생성 : 최대 20개의 배열 생성 가능
//	private static LoginInfoManager manager = new LoginInfoManager(20);	
//	
//	// 외부에서 참조변수 받을 수 있는 메서드
//	public static LoginInfoManager getInstance() {
//		return manager;
//	}
	 

	// 배열에 정보 저장 메서드
	private void addInfo(LoginInfo info) {		
		loginInfo.add(info);
	}
	
	
	// 회원가입 메서드
	//		ID 입력 받기 -> ID 중복 확인 -> 비밀번호 입력 받기 -> 회원가입 완료
	public void joinMember() {
		System.out.println("회원가입을 시작합니다.");
		
		// 아이디 중복 확인 (무한반복)
		while(true) {
			System.out.println("아이디를 입력해주세요.");
			String id = Util.sc.nextLine().trim();

			int index = searchIndex(id);
			if(searchIndex(id)>=0) {
				System.out.println("중복되는 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
				continue;
			} else {
				System.out.println("비밀번호를 입력해주세요.");
				String pw = Util.sc.nextLine().trim();
				addInfo(new LoginInfo(id, pw));
				System.out.println(id+"님, 가입을 축하드립니다!");
				break;
			}
			
		}
	}
	
	
	// 배열의 index 검색 메서드
	public int searchIndex(String id) {
		int index = -1;
		for(int i=0; i<loginInfo.size(); i++) {
			if(loginInfo.get(i).getId().equals(id)) {
				index = i;
			}
		}
		return index;
	}
	

	
	// 로그인 정보 변경 메서드 
	//		재로그인 -> 로그인한 계정 ID 반환 -> 반환한 ID에 해당하는 index의 정보 삭제 -> 새 정보 저장
	public void changeLoginInfo() {
		System.out.println("회원정보 확인을 위해 다시 로그인해주세요.");
		// 로그인한 계정 ID 받기
		NOWID = login();
		
		// 반환한 ID에 해당하는 index 정보 삭제
		loginInfo.remove(searchIndex(NOWID));
		
		// 새 정보 저장
		System.out.println("회원정보 변경을 시작합니다.");	
		System.out.println("새 아이디를 입력해주세요.");
		String changedId = Util.sc.nextLine().trim();
		System.out.println("새 비밀번호를 입력해주세요.");
		String changedPw = Util.sc.nextLine().trim();
		addInfo(new LoginInfo(changedId, changedPw)); // 정보 저장
		
	}
		
	
	// 로그인 메서드 : 로그인 -> 사용자의 로그인 ID 반환
	public String login(){ 
		System.out.println("로그인을 시작합니다.");
		String id = null;	
		
		while(true) {
			// 사용자 입력
			System.out.println("아이디를 입력해주세요.");
			id = Util.sc.nextLine();
			System.out.println("비밀번호를 입력해주세요.");
			String pw = Util.sc.nextLine();
			
			// ID의 배열 index 찾기
			int index = searchIndex(id);
			if(searchIndex(id)>=0) {
				// 해당 index의 비밀번호와 일치 여부 확인
				if(loginInfo.get(index).getPw().equals(pw)) {
					System.out.println(id +"님, 로그인에 성공하였습니다.");
					NOWID=id; 
					for(int i=0; i<loginInfo.size(); i++) {
							if(loginInfo.get(i).getId().equals(id)) {
								POINT=loginInfo.get(i).getPoint();
							}
						}
					break;
				} else {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다. 다시 시도해주세요.");
					System.out.println("===========================================");
				}
			} else {
				System.out.println(id +"아이디가 존재하지 않습니다. 다시 시도해주세요.");
			}
		}
		return id;	
	}

	
	// 로그인 메인 메서드 
	public void loginMain() { 
			while(true) {
	         System.out.println("++++++++2020 포스트시즌 야구 예매++++++++");
	         System.out.println("\n로그인 페이지입니다. \n처음 방문하시는 분은 회원가입을 해주세요.");
	         System.out.println("\n1. 로그인");
	         System.out.println("2. 회원가입");
	         System.out.println("+++++++++++++++++++++++++++++++++");
	         
	         // 사용자 메뉴 선택
	         int select=0;
	         
	         try {
	            select = Util.sc.nextInt();
	            Util.sc.nextLine();
	            // 메뉴 1,2 외 입력 시 예외처리
	            if( select!=1 && select!=2 ) {
	            BadMenuException bme = new BadMenuException(select);
	            throw bme;   
	            } 
	         } catch(BadMenuException bme) {
	            System.out.println("잘못된 입력입니다. 메뉴 번호를 다시 선택해주세요.");
	            Util.sc.nextLine();      
	            return;
	         }         
	         
	         // 로그인/회원가입
	         if(select==1) {
	            login();
	            break;      
	         } else if(select==2) {
	            joinMember();
	            break;
	         }         
      
		}
	}	

	
}
