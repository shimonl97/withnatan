package battlePackage;

public abstract class NPC implements Character {

	private int health;

	private int mana;

	private int attackDamage;

	private int spellDamage;

	private float healthRegeneration;

	private float manaRegeneration;

	private String name;
	
	private float attackSpeed;
	
	private float attackDelay = 1/getAttackSpeed();
	
	public NPC(String name) {
		this.name=name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpellDamage() {
		return spellDamage;
	}

	public void setSpellDamage(int spellDamage) {
		this.spellDamage = spellDamage;
	}

	public float getHealthRegeneration() {
		return healthRegeneration;
	}

	public void setHealthRegeneration(float healthRegeneration) {
		this.healthRegeneration = healthRegeneration;
	}

	public float getManaRegeneration() {
		return manaRegeneration;
	}

	public void setManaRegeneration(float manaRegeneration) {
		this.manaRegeneration = manaRegeneration;
	}

	 public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void attack(Character character){
		character.attacked(getAttackDamage());
	}
	public void attacked(int attackDamage){
		setHealth(getHealth()-attackDamage);;
	}

	

	

	

	public float getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(float attackDelay) {
		this.attackDelay = attackDelay;
	}

	
	
	
}
