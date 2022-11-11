package pageObjects.myLeavesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyLeaveObject {
 
	public WebDriver driver;
	
	public MyLeaveObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myLeave =By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel1");

	
	public void getMyLeave() {
		driver.findElement(myLeave).click();
		}
	
	
}
