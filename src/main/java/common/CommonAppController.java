package common;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CommonAppController extends net.thucydides.core.pages.PageObject {
    private WebDriver driver;

    public WebDriver initDriverTest() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:deviceName","R58N62SNCLR");
//        cap.setCapability("appium:udid","R58N62SNCLR");
        cap.setCapability("appium:platformName","Android");
        cap.setCapability("appium:platformVersion","11.0");
        cap.setCapability("appium:appPackage","com.viettelpost.vtman");
        cap.setCapability("appium:appActivity","com.viettelpost.vtman.MainActivity");
        cap.setCapability("appium:automationName","UiAutomator2");
//        URL url1 = new URL("http://127.0.0.1:4723/wb/hub");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wb/hub"),cap);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }
}
