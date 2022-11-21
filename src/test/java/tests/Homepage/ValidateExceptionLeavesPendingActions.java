	package tests.Homepage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import tests.LoginPage;
import utils.DateConversionFormat;

public class ValidateExceptionLeavesPendingActions {
	public WebDriver driver;
	public HomePageObject hp;
	
	@Test
	public void validateMyException() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		hp = new HomePageObject(driver);
		List<WebElement> lists=	hp.getexplist();
		System.out.println("list size :"+lists.size());
		List<WebElement> alllist = hp.getalllist();
		System.out.println("alllist size :"+alllist.size());
		List<WebElement> ualist = hp.getualist();
		System.out.println("ua size :"+ualist.size());
		String myexception = hp.getmyexptext();
		Assert.assertEquals(myexception, "My Exceptions ("+ualist.size()+")","validation");
		WebElement image=	hp.getsadimage();
			if(lists.size()!=0)
			{	
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "sad");
				System.out.println("Sad Image Matched");
			}
			else
			{
				String  imagetext = image.getAttribute("class").split(" ")[0];
				//System.out.println(imagetext);
				Assert.assertEquals(imagetext, "happy");
				System.out.println("happy Image Matched");
			}
		validateDatenText(lists, alllist, ualist);

	}
	 
	
	public void validateDatenText(List<WebElement> lists,List<WebElement> alllist,List<WebElement> ualist)
	{
		List<String> UAlist1 = new ArrayList<>();
		List<String> UAlist2 = new ArrayList<>();
		List<Integer>data1 = new ArrayList<>() ;
		List<Integer>data2 = new ArrayList<>() ;
		DateConversionFormat datecon = new DateConversionFormat();
		int n=1;
		for(int i=0;i<=lists.size()-1;i++)
		{
			String Ualist = lists.get(i).getText().split("2022 ")[1].trim();
			UAlist1.add(Ualist);
			String text = lists.get(i).getText().split(" ")[0];
			if(n<4)
			{
			List<Integer>d1 = datecon.dateFormatConversion2(text);
			data1.addAll(d1);
			n++;
			}
			else
				break;
			
		}
		int k=1;
		for(int j=alllist.size()-1;j>=0;j--) 
		{
			String text1 = alllist.get(j).getText().split(", ")[1];
			//System.out.println("data 2 text : "+text1);
			if(k<4)
			{
				List<Integer>d2 = datecon.dateFormatConversion1(text1);
				data2.addAll(d2);
				k++;
			}
			else
				break;
		}
		
		int l=1;
		for(int j=ualist.size()-1;j>=0;j--)
		{
			if(l<4)
			{
				UAlist2.add(ualist.get(j).getText());
				l++;
			}
			else
				break;
		}

		System.out.println("data 1 :"+data1);
		System.out.println("data 2 :"+data2);
		Assert.assertEquals(data1, data2);
		System.out.println("ua list 1 : "+UAlist1);
		System.out.println("ua list 2 : "+UAlist2);
		Assert.assertEquals(UAlist1, UAlist2);
		System.out.println("both text and date matched");
		
	}

}
