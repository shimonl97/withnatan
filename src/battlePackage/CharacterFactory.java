package battlePackage;

import java.util.HashMap;
import java.util.Map;

public class CharacterFactory {
	
	private static Map<String,Map<String,Enemy>> characters = new HashMap<>();
	
	public static void setCharacters(Map<String,Map<String,Enemy>> enemy) {
		CharacterFactory.characters=enemy;
	}
	
	public static Enemy getEnemy(String type,String name) {
		Enemy enemy = null;
		Map<String,Enemy> enemiesForType = characters.get(type);
		if (enemiesForType!=null) {
			enemy=enemiesForType.get(name);
		}
		return enemy;
		
	}
	
	

}
