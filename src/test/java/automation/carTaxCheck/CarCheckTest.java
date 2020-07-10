package automation.carTaxCheck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import automation.carTaxCheck.steps.CarTaxCheckSteps;
import automation.carTaxCheck.utils.Car;
import automation.carTaxCheck.utils.FileUtil;
import automation.carTaxCheck.utils.ReportUtil;

public class CarCheckTest {

	WebDriver driver;
	FileUtil fileUtil;
	String projectPath;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReport;

	/*This is the data provider for the test method. This returns the car registration numbers from input data folder.
	 *  Multiple .txt files can be added in inputData folder. */
	@DataProvider(name = "carRegistrations")
	public Object[][] getCarRegNumbers() {
		List<String> carRegList = new ArrayList<String>();
		projectPath = System.getProperty("user.dir");
		String path = projectPath + "\\inputData";
		String file;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		fileUtil = new FileUtil();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				file = listOfFiles[i].getName();
				if (file.endsWith(".txt") || file.endsWith(".TXT")) {
					List<String> carRegs = fileUtil.getCarRegNumbersFromFile(path + "\\" + file);
					for (int x = 0; x < carRegs.size(); x++) {
						carRegList.add(carRegs.get(x));
					}
				}
			}
		}

		String[][] carArray = new String[carRegList.size()][1];
		for (int a = 0; a < carRegList.size(); a++) {
			carArray[a][0] = carRegList.get(a);
		}

		return carArray;
	}

	@BeforeTest
	public void setUp() {
		projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//This is used for reporting
		htmlReporter = new ExtentHtmlReporter("OutputReport.html");
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReporter);
	}

	@Test(dataProvider = "carRegistrations")
	public void testCarCheck(String carRegNumber) {

		ExtentTest test = extentReport.createTest("Car Details Test for " + carRegNumber);

		System.out.println("This is the car Reg-->" + carRegNumber);
		CarTaxCheckSteps carSteps = new CarTaxCheckSteps();
		carSteps.openHomePage(driver);

		carRegNumber = carRegNumber.replaceAll("\\s", "");

		carSteps.openCarDetails(carRegNumber);

		fileUtil = new FileUtil();
		HashMap<String, Car> outputData = fileUtil.readDataFromOutputFile();

		try {
			// if the values are not populating on page then fail the test
			Assert.assertFalse(carSteps.getRegNumber().isEmpty(), "The values are not populating on page");

			if (outputData.containsKey(carRegNumber)) {
				Car car = outputData.get(carRegNumber);

				Assert.assertEquals(carSteps.getRegNumber(), car.getRegistration());
				test.info("Car registration number is :" + car.getRegistration());
				test.pass("Registration test passed.");
				Assert.assertEquals(carSteps.getCarMake(), car.getMake());
				test.info("Car make is :" + car.getMake());
				test.pass("Car make test passed.");
				Assert.assertEquals(carSteps.getCarModel(), car.getModel());
				test.info("Car model is :" + car.getModel());
				test.pass("Car model test passed.");
				Assert.assertEquals(carSteps.getCarColor(), car.getColor());
				test.info("Car color is :" + car.getColor());
				test.pass("Car color test passed.");
				Assert.assertEquals(carSteps.getCarYear(), car.getYear());
				test.info("Car year is :" + car.getYear());
				test.pass("Car year test passed.");
			} else {
				Assert.assertTrue(false, carRegNumber + " is not found in car_output.txt file");
			}

			test.pass("Test for " + carRegNumber + " passed.");
		} catch (Exception | AssertionError ae) {
			ReportUtil.captureScreenShot(driver, carRegNumber);
			try {
				test.fail(ae, ReportUtil.addScreenshot(carRegNumber));

			} catch (IOException e) {
				System.out.println("Could not capture " + carRegNumber + " screenshot");
				e.printStackTrace();
			}
			throw ae;
		}

	}

	@AfterTest
	public void teardown() {
		driver.quit();
		extentReport.flush();
	}

}
