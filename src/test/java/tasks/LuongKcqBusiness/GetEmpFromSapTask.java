package tasks.LuongKcqBusiness;

import common.Utilities;
import config.constant;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import tasks.Common.CallAPICommon;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetEmpFromSapTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static GetEmpFromSapTask callApiGetEmp(Map<String, String> map, String authen)
    {
        String url = constant.sap_emp;
        Object entry = Utilities.getObjectFromString("LuongKCQ\\Get_emp_from_sap",map);

        JsonPath responseEntry = CallAPICommon.callApiPostRequest(url,entry,authen);
        Serenity.setSessionVariable("responseGetEmpSap").to(responseEntry);
        return instrumented(GetEmpFromSapTask.class,map);
    }
}
