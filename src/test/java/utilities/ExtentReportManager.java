package utilities;
import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCasses.BaseClass;

public class ExtentReportManager  implements ITestListener{
public ExtentTest test;
public ExtentReports extent;
 String repName;
 
 public void onStart(ITestContext testContext)
 {
	 String TimeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	 
	 
	 repName="Test-Report-"+TimeStamp+".html";
	 ExtentSparkReporter sparkReporter=new ExtentSparkReporter(".\\reporter\\"+repName);
	 sparkReporter.config().setDocumentTitle("opencart Automation Report");
	 repName="Test-Report-"+TimeStamp+".html";
		sparkReporter =new ExtentSparkReporter(".\\reporter\\"+repName);
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		
		sparkReporter.config().setTheme(Theme.STANDARD);
		
		extent =new ExtentReports();
		extent.attachReporter();
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("user name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Opereting  System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
		
	}
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got Successfully executed");
		
	}
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try {
			String imgpath= new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		public  void onTestSkipped(ITestResult result) {
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+"Got Skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
		}
		public void onFinish(ITestContext testContext) {
			extent.flush();
				String pathOfExtentReport=System.getProperty("user.dir")+"\\reporter\\"+repName;
				File extentReport=new File(pathOfExtentReport);
				try {
					Desktop.getDesktop().browse(extentReport.toURI());
				}catch (Exception e) {
					e.printStackTrace();
				}
			
	}
 }
	

