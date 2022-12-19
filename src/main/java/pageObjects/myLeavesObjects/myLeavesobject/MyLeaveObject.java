package pageObjects.myLeavesObjects.myLeavesobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
	By workingDateListY22= By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/td[5][contains(text(),'2022')]");
	By workingDateListY21= By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/td[5][contains(text(),'2021')]");
	By clickOnLeaveBalance = By.xpath("//span[contains(text(),'Leave Balance')]");
	By leaveBalanceYear = By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_ddlYear");
	By status=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences']/tbody/tr/td[6]");
	By clickonPage2 = By.cssSelector("table[id='ContentPlaceHolderBody_TabContainer1_TabPanel1_GridAbsences'] tbody tr:last-child td a");
	
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
	
	public List<WebElement> getLeaveType(String ltype) {
		s = new Select(driver.findElement(leaveType));
		List<WebElement> leaveTypesOptions=	s.getOptions();
		s.selectByVisibleText(ltype);
		return leaveTypesOptions;
	}
	
	public List<String> getWorkingDateListY22()
	{
		List<WebElement> dateList =  driver.findElements(workingDateListY22);
		List<String> workingDateListText=new ArrayList<>();
		for(WebElement list:dateList)
		{
			workingDateListText.add(list.getText());
		}
		return workingDateListText;
	}
	
	public List<String> getWorkingDateListY21()
	{
		List<WebElement> dateList =  driver.findElements(workingDateListY21);
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
		WebElement year=driver.findElement(leaveBalanceYear);
		s = new Select(year);
		s.selectByValue(selectyr);
	}
	
	public int getLeaveTypeIndex(String leaveTypeText,String columnNameforValue)
	{
		int indexvalue = 0;
		//System.out.println("columnname: "+columnNameforValue);
		if(columnNameforValue.contains("waiting"))
		{
			By leaveTypeindex=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[text()='"+leaveTypeText+"']/following-sibling::td[5]");
			indexvalue=Integer.parseInt(driver.findElement(leaveTypeindex).getText());
			//System.out.println("inside waiting if value: "+indexvalue);
			return indexvalue;
		}
		if(columnNameforValue.contains("availed"))
		{
			By leaveTypeindex=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridLeaveBalance']/tbody/tr/td[text()='"+leaveTypeText+"']/following-sibling::td[4]");
			indexvalue=Integer.parseInt(driver.findElement(leaveTypeindex).getText());
			//System.out.println("inside availed if value: "+indexvalue);
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
	
	public void getClickonPage2()
	{
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(clickonPage2)).click();;
	}
}
