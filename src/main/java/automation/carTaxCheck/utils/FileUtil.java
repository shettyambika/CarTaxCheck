package automation.carTaxCheck.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtil {
	Car car;
	/* Finds the car registration number using regular expression and returns the list of car registrations. */
	public List<String> getCarRegNumbersFromFile(String filePath) {
		List<String> ips = new ArrayList<String>();
		String pattern = "([A-Za-z]{2}[0-9]{2})\\s*[A-Za-z]{3}";
		Pattern r = Pattern.compile(pattern);
		
		String currentLine = "";
		try {

			BufferedReader br = new BufferedReader(new FileReader(filePath));
			while ((currentLine = br.readLine()) != null) {
				Matcher m = r.matcher(currentLine);
				while(m.find()){
		            ips.add(m.group());		            
		        }
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());

		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return ips;

	}	
	
	/* Reads data from car_output.txt from outputData folder 
	 * and returns the hashmap with key as registration number and values as car details */
	public HashMap<String, Car> readDataFromOutputFile() {
		
		HashMap<String, Car> outputData = new HashMap<String, Car>();
		String projectPath = System.getProperty("user.dir");
		String filePath = projectPath + "\\outputData\\car_output.txt";
		
		String currentLine = "";
		try {

			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String headerLine = currentLine = br.readLine();
			while ((currentLine = br.readLine()) != null) {
				String[] arrOfStr = currentLine.split(","); 			
				String reg = arrOfStr[0];
				car = new Car(arrOfStr[0], arrOfStr[1], arrOfStr[2], arrOfStr[3], arrOfStr[4]);
				outputData.put(reg, car);
			}
		        
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());

		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		return outputData;
	}

}
