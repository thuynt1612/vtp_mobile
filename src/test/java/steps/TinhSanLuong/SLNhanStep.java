package steps.TinhSanLuong;

import Bussiness.TinhSLNhanBusiness;
import common.Logger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;
import tasks.CreateBillBussiness.CreateBillBussinessTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SLNhanStep {
    private Actor auto = Actor.named("Auto");
    private JsonPath responseCreateBill;
    
    private String cod;
    private String ncod;
    private String qte;
    private float slOne;
    private float sl5kg;
    private String g00;

    private ArrayList<String> list1 = new ArrayList<String>();
    ;
    private ArrayList<String> list2 = new ArrayList<String>();
    private ArrayList<String> list3 = new ArrayList<String>();
    private ArrayList<String> list4= new ArrayList<String>();

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("Tinh san luong nhan don COD tu {} den {} buu cuc {}")
    public void tinhCod(String startDate, String endDate, String buuCuc) {
        cod = TinhSLNhanBusiness.tinhCod(startDate, endDate, buuCuc);

        //api
//        auto.attemptsTo(CreateBillBussiness.callApiCreateBill());
//        responseCreateBill = Serenity.sessionVariableCalled("responseCreateBill");
//        System.out.print("ma phieu gui la:" + responseCreateBill.get("data.ma_phieugui"));

        //web
//        CreateBillTask createBill = new CreateBillTask(driver);
//        createBill.clickLogin();
    }

    @When("Tinh san luong nhan don NCOD tu {} den {} buu cuc {}")
    public void tinhSlGncod(String startDate, String endDate, String buuCuc) {
        ncod = TinhSLNhanBusiness.tinhNCod(startDate, endDate, buuCuc);
    }

    @Then("Tinh san luong nhan don QTE tu {} den {} buu cuc {}")
    public void tinhSlGqte(String startDate, String endDate, String buuCuc) {
        qte = TinhSLNhanBusiness.tinhQte(startDate, endDate, buuCuc);
    }

    @Given("Tinh san luong nhan thanh cong don COD")
    public void tinhSlGcod1(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list1.add(TinhSLNhanBusiness.tinhSlGcod(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
    }

    @Given("Tinh san luong nhan thanh cong don NCOD")
    public void tinhSlGncod1(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list2.add(TinhSLNhanBusiness.tinhSlGncod(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
    }

    @Given("Tinh san luong nhan thanh cong don QTE")
    public void tinhSlGqte1(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list3.add(TinhSLNhanBusiness.tinhSlGqte(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
        System.out.println("size: " + list3.size());
    }

    @And("Tinh san luong nhan G02")
    public void tinhG02() {
        String a = ncod.replace(",", ".");
        float test = Float.parseFloat(a);
        slOne = TinhSLNhanBusiness.g02(Float.parseFloat(cod), Float.parseFloat(ncod.replace(",", ".")), Float.parseFloat(qte));
    }

    @And("Tinh san luong nhan G00 tu {} den {} buu cuc {}")
    public void tinhG00(String startDate, String endDate, String buuCuc) {
        g00 = TinhSLNhanBusiness.tinhG00(startDate,endDate,buuCuc);
    }
    @And("Tinh san luong nhan thanh cong cua vung {}")
    public void tinhSl1Vung(float heSo) {
        slOne = TinhSLNhanBusiness.sl1vung(list1, list2, list3, heSo);
        for (int i = 0; i < list1.size(); i++) {
            Logger.info("Sản lượng COD của vùng " + i + "là: " + list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            Logger.info("Sản lượng NCOD của vùng " + i + "là: " + list2.get(i));
        }
        for (int i = 0; i < list3.size(); i++) {
            Logger.info("Sản lượng QTE của vùng " + i + "là: " + list3.get(i));
        }
        Logger.info("sản lượng nhận theo vùng và loại là: " + slOne);
    }

    @Given("Tinh san luong nhan G03 tu {} den {} buu cuc {}")
    public void tinhG03(String startDate, String endDate, String buuCuc) {
        cod = TinhSLNhanBusiness.tinhG03(startDate, endDate, buuCuc);
    }

    @Given("Tinh san luong nhan G04 tung vung")
    public void tinhG04V(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list4.add(TinhSLNhanBusiness.tinhG04V(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
    }

    @Given("Tinh san luong nhan G04 with {}")
    public void tinhG04(float heSo) {
        sl5kg = TinhSLNhanBusiness.tinhG04(list4,heSo);
        Logger.info("sản lượng nhận trên 5kg theo vùng là: " + sl5kg);
    }
}
