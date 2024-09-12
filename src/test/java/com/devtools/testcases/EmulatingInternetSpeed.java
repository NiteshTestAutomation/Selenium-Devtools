package com.devtools.testcases;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v126.network.model.ConnectionType;
import org.openqa.selenium.devtools.v127.emulation.Emulation;
import org.openqa.selenium.devtools.v128.network.Network;
//import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmulatingInternetSpeed {

	ChromeDriver driver ;
	WebDriverWait wait;
	@BeforeTest
	public void browserSetup()
	{
		 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 driver  = new ChromeDriver();
	}
	
	@AfterTest
	public void browserClose()
	{
		 
		 driver.close();
	}
	
	@Test(enabled=true)
	public void verifyApplicationOnSlowInternetSpeed()
	{
		driver.manage().window().maximize();
		
		DevTools devTools = driver.getDevTools();
		
		devTools.createSession();
		                                                
//		devTools.send(Network.emulateNetworkConditions(false , 150 ,2500 , 2000, Optional.of(ConnectionType.CELLULAR2G)));
		
		 devTools.createSession();
	        devTools.send(Network.enable(
	                Optional.empty(),
	                Optional.empty(),
	                Optional.empty()));
//	        devTools.send(Network.emulateNetworkConditions(false, 150, 2500, 2000, Optional.of(ConnectionType.CELLULAR2G, null, null, null));
	       /* devTools.send(Network.emulateNetworkConditions(
	                false,
	                150,
	                2500,
	                2000,
	                Optional.of(ConnectionType.CELLULAR2G
	                ));*/
	        driver.get("https://www.amazon.com");
	        System.out.println("Title of the website when used slowNetworkConditions method " + driver.getTitle());
		
	}

}
