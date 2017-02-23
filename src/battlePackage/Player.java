package battlePackage;

public abstract class Player implements Character {

	private int health;
	private int mana;
	private int attackDamage;
	private int spellDamage;
	private float healthRegeneration;
	private float manaRegeneration;
	private String name;
	private PlayerType playerType;
	private float attackSpeed;
	private float attackDelay;

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

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public Player(int health, int mana, int attackDamage,float attackSpeed, String name) {
		super();
		this.health = health;
		this.mana = mana;
		this.attackDamage = attackDamage;
		this.name = name;
		this.attackSpeed = attackSpeed;
		attackDelay= 1 / attackSpeed;
		

	}

	@Override
	public void attack(Character character) {
		character.attacked(getAttackDamage());
	}

	@Override
	public void attacked(int attackDamage) {
		setHealth(getHealth() - attackDamage);
		;
	}

	public float getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(float attackDelay) {
		this.attackDelay = attackDelay;
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public float getAttackSpeed() {
		return attackSpeed;
	}
}

	
