package pageObjects.myLeavesObjects.myCalendarObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyCalendarObject {

	public WebDriver driver;
	
	public MyCalendarObject(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By clickOnMyCalendar=By.xpath("//div[@id='TreeMenu1_MenuTreeViewn4Nodes']/table[3]/tbody/tr[2]/td[4]");
	By empID=By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/caption");
	By calendarDateList=By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/following-sibling::tr[2]/td/a[1]");
	//Tittle= October 31		getText=31
	//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/following-sibling::tr[2]
	By calendarStatusList1=By.xpath("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/following-sibling::tr[2]/td/a[2]");
	
	//Homepage Elements			
	By workingDateList1=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[1]");
			//Wednesday   ,  09 Nov 2022
	By homePageAttendanceStatus=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[4]");
	//WFH  WO  UA
	
	By monthCaleder = By.id("//table[@id='ContentPlaceHolderBody_ScheduleCalender1_ScheduleCalendar']/tbody/tr/td/table/tbody/tr/td[2]");
	//Current Month Name 
	By clickOnPreviousMonth = By.xpath("//a[@title='Go to the previous month']");
	// click on oct month
	
	public void getClickOnMyCalendar() {
		driver.findElement(clickOnMyCalendar).click();;
	}
	
	public String  getEmpID() {
		return driver.findElement(empID).getText();
	}
	
	public List<WebElement> getCalendarDateList() {
		List<WebElement> calendarDateAndMonthList=driver.findElements(calendarDateList);
		return calendarDateAndMonthList;
	}
	
	public List<WebElement> getCalendarStatusList() {
		List<WebElement> calendarStatusList=driver.findElements(calendarStatusList1);
		return calendarStatusList;
	}
	
	public List<WebElement> getWorkingDateList() {
		List<WebElement> workingDateList=driver.findElements(workingDateList1);
		return workingDateList;
	}
	
	public List<WebElement> getAttendanceStatus() {
		List<WebElement> attendantStatus=driver.findElements(homePageAttendanceStatus);
		return attendantStatus;
	}
	
	public String getMonthCalender()
	{
		return driver.findElement(monthCaleder).getText();
	}

	public void getPrevMonthclick()
	{
		driver.findElement(clickOnPreviousMonth).click();;
	}
}
