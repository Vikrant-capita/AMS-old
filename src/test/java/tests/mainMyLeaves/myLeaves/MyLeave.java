package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.MyLeaveObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginPage;
import utils.excelDriven;

public class MyLeave {
	
	public WebDriver driver;
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void validateMyLeave() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getMyLeaves();
		
		MyLeaveObject leaveplan=new MyLeaveObject(driver);
		leaveplan.getMyLeave();
		String empName=leaveplan.getEmployeeName();
		HomePageObject hp=new HomePageObject(driver);
		String ExpectedUserNameText=hp.getUserNameText1().split("e ")[1];
		Assert.assertEquals(empName, ExpectedUserNameText);
		System.out.println("actual name :"+empName+  " Expected Name"+ExpectedUserNameText);
		
		excelDriven excel=new excelDriven();
		ArrayList<String> data=excel.getData(empName, "Username");
		
		String UserID=data.get(0);
		String Password=data.get(1);
		String Username=data.get(2);
		String EMPID=data.get(3);
		String ManagerName=data.get(4);
		String ManagerID=data.get(5);
		
		System.out.println(EMPID);
		System.out.println(ManagerName);
		System.out.println(ManagerID);
		
		String empID=leaveplan.getEmpID();
		String managerName=leaveplan.getmanagerName();
		String managerID=leaveplan.getmanagerID();
		//String managerID=ml.get
		
		Assert.assertEquals(empID, EMPID);
		Assert.assertEquals(managerName, ManagerName);
		Assert.assertEquals(managerID, ManagerID);
		
		List<WebElement> leaveTypeOptions=leaveplan.getLeaveType("CASUAL LEAVE (CL)");
		
		System.out.println("List size :"+leaveTypeOptions.size());
		
//		excelDriven exceld=new excelDriven();
//		exceld.getData(String LeaveType, S Options,LeaveType);
		
		for(WebElement list:leaveTypeOptions) {
			System.out.println(list.getText());
			
		}
		
	}
	

}
