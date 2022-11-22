package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveBalanceObject;
import pageObjects.myLeavesObjects.myLeavesobject.leaveCancelObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginTest.LoginPage;
import utils.excelDriven;

public class leaveCancel {
	
	public WebDriver driver;
	
	public void validateLeaveCancel() throws InterruptedException, IOException {
		
		
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		//to click on maim my leaves button ----------need to remove later--------------
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getMyLeaves();
		//-----------------------------------------------
		
		leaveCancelObject lb=new leaveCancelObject(driver);
		lb.getLeaveChannel();
		String empName=lb.getEmployeeName();
		
		HomePageObject hp=new HomePageObject(driver);
		String ab=hp.getUserNameText1().split("e ")[1];
		//username=hp.userNameText;
		System.out.println("usename: "+ab);
		System.out.println(empName);
		
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
		
		String empID=lb.getEmpID();
		String managerName=lb.getmanagerName();
		String managerID=lb.getmanagerID();
		//String managerID=ml.get
		
		Assert.assertEquals(empID, EMPID);
		Assert.assertEquals(managerName, ManagerName);
		Assert.assertEquals(managerID, ManagerID);
		
		
	}
	

}
