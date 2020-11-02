package Ver01;
public interface BookingManager {
	
	//예약 정보 저장 
	void addreserve();
	

	//예약 정보 인스턴스 생성하고 배열에 저장 
	public void insertInfo();

	//예약 정보 검색 
	public int SearchIndex (String name);


	// 해당 index의 참조변수로 정보 출력 : 사용자가 입력한 이름으로 검색
	public void searchInfor(); 
	/*	{
		if(book.size()==0) {
			System.out.println("입력된 정보가 없습니다.");
			return;
		}
		
		SC.nextLine();
		System.out.println("검색하실 이름을 입력해주세요.");
		String name = SC.nextLine();
		
		int index = searchIndex(name);
		
		if(index<0) {
			System.out.println("검색하신 이름 "+name+"의 정보가 존재하지 않습니다.");
			System.out.println("메뉴로 돌아갑니다.");
		} else {
			System.out.println("검색 결과 =============");
			pBook.get(index).showInfor();
		}
	}
	/**/
	// 예약 명단 전체 정보 출력 
	public void showAllInfor(); 

	// 결제 ->결제 여부 확인 -> 결제 완료 -> 돈 감소 -> 정보 저장  
	public void pay();
		

	


}
