package tests.Homepage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import tests.LoginPage;

public class RegularizeAttendance {
	public WebDriver driver;
	public HomePageObject hp;
	
	@Test
	public void activeAttendance() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		hp=new HomePageObject(driver);
		//WebElement leaveType=hp.getactiveAttendanceOpt();
		//Select s=new Select(hp.getactiveAttendanceOpt());
		hp.getAtiveAttendanceOpt();
		Thread.sleep(5000);
//		hp.getleaveType("Work From Home(WFH)");
//		hp.getCategoryOption("--NA--");
//		hp.getReasonOpt("");
		
		
	}
	
	@Test
	public void historicalAttendance() throws InterruptedException {
		Thread.sleep(2000);
		hp=new HomePageObject(driver);
		hp.getHistoryAttendance();
	}

}
