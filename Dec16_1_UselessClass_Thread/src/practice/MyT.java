package practice;

public class MyT implements Runnable {
	@Override
	public void run() {
		while (true) {
				System.out.println("ㅇㅇㅋㅋ");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
