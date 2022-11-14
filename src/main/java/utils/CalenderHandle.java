package utils;

import java.util.List;

import org.openqa.selenium.WebElement;

public class CalenderHandle {


	public String getCalendor(String month, String year, String date, String[] monthAndDate, WebElement clickOnArrow, List<WebElement> allDateList) throws InterruptedException {
		//ml=new myLeavesObject(driver);
		String expectedmonthy="";
		System.out.println("month :"+ month);
		while(!(monthAndDate[0].contains(month)&& monthAndDate[1].contains(year))){
			//Thread.sleep(1000);
			clickOnArrow.click();
			System.out.println("month inside while :"+month);
			//ml.getclickOnRightArrow();
			//String expectedmonthyr=ml.getMonthYear2();
			//System.out.println("Expected month and year :"+expectedmonthyr);
			break;			
		}
		//List<WebElement> allDateList=ml.getDateList();
		for(WebElement singleDate:allDateList){
			String text=singleDate.getText();
			System.out.println("date selected :"+text);
			if(text.equalsIgnoreCase(date)) {
				System.out.println("date selected inside if :"+text);
				Thread.sleep(1000);
				singleDate.click();
				break;
			}
		}
		//return expectedmonthyr;
		return expectedmonthy;
	}
	
	
	
	
	
}
