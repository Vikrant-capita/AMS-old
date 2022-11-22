package tests.userMannual;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pageObjects.userManualObjects.AMSFAQsObject;
import tests.LoginTest.LoginPage;

public class AMSFAQs {

	public WebDriver driver;
	
	@Test
	public void validateAMSFAQs() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		Thread.sleep(2000);
		System.out.println("page tittle before click :"+ driver.getTitle());
		
		
		AMSFAQsObject AMSFAQs=new AMSFAQsObject(driver);
		AMSFAQs.getClickOnAMSFAQ();
		System.out.println("page tittle after click :"+ driver.getTitle());
	}
	
	
}

//====================================After click page tittle not coming================