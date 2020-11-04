package Ver01;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class BaseballMain implements Menu {

   public static void main(String[] args) throws IOException, ClassNotFoundException {
      // 예매 메인
      
      // 로그인,예매, 회원정보 조회를 위한 인스턴스 생성
      LoginInfoManager user = new LoginInfoManager();
      BookingManager booking=new BookingManager();
      UserInfoManager info = new UserInfoManager();
      int select;
      
      
      // 메인 메뉴 만들기 - 메뉴 부분 인터페이스 차후 수정
      
      System.out.println("\n 2020 포스트시즌 야구 예매 프로그램에 오신 것을 환영합니다!");
      while(true) {
    	  
    	  if(user.NOWID==null) {

         System.out.println("\n ■■■■■■■■■ HOME MENU ■■■■■■■■\n");       
         System.out.println("\t"+ LOGIN+".로그인 ");
         System.out.println("\t"+BOOKING+".예매 ");
         System.out.println("\t"+ EVENT+".이벤트");
         System.out.println("\t"+INFO+".마이페이지 ");
         System.out.println("\t"+EXIT+".종료");
         System.out.println("\n ■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
         System.out.print("  SELECT MENU >> ");

        try {
           select = Util.sc.nextInt();
           Util.sc.nextLine();
           if(select<0 || select>6) {
              BadMenuException e = new BadMenuException(select);
              throw e;
         } 
         } catch(BadMenuException | InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
            Util.sc.nextLine();
            continue;
         }
         
         switch(select) {
         case LOGIN: 
            if(user.NOWID==null) {
            user.loginZone();
            }else {
               user.changeLoginInfo();
            }            
            break;
           case BOOKING: 
          booking.Bookingmain();
            break;
         case EVENT:
            user.pointZone();
            break;
         case INFO:
           info.showMyInfo();
            break;
         case EXIT:
            System.out.println("예매창을 닫습니다.");
            user.saveInfo();
            System.exit(0);
         }
    	  } else {
    		  System.out.println("\n ■■■■■■■■■ HOME MENU ■■■■■■■■\n");       
     	         System.out.println("\t"+BOOKING_C+".예매 ");
    	         System.out.println("\t"+ EVENT_C+".이벤트");
    	         System.out.println("\t"+INFO_C+".마이페이지 ");
    	         System.out.println("\t"+EXIT_C+".종료");
    	         System.out.println("\n ■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
    	         System.out.print("  SELECT MENU >> ");

    	        try {
    	           select = Util.sc.nextInt();
    	           Util.sc.nextLine();
    	           if(select<0 || select>6) {
    	              BadMenuException e = new BadMenuException(select);
    	              throw e;
    	         } 
    	         } catch(BadMenuException | InputMismatchException e) {
    	            System.out.println("잘못된 입력입니다. 다시 입력하세요.");
    	            Util.sc.nextLine();
    	            continue;
    	         }
    	         
    	         switch(select) {
    	         case BOOKING_C: 
    	          booking.Bookingmain();
    	            break;
    	         case EVENT_C:
    	            user.pointZone();
    	            break;
    	         case INFO_C:
    	           info.showMyInfo();
    	            break;
    	         case EXIT_C:
    	            System.out.println("예매창을 닫습니다.");
    	            user.saveInfo();
    	            System.exit(0);
    	  }

   }

      
      
      
      
      
      

   }

}
}