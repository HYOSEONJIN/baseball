package Ver01;

import Ver01.Util;

public class PointZoneManager {
	// Made by 효선 [관련내용은 카톡주세요~~!]
	

	int point = 1000; // 나중에 수정할 것 (받아올 것)

	
	
	// 게임1 메서드
	public void pointGame1() {
		System.out.println("**가위바위보 GAME**");
		// 한번 참여시 100포인트 차감, 승리시 300포인트 돌려주고 패배하면 50, 지면 0포인트 돌려줌
		System.out.println("1.가위 2.바위 3.보     ** 취소 : 0 **");
				
		int rsp = Util.sc.nextInt();
		
		if (rsp==0) {
			System.out.println("게임을 종료합니다");
			return;
		}	
		else {
		//포인트 차감
			point-=100;
		// 경우의수 시작
			int rspCom=randomrsp();
			if(randomrsp()==rsp) {
				System.out.println("비겼습니다 :)");
				point+=50;
			}else if((rsp==1 &&rspCom==2) || (rsp==2 && rspCom==3) || (rsp==3 && rspCom==1)) {
				System.out.println("패배했습니다 ToT");
			}else if((rsp==1 &&rspCom==3) || (rsp==2 && rspCom==1) || (rsp==3 && rspCom==2)) {
			System.out.println("승리했습니다 !");
				point+=300;
		}
		}
		
	}

	// 게임1 컴퓨터 가위바위보 랜덤
	int randomrsp() {
		int result = (int)(Math.random()*3+1);
		return result;	
	}
	
	
	
	
	
	
	
	// 게임2 메서드
	public void pointGame2() {		
		System.out.println("뽑기 GAME~~! 당첨확률은 25%입니다 :)");
		// 한번 참여시 100포인트 차감, 뽑기 성공시 500포인트로 돌려줌.
		System.out.println("1-100까지 원하는 숫자를 고르세요!   ** 취소 : 0 **");
		int gameNumber = Util.sc.nextInt();	
		
		if(gameNumber==0) {
			System.out.println("게임을 취소합니다");
			return;
		}else {
			
		if(randomBox(gameNumber)==1) {
			point-=100;
			System.out.println("당첨입니다 :) 500POINT가 지급됩니다");
			point+=500;
		} else {
			point-=100;
			System.out.println("꽝입니다 ToT");
		}
		}
	}
	
	
	// 게임2 메서드 당첨 랜덤
	
	int randomBox(int gameNumber) {
		
		int [] game = new int[100];
		
		for(int i=0 ; i<100; i++) {
			game[i]=(int)(Math.random()*4+1);
		}
		
		int result=game[gameNumber];
		return result;
	}

	// 포인트로 굿즈 구매 메서드
	
	public void buyGoods() {
		
	}
	
	
	// 포인트로 굿즈를 구매/당첨 된 내역 출력 메서드 -> 나중에 자기정보 조회에서 볼 때 사용할 메서드
	
	public void pointzoneInfo() {
		System.out.println("아직만드는중!");
		
	}

}
