package battlePackage;

public abstract class Weapon extends Equipment {
	private int attackDamage;
	private float BAT;
	private int spellDamage;

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public float getBAT() {
		return BAT;
	}

	public void setBAT(float bAT) {
		BAT = bAT;
	}

	public int getSpellDamage() {
		return spellDamage;
	}

	public void setSpellDamage(int spellDamage) {
		this.spellDamage = spellDamage;
	}

}
