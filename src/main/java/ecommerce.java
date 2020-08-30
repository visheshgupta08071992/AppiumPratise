import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class ecommerce {
	public static AndroidDriver capabilities(String device) throws MalformedURLException {
		DesiredCapabilities cap=new DesiredCapabilities();
		if(device.equals("emulator")){
			cap.setCapability(MobileCapabilityType.DEVICE_NAME,"VisheshEmulator");
		}
		else if(device.equals("real")){
			cap.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Device");
		}
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
		cap.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"/src/General-Store.apk");
		AndroidDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		return driver;
	}
}
