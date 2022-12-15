package tests.managerTest.myTeam.teamExceptionsList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.mgrMyTeamObject.teamExceptionsListObjects.myTeamExceptionListObject;
import tests.LoginTest.LoginPage;
import utils.DateConversionFormat;

public class MyTeamExceptionListTest {
	public LoginPage lp;
	public WebDriver driver;
	public List<String> workingDateListtext = new ArrayList<>();
	public HomePageObject hp;
	
	@BeforeTest
	public void initializer() throws InterruptedException, IOException	{

		lp=new LoginPage();
		driver=lp.validatelogin();
	}	
	
	
	@Test
	public void validateMyTeamExceptionList1() throws InterruptedException, IOException {
		
		
		validateMyTeamExceptionList(driver);
	}
	

	public void validateMyTeamExceptionList(WebDriver driver) throws InterruptedException, IOException
	{
		lp = new LoginPage();
		hp=new HomePageObject(driver);
		List<WebElement> workingDateList=hp.getRedUAWorkingList();
		System.out.println("Working date list size:"+workingDateList.size());
		DateConversionFormat dcf = new DateConversionFormat();
		List<Integer> userWorkingDate = new ArrayList<>();
		for(WebElement list:workingDateList)
		{
			Thread.sleep(2000);
			System.out.println("List:"+list.getText());
			String text = list.getText().split(" , ")[1];
			workingDateListtext.add(list.getText());
			userWorkingDate.addAll(dcf.dateFormatConversion1(text));
		}
		lp.validateLogout();
		System.out.println("User Logout");
		System.out.println("Manager Login");
		lp.validateManagerLoginWOInitialize();
		myTeamExceptionListObject teamExpListObj = new myTeamExceptionListObject(driver);
		teamExpListObj.getClickOnteamExpList();
		//Thread.sleep(1500);
		teamExpListObj.getTeamMemberName("Vikrant Bingi");
		Thread.sleep(3000);
		int count =teamExpListObj.getCount();
		System.out.println("count : "+count);
		List<Integer> manWorkingDates = new ArrayList<>();
		if(count>0)
		{
			List<String> allworkingDateList = teamExpListObj.getWorkingDateList();
			for(String dates:allworkingDateList)
			{
				manWorkingDates.addAll(dcf.dateFormatConversion2(dates));
			}
			Collections.sort(userWorkingDate);
			Collections.sort(manWorkingDates);
			Assert.assertEquals(manWorkingDates, userWorkingDate);
			
			for(int i=0;i<workingDateListtext.size();i++)
			{
				if(workingDateListtext.get(i).contains("Saturday") || workingDateListtext.get(i).contains("Sunday") ) 
				{
					System.out.println("only saturday or sunday:"+workingDateListtext.get(i));
					teamExpListObj.getTypeList("WO",i);
					break;
				}else
				{
					System.out.println("not saturday or sunday:"+workingDateListtext.get(i));
					teamExpListObj.getTypeList("Work From Home(WFH)",i);
					break;
				}
			}
		}
			
			
		}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
	
	

