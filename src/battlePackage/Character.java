package battlePackage;

public interface Character {
	public int getHealth();

	public int getMana();

	public int getAttackDamage();

	public int getSpellDamage();

	public float getHealthRegeneration();

	public float getManaRegeneration();

	public String getName();
	
	public void attack(Character character);
	
	public void attacked(int attackDamage);

	public float getAttackSpeed();
	
	public float getAttackDelay();

}
