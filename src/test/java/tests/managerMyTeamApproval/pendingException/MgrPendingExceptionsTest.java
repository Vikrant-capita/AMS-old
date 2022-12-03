package tests.managerMyTeamApproval.pendingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import junit.framework.Assert;
import pageObjects.homePageObjects.HomePageObject;
import pageObjects.managerMyTeamApprovalObjects.pendingExceptionObjects.PendingExceptionObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;
import utils.excelDriven.excelDriven;

public class MgrPendingExceptionsTest extends BaseTest {

	public WebDriver driver;
	public HomePageObject hp;
	public Properties prop;
	public LoginPage lp;
	List<WebElement> excpDetailedList;
	public PendingExceptionObject penExcp;
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		//driver = initializeDriver();
		lp.validateManagerLogin();
		driver=lp.driver;
	}
	
	public void validateMgrEmpDetails() throws IOException {
		
		PendingExceptionObject penExcp=new PendingExceptionObject(driver);
		String empName= penExcp.getEmpName();
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
		
		String empID=penExcp.getEmpID();
		String managerName=penExcp.getManagerName();
		String managerID=penExcp.getManagerID();
		//String managerID=ml.get
		
		Assert.assertEquals(empID, EMPID);
		Assert.assertEquals(managerName, ManagerName);
		Assert.assertEquals(managerID, ManagerID);
	}
		
		
	@Test
	public void validateManagerException() {
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,150)");
		penExcp.getClickOnPendingException();
		String excpCountStr=penExcp.getExceptionCount();
		int excpCount =Integer.parseInt(excpCountStr);
		if(excpCount>0) {
			List<WebElement> excpDetailedList=penExcp.getExceptionDetails();
			int excpListSize=excpDetailedList.size();
		
			
			penExcp.getCheckboxEmpName();
			penExcp.getRejectEmpName();
			String alertMsg=driver.switchTo().alert().getText();
			System.out.println("Alert msg :"+alertMsg);
			driver.switchTo().alert().accept();
			
			String submitMsg=penExcp.getSubmitMsg();         //Saved successfully | Vikrant  Bingi (50096390)  | Working Date : 25-Nov-2022
			System.out.println("Submit Msg :"+submitMsg);
			
			int updatedExcpCount=Integer.parseInt(penExcp.getExceptionCount());
			Assert.assertEquals(updatedExcpCount, excpCount-1,"Exception count is not matched");
		    Assert.assertTrue(submitMsg.contains("Saved successfully | Vikrant  Bingi (50096390)"), "Not successfully submitted ");
		}
		else {
			System.out.println("No record available to approve/reject");
		}
	}
}
	
