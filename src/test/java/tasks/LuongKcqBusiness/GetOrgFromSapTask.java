package tasks.LuongKcqBusiness;

import common.Utilities;
import config.constant;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import tasks.Common.CallAPICommon;
import java.io.File;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetOrgFromSapTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static GetOrgFromSapTask callApiGetOrg(Map<String, String> map,String authen)
    {
        String url = constant.sap_org;
        Object entry = Utilities.getObjectFromString("LuongKCQ\\Get_org_from_sap",map);

        JsonPath responseEntry = CallAPICommon.callApiPostRequest(url,entry,authen);
        Serenity.setSessionVariable("responseGetOrgSap").to(responseEntry);
        return instrumented(GetOrgFromSapTask.class,map);
    }
}
