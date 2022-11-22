package tests.myDetails.myException;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myDetailsObjects.myExceptionObject.MyExceptionHistoryObject;
import tests.LoginTest.LoginPage;
import utils.UserManagerDetailsValidation;
import utils.excelDriven;

public class MyExceptionHistory  {
	
	public WebDriver driver;

	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
	}
	
	@Test
	public void validateMyExcepHist() throws IOException, InterruptedException {
		MyExceptionHistoryObject myExHist=new MyExceptionHistoryObject(driver);
		
		myExHist.getClickOnMyExceptions();
		Thread.sleep(2000);
		myExHist.getClickOnMyExcepHist();
		
		UserManagerDetailsValidation usermangr=new UserManagerDetailsValidation(driver);
		usermangr.usersManagerDetailsValidation(myExHist);
		
	}
	
	
}
