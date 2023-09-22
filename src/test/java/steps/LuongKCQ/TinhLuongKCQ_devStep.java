package steps.LuongKCQ;

import Bussiness.TinhLuongKcq_devBusiness;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class TinhLuongKCQ_devStep {
    private Actor auto = Actor.named("Auto");
    private String ObjIdNo;
    private JsonPath responeEmp;
    private String startDate;
    private String endDate;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    @Given("Get Organization {} from SAP")
    public void getOrg(String orgId) {
//        String date1 = "30-06-2023";
//        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate d1 = LocalDate.parse(date1, df);
//
//        Date date = Date.from(d1.atStartOfDay(ZoneId.systemDefault()).toInstant());
//
//        String dayOfWeek = new SimpleDateFormat("EEEE").format(date);
//        System.out.println("test: " + dayOfWeek);

        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        ObjIdNo = luong.callApiGetOrg(orgId);
    }

    @When("Get Employee from SAP with {}")
    public void getEmp(String date) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        responeEmp = luong.callApiGetEmp(date, ObjIdNo);
    }

    @Then("Write manv, tennv into excel import file {}")
    public void writeStaffInfo(String fileInput) throws Exception {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.writeStaffInfo(responeEmp,fileInput);
    }

    @Then("Verify sync employee from SAP to database of {}")
    public void verifySync(String periodId) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.verifySync(responeEmp, startDate, endDate, periodId);
    }

    @And("Sync organizations with {}")
    public void syncOrg(String periodId) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.callApiSyncOrg(periodId, ObjIdNo);
    }

    @And("Sync employee with {}")
    public void syncEmp(String periodId) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.callApiSyncEmp(periodId, ObjIdNo);
    }

    @And("Get start_date and end_date of {}")
    public void getDatePeriod(String periodId) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        startDate = luong.getDatePeriod(periodId, "start_date");
        endDate = luong.getDatePeriod(periodId, "end_date");
    }


    @Then("Call api import {} with fileName {}")
    public void callApiImport(String periodId,String fileName) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.callApiImport(fileName,ObjIdNo,periodId);
    }

    @And("Verify import employee to database with {} {}")
    public void verifyImport(String fileName,String periodId) throws Exception {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.verifyImportInfo(responeEmp,fileName,periodId);
    }

    @Then("Call api KHM import {} with fileName {}")
    public void callApiImportKHM(String periodId,String fileName) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.callApiImportKHM(fileName,ObjIdNo,periodId);
    }

    @And("Call api arrears-salary import {} with fileName {}")
    public void callApiImportSalary(String periodId,String fileName) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.callApiImportSalary(fileName,ObjIdNo,periodId);
    }

    @Then("Verify import KHM to database with {} {}")
    public void verifyImportKHM(String fileName,String periodId) throws Exception {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.verifyImportKHM(responeEmp,fileName,periodId);
    }

    @Then("Call api create period {} {} {}")
    public void callApiCreatePeriod(String namePeriod, String startDate, String endDate) {
        TinhLuongKcq_devBusiness luong = new TinhLuongKcq_devBusiness();
        luong.callApiCreatePeriod(namePeriod, startDate, endDate);
    }
}
