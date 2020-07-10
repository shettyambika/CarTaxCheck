package automation.carTaxCheck.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarTaxCheckHomePage {
	
	WebDriver driver;
	
	@FindBy(id = "vrm-input")
	WebElement carRegInputField;
	
	@FindBy(xpath = "//button[contains(@class,'jsx-3655351943')]")
	WebElement freeCarCheckBtn;

	
	@FindBy(xpath = ".//*[@id='m']/div/div[3]/div[1]/div/span/div[2]/dl[1]/dd")
	WebElement carRegField;
	
	@FindBy(xpath = ".//*[@id='m']/div/div[3]/div[1]/div/span/div[2]/dl[2]/dd")
	WebElement makeField;
	
	@FindBy(xpath = ".//*[@id='m']/div/div[3]/div[1]/div/span/div[2]/dl[3]/dd")
	WebElement modelField;
	
	@FindBy(xpath = ".//*[@id='m']/div/div[3]/div[1]/div/span/div[2]/dl[4]/dd")
	WebElement colorField;
	
	@FindBy(xpath = ".//*[@id='m']/div/div[3]/div[1]/div/span/div[2]/dl[5]/dd")
	WebElement yearField;
	
	
	@FindBy(xpath = "//img[contains(@class,'jsx-3978163275')]")
	WebElement homeLink;
	
	
	public CarTaxCheckHomePage(WebDriver drv) {
		this.driver = drv;
		PageFactory.initElements(driver, this);		
	}
	
	public void enterRegNumber(String carReg) {
		waitForVisibility(carRegInputField);
		carRegInputField.sendKeys(carReg);

	}
	
	public void clickFreeCarCheck() {
		freeCarCheckBtn.click();
	}
	
	public String getRegistrationNum() {
		waitForVisibility(carRegField);
		return carRegField.getText();		
	}
	
	public String getMake() {
		return makeField.getText();
		
	}
	
	public String getModel() {
		return modelField.getText();
		
	}
	
	public String getColor() {
		return colorField.getText();
		
	}
	
	public String getYear() {
		return yearField.getText();
		
	}
	
	public void clickOnHomeLink() {
		homeLink.click();
	}
	
	private void waitForVisibility(WebElement element) {
		(new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOf(element));
	}

}
