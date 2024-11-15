package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{
	WebDriver driver;
 public AccountRegistrationPage(WebDriver driver)
 {
	 super(driver);
 }
 @FindBy(xpath="//input[@id='input-firstname']")
 WebElement txtFirstName;
 @FindBy(xpath="//input[@id='input-lastname']")
 WebElement txtLastName;

 @FindBy(xpath="//input[@id='input-email']")
 WebElement txtEmail;

 @FindBy(xpath="//input[@id='input-telephone']")
 WebElement txtTelephone;

 @FindBy(xpath="//input[@id='input-password']")
 WebElement txtPassword;

 
 @FindBy(xpath="//input[@id='input-confirm']")
 WebElement txtConfPassword;

 @FindBy(xpath="//input[@name='agree']")
 WebElement ChkPolicy;

 
 @FindBy(xpath="//input[@value='Continue']")
 WebElement btnContinue;
 
 @FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
 WebElement msgConfirmation;

 public void setFirstName(String Fname)
 {
	 txtFirstName.sendKeys(Fname);
 }
 
 public void setLastName(String lname)
 {
	 txtLastName.sendKeys(lname);
 }
 public void setEmail(String email)
 {
	 txtEmail.sendKeys(email);
 }
 public void setTelephone(String telphone)
 {
	 txtTelephone.sendKeys(telphone);
 }
 public void setPassword(String pwd)
 {
	 txtPassword.sendKeys(pwd);
	 
 }
 public void setConfirmPassword(String confpwd)
 {
	 txtConfPassword.sendKeys(confpwd);
 }
 public void setPolicy()
 {
	 ChkPolicy.click();
 }
 public void setContinue()
 {
	 btnContinue.click();
 }
 public String getConfirmationMsg()
 {
	 try
	 {
		 return(msgConfirmation.getText());
	 }
	 catch(Exception e) {
	 return(e.getMessage());
 }
 }
}
