package tests.Homepage.managerHomepageTests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import pageObjects.homePageObjects.managerHomePageObjects;

public class MgrExceptionLeavesPendingActions {
	
	public WebDriver driver;
	
	
	public void validateMgrExceptionPendingAction() {
		
	managerHomePageObjects mgrHPObj=new managerHomePageObjects(driver);
	try {
		int mgrA =mgrHPObj.getAllPendingActionList().size();
		
		
//	}
	//catch(Exception e) {
//		System.out.println("Exception name :"+e.getMessage());
//		
//	}
	catch(NoSuchElementException exception) {
		System.out.println("No such element foung");
	}
	
	}

}
