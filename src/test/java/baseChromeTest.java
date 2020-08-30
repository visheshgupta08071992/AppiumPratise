import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class baseChromeTest {

	AndroidDriver driver;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver= baseChrome.capabilities("emulator");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void InvokeChrome(){
		driver.get("https://www.facebook.com/");
//		driver.findElement(By.xpath("//*[@id='root']/div[1]/div/div/div[1]/div/a[1]/div/div[2]")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vishesh.gupta@abc.com");
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("hello");

	}
	@Test
	public void AutomateCricbuzz(){
		driver.get("https://www.cricbuzz.com/");
//		driver.findElement(By.xpath("//*[@id='root']/div[1]/div/div/div[1]/div/a[1]/div/div[2]")).click();
		driver.findElement(By.xpath("//span[normalize-space(text()) = 'Menu' ]")).click();
		driver.findElement(By.xpath("//a[@title='Cricbuzz Home']")).click();
		System.out.println(driver.getCurrentUrl());
		//Scroll in Appium
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,480)", "");
		Assert.assertTrue(driver.findElement(By.xpath("//h4[text()='Top Stories']")).getAttribute("class").contains(
				"header"));

	}
}
