package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginPage;
import tests.Homepage.ValidateHomepage;
import utils.CalenderHandle;
import utils.excelDriven;

public class LeavePlan {
	
	public WebDriver driver;
	public leavePlanObject ml;
	public String username;
	public String[] monthYearText1;
	public String[] monthYearText2;
	public CalenderHandle ch;
	
	@Test
	public void validateMyLeaves() throws InterruptedException, IOException {
	LoginPage lp=new LoginPage();
	lp.validatelogin();
	driver = lp.driver;
	
	//to click on maim my leaves button ----------need to remove later--------------
	ml=new leavePlanObject(driver);
	Thread.sleep(2000);
	ml.getMyLeaves();
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
	
	System.out.println(EMPID);
	
	String empID=ml.getEmpID();
	String managerName=ml.getmanagerName();
	String managerID=ml.getmanagerID();
	//String managerID=ml.get
	
	Assert.assertEquals(empID, EMPID);
	Assert.assertEquals(managerName, ManagerName);
	Assert.assertEquals(managerID, ManagerID);
	
	ml.getFromDateClick();
	
	monthYearText1=ml.getMonthYear1().split(", ");
	System.out.println("from month year :"+monthYearText1[0]);
	
	WebElement clickOnRightArrow1=ml.getclickOnRightArrow1();
	
	List<WebElement> allDateList1=ml.getDateList();
	
	
	ch.getCalendor("December", "2022", "20", monthYearText1,clickOnRightArrow1, allDateList1);
	Thread.sleep(2000);
	ml.getToDateClick();
	monthYearText2=ml.getMonthYear2().split(", ");
	System.out.println("to month year :"+monthYearText2[0]);
	WebElement clickOnRightArrow2=ml.getclickOnRightArrow2();
	List<WebElement> allDateList2=ml.getDateList2();
	CalenderHandle ch=new CalenderHandle();
	ch.getCalendor("December", "2022", "22", monthYearText2, clickOnRightArrow2, allDateList2);
	//getCalendor("December","2022","22",toDateClick);
	
	ml.getLeaveType("CASUAL LEAVE (CL)");
	Thread.sleep(5000);
	ml.getCategory("General");
	ml.getRemark("Applying for CL");
	String[] monthDisplayed=ml.getMonthDisplay().split(" ");
	
	System.out.println("month display: "+monthDisplayed);
	//Assert.assertEquals(monthDisplayed,monthYearText2);		//validate passed month and year with calendor month and year
	

	
}
	

	@AfterTest(enabled=true)
	public void tearDown() {
		//driver.close();
	}
}

