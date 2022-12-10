package pageObjects.myLeavesObjects.myLeavesobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class leaveBalanceObject {

	public WebDriver driver;
	Select s ;
	
	public leaveBalanceObject(WebDriver driver) {
		this.driver=driver;
	}

	
	By leaveBalance=By.cssSelector("#__tab_ContentPlaceHolderBody_TabContainer1_TabPanel2");
	By empName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLBEmployeeName");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLBEmployeeID");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLBRMName");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLBRMID");
	
	By leaveBalanceOptions=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_ddlYear");
	
	//Table reading data
	
	//By empIDTable=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[1]");
	By leaveTypesList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[4]");
	
	
	
	public void getClickOnLeaveBalance() {
		driver.findElement(leaveBalance).click();
		}
	
	public String getEmpName() {
		return driver.findElement(empName).getText();
		
	}
	public String getEmpID() {
		return driver.findElement(empID).getText();
		
	}
	
	public String getManagerName() {
		return driver.findElement(managerName).getText();
		
	}
	public String getManagerID() {
		return driver.findElement(managerID).getText();
		
	}
	
	public List<WebElement> getleaveBalanceOptions(String year) {
		s = new Select(driver.findElement(leaveBalanceOptions));
		List<WebElement> leaveBalanceOption=	s.getOptions();
		s.selectByVisibleText(year);
		return leaveBalanceOption;
	}
	
	public List<WebElement> getTableList(int columeNumber) {
		
		By empIDTable=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td["+columeNumber+"]");
		List<WebElement> empIDTableList=driver.findElements(empIDTable);
//		System.out.println("size 1 :"+empIDTableList.size());
//		System.out.println("data :"+empIDTableList);
		return empIDTableList;
	}
	
	public List<WebElement> getleaveTypesList() {
		List<WebElement>leaveTypeTableList =driver.findElements(leaveTypesList);
		return leaveTypeTableList;
	}
	
	
	

	
}
