package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
	
	
	@Test(dependsOnMethods= {"validatelogin"},enabled=false)
	public void validateLogout() throws InterruptedException {
		
		loginpg=new LoginPageObject(driver);
		Thread.sleep(4000);
		loginpg.getsignOutBtn();
		Thread.sleep(5000);
		String text=loginpg.getsignOutTittle();
		System.out.println(text);
		sa.assertEquals(text, "You have been signed out");
		
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
	
}
