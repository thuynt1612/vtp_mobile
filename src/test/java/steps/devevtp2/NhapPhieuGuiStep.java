package steps.devevtp2;

import helpers.PropertiesManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import tasks.devevtp2.KhaithadiPage;
import tasks.devevtp2.LoginPageOld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NhapPhieuGuiStep {
    @Managed(uniqueSession = true)
    public WebDriver driver;
    public Actor auto = Actor.named("Auto");

    @Before
    public void openBrowse() {
        auto.can(BrowseTheWeb.with(driver));
    }

    @Given("Login he thong")
    public void login() throws Exception {
        String username = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.username");
        String password = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.password");
        LoginPageOld login = new LoginPageOld(driver);
        login.signin(username, password);
//
//        String username = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.username");
//        String password = PropertiesManager.getEnvironmentSpecFromProperty("devevtp2.password");
//        auto.attemptsTo(
//                LoginPageTask.withUsername(username).andPassword(password)
//        );
//        Thread.sleep(5000);
    }

    @When("Nhap phieu gui")
    public void inputPhieu(DataTable table) throws InterruptedException {
//        auto.attemptsTo(
//                KhaithadiPageTask.inputPhieu()
//        );
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            Map<String, String> map = new HashMap<>();
            map.put("city", columns.get("city"));
            map.put("district", columns.get("district"));
            map.put("wards", columns.get("wards"));
            map.put("village", columns.get("village"));
            map.put("no", columns.get("no"));

            KhaithadiPage khaithadiPage = new KhaithadiPage(driver);
            khaithadiPage.nhapphieudi(map,columns.get("maKhNG"),columns.get("sdtNN"),columns.get("tenNN"));
        }
    }
}
