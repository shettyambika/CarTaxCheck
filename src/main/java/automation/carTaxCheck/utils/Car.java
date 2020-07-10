package automation.carTaxCheck.utils;

public class Car {
	
	String registration;
	String make;
	String model;
	String color;
	String year;
	
	
	public Car(String reg, String make, String model, String color, String year) {
		
		this.registration = reg;
		this.make = make;
		this.model = model;
		this.color = color;
		this.year = year;
		
	}
	
	public String getRegistration() {
		return registration;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getYear() {
		return year;
	}

}
