package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchronization {
	
	public WebDriver driver;
	
	public Synchronization(WebDriver driver) {
		this.driver=driver;
	}
	
    public void visibilityOfElement() {

    	WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(30));

    	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
    }
	
	
	
	
}
