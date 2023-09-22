package steps.TinhCuoc;

import bll.BG_BANGTINH_LINEbll;
import bll.BG_CUOC_CONGTHEMbll;
import bll.DM_QUANHUYEN_BUUCUCbll;
import bll.USERSbll;
import common.Utilities;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import model.TinhCuocModel;
import model.TinhDvctModel;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.Assert;
import questions.CommonQuestion;
import tasks.TinhCuocBussiness.TinhCuocBussinessTask;

import java.util.*;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class TinhCuocCongThemStep {
    private Actor auto = Actor.named("Auto");
    private ArrayList<TinhDvctModel> modelDvct = new ArrayList<>();
    private ArrayList<TinhCuocModel> model = new ArrayList<>();
    private ArrayList<TinhDvctModel> modelCod = new ArrayList<>();

    private JsonPath responseTinhDvct;
    private int tluong;
    private String error;
    private String gia;
    private String giadvc;
    private String ma_dvct;
    private String giatri;
    private String thu_ho;
    private String muc_dau;
    private String muc_cuoi;
    private String min;
    private String max;
    private String khai_gia;

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @When("Get trong luong trong database")
    public void cuocDb(DataTable table) {
        String userid = USERSbll.getUserid(helpers.PropertiesManager.getAuthenUsername(), helpers.PropertiesManager.getAuthenPostOffice());
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            model = BG_BANGTINH_LINEbll.getGiaCuoc(columns.get("ma_dv"), DM_QUANHUYEN_BUUCUCbll.getqh(userid), columns.get("vung_phat_h"), columns.get("loai_hh"));
        }
    }

    @Given("Get dvct va cach tinh dvct trong database")
    public void cachTinhDb(DataTable table) {
        String userid = USERSbll.getUserid(helpers.PropertiesManager.getAuthenUsername(), helpers.PropertiesManager.getAuthenPostOffice());
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            modelDvct = BG_CUOC_CONGTHEMbll.getGiaDvct(columns.get("ma_dv"), DM_QUANHUYEN_BUUCUCbll.getqh(userid), columns.get("vung_phat_h"), columns.get("loai_hh"));
            modelCod = BG_CUOC_CONGTHEMbll.getDvCOD(columns.get("ma_dv"), DM_QUANHUYEN_BUUCUCbll.getqh(userid), columns.get("vung_phat_h"), columns.get("loai_hh"));
        }
    }

    @Then("Lay gia tri with cach tinh {} {} {} {} {} trong database")
    public void getGiaTri(String testcase, String madv, String vung_phat_h, String hanghoa, String dec) {
        List<TinhDvctModel> dvctlist = modelDvct.stream().filter(x -> x.cach_tinh.equals(testcase)).collect(Collectors.toList());

        if (dvctlist.size() == 0) {
            List<String> list = new ArrayList<>();
            list.add("FF");
            list.add("CC");
            list.add("KG");
            list.add("TL");
            list.add("TH");
            list.add("TT");

            int i = 0;
            List<TinhDvctModel> listdv = new ArrayList<>();
            do {
                int finalI = i;
                listdv = modelDvct.stream().filter(x -> x.cach_tinh.equals(list.get(finalI))).collect(Collectors.toList());
                i++;
            } while (listdv.size() == 1);

            switch (testcase) {
                case "TH": {
                    if (modelCod.size() == 0) {
                        int insert = BG_CUOC_CONGTHEMbll.insertMadv(Utilities.getRandomNumber(7), testcase, "COD", listdv.get(1).ID_CUOC_CONGTHEM);
                        Assert.assertEquals(insert, 1);
                        int update = BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "", "10", "0", "0","", listdv.get(1).ID_CUOC_CONGTHEM);
                        Assert.assertEquals(update, 1);
                    }
                    break;
                }
                case "KG": {
                    int update = BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "100000", "900000", "10", "0", "0","", listdv.get(1).ID_CUOC_CONGTHEM);
                    Assert.assertEquals(update, 1);
                    break;
                }
                default:
                    int update = BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "", "10", "0", "0","", listdv.get(1).ID_CUOC_CONGTHEM);
                    Assert.assertEquals(update, 1);
                    break;
            }
        } else if (dec.equals("min")) {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "", "10", "5000", "0","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        } else if (dec.equals("max")) {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "", "10", "0", "5000","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        } else if (dec.equals("mucdau")) {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "900000", "", "10", "0", "0","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        } else if (dec.equals("muccuoi")) {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "900000", "10", "0", "0","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        } else if (dec.equals("KG")) {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "900000", "10", "0", "0","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        } else if (dec.equals("TT")) {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "", "10", "0", "0","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        } else {
            BG_CUOC_CONGTHEMbll.updateInfoById(testcase, "", "", "10", "0", "0","", dvctlist.get(0).ID_CUOC_CONGTHEM);
        }

        //get lai model dvct sau khi insert COD va update
        String userid = USERSbll.getUserid(helpers.PropertiesManager.getAuthenUsername(), helpers.PropertiesManager.getAuthenPostOffice());
        modelDvct = BG_CUOC_CONGTHEMbll.getGiaDvct(madv, DM_QUANHUYEN_BUUCUCbll.getqh(userid), vung_phat_h, hanghoa);

        dvctlist = modelDvct.stream().filter(x -> x.cach_tinh.equals(testcase)).collect(Collectors.toList());
        List<TinhDvctModel> dvctModel = dvctlist.stream().filter(x -> !"0".equals(x.gia_tri)).collect(Collectors.toList());
        muc_dau = dvctModel.get(0).muc_dau;
        muc_cuoi = dvctModel.get(0).muc_cuoi;
        min = dvctModel.get(0).min;
        max = dvctModel.get(0).max;
        switch (testcase) {
            case "FF": {
                ma_dvct = dvctModel.get(0).ma_dv_congthem;
                giatri = dvctModel.get(0).gia_tri;
                break;
            }
            default:
                ma_dvct = dvctModel.get(0).ma_dv_congthem;
                giatri = String.valueOf(Float.parseFloat(dvctModel.get(0).gia_tri) / 100);
                break;
        }
    }


    @When("Call api tinh cuoc")
    public void callApi(DataTable table) {
        List<TinhCuocModel> listnolk = model.stream().filter(x -> x.luy_ke.equals("0")).collect(Collectors.toList());
        tluong = (int) (Math.random() * (listnolk.get(0).muc_cuoi - listnolk.get(0).muc_dau + 1) + listnolk.get(0).muc_dau);

        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        Map<String, String> map = new HashMap<>();

        for (Map<String, String> columns : rows) {
            map.put("loai_hh", columns.get("loai_hh"));
            map.put("ma_dv", columns.get("ma_dv"));
            map.put("ma_dv_congthem", ma_dvct);
            map.put("thu_ho", columns.get("thu_ho"));
            map.put("tien_kg", columns.get("tien_kg"));
            map.put("trong_luong", String.valueOf(tluong));
            map.put("vung_phat", columns.get("vung_phat"));
            map.put("vung_phat_h", columns.get("vung_phat_h"));
            auto.attemptsTo(TinhCuocBussinessTask.callApiTinhCuoc(map));
            responseTinhDvct = Serenity.sessionVariableCalled("responseTinhCuoc");

            List<Map> books = responseTinhDvct.param("name", ma_dvct).get("data.findAll { data -> data.ma_dv == name }");
            error = responseTinhDvct.getString("error");
            if (error.equals("false")) {
                gia = books.get(0).get("gia").toString();
            }

            String dvc = columns.get("ma_dv");
            List<Map> chinh = responseTinhDvct.param("dvc", dvc).get("data.findAll { data -> data.ma_dv == dvc }");
            error = responseTinhDvct.getString("error");
            if (error.equals("false")) {
                giadvc = chinh.get(0).get("gia").toString();
            }
            thu_ho = columns.get("thu_ho");
            khai_gia = columns.get("tien_kg");
        }
    }

    @Then("Verify gia dich vu cong them voi cach tinh {}")
    public void verifyDvct(String testcase) {
        switch (testcase) {
            case "FF": {
                Assert.assertEquals(gia, giatri);
                break;
            }
            case "CC": {
                Assert.assertEquals(gia, String.valueOf(Float.parseFloat(giatri) * Float.parseFloat(giadvc)));
                break;
            }
            case "KG": {
                if (Float.parseFloat(giatri) * Float.parseFloat(khai_gia) < Float.parseFloat(min)) {
                    Assert.assertEquals(gia, min);
                } else if (Float.parseFloat(giatri) * Float.parseFloat(khai_gia) > Float.parseFloat(max)) {
                    Assert.assertEquals(gia, max);
                } else if (Float.parseFloat(khai_gia) <= Float.parseFloat(muc_dau) || Float.parseFloat(khai_gia) > Float.parseFloat(muc_cuoi)) {
                    Assert.assertEquals(gia, "0");
                } else {
                    Float giaVerify = Float.parseFloat(giatri) * Float.parseFloat(khai_gia);
                    Float giaV = Float.parseFloat(gia);
                    Assert.assertEquals(giaV, giaVerify);
                }
                break;
            }
            case "TH": {
                Float giaVerify = Float.parseFloat(giatri) * Float.parseFloat(thu_ho);
                Float giaV = Float.parseFloat(gia);
                Assert.assertEquals(giaV, giaVerify);
                break;
            }
            case "TL": {
                Float giaVerify = Float.parseFloat(giatri) * tluong;
                Float giaV = Float.parseFloat(gia);
                Assert.assertEquals(giaV, giaVerify);
                break;
            }
        }
    }
}
