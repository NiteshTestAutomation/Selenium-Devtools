package com.devtools.testcases;


import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.devtools.v128.network.Network;
import org.openqa.selenium.devtools.v128.performance.Performance;
import org.openqa.selenium.devtools.v128.performance.model.Metric;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.http.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import org.openqa.selenium.support.ui.WebDriverWait;



public class PerformanceTabDetails {
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
	
	@Test(enabled=false)
	public void verifyPerformanceTabDetails()
	{

//	   driver  = new ChromeDriver();

	   driver.get("https://stg-identity.mt.gov.sa/");
	   
	   driver.manage().window().maximize();
	   
	   DevTools devTools = ((HasDevTools) driver).getDevTools();
	    devTools.createSession();

	    devTools.send(Performance.enable(Optional.empty()));
	    List<Metric> metricList = devTools.send(Performance.getMetrics());
	    
	    Map<String, Number> metrics = new HashMap<>();
	    for (Metric metric : metricList) {
	      metrics.put(metric.getName(), metric.getValue());
	      System.out.println("MetricsGetName: "+metric.getName()+"MetricsGetValue: "+metric.getValue());
	    }
	    System.out.println(metrics.get("DevToolsCommandDuration").doubleValue());
	   // Assertions.assertTrue(metrics.get("DevToolsCommandDuration").doubleValue() > 0);
	    System.out.println(metrics.get("Frames").intValue());
//	    Assertions.assertEquals(12, metrics.get("Frames").intValue());
	   
		
//	 	driver.close();
    }	
	
	@Test(enabled=false)
	public void verifyBrowserCookiesDetails()
	{
//	   driver  = new ChromeDriver();

	  // driver.get("https://stg-identity.mt.gov.sa/");
	   
	   DevTools devTools = ((HasDevTools) driver).getDevTools();
	    devTools.createSession();
	
	    devTools.send(
	            Network.setCookie(
	                    "cheese",
	                    "gouda",
	                    Optional.empty(),
	                    Optional.of("www.selenium.dev"),
	                    Optional.empty(),
	                    Optional.of(true),
	                    Optional.empty(),
	                    Optional.empty(),
	                    Optional.empty(),
	                    Optional.empty(),
	                    Optional.empty(),
	                    Optional.empty(),
	                    Optional.empty(),
	                    Optional.empty()));

	    driver.get("https://www.selenium.dev");
	    
	    driver.manage().window().maximize();
	    Cookie cheese = driver.manage().getCookieNamed("cheese");
        System.out.println(""+cheese.getValue());
//	 	driver.close();
    }	
	
	  @Test(enabled = true)
	  public void verifyRecordResponse() {
	    CopyOnWriteArrayList<String> contentType = new CopyOnWriteArrayList<>();

	    try (NetworkInterceptor ignored =
	        new NetworkInterceptor(
	            driver,
	            (Filter)
	                next ->
	                    req -> {
	                      HttpResponse res = next.execute(req);
	                      contentType.add(res.getHeader("Content-Type"));
	                      return res;
	                    })) {
	      driver.get("https://stg-identity.mt.gov.sa/");
	      wait.until(_d -> contentType.size() > 1);
	    }   
	    System.out.println("contentType: "+contentType.get(1));
	    System.out.println("contentType: "+contentType.get(2));
	    System.out.println("contentType: "+contentType.get(3));
	    System.out.println("contentType: "+contentType.get(4));
	    
	    System.out.println("contentType: "+contentType.get(5));
	    System.out.println("contentType: "+contentType.get(6));
	    System.out.println("contentType: "+contentType.get(7));
	    System.out.println("contentType: "+contentType.get(8));
	    
	   // Assertions.assertEquals("text/html; charset=utf-8", contentType.get(0));
	  }
	
}
