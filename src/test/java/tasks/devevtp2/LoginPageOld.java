package tasks.devevtp2;

import common.CommonWebController;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageOld extends CommonWebController {
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(@placeholder,'Nhập số điện thoại')]")
    WebElement inputUserName;

//    private final By inputUserName = By.xpath("//*[contains(@placeholder,'Nhập số điện thoại')]");
//    private final By btnNhapOtp = By.xpath("//button[text()='Nhận mã OTP']");
//    private final By inputUserPass = By.xpath("//*[contains(@placeholder,'Nhập mã OTP')]");
//    private final By btnLogin = By.xpath("//button[text()='Đăng nhập']");
    @FindBy(xpath = "//button[text()='Nhận mã OTP']")
    WebElement btnNhapOtp;
    @FindBy(xpath = "//*[contains(@placeholder,'Nhập mã OTP')]")
    WebElement inputUserPass;
    @FindBy(xpath = "//button[text()='Đăng nhập']")
    WebElement btnLogin;

    public LoginPageOld(WebDriver dr) {
        this.driver = dr;
        String baseUrl = helpers.PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.url");
        driver = initDriverTest(baseUrl);
        PageFactory.initElements(driver, this);
    }


//
//    public void waitForPageLoaded() {
//        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver driver) {
//                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
//                        .equals("complete");
//            }
//        };
//        try {
//            Thread.sleep(1000);
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//            wait.until(expectation);
//        } catch (Throwable error) {
//            Assert.fail("Timeout waiting for Page Load Request to complete.");
//        }
//    }

//    public void signin(String username, String password) throws Exception {
//        enterUsername(username);
//
//        clickNhapOtp();
//        Thread.sleep(500);
//        enterPassword(password);
//
//        clickSignIn();
//        waitForPageLoaded();
//        Thread.sleep(1000);
//    }

    public void signin(String username, String password) {
        inputUserName.sendKeys(username);
        btnNhapOtp.click();
        inputUserPass.sendKeys(password);
        btnLogin.click();
    }
//    public KhaithadiPage signin(String username, String password) throws Exception {
//        enterEmail(username);
//        enterPassword(password);
//        clickSignIn();
//
//        return new DashboardPage(driver);
//    }
}
