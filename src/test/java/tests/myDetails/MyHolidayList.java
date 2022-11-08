package tests.myDetails;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.myDetailsObject.MyDetails;
import tests.LoginPage;

public class MyHolidayList {
	public WebDriver driver;
	public SoftAssert sa;
	
	@Test
	public void myHolidayList() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		MyDetails md=new MyDetails(driver);
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
		
		List<WebElement> holidayList=md.getHolidayList();
		for(WebElement list:holidayList) {
			String holidayTittle=list.getText().split("- ")[1];
			System.out.println(holidayTittle);	
		}
		
		
	}
	

	@AfterTest(enabled=true)
	public void tearDown() {
		driver.close();
	}



}