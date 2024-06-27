package action;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Assignment2 {

	WebDriver driver;

	String projPath = System.getProperty("user.dir");

	String getSearchTextValue = "", flag = "False";

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", projPath + "\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/"); // Open Amazon URL

	}

	@Test
	public void searchText(){

		try{

			WebElement search = driver.findElement(By.xpath("//*[@placeholder='Search Amazon']")); // Define webElement for search testxbox 
			search.sendKeys("iphone"); // Search iphone in Amazon UI
			WebElement search_btn = driver.findElement(By.id("nav-search-submit-button"));
			search_btn.click(); // Click on search icon
			Thread.sleep(2000);
			// Get first result of search Apple iPhone
			WebElement restultTest = driver.findElement(By.cssSelector("div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1'] span[class='a-size-medium a-color-base a-text-normal']"));
			getSearchTextValue = restultTest.getText();
			System.out.println(getSearchTextValue);
			// Validate text Apple iPhone
			Assert.assertTrue(getSearchTextValue.contains("Apple iPhone"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() {
		driver.close();
	}

}
