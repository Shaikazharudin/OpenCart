package testCasses;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
		
 public WebDriver driver;
 public org.apache.logging.log4j.Logger logger;
 public Properties p;
@BeforeClass
@Parameters({"os","browser"})
public void setup(String os, String br) throws IOException
{
	FileReader file=new FileReader(".//src//test//resources//config.properties");
	
	p=new Properties();
	p.load(file);
	
	
	logger=LogManager.getLogger(this.getClass());
	
	switch(br.toLowerCase())
	{
	case "chrome" : 	driver=new ChromeDriver(); break;
	case "edge" : 	driver=new EdgeDriver(); break;
	case "firefox" : 	driver=new FirefoxDriver(); break;
    default :System.out.println("Invalid browser name...");

	}
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(p.getProperty("appURL2"));
	driver.manage().window().maximize();
}
@AfterClass
public void teardown()
{
	driver.quit();
}
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;

	}
	public String randomAlphaNumberic()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(3);
		String generatedNumber=RandomStringUtils.randomNumeric(3);
		return(generatedString+"@"+generatedNumber);
	}
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenShots\\"+tname+"_"+timeStamp+".png";
		 File targetFile=new File(targetFilePath);
		 sourceFile.renameTo(targetFile);
		 return targetFilePath;
	}

}