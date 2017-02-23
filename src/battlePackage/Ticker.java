package battlePackage;

public class Ticker implements Runnable {
	float seconds;
	Thread owner;

	
	public Ticker(float seconds, Thread owner) {
		super();
		this.seconds = seconds;
		this.owner = owner;
	}


	@Override
	public void run() {
		try {
			Thread.sleep((long) (seconds*1000));
		} catch (InterruptedException e) {
			//do nothing
		}
		owner.interrupt();

	}

}
