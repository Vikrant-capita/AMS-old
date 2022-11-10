package pageObjects.myDetailsObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyDetailsObject {

	public WebDriver driver;
	public Select s ;
	
	public MyDetailsObject(WebDriver driver) {
		this.driver=driver;
	}
	
	
	By myHolidayList= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt1']");
	
	//Emp and Manager details
	By empName=By.cssSelector("#ContentPlaceHolderBody_LBLEmployeeName");
	By empCode=By.cssSelector("#ContentPlaceHolderBody_LBLEmployeeID");
	By lineManager=By.cssSelector("#ContentPlaceHolderBody_LBLRMName");
	By lmCode=By.cssSelector("#ContentPlaceHolderBody_LBLRMID");
	By year=By.xpath("//*[@id='ContentPlaceHolderBody_DDLHYear']"); 
	
	//Holiday Name
	By yellowHoliday=By.cssSelector("#ContentPlaceHolderBody_lblttt");
	By holidayList=By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td");		//array index out of bound
	
	By holidayListWOYellow=By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td/label");
	
	By holidayListWithYello=By.xpath("//table[@id='ContentPlaceHolderBody_CHKLHolidayList']/tbody/tr/td//span/label");
	
	
	public void getMyHolidayList() {
		driver.findElement(myHolidayList).click();
	}
	
	public String getempName() {
		String employeeName=driver.findElement(empName).getText();
		return employeeName;
	}
	
	public void getEmpCode() {
		driver.findElement(empCode).getText();
	}
	
	public String getlineManager() {
		String LM=driver.findElement(lineManager).getText();
		return LM;
	}
	
	public String getlmCode() {
		String LMCode=driver.findElement(lmCode).getText();
		return LMCode;
	}
	
	public void getYear(String yr)
	{
		s = new Select(driver.findElement(year));
		s.selectByVisibleText(yr);
	}
	
	public String getYellowHoliday() {
		String yelloHoliday=driver.findElement(yellowHoliday).getText();
		return yelloHoliday;
	}
	
	public List<WebElement> getHolidayList() {
		List<WebElement> holidayList1=driver.findElements(holidayList);
		return holidayList1;
	}
	
	public List<WebElement> getholidayListWOYellow() {
		List<WebElement> holidayListWOYellow1=driver.findElements(holidayListWOYellow);
		return holidayListWOYellow1;
	}
	
	public List<WebElement> getholidayListWithYellow() {
		List<WebElement> holidayListWithYello1=driver.findElements(holidayListWithYello);
		return holidayListWithYello1;
	}
}
