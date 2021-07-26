package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.Utilities.ReadConfig;



public class BaseClass {
	
	//create object for ReadConfig file
	
	ReadConfig readconfig = new ReadConfig();
	
	
	//BaseClass used by all test classes mainly for initializing webdriver and quitting the driver
	public String baseURL = readconfig.getApplicationURL();
	public String username = readconfig.getUserName();
	public String password = readconfig.getPassword();
	
	public static WebDriver driver;
	
	public String basePath = System.getProperty("user.dir");  //this is used in Log4j.xml file
	
	
	@Parameters("browser")  //Browser name coming from TestNG.xml file
	@BeforeClass
	public void setup(String br) {
	
		//Launching resptive browser based on browser name given in TestNG.xml file
		
		if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.get(baseURL);
			
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
		
		System.out.println("Driver quitted");
	}
	
	//Capturing screenshot Method
	
	public void captureScreenShot(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		
		
	}

}
