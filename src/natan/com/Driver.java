package natan.com;

public class Driver {
	private String name;
	private int age;
	private float speed;
	public Driver(String name, int age, float speed) {
		super();
		this.name = name;
		this.age = age;
		this.speed = speed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
