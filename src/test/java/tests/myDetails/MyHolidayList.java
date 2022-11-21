package tests.myDetails;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.myDetailsObjects.myHolidayListObjects.MyDetailsObject;
import tests.LoginPage;
import utils.DateConversionFormat;

public class MyHolidayList {
	public WebDriver driver;
	public SoftAssert sa;
	public DateConversionFormat df;
	
	@Test
	public void myHolidayList() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		MyDetailsObject md=new MyDetailsObject(driver);
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
	
	
	@Test//(dependsOnMethods= {"amstest"})
	public void  validateMyHolidayList() throws InterruptedException
	{
	//driver.findElement(By.id("TreeMenu1_MenuTreeViewt1")).click();
	Thread.sleep(2000);
	//List<WebElement> lists = driver.findElements(By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label"));
	//System.out.println("size: "+lists.size());
	MyDetailsObject md=new MyDetailsObject(driver);
	List<WebElement> holidayListWOYellow= md.getholidayListWOYellow();
	for(WebElement list: holidayListWOYellow)
	{
	String text = list.getText();
	String schedule = text.split("- ")[1];
	//System.out.println(schedule);
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

	
	

	@AfterTest(enabled=true)
	public void tearDown() {
		driver.close();
	}



}