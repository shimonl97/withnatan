package natan.com;

public class Car {

	private int speed;
	private int fuel;
	private float fuelEfficency;
	private String color;
	private Driver driver;
	
	public Car(int speed, int fuel, float fuelEfficency, String color) {
		if (speed<0)
			this.speed = speed;
		this.fuel = fuel;
		this.fuelEfficency = fuelEfficency;
		this.color = color;
	}
	public Car() {
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		if (speed<0)
			return;
		this.speed = speed;
	}
	public int getFuel() {
		return fuel;
	}
	public void setFuel(int fuel) {
		this.fuel = fuel;
	}
	public float getFuelEfficency() {
		return fuelEfficency;
	}
	public void setFuelEfficency(float fuelEfficency) {
		this.fuelEfficency = fuelEfficency;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public float getKilometer() {
		return getSpeed()*getFuel()*getDriver().getSpeed();
	}
	
}
