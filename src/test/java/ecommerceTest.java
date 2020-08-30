import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class ecommerceTest {
	AndroidDriver driver;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		driver=ecommerce.capabilities("emulator");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void fillFormAndClickOnLetsShop(){
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Hello");
		driver.hideKeyboard();
		WebElement genderRadioButton= (WebElement) driver.findElements(By.xpath("(//android.widget.RadioButton)")).get(1);
		genderRadioButton.click();
		//Click on DropDown
		driver.findElementById("android:id/text1").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
	}

	@Test
	public void verifyToastErrorMessageIsDisplayedWhenUserNameIsNotEntered(){
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		String ToastMessage=driver.findElement(By.xpath("//android.widget.Toast")).getAttribute("name");
		System.out.println(ToastMessage);
		Assert.assertEquals(ToastMessage,"Please enter your name","Expected Toast Message did not match Actual Toast " +
				"message");
	}

	@Test
	public void shopTheItemByScrollingSpecificProductAndAddToCart(){
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Hello");
		driver.hideKeyboard();
		WebElement genderRadioButton= (WebElement) driver.findElements(By.xpath("(//android.widget.RadioButton)")).get(1);
		genderRadioButton.click();
		//Click on DropDown
		driver.findElementById("android:id/text1").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Jordan 6 Rings\").instance(0))"));

		//Verify whether required product is present
		int count=driver.findElementsById("com.androidsample.generalstore:id/productName").size();
		for(int i=0;i<=count;i++){
			WebElement textElement=
					(WebElement) driver.findElementsById("com.androidsample.generalstore:id/productName").get(i);
			String text= textElement.getText();
			if(text.equalsIgnoreCase("Jordan 6 Rings")){
				WebElement addtoCartForJordan6= (WebElement) driver.findElementsById("com.androidsample" +
						".generalstore:id/productAddCart").get(i);
				addtoCartForJordan6.click();
				break;
			}
		}
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();
		String expectedText=driver.findElementById("com.androidsample.generalstore:id/productName").getText();
		Assert.assertEquals(expectedText,"Jordan 6 Rings","ProductName in checkout Page does not match with Product " +
				"Name in Page 2");
		}

	@Test
	public void validateTotalAmountDisplayedCheckoutPage(){
		Double sumOfProductPrice=0.00;
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Hello");
		driver.hideKeyboard();
		WebElement genderRadioButton= (WebElement) driver.findElements(By.xpath("(//android.widget.RadioButton)")).get(1);
		genderRadioButton.click();
		//Click on DropDown
		driver.findElementById("android:id/text1").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		int count=driver.findElementsById("com.androidsample.generalstore:id/productName").size();
		for(int i=0;i<count;i++){
			driver.findElement(By.xpath("//*[@text='ADD TO CART']")).click();
		}
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

		//Get the Total Amount From Checkout
		String totalAmountWith$ = driver.findElementById("com.androidsample.generalstore:id/totalAmountLbl").getText();
		System.out.println(totalAmountWith$);
		String totalAmountWithout$=totalAmountWith$.split(" ")[1];
		System.out.println(totalAmountWithout$);
		Double numericTotalAmount=Double.parseDouble(totalAmountWithout$);

		int totalProducts= driver.findElementsById("com.androidsample.generalstore:id/productPrice").size();
		for(int j=0;j<totalProducts;j++){
			WebElement productElement=
					(WebElement) driver.findElementsById("com.androidsample.generalstore:id/productPrice").get(j);
			String productPrice = productElement.getText();
			productPrice=productPrice.substring(1);
			System.out.println(productPrice);
			Double numericProductPrice=Double.parseDouble(productPrice);
			sumOfProductPrice=sumOfProductPrice + numericProductPrice;
		}
		System.out.println(sumOfProductPrice);
		Assert.assertEquals(numericTotalAmount,sumOfProductPrice);
	}

	@Test
	public void verifyMobileGesturesAreWorkingForLinks() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Hello");
		driver.hideKeyboard();
		WebElement genderRadioButton= (WebElement) driver.findElements(By.xpath("(//android.widget.RadioButton)")).get(1);
		genderRadioButton.click();
		//Click on DropDown
		driver.findElementById("android:id/text1").click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));");
		driver.findElement(By.xpath("//android.widget.TextView[@text='Belgium']")).click();
		driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		int count=driver.findElementsById("com.androidsample.generalstore:id/productName").size();
		for(int i=0;i<count;i++){
			driver.findElement(By.xpath("//*[@text='ADD TO CART']")).click();
		}
		driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

		//Click on Checkbox
		WebElement checkBox=driver.findElementByXPath("//android.widget.CheckBox");
		//driver.findElementByXPath("android.widget.CheckBox").click();
		TouchAction touchAction=new TouchAction(driver);
	   touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(checkBox))).perform();
		WebElement termsAndConditions=driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

		//Open Terms And Conditions by LongPressing.And Then Close it.

	//	touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element
		//	(termsAndConditions)).withDuration(ofSeconds(2))).release().perform();
	//	driver.findElementByXPath("//*[@text='CLOSE']").click();



		//Navigate to Website
		driver.findElementByXPath("//*[@text='Visit to the website to complete " +
				"purchase']").click();

		Thread.sleep(7000);

		Set<String> contexts=driver.getContextHandles();
		for(String context:contexts){
			System.out.println(context);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");

		System.out.println(driver.getCurrentUrl());

		driver.findElement(By.name("q")).sendKeys("hello");

		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);


		driver.context("NATIVE_APP");




	}
}

