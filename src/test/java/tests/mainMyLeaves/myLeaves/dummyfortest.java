package tests.mainMyLeaves.myLeaves;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.myLeavesObjects.myLeavesobject.MyLeaveObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginTest.LoginPage;

public class dummyfortest {

	public WebDriver driver;
	public 	MyLeaveObject leaveplan;
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		driver=lp.validatelogin();
		//driver=lp.validateManagerLogin();
	}
	@Test(priority=1)
	public void ValidatePrivilegeLeavePL() throws InterruptedException, IOException
	{
		String leavetype="PRIVILEGE LEAVE (PL)";
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		leaveplan=new MyLeaveObject(driver);
		leaveplan.getMyLeave();
		leaveplan.getLeaveType(leavetype);
		System.out.println("size:"+leaveplan.getWorkingDateList().size());
		if(leaveplan.getWorkingDateList().size()!=0)
		{
			System.out.println(leavetype+":List Found "+leaveplan.getWorkingDateList().size());
		}
		else
		{
			System.out.println(leavetype+":No List Found");
		}
	}
}
