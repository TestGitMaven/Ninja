package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;

public class Login extends Base {
	WebDriver driver;

	public Login() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void VerifyLoginWithValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"Edit your account information not displayed");

	}

	@Test(priority = 2)
	public void VerifyLoginWithInvalidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwaraningmessage = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

		String expetedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwaraningmessage.contains(expetedWarningMessage),
				"Expexcted warning message not displayed");
	}

	@Test(priority = 3)
	public void VerifyLoginWithValidEmailAddressandInvalidpasword() {

		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwaraningmessage = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

		String expetedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwaraningmessage.contains(expetedWarningMessage),
				"Expexcted warning message not displayed");

	}

	@Test(priority = 4)
	public void VerifyLoginWithInValidEmailAddressandvalidpasword() {

		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwaraningmessage = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

		String expetedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwaraningmessage.contains(expetedWarningMessage),
				"Expexcted warning message not displayed");

	}

	@Test(priority = 5)
	public void VerifyLoginWithoutProvidingCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualwaraningmessage = driver
				.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();

		String expetedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualwaraningmessage.contains(expetedWarningMessage),
				"Expexcted warning message not displayed");

	}

}
