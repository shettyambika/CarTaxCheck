package automation.carTaxCheck.steps;

import org.openqa.selenium.WebDriver;

import automation.carTaxCheck.pages.CarTaxCheckHomePage;

public class CarTaxCheckSteps {	
	
	CarTaxCheckHomePage homePage;
	
	public void openHomePage(WebDriver driver) {
		 driver.navigate().to("https://cartaxcheck.co.uk/");
		 homePage = new CarTaxCheckHomePage(driver);
	}
	
	
	
	public void openCarDetails(String carReg)  {
		homePage.clickOnHomeLink();
		homePage.enterRegNumber(carReg);
		homePage.clickFreeCarCheck();
	}
	
	public String getRegNumber() {
		return homePage.getRegistrationNum();		
	}
	
	public String getCarMake() {
		return homePage.getMake();
	}
	
	public String getCarModel() {
		return homePage.getModel();
	}
	
	public String getCarColor() {
		return homePage.getColor();
	}
	
	public String getCarYear() {
		return homePage.getYear();
	}

}
