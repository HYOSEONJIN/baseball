package Ver01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import Ver01.LoginInfo;


public class UserInfoManager  extends LoginInfoManager{
	
	UserInfo userInfo;


	LoginInfo l = new LoginInfo();
	LoginInfo l1 = new LoginInfo();
	
	


	 // 자가정보 조회    ++++주량 

	public void showMyInfo() throws ClassNotFoundException, IOException{

	      if(NOWID==null) {
	         System.out.println("로그인이 필요합니다");
	         return;
	      }else {
	    	  System.out.println("회원정보페이지입니다.");
	      }
	    	  
	      while(true) {
	      System.out.println("1. 예매조회");
	      System.out.println("2. 이벤트조회");
	      System.out.println("3. 회원정보조회");
	      System.out.println("4. 비밀번호 변경");
	      System.out.println("5. 충전하기");
	      System.out.println("6. 탈퇴하기");
	      System.out.println("7. 나가기");
	      System.out.println("\n>>");
 
	      // 사용자 선택
	      int select =0;
	      
	      try {
	    	 select =Util.sc.nextInt();
	             // 메뉴 외 입력 시 예외처리
	            if( select<1 && select>7 ) {
	            	 BadMenuException e = new   BadMenuException (select);
	            throw e;   
	            }
	          
	            switch(select) {
		         case 1:
		            displayInfo(); //예매조회
		            break;
		         case 2:
		            showEvent1();  //이벤트조회
		            break;
		         case 3:
		        	 showAllinfo();  //회원정보 조회
			            break;
		         case 4:
		        	 changeLoginInfo();  //ID/PW 변경"
			            break;
		         case 5:
		            charge(); // 충전하기
		            break;
		         case 6:
			            deleteInfo(); //회원탈퇴
			            break;
			            
		         case 7:   //나가기
		            return;
	            }         
	            
	      }catch(InputMismatchException  | BadMenuException e) {
				System.out.println("올바르지 않은 입력입니다.");
				Util.sc.nextLine();
				continue;
			}
	      }
	}
	
	
	      
	//}
 
	   
	   // searchInfo() 메서드로 자가정보에 해당하는 index 찾아서 해당 index 정보 출력
	   // id/pw 출력
	   // 보유 금액, 보유 포인트 출력


	
	private void changeInfor() {
		// TODO Auto-generated method stub
		
	}



	//예매이력(보유 금액, 보유 포인트 출력)
	public void displayInfo(){
	      // id/pw 출력

	      if( loginInfo.size() == 0) {
	         System.out.println("조회 할 정보가 없습니다.");
	         return;
   	       }
	      int index = searchIndex(NOWID);
	 
	      for(int i = 0; i<loginInfo.size();i++) {
	         // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
	    	  if (loginInfo.get(i).getId().equals(NOWID)) {
	              NOWID = NOWID;
	              INDEX = i;
	          } else {
	            System.out.println(NOWID + " 아이디는 존재하지 않음.");
	         }
	      }
	      
	      if(INDEX <0) {
	         System.out.println("일치하는 아이디 "+NOWID+"의 정보가 존재하지 않습니다.");
	         System.out.println("메뉴로 이동합니다.");
	      }else {
	        System.out.println("예매 내역입니다.");
	     //   loginInfo.get(index).mySeatView() ; 
	      } 
		
		
}
		
		


	//이벤트내역 조회
	public void showEvent1() {
	 
	   // id/pw로 확인
	   if(loginInfo.size() == 0) {
	      System.out.println("유저등록된 유저 정보가 없음.");
	      return;
	   }
	   
	   System.out.println("이벤트내역 입니다");
	   

	   for(int i = 0; i<loginInfo.size(); i++) {
	      // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
	      if(loginInfo.get(i).getId().equals(NOWID)){
              INDEX = i;
              
	         System.out.println("이벤트 내역이 존재하지 않음.");
	      }
	   }   
	   if(NOWID == null) {
	      System.out.println("일치하는 아이디 "+NOWID+"의 정보가 존재하지 않습니다.");
	      System.out.println("메뉴로 이동합니다.");

	   }else {
		   pointHistoryInfo(NOWID);
	   
	   }

	}

		
		// 충전하기
		void charge() {
			 
			// 현재 로그인 계정의 충전금액/포인트 받아놓기
//			int myMoney = loginInfo.get(INDEX).getMyMoney(); 
//			int point = loginInfo.get(INDEX).getPoint(); 
			
			// 현재 로그인 계정 삭제
			loginInfo.remove(INDEX);
			
			
			// ID/PW 입력 받아 새로운 배열 저장
			System.out.println("충전 할 금액을 입력해주세요");
			int myMoney = Util.sc.nextInt();	
			Util.sc.nextLine();
			l.getMyMoney();	
		//	l.addInfo(new LoginInfo (myMoney));
					
			
		
				
			System.out.println("충전이 완료되었습니다");
			System.out.println("충전 후 금액입니다.:"+l.getMyMoney());
			System.out.println("-----------------------------------");
	  
			  }
		
		//충천 후 금액
		 void chargeResult() {
			  System.out.println("충전 후 금액입니다.:"+l.getMyMoney());
		  }
		
		
	


	// 회원탈퇴 메서드
	public void deleteInfo( ) {
		
	   
	   System.out.println("탈퇴할 정보를 입력해주세요.");
	   System.out.println("비밀번호:");
	   int pass = Util.sc.nextInt();
	   
	   int index = -1;
	   
	   for(int i = 0; i<loginInfo.size();i++) {
	      // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
	      if (loginInfo.get(i).getId().equals(NOWID)
	            && loginInfo.get(i).getPw().equals(pass)) {  
	    	index = 1;
	      
	      } else {
	         System.out.println(NOWID + "님은 정상적으로 탈퇴되었습니다.");
	      }
	   }
	   
	   if(index <0) {
	         System.out.println("아이디 "+NOWID+"님의 정보가 존재하지 않습니다.");
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
	
	
	//전체 정보 조회
	
	public void showAllinfo () {
		 
		   // id/pw로 확인
		   if(loginInfo.size() == 0) {
		      System.out.println("유저등록된 유저 정보가 없음.");
		      return;
		   }
		   
		   System.out.println(NOWID+"회원정보내역 입니다");
		   

		   for(int i = 0; i<loginInfo.size(); i++) {
		      // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
		      if(loginInfo.get(i).getId().equals(NOWID)){
	              INDEX = i;
	          	System.out.println("티켓명:");
				System.out.println("이름:"+ loginInfo.get(i).getId());
				System.out.println("경기일:"+ loginInfo.get(i).getId());
				System.out.println("좌석정보:"+ loginInfo.get(i).getId());
				System.out.println("포인트:"+ loginInfo.get(i).getId());
				System.out.println("충전금액:"+ loginInfo.get(i).getMyMoney());
		         System.out.println("회원정보내역이 존재하지 않음.");
	            //  userInfo.showInfo();
		      }
		   }   
		   if(NOWID == null) {
		      System.out.println("일치하는 아이디 "+NOWID+"의 정보가 존재하지 않습니다.");
		      System.out.println("메뉴로 이동합니다.");

		   }else {


		   }



	}



}



	