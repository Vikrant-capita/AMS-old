package utils;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class CalenderHandle {


	public String getCalendor(String month, String year, String date, String[] monthAndDate, WebElement clickOnArrow, List<WebElement> allDateList, WebElement monthYearEle) throws InterruptedException {
		//ml=new myLeavesObject(driver);
		String expectedmonthy="";
		//System.out.println("month :"+ month);
		
		System.out.println("month index outside while :"+monthAndDate[0]);
		////System.out.println("month index outside while :"+month);
		//System.out.println("year index outside while :"+monthAndDate[1]);
		
		String calendarMonth=monthAndDate[0];
		String calendarYear=monthAndDate[1];
		String monthYear=calendarMonth.concat(" "+calendarYear);
		
		String userMonth=month;
		String userYear=year;
		String userMonthYear=userMonth.concat(", "+userYear);
		
		System.out.println("concat calendar :"+monthYear);
		System.out.println("concat user :"+userMonthYear);
		
		String monthYearEleText=monthYearEle.getText();
		System.out.println("Element Text :"+monthYearEleText);
		
		
		
		while(!monthYearEleText.equals(userMonthYear) ){
					
			//Thread.sleep(1000);
			clickOnArrow.click();
			monthYearEleText=monthYearEle.getText();
			//System.out.println("element text inside while :"+monthYearEleText);
			//System.out.println("concat cale inside while :"+userMonthYear);
			
		}
		//List<WebElement> allDateList=ml.getDateList();
		for(WebElement singleDate:allDateList){
			String text=singleDate.getText();
			//System.out.println("date selected :"+text);
			if(text.equalsIgnoreCase(date)) {
				System.out.println("date selected inside if :"+text);
				Thread.sleep(2000);
				singleDate.click();
				break;
			}
		}
		//return expectedmonthyr;
		return expectedmonthy;
	}
	
	
	
	
	
}
