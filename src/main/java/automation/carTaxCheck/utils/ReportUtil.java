package automation.carTaxCheck.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;

public class ReportUtil {
	
	public static void captureScreenShot(WebDriver driver, String filename){
		// Takes screenshot and stores as a file format             
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//  Copies the  screenshot to desired location using copyFile method
		try{
		FileUtils.copyFile(src, new File("screenshots/"+filename+".png"));
		} catch (IOException e){
			System.out.println("Error while capturing screenshot"+e.getMessage());
		}
	}

	public static MediaEntityModelProvider addScreenshot(String filename) throws IOException {
		return MediaEntityBuilder.createScreenCaptureFromPath("screenshots\\"+filename+".png").build();
	}

}
