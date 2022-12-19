package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.MyLeaveObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginTest.LoginPage;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class MyLeave {
	
	public WebDriver driver;
	
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		driver=lp.validateManagerLogin();
	}
	
		
	
	@SuppressWarnings("deprecation")
	@Test
	public void validateMyLeave() throws InterruptedException, IOException {
			
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		
		MyLeaveObject leaveplan=new MyLeaveObject(driver);
		leaveplan.getMyLeave();
		String empName=leaveplan.getEmpName();
		
		UserManagerDetailsValidation userMgr=new UserManagerDetailsValidation(driver);
		userMgr.usersManagerDetailsValidation(leaveplan.getEmpName(), leaveplan.getEmpID(), leaveplan.getManagerName(), leaveplan.getManagerID());
		String leavetype="CASUAL LEAVE (CL)";
		String indexOfwaiting = "waiting for approval";
		String indexOfAvailed = "availed";
		List<WebElement> leaveTypeOptions=leaveplan.getLeaveType(leavetype);
		Thread.sleep(1500);
		//System.out.println("List size :"+leaveTypeOptions.size());
		//List<String> workingDateListY22 = leaveplan.getWorkingDateListY22();
		//int workingDateListSizeY22 = workingDateListY22.size();
		int waitingStatusSize=0;
		int approveStatusSize=0;
		//System.out.println("List size :"+workingDateListSizeY22);
		List<String> status=leaveplan.getStatus();
		for(String st:status)
		{
			//System.out.println("status:"+st);
			if(st.contains("Waiting"))
			{
				waitingStatusSize++;
				//System.out.println("inside waiting if");
			}
			if(st.contains("Approved"))
			{
				approveStatusSize++;
				//System.out.println("inside approve if");
			}
		}
		System.out.println("**waiting size:"+waitingStatusSize);
		System.out.println("++approve size:"+approveStatusSize);
		leaveplan.getClickonPage2();
		leaveplan.getClickOnLeaveBalance();
		leaveplan.getClickLeaveBalanceYear("2022");
		int waitingValue=leaveplan.getLeaveTypeIndex(leavetype,indexOfwaiting);
		int approveValue=leaveplan.getLeaveTypeIndex(leavetype,indexOfAvailed);
		System.out.println("**waiting value:"+waitingValue);
		System.out.println("++approve value:"+approveValue);
		Assert.assertEquals(waitingStatusSize, waitingValue,"waiting for approval Status and value validation failed");
		Assert.assertEquals(approveStatusSize, approveValue,"Approved Status and value validation failed");
		
	}
	
	
	
	

}
