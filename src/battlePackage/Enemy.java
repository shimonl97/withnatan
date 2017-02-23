package battlePackage;

public abstract class Enemy extends NPC {
	

	
	public Enemy(String name) {
		super(name);
	}
	public void attacked(int attackDamage){
		setHealth(getHealth()-attackDamage);
	}
	
}