package tests.mainMyLeaves.myLeaves;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.myLeavesObjects.myLeavesObject;
import tests.LoginPage;
import tests.Homepage.ValidateHomepage;

public class LeavePlan {
	
	public WebDriver driver;
	
	@Test
	public void validateMyLeaves() throws InterruptedException, IOException {
	myLeavesObject ml=new myLeavesObject(driver);
	LoginPage lp=new LoginPage();
	lp.validatelogin();
	driver = lp.driver;
	ml.getMyLeaves();
	ml.getLeavePlan();
	String empName=ml.getEmployeeName();
	ValidateHomepage hp=new ValidateHomepage();
	String username=hp.userNameText;
	Assert.assertEquals(empName, username);
	

	
	
}
}

