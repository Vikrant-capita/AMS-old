package pageObjects.myLeavesObjects.myLeavesobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyLeaveObject {
 
	public WebDriver driver;
	public Select s;

	
	public MyLeaveObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myLeave =By.id("__tab_ContentPlaceHolderBody_TabContainer1_TabPanel1");
	By empName= By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeName");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLEmployeeID");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMName");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_LBLRMID");
	By leaveType=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_DDLAbsenceLeaveType");
	By workingDateList= By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/td[5]");
	//By workingDateListY21= By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/td[5][contains(text(),'2021')]");
	By clickOnLeaveBalance = By.xpath("//span[contains(text(),'Leave Balance')]");
	By leaveBalanceYear = By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_ddlYear");
	By status=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/td[6]");
	By clickonNextPage = By.cssSelector("table[id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences'] tbody tr:last-child td a");
	
	public void getMyLeave() {
		driver.findElement(myLeave).click();
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
	
	public List<WebElement> getLeaveType(String ltype) throws InterruptedException {
		s = new Select(driver.findElement(leaveType));
		List<WebElement> leaveTypesOptions=	s.getOptions();
		s.selectByVisibleText(ltype);
		return leaveTypesOptions;
	}
	
	public List<String> getWorkingDateList()
	{
		List<WebElement> dateList =  driver.findElements(workingDateList);
		List<String> workingDateListText=new ArrayList<>();
		for(WebElement list:dateList)
		{
			workingDateListText.add(list.getText());
		}
		return workingDateListText;
	}
	
	
	public void getClickOnLeaveBalance()
	{
		driver.findElement(clickOnLeaveBalance).click();
	}
	public void getClickLeaveBalanceYear(String selectyr)
	{
		if(selectyr.contains("currentYear"))
		{
			WebElement year=driver.findElement(leaveBalanceYear);
			s = new Select(year);
			s.selectByIndex(1);
			System.out.println("current year selected");
		}
		if(selectyr.contains("previousYear"))
		{
			WebElement year=driver.findElement(leaveBalanceYear);
			s = new Select(year);
			s.selectByIndex(0);
			System.out.println("previous year selected");
		}
		if(selectyr.contains("nextYear"))
		{
			WebElement year=driver.findElement(leaveBalanceYear);
			s = new Select(year);
			s.selectByIndex(2);
			System.out.println("next year selected");
		}
	}
	
	public int getLeaveTypeIndex(String leaveTypeText,String columnNameforValue)
	{
		int indexvalue = 0;
		if(columnNameforValue.contains("waiting"))
		{
			By leaveTypeindex=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[text()='"+leaveTypeText+"']/following-sibling::td[5]");
			indexvalue=Integer.parseInt(driver.findElement(leaveTypeindex).getText());
			return indexvalue;
		}
		if(columnNameforValue.contains("availed"))
		{
			By leaveTypeindex=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[text()='"+leaveTypeText+"']/following-sibling::td[4]");
			indexvalue=Integer.parseInt(driver.findElement(leaveTypeindex).getText());
			return indexvalue;
		}
		
		if(columnNameforValue.contains("Carry Forward"))
		{
			By leaveTypeindex=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[text()='"+leaveTypeText+"']/following-sibling::td[1]");
			indexvalue=Integer.parseInt(driver.findElement(leaveTypeindex).getText());
			return indexvalue;
		}
		
		if(columnNameforValue.contains("Balance"))
		{
			By leaveTypeindex=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[text()='"+leaveTypeText+"']/following-sibling::td[6]");
			indexvalue=Integer.parseInt(driver.findElement(leaveTypeindex).getText());
			return indexvalue;
		}
		
		System.out.println("value: "+indexvalue);
		return indexvalue;
		
		
	}
	
	public List<String>  getStatus()
	{
		List<WebElement> statuslist=driver.findElements(status);
		List<String> list= new ArrayList<>();
		for(WebElement status:statuslist)
		{
			list.add(status.getText());
			
		}
		return list;
	}
	
	public List<WebElement> getClickOnNextPage()
	{
		WebElement footertable= driver.findElement(By.id("ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences"));
		List<WebElement> nextpage=footertable.findElements(clickonNextPage);
		return nextpage;
	}
}
