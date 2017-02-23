package battlePackage;

public class CharacterFactory {
	
	public static Character createCharacter(String type, String name) {
		Character character=null;
		switch (name) {
		case "warrior":
			character=new Warrior(100, 50, 45, 345, name);
			break;
		case "skeleton":
			character=new Skeleton(name);
			break;
		default:
			break;
		}
		return character;
		
	}

}
