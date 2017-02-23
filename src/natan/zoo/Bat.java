package natan.zoo;

public class Bat extends Mammal{
	
	private int size;
	private int age;
	private Color color;
	private static int averageSize=4;
	
	
	public Bat(int size, int age, Color color) {
		super();
		this.size = size;
		this.age = age;
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	@Override
	public  Character getCharacter() {
		return Character.friendly;
	}
	@Override
	public boolean canFly() {
		return true;
	}

	@Override
	public boolean canWalk() {
		return false;
	}


	
	
	public int getAverageSize() {
		return averageSize;
	}

}
