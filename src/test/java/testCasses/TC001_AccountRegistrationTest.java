package testCasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobjects.AccountRegistrationPage;
import pageobjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
@Test
	public void verify_account_registration()
	{
	logger.info("****Starting TC001_AccountRegistrationTest*****");
	
	try
	{
		HomePage hp=new HomePage(driver);
		hp.ClickMyAccount();
		logger.info("****Clicked on MyAccount****");

		hp.ClickRegister();
		logger.info("*****Clicked on Register*****");

		AccountRegistrationPage regPage=new AccountRegistrationPage(driver);
		logger.info("****providing customer details*****");

		
		regPage.setFirstName(randomString().toUpperCase());
		regPage.setLastName(randomString().toUpperCase());
		regPage.setEmail(randomString()+"@gmail.com");
		
		regPage.setTelephone("9133630484");
		
		String Password=randomAlphaNumberic();
		regPage.setPassword(Password);
		regPage.setConfirmPassword(Password);
		regPage.setPolicy();
		regPage.setContinue();
		
		logger.info("****Validating expected masseage*****");

		String Confmsg=regPage.getConfirmationMsg();
		if(Confmsg.equals("Your Account Has Been Created!!!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs...");
			Assert.assertFalse(false);
		}

	}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("*****Finishing TC001_AccountRegistrationTest*****");

	}
}
