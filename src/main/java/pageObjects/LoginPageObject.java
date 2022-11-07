package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageObject {
	
	
	public WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver=driver;
			
	}
	
	By user=By.name("loginfmt");
	By userNextButton=By.xpath("//input[@id='idSIButton9']");
	By pass=By.xpath("//input[@name='passwd']");
	By passNextButton=By.xpath("//input[@type='submit']");
	By signInButton=By.xpath("//*[@id='idBtn_Back']");
	
	
	By signOutBtn=By.cssSelector("#btnSignOutMaster");
	By signoutTittle=By.cssSelector(".message:nth-child(1)");

	
	public void getuser(String username) {
		driver.findElement(user).sendKeys(username);
	}
	
	public void getclickbtn() {
		driver.findElement(userNextButton).click();;
	}
	
	public void getpass(String password) {
		driver.findElement(pass).sendKeys(password);
	}
	
	public void getpassnextbtn() {
		driver.findElement(passNextButton).click();
	}
	
	public void getsignInButton() {
		driver.findElement(signInButton).click();
	}
	
	public void getsignOutBtn() {
		driver.findElement(signOutBtn).click();
	}
	
	public String getsignOutTittle() {
		String signOutText=driver.findElement(signoutTittle).getText();
		return signOutText;
	}
	
	
	
	
}


