package Ver01;

import javax.swing.JOptionPane;

import Ver01.Util;

public class LoginMain {

	public static void main(String[] args) throws BadMenuException {

		LoginInfoManager manager = LoginInfoManager.getInstance();
		
		System.out.println("******2020 포스트시즌 야구 예매******");
		System.out.println("\n야구 예매 홈페이지에 오신 걸 환영합니다. \n처음 방문하시는 분은 회원가입을 해주세요.");
		System.out.println("\n1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("*******************************");
		
		// 사용자 메뉴 선택
		int select=0;
		
		try {
			select = Util.sc.nextInt();
			// 메뉴 1,2 외 입력 시 예외처리
			if( select!=1 && select!=2 ) {
			BadMenuException bme = new BadMenuException(select);
			throw bme;	
			//continue;
			} 
		} catch(BadMenuException bme) {
			System.out.println("잘못된 입력입니다. 메뉴 번호를 다시 선택해주세요.");
			Util.sc.nextLine();		
			return;
		}			
		
		// 로그인/회원가입
		if(select==1) {
			manager.login();
		} else if(select==2) {
			manager.addProfile();
		}			


	}

}
