package tasks.vtman;

import common.CommonAppController;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class LoginPage extends CommonAppController {
    private WebDriver driver;

    public LoginPage(AppiumDriver dr) throws MalformedURLException {
        this.driver = (AppiumDriver) dr;
        driver = initDriverTest();
        PageFactory.initElements(driver, this);
    }
    public void signin() {

        System.out.println("chay di nao: ");
    }
}
