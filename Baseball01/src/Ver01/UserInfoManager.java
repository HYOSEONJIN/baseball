package Ver01;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;




public class UserInfoManager  extends LoginInfoManager{
	

	//LoginInfoManager log = LoginInfoManager.getInstance();  

	PointZoneManager pointZoneManager = new PointZoneManager(); //포인트
	
	
	
//	List<LoginInfo> loginInfo;
//	LoginInfo info = new LoginInfo();
//	
	
	
//	// 외부에서 참조변수 받을 수 있는 메서드
//	public static LoginInfoManager getInstance() {
//		return manager;
//	}
	
	/*  public void showMyInfo()  {
	         
	         while(true) {
	         System.out.println("1.예매조회");
	         System.out.println("2.환불조회" );
	         System.out.println("3.이벤트내역조회");
	         System.out.println("4.탈퇴하기");
	         System.out.println("5.나가기");
	         int select = Util.sc.nextInt();
	         Util.sc.nextLine();
	         
	         switch(select) {
	         case 1:
	            displayInfo(); //예매조회
	            break;
//	         case 2:
//	            cancleInfo(); //환불조회
//	            break;
	         case 3:
	            showEvent();  //이벤트조회
	            break;
	         case 4:
	            deleteInfo(); //회원탈퇴
	            break;
	         case 5:   //나가기
	            return;
	                     
	         }

	         }   
	      } */
	   

	   // 자가정보 조회    ++++주량 
	

	public void showMyInfo(){
	      if(NOWID==null) {
	         System.out.println("로그인이 필요합니다");
	         return;
	      }

	      while(true) {
	      System.out.println("1. 예매조회");
	      System.out.println("2. 이벤트조회");
	      System.out.println("3. 회원탈퇴");
	      
	      System.out.println("\n>>");
	      
	      // 사용자 선택
	      int select =0;
	      
	      try {
	    	  
	    	 select =Util.sc.nextInt();
	 
	            //  메뉴 외 입력 시 예외처리
	            if( select!=1 && select!=5 ) {
	            	 BadMenuException e = new   BadMenuException (select);
	            throw e;   
	            }
	          
	            switch(select) {
		         case 1:
		            displayInfo(); //예매조회
		            break;
		         case 2:
		            showEvent();  //이벤트조회
		            break;
		         case 3:
		            deleteInfo(); //회원탈퇴
		            break;
		         case 4:   //나가기
		            return;
	            }         
	            
	      }catch(InputMismatchException  | BadMenuException e) {
				System.out.println("올바르지 않은 입력입니다.");
				Util.sc.nextLine();
				continue;
			}
	}
	
	      
	}
 
	   
	   // searchInfo() 메서드로 자가정보에 해당하는 index 찾아서 해당 index 정보 출력
	   // id/pw 출력
	   // 보유 금액, 보유 포인트 출력


	//본인 아이디 비밀번호 입력 후 조회 
	//예매이력(보유 금액, 보유 포인트 출력)
	public void displayInfo(){
	      // id/pw 출력
	      if( loginInfo.size() == 0) {
	         System.out.println("조회 할 정보가 없습니다.");
	         return;
	      }
	      
	      System.out.println("예매 확인 할 아이디 입력해주세요:");
	      String id = Util.sc.nextLine();

	      
	      int index = -1;
	      
	      for(int i = 0; i<loginInfo.size();i++) {
	         // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
	         if (loginInfo.get(i).getId().equals(NOWID)) {  
	            index = i;
	         } else {
	            System.out.println(NOWID + " 아이디는 존재하지 않음.");
	         }
	      }
	      
	      if(index <0) {
	         System.out.println("일치하는 아이디 "+NOWID+"의 정보가 존재하지 않습니다.");
	         System.out.println("메뉴로 이동합니다.");
	      }else {
	        System.out.println("예매 내역입니다.");
	     //   loginInfo.get(index).mySeatView() ; //현재 접속한 유저 값으로 설정하기??
	      } 
		
		
		/*
		// id/pw 출력
				if (loginInfo.size() > 0) {
					for (int i = 0; i < loginInfo.size(); i++) {
						// 입력된 tempUserId가 저장된 유저 정보에 존재하는지 찾음
						if (loginInfo.get(i).getId().equals(id)) {
							loginInfo = (List<LoginInfo>) loginInfo.get(i);
						}
					}

					
					if (loginInfo != null) {
						System.out.println("id : " + loginInfo.getId());
						System.out.println("pw : " + loginInfo.getPass());
						// 보유 금액, 보유 포인트 출력
						System.out.println("user money : " + loginInfo.getMyMoney());
						System.out.println("user point : " + loginInfo.getPoint());
						// 예매이력 출력
						if(loginInfo.viewAll() != null) {
							loginInfo.getBookInfo().printBookInfo();
	         			}else {
							System.out.println(id + "예매 이력 없음.");

						}
					
					}else  {
						System.out.println(tempUserId + "라는 아이디는 존재하지 않음.");
					}
				} else {
					System.out.println("등록된 유저정보 없음");
				}*/
}
		
		
		
		
	      
	/*
	//횐불이력
	public void cancleInfo(){
	   // id/pw로 확인
	   if(log.size() == 0) {
	      System.out.println("유저등록된 유저 정보가 없음.");
	      return;
	   }
	   
	   System.out.println("환불 내역 메뉴입니다");
	   System.out.println(" 조회 할 아이디를 입력해주세요: ");
	   String id = Util.sc.nextLine();
	   System.out.println("비밀번호: ");
	   int pass = Util.sc.nextInt();
	   
	   int index = -1;
	   
	   for(int i = 0; i<log.size();i++) {
	      // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
	      if (log.get(i).getId().equals(id)
	            && log.get(i).getPw().equals(pass)) {    
	         index = i;
	      } else {
	         System.out.println("환불 내역이 존재하지 않음.");
	      }
	   }
	   
	   if(index <0) {
	      System.out.println("일치하는 아이디 "+id+"의 정보가 존재하지 않습니다.");
	      System.out.println("메뉴로 이동합니다.");
	   }else {
	   System.out.println("");   
	    log.get(index)).refondInfo(); //전체 환불 출력에서 환불내역 가져오기..?
	   }
	}  */



	//PointZoneManager pointZoneManager = new PointZoneManager(); //포인트 클래스 

	//이벤트내역 조회
	public void showEvent() {
	   
	   // id/pw로 확인
	   if(loginInfo.size() == 0) {
	      System.out.println("유저등록된 유저 정보가 없음.");
	      return;
	   }
	   
	   System.out.println("이벤트내역 입니다");
	   System.out.println(" 조회 할 아이디를 입력해주세요: ");
	   String id = Util.sc.nextLine();
	   
	   String name = null;
	   
	   for(int i = 0; i<loginInfo.size(); i++) {
	      // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
//	      if (log.get(i).getId().equals(id)) {    
//	         index = i;
	      if(loginInfo.get(i).getId().equals(id)){
	      } else {
	         System.out.println("이벤트 내역이 존재하지 않음.");
	      }
	   }
	   
	   if(name != null) {
	      System.out.println("일치하는 아이디 "+id+"의 정보가 존재하지 않습니다.");
	      System.out.println("메뉴로 이동합니다.");

	   }else {
	      pointZoneManager.pointHistoryInfo(name); 
	   
	   }

	}

	   
	// 회원탈퇴 메서드
	public void deleteInfo() {
	   if(loginInfo.size() == 0) {
	      System.out.println("유저등록된 유저 정보가 없음.");
	      return;
	   }
	   
	   System.out.println("탈퇴할 아이디를 입력해주세요.");
	   String id = Util.sc.nextLine();
	   System.out.println("비밀번호 : ");
	   int pass = Util.sc.nextInt();
	   
	   int index = -1;
	   
	   for(int i = 0; i<loginInfo.size();i++) {
	      // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
	      if (loginInfo.get(i).getId().equals(id)
	            && loginInfo.get(i).getPw().equals(pass)) {  
	         index = i;
	      } else {
	         System.out.println(id + "라는 아이디는 존재하지 않음.");
	      }
	   }
	   
	   if(index <0) {
	         System.out.println("일치하는 아이디 "+id+"의 정보가 존재하지 않습니다.");
	         System.out.println("메뉴로 이동합니다.");
	      }else {
	         if(NOWID != null){
	           NOWID = null;
	         }
	
	         loginInfo.remove(index);
	         System.out.println("정상적으로 탈퇴되었습니다.");
	      } 
	      
	      if(NOWID== null) {
	         System.out.println("로그아웃이 되었습니다.");
	      }
	
	}   
}
	   
	


