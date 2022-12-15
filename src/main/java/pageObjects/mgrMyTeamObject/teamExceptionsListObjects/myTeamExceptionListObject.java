package pageObjects.mgrMyTeamObject.teamExceptionsListObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class myTeamExceptionListObject {
	
	public WebDriver driver;
	
	public myTeamExceptionListObject(WebDriver driver)
	{
		this.driver=driver;
	}
	
	By clickOnTeamExpList = By.xpath("//a[text()='Team Exceptions List']");
	By teamMemberName = By.xpath("//table[@id='ContentPlaceHolderBody_CHKLEmployeeName']/tbody/tr/td/label");
	By count = By.id("ContentPlaceHolderBody_lblAbsent");
	By workingDateList = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[1]/span");
	By employeeNameList = By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[2]/span");
	By typeOptions= By.xpath("//table[@id='ContentPlaceHolderBody_GridLeave']/tbody/tr/td[4]/select");
	
	public void getClickOnteamExpList()
	{
		driver.findElement(clickOnTeamExpList).click();
	}
	
	public void getTeamMemberName(String memberName)
	{
		List<WebElement> lists = driver.findElements(teamMemberName);
		for(WebElement list : lists)
		{
			String text =list.getText();
			if(text.contains(memberName))
			{
				System.out.println("member :"+text);
				list.click();
				break;
			}
		}
		
	}
	
	public int getCount()
	{
				
		return	 Integer.parseInt(driver.findElement(count).getText());
	}
	
	public List<String> getWorkingDateList()
	{
		List<String> allworkingDateList = new ArrayList<>();
		List<WebElement> dateList =driver.findElements(workingDateList);
		for(WebElement date:dateList)
		{
			//System.out.println("working dates:"+date.getText());
			allworkingDateList.add(date.getText());
		}
		return allworkingDateList;
	
	}
	
	public List<String> getEmployeeList()
	{
		List<String> allEmpList = null;
		List<WebElement> empList =driver.findElements(employeeNameList);
		for(WebElement emp:empList)
		{
			allEmpList.add(emp.getText());
		}
		return allEmpList;
	
	}
	
	public void getTypeList(String selectType, int i)
	{
		List<WebElement> types = driver.findElements(typeOptions);
		for(int j=i;j<types.size();j++)
		{
			Select Types1 = new Select(types.get(j));
			Types1.selectByVisibleText(selectType);	
			break;
		}
			
	}
	

}
