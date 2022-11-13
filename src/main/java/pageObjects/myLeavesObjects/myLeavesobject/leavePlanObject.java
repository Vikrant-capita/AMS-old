package pageObjects.myLeavesObjects.myLeavesobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class leavePlanObject {


	public WebDriver driver;
	Select s ;
	
	public leavePlanObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myLeaves=By.cssSelector("#TreeMenu1_MenuTreeViewt5");
	By leavePlan =By.cssSelector("#__tab_ContentPlaceHolderBody_TabContainer1_TabPanel3");
	By employeeName=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPEmployeeName");
	By empID =By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPEmployeeID");
	By managerName=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPRMName");
	By managerID=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_LBLPRMID");
	
	//Calendor
	By fromDate=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate");
	By monthYear1=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_title");
	By clickOnRightArrow1=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_nextArrow");
	By dateList1=By.xpath("//tbody[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTFromLeaveDate_CalendarExtender_daysBody']/tr/td/div");
	
	By toDate=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate");
	By monthYear2=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_title");
	By clickOnRightArrow2=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_nextArrow");
	By dateList2 =By.xpath("//tbody[@id='ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTToLeaveDate_CalendarExtender_daysBody']/tr/td/div");
		
	//
	By leaveType=By.cssSelector("#ContentPlaceHolderBody_TabContainer1_TabPanel3_DDLLeavePlanType");
	By category=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_ddlCategories");
	By remark=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel3_TXTRemarks");
	By monthDisplay=By.xpath("//table[@title='Scheduled ']/tbody/tr/td/table/tbody/tr/td[2]");
	
	
	public void getMyLeaves() {
		driver.findElement(myLeaves).click();
	}
	
	public void getLeavePlan() {
		driver.findElement(leavePlan).click();
	}
	
	public String getEmployeeName() {
		String empName=driver.findElement(employeeName).getText();
		return empName;
	}
	
	public String getEmpID() {
		String EmpID=driver.findElement(empID).getText();
		return EmpID;
	}
	
	public String getmanagerName() {
		String ManagerName=driver.findElement(managerName).getText();
		return ManagerName;
	}
	public String getmanagerID() {
		String ManagerID=driver.findElement(managerID).getText();
		return ManagerID;
	}
	
	public void getFromDateClick() {
		driver.findElement(fromDate).click();
		
	}
	
	public String getMonthYear1() {
		return driver.findElement(monthYear1).getText();
	}
	
	public WebElement getclickOnRightArrow1() {
		return driver.findElement(clickOnRightArrow1);
	}
	
	public List<WebElement> getDateList() {
		List<WebElement> allDates=driver.findElements(dateList1);
		return allDates;
	}
	
	public void getToDateClick() {
		driver.findElement(toDate).click();
		
	}
	
	
	public String getMonthYear2() {
		return driver.findElement(monthYear2).getText();
	}
	
	
	public WebElement getclickOnRightArrow2() {
		return driver.findElement(clickOnRightArrow2);
	}
	
	public List<WebElement> getDateList2() {
		List<WebElement> allDates=driver.findElements(dateList2);
		return allDates;
	}
	
	public void getLeaveType(String ltype) {
		s = new Select(driver.findElement(leaveType));
		s.selectByVisibleText(ltype);
	}
	
	public void getCategory(String cat) {
		s = new Select(driver.findElement(category));
		s.selectByVisibleText(cat);
	}
	
	public void getRemark(String rm) {
		driver.findElement(remark).sendKeys(rm);
	}
	
	public String getMonthDisplay() {
		return driver.findElement(monthDisplay).getText();
	}
	
}
