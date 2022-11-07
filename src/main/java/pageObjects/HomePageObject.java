package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class HomePageObject {

	public WebDriver driver;
	Select s ;
	public HomePageObject(WebDriver driver) {
		this.driver=driver;
	}
	//Left side of Homepage
	By capitaAMS= By.xpath("//div[@id='logo']//a");
	By mydetails= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt0']");
	By myLeaves= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt4']");
	By userManual= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt9']");
	By giftOfLeave= By.xpath("//a[@id='TreeMenu1_MenuTreeViewt12']");
	
	//Righ-Top of Homepage
	
	By sessionExpiryTime= By.xpath("//table/tbody/tr/td[1]/span[@id='lblSessionTime']");  
	By loggedUserName =By.xpath("//table/tbody/tr/td[2]/span[@id='search_text']");			
	//By scheduleTime=By.xpath("//span[@id='ContentPlaceHolderTitle_Lbltopmsg1']");
	
	
	//Right-Top Corner
	By sessionExpiry=By.xpath("//table[@class='tablewidth']/tbody/tr/td/span[@id='lblSessionTime']");
	By userNameText1= By.xpath("//table[@class='tablewidth']/tbody/tr/td[2]/span[@id='search_text']");
	//By userNameText=By.xpath("//table[@class='tablewidth']/tbody/tr/td[2]/span[@id='search_text']");
	By scheduleTime=By.xpath("//span[@id='ContentPlaceHolderTitle_Lbltopmsg1']");
	
	
	//left side links
	By mainMenuLink =By.xpath("//div[@id='TreeMenu1_MenuTreeView']//td//a[@class='TreeMenu1_MenuTreeView_0 groovybutton TreeMenu1_MenuTreeView_1']");
	By activeAttendance=By.xpath("//table[@id='ContentPlaceHolderBody_UserStatus1_TStatus_CurrentPayroll_GridViewMyStatus']/tbody/tr/td[4]/a[@style='color:Red;font-weight:bold;']");
			
	//ACtive Attendance tab
	By activeAttendanceOpt= By.xpath("//a[@style='color:Red;font-weight:bold;']");
	By leaveType = By.id("ContentPlaceHolderBody_UserStatus1_DDLType");
	By categoryOpt = By.id("ContentPlaceHolderBody_UserStatus1_ddlCategories"); 
	By reasonOpt = By.id("ContentPlaceHolderBody_UserStatus1_DDLReason");
	
	
	//Historical Attendance
	By histAttenanceOpt=By.xpath("//*[contains(text(),'Historical Attendance')]");
	
	
	
	
	public String getCapitaAMS() {
		String capitaAMSText=driver.findElement(capitaAMS).getText();
		return capitaAMSText;
	}
	
	public String getmyDetails() {
		String mydetailsText=driver.findElement(mydetails).getText();
		return mydetailsText;
	}
	
	public String getmyLeaves() {
		String myLeavesText=driver.findElement(myLeaves).getText();
		return myLeavesText;
	}
	
	public String getUserManual() {
		String userManualText=driver.findElement(userManual).getText();
		return userManualText;
	}
	
	public String getGiftOfLeave() {
		String giftOfText=driver.findElement(giftOfLeave).getText();
		return giftOfText;
	}
	
	
	public List<WebElement> getMainMenuLink() {
		List<WebElement> menudriver=driver.findElements(mainMenuLink);
		return menudriver;
	}
	
	public List<WebElement> getActiveAttendance() {
		List<WebElement> activeAttendancedriver=driver.findElements(activeAttendance);
		return activeAttendancedriver;
	}
	public String getSessionExpiry() {
		String sessionExpiryText=driver.findElement(sessionExpiry).getText();
		return sessionExpiryText;
	}
	
	public String getUserNameText1() {
		String userNameText=driver.findElement(userNameText1).getText();
		return userNameText;
	}
	
	public String getscheduleTime() {
		String scheduleText=driver.findElement(scheduleTime).getText();
		return scheduleText;
		
	}
	public WebElement getleaveType(String option) {
		WebElement leavetype = driver.findElement(leaveType);
	  s = new Select(leavetype);
		s.selectByVisibleText(option);
		return null;
		
	}
	public void getAtiveAttendanceOpt()
	{
		driver.findElement(activeAttendanceOpt).click();
	}
	public void getCategoryOption(String opt)
	{
		s = new Select(driver.findElement(categoryOpt));
		s.selectByVisibleText(opt);
	}
	public void getReasonOpt(String opt)
	{
		s = new Select(driver.findElement(reasonOpt));
		s.selectByVisibleText(opt);
	}
	
	public void getHistoryAttendance() {
		driver.findElement(histAttenanceOpt).click();
	}
}
