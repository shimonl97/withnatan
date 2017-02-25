package battlePackage;

public class Mage extends Player {

	public Mage(int health, int mana, int attackDamage, float attackSpeed, String name) {
		super(health, mana, attackDamage, attackSpeed, name);
	
	}

	@Override
	public PlayerType getPlayerType() {
		return PlayerType.mage;
	}

}
