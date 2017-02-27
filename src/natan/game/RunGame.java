package natan.game;

import java.text.NumberFormat;

public class RunGame {

	
	public static void  main(String[] args) {
		Runnable runnable = new Runnable() {
			
			private void work() throws InterruptedException {
				while (true) {
					Thread.sleep(1000);
					System.out.println("no one interupt me");
				}
			}
			@Override
			public void run() {
				try {
					work();
				} catch (InterruptedException e) {
				}
				
			}
			
			
		};
		Thread t = new Thread(runnable);
		t.start();
		float f;
		for (f=0;f<10;f+=0.1) {
			try {
				Thread.sleep(100);
				System.out.println(f);
			} catch (InterruptedException e) {
			
			}
		}
		try {
			t.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		for (f=0;f<10;f+=0.1) {
			try {
				Thread.sleep(100);
				System.out.println(NumberFormat.getNumberInstance().format(f));
			} catch (InterruptedException e) {
			
			}
		}
	}
}
