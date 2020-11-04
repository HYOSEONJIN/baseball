package Ver01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import Ver01.LoginInfo;


public class UserInfoManager  extends LoginInfoManager{
   

   
	   BookingManager re = new BookingManager();
	   Seat se = new Seat();
	   ReserveSeat se1 = new ReserveSeat();
	


    // 자가정보 조회  

   public void showMyInfo() throws ClassNotFoundException, IOException{

         if(NOWID==null) {
            System.out.println("로그인이 필요합니다");
            return;
         }else {
            System.out.println("회원정보페이지입니다.");
         }
            
         while(true) {
    	     System.out.println("\n〓〓〓〓〓〓〓〓   MY PAGE 〓〓〓〓〓〓〓〓〓\n");
             System.out.println("\t"+SHOWBOOK+". 예매 조회");
             System.out.println("\t"+EVETALL+". 이벤트 조회");
             System.out.println("\t"+MEMBER+". 회원정보 조회");
             System.out.println("\t"+CHANGE+". ID/PW 변경");
	         System.out.println("\t"+DELETE+" 탈퇴하기");
	         System.out.println("\t"+EXIT1+". 나가기");
	         System.out.println("\n〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         System.out.print(" SELECT MENU >> ");

	 
         // 사용자 선택
         int select =0;
         
         try {
           select =Util.sc.nextInt();
           Util.sc.nextLine();
                // 메뉴 외 입력 시 예외처리
               if( select<1 && select>7 ) {
                   BadMenuException e = new   BadMenuException (select);
               throw e;   
               }
             
               switch(select) {
               case SHOWBOOK:
                  displayInfo(); //예매조회
                  break;
               case EVETALL:
                  showEvent1();  //이벤트조회
                  break;
               case MEMBER:
                  showAllinfo();  //회원정보 조회
                     break;
               case CHANGE:
                  changeLoginInfo();  //ID/PW 변경"
                     break;
         
               case DELETE:
                     deleteInfo(); //회원탈퇴
                     break;
                     
               case EXIT1:   //나가기
                  return;
               }         
               
         }catch(InputMismatchException  | BadMenuException e) {
            System.out.println("올바르지 않은 입력입니다.");
            Util.sc.nextLine();
            continue;
         }
         }
   }
   
   
         

   



   //예매이력(보유 금액, 보유 포인트 출력)
   public void displayInfo(){
         // id/pw 출력

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
           ReserveSeat.mySeatView(); 
         } 
      
      
}
      
      


   //이벤트내역 조회
   public void showEvent1() {
    

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

      
   
      
      
   

   // 회원탈퇴 메서드
   public void deleteInfo( ) {
      

      System.out.println("탈퇴할 정보를 입력해주세요.");
      System.out.println("비밀번호:");
      String pass = Util.sc.nextLine();
      

     
         // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
         if (loginInfo.get(INDEX).getPw().equals(pass)) {  
            loginInfo.remove(INDEX);
          System.out.println("정상적으로 탈퇴 되었습니다.");
          
          NOWID = null;
          return;
         }   else {
            System.out.println("아이디 "+NOWID+"님의 정보가 존재하지 않습니다.");
            System.out.println("비밀번호가 틀렸습니다.");
         }
         } 

     
   
   
   //전체 정보 조회
   
   public void showAllinfo () {
       
 
         System.out.println(NOWID+"회원정보내역 입니다");
         

         for(int i = 0; i<loginInfo.size(); i++) {
            // 입력된 아이디와 패스워드가 일치하는 정보를 찾음.
            if(loginInfo.get(i).getId().equals(NOWID)){
                 INDEX = i;

            System.out.println("아이디:"+ loginInfo.get(i).getId());            
            System.out.println("좌석정보:"+ se.getSeatNum());
            System.out.println("포인트:"+ loginInfo.get(i).getPoint());
            System.out.println("현재 충전금액:"+ loginInfo.get(i).getMyMoney());
          
            }
         }   
         if(NOWID == null) {
            System.out.println("일치하는 아이디 "+NOWID+"의 정보가 존재하지 않습니다.");
            System.out.println("메뉴로 이동합니다.");

         }else {


         }



   }



}
