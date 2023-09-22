package tasks.devevtp2;

import helpers.PropertiesManager;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.WebDriver;


public class LoginPage extends PageObject {
    WebDriver driver;
    String inputUserName = "//*[contains(@placeholder,'Nhập số điện thoại')]";
    String btnNhapOtp = "//button[text()='Nhận mã OTP']";
    String inputUserPass = "//*[contains(@placeholder,'Nhập mã OTP')]";
    String btnLogin = "//button[text()='Đăng nhập']";

    public void clickLogin() {

        openUrl("https://devevtp2.viettelpost.vn/");
        String username = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.username");
        String password = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.password");
        System.out.println("Thực hiện login với thông tin username: " + username);

        $(inputUserName).sendKeys(username);
        $(btnNhapOtp).click();
        $(inputUserPass).sendKeys(password);
        $(btnLogin).click();
    }

    public void quitDriver() {
        driver.quit();
    }

}
