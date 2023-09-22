package tasks.CreateBillBussiness;

import common.Utilities;
import config.constant;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import tasks.Common.CallAPICommon;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateBillBussinessTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static CreateBillBussinessTask callApiCreateBill()
    {
//        String authen = CallAPICommon.callAPIGetToken();
//        String url =  helpers.PropertiesManager.getCurrentBaseUrl();
//        String postoffice = helpers.PropertiesManager.getAuthenPostOffice();
//        String endPoint = constant.entry_phieugui;
//
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("postoffice",postoffice);
//        Object entry = Utilities.getObjectFromString("EntryPhieuGui",map);
//
//        JsonPath response = CommonApi.postApi(url+endPoint,entry,authen);
//
//        String maPhieuGui = response.getString("data.ma_phieugui");
//        String idPhieuGui = response.getString("data.id_phieugui");
//
//        Map<String, String> hashMap = new HashMap<String, String>();
//        hashMap.put("maPhieuGui",maPhieuGui);
//        hashMap.put("idPhieuGui",idPhieuGui);
//
//        Serenity.setSessionVariable("responseCreateBill").to(hashMap);


        String endPoint = constant.entry_phieugui;
        String postoffice = helpers.PropertiesManager.getAuthenPostOffice();

        Map<String, String> map = new HashMap<String, String>();
        map.put("postoffice",postoffice);
        Object entry = Utilities.getObjectFromString("EntryPhieuGui",map);

        JsonPath responseEntry = CallAPICommon.callApiPostRequest(endPoint,entry);
        Serenity.setSessionVariable("responseCreateBill").to(responseEntry);
        return instrumented(CreateBillBussinessTask.class);
    }
}
