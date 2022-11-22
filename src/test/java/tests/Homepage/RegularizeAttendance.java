package tests.Homepage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import tests.LoginTest.LoginPage;

public class RegularizeAttendance {
	public WebDriver driver;
	public HomePageObject hp;
	
	@Test
	public void activeAttendance() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		//System.out.println("Attendance driver : "+driver);
		hp=new HomePageObject(driver);
		//WebElement leaveType=hp.getactiveAttendanceOpt();
		//Select s=new Select(hp.getactiveAttendanceOpt());
		//hp.getAtiveAttendanceOpt();
		//Thread.sleep(5000);
//		hp.getleaveType("Work From Home(WFH)");
//		hp.getCategoryOption("--NA--");
//		hp.getReasonOpt("");
		
		
	}
	
	@Test
	public void historicalAttendance() throws InterruptedException {
		Thread.sleep(4000);
		hp=new HomePageObject(driver);
		//System.out.println("historical driver : "+driver);
		hp.getHistoryAttendance();
		hp.getYear("2022");
		hp.getMonth("October");
		Thread.sleep(2000);
		hp.getButton();
		List<WebElement> attendanceHistory1= hp.getAttendanceHistory();
		for(WebElement link:attendanceHistory1) {
			String day=link.getText();
			System.out.println("Date :"+day);
		}
	}

}
