
public class GameDTO {
	int computer;		// 컴퓨터가 낸 결과
	int person=1;			// 사람이 낸 결과
	int winner=1;			// 경기 결과 1.컴퓨터 2.사람 3.무승부
	
		
	// 요새 나오는 언어들은 다 데이터를 public으로 붙인다
	// 근데 java는 여전히 getter/setter 써야됨 -> 롬복으로 대체
	
	public GameDTO(int computer, int person, int winner) {
		super();
		this.computer = (int)(Math.random() * 3) + 1;	// 소숫점 짤림			// 객체 생성할 때마다 이 정보가 저장된다
	}
	
	public GameDTO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("컴퓨터 : %d 사람 : %d, 승자 : %d", computer, person, winner);
	}
	
	public int isWinner() {
		if (computer==person) {
			winner=3;						// 무승부
			return winner;				// 게임이 끝났으므로 return해서 종료
		}
		if(computer == 1 && person == 2) {
			winner = 2;				// 사람 승리
		}else if (computer == 1 && person == 3) {
			winner = 1;
		}else if (computer == 2 && person == 1) {
					winner = 1;
		}else if (computer == 2 && person == 3) {
			winner = 2;
		}else if (computer == 3 && person == 1) {
			winner = 2;
		}else if(computer == 3 && person == 2) {
			winner = 1;
		}
			return winner;

}

	
	public int getComputer() {
		return computer;
	}

	public void setComputer(int computer) {
		this.computer = computer;
	}

	public int getPerson() {
		return person;
	}

	public void setPerson(int person) {
		this.person = person;
	}

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}
	

}
