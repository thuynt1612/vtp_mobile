package tasks.devevtp2;

import common.CommonWebController;
import common.HashMapExtend;
import common.Utilities;
import helpers.PropertiesManager;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KhaithadiPage extends CommonWebController {

    private WebDriver driver;
    @FindBy(xpath = "//*[contains(@placeholder,'Nhập số điện thoại')]")
    WebElement inputUserName;

    //    private final By inputUserName = By.xpath("//*[contains(@placeholder,'Nhập số điện thoại')]");
//    private final By btnNhapOtp = By.xpath("//button[text()='Nhận mã OTP']");
//    private final By inputUserPass = By.xpath("//*[contains(@placeholder,'Nhập mã OTP')]");
//    private final By btnLogin = By.xpath("//button[text()='Đăng nhập']");
        private String KTD_LABLE = "//div[@class='z-menubar z-menubar-vertical']//following::li[@title='Khai thác đi' and @class='z-menu']";
        private String NPG_LABLE = "//div[@class='z-menubar z-menubar-vertical']//following::li[@title='Khai thác đi' and @class='z-menuitem']";
        private String cuocchinhxpath = "//span[text()='Cước chính']//following::input[1]";
        private String cuoccongthemxpath = "//span[text()='Cước chính']//following::input[3]";

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

    @FindBy(xpath = "//span[text()='Cước chính']//following::input[1]")
    WebElement cuocchinh;

    @FindBy(xpath = "//span[text()='Cước chính']//following::input[3]")
    WebElement cuoccongthem;
    @FindBy(xpath = "//textarea[@readonly='readonly']")
    WebElement dcchitiet;

    @FindBy(xpath = "//div[text()='Loại hàng hóa' and @class='z-caption-content']//following::span[text()='Trọng lượng'][1]//following::input[1]")
    WebElement trongluong;

    @FindBy(xpath = "//div[@class='z-div' and @style='max-height:150px;overflow-x:hidden;overflow-y:auto;margin-top:5px;']//child::div[@class='row z-div']//child::span[@title='Đồng kiểm']")
    WebElement dvct;

    @FindBy(xpath = "//textarea[@readonly='readonly']")
    WebElement dichichitiet;
    @FindBy(xpath = "//input[@placeholder='Tỉnh/TP']//following::a[@class='z-combobox-button'][1]")
    WebElement checkboxTinhtpNN;

    @FindBy(xpath = "//div[@class='z-combobox-popup   z-combobox-open z-combobox-shadow']//child::ul//child::li")
    List<WebElement> listTinhtpNN;

    @FindBy(xpath = "//div[text() = 'Dịch vụ' and @class='z-caption-content']/parent::div[@class='z-caption z-caption-readonly']/parent::div/parent::div/parent::div/parent::div/following-sibling::div//button[text()='Ghi lại']")
    WebElement btnGhiLai;

    @FindBy(xpath = "//div[text()='Loại hàng hóa' and @class='z-caption-content']//following::span[text()='Loại BP/BK'][1]//following::input[1]")
    WebElement loaiBL_BK;

    @FindBy(xpath = "//span[text()='Nội dung']//ancestor::table[@class='z-hbox']/parent::td/parent::tr/parent::tbody/tr[3]//textarea")
    WebElement textareaNoidung;

//    @FindBy(xpath = "//*[@id='nJsPfv1-cave']/li/a/span")
//    List<WebElement> listTabparent;

    public KhaithadiPage(WebDriver dr) {
        this.driver = dr;
        String baseUrl = helpers.PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.url");
        driver = initDriverTest(baseUrl);
        PageFactory.initElements(driver, this);
    }

    public WebElement getDynamicEmement(String ColummName) {
        WebElement element = driver.findElement(By.xpath("//input[@placeholder='" + ColummName + "']"));
        return element;
    }

    public void inputReceiverAddress(Map<String, String> map) throws InterruptedException {
        Map<String, String> mapForReplaceJSONFile = new HashMap<>();
        Utilities utilities = new Utilities();
        HashMapExtend<String, Object> dataDeal = new HashMapExtend<>();
        HashMapExtend<String, Object> dataDeal1 = new HashMapExtend<>();

        mapForReplaceJSONFile.putAll(map);
        dataDeal = utilities.getHashMapFromString("Khaithacdi/KhaithacdiPageJsonFile", mapForReplaceJSONFile);
        dataDeal1 = utilities.getHashMapFromString("Khaithacdi/KhaithacdiPageJson1File", mapForReplaceJSONFile);

        for (Map.Entry<String, Object> entry : dataDeal.entrySet()) {
            if (entry.getValue().toString().contains("${{")) {
                continue;
            }
            senkeysAndSubmitToElement(driver, getDynamicEmement(entry.getKey()), entry.getValue().toString());
        }

        for (Map.Entry<String, Object> entry : dataDeal1.entrySet()) {
            if (entry.getValue().toString().contains("${{")) {
                continue;
            }
            senkeysAndSubmitToElement(driver, getDynamicEmement(entry.getKey()), entry.getValue().toString());
        }
    }
    public void typeInputContract(Map<String, String> map,String makhNG,String sdtNN,String tenNN) throws InterruptedException {
        //clear attribute
        removeAttribute(driver,buucucTextbox,"value");

        //senkeys buu cuc
        String buucuc = PropertiesManager.getEnvironmentSpecFromProperty("auth.postoffice");
        buucucTextbox.sendKeys(buucuc);

        //click search button, and choose tn2
        btnSearch.click();
        tn2.click();

        //click khaithacdi/khaithacdi
        waitStalenessOfElement(driver, KTD_LABLE, 30);
        KHAITHACDI_LABLE.click();
        waitForElementVisible(driver, NPG_LABLE);
        NHAPPHIEUGUI_LABLE.click();

        //input thong tin nguoi gui
        MAKHACHHANG_TEXTBOX.sendKeys(makhNG);
        btnTimkiem.click();

        //input thong tin nguoi nhan
        inputSdtNN.sendKeys(sdtNN);
        Thread.sleep(30);
        senkeysAndSubmitToElement(driver, inputNameNN, tenNN);

        //chon dia chi nguoi nhan
        inputReceiverAddress(map);

        //input trong luong
        removeAttribute(driver,trongluong,"value");
        senkeysAndSubmitToElement(driver, trongluong, Serenity.sessionVariableCalled("getTrongLuong").toString());

        //select dich vu cong them
        if (dvct.isSelected() == false) {
            dvct.click();
        }
    }

    private void clearElement(WebElement element){
        element.click();
        element.clear();
    }

    private void typeInputContract(Map<String, String> map,String makhNG,String sdtNN,String tenNN, String trongLuong, String loaiBP_BK) throws InterruptedException {
        //clear attribute
        removeAttribute(driver,buucucTextbox,"value");

        //senkeys buu cuc
        String buucuc = PropertiesManager.getEnvironmentSpecFromProperty("auth.postoffice");
        buucucTextbox.sendKeys(buucuc);

        //click search button, and choose tn2
        btnSearch.click();
        tn2.click();

        //click khaithacdi/khaithacdi
        waitStalenessOfElement(driver, KTD_LABLE, 30);
        KHAITHACDI_LABLE.click();
        waitForElementVisible(driver, NPG_LABLE);
        NHAPPHIEUGUI_LABLE.click();

        //input thong tin nguoi gui
        MAKHACHHANG_TEXTBOX.sendKeys(makhNG);
        btnTimkiem.click();

        //input thong tin nguoi nhan
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", inputSdtNN);
        inputSdtNN.sendKeys(sdtNN);
        Thread.sleep(30);

        clearElement(tinhtpNN);
        clearElement(quanhuyenNN);
        clearElement(phuongxaNN);
        clearElement(sonhaNN);
        Thread.sleep(30);

        removeAttribute(driver,inputNameNN,"value");
        senkeysAndSubmitToElement(driver, inputNameNN, tenNN);

        //chon dia chi nguoi nhan
        inputReceiverAddress(map);

        //input trong luong
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", NHAPPHIEUGUI_LABLE);
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', arguments[1])", loaiBL_BK, loaiBP_BK);

        removeAttribute(driver,trongluong,"value");
        senkeysAndSubmitToElement(driver, trongluong, trongLuong);
        senkeysAndSubmitToElement(driver, textareaNoidung, "noidung");

        //select dich vu cong them
        if (dvct.isSelected() == false) {
            dvct.click();
        }
    }

    private void clickButtonGhiLai(){
        btnGhiLai.click();
    }

    public void verifyCuoc() throws InterruptedException {

        //cuon den cuoi trang
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", cuocchinh);
//        System.out.println("TEST: " + js.executeScript("return arguments[0].value;", dcchitiet));
//        js.executeScript("arguments[0].removeAttribute('disabled');", cuocchinh);
//        js.executeScript("arguments[0].removeAttribute('readonly');", cuocchinh);
//        System.out.println("TEST1: " + js.executeScript("return arguments[0].getAttribute('text');", cuocchinh));
//        System.out.println("TEST2: " + js.executeScript("return arguments[0].innerText;", cuocchinh));
//        System.out.println("TEST1: " + js.executeScript("return arguments[0].getAttribute('value');", cuocchinh));
//        System.out.println("TEST1: " + driver.findElement(By.xpath("//span[text()='Cước chính']//following::input[1]")).getAttribute("value"));

        //verify thong tin cuoc chinh
        Thread.sleep(500);
        String cuocchinhweb = driver.findElement(By.xpath(cuocchinhxpath)).getAttribute("value").replace(",","");

        System.out.println("gia trong web: " + cuocchinhweb);

        List<Integer> list = new ArrayList<>();
        list = Serenity.sessionVariableCalled("getcuocapi");

        int cuocchinh = list.get(0).intValue();
        System.out.println("gia trong api: " + cuocchinh);
        Assert.assertEquals( list.get(0).intValue(),Integer.parseInt(cuocchinhweb));

        //verify thong tin cuoc cong them
        String cuoccongthemweb = driver.findElement(By.xpath(cuoccongthemxpath)).getAttribute("value").replace(",","");
        System.out.println("gia trong web: " + cuoccongthemweb);

        int cuocdvct = list.get(1).intValue();
        System.out.println("gia dvct trong api: " + cuocdvct);
        Assert.assertEquals(cuocdvct,Integer.parseInt(cuoccongthemweb));
    }
    public void signin() {
        String username = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.username");
        String password = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.password");
        inputUserName.sendKeys(username);
        btnNhapOtp.click();
        inputUserPass.sendKeys(password);
        btnLogin.click();
    }

    public void nhapphieudi(Map<String, String> map,String makhNG,String sdtNN,String tenNN) throws InterruptedException {
        signin();
        typeInputContract(map,makhNG,sdtNN,tenNN);
        verifyCuoc();
    }

    public void nhapphieugui(Map<String, String> map, String makhNG, String sdtNN, String tenNN, String trongLuong, String loaiBP_BK) throws InterruptedException {
        signin();
        typeInputContract(map,makhNG,sdtNN,tenNN, trongLuong, loaiBP_BK);
        clickButtonGhiLai();
    }

//    private void clickTabparent(String tabName){
//        if(listTabparent == null || listTabparent.size() == 0){return;}
//        for (int i = 0; i < listTabparent.size(); i++){
//            if(tabName.equals(listTabparent.get(i).getText())){
//                listTabparent.get(i).click();
//            }
//        }
//    }
}
