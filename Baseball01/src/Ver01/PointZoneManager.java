package Ver01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class PointZoneManager extends LoginInfoManager {
	// Made by 효선 [관련내용은 카톡주세요~~!]

	

	// 포인트존메인메서드 ( 예외처리끝 )
	public void pointZone() throws IOException {
		if(NOWID==null) {
			System.out.println("로그인 먼저 :)");
			return;
		}

		while (true) {
			System.out.println("**********************************");
			System.out.println("\t"+POINT+"POINT 보유중");
			System.out.println("**********************************");
			System.out.println();
			System.out.println("\t1. 숫자게임");
			System.out.println("\t2. 랜덤뽑기");
			System.out.println("\t3. 굿즈구매");
			System.out.println("\t4. 포인트 사용 내역 조회");
			System.out.println("\t5. 포인트존 나가기");
			System.out.println();
		    System.out.println("**********************************");
			try {
				int choice = Util.sc.nextInt();
				
				Util.sc.nextLine();
				if (choice < 1 || choice > 5) {
					PointZoneException e = new PointZoneException(choice);
					throw e;
				}

				switch (choice) {
				case 1:
					pointGame1();
					break;
				case 2:
					pointGame2();
					break;
				case 3:
					buyGoods();
					break;
				case 4:
					pointHistoryInfo(NOWID);
					break;
				case 5:
					return;
				}
			} catch (InputMismatchException | PointZoneException e) {
				System.out.println("올바르지 않은 입력입니다.");
				Util.sc.nextLine();
				continue;
			}

		}

	}

	
	
	
	// 게임1 메서드 ( 예외처리 완료 )
	public void pointGame1() throws IOException {

		int buyGame = -100;
		int win = +300;
		int draw = 50;

		String cause = "숫자게임 포인트 지급";
		String game = "숫자 게임 참여";

		if (POINT < 100) {
			System.out.println("게임에 참여할 포인트가 모자라요 ToT");
			System.out.println("현재 보유 포인트는 " + POINT + "point 입니다.");
			return;
		}

		while (true) {
			System.out.println("**가위바위보 GAME**");
			// 한번 참여시 100포인트 차감, 승리시 300포인트 돌려주고 비기면 50, 지면 0포인트 돌려줌
			System.out.println("1.가위 2.바위 3.보     ** 취소 : 0 **");

			try {
				int rsp = Util.sc.nextInt();

				if (rsp < 0 || rsp > 3) {
					PointZoneException e = new PointZoneException(rsp);
					throw e;
				}

				if (rsp == 0) {
					System.out.println("게임을 종료합니다");
					return;
				}
				POINT += buyGame;
				pointHistory(NOWID, buyGame, game);

				// 경우의수 시작

				switch ((int) (Math.random() * 3 + 1)) {
				case 1:
					if (rsp == 1) {
						System.out.println("비겼습니다");
						POINT += draw;
						pointHistory(NOWID, draw, cause);
					} else if (rsp == 2) {
						System.out.println("패배했습니다 ToT");
					} else if (rsp == 3) {
						System.out.println("승리했습니다 !");
						POINT += win;
						pointHistory(NOWID, win, cause);
					}
					break;
				case 2:
					if (rsp == 1) {
						System.out.println("승리했습니다 !");
						POINT += win;
						pointHistory(NOWID, win, cause);
					} else if (rsp == 2) {
						System.out.println("비겼습니다");
						POINT += draw;
						pointHistory(NOWID, draw, cause);
					} else if (rsp == 3) {
						System.out.println("패배했습니다 ToT");
					}
					break;
				case 3:
					if (rsp == 1) {
						System.out.println("패배했습니다 ToT");
					} else if (rsp == 2) {
						System.out.println("승리했습니다 !");
						POINT += win;
						pointHistory(NOWID, win, cause);
					} else if (rsp == 3) {
						System.out.println("비겼습니다");
						POINT += draw;
						pointHistory(NOWID, draw, cause);
					}
					break;
				}
				return;

			} catch (InputMismatchException e) {
				System.out.println("올바르지 않은 입력입니다.");
				Util.sc.nextLine();
				continue;
			} catch (PointZoneException e) {
				System.out.println("0-3 사이의 숫자만 입력하세요");
				continue;
			}
		}
	}
	
	
	
	

	// 게임2 메서드 ( 예외처리 끝 ) 
	
	public void pointGame2() throws IOException {
		// 한번 참여시 100포인트 차감, 뽑기 성공시 500포인트로 돌려줌.
		int buyGame = -100;
		int win = 500;
		String cause = "랜덤뽑기 당첨 포인트 지급";
		String game = "랜덤뽑기 게임 참여";

		if (POINT < 100) {
			System.out.println("게임에 참여할 포인트가 모자라요 ToT");
			System.out.println("현재 보유 포인트는 " + POINT + "point 입니다.");
			return;
		}
		
		while(true) {
		System.out.println("뽑기 GAME~~! 당첨확률은 25%입니다 :)");
		System.out.println("1-100까지 원하는 숫자를 입력하세요!   ** 취소 : 0 **");
		
		try {
			
		int gameNumber = Util.sc.nextInt();
		
		if(gameNumber<0 || gameNumber>100) {
			PointZoneException e = new PointZoneException(gameNumber);
			throw e;			
		}

		if (gameNumber == 0) {
			System.out.println("게임을 취소합니다");
			return;
		} else {

				if (randomBox(gameNumber) == 1) {
					POINT += buyGame;
					pointHistory(NOWID, buyGame, game);
					System.out.println("당첨입니다 :) 500POINT가 지급됩니다");
					POINT += win;
					pointHistory(NOWID, win, cause);
					break;
				} else {
					POINT += buyGame;
					pointHistory(NOWID, buyGame, game);
					System.out.println("꽝입니다 ToT");
					break;

				}
			}
		}catch (InputMismatchException e) {
				System.out.println("올바르지 않은 입력입니다.");
				Util.sc.nextLine();
				continue;
		}catch (PointZoneException e) {
				System.out.println("0-100 사이의 숫자만 입력하세요");
				continue;
		}
		}
		}
	

	// 게임2 메서드 당첨 랜덤

	int randomBox(int gameNumber) {

		int[] game = new int[100];

		for (int i = 0; i < 100; i++) {
			game[i] = (int) (Math.random() * 4 + 1);
		}

		int result = game[gameNumber];
		return result;
	}


	
	
	
	
	// 포인트 적립 내역 저장 메서드
	void pointHistory(String NOWID, int p, String cause) throws IOException {
		// 사용자의 이름(String) or 회원번호(int)를 받을 예정 + 포인트 적립얼마했는지 int p로 받는다.

		String point = "" + p; // int 포인트를 String으로 바꿈

		String txt = NOWID.concat("point.txt");
		File pointHistoryFile = new File(txt);
		BufferedWriter out = null;

		if (!pointHistoryFile.exists()) {
			out = new BufferedWriter(new FileWriter(txt));
			out.write(point + "\t" + cause);
			out.newLine();
			out.close();
		} else {
			out = new BufferedWriter(new FileWriter(txt, true));
			PrintWriter pw = new PrintWriter(out, true);
			pw.write(point + "\t" + cause + "\n");
			pw.close();

		}

	}

	// 포인트 적립 내역 출력 메서드
	void pointHistoryInfo(String NOWID) {

		String txt = NOWID.concat("point.txt");
		File pointHistoryFile = new File(txt);
		BufferedReader in = null;

		if (!pointHistoryFile.exists()) {
			System.out.println("포인트 사용 내역이 없습니다.");
		} else {
			System.out.println("--------------------");
			System.out.println("포인트 |\t사용내역");
			try {
				in = new BufferedReader(new FileReader(txt));
				String str = null;
				while (true) {
					str = in.readLine();
					if (str == null) {
						break;
					}
					System.out.println(str);
				}
				System.out.println("--------------------");
				System.out.println("잔여포인트는 " + POINT + " point");
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

	// 포인트로 굿즈 구매 메서드 (예외처리완료)

	public void buyGoods() throws IOException {

		while (true) {
			System.out.println("--------------------------------------");
			System.out.println("현재 보유 포인트는 " + POINT + "point 입니다.");
			System.out.println("--------------------------------------");
			System.out.println("1. 히어로즈 야구점퍼 (1000point)");
			System.out.println("2. 히어로즈 슬로건 (700point)");
			System.out.println("3. 히어로즈 마스코트인형 (500point)");
			System.out.println("--------------------------------------");

			int price = 0;
			int choice = 0;
			String cause = null;

			try {
				choice = Util.sc.nextInt();
				if (choice == 1) {
					price = -1000;
					cause = "야구점퍼 구매";
				} else if (choice == 2) {
					price = -700;
					cause = "슬로건 구매";
				} else if (choice == 3) {
					price = -500;
					cause = "마스코트 인형 구매";
				}
				if (choice < 1 || choice > 3) {
					PointZoneException e = new PointZoneException(choice);
					throw e;
				}
			} catch (InputMismatchException | PointZoneException e) {
				System.out.println("1-3사이의 숫자만 입력해주세요");
				Util.sc.nextLine();
				continue;
			}

			if (price > POINT) {
				System.out.println("포인트가 부족합니다.");
			} else {
				System.out.println("구매완료!");
				POINT += price;
				pointHistory(NOWID, price, cause);
				System.out.println("--------------------------------------");
				return;
			}

		}

	}

}
