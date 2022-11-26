package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import resources.BaseTest;
import tests.Homepage.ValidateHomepage;
import tests.LoginTest.LoginPage;
import utils.CalenderHandle;
import utils.excelDriven;

public class LeavePlan extends BaseTest{
	
	public WebDriver driver;
	public leavePlanObject ml;
	public String username;
	public String[] monthYearText1;
	public String[] monthYearText2;
	public CalenderHandle ch;
	String expectedDate;
	String monthDisplayed;
	String toDate;
	String toMonth;
	String toYear;
	public String calendarStatusText;
	public String scheduleTimeText;
	public String submitmsg;
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	@Test(priority=0)
	public void validateMyLeaves() throws InterruptedException, IOException {
		
	//to click on maim my leaves button ----------need to remove later--------------
	ml=new leavePlanObject(driver);
	scheduleTimeText=ml.getScheduleTime();
	
	System.out.println("schedule time in leave plan :"+scheduleTimeText);
	ml.getClickOnMyLeaves();
	
	//-----------------------------------------------
	
	Thread.sleep(2000);
	ml.getLeavePlan();
	String empName=ml.getEmployeeName();
	HomePageObject hp=new HomePageObject(driver);
	String ab=hp.getUserNameText1().split("e ")[1];
	//username=hp.userNameText;
	System.out.println("usename: "+ab);
	System.out.println(empName);
	System.out.println("outer class data "+username);
	
	Assert.assertEquals(empName, ab);
	
	excelDriven excel=new excelDriven();
	ArrayList<String> data=excel.getData(empName, "Username");
	
	String UserID=data.get(0);
	String Password=data.get(1);
	String Username=data.get(2);
	String EMPID=data.get(3);
	String ManagerName=data.get(4);
	String ManagerID=data.get(5);
	
	String empID=ml.getEmpID();
	String managerName=ml.getmanagerName();
	String managerID=ml.getmanagerID();
	//String managerID=ml.get
	
	Assert.assertEquals(empID, EMPID);
	Assert.assertEquals(managerName, ManagerName);
	Assert.assertEquals(managerID, ManagerID);
	}
	
	
	@Test(priority=1)
	public void validateLeavePlanFlow() throws IOException, InterruptedException {
		ml.getFromDateClick();
		
		monthYearText1=ml.getMonthYear1();
		System.out.println("from month year :"+monthYearText1[0]);
		
		WebElement clickOnRightArrow1=ml.getclickOnRightArrow1();
		
		List<WebElement> allDateList1=ml.getDateList();
		
		Properties prop=getProperties();
		String fromMonth=prop.getProperty("fromMonth");
		String fromYear=prop.getProperty("fromYear");
		String fromDate=prop.getProperty("fromDate");
		
		WebElement monthYearEle1= ml.getMonthYearEle1();
		
		ch=new CalenderHandle();
		ch.getCalendor(fromMonth, fromYear, fromDate, monthYearText1,clickOnRightArrow1, allDateList1,monthYearEle1);
		Thread.sleep(2000);
		ml.getToDateClick();
		monthYearText2=ml.getMonthYear2().split(", ");
		System.out.println("to month year :"+monthYearText2[0]);
		WebElement clickOnRightArrow2=ml.getclickOnRightArrow2();
		List<WebElement> allDateList2=ml.getDateList2();
		
		toMonth=prop.getProperty("toMonth");
		toYear=prop.getProperty("toYear");
		toDate=prop.getProperty("toDate");
		
		WebElement monthYearEle2= ml.getMonthYearEle2();
		
		ch.getCalendor(toMonth, toYear, toDate, monthYearText2, clickOnRightArrow2, allDateList2,monthYearEle2);
		Thread.sleep(1000);
		// 'To date' greater than from date
		if(ml.getCalDateError().isDisplayed()) {
			String calDateError=ml.getCalDateError().getText();
		    System.out.println(" Condition 7 : Cal date Error Text :" +ml.getCalDateError().getText());
			Assert.assertEquals(calDateError, "To date should be greater than equal From date.");
			Assert.assertTrue(false);
		}
				
		
		
		
		
		ml.getLeaveType("Work From Home(WFH)");
		Thread.sleep(4000);
		ml.getCategory("--NA--");
		ml.getRemark("Applying for CL");
		Thread.sleep(2000);
		String monthYearDisplayed=ml.getMonthYearCalDisplay().getText();
		// monthDisplayed=ml.getMonthDisplay();
		System.out.println("Month Displayed :"+monthYearDisplayed);
		ml.getClickOnSubmitBtn();
		
		CalendarDatesValidation calError= new CalendarDatesValidation(driver);
		calError.calendarErrorValidation(fromMonth, fromYear,fromDate, toMonth,toYear, toDate,scheduleTimeText);
		
	}
	
	

	//@Test(priority=2, enabled=false)
	public void validateCalendor() throws InterruptedException {
		//
		Assert.assertEquals(monthDisplayed,expectedDate);		//validate passed month and year with calendor month and year
		Thread.sleep(1000);
		List<WebElement> calendarList=ml.getCalendarDateList();
		for(int i=0; i<=calendarList.size()-1; i++) {
			String text=calendarList.get(i).getText();
			if(text.equalsIgnoreCase(toDate)) {
				calendarStatusText=ml.getCalendarStatusList();
				System.out.println("calendar text inside loop :"+ calendarStatusText);
			}
		}
		if(calendarStatusText.contains("Scheduled")||calendarStatusText.contains("WO")) {
		Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
	}
	
	//@Test(priority=3,enabled=false)
	public void validateStatusOfSubmittedRequest() {
		List<WebElement> workingDateList=ml.getWorkingDateList();
		System.out.println("working date size list :"+workingDateList.size());
		for(int i=0;i<=workingDateList.size()-1;i++) {
			String[] workingDateArrText=workingDateList.get(i).getText().split("-");
			System.out.println("working date text :"+ workingDateList.get(i).getText());
			if(workingDateArrText[0].contains(toDate)&&workingDateArrText[1].contains(toMonth)&&workingDateArrText[1].contains(toYear)) {
				boolean flag=ml.getLeaveStatusList().get(i).getText().contains("Waiting for approval");
				Assert.assertTrue(flag);
			}
		}
	}
	
	
	
	
		
	
	@AfterTest(enabled=true)
	public void tearDown() {
		
	//driver.close();
	}
}

