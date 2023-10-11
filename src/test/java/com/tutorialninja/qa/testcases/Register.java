package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.base.Base;
import com.tutorialninja.qa.utils.utils;

public class Register extends Base {
	WebDriver driver;
	public Register() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = intializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));

		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		driver.findElement(By.id("input-firstname")).sendKeys("vv");
		driver.findElement(By.id("input-lastname")).sendKeys("rr");
		driver.findElement(By.id("input-email")).sendKeys(utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String ActualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(ActualSuccessMessage, "Your Account Has Been Created!",
				"Acccount not created successfully");

	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithAllFields() {

		driver.findElement(By.id("input-firstname")).sendKeys("vv");
		driver.findElement(By.id("input-lastname")).sendKeys("rr");
		driver.findElement(By.id("input-email")).sendKeys(utils.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String ActualSuccessMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(ActualSuccessMessage, "Your Account Has Been Created!",
				"Acccount not created successfully");

	}

	@Test(priority = 3)
	public void verifyRegisteringAnAccountWithExsistingEmailAddress() {

		driver.findElement(By.id("input-firstname")).sendKeys("vv");
		driver.findElement(By.id("input-lastname")).sendKeys("rr");
		driver.findElement(By.id("input-email")).sendKeys("amotooricap9@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'][@value=1]")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String ActualWaraning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"))
				.getText();
		System.out.println(ActualWaraning);
		Assert.assertTrue(ActualWaraning.contains("Warning: E-Mail Address is already registered!"),
				"Waraning messsage not dispalyed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {

		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		String ActualPrivacyPolicyWaraning = driver
				.findElement(By.xpath("//div[contains(@class,'alert alert-danger alert-dismissible')]")).getText();
		Assert.assertTrue(ActualPrivacyPolicyWaraning.contains("Warning: You must agree to the Privacy Policy!"),
				"Privacy Warning message not displayed");
		String AcutalFistNameWarning = driver
				.findElement(By.xpath("//input[@id ='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(AcutalFistNameWarning, "First Name must be between 1 and 32 characters!",
				"FirstName Warning Not displayed");
		String AcutalLastNameWarning = driver
				.findElement(By.xpath("//input[@id ='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(AcutalLastNameWarning, "Last Name must be between 1 and 32 characters!",
				"LastName Warning Not displayed");
		String AcutalEmailWarning = driver.findElement(By.xpath("//input[@id ='input-email']/following-sibling::div"))
				.getText();
		Assert.assertEquals(AcutalEmailWarning, "E-Mail Address does not appear to be valid!",
				"Email Warning Not displayed");
		String AcutalTelephoneWarning = driver
				.findElement(By.xpath("//input[@id ='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(AcutalTelephoneWarning, "Telephone must be between 3 and 32 characters!",
				"Telephone Warning Not displayed");
		String AcutalPasswordWarning = driver
				.findElement(By.xpath("//input[@id ='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(AcutalPasswordWarning, "Password must be between 4 and 20 characters!",
				"Password Warning Not displayed");

	}

}
