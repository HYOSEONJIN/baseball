package Ver01;

import javax.swing.JOptionPane;

import Ver01.Util;

public class BaseballMain_Jungeun {

	public static void main(String[] args) throws BadMenuException {

		UserInfoManager manager = UserInfoManager.getInstance();
		
		System.out.println("==== 야구경기 예매 프로그램 ====");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("==========================");
		
	      // 메뉴 1,2외 입력 시 예외처리
	      int select = 0;
	         select = Util.sc.nextInt(); 
//	         Util.sc.nextLine();
	         
	      // 로그인/회원가입
	      if(select==1) {
	    	  System.out.println("1번 선택");
	          manager.login();
	      } else if(select==2) {
	         System.out.println("2번 선택");         
	         manager.addProfile();
	      }
		

	}

}
