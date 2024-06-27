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
		//driver.get("https://erail.in/trains-between-stations/ghaziabad-GZB/new-delhi-NDLS");
		driver.get("https://www.amazon.com/");

	}

	@Test
	public void searchText(){

		try{

			WebElement search = driver.findElement(By.xpath("//*[@placeholder='Search Amazon']"));
			search.sendKeys("iphone");
			//search.sendKeys(Keys.RETURN);
			WebElement search_btn = driver.findElement(By.id("nav-search-submit-button"));
			search_btn.click();
			Thread.sleep(2000);

			// 1 Way

			WebElement restultTest = driver.findElement(By.cssSelector("div[class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_1'] span[class='a-size-medium a-color-base a-text-normal']"));
			getSearchTextValue = restultTest.getText();
			System.out.println(getSearchTextValue);
			Assert.assertTrue(getSearchTextValue.contains("Apple iPhone"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void waitForLoad(driver) {
		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) driver ->
				((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}

	@After
	public void tearDown() {
		driver.close();
	}

}
