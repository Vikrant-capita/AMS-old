package pageObjects.myLeavesObjects.myLeavesobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class leaveCancelObject {

	public WebDriver driver;
	Select s ;
	
	public leaveCancelObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By leaveChannel=By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel4");
	
	By empName =By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label1");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label2");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label3");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel4_Label4");
	
	
	public void getLeaveChannel() {
		driver.findElement(leaveChannel).click();
	}

	
	public String getEmployeeName() {
		return driver.findElement(empName).getText();
		
	}
	public String getEmpID() {
		return driver.findElement(empID).getText();
		
	}
	
	public String getmanagerName() {
		return driver.findElement(managerName).getText();
		
	}
	public String getmanagerID() {
		return driver.findElement(managerID).getText();
		
	}
	
	
}
