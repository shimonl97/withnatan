package battlePackage;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {
	
	private static Map<String,Enemy> characters = new HashMap<>();
	
	public static void setCharacters(Map<String,Enemy> enemy) {
		CharacterFactory.characters=enemy;
	}
	
	public static Enemy getEnemy(String name) {
		Enemy enemy = characters.get(name);
		return enemy;
		
	}
	
	

}
