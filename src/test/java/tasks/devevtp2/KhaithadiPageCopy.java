package tasks.devevtp2;

import common.CommonWebController;
import common.HashMapExtend;
import common.Utilities;
import helpers.PropertiesManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KhaithadiPageCopy extends CommonWebController {

    private WebDriver driver;
    @FindBy(xpath = "//*[contains(@placeholder,'Nhập số điện thoại')]")
    WebElement inputUserName;

    //    private final By inputUserName = By.xpath("//*[contains(@placeholder,'Nhập số điện thoại')]");
//    private final By btnNhapOtp = By.xpath("//button[text()='Nhận mã OTP']");
//    private final By inputUserPass = By.xpath("//*[contains(@placeholder,'Nhập mã OTP')]");
//    private final By btnLogin = By.xpath("//button[text()='Đăng nhập']");
//        private final By buucucTextbox = By.xpath("//input[@placeholder='Chọn bưu cục']");
    @FindBy(xpath = "//button[text()='Nhận mã OTP']")
    WebElement btnNhapOtp;
    @FindBy(xpath = "//*[contains(@placeholder,'Nhập mã OTP')]")
    WebElement inputUserPass;
    @FindBy(xpath = "//button[text()='Đăng nhập']")
    WebElement btnLogin;
    @FindBy(xpath = "//input[@placeholder='Chọn bưu cục']")
    WebElement buucucTextbox;

    @FindBy(xpath = "//input[@placeholder='Chọn bưu cục']//following-sibling::a[@class='z-bandbox-button']")
    WebElement btnSearch;
    @FindBy(xpath = "//div[text()='TN2']")
    WebElement tn2;

    @FindBy(xpath = "//div[@class='z-menubar z-menubar-vertical']//following::li[@title='Khai thác đi' and @class='z-menu']")
    WebElement KHAITHACDI_LABLE;
    @FindBy(xpath = "//div[@class='z-menubar z-menubar-vertical']//following::li[@title='Khai thác đi' and @class='z-menuitem']")
    WebElement NHAPPHIEUGUI_LABLE;

    @FindBy(xpath = "//*[@title='Tên công ty']//preceding::input[@placeholder='Nhập mã khách hàng...']")
    WebElement MAKHACHHANG_TEXTBOX;

    @FindBy(xpath = "//*[@title='Tên công ty']//preceding::input[@placeholder='Nhập mã khách hàng...']//following::button[text()='Tìm kiếm'][1]")
    WebElement btnTimkiem;

    @FindBy(xpath = "//input[@placeholder='Tỉnh/TP']//ancestor::div[@class='z-div']//preceding::div[@class='z-div'][1]/preceding::input[2]")
    WebElement inputSdtNN;

    @FindBy(xpath = "//input[@placeholder='Tỉnh/TP']//ancestor::div[@class='z-div']/preceding::input[1]")
    WebElement inputNameNN;

    @FindBy(xpath = "//input[@placeholder='Tỉnh/TP']")
    WebElement tinhtpNN;

    @FindBy(xpath = "//input[@placeholder='Chọn Quận/Huyện']")
    WebElement quanhuyenNN;

    @FindBy(xpath = "//input[@placeholder='Chọn Phường/Xã']")
    WebElement phuongxaNN;

    @FindBy(xpath = "//input[@placeholder='Chọn Đường/Thôn/Xóm']")
    WebElement thonxomNN;

    @FindBy(xpath = "//input[@placeholder='Nhập chi tiết số nhà/ngõ/ngách.....']")
    WebElement sonhaNN;

    @FindBy(xpath = "//div[text()='Loại hàng hóa' and @class='z-caption-content']//following::span[text()='Trọng lượng'][1]//following::input[1]")
    WebElement trongluong;

    @FindBy(xpath = "//div[@class='z-div' and @style='max-height:150px;overflow-x:hidden;overflow-y:auto;margin-top:5px;']//child::div[@class='row z-div']//child::span[@title='Đồng kiểm']")
    WebElement dvct;
    @FindBy(xpath = "//input[@placeholder='Tỉnh/TP']//following::a[@class='z-combobox-button'][1]")
    WebElement checkboxTinhtpNN;

    @FindBy(xpath = "//div[@class='z-combobox-popup   z-combobox-open z-combobox-shadow']//child::ul//child::li")
    List<WebElement> listTinhtpNN;

    public KhaithadiPageCopy(WebDriver dr) {
        this.driver = dr;
        String baseUrl = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.url");
        driver = initDriverTest(baseUrl);
        PageFactory.initElements(driver, this);
    }

    public WebElement getDynamicEmement(String ColummName) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='" + ColummName +"']"));
        return element;
    }

    public void typeInputContract() throws InterruptedException {
        //clear attribute
        waitElementToBeClickable(driver, buucucTextbox);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].removeAttribute('value');", buucucTextbox);
        Thread.sleep(1500);

        //senkeys buu cuc
        String buucuc = PropertiesManager.getEnvironmentSpecFromProperty("auth.postoffice");
        buucucTextbox.sendKeys(buucuc);

        //click search button, and choose tn2
        btnSearch.click();
        tn2.click();

        //click khaithacdi/khaithacdi
        waitStalenessOfElement(driver, "//div[@class='z-menubar z-menubar-vertical']//following::li[@title='Khai thác đi' and @class='z-menu']", 30);
        KHAITHACDI_LABLE.click();
        waitForElementVisible(driver, "//div[@class='z-menubar z-menubar-vertical']//following::li[@title='Khai thác đi' and @class='z-menuitem']");
        NHAPPHIEUGUI_LABLE.click();

        //input thong tin nguoi gui
        MAKHACHHANG_TEXTBOX.sendKeys("82");
        btnTimkiem.click();

        //input thong tin nguoi nhan
        inputSdtNN.sendKeys("0123456789");
        Thread.sleep(30);
        senkeysAndSubmitToElement(driver, inputNameNN, "Nguyễn Ngọc Anh");

        //chon dia chi nguoi nhan
        senkeysAndSubmitToElement(driver, tinhtpNN, "THÀNH PHỐ HỒ CHÍ MINH");
        senkeysAndSubmitToElement(driver, quanhuyenNN, "QUẬN 1");
        senkeysAndSubmitToElement(driver, phuongxaNN, "PHƯỜNG BẾN NGHÉ");
        senkeysAndSubmitToElement(driver, thonxomNN, "Đ.Hai Bà Trưng");
        senkeysAndSubmitToElement(driver, sonhaNN, "Số 2");

        //input trong luong
        senkeysAndSubmitToElement(driver, trongluong, "250");

        //select dich vu cong them
        if (dvct.isSelected()==false) {
            dvct.click();
        }
    }

    public void signin() {
        String username = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.username");
        String password = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.password");
        inputUserName.sendKeys(username);
        btnNhapOtp.click();
        inputUserPass.sendKeys(password);
        btnLogin.click();
    }

    public void nhapphieudi() throws InterruptedException {
        signin();
        typeInputContract();
    }

//    public void typeInputContract() {
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("nguoinhanname","Tên người nhận");
//
//        Object kt = Utilities.getObjectFromString("KhaithacdiPageJsonFile",map);
//
//
//
//        Map<String, String> map1 = new HashMap<>();
//        Utilities utilities = new Utilities();
//        map1 = utilities.getHashMapFromString(kt.toString());
//        for (Map.Entry<String, String> entry : map1.entrySet()) {
//            if (entry.getValue().toString().contains("${{")) {
//                continue;
//            }
//            getDynamicEmement(entry.getKey()).clear();
//            getDynamicEmement(entry.getKey()).sendKeys(entry.getValue().toString());
//        }
//    }

    public void typeInputContract(Map< String, String > map, String option) {
        Map < String, String > mapForReplaceJSONFile = new HashMap< >();

        Utilities utilities = new Utilities();
        HashMapExtend< String, Object > dataDeal = new HashMapExtend < > ();

        mapForReplaceJSONFile.putAll(map);
        dataDeal = utilities.getHashMapFromString("t24web/updateCusT24.json", mapForReplaceJSONFile);

        if (option.equals("full")) {
            for (Map.Entry < String, Object > entry: dataDeal.entrySet()) {
                if (entry.getValue().toString().contains("${{")) {
                    continue;
                }
                getDynamicEmement(entry.getKey()).clear();
                getDynamicEmement(entry.getKey()).sendKeys(entry.getValue().toString());
            }
        }

    }


}
