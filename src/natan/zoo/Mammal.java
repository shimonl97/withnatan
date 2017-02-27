package natan.zoo;

public abstract class Mammal implements Animal {

	@Override
	public boolean canWalk() {
		return true;
	}

	@Override
	public boolean canFly() {
		return false;
	}

	@Override
	public abstract int getSize();

	@Override
	public abstract int getAge();

	@Override
	public abstract Color getColor();
	
	@Override
	public abstract Character getCharacter();
	

	

}
