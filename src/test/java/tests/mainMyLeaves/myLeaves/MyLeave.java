package tests.mainMyLeaves.myLeaves;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.homePageObjects.HomePageObject;
import pageObjects.myLeavesObjects.myLeavesobject.MyLeaveObject;
import pageObjects.myLeavesObjects.myLeavesobject.leavePlanObject;
import tests.LoginTest.LoginPage;
import utils.UserManagerDetailsValidation;
import utils.excelDriven.excelDriven;

public class MyLeave {
	
	public WebDriver driver;
	public 	MyLeaveObject leaveplan;
	public int waitingStatusSizeCurrentYr=0;
	public int approveStatusSizCurrentYr=0;
	public int waitingStatusSizePrevYr=0;
	public int approveStatusSizePrevYr=0;
	public String leavetype;
	public String valueOfWaiting;
	public String valueOfAvailed;
	public String valueOfCarryForward;
	public String valueOfPrevYearBalanceOfPL;
	public int carryForwardValueCY = 0;
	public int balnceValuePY=0;
	@BeforeClass
	public void initialize() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage();
		driver=lp.validatelogin();
		//driver=lp.validateManagerLogin();
	}
	
	@Test(priority=1)
	public void validateUserDetails() throws IOException, InterruptedException
	{
		leavePlanObject ml=new leavePlanObject(driver);
		Thread.sleep(2000);
		ml.getClickOnMyLeaves();
		leaveplan=new MyLeaveObject(driver);
		leaveplan.getMyLeave();
		String empName=leaveplan.getEmpName();
		UserManagerDetailsValidation userMgr=new UserManagerDetailsValidation(driver);
		userMgr.usersManagerDetailsValidation(leaveplan.getEmpName(), leaveplan.getEmpID(), leaveplan.getManagerName(), leaveplan.getManagerID());
	}
	
	@Test(priority=2)
	public void ValidatePrivilegeLeavePL() throws InterruptedException, IOException
	{
		String leavetype="PRIVILEGE LEAVE (PL)";
		validateMyLeaveWithLeaveBalance(leavetype);
	}
	@Test(priority=3)
	public void ValidateCasualLeaveCL() throws InterruptedException, IOException
	{
		String leavetype="CASUAL LEAVE (CL)";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}
	
	@Test(priority=4)
	public void ValidateAdoptionLeaveAL() throws InterruptedException, IOException
	{
		String leavetype="ADOPTION LEAVE (AL) Male";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}
	@Test(priority=5)
	public void ValidateBereavementLEaveBL() throws InterruptedException, IOException
	{
		String leavetype="BEREAVEMENT LEAVE (BL)";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}
	@Test(priority=6)
	public void ValidateCSRActivitiesLeaveCAL() throws InterruptedException, IOException
	{
		String leavetype="CSR ACTIVITIES LEAVE (CAL)";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}
	
	@Test(priority=7)
	public void ValidateFiveForFive() throws InterruptedException, IOException
	{
		String leavetype="FIVE FOR FIVE";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}
	@Test(priority=8)
	public void ValidateFiveForTen() throws InterruptedException, IOException
	{
		String leavetype="FIVE FOR TEN";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}

	@Test(priority=9)
	public void ValidateGiftOfLeaveGOL() throws InterruptedException, IOException
	{
		String leavetype="GIFT OF LEAVE (GOL)";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}

	@Test(priority=10)
	public void ValidatePatenityLeave() throws InterruptedException, IOException
	{
		String leavetype="PATERNITY LEAVE";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}

	@Test(priority=11)
	public void ValidateRegulatoryLeave() throws InterruptedException, IOException
	{
		String leavetype="REGULATORY LEAVE";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}

	@Test(priority=12)
	public void ValidateSabbaticalLeaveSBL() throws InterruptedException, IOException
	{
		String leavetype="SABBATICAL LEAVE(SBL)";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}
	@Test(priority=13)
	public void ValidateStudyLeaveACE() throws InterruptedException, IOException
	{
		String leavetype="STUDY LEAVE ACE";
		validateMyLeaveWithLeaveBalance(leavetype);
		
	}

	
	public void validateMyLeaveWithLeaveBalance(String leavetype) throws InterruptedException, IOException 
	{
		//leavetype="CASUAL LEAVE (CL)";
		leaveplan.getMyLeave();
		valueOfWaiting = "waiting for approval";
		valueOfAvailed = "availed";
		valueOfCarryForward = "Carry Forward";
		valueOfPrevYearBalanceOfPL= "Balance";
		leaveplan.getLeaveType(leavetype);
		Thread.sleep(3000);
		System.out.println("size:"+leaveplan.getWorkingDateList().size());
		if(leaveplan.getWorkingDateList().size()!=0)
		{
			statuslist(leaveplan.getStatus(),leaveplan.getWorkingDateList());
				for(int i=0;i<leaveplan.getClickOnNextPage().size();i++) 
				{
					
				try {
						if(leaveplan.getClickOnNextPage().get(i).isDisplayed())
						{
							Thread.sleep(2200);
							leaveplan.getClickOnNextPage().get(i).click();
							System.out.println("next page clicked");
							Thread.sleep(2000);
							statuslist(leaveplan.getStatus(),leaveplan.getWorkingDateList());
							//flag=true;
						}	
					}
					catch(NoSuchElementException e)
					{
						System.out.println("error : "+e.getMessage());
						
					}
				}
			
			Thread.sleep(2000);
			leaveplan.getClickOnLeaveBalance();
			validationOfStatusandValue("currentYear",leavetype);
			Thread.sleep(1000);
			if(waitingStatusSizePrevYr>0 || approveStatusSizePrevYr>0 ||leavetype.contains("PRIVILEGE LEAVE (PL)"))
			{
				validationOfStatusandValue("previousYear",leavetype);
			}
			//click on My leave
		}
		else
		{
			System.out.println(leavetype+":No List Found");
		}
		
	}
	
	public void statuslist(List<String>status,List<String> workingDateList)
	{
//		System.out.println("year size:"+workingDateList.size());
//		System.out.println("Status size:"+status.size());
		  int currentYear = Year.now().getValue();
	        int previousYear = currentYear-1;
	        String curYr=Integer.toString(currentYear);
	        String prevYr=Integer.toString(previousYear);
//	        System.out.println(curYr);
//	        System.out.println(prevYr);
		for(int i=0;i<workingDateList.size();i++)
		{
			String year = workingDateList.get(i);
			//System.out.println("year:"+year);
			
			for(int j=i;j<status.size();j++)
			{
				String st=status.get(i);
				//System.out.println("status:"+status);
				if(year.contains(curYr))
				{
					
					if(st.contains("Waiting"))
					{
						waitingStatusSizeCurrentYr++;
						//System.out.println("inside waiting if");
						break;
					}
					if(st.contains("Approved"))
					{
						approveStatusSizCurrentYr++;
						//System.out.println("inside approve if");
						break;
					}
					
				}

				if(year.contains(prevYr))
				{
					
					if(st.contains("Waiting"))
					{
						waitingStatusSizePrevYr++;
						//System.out.println("inside waiting if");
						break;
					}
					if(st.contains("Approved"))
					{
						approveStatusSizePrevYr++;
						//System.out.println("inside approve if");
						break;
					}
					
					
				}
			}
		}



	}
	
	public void validationOfStatusandValue(String year,String leavetype) throws InterruptedException
	{
		
		leaveplan.getClickLeaveBalanceYear(year);
		Thread.sleep(2000);
		int waitingValue=leaveplan.getLeaveTypeIndex(leavetype,valueOfWaiting);
		int approveValue=leaveplan.getLeaveTypeIndex(leavetype,valueOfAvailed);
		System.out.println("waiting value "+year+":"+waitingValue);
		System.out.println("approve value "+year+":"+approveValue);
		if(year.contains("currentYear"))
		{
			System.out.println("waiting size of "+year+":"+waitingStatusSizeCurrentYr);
			System.out.println("approve size of "+year+":"+approveStatusSizCurrentYr);
			Assert.assertEquals(waitingStatusSizeCurrentYr , waitingValue,"waiting for approval Status and value validation failed for "+year+"");
			Assert.assertEquals(approveStatusSizCurrentYr, approveValue,"Approved Status and value validation failed for "+year+"");
		}
		if(year.contains("previousYear"))
		{
			
			System.out.println("waiting size of "+year+":"+waitingStatusSizePrevYr);
			System.out.println("approve size of "+year+":"+approveStatusSizePrevYr);
			Assert.assertEquals(waitingStatusSizePrevYr , waitingValue,"waiting for approval Status and value validation failed for "+year+"");
			Assert.assertEquals(approveStatusSizePrevYr, approveValue,"Approved Status and value validation failed for "+year+"");
		}
		if(leavetype.contains("PRIVILEGE LEAVE (PL)"))
		{
			if(year.contains("currentYear"))
			{
				carryForwardValueCY=leaveplan.getLeaveTypeIndex(leavetype,valueOfCarryForward);
				System.out.println("carry forward value "+year+":"+carryForwardValueCY);
			}
			
			if(year.contains("previousYear"))
			{ 
				balnceValuePY=leaveplan.getLeaveTypeIndex(leavetype,valueOfPrevYearBalanceOfPL);
				System.out.println("Balance value of "+year+" :"+balnceValuePY);
				Assert.assertEquals(carryForwardValueCY, balnceValuePY,"carry forward and balance validation of PL");
			}

		}
	}
		
		
		
	
	
	

}
