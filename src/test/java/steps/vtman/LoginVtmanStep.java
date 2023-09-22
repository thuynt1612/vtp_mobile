package steps.vtman;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import tasks.vtman.LoginPage;

import java.net.MalformedURLException;

public class LoginVtmanStep {

    @Managed(uniqueSession = true)
    public AppiumDriver driver;
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("Login Vtman app")
    public void login() throws MalformedURLException {
        LoginPage testapp = new LoginPage(driver);
        testapp.signin();
    }
}
