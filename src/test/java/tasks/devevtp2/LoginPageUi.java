package tasks.devevtp2;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;


public class LoginPageUi {
    public static final Target USERNAME_FIELD = Target.the("username textbox")
            .located(By.xpath("//*[contains(@placeholder,'Nhập số điện thoại')]"));
    public static final Target PASSWORD_FIELD = Target.the("otp textbox")
            .located(By.xpath("//*[contains(@placeholder,'Nhập mã OTP')]"));
    public static final Target OTP_BUTTON = Target.the("Otp button")
            .located(By.xpath("//button[text()='Nhận mã OTP']"));

    public static final Target LOGIN_BUTTON = Target.the("Login button")
            .located(By.xpath("//button[text()='Đăng nhập']"));
}
