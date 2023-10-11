package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class Search extends Base {
	WebDriver driver;
	public Search() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProruct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		driver.findElement(By.linkText("HP LP3065")).isDisplayed();

	}

	@Test(priority = 2)
	public void verifySearchWithInValidProruct() {
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String ActualSearchmessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p"))
				.getText();
		System.out.println(ActualSearchmessage);
		Assert.assertEquals(ActualSearchmessage, "There is no product that matches the search criteria.",
				"No Product in Search");

	}

	@Test(priority = 3)
	public void verifySearchWithoutAnyProruct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String ActualSearchmessage = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p"))
				.getText();

		Assert.assertEquals(ActualSearchmessage, "There is no product that matches the search criteria.",
				"No Product in Search");

	}
}
