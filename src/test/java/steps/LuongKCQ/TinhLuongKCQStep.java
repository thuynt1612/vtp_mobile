package steps.LuongKCQ;

import Bussiness.TinhLuongKcqBusiness;
import helpers.ExcelHelpers;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.json.JSONObject;

public class TinhLuongKCQStep {
    private String doituong;
    private float ki;
    private float ngaycongtt;
    private float ngaynghicoluong;
    private float ngaytruc;
    private float luongcung;
    private float tylehuong;
    private float ngaycongchedo;
    private float kpi;
    private float namthamnien;
    private float luongCb;
    private float ngaycongtinhluong;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("Read Data from excel file {}")
    public void readData(String fileName) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;

        // Setup đường dẫn của file excel
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");


        // Đọc data từ file excel
        for (int i = 1; i < 3; i++) {
            System.out.println("Manv la: " + excel.getCellData("Manv", i));
            System.out.println("Luong co so la: " + excel.getCellData("Luongcoso", i));

            luongCb = Float.parseFloat(excel.getCellData("Luongcoso", i));
            System.out.println("Luong co ban la " + luongCb);
        }
    }

    @Given("Ghi manv tu {} vao {}")
    public void writeManv(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.writeStaffInfo(fileInput,fileOutput);
    }

    @Given("Tinh luong Khoi co quan {} {} va verify luong output")
    public void tinhLuongCung(String fileName,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhLuongcung(fileName,fileOutput);
    }

    @Then("Tinh luong tham nien {} {} va verify luong output")
    public void luongThamNien(String fileName,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhLuongthamnien(fileName,fileOutput);
    }

    @Then("Tinh luong le phep {} {} va verify luong output")
    public void luongLePhep(String fileName,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhLuonglephep(fileName,fileOutput);
    }

    @And("Tinh luong boi duong truc le {} {} va verify luong output")
    public void luongBoiDuong(String fileName,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhLuongBoiduong(fileName,fileOutput);
    }

    @Then("Tinh tong luong {} {} va verify luong output")
    public void tongluong(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhTongLuong(fileInput,fileOutput);
    }

    @And("Tinh thue thu nhap ca nhan {} {} va verify luong output")
    public void thuethunhapCn(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhThue(fileInput,fileOutput);
    }

    @Then("Tinh phu cap doan the {} {} va verify luong output")
    public void phucapdoanthe(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhPhucapdoanthe(fileInput,fileOutput);
    }
    @Then("Tinh phu cap an ca {} {} va verify luong output")
    public void phucapanca(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhPhucapAnca(fileInput,fileOutput);
    }
    @Then("Tinh phu cap dien thoai {} {} {} va verify luong output")
    public void phucapdienthoai(String fileInput,String fileOutput,String filePhucapdt) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhPhucapDienthoai(fileInput,fileOutput,filePhucapdt);
    }
    @Then("Tinh tong phu cap {} {} va verify luong output")
    public void tongphucap(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhTongphucap(fileInput,fileOutput);
    }

    @Then("Tinh luong con lai {} {} va verify luong output")
    public void luongconlai(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhLuongconlai(fileInput,fileOutput);
    }

    @Then("Tinh tong thu nhap {} {} va verify luong output")
    public void tongthunhap(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhTongthunhap(fileInput,fileOutput);
    }

    @Then("Tinh so tien con lai {} {} va verify luong output")
    public void sotienconlai(String fileInput,String fileOutput) throws Exception {
        TinhLuongKcqBusiness luong = new TinhLuongKcqBusiness();
        luong.tinhSotienconlai(fileInput,fileOutput);
    }
}
