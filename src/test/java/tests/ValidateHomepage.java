package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;

public class ValidateHomepage {

	public WebDriver driver;
	public HomePageObject homepage;
	
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
	
	/*@AfterTest(enabled=true)
	public void tearDown() {
		driver.close();
	}*/

	
}
