package testCasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	@Test
	public void verify_login()
	{
		logger.info("****Starting TC002_LoginTest****");
		try 
		{
			HomePage hp=new HomePage(driver);
			hp.ClickMyAccount();
			hp.ClickLogin();
			LoginPage lp=new LoginPage(driver);
			lp.SetEmailAddress(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.ClickLogin();
			MyAccountPage macc=new MyAccountPage(driver);
			boolean targetPage=macc.isMyAccountPageExists();
			
			
			Assert.assertEquals(targetPage,true);
		}
		catch(Exception e)
		{
			  
			logger.info("*****Finished TC002_LoginTest****");
			Assert.fail();

		}
	
	}

}
