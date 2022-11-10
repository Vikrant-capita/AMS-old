package pageObjects.myLeavesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class myLeavesObject {


	public WebDriver driver;
	Select s ;
	public myLeavesObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myLeaves=By.cssSelector("#TreeMenu1_MenuTreeViewt5");
	By leavePlan =By.cssSelector("#__tab_ContentPlaceHolderBody_TabContainer1_TabPanel3");
	By employeeName=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPEmployeeName");
	
	
	public void getMyLeaves() {
		driver.findElement(myLeaves);
	}
	
	public void getLeavePlan() {
		driver.findElement(leavePlan).click();
	}
	
	public String getEmployeeName() {
		String empName=driver.findElement(employeeName).getText();
		return empName;
	}
}
