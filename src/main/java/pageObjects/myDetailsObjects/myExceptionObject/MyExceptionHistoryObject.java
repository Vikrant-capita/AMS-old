package pageObjects.myDetailsObjects.myExceptionObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MyExceptionHistoryObject {
	
	public WebDriver driver;
	public Select s ;
	
	public MyExceptionHistoryObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By clickOnMyExceptions= By.xpath("//td[@class='groovybutton TreeMenu1_MenuTreeView_2 TreeMenu1_MenuTreeView_5']/a[@id='TreeMenu1_MenuTreeViewt2']");
	
	By clickOnMyExcepHist=By.xpath("//div[@id='ContentPlaceHolderBody_TabContainer1_header']/span[2]/span/span/span");
	
	By empName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLEmployeeName1");
	By empID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLEmployeeID1");
	By managerName=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLRMName1");
	By managerID=By.id("ContentPlaceHolderBody_TabContainer1_TabPanel2_LBLRMID1");
	
	By workingDateList=By.xpath("//table[@id='ContentPlaceHolderBody_TabContainer1_TabPanel2_GridviewExceptionHistry']/tbody/tr/preceding-sibling::tr[1]/td[1]/span");
	
	
	public void getClickOnMyExceptions() {
		driver.findElement(clickOnMyExceptions).click();
	}
	public void getClickOnMyExcepHist() {
		driver.findElement(clickOnMyExcepHist).click();
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
	
	
	
	
}
