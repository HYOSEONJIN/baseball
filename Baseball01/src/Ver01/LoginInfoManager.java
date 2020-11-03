package Ver01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class LoginInfoManager implements Menu {
	
	// 생성자
	public LoginInfoManager() {
		super();
	}

	// 변수 상수화 
	static String NOWID ;	// 현재 로그인 ID
	static int INDEX; 		// 현재 로그인 ID index
	static int POINT;		// 현재 로그인 ID 포인트
	
	// 로그인정보 배열 생성
	static ArrayList<LoginInfo> loginInfo = new ArrayList<LoginInfo>();	 
	
	// 로그인 메뉴 메서드
	public void loginMain() throws IOException { 
		
		while(true) {
	         System.out.println("************ L O G I N ************");
	         System.out.println("\n           "+LOG+". 로그인");
	         System.out.println("           "+JOIN+". 회원가입");
	         System.out.println("           "+HOME+ ". 홈 메뉴로 돌아가기");
	         System.out.println("\n***********************************");
	         
	         // 사용자 메뉴 선택
	         int select=0;
	         
	         try {
	            select = Util.sc.nextInt();
	            Util.sc.nextLine();
	            // 예외처리
	            if( !(select>0 && select<4) ) {
	            BadMenuException e = new BadMenuException(select);
	            throw e;   
	            } 
	         } catch(BadMenuException | InputMismatchException e) {
	            System.out.println("잘못된 입력입니다. 메뉴 번호를 다시 선택해주세요.");
	            Util.sc.nextLine();      
	            return;
	         }         
	         
	         switch(select) {
	         	case LOG : 
	         		login();
					try {
						saveLogin();
					} catch (ClassNotFoundException | IOException e) {
	
						e.printStackTrace();
					}
	         		return;
	         	case JOIN :
	         		joinMember();
	         		return;
	         	case HOME : 
	         		return;
	         }
		}
	}
	
	
	// 로그인 메서드 : 로그인 -> 사용자의 로그인 ID 반환
	public String login(){ 
		String id = null;	
		
		while(true) {
			// 사용자 입력
			System.out.println("\n 아이디 : ");
			id = Util.sc.nextLine();

			System.out.println("비밀번호 : ");
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
				System.out.println("존재하지 않는 아이디입니다. 다시 시도해주세요.");	
				System.out.println("홈 메뉴로 돌아가시려면 \"home\"을 입력하세요.");
				String insert = null;
				insert = Util.sc.nextLine();
				if(insert=="home") {
					break;
				} else {
					continue;
				}
				
			}
		}
		return id;	
	}	
	
	
	// 배열에 정보 저장 메서드
	private void addInfo(LoginInfo info) {		
		loginInfo.add(info);
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
		
	
	// 회원가입 메서드
	//		ID 입력 받기 -> ID 중복 확인 -> 비밀번호 입력 받기 -> 회원가입 완료
	public void joinMember() throws IOException{
		System.out.println("회원가입을 시작합니다.");
		
		// 아이디 중복 확인 (무한반복)
		while(true) {
			System.out.println("\n아이디 : ");
			String id = Util.sc.nextLine().trim();
			String pw = null;

			int index = searchIndex(id);
			if(searchIndex(id)>=0) {
				System.out.println("중복되는 아이디가 존재합니다. 다른 아이디를 입력해주세요.");
				continue;
			} else {
				System.out.println("비밀번호 : ");
				pw = Util.sc.nextLine().trim();
				addInfo(new LoginInfo(id, pw));
					System.out.println(id+"님, 가입을 축하드립니다!");
					break;
			}
			
		}
	}
	
	// 파일 저장 메서드
	void saveLogin() throws IOException, ClassNotFoundException{
		 
	      // 인스턴스 저장을 위한 스트림 생성
	      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("LoginInfo.ser"));   
	      out.writeObject(loginInfo);
	      out.close();
	      // 인스턴스 복원을 위한 스트림 생성
	      ObjectInputStream in = new ObjectInputStream(new FileInputStream("LoginInfo.ser"));
	      // 복원
	      LoginInfo reInfo = (LoginInfo) in.readObject();
		  
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

}
