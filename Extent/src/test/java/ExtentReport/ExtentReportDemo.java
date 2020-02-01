package ExtentReport;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportDemo {
	
	ExtentReports extent;
	ExtentTest logger;
	WebDriver driver;
	
	@BeforeMethod
	public void setUp()
	{
		// Create Object of ExtentHtmlReporter and provide the path where you want to generate the report 
        // I used (.) in path where represent the current working directory
		
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./ExtentReport/Report1.html");
		
		// Create object of ExtentReports class- This is main class which will create report
		extent = new ExtentReports();
		
		// attach the reporter which we created in Step 1
		extent.attachReporter(reporter);
		
		// call createTest method and pass the name of TestCase- Based on your requirement
		logger=extent.createTest("LoginTest");
		
	}
	
	   // Actual Test which will start the application and verify the title
		@Test
		public void loginTest() throws IOException
		{
			System.setProperty("webdriver.chrome.driver","C:\\Work\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get("http://www.google.com");
			System.out.println("title is "+driver.getTitle());
			Assert.assertTrue(driver.getTitle().contains("Mukesh"));
		}
		
	    // This will run after testcase and it will capture screenshot and add in report
		@AfterMethod
		public void tearDown(ITestResult result) throws IOException
		{
			
			if(result.getStatus()==ITestResult.FAILURE)
			{
	
				logger.log(Status.FAIL, "Title verified");
			}
			extent.flush();
			driver.quit();
		}
}
