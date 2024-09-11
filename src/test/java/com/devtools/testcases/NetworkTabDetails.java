package com.devtools.testcases;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v128.network.Network;
import org.testng.annotations.Test;
//import org.openqa.selenium.devtools.v128.network.model.AuthChallengeResponse.Response;
import org.openqa.selenium.devtools.v128.network.model.Request;
import org.openqa.selenium.devtools.v128.network.model.Response;
import org.openqa.selenium.devtools.v128.deviceaccess.model.RequestId;
import java.util.Optional;




public class NetworkTabDetails {
	
	ChromeDriver driver ;
	@Test
	public void verifyNetworkTabDetails()
	{
		
	//	System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
//		ChromeOptions options = new ChromeOptions();
	    //Opening devTools with this command
		//options.addArguments("--auto-open-devtools-for-tabs");
		
	    driver  = new ChromeDriver();

		DevTools devTools = driver.getDevTools();
	        
		devTools.createSession();

		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		//request
		devTools.addListener(Network.requestWillBeSent(), requestSent -> {
			
		Request request = requestSent.getRequest();

	   // System.out.println("Request Sent URL => " + request.getUrl());
//	    System.out.println("Request Sent URL => " + request);   
	       });
		

		//response 
		devTools.addListener(Network.responseReceived(), responseReceived -> {
			
		Response response = responseReceived.getResponse();

        System.out.println("Response URL => " + response.getUrl());
        
        System.out.println("Response status => " + response.getStatus());
        
        System.out.println("Response statusText => " + response.getStatusText());
        
        System.out.println("Response header => " + response.getHeaders());
        
        System.out.println("Response timing => " + response.getTiming());
        
        System.out.println("Response protocol => " + response.getProtocol());
        
        
       });
		driver.get("https://demowebshop.tricentis.com/");
		
//		driver.get("https://stg-identity.mt.gov.sa/");
		
		driver.manage().window().maximize();
		
		driver.close();
	}

}
