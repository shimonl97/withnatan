package battlePackage;

public class ActionDuration implements Runnable {
	
	String specificAction;
	Character targeter;
	Character target;
	boolean running = true;
	

	



	public ActionDuration(String specificAction, Character targeter, Character target) {
		super();
		this.specificAction = specificAction;
		this.targeter = targeter;
		this.target = target;
	}


  public void stop() {
	  running=false;
  }
  
  public void resume() {
	  running=true;
  }




	@Override
	public void run() {
		while (true && target.getHealth()>0) {
			try {
			while (running) {
					switch (specificAction) {
					case "attack":
						targeter.attack(target);
						System.out.println(target.getHealth());
							Thread.sleep((long) (targeter.getAttackDelay()*1000));
						
						break;
		
					default:
						break;
					}
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		} 
		
	}


	
		
	}


