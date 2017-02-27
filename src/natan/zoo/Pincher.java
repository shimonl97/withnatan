package natan.zoo;

public class Pincher extends Dog {

	public Pincher(int size, int age, Color color) {
		throw new RuntimeException("can't change color");
	}
	
	public Pincher(int size, int age) {
		super(size, age);
	}

	@Override
	public Character getCharacter() {
		return Character.quiet;
	}
	
	@Override
	public Color getColor() {
		return Color.black;
	}
	
	private static int averageSize=24;

	
	
	public int getAverageSize() {
		return averageSize;
	}
	
	public String getStam() {
		return "stam stam";
	}

}
