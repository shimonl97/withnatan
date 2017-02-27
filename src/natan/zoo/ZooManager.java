package natan.zoo;

public class ZooManager {

	public static void main(String[] args) {


		Animal bat = new Bat(12, 1, Color.red);
		Dog labrador = new Labrador(45, 12, null);
		Dog pincher=null;
		try {
		  pincher = new Pincher(34, 56);
		} catch (Exception ex) {
			System.out.println(ex);
			pincher = new Pincher(34, 56);
		}
		
		System.out.println(bat.getCharacter());
		System.out.println(labrador.getCharacter());
		System.out.println(pincher.getCharacter());
		System.out.println(bat.getColor());
		System.out.println(((Dog)labrador).getColor());
		System.out.println(pincher.getColor());
		System.out.println(((Pincher)pincher).getStam());

	}

}
