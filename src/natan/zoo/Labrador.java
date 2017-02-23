package natan.zoo;

public class Labrador extends Dog {
	
	private static int averageSize=54;

	public Labrador(int size, int age, Color color) {
		super(size, age, color);
	}

	@Override
	public Character getCharacter() {
		return Character.wild;
	}
	
	public int getAverageSize() {
		return averageSize;
	}

}
