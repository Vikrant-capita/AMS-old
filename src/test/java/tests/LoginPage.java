package tests;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import pageObjects.LoginPageObject;
import resources.Base;

public class LoginPage extends Base{
	
	public WebDriver driver;
	public LoginPageObject loginpg;
	public SoftAssert sa;
/*
	@BeforeTest
	@Parameters({"browser","url"})
	public void initializer(String browser, String url) {
		if (browser.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get(url);
		    driver.manage().window().maximize();
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
			
		}
		}
		*/
	@Test
	public void validatelogin() throws InterruptedException, IOException {
		driver = initializeDriver();
		//System.out.println("login driver : "+driver);
	    loginpg=new LoginPageObject(driver);
		loginpg.getuser("P50096390@capitaindia.onmicrosoft.com");
		//Thread.sleep(2000);
		loginpg.getclickbtn();
		//Thread.sleep(2000);
		loginpg.getpass("Admin$2145");
		Thread.sleep(2000);
		loginpg.getpassnextbtn();
		Thread.sleep(2000);
		loginpg.getsignInButton();
		String loginTittle=driver.getTitle();
		
		//SoftAssert sa=new SoftAssert();
		Assert.assertEquals(loginTittle, "Attendance Management System.");
		System.out.println("Login Tittle Matched");	
		
		}
	
	/*
	
	@Test(dependsOnMethods= {"validatelogin"},enabled=true)
	public void validateLogout() throws InterruptedException {
		
		loginpg=new LoginPageObject(driver);
		Thread.sleep(2000);
		loginpg.getsignOutBtn();
		Thread.sleep(3000);
		String text=loginpg.getsignOutTittle();
		System.out.println(text);
		Assert.assertEquals(text, "You have been signed out");
		
	}
*/

	
	@Test(dependsOnMethods= {"validatelogin"},enabled=true)
	public void validateAutoLogout() throws InterruptedException {
		
		
		
		loginpg=new LoginPageObject(driver);
		Instant start = Instant.now();
		System.out.println("Start time :"+start);
		
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(1210));
		// WebDriverWait webDriverWait = new WebDriverWait(driver, TimeUnit.toSeconds(60));
		wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//span[@id='lblSessionTime']")), "00"));
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken: "+ timeElapsed.toMillis() +" milliseconds");
		Thread.sleep(2000);
		//driver.switchTo().alert().accept();
		
		
		
		
		
		
		//loginpg.getsignOutBtn();
		//Thread.sleep(3000);
//		
//		String text=loginpg.getsignOutTittle();
//		System.out.println(text);
//		Assert.assertEquals(text, "You have been signed out");
		
		/*
		String logoutTittle=driver.getTitle();
		System.out.println("Logout tittle :"+logoutTittle+":");
		Thread.sleep(8000);
		sa.assertEquals(logoutTittle, "You have been signed out");
		// You have been signed out
		
		System.out.println("Logout Tittle Matched");
		*/	
	}
	
	@AfterTest(enabled=false)
	public void tearDown() {
		driver.close();
	}
		
	
	/*	@DataProvider
	public Object[][] getdata(){
		Object[][] data=new Object[1][2];
		data[0][0]="P50096390@capitaindia.onmicrosoft.com";
		data[0][1]="Admin$2145";
		
//		data[1][0]="invalid@capitaindia.onmicrosoft.com";
//		data[1][1]="invalid";
		
		return data;
		
	}*/
//	SimpleDateFormat sdf = new  SimpleDateFormat("HH.mm.ss");
//	Date dt= new Date();
	
	//System.out.println("time : "+formatter.format(dt));
	
}
