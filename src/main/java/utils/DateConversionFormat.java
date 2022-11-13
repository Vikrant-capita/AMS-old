package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

public class DateConversionFormat {

	
	//=====================Suitable for On HomePage : Available till "30-Dec-2023"=================================================
	
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
	
	}
	
	//=====================Suitable for On HomePage : Available till "30-Dec-2023" With Return=================================================

	public void dateFormatConversion2(String  schedule)
	{

	ArrayList<String> exceptionLeavePending=new ArrayList<String>();
	
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
	
	exceptionLeavePending.add(schedule)
	
	
	}
	
	
	
	
	//=====================Suitable for On "12 Nov 2022"=================================================

	public List<Integer> dateFormatConversion1(String  schedule)
	{
		
	List<Integer> alldata = new ArrayList<>();
	int date = Integer.parseInt(schedule.split(" ")[0]);
	int year = Integer.parseInt(schedule.split(" ")[2]);
	String Month = schedule.split(" ")[1];

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
	alldata.add(date);
	alldata.add(month);
	alldata.add(year);
	
	return alldata;
	}
	
	
	
	
	
}
