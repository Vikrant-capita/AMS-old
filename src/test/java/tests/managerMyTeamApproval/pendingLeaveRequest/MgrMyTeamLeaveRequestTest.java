package tests.managerMyTeamApproval.pendingLeaveRequest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
//import junit.framework.Assert;
import pageObjects.homePageObjects.managerHomePageObjects;
import pageObjects.managerMyTeamApprovalObjects.pendingLeaveReqObjects.MyTeamLeaveRequestObject;
import tests.LoginTest.LoginPage;

public class MgrMyTeamLeaveRequestTest {

	public WebDriver driver;
	public LoginPage lp;
	boolean leavePendingActionDisplayed;
	public managerHomePageObjects  mgrHP;
	public String pendingActLeavesText;
	
	
	
//	public MgrMyTeamLeaveRequestTest(WebDriver driver) {
//		// TODO Auto-generated constructor stub
//		this.driver=driver;
//	}

	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validateManagerLogin();
		driver=lp.driver;
	}
	
	@Test
	public void validateManagerMyPendingAction() {
		mgrHP=new managerHomePageObjects(driver);
		leavePendingActionDisplayed=mgrHP.getLeavesPendingAction().isDisplayed();
		System.out.println(leavePendingActionDisplayed);
		String pendingActLeavesText=mgrHP.getLeavesPendingAction().getText();
		System.out.println("pending action leaves text :"+pendingActLeavesText);
	}
	
	@Test
	public void validateMyTeamLeaveRequest() throws InterruptedException {
		
		if(leavePendingActionDisplayed) {
			MyTeamLeaveRequestObject teamLeaveReq=new MyTeamLeaveRequestObject(driver);
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//Thread.sleep(2000);
			mgrHP.getLeavesPendingAction().click();
			System.out.println("pending action leaves text :"+pendingActLeavesText);
			//teamLeaveReq.getclickOnPendingLeaveRequest();
			//teamLeaveReq.getClickOnMyTeamLeaveReq();
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,100)");
			String empNameText=teamLeaveReq.getClickOnEmpName().getText();
			System.out.println("Emp Name :"+empNameText);
			Thread.sleep(1000);
			teamLeaveReq.getClickOnEmpName().click();
						
			List<WebElement> leaveRequestList= teamLeaveReq.getTeamLeaveRequestTable();
			System.out.println("request list size :"+leaveRequestList.size());
			for(WebElement leaveRequest:leaveRequestList) {
				String leaveRequestText=leaveRequest.getText();
				System.out.println("Leave request text :"+leaveRequestText);
			}
			Thread.sleep(5000);
			teamLeaveReq.getActionDD("Reject");
			teamLeaveReq.getSubmitBtn();
			System.out.println("Leave submitted");
			String submitMsg=teamLeaveReq.getSubmitMsg();
			//Assert.assertEquals(submitMsg, "Saved");
			
		}
		
	}
	
	
}
