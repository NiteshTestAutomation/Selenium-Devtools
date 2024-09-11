package com.devtools.testcases;

import java.time.Duration;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.emulation.Emulation;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmulatingGeoLocation {
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
	public void verifyGeolocationisEmulated()
	{
		driver.manage().window().maximize();
		
		DevTools devTools = driver.getDevTools();
		
		devTools.createSession();
		                                                //lattitude
		devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043), 
				//longitutde
				Optional.of(13.4501),Optional.of(1)));
		
		driver.get("https://my-location.org/");
		
	}

}
