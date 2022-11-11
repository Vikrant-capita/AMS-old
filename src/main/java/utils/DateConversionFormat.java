package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.testng.Assert;

public class DateConversionFormat {

	public void dateFormatConversion(String  schedule)
	{

	System.out.println("Schedule date : "+schedule); 
	int date = Integer.parseInt(schedule.split("-")[0]);
	int year = Integer.parseInt(schedule.split("-")[2]);
	String Month = schedule.split("-")[1];

	HashMap<String,Integer> hm = new HashMap<String,Integer>();
	hm.put("Jan", 1);
	hm.put("Feb", 2);
	hm.put("Mar", 3);
	hm.put("Apr", 4);
	hm.put("May", 5);
	hm.put("Jun", 6);
	hm.put("Jul", 7);
	hm.put("Aug", 8);
	hm.put("Sep", 9);
	hm.put("Oct", 10);
	hm.put("Nov", 11);
	hm.put("Dec", 12);

	int month = hm.get(Month);

	DateFormat dateFormat = new SimpleDateFormat("dd");
	DateFormat yearFormat = new SimpleDateFormat("yyyy");
	DateFormat monthFormat = new SimpleDateFormat("MM");
	Date date1 = new Date();
	int systemDate = Integer.parseInt(dateFormat.format(date1));
	int systemYear = Integer.parseInt(yearFormat.format(date1));
	int systemMonth = Integer.parseInt(monthFormat.format(date1));
	Assert.assertTrue(systemDate<=date,"Date verification failed");
	Assert.assertTrue(systemMonth<=month,"Month verification failed");
	Assert.assertTrue(systemYear<=year,"Year verification failed");
	/* if(systemDate>=date && systemMonth<=month && systemYear<=year)
	{
	System.out.println("schedule varified");
	}
	else
	{
	System.out.println("schedule verification failed");
	}*/


	}

	
	
	
	
	
}