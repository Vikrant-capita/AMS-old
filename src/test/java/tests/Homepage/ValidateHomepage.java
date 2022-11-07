package tests.Homepage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePageObject;
import tests.LoginPage;

public class ValidateHomepage {

	public WebDriver driver;
	public HomePageObject hp;
	public SoftAssert sa;
	
	@Test
	public void homepageElements() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		HomePageObject hp=new HomePageObject(driver);
		String capitaAMS = hp.getCapitaAMS();
		Assert.assertEquals(capitaAMS, "CAPITA AMS");
		System.out.println("CapitaAMS is matched");
		
		String myDetails = hp.getmyDetails();
		Assert.assertEquals(myDetails, "My Leaves");
		System.out.println("myDetails is matched");
		
		String myLeaves=hp.getmyLeaves();
		Assert.assertEquals(myLeaves, "User Manual");
		System.out.println("myLeaves is matched");
		
		String GiftOfLeave=hp.getGiftOfLeave();
		Assert.assertEquals(GiftOfLeave, "Gift Of Leave");
		System.out.println("GiftOfLeave is matched");
	}
	
	@Test(enabled=true)
	public void validateRightPanelText() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		hp=new HomePageObject(driver);
		sa=new SoftAssert();
		Thread.sleep(2000);
		String expiryTimeText= hp.getSessionExpiry();
		//System.out.println("Expiry Time is :"+ expiryTimeText);
		Assert.assertEquals(expiryTimeText, "20", "Default expiry time out sesion matched");
		System.out.println("Default expiry time out sesion : '"+ expiryTimeText +"' is matched");
		//Assert.assertEquals(expiryTimeText, 20, "Default expiry time out sesion matched");
		Thread.sleep(2000);
		String userNameText=hp.getUserNameText1();
		//System.out.println("User Name is :"+ userNameText);
		Assert.assertEquals(userNameText, "Welcome Vikrant Bingi", "Default expiry time out sesion matched");
		System.out.println("User Name : '"+ userNameText +"' is matched");
		Thread.sleep(2000);
		String scheduleName=hp.getscheduleTime();
		//System.out.println(scheduleName);
		Assert.assertEquals(scheduleName, "Schedule : Available till 30-Dec-2023", scheduleName);
		System.out.println("Scheduled name : '"+ scheduleName +"' is matched");
		
	}
	
	
	
	@AfterTest(enabled=true)
	public void tearDown() {
		driver.close();
	}

	
}
