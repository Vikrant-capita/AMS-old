package pageObjects.myDetailsObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MyExceptionsObject {
	
	public WebDriver driver;
	public Select s ;
	
	public MyExceptionsObject(WebDriver driver) {
		this.driver=driver;
	}
	
	By myExceptions= By.cssSelector("#TreeMenu1_MenuTreeViewt2");
	
	
	public void getMyExceptions() {
		driver.findElement(myExceptions).click();;
	}

}
