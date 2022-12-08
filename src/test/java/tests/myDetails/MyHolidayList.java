package tests.myDetails;

import java.io.IOException;
import java.time.Duration;
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
import tests.LoginTest.LoginPage;
import tests.managerMyTeamApproval.pendingHolidayRequest.MgrHolidayApprovalTest;
import utils.DateConversionFormat;

public class MyHolidayList {
	public WebDriver driver;
	public SoftAssert sa;
	public DateConversionFormat df;
	String appliedHolidayName;
	String submitMsgText;
	List<WebElement> holidayNameList;
	public LoginPage lp;
	
	@Test(priority=0)
	public void myHolidayList() throws InterruptedException, IOException {
		lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		MyHolidayObject md=new MyHolidayObject(driver);
		sa=new SoftAssert();
		md.getMyHolidayList();
		String empName=md.getempName();
		sa.assertEquals(empName, "Vikrant Bingi");
		String lm=md.getlineManager();
		sa.assertEquals(empName, "Pradnya  Patil"); 
		String lmCode=md.getlmCode();
		sa.assertEquals(lmCode, "50044121"); 
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
	
	@Test(priority=1)//(dependsOnMethods= {"amstest"})
	public void  validateMyHolidayList() throws InterruptedException
	{
	//driver.findElement(By.id("TreeMenu1_MenuTreeViewt1")).click();
	Thread.sleep(2000);
	//List<WebElement> lists = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label"));
	//System.out.println("size: "+lists.size());
	MyHolidayObject md=new MyHolidayObject(driver);
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
	System.out.println("submitted text :"+submitMsgText);
	int afterApplyHolidaysize=holidayCheckBoxList.size();
	System.out.println("after submitted holiday list size :"+afterApplyHolidaysize);
	
	//Assert.assertEquals(afterApplyHolidaysize, beforeApplyHolidaysize-1);  //after holiday applied list size is still showing same as previous..need to check diffrenet tech to locate element
	Assert.assertEquals(submitMsgText, "Holiday applied", "Holiday submit verification text/msg is not matching");
	
	//holiday table validation=================================
	if(md.gettableExist().isDisplayed()) {
		List<WebElement> holidayNameListTable= md.getHolidayNameList();
		System.out.println("holiday list size :"+holidayNameList.size());
	/*
	//Holiday Cancellation=========================	
		md.getCancelBtn();
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		String cancelMsgText=md.getSubmitMsg();
		//Thread.sleep(2000);
		System.out.println("Cancel btn text :"+cancelMsgText);
		Assert.assertEquals(cancelMsgText, "Holiday deleted", "Holiday cancel verification text/msg is not matching");		
	*/
	}	
}

	@Test(priority=2)
	public void validateUserLogout() throws InterruptedException {
		// TODO Auto-generated method stub
		lp.validateLogout();
		System.out.println("User Logout");
	}
	
	@Test(priority=3)
	public void validateMgrHolidayReqFlow() throws IOException, InterruptedException {
		driver=lp.validateManagerLoginWOInitialize();
		//driver=lp.driver;
		System.out.println("Manager logged in succesful");
		MgrHolidayApprovalTest mgrHoliAppr=new MgrHolidayApprovalTest();
		mgrHoliAppr.validateholidayReq1(driver); 
		

	}
	
	@Test(priority=4)
	public void validateManagerLogout() throws InterruptedException {
		lp.validateLogout();
		System.out.println("Manager Logged out");
	}
	
	@Test(priority=5)
	public void validateUserAppliedHolidays() throws InterruptedException, IOException {
		driver=lp.validateLoginWOInitialize();
		System.out.println("User logged in again");
		//Thread.sleep(2000);	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
}
