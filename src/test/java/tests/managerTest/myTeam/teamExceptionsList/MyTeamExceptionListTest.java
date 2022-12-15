package tests.managerTest.myTeam.teamExceptionsList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

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
import resources.BaseTest;
import tests.LoginTest.LoginPage;
import utils.DateConversionFormat;

public class MyTeamExceptionListTest extends BaseTest{
	public LoginPage lp;
	public WebDriver driver;
	
	public HomePageObject hp;
	
	@BeforeTest
	public void initializer() throws InterruptedException, IOException	{

		lp=new LoginPage();
		driver=lp.validatelogin(); 
	}	
	
	
	@Test
	public void validateMyTeamExceptionList1() throws InterruptedException, IOException {
		
		
		validateMyTeamExceptionList(driver,lp);
	}
	

	public void validateMyTeamExceptionList(WebDriver driver,LoginPage lp) throws InterruptedException, IOException
	{
		Properties prop=getProperties();
		String leaveTypeProp=prop.getProperty("leaveType");
		String categoryProp=prop.getProperty("category");
		String reasonProp=prop.getProperty("reason");
		String remarkProp=prop.getProperty("leaveRemark");
		String leaveTypeForSSProp=prop.getProperty("leaveTypeForSS");
		hp=new HomePageObject(driver);
		List<WebElement> workingDateList=hp.getRedUAWorkingList();
		System.out.println("Working date list size:"+workingDateList.size());
		DateConversionFormat dcf = new DateConversionFormat();
		List<Integer> userWorkingDate = new ArrayList<>();
		List<String> workingDateListtext = new ArrayList<>();
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
		lp.validateManagerLoginWOInitialize();
		System.out.println("Manager Login");
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
			
		for(int j=0;j<teamExpListObj.getapprove().size();j++)
		{
			for(int i=j;i<workingDateListtext.size();i++)
			{
				if(workingDateListtext.get(i).contains("Saturday") || workingDateListtext.get(i).contains("Sunday") ) 
				{
					System.out.println("only saturday or sunday:"+workingDateListtext.get(i));
					teamExpListObj.getTypeList(leaveTypeForSSProp,i);
					Thread.sleep(1500);
					teamExpListObj.getCategoryList(categoryProp, i);
					Thread.sleep(1500);
					teamExpListObj.getReasonList(reasonProp, i);
					Thread.sleep(1500);
					teamExpListObj.getRemark(remarkProp, i);
					Thread.sleep(1500);
					//teamExpListObj.getapprove();
					break;
				}else
				{
					System.out.println("not saturday or sunday:"+workingDateListtext.get(i));
					teamExpListObj.getTypeList(leaveTypeProp,i);
					Thread.sleep(1500);
					teamExpListObj.getCategoryList(categoryProp, i);
					Thread.sleep(1500);
					teamExpListObj.getReasonList(reasonProp, i);
					Thread.sleep(1500);
					teamExpListObj.getRemark(remarkProp, i);
					Thread.sleep(1500);
					//teamExpListObj.getapprove();
					break;
				}
			}
			break;
		}
			
			
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
	
	

