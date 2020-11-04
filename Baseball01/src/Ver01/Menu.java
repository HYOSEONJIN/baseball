package Ver01;

public interface Menu {

	   int LOGIN=1;
	   int BOOKING=2;
	   int EVENT=3;
	   int INFO=4;
	   int EXIT=5;
	   
	// 로그인 후
	   
	   int BOOKING_C=1;
	   int EVENT_C=2;
	   int INFO_C=3;
	   int EXIT_C=4;
	
	
	// *****************************
	// 로그인 상세메뉴 (LoginInfoManager)
	final int LOG=1;
	final int JOIN=2;
	final int HOME=3;
	
	// *****************************
	// 예약 상세메뉴 (BookingManager)
	int RECHARGE=1;
	int  BOOKINGNOW=2;
	int CANCEL=3;
	int BOOKINGINFO=4;
	int EXIT2=5;
	
	// *****************************
    // 회원정보 상세메뉴 (UserManager)
    int  SHOWBOOK=1;
    int EVETALL=2;
    int MEMBER=3;
    int CHANGE=4;
    int DELETE=5;
    int EXIT1 =6;
	
}