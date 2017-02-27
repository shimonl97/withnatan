package natan.com;

public class Road {
	private int maxSpeed;
	private float safety;
	public Road(int maxSpeed, float safety) {
		super();
		this.maxSpeed = maxSpeed;
		this.safety = safety;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public float getSafety() {
		return safety;
	}
	public void setSafety(float safety) {
		this.safety = safety;
	}
	
	

}
