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

public class SyncEmpTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static SyncEmpTask callApiSyncEmp(Map<String, String> map)
    {
        String endPoint = constant.sync_emp;
        Object entry = Utilities.getObjectFromString("LuongKCQ\\Sync_org",map);

        JsonPath responseEntry = CallAPICommon.callApiPostNoAuthen(endPoint,entry);
        Serenity.setSessionVariable("responseSyncEmp").to(responseEntry);
        return instrumented(SyncEmpTask.class,map);
    }
}
