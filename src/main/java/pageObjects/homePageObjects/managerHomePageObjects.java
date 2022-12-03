package pageObjects.homePageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class managerHomePageObjects {
	
	public WebDriver driver;
	Select s ;
	
	public managerHomePageObjects(WebDriver driver) {
		this.driver=driver;
	}
	
	//Pending action
	By leavesPendingAction=By.id("ContentPlaceHolderBody_UserStatus1_lbLeavePend");
	
	
	public WebElement getLeavesPendingAction() {
		return driver.findElement(leavesPendingAction);
	}
	
	

}
