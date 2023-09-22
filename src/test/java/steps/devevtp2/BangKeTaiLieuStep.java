package steps.devevtp2;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import tasks.devevtp2.KhaithadiPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BangKeTaiLieuStep {
    @Managed(uniqueSession = true)
    public WebDriver driver;
    public Actor auto = Actor.named("Auto");

    @Before
    public void openBrowse() {
        auto.can(BrowseTheWeb.with(driver));
    }

    @Given("Nhap phieu di")
    public void inputPhieu(DataTable table) throws InterruptedException {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            Map<String, String> map = new HashMap<>();
            map.put("city", columns.get("city"));
            map.put("district", columns.get("district"));
            map.put("wards", columns.get("wards"));
            map.put("no", columns.get("no"));

            KhaithadiPage khaithacdi = new KhaithadiPage(driver);
            khaithacdi.nhapphieugui(map,columns.get("maKhNG"),columns.get("sdtNN"),columns.get("tenNN"),columns.get("trongluong"),columns.get("LoaiBP/BK"));
        }
    }
}
