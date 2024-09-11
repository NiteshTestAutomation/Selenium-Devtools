package com.devtools.testcases;

import java.util.concurrent.CopyOnWriteArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v128.log.Log;
import org.openqa.selenium.logging.EventType;
import org.openqa.selenium.logging.HasLogEvents;
import org.testng.annotations.Test;

public class ConsoleTabDetails {

	ChromeDriver driver;

	@Test
	public void verifyConsoleTabDetails() {
		driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();

		driver.get("https://stg-identity.mt.gov.sa/");

		driver.findElement(By.xpath("//*[@id=\"top-nav\"]/nav/a")).click();
		  devTools.createSession();
		  
		  devTools.send(Log.enable());
		  
		  devTools.addListener(Log.entryAdded(), logEntry -> {
		  System.out.println("log: "+logEntry.getText());
		  System.out.println("level: "+logEntry.getLevel()); });
		  
		  devTools.getDomains().events().addConsoleListener(consoleEvent ->
		  System.out.println(consoleEvent));
		  
		  devTools.send(Log.clear());
		  
		  devTools.send(Log.disable());
		 
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//*[@id=\"top-nav\"]/nav/a")).click();

		/*
		 * CopyOnWriteArrayList<String> messages = new CopyOnWriteArrayList<>();
		 * 
		 * ((HasLogEvents) driver).onLogEvent(consoleEvent(e ->
		 * messages.add(e.getMessages().get(0))));
		 */

  

		driver.close();

	}


}
