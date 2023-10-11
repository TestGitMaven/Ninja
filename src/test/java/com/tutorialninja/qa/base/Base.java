package com.tutorialninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialninja.qa.utils.utils;

public class Base {
	WebDriver driver;
	public Properties prop;

	public Base() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver intializeBrowserAndOpenApplicationURL(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(utils.IMPLICITY_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(utils.PAGE_LOAD_TIME));
		return driver;

	}

}
