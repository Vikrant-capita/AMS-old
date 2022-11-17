package tests.userMannual;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.userManualObjects.AMSManualObject;
import tests.LoginPage;

public class AMSManual {

	public WebDriver driver;
	
	
	@Test
	public void ValidateAMSManualDownload() throws InterruptedException, IOException {

		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
				
		String downloadPath = "C:\\Users\\P50096390\\Downloads\\"; 
		
		AMSManualObject AMS= new AMSManualObject(driver);
		AMS.getClickOnAMSManual();
		Thread.sleep(8000);
				
		Assert.assertTrue(isFileDownloaded(downloadPath, "AMS_1.xps"), "Failed to download Expected document");
				
}
	
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
	    File dir = new File(downloadPath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++) {
	        if (dir_contents[i].getName().equals(fileName))
	            return flag=true;
	            }

	    return flag;
	}
	
	
	
}
