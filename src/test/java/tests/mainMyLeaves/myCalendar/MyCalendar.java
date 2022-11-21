package tests.mainMyLeaves.myCalendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import pageObjects.myLeavesObjects.myCalendarObjects.MyCalendarObject;
import tests.LoginPage;

public class MyCalendar {

	public WebDriver driver;
	public String calendarDate;
	
	
	@Test
	public void validateCalendar() throws InterruptedException, IOException {
		
		LoginPage lp=new LoginPage();
		lp.validatelogin();
		driver = lp.driver;
		
		MyCalendarObject mc=new MyCalendarObject(driver);
		Thread.sleep(2000);
		List<WebElement> workingDateList=mc.getWorkingDateList();
		List<WebElement> attendanceStatusList=mc.getAttendanceStatus();
		Thread.sleep(2000);
		System.out.println("before calendar click");
		
		Thread.sleep(1000);
		//System.out.println("After calendar click");
		
		
		List<String> homePageMonthList = new ArrayList<>();
		List<String> homePageDateList = new ArrayList<>();
		for(WebElement workingDate:workingDateList) {
			String date=workingDate.getText().split(", ")[1].split(" ")[0];			 //date only print "20"
			String monthAndYear=workingDate.getText().split(", ")[1].split(" ")[1];  //Month and year will print
			homePageMonthList.add(monthAndYear);
			homePageDateList.add(date);
			System.out.println("Month splitted :"+monthAndYear);
		}
		System.out.println("After calendar click");
		
		for(WebElement attendanceStatus:attendanceStatusList) {
			String attendanceStatusText=attendanceStatus.getText();
			System.out.println("Attendance status text :"+attendanceStatusText);
		}
		
		System.out.println("All working dates size :"+workingDateList.size());
		System.out.println("All attendance status size :"+attendanceStatusList.size());
		
		mc.getClickOnMyCalendar();
		
		List<WebElement> dateList=mc.getCalendarDateList();
		List<String> calendarDateList = new ArrayList<>();
		for(WebElement datelist:dateList) {
			String dateText=datelist.getText();
			calendarDateList.add(dateText);
		}
		System.out.println("Calendar array date list data :"+calendarDateList);
	
		List<WebElement>statusTextList=mc.getCalendarStatusList();
		List<String> calendarSatusList = new ArrayList<>();
		for(WebElement statuslist:statusTextList) {
			String statusText=statuslist.getText();
			calendarSatusList.add(statusText);
		}
		System.out.println("Calendar status list size :"+calendarSatusList.size());
		System.out.println("Calendar array Status list data :"+calendarSatusList);
		
		
		String monthCalender = mc.getMonthCalender();
		System.out.println("calender month : "+monthCalender);
		
		
		
		
		
		
		
		/*
		while(!homePageMonthList.contains(monthCalender))
		{
			mc.getPrevMonthclick();
		}
		for(String homepagedDateList1:homePageDateList){
			for(WebElement calendarDateList:dateList) {
				String calendarDate=calendarDateList.getText();
			}
			homepagedDateList1.contains(calendarDate);
			System.out.println("homape Date and caledar date matched");
			
		}
		*/
		
	}
	
	
	
	
}
