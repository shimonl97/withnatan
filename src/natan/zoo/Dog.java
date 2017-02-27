package natan.zoo;

public abstract class Dog extends Mammal{
	
	private int size;
	private int age;
	private Color color;
	
	public Dog() {
		
	}
	
	public Dog(int size, int age, Color color) {
		super();
		this.size = size;
		this.age = age;
		this.color = color;
	}
	public Dog(int size, int age) {
		this.size = size;
		this.age=age;
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
	public abstract Character getCharacter();
	
	@Override
	public abstract int getAverageSize();

	

}
