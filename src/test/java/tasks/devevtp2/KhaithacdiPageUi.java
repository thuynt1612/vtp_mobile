package tasks.devevtp2;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class KhaithacdiPageUi {
    public static final Target KHAITHACDI_LABLE = Target.the("Khai thac di lable")
            .located(By.xpath("//*[text()='Điều hành']//following::span[text()='Khai thác đi'][1]"));
    public static final Target NHAPPHIEUGUI_LABLE = Target.the("Nhap phieu gui lable")
            .located(By.xpath("//*[text()='Điều hành']//following::span[text()='Khai thác đi'][2]"));
    public static final Target BUUCUC_TEXTBOX = Target.the("Buu cuc textbox")
            .located(By.xpath("//input[@placeholder='Chọn bưu cục']"));

    public static final Target BUUCUCTN2= Target.the("Buu cuc textbox")
            .located(By.xpath("//div[text()='TN2']"));
    public static final Target SEARCH_BUUCUC_BUTTON =  Target.the("search buu cuc textbox")
            .located(By.xpath("//input[@placeholder='Chọn bưu cục']//following-sibling::a"));
    public static final Target MAKHACHHANG_TEXTBOX = Target.the("Buu cuc textbox")
            .located(By.xpath("//*[@title='Tên công ty']//preceding::input[@placeholder='Nhập mã khách hàng...']"));

    public static final Target CHONTHONXOM_TEXTBOX = Target.the("Thon xom textbox")
            .located(By.xpath("//span[text()='Đường/Thôn/Xóm']//following::input[@placeholder='Chọn Đường/Thôn/Xóm']"));

    public static final Target DIACHI_TEXTBOX = Target.the("Dia chi textbox")
            .located(By.xpath("//span[text()='Địa chỉ']//preceding::input[@placeholder='Nhập địa chỉ chi tiết']"));
}
