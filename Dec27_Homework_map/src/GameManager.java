import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ctrl + shift + o : 자동 import
public class GameManager {
	List<GameDTO> gameList = new ArrayList<GameDTO>();		// 게임 전적 정보가 저장될 리스트
	Scanner sc = new Scanner(System.in);
	
	public void gameStart() {
		while(true) {
			GameDTO dto = new GameDTO();
			System.out.print("1. 가위 2. 바위 3. 보");
			dto.setPerson(sc.nextInt());
			System.out.println(dto);
			if(dto.isWinner()==1) 
				System.out.println("컴퓨터 승리");
			else if(dto.isWinner()==2)
				System.out.println("사람 승리");
			else
				System.out.println("무승부");
			gameList.add(dto);
			System.out.println("계속 하시겠습니까?");
			String again = sc.next();
			if (again.equals("N"))
				break;
		}
	}
	
	public void showResult() {
		int com=0;	// 컴퓨터 승리 횟수
		int per=0;		// 사람 승리 횟수
		int draw=0;		// 무승부 횟수
		for(GameDTO dto : gameList) {
			if(dto.getWinner()==1) 
				com++;
			else if(dto.getWinner()==2) 
				per++;
			else 
				draw++;
				System.out.println(dto);
			}
			
			int total = com+per+draw;	// 전체 게임 횟수
			if(per == 0) 
				System.out.println("승률 0%");
			else 
				System.out.printf("승률은 %.2f%%입니다.\n", (float)per/total);
			}
	}

