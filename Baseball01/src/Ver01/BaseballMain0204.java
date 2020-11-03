package Ver01;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class BaseballMain0204 implements Menu {

   public static void main(String[] args) throws IOException, ClassNotFoundException {
      // 예매 메인
      
      // 로그인,예매, 회원정보 조회를 위한 인스턴스 생성
      LoginInfoManager user = new LoginInfoManager();
      PointZoneManager pointZone = new PointZoneManager();
      // BookingManager booking=new BookingManager();
      UserInfoManager info = new UserInfoManager();
      
      
      // 메인 메뉴 만들기 - 메뉴 부분 인터페이스 차후 수정
      int select;
      while(true) {
         System.out.println("********2020 포스트시즌 야구 예매********");
         System.out.println("             "+ LOGIN+".로그인                        ");
         System.out.println("              "+BOOKING+".예매                       ");
         System.out.println("            "+INFO+".회원정보                         ");
         System.out.println("        "+SAVE+".예매내역 외부 파일로 저장             ");
         System.out.println("             "+ EVENT+".이벤트                       ");
         System.out.println("           "+EXIT+".페이지 닫기                         ");
         System.out.println("**********************************");
         System.out.println("원하는 메뉴의 숫자를 입력하세요 >> ");
         try {
         select = Util.sc.nextInt();
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
            user.loginMain();
            break;
//        case BOOKING:
//        	booking.Bookingmain();
//            break;
         case INFO:
            info.showMyInfo();
            break;
         case SAVE:
         //   SaveManager.save();
            break;
         case EVENT:
            pointZone.pointZone();
            break;
         case EXIT:
            System.out.println("예매창을 닫습니다.");
            System.exit(0);
         }
         

   }

      
      
      
      
      
      

   }

}