# CarTaxCheck
1. The project can be run either as maven test or testNG test.
2. Multiple input files with .txt extensions can be added under inputData folder. 
3. Reporting is added to this project using Extent report utility. 
4. After the test is done refresh the project to see the latest extent report OutputReport.html in the project directory. 
5. In the extent report OutputReport.html, click on the test cases to see the details that are logged. 
6. If the test fails, the extend report shows the reason of failure along with the screenshot of the page. This screenshot can be found below the displayed error message. Click on the screenshot icon to maximize the screenshot. 

7. TestNG report index.html can also be found under the folder test-output. 
8. car_output.txt is in outputData folder


# Two issues seen:
1. Inconsistency while retrieving the valid car registration details. Car with the valid details cannot be retrieved few times. And the page displays the message as "Please provide a valid vehicle registration".
2. Car with the invalid registration number displays the field with empty values. Instead this should display message as "Please provide a valid vehicle registration".

Above two issues are inconsistent. Running the tests multiple times these issues can be reproduced. 
