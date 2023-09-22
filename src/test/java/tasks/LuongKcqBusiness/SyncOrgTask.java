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

public class SyncOrgTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static SyncOrgTask callApiSyncOrg(Map<String, String> map)
    {
        String endPoint = constant.sync_org;
        Object entry = Utilities.getObjectFromString("LuongKCQ\\Sync_org",map);

        JsonPath responseEntry = CallAPICommon.callApiPostNoAuthen(endPoint,entry);
        Serenity.setSessionVariable("responseSyncOrg").to(responseEntry);
        return instrumented(SyncOrgTask.class,map);
    }
}
