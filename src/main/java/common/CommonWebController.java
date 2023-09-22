package common;

import com.ibm.icu.impl.Assert;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.sleep;


public class CommonWebController {
    public WebDriver driver;

    public WebDriver initDriverTest(String url) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/webdriver/windows/chromedriver.exe");

//        WebDriverManager.chromedriver().proxy("10.60.117.113:8080").setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        //khoi tao chrome driver co options
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.navigate().to(url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        return driver;
    }

    public static void waitForJStoLoad(WebDriver driver1) {
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(60), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver1;

        //Wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            assert driver != null;
            return ((Long) ((JavascriptExecutor) driver)
                    .executeScript("return jQuery.active") == 0);
        };

        //Get JQuery is Ready
        boolean jqueryReady = (Boolean) js.executeScript("return jQuery.active==0");

        //Wait JQuery until it is Ready!
        if (!jqueryReady) {
            Logger.info("JQuery is NOT Ready!");
            try {
                //Wait for jQuery to load
                wait.until(jQueryLoad);
            } catch (Throwable error) {
                Assert.fail("Timeout waiting for JQuery load. (" + 60 + "s)");
            }
        }
    }


    public void switchToChildWindowsByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);
            driver.manage().window().maximize();
            //do chạy headless nên hệ thống not working function maximize(). Giải pháp là sử dụng set size cho màn hình            driver.manage().window().setSize(new Dimension(1440, 900));
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                waitForJStoLoad(driver);
                break;
            }
        }
    }

    public void switchToChildWindowsWithElement(WebDriver driver, String xpath) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            driver.switchTo().window(runWindow);//            driver.manage().window().maximize();            //do chạy headless nên hệ thống not working function maximize(). Giải pháp là sử dụng set size cho màn hình            driver.manage().window().setSize(new Dimension(1440, 900));
            boolean isContainsElement = driver.findElements(By.xpath(xpath)).size() != 0 ? true : false;
            if (isContainsElement) {
                break;
            }
        }
    }

    public void quitDriver(WebDriver driver) {
        if (!driver.toString().contains("null")) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

    public void selectOptionFromCombobox(WebElement element, String text) {
        Select drpCountry = new Select(element);
        drpCountry.selectByVisibleText(text);
    }

    public static void waitForElementVisible(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementVisible(WebDriver driver, String xpath) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public static void sendKeyToElement(WebDriver driver, WebElement element, String text) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
    }

    public void senkeysAndSubmitToElement(WebDriver driver,WebElement element,String text) throws InterruptedException {
        Actions action = new Actions(driver);
        action.sendKeys(element, text).build().perform();
        Thread.sleep(2000);
        action.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
    }

    public static WebElement getWebElement(WebDriver driver,By by) {
        return driver.findElement(by);
    }
    public static boolean moveToElement(WebDriver driver,By toElement) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(getWebElement(driver,toElement)).release(getWebElement(driver,toElement)).build().perform();
            return true;
        } catch (Exception e) {
            Logger.info(e.getMessage());
            return false;
        }
    }

    public static Boolean checkElementExist(WebDriver driver,By by) throws InterruptedException {
        Thread.sleep(2);
        List<WebElement> listElement = driver.findElements(by);

        if (listElement.size() > 0) {
            System.out.println("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            System.out.println("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    public static String getElementText(WebDriver driver,By by) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(2);
        String text = driver.findElement(by).getText();
        Logger.info("Get text: " + text);
        return text; //Trả về một giá trị kiểu String
    }

    public static void setText(WebDriver driver,By by, String value) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        sleep(2);
        driver.findElement(by).sendKeys(value);
        Logger.info("Set text: " + value + " on element " + by);
    }

    public static void removeAttribute(WebDriver driver,WebElement element,String attribute) throws InterruptedException {
        waitElementToBeClickable(driver, element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('"+ attribute+ "');", element);
        Thread.sleep(1500);
    }

    // use in case element readonly, disabled in html (also can be used driver.findElement(By.xpath("")).getAttribute("value");)
    public static String getValueElement(WebDriver driver,WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String elementText = (String) js.executeScript("return arguments[0].value;", element);
        return elementText;
    }
    public static void waitForElementVisible(WebDriver driver,By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            Logger.info("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementVisible(WebDriver driver,By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            Logger.info("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForElementPresent(WebDriver driver,By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            Logger.info("Element not exist. " + by.toString());
        }
    }


    public static void waitForElementPresent(WebDriver driver,By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            Logger.info("Element not exist. " + by.toString());
        }
    }

    public static void waitElementToBeClickable(WebDriver driver,WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForElementClickable(WebDriver driver,By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver,by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            Logger.info("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementClickable(WebDriver driver,By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(driver,by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            Logger.info("Timeout waiting for the element ready to click. " + by.toString());
        }
    }
    public void waitStalenessOfElement(WebDriver driver,String xpath,int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public static void implicitlyWait(WebDriver driver,int timeOut) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }
    public static void pageLoadTimeout(WebDriver driver,int timeOut) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
    }
}
