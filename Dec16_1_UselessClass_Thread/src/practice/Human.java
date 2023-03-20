package practice;
// 문제점 : Thread 두 개가 한 brain 변수를 공유해서 취미: 홍길동/ 이름 : 독서 라고 출력되는 경우가 발생
// multiThread : 데이터 분석할 때 여러 자료에 동시에 접근하기 때문에 의미있음
// 						근데 제대로 한다면 Hadoop/ElasticSearch를 더 많이 사용함
//							웹개발할 때는 동시에 여러 클라이언트가 접근하기 때문에 더욱 의미있음
//							근데 jsp.jar/spring.jar가 알아서 함

// KTX 예매사이트 등등 한 데이터에 동시에 접근할 때면?
// critical section 설정(임계영역)
public class Human {
	private String brain;

	// synchronized는 하나만 가능함(동시 실행이 안되는 메소드) -> 이게 끝나야 다른 synchronized가 동작함
	private synchronized void sayName () {				// 공유 자원을 쓰는 메소드들에 synchronized 표시
		for (int i = 0; i < 10; i++) {
			brain = "홍길동";
			try {
				Thread.sleep(100);
				System.out.printf("이름 : %s\n", brain);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void sayHobby() {
		for (int i = 0; i < 10; i++) {
			brain = "독서";
			try {
				Thread.sleep(400);					// 이름 떠올리는 것보다는 시간이 더 걸리니까..?
				System.out.printf("취미 : %s\n", brain);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void introduce () {
		// Java만이 가능한 기능 = 객체를 만들면서 클래스를 함께 만드는 것(다형성)
		Thread t = new Thread() {					// 이름과 취미를 동시에 말하는 multiThread
			@Override
			public void run() {
				super.run();
				sayName();
				sayHobby();
			}
		};
		t.start();
		sayHobby();
	}

}
