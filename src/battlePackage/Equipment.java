package battlePackage;

abstract class Equipment implements Item {
	private int durability;
	private int name;
	private Enchantment enchantment;
	private SpecialEffect specialEffect;
	private Active active;

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	public Enchantment getEnchantment() {
		return enchantment;
	}

	public void setEnchantment(Enchantment enchantment) {
		this.enchantment = enchantment;
	}

	public SpecialEffect getSpecialEffect() {
		return specialEffect;
	}

	public void setSpecialEffect(SpecialEffect specialEffect) {
		this.specialEffect = specialEffect;
	}

	public Active getActive() {
		return active;
	}

	public void setActive(Active active) {
		this.active = active;
	}

}
