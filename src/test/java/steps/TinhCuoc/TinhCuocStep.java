package steps.TinhCuoc;

import bll.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.path.json.JsonPath;
import model.PhieuGuiModel;
import model.TinhCuocModel;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Switch;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.Assert;
import tasks.Common.CallAPICommon;
import tasks.TinhCuocBussiness.TinhCuocBussinessTask;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TinhCuocStep {
    private Actor auto = Actor.named("Auto");
    private JsonPath responseTinhCuoc;
    private ArrayList<TinhCuocModel> model = new ArrayList<>();
    private int gia;
    private int giadvct;
    private float gia_line;
    private float gia_dvct;
    private String error;
    private int tluong;
    private ArrayList<PhieuGuiModel> listPgModel = new ArrayList<>();
    private ArrayList<PhieuGuiModel> listNPgModel = new ArrayList<>();
    private ArrayList<Float> listGia = new ArrayList<Float>();
    private ArrayList<Float> listGiadvct = new ArrayList<Float>();
    private float tongdvct = 0;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("Get data bill tu database")
    public void getDataBill(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            listPgModel = PHIEUGUIbll.getInfoByBuucucMadvAndDate(columns.get("startDate"), columns.get("endDate"), columns.get("buucuc"));
            listNPgModel = PHIEUGUIbll.getInfoByNotMadvAndDate(columns.get("startDate"), columns.get("endDate"), columns.get("buucuc"));
            System.out.println("abc: " + listNPgModel.get(0).tongtien);
        }
    }

    @Given("Tinh gia dich vu chinh")
    public void requestCuoc(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> map = new HashMap<>();

        for (Map<String, String> columns : rows) {
            map.put("loai_hh", columns.get("loai_hh"));
            map.put("ma_dv", columns.get("ma_dv"));
            map.put("ma_dv_congthem", columns.get("ma_dv_congthem"));
            map.put("thu_ho", columns.get("thu_ho"));
            map.put("tien_kg", columns.get("tien_kg"));
            map.put("trong_luong", String.valueOf(tluong));
            map.put("vung_phat", columns.get("vung_phat"));
            map.put("vung_phat_h", columns.get("vung_phat_h"));
            auto.attemptsTo(TinhCuocBussinessTask.callApiTinhCuoc(map));
            responseTinhCuoc = Serenity.sessionVariableCalled("responseTinhCuoc");

            String name = columns.get("ma_dv");
            List<Map> books = responseTinhCuoc.param("name", name).get("data.findAll { data -> data.ma_dv == name }");
            error = responseTinhCuoc.getString("error");
            if (error.equals("false")) {
                gia = Integer.parseInt(books.get(0).get("gia").toString());
            }

            String dvctname = columns.get("ma_dv_congthem");
            List<Map> listdvct = responseTinhCuoc.param("dvctname", dvctname).get("data.findAll { data -> data.ma_dv == dvctname }");
            error = responseTinhCuoc.getString("error");
            if (error.equals("false")) {
                giadvct = Integer.parseInt(listdvct.get(0).get("gia").toString());
            }
            List<Integer> list = new ArrayList<>();
            list.add(gia);
            list.add(giadvct);
            Serenity.setSessionVariable("getcuocapi").to(list);
        }
    }

    @When("Tinh gia doanh thu cac dich vu VCBA,VCBO,VBK")
    public void callApiTinhGia() {
        for (int i = 0; i < listPgModel.size(); i++) {
            Map<String, String> map = new HashMap<>();
            map.put("loai_hh", listPgModel.get(i).loai_hh);

            String tinh_buucucgoc = DM_TINH_BUUCUCbll.getTinhByBuucuc(listPgModel.get(i).buucuc_goc);
            String tinh_buucucphat = DM_TINH_BUUCUCbll.getTinhByBuucuc(listPgModel.get(i).buucuc_phat);

            String namedv = "";
            if (listPgModel.get(i).ma_dv.equals("VCBA")) {
                map.put("ma_dv", "NCOD");
                namedv = "NCOD";
            } else if (listPgModel.get(i).ma_dv.equals("VBK")) {
                map.put("ma_dv", "LCOD");
                namedv = "LCOD";
            } else if (listPgModel.get(i).ma_dv.equals("VCBO") && tinh_buucucgoc.equals(tinh_buucucphat) == true) {
                map.put("ma_dv", "PHS");
                namedv = "PHS";
            } else if (listPgModel.get(i).ma_dv.equals("VCBO") && tinh_buucucgoc.equals(tinh_buucucphat) == false) {
                map.put("ma_dv", "LCOD");
                namedv = "LCOD";
            }
            map.put("ma_dv_congthem", listPgModel.get(i).ma_dv_congthem);
            map.put("thu_ho", String.valueOf(listPgModel.get(i).thu_ho));
            map.put("tien_kg", String.valueOf(listPgModel.get(i).tien_kg));
            map.put("trong_luong", String.valueOf(listPgModel.get(i).trong_luong));
            map.put("vung_phat", listPgModel.get(i).vung_phat);
            map.put("vung_phat_h", DM_QUANHUYEN_BUUCUCbll.getQuanhuyenByBuucuc(listPgModel.get(i).buucuc_phat));
            auto.attemptsTo(TinhCuocBussinessTask.callApiTinhCuoc(map));
            responseTinhCuoc = Serenity.sessionVariableCalled("responseTinhCuoc");

            List<Map> books = responseTinhCuoc.param("name", namedv).get("data.findAll { data -> data.ma_dv == name }");
            error = responseTinhCuoc.getString("error");
            if (error.equals("false")) {
                gia_line = Float.parseFloat(books.get(0).get("gia").toString());
            }
            listGia.add(gia_line);

            String[] list_dvct = listPgModel.get(i).ma_dv_congthem.split(",");
            for (String item : list_dvct) {
                if (item.equals("") == false) {
                    List<Map> dvcts = responseTinhCuoc.param("name", item).get("data.findAll { data -> data.ma_dv == name }");
                    error = responseTinhCuoc.getString("error");
                    if (error.equals("false")) {
                        gia_dvct = Float.parseFloat(dvcts.get(0).get("gia").toString());
                    }
                    listGiadvct.add(gia_dvct);
                }
            }
        }
    }

    @Then("Tinh gia doanh thu cac dich vu con lai")
    public void doanhThudvkhac() {

    }

    @When("Gia cuoc config trong database")
    public void cuocDb(DataTable table) {
        String userid = USERSbll.getUserid(helpers.PropertiesManager.getAuthenUsername(), helpers.PropertiesManager.getAuthenPostOffice());
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            model = BG_BANGTINH_LINEbll.getGiaCuoc(columns.get("ma_dv"), DM_QUANHUYEN_BUUCUCbll.getqh(userid), columns.get("vung_phat_h"), columns.get("loai_hh"));
        }
    }

    @Given("Lay trong luong trong Database with case = {}")
    public void layTrongLuong(String tc) {
        List<TinhCuocModel> listnolk = model.stream().filter(x -> x.luy_ke.equals("0")).collect(Collectors.toList());
        List<TinhCuocModel> listlk = model.stream().filter(x -> !"0".equals(x.luy_ke)).collect(Collectors.toList());

        TinhCuocModel nolkminModel = listnolk.stream().min(Comparator.comparingInt(s -> s.muc_dau)).orElse(null);
        TinhCuocModel nolkmaxModel = listlk.stream().max(Comparator.comparingInt(s -> s.muc_cuoi)).orElse(null);

        switch (tc) {
            case "equalStart":
                tluong = listnolk.get(0).muc_dau;
                break;
            case "equalEnd":
                tluong = listnolk.get(0).muc_cuoi;
                break;
            case "betweenStartEnd":
                tluong = (int) (Math.random() * (listnolk.get(0).muc_cuoi - listnolk.get(0).muc_dau + 1) + listnolk.get(0).muc_dau);
                break;
            case "luyke":
                tluong = (int) (Math.random() * (listlk.get(0).muc_cuoi - listlk.get(0).muc_dau + 1) + listlk.get(0).muc_dau);
                break;
            case "lesserStart":

                tluong = nolkminModel.muc_dau - 1;
                break;
            case "biggerEnd":
                tluong = nolkmaxModel.muc_cuoi + 1;
                break;
        }
        Serenity.setSessionVariable("getTrongLuong").to(tluong);
    }

    @Then("Verify gia cuoc nhan duoc")
    public void verifyCuoc() {
        for (int i = 0; i < model.size(); i++) {
            if (tluong <= model.get(i).muc_cuoi && tluong >= model.get(i).muc_dau) {
                Assert.assertEquals(gia, model.get(i).gia_cuoc);
            }
        }
    }

    @Then("Tinh doanh thu theo gia cong bo")
    public void verifyCuocChinhVaCongThem() {
        long khac = 0;
        for (int i = 0; i < listNPgModel.size(); i++) {
            khac += listNPgModel.get(i).tongtien;
        }
        System.out.println("Doanh thu dich vu khac la : " + khac);

        long chinh = 0;
        for (int i = 0; i < listPgModel.size(); i++) {
            chinh += listGia.get(i);
        }
        System.out.println("Doanh thu dich vu chinh la : " + chinh);

        long dvct = 0;
        for (int j = 0; j < listGiadvct.size(); j++) {
            dvct += listGiadvct.get(j);
        }
        System.out.println("Doanh thu dich vu cong them la : " + dvct);

        long tonggia = khac + chinh + dvct;
        System.out.println("Doanh thu cong bo la : " + tonggia);
    }


    @Then("Verify gia cuoc co luy ke")
    public void verifyCuoclk() {
        List<TinhCuocModel> listlk = model.stream().filter(x -> !"0".equals(x.luy_ke)).collect(Collectors.toList());
        List<TinhCuocModel> listnolk = model.stream().filter(x -> x.luy_ke.equals("0")).collect(Collectors.toList());

        TinhCuocModel nolkModel = listnolk.stream().max(Comparator.comparingInt(s -> s.muc_cuoi)).orElse(null);

        Assert.assertTrue("Trong luong nam trong muc dau cuoi", listlk.get(0).muc_dau <= tluong && listlk.get(0).muc_cuoi >= tluong);

        //lay thuong
        int thg = (tluong - nolkModel.muc_cuoi) / Integer.parseInt(listlk.get(0).luy_ke);

        Assert.assertEquals(gia, nolkModel.gia_cuoc + listlk.get(0).gia_cuoc * thg);
    }

    @Then("Verify response co error = true")
    public void verifyError() {
        Assert.assertEquals(error, "true");
    }
}
