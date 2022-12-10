package tests.myDetails;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
//import junit.framework.Assert;
import pageObjects.myDetailsObjects.myHolidayListObjects.MyHolidayObject;
import tests.Homepage.ValidateExceptionLeavesPendingActions;
import tests.LoginTest.LoginPage;
import tests.managerMyTeamApproval.pendingHolidayRequest.MgrHolidayApprovalTest;
import utils.DateConversionFormat;
import utils.UserManagerDetailsValidation;


public class MyHolidayList {
	public WebDriver driver;
	//public SoftAssert sa;
	public DateConversionFormat df;
	String appliedHolidayName;
	String submitMsgText;
	List<WebElement> holidayNameList;
	public LoginPage lp;
	public MyHolidayObject md;
	
	List<String> selectedlist;
	String alertMessage;
	SoftAssert sa = new SoftAssert();
	public ValidateExceptionLeavesPendingActions userPenLeavHoliReq;
	
	@Test(priority=1)
	public void myHolidayList() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		md=new MyHolidayObject(driver);
		UserManagerDetailsValidation userMgr1=new UserManagerDetailsValidation(driver);
		userMgr1.usersManagerDetailsValidation(md.getEmpName(), md.getEmpID(), md.getManagerName(), md.getManagerID());
			
		md.getYear("2022");
		Thread.sleep(2000);
		String yelloHoliday=md.getYellowHoliday();
		sa.assertEquals(2022, "*Yellow background denotes Holidays falling on Saturday and Sunday");
		
//		List<WebElement> holidayList=md.getHolidayList();
//		for(WebElement list:holidayList) {
//			String holidayTittle=list.getText().split("- ")[1];
//			System.out.println(holidayTittle);	
//		}
		
		 
	}
	
	@Test(priority=2)//(dependsOnMethods= {"amstest"})
	public void  validateMyHolidayList() throws InterruptedException
	{
		//driver.findElement(By.id("TreeMenu1_MenuTreeViewt1")).click();
		Thread.sleep(2000);
		//List<WebElement> lists = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label"));
		//System.out.println("size: "+lists.size());
		
		//=============Validation of all holidays are with future dates=============================
		md=new MyHolidayObject(driver);
		List<WebElement> holidayListWOYellow= md.getholidayListWOYellow();
		for(WebElement list: holidayListWOYellow)
		{
			String text = list.getText();
			String schedule = text.split("- ")[1];
			System.out.println(schedule);
			df=new DateConversionFormat();
			df.dateFormatConversion(schedule);
	
		}
		//List<WebElement> yellowlist = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td//span/label"));
		List<WebElement> holidayListWithYellow= md.getholidayListWithYellow();
		for(WebElement list: holidayListWithYellow)
		{
			String text = list.getText();
			String schedule = text.split("- ")[1];
			//System.out.println(schedule);
			df=new DateConversionFormat();
			df.dateFormatConversion(schedule);
		}
	}
	
	//======================To validate Saturday and Sunday holidays are highlighted in yellow background=============
	@Test(priority=3)
	public void validateYellowHolidays() {
		List<WebElement> lists=	md.getholidayListWithYellow();
		for(WebElement list:lists)
		{
			String text=list.getText().split(" -")[0].split("[.] ")[1];
			//System.out.println("yellow holiday text:"+text);
			if(text.contains("Saturday") || text.contains("Sunday"))
			{
				//System.out.println("Highlight yellow holiday are saturday or sunday");
				sa.assertTrue(true,"Highlight yellow holiday are saturday or sunday");
			}
			else
			{
				sa.assertTrue(false,"Highlight yellow holiday not saturday or sunday");
			}
		}
	}
	
	@Test(priority=4)
	public void validateSelectedHolidays() throws InterruptedException {
			List<WebElement> lists = md.getAllHolidayNameList();
			int beforelist = lists.size();
			//System.out.println("before list size (7) : "+beforelist);
			//System.out.println("submit list before submit (0) : "+ml.getsubmitedList().size());
			selectedlist = new ArrayList<>();
			int n=1;
			for(int i=0;i<lists.size();i++)
			{
				if(n<3)
				{
				lists.get(i).click();
					selectedlist.add(lists.get(i).getText().split(" [....]")[0]);
					//System.out.println("selected list : "+lists.get(i).getText());
					n++;
				}
				
			}
			//System.out.println("selected size(2) : "+selectedlist.size());
			md.getSubmitBtn();
			Thread.sleep(2000);
			
			if(selectedlist.size()>2) {
				alertMessage= md.getSubmitMsg();
				sa.assertEquals(alertMessage, "Already Credited or you can select maximum 2","You have selected more holiday list");
			}
			else
			{
				alertMessage= md.getSubmitMsg();
				sa.assertEquals(alertMessage,"Holiday applied","Alert message verification failed after submit");
			}
			
			
	}
		
	@Test(priority=5)
	public void validateSubmittedHolidayList() {
		List<String> submitedList=md.getSubmittedHolidayNameList();
		if(submitedList.size()>2)
		{
			//System.out.println("Alert messege : Submitted list limit exceeded");
			sa.assertTrue(false,"Alert messege : Submitted list limit exceeded");
		}
		else
		{
			//System.out.println("submitted list within limit");
		}
		if(selectedlist.size()==submitedList.size()) {
			sa.assertEquals(selectedlist,submitedList,"List not matched");
			
		}
		else {
			sa.assertTrue(false,"Selected and submitted count not matched");
			//System.out.println("Selected and submitted count not matched");
		}

		System.out.println("Selected list : "+selectedlist);
		System.out.println("submitted list : "+submitedList);
		
	}
		
		
		
		
	/*
		//================old code==================================
		//to click on second holiday checkbox
		List<WebElement> holidayCheckBoxList=md.getAllHolidayCheckBoxList();
		int beforeApplyHolidaysize=holidayCheckBoxList.size();
		holidayNameList=md.getAllHolidayNameList();
		for(int i=0;i<=holidayCheckBoxList.size()-1;i++) {
			holidayCheckBoxList.get(i+1).click();
		    String appliedHolidayNameText=holidayNameList.get(i+1).getText();
			break;
		}
		md.getSubmitBtn();
		Thread.sleep(2000);
		String submitMsgText=md.getSubmitMsg();
		System.out.println("submitted text :"+submitMsgText());
		int afterApplyHolidaysize=holidayCheckBoxList.size();
		System.out.println("after submitted holiday list size :"+afterApplyHolidaysize);
		
		//Assert.assertEquals(afterApplyHolidaysize, beforeApplyHolidaysize-1);  //after holiday applied list size is still showing same as previous..need to check diffrenet tech to locate element
		Assert.assertEquals(submitMsgText, "Holiday applied", "Holiday submit verification text/msg is not matching");
		
		//holiday table validation=================================
		if(md.gettableExist().isDisplayed()) {
			List<WebElement> holidayNameListTable= md.getHolidayNameList();
			System.out.println("holiday list size :"+holidayNameList.size());
			
			
		//=================Holiday Cancellation=========================	
			md.getCancelBtn();
			driver.switchTo().alert().accept();
			Thread.sleep(2000);
			String cancelMsgText=md.getSubmitMsg();
			//Thread.sleep(2000);
			System.out.println("Cancel btn text :"+cancelMsgText);
			Assert.assertEquals(cancelMsgText, "Holiday deleted", "Holiday cancel verification text/msg is not matching");		
		*/
		


	@Test(priority=6)
	public void validateUserLogout() throws InterruptedException {
		// TODO Auto-generated method stub
		lp.validateLogout();
		System.out.println("User Logout");
	}
	
	@Test(priority=7)
	public void validateMgrHolidayReqFlow() throws IOException, InterruptedException {
		driver=lp.validateManagerLoginWOInitialize();
		//driver=lp.driver;
		System.out.println("Manager logged in succesful");
		MgrHolidayApprovalTest mgrHoliAppr=new MgrHolidayApprovalTest();
		mgrHoliAppr.validateholidayReq1(driver); 
		

	}
	
	@Test(priority=8)
	public void validateManagerLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("Manager Logged out");
	}
	
	@Test(priority=9)
	public void validateUserAppliedHolidays() throws InterruptedException, IOException {
		driver=lp.validateLoginWOInitialize();
		System.out.println("User logged in again");
		Thread.sleep(2000);
		userPenLeavHoliReq=new ValidateExceptionLeavesPendingActions();
		List<String> pendingActionText=userPenLeavHoliReq.validatePendingLeave1(driver);
		System.out.println("User Pending action text :"+pendingActionText);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
