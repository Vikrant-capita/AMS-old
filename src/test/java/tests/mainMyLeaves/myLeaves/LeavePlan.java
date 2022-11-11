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

import pageObjects.myLeavesObjects.myLeavesObject;
import tests.LoginPage;
import tests.Homepage.ValidateHomepage;
import utils.excelDriven;

public class LeavePlan {
	
	public WebDriver driver;
	public myLeavesObject ml;
	public String username;
	public String[] monthYearText1;
	public String[] monthYearText2;
	
	@Test
	public void validateMyLeaves() throws InterruptedException, IOException {
	LoginPage lp=new LoginPage();
	lp.validatelogin();
	driver = lp.driver;
	ml=new myLeavesObject(driver);
	Thread.sleep(2000);
	ml.getMyLeaves();
	Thread.sleep(2000);
	ml.getLeavePlan();
	String empName=ml.getEmployeeName();
	ValidateHomepage hp=new ValidateHomepage();
	String ab=hp.validateRightPanelText();
	//username=hp.userNameText;
	System.out.println(ab);
	System.out.println(empName);
	System.out.println("outer class data "+username);
	
	//Assert.assertEquals(empName, username);
	
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
	
	
	getCalendor("December", "2022", "20", monthYearText1,clickOnRightArrow1, allDateList1);
	Thread.sleep(2000);
	ml.getToDateClick();
	monthYearText2=ml.getMonthYear2().split(", ");
	System.out.println("to month year :"+monthYearText2[0]);
	WebElement clickOnRightArrow2=ml.getclickOnRightArrow2();
	List<WebElement> allDateList2=ml.getDateList2();
	getCalendor("December", "2022", "22", monthYearText2, clickOnRightArrow2, allDateList2);
	//getCalendor("December","2022","22",toDateClick);
	
	ml.getLeaveType("CASUAL LEAVE (CL)");
	Thread.sleep(5000);
	ml.getCategory("General");
	ml.getRemark("Applying for CL");
	String monthDisplayed=ml.getMonthDisplay();
	//Assert.assertEquals(monthDisplayed, );
	

	
}
	
	public void getCalendor(String month, String year, String date, String[] m, WebElement clickOnArrow, List<WebElement> allDateList) {
		//ml=new myLeavesObject(driver);
		while(!(m[0].contains(month)&& m[1].contains(year))){
			clickOnArrow.click();
			//ml.getclickOnRightArrow();
			System.out.println("inside while loop");
			break;
			
		}
		//List<WebElement> allDateList=ml.getDateList();
		for(WebElement singleDate:allDateList){
			String text=singleDate.getText();
			if(text.equalsIgnoreCase(date)) {
				singleDate.click();
				break;
			}
		}
	}

	@AfterTest(enabled=true)
	public void tearDown() {
		//driver.close();
	}
}

