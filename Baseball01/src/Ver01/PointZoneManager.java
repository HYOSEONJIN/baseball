package Ver01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PointZoneManager {
	// Made by 효선 [관련내용은 카톡주세요~~!]
	

	String name = "hyoseon"; 
	int point = 1000; 			// 나중에 수정할 것 (받아올 것) 

	
	
	// 게임1 메서드
	public void pointGame1() {
		
		int buyGame = -100;
		int win= +300;
		int draw=50;
		
		String cause = "숫지게임 포인트 지급";
		String game = "숫자 게임 참여";
		
		if(point==0) {
			System.out.println("게임에 참여할 포인트가 모자라요 ToT");
			System.out.println("현재 보유 포인트는 " + point + "point 입니다.");
			return;
		}
		System.out.println("**가위바위보 GAME**");
		// 한번 참여시 100포인트 차감, 승리시 300포인트 돌려주고 비기면 50, 지면 0포인트 돌려줌
		System.out.println("1.가위 2.바위 3.보     ** 취소 : 0 **");
				
		int rsp = Util.sc.nextInt();
		Util.sc.nextLine();
		
		if (rsp==0) {
			System.out.println("게임을 종료합니다");
			return;
		}	
		
		else {
		//포인트 차감
			try {
			point+=buyGame;
			pointHistory(name, buyGame, game);		
			
		// 경우의수 시작
			int rspCom=randomrsp();
			if(randomrsp()==rsp) {
				System.out.println("비겼습니다 :)");
				System.out.println("컴퓨터는 "+randomrsp());
				point+=draw;
				pointHistory(name, draw, cause);
				}else if((rsp==1 &&rspCom==2) || (rsp==2 && rspCom==3) || (rsp==3 && rspCom==1)) {
				System.out.println("패배했습니다 ToT");
				System.out.println("컴퓨터는 "+randomrsp());
			}else if((rsp==1 &&rspCom==3) || (rsp==2 && rspCom==1) || (rsp==3 && rspCom==2)) {
			System.out.println("승리했습니다 !");System.out.println("컴퓨터는 "+randomrsp());
				point+=win;
				pointHistory(name, win, cause);
		}else {
			System.out.println("컴퓨터는 "+randomrsp());
		}
		}	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}

	// 게임1 컴퓨터 가위바위보 랜덤
	int randomrsp() {
		int result = (int)(Math.random()*3+1);
		return result;	
	}
	
	
	
	
	
	
	
	// 게임2 메서드
	public void pointGame2()  {	
		// 한번 참여시 100포인트 차감, 뽑기 성공시 500포인트로 돌려줌. 
		int buyGame = -100;
		int win = 500;
		String cause = "랜덤뽑기 당첨 포인트 지급";
		String game = "랜덤뽑기 게임 참여";
	
		
		if(point==0) {
			System.out.println("게임에 참여할 포인트가 모자라요 ToT");
			System.out.println("현재 보유 포인트는 " + point + "point 입니다.");
			return;
		}
		System.out.println("뽑기 GAME~~! 당첨확률은 25%입니다 :)");

		System.out.println("1-100까지 원하는 숫자를 고르세요!   ** 취소 : 0 **");
		int gameNumber = Util.sc.nextInt();	
		
		if(gameNumber==0) {
			System.out.println("게임을 취소합니다");
			return;
		}else {
		
			try {
		if(randomBox(gameNumber)==1) {
			point+=buyGame;
			pointHistory(name, buyGame , game);
			System.out.println("당첨입니다 :) 500POINT가 지급됩니다");
			point+=win;
			pointHistory(name, win, cause);
		} else {
			point+=buyGame;
			pointHistory(name, buyGame, game);
			System.out.println("꽝입니다 ToT");
			
		}} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	

	
	
	// 포인트 적립 내역 저장 메서드
	void pointHistory(String name, int p, String cause) throws IOException { 
		// 사용자의 이름(String) or 회원번호(int)를 받을 예정 + 포인트 적립얼마했는지 int p로 받는다.
		
		String point= ""+p; // int 포인트를 String으로 바꿈 
		
		String txt=name.concat("point.txt"); 
		File pointHistoryFile = new File(txt);
		BufferedWriter out =null;
		
		if(!pointHistoryFile.exists()) {
			out = new BufferedWriter(new FileWriter(txt));
			out.write(point+"\t"+cause);
			out.newLine();
			out.close();
		} else {
			out = new BufferedWriter(new FileWriter(txt,true));
			PrintWriter pw=new PrintWriter(out,true);
			pw.write(point+"\t"+cause+"\n");	
			pw.close();
			
		}
		

	}
	
	
	// 포인트 적립 내역 출력 메서드
	void pointHistoryInfo(String name) {
		
		String txt=name.concat("point.txt");			
		File pointHistoryFile = new File(txt);
		BufferedReader in = null;
		
		if(!pointHistoryFile.exists()){
			System.out.println("포인트 사용 내역이 없습니다.");
		}else {
			System.out.println("--------------------");
			System.out.println("포인트 |\t사용내역");
			try {
				in=new BufferedReader(new FileReader(txt));
				String str=null;
				while(true) {
					str=in.readLine();
					if(str==null) {
						break;
					}
					System.out.println(str);
				}
				System.out.println("--------------------");
				System.out.println("잔여포인트는 " + point + " point");
				System.out.println("--------------------");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	// 포인트로 굿즈 구매 메서드
	
	public void buyGoods() {
		System.out.println("--------------------------------------");
		System.out.println("현재 보유 포인트는 " + point + "point 입니다.");
		System.out.println("--------------------------------------");
		System.out.println("1. 히어로즈 야구점퍼 (1000point)");
		System.out.println("2. 히어로즈 슬로건 (700point)");
		System.out.println("3. 히어로즈 마스코트인형 (500point)");
		System.out.println("--------------------------------------");
		
		int price =0;
		String cause= null;
		
		int choice=Util.sc.nextInt();
		
		if(choice==1) {
			price=-1000;
			cause="야구점퍼 구매";
		}else if(choice==2) {
			price=-700;
			cause="슬로건 구매";
		}else if(choice==3) {
			price=-500;
			cause="마스코트 인형 구매";
		}
		
		if(price>point) {
			System.out.println("포인트가 부족합니다.");
		}else {
			try {
			System.out.println("구매완료!");
			point+=price;			
			pointHistory(name, price, cause);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	
		
		
	}
	
}
	
	
