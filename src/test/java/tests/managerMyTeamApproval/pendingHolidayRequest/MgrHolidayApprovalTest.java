package tests.managerMyTeamApproval.pendingHolidayRequest;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.TestNGException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.managerMyTeamApprovalObjects.pendingHolidayReqObjects.PendingHolidayReqObject;
import resources.BaseTest;
import tests.LoginTest.LoginPage;

public class MgrHolidayApprovalTest extends BaseTest {

	public WebDriver driver;
	public LoginPage lp;
	
	
//	public MgrHolidayApprovalTest(WebDriver driver) {
//		this.driver=driver;
//	}
	
	@BeforeTest
	public void initialize() throws InterruptedException, IOException {
		lp=new LoginPage();
		driver=lp.validateManagerLogin();
		driver=lp.driver;
	}
	
	@Test
	public void validateholidayReq(WebDriver dr) throws InterruptedException {
		try{
			lp=new LoginPage();
		
		//driver=lp.driver;
		PendingHolidayReqObject holiReq=new PendingHolidayReqObject(dr);
		Thread.sleep(2000);
		System.out.println("before click on holiday ");
		String holidayCount=holiReq.getClickOnHoliday().getText();
		int holidayCountBefore=Integer.parseInt(holidayCount.substring(9, holidayCount.length()-1));
		System.out.println("holiday count Before accept/reject:"+holidayCountBefore);
		JavascriptExecutor js = (JavascriptExecutor)dr;
		js.executeScript("window.scrollBy(0,200)");
		holiReq.getClickOnPendingHoliReq();
		System.out.println("After click on holiday ");
		
		
//		holiReq.getClickOnHoliday().click();
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,200)");
//		holiReq.getClickOnPendingHoliReq();
		
		if(holiReq.getHolidayReqTable().isDisplayed()) {
			
			List<WebElement> holiReqTableList=holiReq.getHolidayTableList();
			System.out.println("holiday count table list :"+ holiReqTableList.size());
			holiReq.getClickoncheckBox();
			holiReq.getClickOnReject();
			String alertText=driver.switchTo().alert().getText();
			System.out.println("Aler pop text :"+driver.switchTo().alert().getText());
			Assert.assertEquals(alertText, "Are you sure to Reject?" ,"Alert text not matching");
			
			driver.switchTo().alert().accept();
			
			holiReq.getClickOnCapitaAMS();
			String holidayCountAfterText=holiReq.getClickOnHoliday().getText();
			
			if(holidayCountBefore>1) {
			int holidayCountAfter=Integer.parseInt(holidayCountAfterText.substring(9, holidayCountAfterText.length()-1));
			System.out.println("Holiday count after accept/reject :"+holidayCountAfter);
			Assert.assertEquals(holidayCountBefore, holidayCountAfter+1);
			}
//			else {
//				Assert.assertFalse(holiReq.getClickOnHoliday().isDisplayed());
//			}
		}
		
		} 
		catch(TestNGException test) {
			lp=new LoginPage();
			
			driver=lp.driver;
			PendingHolidayReqObject holiReq=new PendingHolidayReqObject(driver);
			Thread.sleep(2000);
			System.out.println("before click on holiday ");
			String holidayCount=holiReq.getClickOnHoliday().getText();
			int holidayCountBefore=Integer.parseInt(holidayCount.substring(9, holidayCount.length()-1));
			System.out.println("holiday count Before accept/reject:"+holidayCountBefore);
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,200)");
			holiReq.getClickOnPendingHoliReq();
			System.out.println("After click on holiday ");
			
			
//			holiReq.getClickOnHoliday().click();
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("window.scrollBy(0,200)");
//			holiReq.getClickOnPendingHoliReq();
			
			if(holiReq.getHolidayReqTable().isDisplayed()) {
				
				List<WebElement> holiReqTableList=holiReq.getHolidayTableList();
				System.out.println("holiday count table list :"+ holiReqTableList.size());
				holiReq.getClickoncheckBox();
				holiReq.getClickOnReject();
				String alertText=driver.switchTo().alert().getText();
				System.out.println("Aler pop text :"+driver.switchTo().alert().getText());
				Assert.assertEquals(alertText, "Are you sure to Reject?" ,"Alert text not matching");
				
				driver.switchTo().alert().accept();
				
				holiReq.getClickOnCapitaAMS();
				String holidayCountAfterText=holiReq.getClickOnHoliday().getText();
				
				if(holidayCountBefore>1) {
				int holidayCountAfter=Integer.parseInt(holidayCountAfterText.substring(9, holidayCountAfterText.length()-1));
				System.out.println("Holiday count after accept/reject :"+holidayCountAfter);
				Assert.assertEquals(holidayCountBefore, holidayCountAfter+1);
				}
//				else {
//					Assert.assertFalse(holiReq.getClickOnHoliday().isDisplayed());
//				}
			}
		}
	}
	
	public void tearDown() {
		driver.quit();
	}
	
}
