package com.inetBanking.testCases;

import org.testng.annotations.Test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


import com.inetBanking.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass
{
	public static Logger logger = LogManager.getLogger(TC_LoginTest_001.class.getName());

	
	@Test
	public void LoginTest() throws IOException 
	{
		//Run this file from TestNG.xml as there is browser parameter given in xml file
		logger.info("URL launched successfully");
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setUsername(username);
		lp.setPassword(password);
		logger.info("Login credential entered");
		lp.clickSubmit();
		
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		} 
		else {
			
			captureScreenShot(driver, "LoginTest");  //Take screenshot on failure
			Assert.assertTrue(false);
			logger.info("Login test failed & screenshot taken");
		}
		
	}

}
