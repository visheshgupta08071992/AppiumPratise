import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class baseTest {
	AndroidDriver driver;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver=base.capabilities("emulator");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test
	public void test1(){
		WebElement preferance=driver.findElement(By.xpath("//android.widget.TextView[@text='Preference']"));
		preferance.click();
		WebElement preferanceFromCode=driver.findElement(By.xpath("//android.widget.TextView[@text='3. Preference dependencies']"));
		preferanceFromCode.click();
		WebElement wifiCheckBox=driver.findElementById("android:id/checkbox");
		wifiCheckBox.click();
		WebElement wifiSettingsButton=driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]"));
		wifiSettingsButton.click();
		WebElement wifiTextBox=driver.findElementByClassName("android.widget.EditText");
		wifiTextBox.sendKeys("hello");
		WebElement wifiOkButton=driver.findElementByXPath("(//android.widget.Button)[2]");
		wifiOkButton.click();
	}

	@Test
	public void uiAutomaterTest(){
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		System.out.println(driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size());
	}

	@Test
	public void gesturesTest(){
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Custom Adapter']")).click();
		driver.findElement(By.xpath("(//android.widget.TextView[text()='People Names']")).click();
	}

	@Test
	public void gesturesTap(){
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebElement expandList=driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable " +
				"Lists']"));
		TouchAction touchAction=new TouchAction(driver);
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(expandList))).perform();
		WebElement customAdaptor=driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Custom " +
				"Adapter']"));
		touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(customAdaptor))).perform();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Custom Adapter']")).click();
		WebElement peoplesName=driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(peoplesName)).withDuration(Duration.ofSeconds(2))).release().perform();
	}

	@Test
	public void testSwipe(){
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Date Widgets']")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='2. Inline']")).click();
		driver.findElement(By.xpath("//*[@content-desc='9']")).click();
		TouchAction touchAction=new TouchAction(driver);
		//Swipe is nothing but LongPress on one element for 1 sec, move to another element and then you release on
		// another element
		WebElement first= driver.findElement(By.xpath("//*[@content-desc='15']"));
		WebElement second= driver.findElement(By.xpath("//*[@content-desc='45']"));
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(first)).withDuration(Duration.ofSeconds(2))).moveTo(ElementOption.element(second)).release().perform();
	}

	@Test
	public void testScroll(){
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));");
	}

	@Test
	public void DragDrop(){
		driver.findElementByAndroidUIAutomator("text(\"Views\")").click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Drag and Drop']")).click();
		TouchAction touchAction=new TouchAction(driver);
		//LongPress(Source) -> move(Destination) -> Release
		WebElement source= (WebElement) driver.findElementsByClassName("android.view.View").get(0);
		WebElement destination= (WebElement) driver.findElementsByClassName("android.view.View").get(1);
		touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(source)).withDuration(Duration.ofSeconds(2))).moveTo(ElementOption.element(destination)).release().perform();

	}
}
