package natan.com;

public class Runner {

	public static void main(String[] args) {
		Car[] cars = new Car[3];
		cars[0]= new Car(200, 80, 0.2f, args.length>1?args[0]:"red");
		cars[1]= new Car(100, 30, 1.2f, "black");
		cars[2]= new Car();
		
		Driver[] drivers = new Driver[2];
		drivers[0] = new Driver("natan", 12, 1.2f);
		drivers[1] = new Driver("omer", 12, 0.2f);
		cars[0].setDriver(drivers[0]);
		System.out.println("You traveled "+cars[0].getKilometer()+" kilometers");

	}

}
