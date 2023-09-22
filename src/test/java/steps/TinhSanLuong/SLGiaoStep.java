package steps.TinhSanLuong;

import Bussiness.TinhSLGiaoBusiness;
import bll.VTP_KETQUA_KHOAN_LINEbll;
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

public class SLGiaoStep {
    private Actor auto = Actor.named("Auto");
    private JsonPath responseCreateBill;

    public WebDriver driver;
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

    @Given("Tinh san luong giao don COD tu {} den {} buu cuc {}")
    public void tinhCod(String startDate, String endDate, String buuCuc) {
        cod = TinhSLGiaoBusiness.tinhCod(startDate, endDate, buuCuc);

        //api
//        auto.attemptsTo(CreateBillBussiness.callApiCreateBill());
//        responseCreateBill = Serenity.sessionVariableCalled("responseCreateBill");
//        System.out.print("ma phieu gui la:" + responseCreateBill.get("data.ma_phieugui"));

        //web
//        CreateBillTask createBill = new CreateBillTask(driver);
//        createBill.clickLogin();
    }

    @When("Tinh san luong giao don NCOD tu {} den {} buu cuc {}")
    public void tinhSlGncod(String startDate, String endDate, String buuCuc) {
        ncod = TinhSLGiaoBusiness.tinhNCod(startDate, endDate, buuCuc);
    }

    @Then("Tinh san luong giao don QTE tu {} den {} buu cuc {}")
    public void tinhSlGqte(String startDate, String endDate, String buuCuc) {
        qte = TinhSLGiaoBusiness.tinhQte(startDate, endDate, buuCuc);
    }

//    @And("Tinh san luong giao thanh cong cua vung {} {}")
//    public void tinhSl1Vung(float heSo) {
//        String a = ncod.replace(",",".");
//        float test = Float.parseFloat(a);
//        slOne = TinhSLGiaoBusiness.sl1vung(Float.parseFloat(cod),Float.parseFloat(ncod.replace(",",".")),Float.parseFloat(qte),heSo);
//        TinhSLGiaoBusiness.saveTxtFile(String.valueOf(slOne));
//    }

    @Given("Nhap phieu gui trong nuoc")
    public void inputPhieuGui() {
        auto.attemptsTo(CreateBillBussinessTask.callApiCreateBill());
        responseCreateBill = Serenity.sessionVariableCalled("responseCreateBill");
        System.out.print("ma phieu gui la:" + responseCreateBill.get("data.ma_phieugui"));
    }

    @Given("Tinh san luong giao thanh cong don COD")
    public void tinhSlGcod1(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list1.add(TinhSLGiaoBusiness.tinhSlGcod(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
    }

    @Given("Tinh san luong giao thanh cong don NCOD")
    public void tinhSlGncod1(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list2.add(TinhSLGiaoBusiness.tinhSlGncod(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
    }

    @Given("Tinh san luong giao thanh cong don QTE")
    public void tinhSlGqte1(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list3.add(TinhSLGiaoBusiness.tinhSlGqte(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
        System.out.println("size: " + list3.size());
    }

    @And("Tinh san luong G02")
    public void tinhG02() {
        String a = ncod.replace(",", ".");
        float test = Float.parseFloat(a);
        slOne = TinhSLGiaoBusiness.g02(Float.parseFloat(cod), Float.parseFloat(ncod.replace(",", ".")), Float.parseFloat(qte));
    }

    @And("Tinh san luong G00 tu {} den {} buu cuc {}")
    public void tinhG00(String startDate, String endDate, String buuCuc) {
        g00 = TinhSLGiaoBusiness.tinhG00(startDate,endDate,buuCuc);
    }
    @And("Tinh san luong giao thanh cong cua vung {}")
    public void tinhSl1Vung(float heSo) {
        slOne = TinhSLGiaoBusiness.sl1vung(list1, list2, list3, heSo);
        for (int i = 0; i < list1.size(); i++) {
            Logger.info("Sản lượng COD của vùng " + i + "là: " + list1.get(i));
        }
        for (int i = 0; i < list2.size(); i++) {
            Logger.info("Sản lượng NCOD của vùng " + i + "là: " + list2.get(i));
        }
        for (int i = 0; i < list3.size(); i++) {
            Logger.info("Sản lượng QTE của vùng " + i + "là: " + list3.get(i));
        }
        Logger.info("sản lượng giao theo vùng và loại là: " + slOne);
    }

    @Given("Tinh san luong giao G03 tu {} den {} buu cuc {}")
    public void tinhG03(String startDate, String endDate, String buuCuc) {
        cod = TinhSLGiaoBusiness.tinhG03(startDate, endDate, buuCuc);
    }

    @Given("Tinh san luong giao G04 tung vung")
    public void tinhG04V(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            list4.add(TinhSLGiaoBusiness.tinhG04V(columns.get("Start_date"), columns.get("End_date"), columns.get("Buu_cuc"), columns.get("Ma_vung")));
        }
    }

    @Given("Tinh san luong giao G04 with {}")
    public void tinhG04(float heSo) {
        sl5kg = TinhSLGiaoBusiness.tinhG04(list4,heSo);
        Logger.info("sản lượng giao trên 5kg theo vùng là: " + sl5kg);
    }
}


