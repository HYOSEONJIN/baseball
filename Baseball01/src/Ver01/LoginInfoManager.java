package Ver01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class LoginInfoManager implements Menu {

	// 생성자
	public LoginInfoManager() {
		super();
	}

	// 변수 상수화 
	static String NOWID ;	// 현재 로그인 ID
	static String NOWPW;	// 현재 로그인 ID PW 
	static int INDEX; 		// 현재 로그인 ID index
	
	// 회원정보 배열 생성
	static ArrayList<LoginInfo> loginInfo = new ArrayList<LoginInfo>();	 
	
	// 포인트 출석 해시셋
    Set<Integer> set = new HashSet<Integer>();	
	
	
	// 로그인 메뉴 메서드
	public void loginZone() throws IOException, ClassNotFoundException { 
		
		while(true) {
	         System.out.println("\n 〓〓〓〓〓〓 LOGIN MENU 〓〓〓〓〓〓");
	         System.out.println("\n	"+LOG+". 로그인");
	         System.out.println("	"+JOIN+". 회원가입");
	         System.out.println("	"+HOME+ ". 홈 메뉴로 돌아가기");
	         System.out.println("\n 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓"); 
	         System.out.print("SELECT MENU >> ");	       
	         
	         int select=0; // 사용자 메뉴 선택 
	         try {
	            select = Util.sc.nextInt();
	            Util.sc.nextLine();
	            // 예외처리
	            if( !(select>0 && select<5) ) {
	            BadMenuException e = new BadMenuException(select);
	            throw e;   
	            } 
	         } catch(BadMenuException | InputMismatchException e) {
	            System.out.println("[잘못된 입력입니다. 다시 입력하세요.]\n>> ");
	            Util.sc.nextLine();      
	            return;
	         }         
	         
	         switch(select) {
	         	case LOG : 
	         		callInfo();	// 파일에서 로그인정보 불러오기
	         		login();
	         		return;
	         	case JOIN :
	         		callInfo();
	         		joinMember();
					return;
	         	case HOME : 
	         		return;
	         }
		}
	}
	
	
	// 로그인 메서드 
	//		사용자 입력 -> 로그인 -> 사용자의 로그인 ID 반환 
	public void login() throws IOException, ClassNotFoundException { 
		String id = null;
		String cause="로그인 적립 포인트";
		
		while(true) {
			// 사용자 입력
	        System.out.println("\n________ L O G I N _________\n");
			System.out.print(" I  D : ");
			id = Util.sc.nextLine();

			System.out.print(" P  W : ");
			String pw = Util.sc.nextLine();
	        System.out.println("____________________________"); 			
		
			// ID의 배열 index 찾기
			int index = searchIndex(id);
			if(searchIndex(id)>=0) {
				// 해당 index의 비밀번호와 일치 여부 확인
				if(loginInfo.get(index).getPw().equals(pw)) {
					System.out.println("[ "+id +"님, 반갑습니다. ]\n");
					NOWID=id; 
					for(int i=0; i<loginInfo.size(); i++) {
							if(loginInfo.get(i).getId().equals(id)) {
		                        loginInfo.get(INDEX).setPoint(100);
		                        pointHistory(NOWID, 100, cause);
		                        saveInfo();
							}
						}
					return;
				} else {
					System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
					System.out.print("다시 시도하려면 Enter키를, 홈 메뉴로 돌아가시려면 \"0\"을 입력해주세요.\n>> ");
					String insert = null;
						insert = Util.sc.nextLine();
						if(insert.equals("0")) {
							return;
						} else {
							continue;
						}
				}
			} else {
				System.out.println("존재하지 않는 아이디입니다.");
				System.out.print("다시 시도하려면 Enter키를, 홈 메뉴로 돌아가시려면 \"0\"을 입력해주세요.\n>> ");
				String insert = null;
				insert = Util.sc.nextLine();
				if(insert.equals("0")) {
					return;
				} else {
					continue;
				}
			}
		}
	}	
	
	
	
	// 배열에 정보 저장 메서드
	public void addInfo(LoginInfo info) throws IOException, ClassNotFoundException {		
		loginInfo.add(info);		
		saveInfo();
	}

	// 배열의 index 검색 메서드
	public int searchIndex(String id) {
		int index = -1;
		for(int i=0; i<loginInfo.size(); i++) {
			if(loginInfo.get(i).getId().equals(id)) {
				index = i;
			}
		}
		return index;
	}	
	
	
	// 회원정보 외부 저장 메서드
	public void saveInfo() throws IOException, ClassNotFoundException{
		
		// 파일 중복생성 방지
		File f = new File("LoginInfo.ser");
		f.delete();
		
	    // 인스턴스 저장을 위한 스트림 생성
	    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("LoginInfo.ser"));   
	    out.writeObject(loginInfo);
	    out.close();		  
	}
	
		
	// 외부에 저장된 회원정보 불러오기 메서드
	public void callInfo() {
		// 인스턴스 복원을 위한 스트림 생성
	    ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream("LoginInfo.ser"));
			loginInfo = (ArrayList<LoginInfo>)in.readObject();	
		} catch (IOException | ClassNotFoundException e) {
		}
	}
	
	

	// 회원가입 메서드
	//		ID 입력 받기 -> ID 중복 확인 -> 비밀번호 입력 받기 -> 회원가입 완료
	public void joinMember() throws IOException, ClassNotFoundException{
		String id = null;
		String pw = null;	      
		System.out.println("\n________ J O I N _________\n");
		
		// ID 중복 확인 (무한반복)
		while(true) {
			System.out.print(" I  D : ");
			// ID 공백 입력 시 예외처리
			try {
				id = Util.sc.nextLine().trim();	
				if(id.equals("")) {
					NullInputException e = new NullInputException();
					throw e;
				}	
			} catch(NullInputException e) {
				System.out.print("아이디를 잘못 입력하셨습니다. 다시 입력해주세요.\n");
			}
			
			int index = searchIndex(id);
			if(searchIndex(id)>=0) {
				System.out.print("중복되는 아이디가 존재합니다. 다른 아이디를 입력해주세요.\n"); 
				continue;
				/*   
				*******************************************************************************************
				오류 : 테스트마다 적용될 때도 있고 적용 안될때도 있음.. HashSet으로 정보 저장하는 방식을 변경하는 것 고려해보기.
				>> 로그인메뉴에 joinMember() 앞에 callInfo()추가했고 여러번 테스트했을 때 중복 방지되었음.
				       근데 정보를 안불러와서 안된거면 왜 없을때 정상적으로 돌아간 경우도 있었는지 이해가 안감.
				*******************************************************************************************
				*/	
				
			}
				System.out.print(" P  W : ");
				// 비밀번호 공백 입력 시 예외처리
				try{
					pw = Util.sc.nextLine().trim();
					if(pw.equals("")) {
						NullInputException e = new NullInputException();
						throw e;
					}
					addInfo(new LoginInfo(id, pw));
					System.out.println("____________________________"); 
					System.out.println("["+id+"님, 가입을 축하드립니다!]\n");
					break;								
				} catch(NullInputException e) {
					System.out.println("____________________________");
					System.out.println("비밀번호를 잘못 입력하셨습니다. 다시 입력해주세요.\n");
				}
				
			
		}
	}

	
	
	// 로그인 정보 변경 메서드 
	//		충전금액/포인트 받아놓기-> 이전 계정 삭제 -> 새 ID/PW 입력 받기 -> 새 계정 생성 -> 상수 변수(NOWID,PW,INDEX) 변경
	public void changeLoginInfo() throws IOException, ClassNotFoundException {
		
		// 현재 로그인 계정의 충전금액/포인트 받아놓기
		int myMoney = loginInfo.get(INDEX).getMyMoney(); 
		int point = loginInfo.get(INDEX).getPoint(); 

		// 이전 로그인 계정 삭제       
		loginInfo.remove(INDEX);	
        
		// ID/PW 입력 받아 새 계정 생성
		System.out.println("\n 〓〓〓〓〓〓  CHANGE INFO 〓〓〓〓〓\n");
		System.out.print(" I  D : ");
		String newId = Util.sc.nextLine().trim();	
		System.out.print(" P  W : ");
		String newPw = Util.sc.nextLine().trim();	
		System.out.println("\n 〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓"); 
		addInfo(new LoginInfo(newId, newPw, myMoney, point));
		
        // 새로운 포인트 내역 저장 메서드      
        String txt=NOWID.concat("point.txt");
        String newtxt=newId.concat("point.txt");
        File file = new File(txt);
        if(file.exists()) {
        file.renameTo(new File(newtxt));
        }else {
        }
				
		// 상수화한 로그인 ID,PW,INDEX 변경
		NOWID = newId;
		NOWPW = newPw;	
		INDEX = searchIndex(newId);
		System.out.println(" ID/PW 변경이 완료되었습니다.\n");
	}

	
	
	
	
	
	
// =======================================================================================================================================
	
	
	// 포인트존메인메서드 ( 예외처리끝 )
    public void pointZone() throws IOException, ClassNotFoundException {
       if(NOWID==null) {
          System.out.println("로그인 먼저 :)");
          return;
       }

       while (true) {
           System.out.println("〓〓〓〓〓〓P O I N T Z O N E〓〓〓〓〓〓〓〓\n");
           System.out.println("\t"+NOWID+"님, 안녕하세요!");
           System.out.println("\t"+loginInfo.get(INDEX).getPoint()+" POINT 보유중");
           System.out.println("\n〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
           System.out.println();
           System.out.println("\t1. R-S-P GAME (-100P)");
           System.out.println("\t2. RANDOMBOX (-100P)");
           System.out.println("\t3. 7DAYS CHECK");
           System.out.println("\t4. BUY GOODS");
           System.out.println("\t5. MY POINT ");
           System.out.println("\t6. EXIT");
           System.out.println();
           System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");          
           try {
             int choice = Util.sc.nextInt();
             
             Util.sc.nextLine();
             if (choice < 1 || choice > 7) {
                PointZoneException e = new PointZoneException(choice);
                throw e;
             }

             switch (choice) {
             case 1:
                pointGame1();
                saveInfo();
                break;
             case 2:
                pointGame2();
                saveInfo();
                break;
             case 3:
            	 att();
            	 saveInfo();
            	 break;
             case 4:
                buyGoods();
                saveInfo();
                break;
             case 5:
                pointHistoryInfo(NOWID);
                break;
             case 6:
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

	      String cause = "가위바위보 포인트 지급";
	      String game = "가위바위보 참여";

	      if (loginInfo.get(INDEX).getPoint() < 100) {
	         System.out.println("게임에 참여할 포인트가 모자라요 ToT");
	         System.out.println("현재 보유 포인트는 " + loginInfo.get(INDEX).getPoint() + "point 입니다.");
	         return;
	      }

	      while (true) {
		         // 한번 참여시 100포인트 차감, 승리시 300포인트 돌려주고 비기면 50, 지면 0포인트 돌려줌
	    	 System.out.println("〓〓〓〓〓〓〓가위 바위 보 G A M E〓〓〓〓〓〓〓");
	    	 System.out.println("      win : +300p | draw : +50p");
	         System.out.println("      1 : 가위 | 2 :바위 | 3 : 보 ");
	         System.out.println("      0 : 취소");
	         System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
	         	         
	         try {
	            int rsp = Util.sc.nextInt();

	            if (rsp < 0 || rsp > 3) {
	               PointZoneException e = new PointZoneException(rsp);
	               throw e;
	            }

	            if (rsp == 0) {
	               System.out.println("게임을 종료합니다\n");
	               return;
	            }
	            loginInfo.get(INDEX).setPoint(buyGame);
	            pointHistory(NOWID, buyGame, game);

	            // 경우의수 시작

	            switch ((int) (Math.random() * 3 + 1)) {
	            case 1:
	               if (rsp == 1) {
	                  System.out.println("비겼습니다");
	                  loginInfo.get(INDEX).setPoint(draw);
	                  pointHistory(NOWID, draw, cause);
	               } else if (rsp == 2) {
	                  System.out.println("패배했습니다 ToT");
	               } else if (rsp == 3) {
	                  System.out.println("승리했습니다 !");
	                  loginInfo.get(INDEX).setPoint(win);
	                  pointHistory(NOWID, win, cause);
	               }
	               System.out.println();
	               break;
	            case 2:
	               if (rsp == 1) {
	                  System.out.println("승리했습니다 !");
	                  loginInfo.get(INDEX).setPoint(win);
	                  pointHistory(NOWID, win, cause);
	               } else if (rsp == 2) {
	                  System.out.println("비겼습니다");
	                  loginInfo.get(INDEX).setPoint(draw);
	                  pointHistory(NOWID, draw, cause);
	               } else if (rsp == 3) {
	                  System.out.println("패배했습니다 ToT");
	               }
	               System.out.println();
	               break;
	            case 3:
	               if (rsp == 1) {
	                  System.out.println("패배했습니다 ToT");
	               } else if (rsp == 2) {
	                  System.out.println("승리했습니다 !");
	                  loginInfo.get(INDEX).setPoint(win);
	                  pointHistory(NOWID, win, cause);
	               } else if (rsp == 3) {
	                  System.out.println("비겼습니다");
	                  loginInfo.get(INDEX).setPoint(draw);
	                  pointHistory(NOWID, draw, cause);
	                  System.out.println();
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
	            System.out.println();
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

	      if (loginInfo.get(INDEX).getPoint() < 100) {
	         System.out.println("게임에 참여할 포인트가 모자라요 ToT");
	         System.out.println("현재 보유 포인트는 " + loginInfo.get(INDEX).getPoint() + "point 입니다.");
	         return;
	      }
	      
	      while(true) {
	    System.out.println("〓〓〓〓〓〓〓〓〓R A N D O M〓〓〓〓〓〓〓〓〓");
	      System.out.println("\t당첨확률 25% 맞추면 +500p");
	      System.out.println("    1-100까지 원하는 숫자를 입력하세요!");
	      System.out.println("\t취소 : 0 **");
	      System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
	      
	      try {
	         
	      int gameNumber = Util.sc.nextInt();
	      
	      if(gameNumber<0 || gameNumber>100) {
	         PointZoneException e = new PointZoneException(gameNumber);
	         throw e;         
	      }

	      if (gameNumber == 0) {
	         System.out.println("게임을 취소합니다");
	         System.out.println();
	         return;
	      } else {

	            if (randomBox(gameNumber) == 1) {
	               loginInfo.get(INDEX).setPoint(buyGame);
	               pointHistory(NOWID, buyGame, game);
	               System.out.println("당첨입니다 :) 500POINT가 지급됩니다");
	               loginInfo.get(INDEX).setPoint(win);
	               pointHistory(NOWID, win, cause);
	               System.out.println();
	               break;
	            } else {
	               loginInfo.get(INDEX).setPoint(buyGame);
	               pointHistory(NOWID, buyGame, game);
	               System.out.println("꽝입니다 ToT");
	               System.out.println();
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
	         System.out.println("\n〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
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
	            System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	            System.out.println("잔여포인트는 " + loginInfo.get(INDEX).getPoint() + " point");
	            System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓\n");
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
	         System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         System.out.println("현재 보유 포인트는 " + loginInfo.get(INDEX).getPoint() + "point 입니다.");
	         System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	         System.out.println("1. 히어로즈 야구점퍼 (1000point)");
	         System.out.println("2. 히어로즈 슬로건 (700point)");
	         System.out.println("3. 히어로즈 마스코트인형 (500point)");
	         System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");

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

	         if (Math.abs(price) > loginInfo.get(INDEX).getPoint()) {
	            System.out.println("포인트가 부족합니다.");
	            return;
	         } else {
	            System.out.println("구매완료!");
	            loginInfo.get(INDEX).setPoint(price);
	            pointHistory(NOWID, price, cause);
	            System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
	            return;
	         }

	      }

	   }
	
	
		// 출석체크이벤트 이력
       void att() throws IOException, ClassNotFoundException {
          // 날짜 받기
          Date today=new Date();
          SimpleDateFormat now = new SimpleDateFormat("dd");
          SimpleDateFormat monthcheck = new SimpleDateFormat("MM");
          int month = Integer.parseInt(monthcheck.format(today));
          int day = Integer.parseInt(now.format(today));
          int sevenDay=500;
          String cause="7일 출석체크 보상";
          boolean check=false;

          String fileName= NOWID.concat("check.ser");
          File f = new File(fileName);
          
          if(f.exists()) {
          ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
          set=(Set<Integer>) in.readObject();
          in.close();
          }
                         
      	if(month==12) {
    		System.out.println("11월의 출석체크가 끝났습니다\n");
    		return;
    	}
    	
             while(true) {
                int choice;
                System.out.println("〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
                System.out.println("\n매일매일 CHUL-SEOK 체크 이벤트!");
                System.out.println("일주일간 매일 출석을 하시면, ");
                System.out.println("500포인트를 드립니다. ");
                System.out.println("출석하시려면 번호를 눌러주세요.\n");
                System.out.println("지금까지 "+set.size()%7+"번 출석 완료!");
                System.out.println("1.출석  2.홈으로 돌아가기");
                System.out.println("\n〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓〓");
                choice=Util.sc.nextInt();
                
                // 출첵을 할 때
                switch(choice) {
                case 1:
               
                if(choice==1) {
                   if(set.contains(day)) {  // 출첵을 이미 했는데 또 할 경우 
                      System.out.println("오늘은 이미 출석하셨어요. 내일을 기다리세요.");
                      return;
                   }else{
                      set.add(day);   // 출첵 정보를 저장
                      System.out.println("출석 완료!");
                      System.out.println(set.size()%7+"번째 출석을 완료하셨습니다\n");
                      
                      // 출첵해서 일주일 보상을 받을 경우
                      if(set.size()%7==0) {
                          loginInfo.get(INDEX).setPoint(sevenDay);
                          pointHistory(NOWID, sevenDay, cause);
                         System.out.println("일주일간 출석해서 500 포인트가 지급됩니다!");
                        System.out.println(NOWID+"님의 포인트는"+loginInfo.get(INDEX).getPoint()+"입니다.");
                        System.out.println("홈으로 돌아갑니다.\n");
                        ;      // 셋에 저장된 정보 리셋
                            
                        } 
  	   
                      ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
                      out.writeObject(set);
                      out.close();
                      return;
                   }
                } case 2:
                	System.out.println("홈으로 돌아갑니다.\n");
                	return;
                } 
             

                
                }   
                   
       }
	
}
