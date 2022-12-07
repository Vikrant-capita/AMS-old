package tests.Homepage;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.homePageObjects.HomePageObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;

public class RegularizeAttendance extends BaseTest {
	public WebDriver driver;
	public HomePageObject hp;
	public LoginPage lp;
	String submitText;
	
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}	
	
	@Test(priority=0)
	public void activeAttendance() throws InterruptedException, IOException {
		
		//System.out.println("Attendance driver : "+driver);
		hp=new HomePageObject(driver);
		
		Properties prop=getProperties();
		String leaveTypeProp=prop.getProperty("leaveType");
		String categoryProp=prop.getProperty("category");
		String reasonProp=prop.getProperty("reason");
		String remarkProp=prop.getProperty("leaveRemark");
		String leaveTypeForSSProp=prop.getProperty("leaveTypeForSS");
			
		List<WebElement> UAList=hp.getualist();
		System.out.println("UA list size :"+UAList.size());
		int UAListNumber=0;
		List<WebElement> workingDateList=hp.getWorkingDateList();
				
		for(int i=0; i<=UAList.size()-1;i++) {
			
			if(UAList.get(i).getAttribute("style").contains("red") && !workingDateList.get(i).getText().contains("Saturday") && !workingDateList.get(i).getText().contains("Sunday") ) {
				
				UAList.get(i).click();
				UAListNumber=i;
				System.out.println("UA list number :"+UAListNumber);
				Thread.sleep(2000);
				 
				hp.getleaveType(leaveTypeProp);
				Thread.sleep(3000);
				hp.getCategory(categoryProp);
				hp.getReason(reasonProp);
				hp.getRemark(remarkProp);
				hp.getSubmitBtn();
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				String submitText=hp.getSubmitText();
				System.out.println("Submited Text outside :"+submitText);
			}
			else {
				UAList.get(i).click();
				UAListNumber=i;
				System.out.println("UA list number :"+UAListNumber);
				Thread.sleep(2000);
				 
				hp.getleaveType(leaveTypeForSSProp);
				Thread.sleep(3000);
				hp.getCategory(categoryProp);
				hp.getReason(categoryProp);
				hp.getRemark(remarkProp);
				hp.getSubmitBtn();
				driver.switchTo().alert().accept();
				Thread.sleep(3000);
				submitText=hp.getSubmitText();
				System.out.println("Submited Text outside :"+submitText);
			}
		}
		
		
		if(submitText.contains("Saved")) {
			System.out.println("UA list number :"+UAListNumber);
			for(int i=UAListNumber;i<=UAList.size()-1;i++) {
				System.out.println("inside for loop for green UA validation");
				String UAColor=UAList.get(i).getAttribute("style");
				Boolean flag=UAList.get(i).getAttribute("style").contains("DarkGreen");
				System.out.println("UA text :"+UAList.get(i).getText());
				Assert.assertTrue(flag);
			}	
		}
		else {
			//This exception is already handled
			//write code to handle this from manager account
			System.out.println("Submited Text :"+submitText);
		}
	}
	
	@Test(priority=1)
	public void validateUserLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("User Logout");
	}
	
	@Test(priority=2)
	public void validateMgrPendingExceptionFlow() throws IOException, InterruptedException {
		driver=lp.validateManagerLoginWOInitialize();
		tests.managerMyTeamApproval.pendingException.MgrPendingExceptionsTest mpe=new tests.managerMyTeamApproval.pendingException.MgrPendingExceptionsTest();
		mpe.validateManagerException(driver);
	}
	
	@Test(priority=3)
	public void validateManagerLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("Manager Logged out");
	}
		
	@Test(priority=4)
	public void validateUserAppliedAttendance() throws InterruptedException, IOException {
		driver=lp.validateLoginWOInitialize();
		System.out.println("User logged in again");
		//Thread.sleep(2000);
		
		
	}
	
	
	
	
	
	@Test(enabled=false)
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
